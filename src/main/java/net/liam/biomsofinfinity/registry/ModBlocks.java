package net.liam.biomsofinfinity.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.liam.biomsofinfinity.Biomsofinfinity;
import net.liam.biomsofinfinity.block.DreamBlossomBlock;
import net.liam.biomsofinfinity.block.GlowshroomCapBlock;
import net.liam.biomsofinfinity.block.GlowshroomStemBlock;
import net.liam.biomsofinfinity.block.GlowingMossBlock;
import net.liam.biomsofinfinity.block.LevitationSoilBlock;
import net.liam.biomsofinfinity.block.LuminescentMossBlock;
import net.liam.biomsofinfinity.block.ShadowKingAltarBlock;
import net.liam.biomsofinfinity.block.ShadowstoneBlock;
import net.liam.biomsofinfinity.block.SkyCrystalLanternBlock;
import net.liam.biomsofinfinity.block.SkyVineBlock;
import net.liam.biomsofinfinity.block.UmbralThornBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Central block registry that wires together legacy content and the new biome blocks used for the End overhaul.
 */
public final class ModBlocks {

    private ModBlocks() {
    }

    // --- Legacy blocks retained for backwards compatibility ---
    public static final Block MIST_GRANITE = register("mist_granite",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY).strength(1.5f, 6.0f)));

    public static final Block GLOWING_MOSS_BLOCK = register("glowing_moss_block",
            new GlowingMossBlock(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).luminance(state -> 9).strength(0.2f)));

    public static final Block ETHEREAL_LOG = register("ethereal_log",
            new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLUE).strength(2.0f)));

    // --- Glowshroom Forest ---
    public static final Block GLOWSHROOM_CAP = register("glowshroom_cap",
            new GlowshroomCapBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).luminance(state -> 12).nonOpaque().strength(0.4f)));

    public static final Block GLOWSHROOM_STEM = register("glowshroom_stem",
            new GlowshroomStemBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE).strength(0.8f)));

    public static final Block LUMINESCENT_MOSS = register("luminescent_moss",
            new LuminescentMossBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).luminance(state -> 10).strength(0.3f)));

    // --- Floating Gardens ---
    public static final Block DREAM_BLOSSOM = register("dream_blossom",
            new DreamBlossomBlock(AbstractBlock.Settings.create().mapColor(MapColor.PINK).nonOpaque().noCollision().luminance(state -> 6)));

    public static final Block SKY_VINE = register("sky_vine",
            new SkyVineBlock(AbstractBlock.Settings.create().noCollision().ticksRandomly().nonOpaque().luminance(state -> 4)));

    public static final Block LEVITATION_SOIL = register("levitation_soil",
            new LevitationSoilBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.6f)));

    public static final Block SKY_CRYSTAL_LANTERN = register("sky_crystal_lantern",
            new SkyCrystalLanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).luminance(state -> 15).strength(1.0f)));

    // --- Shadow Isles ---
    public static final Block SHADOWSTONE = register("shadowstone",
            new ShadowstoneBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).strength(2.5f, 6.0f)));

    public static final Block UMBRAL_THORN = register("umbral_thorn",
            new UmbralThornBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).nonOpaque().noCollision().luminance(state -> 3)));

    public static final Block RITUAL_LANTERN = register("ritual_lantern",
            new Block(AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN).luminance(state -> 13)));

    public static final Block SHADOW_KING_ALTAR = register("shadow_king_altar",
            new ShadowKingAltarBlock(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(5.0f, 10.0f)));

    /**
     * Called from mod init to hook the blocks into creative tabs.
     */
    public static void bootstrap() {
        Biomsofinfinity.LOGGER.info("Registering {} mod blocks", Registries.BLOCK.size());

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(MIST_GRANITE);
            entries.add(GLOWING_MOSS_BLOCK);
            entries.add(ETHEREAL_LOG);
            entries.add(GLOWSHROOM_CAP);
            entries.add(GLOWSHROOM_STEM);
            entries.add(LUMINESCENT_MOSS);
            entries.add(LEVITATION_SOIL);
            entries.add(SHADOWSTONE);
            entries.add(RITUAL_LANTERN);
            entries.add(SHADOW_KING_ALTAR);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL_BLOCKS).register(entries -> {
            entries.add(DREAM_BLOSSOM);
            entries.add(SKY_VINE);
            entries.add(UMBRAL_THORN);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(SKY_CRYSTAL_LANTERN);
        });
    }

    private static Block register(String name, Block block) {
        Identifier id = Identifier.of(Biomsofinfinity.MOD_ID, name);
        if (Registries.BLOCK.getIds().contains(id)) {
            return Registries.BLOCK.get(id);
        }
        Block registered = Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(registered, new Item.Settings()));
        return registered;
    }
}
