package net.liam.biomsofinfinity.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class CrystalSpiderEntity extends HostileEntity {

    public CrystalSpiderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4f));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createCrystalSpiderAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 12.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.FOLLOW_RANGE, 12.0);
    }

    public boolean tryAttack(LivingEntity target) {
        boolean attacked = false;

        if (this.getWorld() instanceof ServerWorld serverWorld) {
            float damage = (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
            attacked = target.damage(serverWorld, this.getDamageSources().mobAttack(this), damage);

            if (attacked && target instanceof PlayerEntity) {
                // Kristall-Gift-Effekt
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 0));

                // Kristallsplitter-Partikel
                serverWorld.spawnParticles(ParticleTypes.CRIT,
                        target.getX(), target.getY() + 1, target.getZ(),
                        8, 0.5, 0.5, 0.5, 0.2);
            }
        }

        return attacked;
    }

    @Override
    public void tick() {
        super.tick();

        // Kristall-Glitzer um die Spinne
        if (this.getWorld() instanceof ServerWorld serverWorld && this.age % 25 == 0) {
            serverWorld.spawnParticles(ParticleTypes.ENCHANT,
                    this.getX(), this.getY() + 0.3, this.getZ(),
                    2, 0.3, 0.1, 0.3, 0.02);
        }

        // Verhindert Baby-Form
        this.setBaby(false);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SPIDER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SPIDER_DEATH;
    }

    // Klettern-Funktionalität (angepasst für MC 1.21.8)
    public boolean canClimb() {
        return true;
    }
}
