package de.untrusteduser.customweapons.items;

import de.untrusteduser.customweapons.CustomWeapons;
import de.untrusteduser.customweapons.utils.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
