package net.hallow.unrotted.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hallow.unrotted.Unrotted;
import net.hallow.unrotted.block.ModBlocks;
import net.hallow.unrotted.item.custom.SporeBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SPORE_BOTTLE = registerItem("spore_bottle", new SporeBottleItem(new Item.Settings().maxCount(16)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Unrotted.MOD_ID, name), item);
    }

    public static void initialize() {
        Unrotted.LOGGER.info("Registering Mod Items for " + Unrotted.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModItems.SPORE_BOTTLE);
        });
    }
}
