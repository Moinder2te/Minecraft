package net.liam.biomsofinfinity.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MistWolfEntity extends PathAwareEntity {

    public MistWolfEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));

        // Nebelwölfe sind normalerweise friedlich, werden aber aggressiv wenn angegriffen
        this.targetSelector.add(1, new RevengeGoal(this));
    }

    public static DefaultAttributeContainer.Builder createMistWolfAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 16.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.FOLLOW_RANGE, 10.0);
    }

    @Override
    public void tick() {
        super.tick();

        // Geister-Partikel-Effekt
        if (this.getWorld() instanceof ServerWorld serverWorld && this.age % 20 == 0) {
            serverWorld.spawnParticles(ParticleTypes.END_ROD,
                this.getX(), this.getY() + 0.5, this.getZ(),
                2, 0.2, 0.2, 0.2, 0.01);
        }

        // Lautloses Bewegen - reduziert Geräusche
        if (this.isOnGround() && this.getVelocity().horizontalLengthSquared() > 0.01) {
            if (this.random.nextFloat() < 0.3f) {
                // Manchmal komplett lautlos
                this.setNoGravity(true);
            } else {
                this.setNoGravity(false);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        // Sehr leise, geisterhafte Geräusche - verwende verfügbare Sounds
        return this.random.nextFloat() < 0.1f ? SoundEvents.ENTITY_FOX_AMBIENT : null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_FOX_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_FOX_DEATH;
    }
}
