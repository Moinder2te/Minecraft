package net.liam.biomsofinfinity.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CrystalGolemEntity extends HostileEntity {

    public CrystalGolemEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new CrystalGolemAttackGoal(this, 1.0, false));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.6));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(5, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createCrystalGolemAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 40.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.FOLLOW_RANGE, 20.0);
    }

    public boolean tryAttack(LivingEntity target) {
        boolean attacked = false;

        // Verwende die korrekte Damage-Methode für MC 1.21.8
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            float damage = (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
            attacked = target.damage(serverWorld, this.getDamageSources().mobAttack(this), damage);

            if (attacked) {
                // Kristall-Explosion bei Nahkampf
                serverWorld.spawnParticles(ParticleTypes.END_ROD,
                    target.getX(), target.getY() + 1, target.getZ(),
                    20, 1.0, 1.0, 1.0, 0.2);

                serverWorld.playSound(null, target.getBlockPos(),
                    SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, net.minecraft.sound.SoundCategory.HOSTILE,
                    1.0f, 0.8f);
            }
        }

        return attacked;
    }

    @Override
    public void tick() {
        super.tick();

        // Kristall-Glitzer-Effekt
        if (this.getWorld() instanceof ServerWorld serverWorld && this.age % 15 == 0) {
            serverWorld.spawnParticles(ParticleTypes.ENCHANT,
                this.getX(), this.getY() + 1.5, this.getZ(),
                3, 0.5, 0.5, 0.5, 0.05);
        }

        // Fernkampf-Angriff mit Lichtstrahlen
        LivingEntity target = this.getTarget();
        if (target != null && this.distanceTo(target) > 4.0f && this.age % 60 == 0) {
            this.shootLightBeam(target);
        }
    }

    private void shootLightBeam(LivingEntity target) {
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            // Erstelle einen Shulker-Bullet als "Lichtstrahl"
            ShulkerBulletEntity bullet = new ShulkerBulletEntity(this.getWorld(), this, target, null);
            bullet.setPosition(this.getX(), this.getY() + 1.5, this.getZ());
            this.getWorld().spawnEntity(bullet);

            // Kristallines Schuss-Geräusch
            serverWorld.playSound(null, this.getBlockPos(),
                SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, net.minecraft.sound.SoundCategory.HOSTILE,
                0.8f, 1.5f);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    // Spezielle Angriffs-Goal für den Kristall-Golem
    private static class CrystalGolemAttackGoal extends MeleeAttackGoal {
        public CrystalGolemAttackGoal(CrystalGolemEntity golem, double speed, boolean pauseWhenMobIdle) {
            super(golem, speed, pauseWhenMobIdle);
        }

        @Override
        protected void attack(LivingEntity target) {
            if (this.canAttack(target)) {
                this.resetCooldown();
                // Verwende die korrekte tryAttack-Methode
                ((CrystalGolemEntity) this.mob).tryAttack(target);

                // Zusätzlicher Rückstoß-Effekt
                Vec3d vec3d = target.getPos().subtract(this.mob.getPos()).normalize().multiply(1.5);
                target.setVelocity(target.getVelocity().add(vec3d.x, 0.3, vec3d.z));
            }
        }
    }
}
