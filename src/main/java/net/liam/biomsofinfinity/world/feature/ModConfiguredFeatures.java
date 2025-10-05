package net.liam.biomsofinfinity.world.feature;

import net.liam.biomsofinfinity.Biomsofinfinity;
import net.liam.biomsofinfinity.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {

    // Nebelgranit-Adern in Nebelwipfel
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIST_GRANITE_ORE_KEY = registerKey("mist_granite_ore");

    // Leuchtende Moosblöcke in beiden Biomen
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MOSS_PATCH_KEY = registerKey("glowing_moss_patch");

    // Ethereal Log Bäume im Kristallhain
    public static final RegistryKey<ConfiguredFeature<?, ?>> ETHEREAL_TREE_KEY = registerKey("ethereal_tree");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        // Erstelle RuleTest für Stein-Ersetzung
        RuleTest stoneReplaceables = new BlockMatchRuleTest(Blocks.STONE);

        // Nebelgranit spawnt als Erzadern in Nebelwipfel (ersetzt Stein)
        register(context, MIST_GRANITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(
            stoneReplaceables,
            ModBlocks.MIST_GRANITE.getDefaultState(),
            32)); // Größere Adern für mystische Felsen

        // Leuchtende Moosblöcke als Patches - vereinfachte Version
        register(context, GLOWING_MOSS_PATCH_KEY, Feature.RANDOM_PATCH,
            new RandomPatchFeatureConfig(4, 4, 3,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                    new SimpleBlockFeatureConfig(SimpleBlockStateProvider.of(ModBlocks.GLOWING_MOSS_BLOCK.getDefaultState())))));

        // Ethereal Trees mit Ethereal Log
        register(context, ETHEREAL_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
            SimpleBlockStateProvider.of(ModBlocks.ETHEREAL_LOG.getDefaultState()),
            new StraightTrunkPlacer(4, 2, 0),
            SimpleBlockStateProvider.of(Blocks.END_STONE.getDefaultState()), // Kristalline Blätter
            new BlobFoliagePlacer(ConstantIntProvider.create(2),
                                  ConstantIntProvider.create(0), 3),
            new TwoLayersFeatureSize(1, 0, 1)).build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Biomsofinfinity.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
