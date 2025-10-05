package net.liam.biomsofinfinity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MistMushroomBlock extends Block {

    public MistMushroomBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        // Note: Particle spawning requires client-side implementation
        // For now, we'll keep this method simple without particles
        // Mist particles can be added through a client-side mixin or event handler
    }
}
