package net.liam.biomsofinfinity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.liam.biomsofinfinity.registry.ModBlocks;
import net.liam.biomsofinfinity.registry.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

/**
 * Outputs crafting recipes for new resources and gear.
 */
public class BOIRecipeProvider extends FabricRecipeProvider {

    public BOIRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GLOWSHROOM_ESSENCE, 2)
                .input(ModBlocks.GLOWSHROOM_CAP)
                .criterion(hasItem(ModBlocks.GLOWSHROOM_CAP), conditionsFromItem(ModBlocks.GLOWSHROOM_CAP))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LUMINOUS_FIBER, 3)
                .input(ModBlocks.GLOWSHROOM_STEM)
                .criterion(hasItem(ModBlocks.GLOWSHROOM_STEM), conditionsFromItem(ModBlocks.GLOWSHROOM_STEM))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.DREAMSEED)
                .input(ModBlocks.DREAM_BLOSSOM)
                .criterion(hasItem(ModBlocks.DREAM_BLOSSOM), conditionsFromItem(ModBlocks.DREAM_BLOSSOM))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SHADOW_HEART)
                .input(ModBlocks.SHADOWSTONE)
                .input(ModBlocks.UMBRAL_THORN)
                .input(Items.ENDER_PEARL)
                .criterion(hasItem(ModBlocks.SHADOWSTONE), conditionsFromItem(ModBlocks.SHADOWSTONE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.SKY_CRYSTAL_LANTERN)
                .pattern(" F ")
                .pattern("ETE")
                .pattern(" F ")
                .input('F', ModItems.LUMINOUS_FIBER)
                .input('E', ModItems.GLOWSHROOM_ESSENCE)
                .input('T', Items.TORCH)
                .criterion(hasItem(ModItems.GLOWSHROOM_ESSENCE), conditionsFromItem(ModItems.GLOWSHROOM_ESSENCE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_VEIL_CLOAK)
                .pattern("DLD")
                .pattern("DSD")
                .pattern("DDD")
                .input('D', Items.BLACK_WOOL)
                .input('L', ModItems.LUMINOUS_FIBER)
                .input('S', ModItems.SHADOW_HEART)
                .criterion(hasItem(ModItems.SHADOW_HEART), conditionsFromItem(ModItems.SHADOW_HEART))
                .offerTo(exporter);
    }
}
