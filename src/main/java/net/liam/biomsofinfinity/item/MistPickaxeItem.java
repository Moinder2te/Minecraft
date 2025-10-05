package net.liam.biomsofinfinity.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MistPickaxeItem extends Item {

    public MistPickaxeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (world instanceof ServerWorld serverWorld) {
            // Nebel-Effekt beim Abbauen
            serverWorld.spawnParticles(ParticleTypes.MYCELIUM,
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                8, 0.3, 0.3, 0.3, 0.05);

            // Chance für zusätzlichen Geist-Essenz Drop
            if (serverWorld.random.nextFloat() < 0.15f) {
                miner.getWorld().spawnEntity(new net.minecraft.entity.ItemEntity(
                    miner.getWorld(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    new ItemStack(ModItems.GHOST_ESSENCE)));
                serverWorld.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                    SoundCategory.PLAYERS, 0.3f, 1.8f);
            }
        }

        // Damage the item
        stack.setDamage(stack.getDamage() + 1);
        
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
