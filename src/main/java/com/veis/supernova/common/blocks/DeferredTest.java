package com.veis.supernova.common.blocks;


import com.veis.supernova.common.Supernova;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.common.Mod;

@Mod(Supernova.MODID)
public class DeferredTest {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Supernova.MODID);

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
    }




    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}