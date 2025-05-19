package com.veis.supernova.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CrossPickItem extends Item {
    public CrossPickItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        BlockPos aboveBroken = pos.above();

        level.destroyBlock(aboveBroken, true);

        return super.mineBlock(stack, level, state, pos, miningEntity);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        BlockPos clickedPos = context.getClickedPos();

        level.destroyBlock(clickedPos, true);
        level.destroyBlock(clickedPos.north(), true);
        level.destroyBlock(clickedPos.north().east(), true);
        level.destroyBlock(clickedPos.north().west(), true);
        level.destroyBlock(clickedPos.east(), true);
        level.destroyBlock(clickedPos.south(), true);
        level.destroyBlock(clickedPos.south().east(), true);
        level.destroyBlock(clickedPos.south().west(), true);
        level.destroyBlock(clickedPos.west(), true);
        level.destroyBlock(clickedPos.above(), true);
        level.destroyBlock(clickedPos.above().north(), true);
        level.destroyBlock(clickedPos.above().north().east(), true);
        level.destroyBlock(clickedPos.above().north().west(), true);
        level.destroyBlock(clickedPos.above().east(), true);
        level.destroyBlock(clickedPos.above().south(), true);
        level.destroyBlock(clickedPos.above().south().east(), true);
        level.destroyBlock(clickedPos.above().south().west(), true);
        level.destroyBlock(clickedPos.above().west(), true);
        level.destroyBlock(clickedPos.below(), true);
        level.destroyBlock(clickedPos.below().north(), true);
        level.destroyBlock(clickedPos.below().north().east(), true);
        level.destroyBlock(clickedPos.below().north().west(), true);
        level.destroyBlock(clickedPos.below().east(), true);
        level.destroyBlock(clickedPos.below().south(), true);
        level.destroyBlock(clickedPos.below().south().east(), true);
        level.destroyBlock(clickedPos.below().south().west(), true);
        level.destroyBlock(clickedPos.below().west(), true);

        return InteractionResult.SUCCESS;
    }
}
