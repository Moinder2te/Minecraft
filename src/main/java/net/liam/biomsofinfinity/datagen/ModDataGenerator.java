package net.liam.biomsofinfinity.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.liam.biomsofinfinity.world.biome.ModBiomes;
import net.liam.biomsofinfinity.world.feature.ModConfiguredFeatures;
import net.liam.biomsofinfinity.world.feature.ModPlacedFeatures;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Hier können weitere Datagen-Provider hinzugefügt werden
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        // Registriere alle Weltgenerierungs-Features
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
    }
}
