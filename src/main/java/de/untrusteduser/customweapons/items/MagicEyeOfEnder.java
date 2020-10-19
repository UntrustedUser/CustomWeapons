package de.untrusteduser.customweapons.items;

import de.untrusteduser.customweapons.CustomWeapons;
import de.untrusteduser.customweapons.utils.ItemCreator;
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
    public static ItemStack magicEyeOfEnder = ItemCreator.createItem(Material.ENDER_EYE, 44044,
            ChatColor.GREEN + "Magic Eye of Ender", ChatColor.BLUE + "Can be used to craft end items");

    @Deprecated
    public static ShapedRecipe addItemRecipe() {
        NamespacedKey key = new NamespacedKey(CustomWeapons.getPlugin(), "magic_eye_of_ender");
        RecipeChoice magicEnderPearl = new RecipeChoice.ExactChoice(MagicEnderPearl.magicEnderPearl);
        ShapedRecipe recipe = new ShapedRecipe(key, magicEyeOfEnder);
        recipe.shape("PPP", "PEP", "PPP");
        recipe.setIngredient('P', magicEnderPearl);
        recipe.setIngredient('E', Material.ENDER_EYE);
        return recipe;
    }
}
