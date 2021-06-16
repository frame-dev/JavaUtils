package de.framedev.javautils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName ItemBuilder
 * Date: 14.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder hideEnchantments() {
        this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... flags) {
        for (ItemFlag flag : flags) {
            this.itemMeta.addItemFlags(flag);
        }
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public ItemBuilder setDisplayName(String displayName) {
        if (itemMeta == null) {
            return null;
        }
        this.itemMeta.setDisplayName(displayName);
        return this;
    }

    public String getDisplayName() {
        if (itemMeta == null) {
            return null;
        }
        return this.itemMeta.getDisplayName();
    }

    public ItemBuilder setLore(String... lore) {
        if (itemMeta == null) return null;
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public List<String> getLore() {
        if (itemMeta == null) return null;
        return this.itemMeta.getLore();
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public int getAmount() {
        if (itemStack == null) return 0;
        return itemStack.getAmount();
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder addLore(String str) {
        if (this.itemMeta == null) return null;
        if (!this.itemMeta.hasLore()) {
            List<String> lore = new ArrayList<>();
            lore.add(str);
            this.itemMeta.setLore(lore);
        } else {
            List<String> lore = this.itemMeta.getLore();
            if (this.itemMeta.getLore() != null) {
                lore.add(str);
                this.itemMeta.setLore(lore);
            } else {
                lore = new ArrayList<>();
                lore.add(str);
                this.itemMeta.setLore(lore);
            }
        }
        return this;
    }

    public Material getMaterial() {
        if (this.itemStack == null) return null;
        return this.itemStack.getType();
    }

    public String getLore(int index) {
        if (this.itemMeta == null) return null;
        if (this.itemMeta.hasLore() && this.itemMeta.getLore() != null) {
            return this.itemMeta.getLore().get(index);
        }
        return null;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignore) {
        if (this.itemMeta == null) return null;
        itemMeta.addEnchant(enchantment, level, ignore);
        return this;
    }

    public ItemStack build() {
        if (this.itemMeta == null) {
            return itemStack;
        }
        this.itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static class TestEnchantment extends Enchantment implements Listener {

        @Deprecated
        public TestEnchantment(String namespace) {
            super(new NamespacedKey(namespace, namespace));
        }

        @Override
        public String getName() {
            return "Data";
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.TOOL;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }
    }
}
