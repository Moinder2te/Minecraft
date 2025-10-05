package net.liam.biomsofinfinity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Soft carpet that rewards players with night vision pips while traversing the Glowshroom Forest.
 */
public class LuminescentMossBlock extends Block {

    public LuminescentMossBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (!world.isClient && entity instanceof net.minecraft.entity.LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 80, 0, false, false));
        }
    }
}
