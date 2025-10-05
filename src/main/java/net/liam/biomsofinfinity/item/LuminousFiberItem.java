package net.liam.biomsofinfinity.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Crafting component for light-infused technology harvested from Glowshroom stems.
 */
public class LuminousFiberItem extends Item {

    public LuminousFiberItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.biomsofinfinity.luminous_fiber.tooltip"));
    }
}
