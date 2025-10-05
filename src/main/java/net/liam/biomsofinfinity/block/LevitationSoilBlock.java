package net.liam.biomsofinfinity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Soft floating soil that gently boosts players upward, simulating buoyant islands.
 */
public class LevitationSoilBlock extends Block {

    public LevitationSoilBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance * 0.2f);
        if (!world.isClient) {
            Vec3d velocity = entity.getVelocity();
            if (velocity.y < 0.0) {
                entity.setVelocity(velocity.x * 0.6, 0.3, velocity.z * 0.6);
                entity.velocityDirty = true;
            }
        }
    }
}
