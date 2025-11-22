package net.hallow.unrotted.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hallow.unrotted.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.BLACKWOOD_LOG)
                .add(ModBlocks.BLACKWOOD_WOOD)
                .add(ModBlocks.STRIPPED_BLACKWOOD_LOG)
                .add(ModBlocks.STRIPPED_BLACKWOOD_WOOD)
                .add(ModBlocks.BLACKWOOD_PLANKS)
                .add(ModBlocks.ROTWOOD_LOG)
                .add(ModBlocks.ROTWOOD_WOOD)
                .add(ModBlocks.STRIPPED_ROTWOOD_LOG)
                .add(ModBlocks.STRIPPED_ROTWOOD_WOOD)
                .add(ModBlocks.ROTWOOD_PLANKS);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.TINDER_FUNGUS);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLACKWOOD_LOG)
                .add(ModBlocks.BLACKWOOD_WOOD)
                .add(ModBlocks.STRIPPED_BLACKWOOD_LOG)
                .add(ModBlocks.STRIPPED_BLACKWOOD_WOOD)
                .add(ModBlocks.ROTWOOD_LOG)
                .add(ModBlocks.ROTWOOD_WOOD)
                .add(ModBlocks.STRIPPED_ROTWOOD_LOG)
                .add(ModBlocks.STRIPPED_ROTWOOD_WOOD);
    }
}
