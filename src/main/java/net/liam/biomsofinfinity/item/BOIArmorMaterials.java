package net.liam.biomsofinfinity.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.EnumMap;
import java.util.Map;

/**
 * Custom armor materials used across Bioms of Infinity equipment.
 */
public final class BOIArmorMaterials {

    private BOIArmorMaterials() {
    }

    private static final Map<ArmorItem.Type, Integer> DARK_VEIL_DURABILITY = new EnumMap<>(ArmorItem.Type.class);
    private static final Map<ArmorItem.Type, Integer> DARK_VEIL_PROTECTION = new EnumMap<>(ArmorItem.Type.class);

    static {
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            DARK_VEIL_DURABILITY.put(type, 20);
        }
        DARK_VEIL_PROTECTION.put(ArmorItem.Type.HELMET, 2);
        DARK_VEIL_PROTECTION.put(ArmorItem.Type.CHESTPLATE, 6);
        DARK_VEIL_PROTECTION.put(ArmorItem.Type.LEGGINGS, 5);
        DARK_VEIL_PROTECTION.put(ArmorItem.Type.BOOTS, 2);
    }

    public static final ArmorMaterial DARK_VEIL = new ArmorMaterial() {
        @Override
        public int getDurability(ArmorItem.Type type) {
            return DARK_VEIL_DURABILITY.getOrDefault(type, 15);
        }

        @Override
        public int getProtection(ArmorItem.Type type) {
            return DARK_VEIL_PROTECTION.getOrDefault(type, 1);
        }

        @Override
        public int getEnchantability() {
            return 25;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }

        @Override
        public String getName() {
            return "dark_veil";
        }

        @Override
        public float getToughness() {
            return 1.0f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.0f;
        }
    };
}
