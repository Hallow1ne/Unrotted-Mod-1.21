package net.hallow.unrotted;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.hallow.unrotted.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class UnrottedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACKWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROTWOOD_SAPLING, RenderLayer.getCutout());
    }
}
