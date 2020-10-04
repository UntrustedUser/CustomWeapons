package de.untrusteduser.customweapons.items;

import de.untrusteduser.customweapons.CustomWeapons;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MagicEyeOfEnder {
    @Deprecated
    public static ShapedRecipe addItemRecipe() {
        NamespacedKey key = new NamespacedKey(CustomWeapons.getPlugin(), "magic_ender_eye");
        RecipeChoice enchantedEnderEye = new RecipeChoice.ExactChoice(MagicEnderPearl.getItem());
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());
        recipe.shape("EEE", "E*E", "EEE");
        recipe.setIngredient('E', enchantedEnderEye);
        recipe.setIngredient('*', Material.AIR);
        return recipe;
    }

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Magic Eye of Ender");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "Can be used to create ender-items");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LURE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
}
