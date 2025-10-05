package net.liam.biomsofinfinity.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.liam.biomsofinfinity.config.BOIConfig;
import net.liam.biomsofinfinity.systems.boss.BossCooldownManager;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

/**
 * Registers the /boi command tree used for runtime configuration and boss management.
 */
public final class BOICommands {

    private BOICommands() {
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register(BOICommands::register);
    }

    private static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("boi")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("config")
                        .then(CommandManager.literal("reload")
                                .executes(ctx -> {
                                    BOIConfig.load();
                                    ctx.getSource().sendFeedback(() -> Text.translatable("commands.biomsofinfinity.config.reload"), true);
                                    return 1;
                                })))
                .then(CommandManager.literal("boss")
                        .then(CommandManager.literal("enable").executes(ctx -> toggleBosses(ctx.getSource(), true)))
                        .then(CommandManager.literal("disable").executes(ctx -> toggleBosses(ctx.getSource(), false)))
                        .then(CommandManager.literal("resetcooldowns").executes(ctx -> resetCooldowns(ctx.getSource())))));
    }

    private static int toggleBosses(ServerCommandSource source, boolean value) {
        BOIConfig.ConfigData config = BOIConfig.get();
        config.bosses.allowBossSpawns = value;
        BOIConfig.save();
        source.sendFeedback(() -> Text.translatable(value ? "commands.biomsofinfinity.boss.enable" : "commands.biomsofinfinity.boss.disable"), true);
        return 1;
    }

    private static int resetCooldowns(ServerCommandSource source) {
        for (ServerWorld world : source.getServer().getWorlds()) {
            BossCooldownManager.get(world).reset();
        }
        source.sendFeedback(() -> Text.translatable("commands.biomsofinfinity.boss.reset"), true);
        return 1;
    }
}
