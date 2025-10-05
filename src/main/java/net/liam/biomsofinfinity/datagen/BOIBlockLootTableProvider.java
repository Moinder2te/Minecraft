package net.liam.biomsofinfinity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.liam.biomsofinfinity.registry.ModBlocks;

/**
 * Generates block loot tables for new Bioms of Infinity blocks.
 */
public class BOIBlockLootTableProvider extends FabricBlockLootTableProvider {

    public BOIBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.GLOWSHROOM_CAP);
        addDrop(ModBlocks.GLOWSHROOM_STEM);
        addDrop(ModBlocks.LUMINESCENT_MOSS);
        addDrop(ModBlocks.DREAM_BLOSSOM);
        addDrop(ModBlocks.SKY_VINE);
        addDrop(ModBlocks.LEVITATION_SOIL);
        addDrop(ModBlocks.SKY_CRYSTAL_LANTERN);
        addDrop(ModBlocks.SHADOWSTONE);
        addDrop(ModBlocks.UMBRAL_THORN);
        addDrop(ModBlocks.RITUAL_LANTERN);
        addDrop(ModBlocks.SHADOW_KING_ALTAR);
    }
}
