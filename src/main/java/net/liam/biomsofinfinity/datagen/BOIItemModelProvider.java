package net.liam.biomsofinfinity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricItemModelProvider;
import net.liam.biomsofinfinity.registry.ModItems;
import net.minecraft.data.client.Models;

/**
 * Generates item models for new gear and resources.
 */
public class BOIItemModelProvider extends FabricItemModelProvider {

    public BOIItemModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateItemModels() {
        Models.GENERATED.upload(ModItems.GLOWSHROOM_ESSENCE, texture(ModItems.GLOWSHROOM_ESSENCE), this.writer);
        Models.GENERATED.upload(ModItems.LUMINOUS_FIBER, texture(ModItems.LUMINOUS_FIBER), this.writer);
        Models.GENERATED.upload(ModItems.DREAMSEED, texture(ModItems.DREAMSEED), this.writer);
        Models.GENERATED.upload(ModItems.SHADOW_HEART, texture(ModItems.SHADOW_HEART), this.writer);
        Models.GENERATED.upload(ModItems.DARK_VEIL_CLOAK, texture(ModItems.DARK_VEIL_CLOAK), this.writer);
    }
}
