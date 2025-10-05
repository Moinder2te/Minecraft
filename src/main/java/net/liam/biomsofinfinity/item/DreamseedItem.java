package net.liam.biomsofinfinity.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

/**
 * Floating garden delicacy that slows descent and restores a little hunger.
 */
public class DreamseedItem extends Item {

    private static final FoodComponent FOOD = new FoodComponent.Builder()
            .hunger(3)
            .saturationModifier(0.4f)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20 * 15, 0), 1.0f)
            .build();

    public DreamseedItem(Settings settings) {
        super(settings.food(FOOD));
    }
}
