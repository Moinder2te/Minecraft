package net.liam.biomsofinfinity.block;

import net.minecraft.block.PlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Thorny plant that inflicts minor damage and a brief wither effect, guarding the Shadow Isles ritual sites.
 */
public class UmbralThornBlock extends PlantBlock {

    public UmbralThornBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(net.minecraft.block.BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && entity instanceof net.minecraft.entity.LivingEntity living) {
            living.damage(DamageSource.MAGIC, 1.0f);
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 0, false, true));
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}
