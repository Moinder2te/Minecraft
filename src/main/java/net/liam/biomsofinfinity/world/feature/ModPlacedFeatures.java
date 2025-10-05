package net.liam.biomsofinfinity.world.feature;

import net.liam.biomsofinfinity.Biomsofinfinity;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {

    // PlacedFeature Keys für die Weltgenerierung
    public static final RegistryKey<PlacedFeature> MIST_GRANITE_ORE_PLACED_KEY = registerKey("mist_granite_ore_placed");
    public static final RegistryKey<PlacedFeature> GLOWING_MOSS_PATCH_PLACED_KEY = registerKey("glowing_moss_patch_placed");
    public static final RegistryKey<PlacedFeature> ETHEREAL_TREE_PLACED_KEY = registerKey("ethereal_tree_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Nebelgranit-Adern spawnen häufig in Nebelwipfel (Underground)
        register(context, MIST_GRANITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MIST_GRANITE_ORE_KEY),
            List.of(
                CountPlacementModifier.of(20), // Häufig spawnen für mystisches Gebirge
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(64))
            ));

        // Leuchtende Moosblöcke spawnen auf der Oberfläche beider Biome
        register(context, GLOWING_MOSS_PATCH_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MOSS_PATCH_KEY),
            List.of(
                CountPlacementModifier.of(8), // Moderate Häufigkeit
                SquarePlacementModifier.of(),
                HeightmapPlacementModifier.of(Heightmap.Type.MOTION_BLOCKING), // Auf der Oberfläche
                BiomePlacementModifier.of()
            ));

        // Ethereal Trees spawnen selten aber markant im Kristallhain
        register(context, ETHEREAL_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ETHEREAL_TREE_KEY),
            List.of(
                CountPlacementModifier.of(3), // Seltener für besondere Bäume
                SquarePlacementModifier.of(),
                HeightmapPlacementModifier.of(Heightmap.Type.MOTION_BLOCKING),
                BiomePlacementModifier.of()
            ));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Biomsofinfinity.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                net.minecraft.registry.entry.RegistryEntry<net.minecraft.world.gen.feature.ConfiguredFeature<?, ?>> configuration,
                                List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
