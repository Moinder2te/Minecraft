package net.liam.biomsofinfinity.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.liam.biomsofinfinity.world.biome.ModBiomes;
import net.liam.biomsofinfinity.world.feature.ModConfiguredFeatures;
import net.liam.biomsofinfinity.world.feature.ModPlacedFeatures;

import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ModDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(BOIBlockStateProvider::new);
        pack.addProvider(BOIItemModelProvider::new);
        pack.addProvider(BOIBlockLootTableProvider::new);
        pack.addProvider(BOIEntityLootTableProvider::new);
        pack.addProvider(BOIRecipeProvider::new);
        pack.addProvider(BOIAdvancementProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
    }
}
