package net.liam.biomsofinfinity.block;

import net.minecraft.block.VineBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Hanging vine that briefly grants slow falling when touched, making aerial navigation safer.
 */
public class SkyVineBlock extends VineBlock {

    public SkyVineBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(net.minecraft.block.BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (!world.isClient && entity instanceof net.minecraft.entity.LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40, 0, false, false));
        }
    }
}
