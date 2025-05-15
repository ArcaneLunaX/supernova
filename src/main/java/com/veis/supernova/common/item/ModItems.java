package com.veis.supernova.common.item;

import com.veis.supernova.common.Supernova;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Supernova.MODID);

    public static final DeferredItem<Item> SUPERNOVA_INGOT = ITEMS.register("supernova_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> UNCHARGED_NOVA_INGOT = ITEMS.register("uncharged_nova_ingot",
            () -> new Item(new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
