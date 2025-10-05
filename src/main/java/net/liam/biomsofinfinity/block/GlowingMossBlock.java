package net.liam.biomsofinfinity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class GlowingMossBlock extends Block implements Fertilizable {

    public GlowingMossBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        // Note: Particle spawning requires client-side implementation
        // For now, we'll keep this method simple without particles
        // Particles can be added through a client-side mixin or event handler
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            // Play mystical sound when interacted with
            world.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 0.5f, 1.5f);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        // Spread glowing moss to nearby moss blocks
        for (int i = 0; i < 4; ++i) {
            BlockPos targetPos = pos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            BlockState targetState = world.getBlockState(targetPos);

            if (targetState.isOf(net.minecraft.block.Blocks.MOSS_BLOCK)) {
                world.setBlockState(targetPos, ModBlocks.GLOWING_MOSS_BLOCK.getDefaultState());
                world.playSound(null, targetPos, SoundEvents.BLOCK_MOSS_PLACE, SoundCategory.BLOCKS, 0.3f, 1.2f);
            }
        }
    }
}
