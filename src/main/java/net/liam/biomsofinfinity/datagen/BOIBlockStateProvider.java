package net.liam.biomsofinfinity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockStateProvider;
import net.liam.biomsofinfinity.registry.ModBlocks;
import net.minecraft.data.client.BlockStateModelGenerator;

/**
 * Generates blockstate and block model JSON scaffolding for Bioms of Infinity.
 */
public class BOIBlockStateProvider extends FabricBlockStateProvider {

    public BOIBlockStateProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(ModBlocks.GLOWSHROOM_CAP);
        generator.registerSimpleCubeAll(ModBlocks.GLOWSHROOM_STEM);
        generator.registerSimpleCubeAll(ModBlocks.LUMINESCENT_MOSS);
        generator.registerTintableCross(ModBlocks.DREAM_BLOSSOM, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerVine(ModBlocks.SKY_VINE);
        generator.registerSimpleCubeAll(ModBlocks.LEVITATION_SOIL);
        generator.registerSimpleCubeAll(ModBlocks.SKY_CRYSTAL_LANTERN);
        generator.registerSimpleCubeAll(ModBlocks.SHADOWSTONE);
        generator.registerTintableCross(ModBlocks.UMBRAL_THORN, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerSimpleCubeAll(ModBlocks.RITUAL_LANTERN);
        generator.registerSimpleCubeAll(ModBlocks.SHADOW_KING_ALTAR);
    }
}
