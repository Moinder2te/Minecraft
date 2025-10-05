package net.liam.biomsofinfinity.world.biome;

import net.liam.biomsofinfinity.Biomsofinfinity;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class ModBiomes {

    // Nebelwipfel - mystische Gebirgskette mit schwebenden Inseln
    public static final RegistryKey<Biome> NEBELWIPFEL = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(Biomsofinfinity.MOD_ID, "nebelwipfel"));

    // Kristallhain - verwunschener Wald aus durchscheinenden Kristallen
    public static final RegistryKey<Biome> KRISTALLHAIN = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(Biomsofinfinity.MOD_ID, "kristallhain"));

    // Legacy Namen für Rückwärtskompatibilität
    public static final RegistryKey<Biome> MIST_PEAKS = NEBELWIPFEL;
    public static final RegistryKey<Biome> CRYSTAL_GROVE = KRISTALLHAIN;

    public static void bootstrap(Registerable<Biome> context) {
        context.register(NEBELWIPFEL, createNebelwipfel(context));
        context.register(KRISTALLHAIN, createKristallhain(context));
    }

    public static Biome createNebelwipfel(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        // Spawn settings für Nebelgeister und Nebelwölfe werden später erweitert

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        // Füge Mist Granite Ore als Underground Feature hinzu
        biomeBuilder.feature(net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES,
            context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(
                net.liam.biomsofinfinity.world.feature.ModPlacedFeatures.MIST_GRANITE_ORE_PLACED_KEY));

        // Füge Glowing Moss als Surface Feature hinzu
        biomeBuilder.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
            context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(
                net.liam.biomsofinfinity.world.feature.ModPlacedFeatures.GLOWING_MOSS_PATCH_PLACED_KEY));

        return new Biome.Builder()
                .precipitation(false) // Kein Regen, nur Nebel
                .downfall(0.1f) // Sehr wenig Niederschlag
                .temperature(0.2f) // Kühl in den Bergen
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x7BB3FF) // Zartes Blau wie beschrieben
                        .waterFogColor(0x4A90E2) // Mystisches Blau
                        .skyColor(0xC8E6FF) // Pastellblauer Himmel
                        .grassColor(0x98FFB3) // Mintgrün für Vegetation
                        .foliageColor(0xA5FFCC) // Durchscheinende, glühende Blätter
                        .fogColor(0xE6F3FF) // Heller Nebel wie Geisterschleier
                        .moodSound(BiomeMoodSound.CAVE)
                        .build())
                .build();
    }

    public static Biome createKristallhain(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        // Spawn settings für Kristall-Golems und Kristallspinnen

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        // Füge Glowing Moss als Surface Feature hinzu
        biomeBuilder.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
            context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(
                net.liam.biomsofinfinity.world.feature.ModPlacedFeatures.GLOWING_MOSS_PATCH_PLACED_KEY));

        // Füge Ethereal Trees als Surface Feature hinzu
        biomeBuilder.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION,
            context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(
                net.liam.biomsofinfinity.world.feature.ModPlacedFeatures.ETHEREAL_TREE_PLACED_KEY));

        return new Biome.Builder()
                .precipitation(false) // Magische Atmosphäre ohne Regen
                .downfall(0.0f) // Trocken, nur kristalline Atmosphäre
                .temperature(0.4f) // Mild temperiert durch Kristallenergie
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x9966CC) // Violett-bläulich für kristallines Wasser
                        .waterFogColor(0x6633AA) // Tiefer Violettton
                        .skyColor(0xB19CD9) // Violett-blauer Himmel mit Lichtbrechung
                        .grassColor(0x8A5FBF) // Dunkles Violett für Vegetation
                        .foliageColor(0x9A6FCF) // Kristallines Violett für Blätter
                        .fogColor(0xD4C4F0) // Heller violetter Nebel mit Prismeneffekt
                        .moodSound(BiomeMoodSound.CAVE)
                        .build())
                .build();
    }
}
