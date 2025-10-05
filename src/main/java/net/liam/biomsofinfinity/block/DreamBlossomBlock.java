package net.liam.biomsofinfinity.block;

import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * Floating garden flower that sprinkles gentle particles and grants slow falling when used in Suspicious Stew.
 */
public class DreamBlossomBlock extends FlowerBlock {

    public DreamBlossomBlock(Settings settings) {
        super(StatusEffects.SLOW_FALLING, 8, settings);
    }

    @Override
    public void randomDisplayTick(net.minecraft.block.BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.15f) {
            world.addParticle(ParticleTypes.CLOUD,
                    pos.getX() + 0.5 + random.nextGaussian() * 0.2,
                    pos.getY() + 0.8,
                    pos.getZ() + 0.5 + random.nextGaussian() * 0.2,
                    0.0, 0.02, 0.0);
        }
    }

    @Override
    public StatusEffect getEffectInStew() {
        return StatusEffects.SLOW_FALLING;
    }
}
