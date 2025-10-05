package net.liam.biomsofinfinity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.liam.biomsofinfinity.registry.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

/**
 * Generates entity loot for Bioms of Infinity bosses.
 */
public class BOIEntityLootTableProvider extends SimpleFabricLootTableProvider {

    public BOIEntityLootTableProvider(FabricDataOutput output) {
        super(output, net.minecraft.loot.LootContextTypes.ENTITY);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        LootTable.Builder shadowKing = LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(ModItems.DARK_VEIL_CLOAK)))
                .pool(LootPool.builder().with(ItemEntry.builder(ModItems.SHADOW_HEART)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2)))));
        exporter.accept(Identifier.of("bioms-of-infinity", "entities/shadow_king"), shadowKing);
    }
}
