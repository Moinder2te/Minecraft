package net.liam.biomsofinfinity.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class CrystalSwordItem extends Item {

    public CrystalSwordItem(Item.Settings settings) {
        super(settings.maxDamage(ModToolMaterials.CrystalMaterial.DURABILITY));
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.getWorld() instanceof ServerWorld serverWorld) {
            // Kristall-Explosionseffekt
            serverWorld.spawnParticles(ParticleTypes.END_ROD,
                target.getX(), target.getY() + 1, target.getZ(),
                10, 0.5, 0.5, 0.5, 0.1);

            // Chance für Verlangsamung
            if (serverWorld.random.nextFloat() < 0.3f) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
            }

            // Kristallines Klingen-Geräusch
            serverWorld.playSound(null, target.getBlockPos(),
                SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 0.5f, 1.5f);
        }

        // Damage the item
        stack.setDamage(stack.getDamage() + 1);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
