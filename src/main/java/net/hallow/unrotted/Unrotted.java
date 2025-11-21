package net.hallow.unrotted;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.hallow.unrotted.block.ModBlocks;
import net.hallow.unrotted.item.ModItems;
import net.hallow.unrotted.world.gen.ModWorldGeneration;
import net.minecraft.block.FireBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Unrotted implements ModInitializer {
	public static final String MOD_ID = "unrotted";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModBlocks.initialize();
        ModItems.initialize();

        ModWorldGeneration.generateModWorldGen();

        StrippableBlockRegistry.register(ModBlocks.BLACKWOOD_LOG, ModBlocks.STRIPPED_BLACKWOOD_LOG);
        StrippableBlockRegistry.register(ModBlocks.BLACKWOOD_WOOD, ModBlocks.STRIPPED_BLACKWOOD_WOOD);
        StrippableBlockRegistry.register(ModBlocks.ROTWOOD_LOG, ModBlocks.STRIPPED_ROTWOOD_LOG);
        StrippableBlockRegistry.register(ModBlocks.ROTWOOD_WOOD, ModBlocks.STRIPPED_ROTWOOD_WOOD);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLACKWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BLACKWOOD_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ROTWOOD_LOG, 10, 10);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ROTWOOD_WOOD, 10, 10);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ROTWOOD_LOG, 10, 10);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ROTWOOD_WOOD, 10, 10);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BLACKWOOD_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ROTWOOD_PLANKS, 10, 40);


	}
}