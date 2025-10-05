package net.liam.biomsofinfinity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.liam.biomsofinfinity.block.ModBlocks;
import net.liam.biomsofinfinity.command.BOICommands;
import net.liam.biomsofinfinity.config.BOIConfig;
import net.liam.biomsofinfinity.entity.ModEntities;
import net.liam.biomsofinfinity.item.ModItems;
import net.liam.biomsofinfinity.world.ModWorldGeneration;
import net.minecraft.registry.RegistryKeys;
import net.liam.biomsofinfinity.world.feature.ModConfiguredFeatures;
import net.liam.biomsofinfinity.world.feature.ModPlacedFeatures;
import net.liam.biomsofinfinity.world.biome.ModBiomes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Biomsofinfinity implements ModInitializer {
	public static final String MOD_ID = "bioms-of-infinity";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        @Override
        public void onInitialize() {
                // This code runs as soon as Minecraft is in a mod-load-ready state.
                // However, some things (like resources) may still be uninitialized.
                // Proceed with mild caution.

                LOGGER.info("Initializing Bioms of Infinity - Adding mystical dimensions to Minecraft!");

                BOIConfig.load();

                // Register all mod content
                ModBlocks.registerModBlocks();
                ModItems.registerModItems();
                ModEntities.registerModEntities();

                BOICommands.register();

		// --- Dynamic Registry Setup für Worldgen (1.21.x) ---
		DynamicRegistrySetupCallback.EVENT.register(registryManager -> {
			// Diese wird automatisch von Fabric aufgerufen wenn die Dynamic Registries geladen werden
			LOGGER.info("Setting up dynamic registries for worldgen features");

			// Die Biome werden über die ModBiomes-Klasse registriert (diese wird von Fabric automatisch aufgerufen)
			// Features sind bereits über JSON-Dateien definiert
		});
		// --- Ende: Dynamic Registry Setup ---

		ModWorldGeneration.generateModWorldGen();

		// End-Biome Integration nach der Weltgenerierung
		net.liam.biomsofinfinity.world.biome.EndBiomeIntegration.registerEndBiomes();

                LOGGER.info("Bioms of Infinity loaded successfully! Mist Peaks and Crystal Grove await...");
        }
}