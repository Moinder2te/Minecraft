package net.liam.biomsofinfinity.content.boss;

import net.liam.biomsofinfinity.systems.boss.BossCooldownManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

/**
 * Shared base class for all Bioms of Infinity bosses.
 */
public abstract class BaseBossEntity extends HostileEntity {

    private final ServerBossBar bossBar;
    private int phase;
    private int ticksWithoutPlayer;

    protected BaseBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = new ServerBossBar(Text.empty(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS);
        this.phase = 0;
        this.ticksWithoutPlayer = 0;
    }

    public static DefaultAttributeContainer.Builder createBossAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 320.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.8D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClient) {
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            this.bossBar.setName(getBossBarTitle());
            this.updatePhase();
            boolean playerNearby = !this.world.getEntitiesByClass(PlayerEntity.class, new Box(this.getBlockPos()).expand(48.0), Entity::isAlive).isEmpty();
            if (!playerNearby) {
                ticksWithoutPlayer++;
            } else {
                ticksWithoutPlayer = 0;
            }

            if (ticksWithoutPlayer > 20 * 60) {
                this.discard();
                if (this.world instanceof ServerWorld serverWorld) {
                    BossCooldownManager.get(serverWorld).onBossDefeated(getBossId());
                }
            }
        }
    }

    protected void updatePhase() {
        int newPhase;
        float health = this.getHealth() / this.getMaxHealth();
        if (health <= 0.33f) {
            newPhase = 2;
        } else if (health <= 0.66f) {
            newPhase = 1;
        } else {
            newPhase = 0;
        }

        if (newPhase != this.phase) {
            this.phase = newPhase;
            onPhaseChanged(newPhase);
        }
    }

    protected void onPhaseChanged(int newPhase) {
        if (!this.world.isClient) {
            this.world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_WARDEN_ROAR, SoundCategory.HOSTILE, 1.2f, 1.0f);
        }
    }

    protected abstract Text getBossBarTitle();

    public abstract String getBossId();

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void onDeath(net.minecraft.entity.damage.DamageSource source) {
        super.onDeath(source);
        if (!this.world.isClient && this.world instanceof ServerWorld serverWorld) {
            BossCooldownManager.get(serverWorld).onBossDefeated(getBossId());
            this.bossBar.clearPlayers();
        }
    }

    public int getPhase() {
        return phase;
    }
}
