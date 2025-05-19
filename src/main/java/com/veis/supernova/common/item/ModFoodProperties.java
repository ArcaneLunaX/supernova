package com.veis.supernova.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    public static final FoodProperties NOVA_APPLE = new FoodProperties.Builder().alwaysEdible().usingConvertsTo(Items.BONE).nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 400),1f).build();
}