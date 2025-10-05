package net.liam.biomsofinfinity.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.liam.biomsofinfinity.Biomsofinfinity;
import net.liam.biomsofinfinity.item.DarkVeilCloakItem;
import net.liam.biomsofinfinity.item.DreamseedItem;
import net.liam.biomsofinfinity.item.GlowshroomEssenceItem;
import net.liam.biomsofinfinity.item.LuminousFiberItem;
import net.liam.biomsofinfinity.item.MistCrystalItem;
import net.liam.biomsofinfinity.item.MistPickaxeItem;
import net.liam.biomsofinfinity.item.PrismaticCrystalItem;
import net.liam.biomsofinfinity.item.ShadowHeartItem;
import net.liam.biomsofinfinity.item.CrystalSwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Core item registry bridging legacy gear with the new biome resources.
 */
public final class ModItems {

    private ModItems() {
    }

    public static final Item MIST_GLIMMER = register("mist_glimmer", new Item(new Item.Settings()));
    public static final Item MIST_CRYSTAL = register("mist_crystal", new MistCrystalItem(new Item.Settings().maxCount(16)));
    public static final Item GHOST_ESSENCE = register("ghost_essence", new Item(new Item.Settings()));
    public static final Item PRISMATIC_CRYSTAL = register("prismatic_crystal", new PrismaticCrystalItem(new Item.Settings().maxCount(16)));
    public static final Item CRYSTAL_SHARD = register("crystal_shard", new Item(new Item.Settings()));
    public static final Item CHROMATIC_DUST = register("chromatic_dust", new Item(new Item.Settings()));

    public static final Item CRYSTAL_SWORD = register("crystal_sword", new CrystalSwordItem(new Item.Settings()));
    public static final Item MIST_PICKAXE = register("mist_pickaxe", new MistPickaxeItem(new Item.Settings().maxDamage(512)));

    // New biome resources
    public static final Item GLOWSHROOM_ESSENCE = register("glowshroom_essence", new GlowshroomEssenceItem(new Item.Settings().maxCount(16)));
    public static final Item LUMINOUS_FIBER = register("luminous_fiber", new LuminousFiberItem(new Item.Settings()));
    public static final Item DREAMSEED = register("dreamseed", new DreamseedItem(new Item.Settings()));
    public static final Item SHADOW_HEART = register("shadow_heart", new ShadowHeartItem(new Item.Settings().maxCount(1))); 
    public static final Item DARK_VEIL_CLOAK = register("dark_veil_cloak", new DarkVeilCloakItem(new Item.Settings().maxCount(1)));

    private static Item register(String name, Item item) {
        Identifier id = Identifier.of(Biomsofinfinity.MOD_ID, name);
        if (Registries.ITEM.getIds().contains(id)) {
            return Registries.ITEM.get(id);
        }
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void bootstrap() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(MIST_GLIMMER);
            entries.add(MIST_CRYSTAL);
            entries.add(GHOST_ESSENCE);
            entries.add(PRISMATIC_CRYSTAL);
            entries.add(CRYSTAL_SHARD);
            entries.add(CHROMATIC_DUST);
            entries.add(GLOWSHROOM_ESSENCE);
            entries.add(LUMINOUS_FIBER);
            entries.add(DREAMSEED);
            entries.add(SHADOW_HEART);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(CRYSTAL_SWORD);
            entries.add(DARK_VEIL_CLOAK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(MIST_PICKAXE);
        });
    }
}
