package net.liam.biomsofinfinity.block;

import net.liam.biomsofinfinity.Biomsofinfinity;
import net.liam.biomsofinfinity.config.BOIConfig;
import net.liam.biomsofinfinity.registry.ModEntities;
import net.liam.biomsofinfinity.registry.ModItems;
import net.liam.biomsofinfinity.systems.boss.BossCooldownManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Ritual altar that exclusively summons the Shadow King boss when the correct key item is offered.
 */
public class ShadowKingAltarBlock extends Block {

    private static final Text SUMMON_DISABLED = Text.translatable("message.biomsofinfinity.shadow_king.disabled");
    private static final Text SUMMON_COOLDOWN = Text.translatable("message.biomsofinfinity.shadow_king.cooldown");

    public ShadowKingAltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        ServerWorld serverWorld = (ServerWorld) world;
        ItemStack stack = player.getStackInHand(hand);

        if (!stack.isOf(ModItems.SHADOW_HEART)) {
            return ActionResult.PASS;
        }

        BOIConfig.ConfigData config = BOIConfig.get();
        if (!config.bosses.allowBossSpawns || !config.bosses.altarSpawnsOnly) {
            player.sendMessage(SUMMON_DISABLED, true);
            return ActionResult.CONSUME;
        }

        BossCooldownManager cooldowns = BossCooldownManager.get(serverWorld);
        if (!cooldowns.canSummon(serverWorld, ModEntities.SHADOW_KING_ID)) {
            player.sendMessage(SUMMON_COOLDOWN, true);
            return ActionResult.CONSUME;
        }

        if (!player.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        var boss = ModEntities.SHADOW_KING.create(serverWorld);
        if (boss == null) {
            Biomsofinfinity.LOGGER.error("Failed to create Shadow King entity");
            return ActionResult.CONSUME;
        }

        boss.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, world.random.nextFloat() * 360f, 0f);
        serverWorld.spawnEntity(boss);
        cooldowns.onBossSummoned(ModEntities.SHADOW_KING_ID, serverWorld);

        world.playSound(null, pos, SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.HOSTILE, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);

        if (config.bosses.announceBossEvents) {
            for (ServerPlayerEntity serverPlayer : serverWorld.getPlayers()) {
                serverPlayer.sendMessage(Text.translatable("message.biomsofinfinity.shadow_king.spawn", player.getDisplayName()), false);
            }
        }

        return ActionResult.SUCCESS;
    }
}
