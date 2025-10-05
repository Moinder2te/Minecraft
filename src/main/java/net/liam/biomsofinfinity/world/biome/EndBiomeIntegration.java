package net.liam.biomsofinfinity.world.biome;

import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.liam.biomsofinfinity.Biomsofinfinity;
import net.liam.biomsofinfinity.config.BOIConfig;

public class EndBiomeIntegration {

    public static void registerEndBiomes() {
        try {
            Biomsofinfinity.LOGGER.info("Registering End biomes for " + Biomsofinfinity.MOD_ID);

            // Nebelwipfel als Hochland-Biom (gleichwertig mit End-Hochland)
            TheEndBiomes.addHighlandsBiome(ModBiomes.NEBELWIPFEL, 1.0);

            // Kristallhain als kleinere Inseln für mehr Variation
            TheEndBiomes.addSmallIslandsBiome(ModBiomes.KRISTALLHAIN, 1.0);

            // Optional: Auch als Mitte-Inseln für Übergänge
            TheEndBiomes.addMidlandsBiome(ModBiomes.NEBELWIPFEL, ModBiomes.KRISTALLHAIN, 0.5);

            BOIConfig.ConfigData config = BOIConfig.get();
            if (config.worldgen.enableAllBiomes) {
                double glowWeight = config.worldgen.biomeWeights.getOrDefault("glowshroom_forest", 6);
                if (glowWeight > 0) {
                    TheEndBiomes.addHighlandsBiome(ModBiomes.GLOWSHROOM_FOREST, glowWeight);
                }

                double gardenWeight = config.worldgen.biomeWeights.getOrDefault("floating_gardens", 5);
                if (gardenWeight > 0) {
                    TheEndBiomes.addMidlandsBiome(ModBiomes.GLOWSHROOM_FOREST, ModBiomes.FLOATING_GARDENS, gardenWeight);
                }

                double shadowWeight = config.worldgen.biomeWeights.getOrDefault("shadow_isles", 3);
                if (shadowWeight > 0) {
                    TheEndBiomes.addSmallIslandsBiome(ModBiomes.SHADOW_ISLES, shadowWeight);
                }
            }

            Biomsofinfinity.LOGGER.info("End biomes registered successfully - Nebelwipfel and Kristallhain are now part of the End dimension!");
        } catch (Exception e) {
            Biomsofinfinity.LOGGER.warn("Could not register End biomes (this is normal during bootstrap): " + e.getMessage());
        }
    }
}
