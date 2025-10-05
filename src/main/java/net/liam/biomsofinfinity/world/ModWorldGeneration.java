package net.liam.biomsofinfinity.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.liam.biomsofinfinity.Biomsofinfinity;
import net.liam.biomsofinfinity.world.biome.ModBiomes;
import net.liam.biomsofinfinity.world.feature.ModPlacedFeatures;
import net.minecraft.world.gen.GenerationStep;

public class ModWorldGeneration {

    public static void generateModWorldGen() {
        Biomsofinfinity.LOGGER.info("Registering biome modifications for " + Biomsofinfinity.MOD_ID);

        // Nebelgranit-Adern NUR in Nebelwipfel
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.NEBELWIPFEL),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.MIST_GRANITE_ORE_PLACED_KEY
        );

        // Leuchtendes Moos NUR in beiden custom Biomen
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.NEBELWIPFEL, ModBiomes.KRISTALLHAIN),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.GLOWING_MOSS_PATCH_PLACED_KEY
        );

        // Ethereal Trees NUR im Kristallhain
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.KRISTALLHAIN),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ETHEREAL_TREE_PLACED_KEY
        );

        // ENTFERNT: Features aus dem gesamten End
        // Keine Features mehr in foundInTheEnd() Biomen

        Biomsofinfinity.LOGGER.info("Biome modifications registered successfully!");
        Biomsofinfinity.LOGGER.info("Nebelwipfel and Kristallhain biomes are now available!");
    }
}
