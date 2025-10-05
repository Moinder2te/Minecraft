package net.liam.biomsofinfinity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.liam.biomsofinfinity.registry.ModBlocks;
import net.liam.biomsofinfinity.registry.ModItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

/**
 * Creates guiding advancements for the new progression path.
 */
public class BOIAdvancementProvider extends FabricAdvancementProvider {

    public BOIAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.create()
                .display(ModBlocks.GLOWSHROOM_CAP, Text.literal("Bioms of Infinity"), Text.literal("Explore the renewed End."), new Identifier("textures/gui/advancements/backgrounds/end.png"), AdvancementFrame.TASK, true, true, false)
                .criterion("entered_end", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().items(Items.ENDER_PEARL).build()))
                .build(Identifier.of("bioms-of-infinity", "root"));

        Advancement discoverGlowshroom = Advancement.Builder.create().parent(root)
                .display(ModBlocks.GLOWSHROOM_CAP, Text.literal("Glowshroom Glades"), Text.literal("Collect glowshroom essence."), null, AdvancementFrame.GOAL, true, true, false)
                .criterion("essence", InventoryChangedCriterion.Conditions.items(ModItems.GLOWSHROOM_ESSENCE))
                .build(Identifier.of("bioms-of-infinity", "glowshroom_essence"));

        Advancement shadowRitual = Advancement.Builder.create().parent(discoverGlowshroom)
                .display(ModBlocks.SHADOW_KING_ALTAR, Text.literal("Shadow Ritual"), Text.literal("Awaken the Shadow King via an altar."), null, AdvancementFrame.CHALLENGE, true, true, true)
                .criterion("altar", InventoryChangedCriterion.Conditions.items(ModItems.SHADOW_HEART))
                .build(Identifier.of("bioms-of-infinity", "shadow_ritual"));

        consumer.accept(root);
        consumer.accept(discoverGlowshroom);
        consumer.accept(shadowRitual);
    }
}
