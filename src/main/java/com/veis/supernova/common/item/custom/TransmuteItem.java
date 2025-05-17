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
import net.neoforged.fml.common.Mod;


import java.util.Map;

public class TransmuteItem extends Item {

    public TransmuteItem(Properties properties) {
        super(properties);
    }
    //chisel
    private static final Map<Block, Block> TRANSMUTE_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.SANDSTONE, Blocks.SMOOTH_SANDSTONE,
                    Blocks.AIR, Blocks.ACACIA_LOG
            );

    private static final Map<Block, Block> ASCENDANT_MAP =
            Map.of(
                    ModBlocks.SUPERNOVA_CATALYST.get(), ModBlocks.SUPERNOVA_BLOCK.get(),
                    ModBlocks.SUPERNOVA_SOURCE.get(), Blocks.STONE
            );

    private static final Map<Block, Block> STARSEER_MAP =
            Map.of(
                    Blocks.STONE, ModBlocks.SUPERNOVA_SOURCE.get(),
                    Blocks.ANDESITE, ModBlocks.SUPERNOVA_SOURCE.get(),
                    Blocks.DIORITE, ModBlocks.SUPERNOVA_SOURCE.get(),
                    Blocks.GRANITE, ModBlocks.SUPERNOVA_SOURCE.get()
            );
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        Block belowBlock = level.getBlockState(context.getClickedPos().below()).getBlock();
        Block aboveBlock = level.getBlockState(context.getClickedPos().above()).getBlock();
        if(TRANSMUTE_MAP.containsKey(clickedBlock) & context.getItemInHand().is(ModItems.CHISEL.asItem())) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), TRANSMUTE_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            }
        }


        if(ASCENDANT_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                if(belowBlock == ModBlocks.SUPERNOVA_SOURCE.get() & aboveBlock == ModBlocks.SUPERNOVA_SOURCE.get()) {
                    if(clickedBlock == ModBlocks.SUPERNOVA_CATALYST.get()) {
                        level.setBlockAndUpdate(context.getClickedPos(), ASCENDANT_MAP.get(clickedBlock).defaultBlockState());
                        level.setBlockAndUpdate(context.getClickedPos().below(), ASCENDANT_MAP.get(belowBlock).defaultBlockState());
                        level.setBlockAndUpdate(context.getClickedPos().above(), ASCENDANT_MAP.get(aboveBlock).defaultBlockState());
                        level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
                        context.getItemInHand().hurtAndBreak(16, ((ServerLevel) level), context.getPlayer(),
                                item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                    }
                    if(clickedBlock == ModBlocks.SUPERNOVA_BLOCK.get()) {
                        level.setBlockAndUpdate(context.getClickedPos(), Blocks.OBSIDIAN.defaultBlockState());
                    }
                }
            }
        }

        if(!level.isClientSide() & context.getItemInHand().is(ModItems.STARSEER_EYE.asItem())) {
            level.setBlockAndUpdate(context.getClickedPos(), STARSEER_MAP.get(clickedBlock).defaultBlockState());
        }


        return InteractionResult.SUCCESS;
    }
}
