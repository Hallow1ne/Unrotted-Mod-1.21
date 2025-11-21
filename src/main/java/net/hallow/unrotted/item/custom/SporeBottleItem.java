package net.hallow.unrotted.item.custom;

import net.hallow.unrotted.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class SporeBottleItem extends Item {
    private static final List<Block> SPORE_LIST =
            List.of(
                    Blocks.OAK_SAPLING,
                    Blocks.DARK_OAK_SAPLING,
                    Blocks.BIRCH_SAPLING,
                    Blocks.SPRUCE_SAPLING,
                    Blocks.ACACIA_SAPLING,
                    Blocks.CHERRY_SAPLING,
                    Blocks.JUNGLE_SAPLING
            );

    public SporeBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(SPORE_LIST.contains(clickedBlock)) {
            if(!world.isClient()) {
                world.setBlockState(context.getBlockPos(), ModBlocks.ROTWOOD_SAPLING.getDefaultState());

                context.getStack().decrement(1);

                ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
                if(!context.getPlayer().isCreative()) {
                    if (!context.getPlayer().getInventory().insertStack(itemStack)) {
                        context.getPlayer().dropItem(itemStack, false);
                    }
                }
                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_COMPOSTER_FILL, SoundCategory.BLOCKS);
            }

        }

        return ActionResult.SUCCESS;
    }
}
