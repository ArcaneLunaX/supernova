package com.veis.supernova.common.block;

import com.mojang.logging.LogUtils;
import com.veis.supernova.common.Supernova;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Supernova.MODID)
public class Basic {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "supernova";
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    //supernova source
    public static final DeferredBlock<Block> SUPERNOVA_SOURCE = BLOCKS.registerSimpleBlock("supernova_source", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK));
    //supernova source item
    public static final DeferredItem<BlockItem> SUPERNOVA_SOURCE_ITEM = ITEMS.registerSimpleBlockItem("supernova_source", SUPERNOVA_SOURCE);
    //supernova catalyst
    //public static final DeferredBlock<Block> SUPERNOVA_CATALYST = BLOCKS.registerSimpleBlock("supernova_catalyst", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY));
    //supernova catalyst item
    //public static final DeferredItem<BlockItem> SUPERNOVA_CATALYST_ITEM = ITEMS.registerSimpleBlockItem("supernova_catalyst", SUPERNOVA_CATALYST);

    //creative tab for blocks
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SUPERNOVA_BLOCKS = CREATIVE_MODE_TABS.register("supernova_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.supernova_blocks"))
            .icon(() -> SUPERNOVA_SOURCE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(SUPERNOVA_SOURCE_ITEM.get());
            }).build());

    public Basic(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("BASIC server loading");
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("blocks/basic loading");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("CLIENT SETUP BASIC");
        }
    }




}
