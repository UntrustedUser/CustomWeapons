package de.untrusteduser.customweapons.weapons.range;

import de.untrusteduser.customweapons.CustomWeapons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WandOfFire implements Listener {
    private final ArrayList<String> fireRodCooldown = new ArrayList<>();

    public static ItemStack getWand() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(44040);
        meta.setDisplayName(ChatColor.GOLD + "Wand of Fire");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Shoot fireballs at your enemies");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LURE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().isSimilar(getWand())) {
                if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                    if (!fireRodCooldown.contains(event.getPlayer().getName())) {
                        fireRodCooldown.add(event.getPlayer().getName());
                        Bukkit.getScheduler().scheduleSyncDelayedTask(CustomWeapons.getPlugin(), () -> fireRodCooldown.remove(event.getPlayer().getName()), 80);
                        Fireball fireBall = event.getPlayer().launchProjectile(Fireball.class);
                        fireBall.setYield(0f);
                    }
                }
            }
        }
    }
}
