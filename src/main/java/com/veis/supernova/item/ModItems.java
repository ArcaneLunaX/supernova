package com.veis.supernova.item;

import com.veis.supernova.Supernova;
import com.veis.supernova.item.custom.CrossPickItem;
import com.veis.supernova.item.custom.FuelItem;
import com.veis.supernova.item.custom.TransmuteItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Supernova.MODID);

    public static final DeferredItem<Item> SUPERNOVA_INGOT = ITEMS.register("supernova_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> NOVA_INGOT = ITEMS.register("nova_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> NOVA_APPLE = ITEMS.register("nova_apple",
            () -> new Item(new Item.Properties().food(ModFoodProperties.NOVA_APPLE)));


    public static  final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new TransmuteItem(new Item.Properties().durability(64).rarity(Rarity.UNCOMMON)));


    public static final DeferredItem<Item> STARSEER_EYE = ITEMS.register("starseer_eye",
            () -> new TransmuteItem(new Item.Properties().durability(300).fireResistant().setNoRepair().rarity(Rarity.RARE)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if(!Screen.hasShiftDown()){
                        tooltipComponents.add(Component.translatable("tooltip.supernova.transmute_object.hold_shift"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.supernova.transmute_object.shift_held_starseer"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> STARSEER_BREAKER = ITEMS.register("starseer_breaker",
            () -> new CrossPickItem(new Item.Properties().durability(4).fireResistant().setNoRepair().rarity(Rarity.RARE)));

    public static final DeferredItem<Item> STELLAR_COAL = ITEMS.register("stellar_coal",
            () -> new FuelItem(new Item.Properties(), 8000));
    public static final DeferredItem<Item> STELLAR_CHARCOAL = ITEMS.register("stellar_charcoal",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
