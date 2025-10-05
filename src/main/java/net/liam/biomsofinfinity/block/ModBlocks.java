package net.liam.biomsofinfinity.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.liam.biomsofinfinity.Biomsofinfinity;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Helper methods for cleaner registration
    private static RegistryKey<Item> key(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Biomsofinfinity.MOD_ID, name));
    }

    private static RegistryKey<Block> blockKey(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Biomsofinfinity.MOD_ID, name));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Biomsofinfinity.MOD_ID, name), item);
    }

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Biomsofinfinity.MOD_ID, name), block);
    }

    // Statische finale Blöcke mit korrekter 1.21.8 Registrierung
    public static final Block MIST_GRANITE = registerBlock("mist_granite",
            new Block(AbstractBlock.Settings.create()
                    .strength(1.5f)
                    .registryKey(blockKey("mist_granite"))));

    public static final Block GLOWING_MOSS_BLOCK = registerBlock("glowing_moss_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(0.1f)
                    .luminance(state -> 7)
                    .registryKey(blockKey("glowing_moss_block"))));

    public static final Block ETHEREAL_LOG = registerBlock("ethereal_log",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(2.0f)
                    .registryKey(blockKey("ethereal_log"))));

    // Block Items mit korrekter 1.21.8 Registrierung
    public static final Item MIST_GRANITE_ITEM = registerItem("mist_granite",
            new BlockItem(MIST_GRANITE, new Item.Settings().registryKey(key("mist_granite"))));

    public static final Item GLOWING_MOSS_BLOCK_ITEM = registerItem("glowing_moss_block",
            new BlockItem(GLOWING_MOSS_BLOCK, new Item.Settings().registryKey(key("glowing_moss_block"))));

    public static final Item ETHEREAL_LOG_ITEM = registerItem("ethereal_log",
            new BlockItem(ETHEREAL_LOG, new Item.Settings().registryKey(key("ethereal_log"))));

    public static void registerModBlocks() {
        Biomsofinfinity.LOGGER.info("Registering ModBlocks for " + Biomsofinfinity.MOD_ID);

        // ItemGroup-Registrierung
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(MIST_GRANITE);
            entries.add(GLOWING_MOSS_BLOCK);
            entries.add(ETHEREAL_LOG);
        });
    }
}
