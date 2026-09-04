package net.entropy.cobblesona.datagen;

import net.entropy.cobblesona.CobbleSona;
import net.entropy.cobblesona.block.ModBlocks;
import net.entropy.cobblesona.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        List<ItemLike> COGNITITE_SMELTABLES = List.of(ModBlocks.EMBEDDED_COGNITION);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METANAVIGATOR.get())
                .pattern("IRI")
                .pattern("RCR")
                .pattern("IRI")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('C', ModItems.COGNITIVEFABRIC)
                .unlockedBy("meta_unlocked", has(ModItems.COGNITIVEFABRIC)).
                save(recipeOutput, "entropyscobblesona:meta_from_crafting");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METANAVIGATOR.get())
                .pattern("RIR")
                .pattern("ICI")
                .pattern("RIR")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('C', ModItems.COGNITIVEFABRIC)
                .unlockedBy("meta_unlocked", has(ModItems.COGNITIVEFABRIC))
                .save(recipeOutput, "entropyscobblesona:rotated_meta_from_crafting");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COGNITIVEFABRIC.get(), 9)
                .requires(ModBlocks.COGNITION_BLOCK)
                .unlockedBy("has_cognition_block", has(ModBlocks.COGNITION_BLOCK.get()))
                .save(recipeOutput, "entropyscobblesona:cognitite_from_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.COGNITION_BLOCK.get(), 1)
                .requires(ModItems.COGNITIVEFABRIC.get(), 9)
                .unlockedBy("has_cognition_block", has(ModBlocks.COGNITION_BLOCK.get()))
                .save(recipeOutput, "entropyscobblesona:block_from_cognitite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SLOTH_DOOR.get())
                .pattern("SS")
                .pattern("RC")
                .pattern("SS")
                .define('S', Items.CUT_SANDSTONE)
                .define('R', Items.REDSTONE)
                .define('C', ModItems.COGNITIVEFABRIC)
                .unlockedBy("meta_unlocked", has(ModItems.COGNITIVEFABRIC))
                .save(recipeOutput, "entropyscobblesona:sloth_door_crafting");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAMP_CARD.get(), 1)
                .pattern("PPP")
                .define('P', Items.PAPER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(recipeOutput, "entropyscobblesona:stamp_card_crafting");

        modOreSmelting(recipeOutput, COGNITITE_SMELTABLES, RecipeCategory.MISC, ModItems.COGNITIVEFABRIC.get(), 1f, 200, "cognitite");
        modOreBlasting(recipeOutput, COGNITITE_SMELTABLES, RecipeCategory.MISC, ModItems.COGNITIVEFABRIC.get(), 1f, 200, "cognitite");
    }

    //Helper functions below here
    protected static void modOreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category,
                                         ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result,
                experience, cookingTime, group, "_from_smelting");
    }

    protected static void modOreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category,
                                         ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result,
                experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> cookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String recipeName) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, cookingSerializer, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, CobbleSona.MOD_ID + ":" + getItemName(result) + recipeName + "_" + getItemName(itemlike));
        }
    }
}
