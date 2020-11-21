package de.tosoxdev.customweapons.items;

import de.tosoxdev.customweapons.CustomWeapons;
import de.tosoxdev.customweapons.utils.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class EndStick {
    public static ItemStack endStick = ItemCreator.createItem(Material.STICK, 44042,
            ChatColor.GREEN + "End Stick", ChatColor.BLUE + "Can be used to craft end items");

    public static ShapedRecipe addItemRecipe() {
        NamespacedKey key = new NamespacedKey(CustomWeapons.getPlugin(), "end_stick");
        ShapedRecipe recipe = new ShapedRecipe(key, endStick);
        recipe.shape("EEE", "ESE", "EEE");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }
}
