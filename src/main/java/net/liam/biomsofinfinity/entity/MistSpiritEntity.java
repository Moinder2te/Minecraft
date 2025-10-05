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

public class MistSpiritEntity extends HostileEntity {

    public MistSpiritEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createMistSpiritAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.FOLLOW_RANGE, 16.0);
    }

    public boolean tryAttack(LivingEntity target) {
        boolean attacked = false;

        // Verwende die korrekte Damage-Methode für MC 1.21.8
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            float damage = (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
            attacked = target.damage(serverWorld, this.getDamageSources().mobAttack(this), damage);

            if (attacked && target instanceof PlayerEntity) {
                // Halluzinations-Effekt
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));

                // Nebel-Partikel um das Ziel
                serverWorld.spawnParticles(ParticleTypes.MYCELIUM,
                    target.getX(), target.getY() + 1, target.getZ(),
                    15, 1.0, 1.0, 1.0, 0.1);
            }
        }

        return attacked;
    }

    @Override
    public void tick() {
        super.tick();

        // Konstanter Nebel-Effekt
        if (this.getWorld() instanceof ServerWorld serverWorld && this.age % 10 == 0) {
            serverWorld.spawnParticles(ParticleTypes.MYCELIUM,
                this.getX(), this.getY() + 1, this.getZ(),
                3, 0.3, 0.3, 0.3, 0.02);
        }

        // Teleportation bei niedrigem Leben (wie Enderman)
        if (this.getHealth() < this.getMaxHealth() * 0.3f && this.random.nextFloat() < 0.1f) {
            this.teleportRandomly();
        }
    }

    private boolean teleportRandomly() {
        if (!this.getWorld().isClient() && this.isAlive()) {
            double x = this.getX() + (this.random.nextDouble() - 0.5) * 16.0;
            double y = this.getY() + (this.random.nextInt(16) - 8);
            double z = this.getZ() + (this.random.nextDouble() - 0.5) * 16.0;
            return this.teleportTo(x, y, z);
        }
        return false;
    }

    private boolean teleportTo(double x, double y, double z) {
        boolean teleported = this.teleport(x, y, z, true);
        if (teleported && this.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.PORTAL,
                this.getX(), this.getY(), this.getZ(),
                20, 0.5, 0.5, 0.5, 0.2);
        }
        return teleported;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ENDERMAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_ENDERMAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDERMAN_DEATH;
    }
}
