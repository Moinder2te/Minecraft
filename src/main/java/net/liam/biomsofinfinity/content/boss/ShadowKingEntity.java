package net.liam.biomsofinfinity.content.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Phase-based boss for the Shadow Isles. Teleports, cloaks, and summons mirror strikes.
 */
public class ShadowKingEntity extends BaseBossEntity {

    private int phaseTimer;

    public ShadowKingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 500;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.1, true));
        this.goalSelector.add(7, new WanderAroundGoal(this, 0.6));
        this.targetSelector.add(1, new net.minecraft.entity.ai.goal.ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!world.isClient) {
            phaseTimer++;
            PlayerEntity target = getTarget();
            if (target != null && phaseTimer % 100 == 0) {
                performShadowStep(target);
            }
            if (getPhase() >= 1 && phaseTimer % 80 == 0) {
                castVeil();
            }
            if (getPhase() >= 2 && phaseTimer % 60 == 0) {
                unleashMirrorStrike(target);
            }
        }
    }

    private void performShadowStep(PlayerEntity target) {
        Vec3d offset = new Vec3d(world.random.nextDouble() - 0.5, 0, world.random.nextDouble() - 0.5).normalize().multiply(4.0);
        refreshPositionAndAngles(target.getX() + offset.x, target.getY(), target.getZ() + offset.z, getYaw(), getPitch());
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SMOKE, getX(), getY() + 1, getZ(), 30, 0.5, 0.5, 0.5, 0.01);
            world.playSound(null, getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0f, 0.6f);
        }
    }

    private void castVeil() {
        addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 60, 0, false, false));
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.ASH, getX(), getY() + 1, getZ(), 50, 0.6, 0.6, 0.6, 0.02);
        }
    }

    private void unleashMirrorStrike(PlayerEntity target) {
        if (target == null) {
            return;
        }
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
        world.playSound(null, target.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_ATTACK, SoundCategory.HOSTILE, 1.1f, 0.5f);
    }

    @Override
    protected Text getBossBarTitle() {
        return Text.translatable("entity.biomsofinfinity.shadow_king");
    }

    @Override
    public String getBossId() {
        return "shadow_king";
    }
}
