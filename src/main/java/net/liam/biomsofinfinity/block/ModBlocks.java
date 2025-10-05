package net.liam.biomsofinfinity.block;

import net.minecraft.block.Block;

public class ModBlocks {

    public static final Block MIST_GRANITE = net.liam.biomsofinfinity.registry.ModBlocks.MIST_GRANITE;
    public static final Block GLOWING_MOSS_BLOCK = net.liam.biomsofinfinity.registry.ModBlocks.GLOWING_MOSS_BLOCK;
    public static final Block ETHEREAL_LOG = net.liam.biomsofinfinity.registry.ModBlocks.ETHEREAL_LOG;

    public static void registerModBlocks() {
        net.liam.biomsofinfinity.registry.ModBlocks.bootstrap();
    }
}
