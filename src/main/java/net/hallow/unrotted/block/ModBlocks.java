package net.hallow.unrotted.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hallow.unrotted.Unrotted;
import net.hallow.unrotted.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ModBlocks {
    public static final Block BLACKWOOD_LOG = registerBlock("blackwood_log", createLogBlock(MapColor.BLACK, MapColor.WHITE_GRAY));
    public static final Block STRIPPED_BLACKWOOD_LOG = registerBlock("stripped_blackwood_log", createLogBlock(MapColor.BLACK, MapColor.BLACK));
    public static final Block BLACKWOOD_WOOD = registerBlock(
            "blackwood_wood",
            new PillarBlock(
                    AbstractBlock.Settings.create().mapColor(MapColor.WHITE_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()
            )
    );
    public static final Block STRIPPED_BLACKWOOD_WOOD = registerBlock(
            "stripped_blackwood_wood",
            new PillarBlock(
                    AbstractBlock.Settings.create().mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()
            )
    );
    public static final Block BLACKWOOD_PLANKS = registerBlock(
            "blackwood_planks",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.BLACK)
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(2.0F, 3.0F)
                            .sounds(BlockSoundGroup.WOOD)
                            .burnable()
            )
    );

    public static final Block BLACKWOOD_LEAVES = registerBlock(
            "blackwood_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES))
    );

    public static final Block BLACKWOOD_SAPLING = registerBlock(
            "blackwood_sapling",
            new SaplingBlock(ModSaplingGenerators.BLACKWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING))
    );



    public static Block registerBlock(String id, Block block) {
        registerBlockItem(id, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Unrotted.MOD_ID, id), block);
    }

    public static void registerBlockItem(String id, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Unrotted.MOD_ID, id),
                new BlockItem(block, new Item.Settings()));
    }

    public static Block createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return new PillarBlock(
                AbstractBlock.Settings.create()
                        .mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                        .instrument(NoteBlockInstrument.BASS)
                        .strength(2.0F)
                        .sounds(BlockSoundGroup.WOOD)
                        .burnable()
        );
    }

    public static void initialize() {
        Unrotted.LOGGER.info("Registering Mod Blocks for " + Unrotted.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.BLACKWOOD_LOG);
            fabricItemGroupEntries.add(ModBlocks.BLACKWOOD_WOOD);
            fabricItemGroupEntries.add(ModBlocks.STRIPPED_BLACKWOOD_LOG);
            fabricItemGroupEntries.add(ModBlocks.STRIPPED_BLACKWOOD_WOOD);
            fabricItemGroupEntries.add(ModBlocks.BLACKWOOD_PLANKS);
            fabricItemGroupEntries.add(ModBlocks.BLACKWOOD_LEAVES);
            fabricItemGroupEntries.add(ModBlocks.BLACKWOOD_SAPLING);
        });
    }
}
