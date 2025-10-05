package net.liam.biomsofinfinity.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Chest-slot cloak that grants periodic invisibility while sneaking, perfect for Shadow Isle infiltrations.
 */
public class DarkVeilCloakItem extends ArmorItem {

    public DarkVeilCloakItem(Settings settings) {
        super(BOIArmorMaterials.DARK_VEIL, ArmorItem.Type.CHESTPLATE, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, LivingEntity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient && entity instanceof PlayerEntity player) {
            ItemStack equipped = player.getEquippedStack(EquipmentSlot.CHEST);
            if (equipped == stack && player.isSneaking()) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 40, 0, false, false, true));
            }
        }
    }
}
