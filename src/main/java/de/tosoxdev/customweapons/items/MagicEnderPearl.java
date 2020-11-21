package de.tosoxdev.customweapons.items;

import de.tosoxdev.customweapons.CustomWeapons;
import de.tosoxdev.customweapons.utils.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MagicEnderPearl {
    public static ItemStack magicEnderPearl = ItemCreator.createItem(Material.ENDER_PEARL, 44043,
            ChatColor.GREEN + "Magic Ender Pearl", ChatColor.BLUE + "Can be used to craft end items");

    @Deprecated
    public static ShapedRecipe addItemRecipe() {
        NamespacedKey key = new NamespacedKey(CustomWeapons.getPlugin(), "magic_ender_pearl");
        ShapedRecipe recipe = new ShapedRecipe(key, magicEnderPearl);
        recipe.shape("PPP", "P*P", "PPP");
        recipe.setIngredient('P', Material.ENDER_PEARL);
        recipe.setIngredient('*', Material.AIR);
        return recipe;
    }
}
