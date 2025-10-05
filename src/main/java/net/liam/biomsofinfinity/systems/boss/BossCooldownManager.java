package net.liam.biomsofinfinity.systems.boss;

import net.liam.biomsofinfinity.config.BOIConfig;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Persists boss summon cooldowns and the active boss count per world.
 */
public class BossCooldownManager extends PersistentState {

    private static final String STORAGE_KEY = "boi_boss_cooldowns";

    private final Map<String, Long> lastBossSummonDay = new HashMap<>();
    private long lastGlobalDay = Long.MIN_VALUE;
    private int activeBosses;

    public static BossCooldownManager get(ServerWorld world) {
        PersistentStateManager manager = world.getPersistentStateManager();
        return manager.getOrCreate(BossCooldownManager::fromNbt, BossCooldownManager::new, STORAGE_KEY);
    }

    private static BossCooldownManager fromNbt(NbtCompound nbt) {
        BossCooldownManager state = new BossCooldownManager();
        state.lastGlobalDay = nbt.getLong("LastGlobalDay");
        state.activeBosses = nbt.getInt("ActiveBosses");
        NbtCompound bosses = nbt.getCompound("BossTimes");
        for (String key : bosses.getKeys()) {
            state.lastBossSummonDay.put(key, bosses.getLong(key));
        }
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putLong("LastGlobalDay", lastGlobalDay);
        nbt.putInt("ActiveBosses", activeBosses);
        NbtCompound bosses = new NbtCompound();
        lastBossSummonDay.forEach(bosses::putLong);
        nbt.put("BossTimes", bosses);
        return nbt;
    }

    public boolean canSummon(ServerWorld world, String bossId) {
        BOIConfig.ConfigData config = BOIConfig.get();
        if (!config.bosses.allowBossSpawns || !config.bosses.altarSpawnsOnly) {
            return false;
        }
        if (config.bosses.maxConcurrentBosses > 0 && activeBosses >= config.bosses.maxConcurrentBosses) {
            return false;
        }

        long currentDay = world.getTimeOfDay() / 24000L;
        long globalCooldown = config.bosses.cooldowns.globalDays;
        long perBossCooldown = config.bosses.cooldowns.perBossDays;

        if (lastGlobalDay != Long.MIN_VALUE && currentDay - lastGlobalDay < globalCooldown) {
            return false;
        }

        long lastBossDay = lastBossSummonDay.getOrDefault(bossId, Long.MIN_VALUE);
        return lastBossDay == Long.MIN_VALUE || currentDay - lastBossDay >= perBossCooldown;
    }

    public void onBossSummoned(String bossId, ServerWorld world) {
        activeBosses++;
        long currentDay = world.getTimeOfDay() / 24000L;
        lastGlobalDay = currentDay;
        lastBossSummonDay.put(bossId, currentDay);
        markDirty();
    }

    public void onBossDefeated(String bossId) {
        if (activeBosses > 0) {
            activeBosses--;
            markDirty();
        }
    }

    public void reset() {
        lastBossSummonDay.clear();
        lastGlobalDay = Long.MIN_VALUE;
        activeBosses = 0;
        markDirty();
    }

    public int getActiveBosses() {
        return activeBosses;
    }
}
