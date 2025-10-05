package net.liam.biomsofinfinity.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Crafting component for light-infused technology harvested from Glowshroom stems.
 */
public class LuminousFiberItem extends Item {

    public LuminousFiberItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.bioms-of-infinity.luminous_fiber.tooltip"));
    }
}
