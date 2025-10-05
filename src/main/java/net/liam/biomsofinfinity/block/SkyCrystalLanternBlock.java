package net.liam.biomsofinfinity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Portable lantern that can toggle its brightness, doubling as a utility light block for Floating Garden expeditions.
 */
public class SkyCrystalLanternBlock extends Block {

    public static final IntProperty LIGHT = Properties.LEVEL_15;

    public SkyCrystalLanternBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIGHT, 15));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT);
    }

    @Override
    public int getLuminance(BlockState state) {
        return state.get(LIGHT);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            int newValue = state.get(LIGHT) == 15 ? 4 : 15;
            world.setBlockState(pos, state.with(LIGHT, newValue), Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.BLOCKS, 0.6f, 1.2f);
        }
        return ActionResult.SUCCESS;
    }
}
