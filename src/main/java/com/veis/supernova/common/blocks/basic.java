package com.veis.supernova.common.blocks;


import com.veis.supernova.supernova;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.server.command.ModIdArgument;

@Mod(supernova.MODID)
public class basic {

    public static final String MODID = "supernova";
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    //supernova block
    public static final DeferredBlock<Block> SUPERNOVA_SOURCE = BLOCKS.registerSimpleBlock("supernova_source", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK));
    public static final DeferredItem<BlockItem> SUPERNOVA_SOURCE_ITEM = ITEMS.registerSimpleBlockItem("supernova_source", SUPERNOVA_SOURCE);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SUPERNOVA_BLOCKS = CREATIVE_MODE_TABS.register("supernova_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.supernova_blocks"))
            .icon(() -> SUPERNOVA_SOURCE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(SUPERNOVA_SOURCE_ITEM.get());
            }).build());






}
