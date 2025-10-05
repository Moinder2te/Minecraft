package net.liam.biomsofinfinity.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Ritual key harvested from the depths of the Shadow Isles. Required to awaken the Shadow King.
 */
public class ShadowHeartItem extends Item {

    public ShadowHeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.bioms-of-infinity.shadow_heart.tooltip"));
    }
}
