package net.liam.biomsofinfinity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Bioluminescent mushroom cap that softly emits particles to sell the atmosphere of the Glowshroom Forest.
 */
public class GlowshroomCapBlock extends Block {

    public GlowshroomCapBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.2f) {
            double x = pos.getX() + 0.5 + random.nextGaussian() * 0.1;
            double y = pos.getY() + 0.8 + random.nextGaussian() * 0.05;
            double z = pos.getZ() + 0.5 + random.nextGaussian() * 0.1;
            world.addParticle(ParticleTypes.END_ROD, x, y, z, 0.0, 0.01, 0.0);
        }
    }

}
