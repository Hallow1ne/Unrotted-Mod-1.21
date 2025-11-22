package net.hallow.unrotted.block.custom;

import com.mojang.serialization.MapCodec;
import net.hallow.unrotted.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class TreeFungusBlock extends Block implements Fertilizable {
    public static final MapCodec<TreeFungusBlock> CODEC = createCodec(TreeFungusBlock::new);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty FUNGUS_AMOUNT = Properties.FLOWER_AMOUNT;
    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(1.0, 0.0, 9.0, 15.0, 15.0, 16.0);
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(1.0, 0.0, 0.0, 15.0, 15.0, 7.0);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(9.0, 0.0, 1.0, 16.0, 15.0, 15.0);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0, 0.0, 1.0, 7.0, 15.0, 15.0);



    public TreeFungusBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FUNGUS_AMOUNT, 1));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch ((Direction) state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> EAST_SHAPE;
        };
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    private boolean canPlaceOn(BlockView world, BlockPos pos, Direction side) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isSideSolidFullSquare(world, pos, side);
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && state.get(FUNGUS_AMOUNT) < 4 || super.canReplace(state, context);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        return this.canPlaceOn(world, pos.offset(direction.getOpposite()), direction);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockState = context.getWorld().getBlockState(context.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.with(FUNGUS_AMOUNT, Math.min(4, (Integer)blockState.get(FUNGUS_AMOUNT) + 1));
        }

        BlockState thisBlockState = this.getDefaultState();
        WorldView worldView = context.getWorld();
        BlockPos blockPos = context.getBlockPos();

        for (Direction direction : context.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                thisBlockState = thisBlockState.with(FACING, direction.getOpposite());
                if (thisBlockState.canPlaceAt(worldView, blockPos)) {
                    return thisBlockState;
                }
            }
        }

        return null;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i = (Integer)state.get(FUNGUS_AMOUNT);
        boolean bl = false;
        Item item = stack.getItem();
        if (stack.isOf(Items.GLASS_BOTTLE)) {
            stack.decrement(1);
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_COMPOSTER_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (stack.isEmpty()) {
                player.setStackInHand(hand, new ItemStack(ModItems.SPORE_BOTTLE));
            } else if (!player.getInventory().insertStack(new ItemStack(ModItems.SPORE_BOTTLE))) {
                player.dropItem(new ItemStack(ModItems.SPORE_BOTTLE), false);
            }

            bl = true;
            world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);

            if (i > 1) {
                world.setBlockState(pos, state.with(FUNGUS_AMOUNT, i-1), Block.NOTIFY_ALL);
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
            }
        }

        if (!world.isClient() && bl) {
            player.incrementStat(Stats.USED.getOrCreateStat(item));
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, FUNGUS_AMOUNT);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return (Integer)state.get(FUNGUS_AMOUNT) < 4;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = (Integer)state.get(FUNGUS_AMOUNT);
        if (i < 4) {
            world.setBlockState(pos, state.with(FUNGUS_AMOUNT, i + 1), Block.NOTIFY_LISTENERS);
        }
    }
}
