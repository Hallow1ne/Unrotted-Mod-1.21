package net.hallow.unrotted.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hallow.unrotted.Unrotted;
import net.hallow.unrotted.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> BLACKWOOD_LIST = List.of(ModBlocks.BLACKWOOD_LOG, ModBlocks.BLACKWOOD_WOOD,
                ModBlocks.STRIPPED_BLACKWOOD_LOG, ModBlocks.STRIPPED_BLACKWOOD_WOOD);

        offerSmelting(exporter, BLACKWOOD_LIST, RecipeCategory.MISC, Items.CHARCOAL, 0, 200, "blackwood");

        makePlanks(exporter, ModBlocks.BLACKWOOD_LOG, ModBlocks.BLACKWOOD_PLANKS, 4, "blackwood_log_to_planks");
        makePlanks(exporter, ModBlocks.BLACKWOOD_WOOD, ModBlocks.BLACKWOOD_PLANKS, 4, "blackwood_wood_to_planks");
        makePlanks(exporter, ModBlocks.STRIPPED_BLACKWOOD_LOG, ModBlocks.BLACKWOOD_PLANKS, 4, "stripped_blackwood_log_to_planks");
        makePlanks(exporter, ModBlocks.STRIPPED_BLACKWOOD_WOOD, ModBlocks.BLACKWOOD_PLANKS, 4, "stripped_blackwood_wood_to_planks");

        offerBarkBlockRecipe(exporter, ModBlocks.BLACKWOOD_WOOD, ModBlocks.BLACKWOOD_LOG);
        offerBarkBlockRecipe(exporter, ModBlocks.STRIPPED_BLACKWOOD_LOG, ModBlocks.STRIPPED_BLACKWOOD_LOG);
    }

    public void makePlanks(RecipeExporter exporter, Block input, Block output, int count, String name) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, count)
                .input(input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, Identifier.of(Unrotted.MOD_ID, name));
    }
}
