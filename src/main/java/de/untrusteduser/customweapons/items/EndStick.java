package de.untrusteduser.customweapons.items;

import de.untrusteduser.customweapons.CustomWeapons;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EndStick {
    public static ShapedRecipe addItemRecipe() {
        NamespacedKey key = new NamespacedKey(CustomWeapons.getPlugin(), "end_stick");
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());
        recipe.shape("EEE", "ESE", "EEE");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(44042);
        meta.setDisplayName(ChatColor.GREEN + "End Stick");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "Can be used to craft end items");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LURE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
}
