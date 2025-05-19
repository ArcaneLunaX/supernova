package com.veis.supernova.util;

import com.veis.supernova.Supernova;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Supernova.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> TRANSMUTABLE_ITEMS = createTag("transmutable_items");
        public static final TagKey<Item> HIGH_REQUIREMENT_TRANSMUTE = createTag("high_requirement_transmute");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Supernova.MODID, name));
        }
    }
}
