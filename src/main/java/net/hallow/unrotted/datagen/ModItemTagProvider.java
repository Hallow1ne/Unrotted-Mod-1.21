package net.hallow.unrotted.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hallow.unrotted.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLACKWOOD_LOG.asItem())
                .add(ModBlocks.BLACKWOOD_WOOD.asItem())
                .add(ModBlocks.STRIPPED_BLACKWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_BLACKWOOD_WOOD.asItem())
                .add(ModBlocks.ROTWOOD_LOG.asItem())
                .add(ModBlocks.ROTWOOD_WOOD.asItem())
                .add(ModBlocks.STRIPPED_ROTWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_ROTWOOD_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.BLACKWOOD_PLANKS.asItem())
                .add(ModBlocks.ROTWOOD_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(ModBlocks.BLACKWOOD_SAPLING.asItem())
                .add(ModBlocks.ROTWOOD_SAPLING.asItem());
    }
}
