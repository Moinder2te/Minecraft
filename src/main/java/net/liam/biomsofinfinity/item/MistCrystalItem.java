package net.liam.biomsofinfinity.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class MistCrystalItem extends Item {

    public MistCrystalItem(Settings settings) {
        super(settings);
    }

    // Tooltip method for MC 1.21.8 (no @Override as signature has changed)
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.bioms-of-infinity.mist_crystal.tooltip")
                .formatted(Formatting.AQUA, Formatting.ITALIC));
        tooltip.add(Text.translatable("item.bioms-of-infinity.mist_crystal.tooltip2")
                .formatted(Formatting.GRAY));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true; // Mystischer Glanz
    }

    // Custom method for particle effects when item is held
    public void onInventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        if (world instanceof ServerWorld serverWorld && selected && world.random.nextInt(40) == 0) {
            // Spawn mist particles around player when held
            serverWorld.spawnParticles(ParticleTypes.END_ROD,
                entity.getX(), entity.getY() + 1, entity.getZ(),
                3, 0.5, 0.5, 0.5, 0.02);
        }
    }
}
