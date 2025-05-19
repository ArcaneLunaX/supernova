package com.veis.supernova.common.item.custom;

import com.veis.supernova.common.block.ModBlocks;
import com.veis.supernova.common.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.antlr.v4.runtime.atn.SemanticContext;


import java.util.Map;

public class TransmuteItem extends Item {

    public TransmuteItem(Properties properties) {
        super(properties);
    }
    //chisel
    private static final Map<Block, Block> TRANSMUTE_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.STONE_BRICKS, Blocks.ANDESITE,
                    Blocks.ANDESITE, Blocks.DIORITE,
                    Blocks.DIORITE, Blocks.GRANITE,
                    Blocks.SANDSTONE, Blocks.SMOOTH_SANDSTONE,
                    Blocks.AIR, Blocks.ACACIA_LOG
            );


    private static final Map<Block, Block> STARSEER_MAP =
            Map.of(
                    Blocks.STONE, ModBlocks.STARSEER_ESSENCE.get(),
                    Blocks.ANDESITE, ModBlocks.STARSEER_ESSENCE.get(),
                    Blocks.DIORITE, ModBlocks.STARSEER_ESSENCE.get(),
                    Blocks.GRANITE, ModBlocks.STARSEER_ESSENCE.get(),
                    ModBlocks.SUPERNOVA_CATALYST.get(), ModBlocks.SUPERNOVA_BLOCK.get(),
                    ModBlocks.STARSEER_ESSENCE.get(), Blocks.STONE
            );
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        Block belowBlock = level.getBlockState(context.getClickedPos().below()).getBlock();
        Block aboveBlock = level.getBlockState(context.getClickedPos().above()).getBlock();
        if(TRANSMUTE_MAP.containsKey(clickedBlock) & !context.getItemInHand().is(ModItems.STARSEER_EYE.asItem())) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), TRANSMUTE_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            }
        }


        if(STARSEER_MAP.containsKey(clickedBlock) && !level.isClientSide()) {
            if(context.getItemInHand().is(ModItems.STARSEER_EYE.asItem())) {
                if(clickedBlock == ModBlocks.SUPERNOVA_CATALYST.get()) {
                    if(belowBlock == ModBlocks.STARSEER_ESSENCE.get() & aboveBlock == ModBlocks.STARSEER_ESSENCE.get()) {
                        level.setBlockAndUpdate(context.getClickedPos(), STARSEER_MAP.get(clickedBlock).defaultBlockState());
                        level.setBlockAndUpdate(context.getClickedPos().below(), STARSEER_MAP.get(belowBlock).defaultBlockState());
                        level.setBlockAndUpdate(context.getClickedPos().above(), STARSEER_MAP.get(aboveBlock).defaultBlockState());
                        level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
                        context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                                item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                    }
                    else{
                        level.playSound(null, context.getClickedPos(), SoundEvents.PLAYER_HURT, SoundSource.BLOCKS);
                    }
                } else if (clickedBlock == ModBlocks.SUPERNOVA_BLOCK.get()) {
                    level.setBlockAndUpdate(context.getClickedPos(), Blocks.OBSIDIAN.defaultBlockState());
                } else if (clickedBlock == ModBlocks.STARSEER_ESSENCE.get()) {
                    level.setBlockAndUpdate(context.getClickedPos(), STARSEER_MAP.get(clickedBlock).defaultBlockState());
                    context.getItemInHand().hurtAndBreak(-1, ((ServerLevel) level), context.getPlayer(),
                            item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                } else {
                    level.setBlockAndUpdate(context.getClickedPos(), STARSEER_MAP.get(clickedBlock).defaultBlockState());
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
