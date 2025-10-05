package net.liam.biomsofinfinity.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.liam.biomsofinfinity.Biomsofinfinity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    // Helpers
    private static RegistryKey<Item> key(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Biomsofinfinity.MOD_ID, name));
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Biomsofinfinity.MOD_ID, name), item);
    }

    // Mist Peaks Items
    public static final Item MIST_GLIMMER = registerItem("mist_glimmer",
            new Item(new Item.Settings().registryKey(key("mist_glimmer"))));

    public static final Item MIST_CRYSTAL = registerItem("mist_crystal",
            new MistCrystalItem(new Item.Settings().maxCount(16).registryKey(key("mist_crystal"))));

    public static final Item GHOST_ESSENCE = registerItem("ghost_essence",
            new Item(new Item.Settings().maxCount(64).registryKey(key("ghost_essence"))));

    // Crystal Grove Items
    public static final Item PRISMATIC_CRYSTAL = registerItem("prismatic_crystal",
            new PrismaticCrystalItem(new Item.Settings().maxCount(16).registryKey(key("prismatic_crystal"))));

    public static final Item CRYSTAL_SHARD = registerItem("crystal_shard",
            new Item(new Item.Settings().registryKey(key("crystal_shard"))));

    public static final Item CHROMATIC_DUST = registerItem("chromatic_dust",
            new Item(new Item.Settings().registryKey(key("chromatic_dust"))));

    // Tools
    public static final Item CRYSTAL_SWORD = registerItem("crystal_sword",
            new CrystalSwordItem(new Item.Settings().registryKey(key("crystal_sword"))));

    public static final Item MIST_PICKAXE = registerItem("mist_pickaxe",
            new MistPickaxeItem(new Item.Settings()
                    .maxDamage(ModToolMaterials.MistMaterial.DURABILITY)
                    .registryKey(key("mist_pickaxe"))));

    public static void registerModItems() {
        Biomsofinfinity.LOGGER.info("Registering ModItems for {}", Biomsofinfinity.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(MIST_GLIMMER);
            entries.add(MIST_CRYSTAL);
            entries.add(GHOST_ESSENCE);
            entries.add(PRISMATIC_CRYSTAL);
            entries.add(CRYSTAL_SHARD);
            entries.add(CHROMATIC_DUST);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(CRYSTAL_SWORD);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(MIST_PICKAXE);
        });
    }
}
