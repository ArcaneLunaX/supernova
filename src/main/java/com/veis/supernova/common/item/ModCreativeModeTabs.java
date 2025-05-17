package com.veis.supernova.common.item;

import com.veis.supernova.common.Supernova;
import com.veis.supernova.common.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Supernova.MODID);

    public static final Supplier<CreativeModeTab> SUPERNOVA_ITEMS_TAB = CREATIVE_MODE_TAB.register("supernova_items_tab",
            () -> CreativeModeTab.builder()
                    .icon( () -> new ItemStack(ModItems.SUPERNOVA_INGOT.get()))
                    .title(Component.translatable("creativetab.supernova.supernova_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.SUPERNOVA_INGOT);
                        output.accept(ModItems.NOVA_INGOT);
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.STARSEER_EYE);
                    }).build());
    public static final Supplier<CreativeModeTab> SUPERNOVA_BLOCKS_TAB = CREATIVE_MODE_TAB.register("supernova_blocks_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Supernova.MODID, "supernova_items_tab"))
                    .icon( () -> new ItemStack(ModBlocks.SUPERNOVA_BLOCK.get()))
                    .title(Component.translatable("creativetab.supernova.supernova_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.SUPERNOVA_CATALYST);
                        output.accept(ModBlocks.SUPERNOVA_SOURCE);
                        output.accept(ModBlocks.SUPERNOVA_BLOCK);
                    }).build());






    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }



}
