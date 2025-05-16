package com.veis.supernova.common.item.custom;

import com.veis.supernova.common.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.SANDSTONE, Blocks.SMOOTH_SANDSTONE,
                    ModBlocks.SUPERNOVA_CATALYST.get(), ModBlocks.SUPERNOVA_BLOCK.get(),
                    ModBlocks.SUPERNOVA_SOURCE.get(), Blocks.STONE
            );
    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        Block belowBlock = level.getBlockState(context.getClickedPos().below()).getBlock();
        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                if(belowBlock == ModBlocks.SUPERNOVA_SOURCE.get()) {
                    context.getItemInHand().hurtAndBreak(10, ((ServerLevel) level), context.getPlayer(),
                            item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                    level.setBlockAndUpdate(context.getClickedPos().below(), CHISEL_MAP.get(belowBlock).defaultBlockState());
                }


                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }


        return InteractionResult.SUCCESS;
    }
}
