package de.untrusteduser.customweapons.weapons.range;

import de.untrusteduser.customweapons.CustomWeapons;
import de.untrusteduser.customweapons.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class WandOfFire implements Listener {
    private final ArrayList<String> fireRodCooldown = new ArrayList<>();
    public static ItemStack wandOfFire = ItemCreator.createItem(Material.BLAZE_ROD, 44040,
            ChatColor.GOLD + "Wand of Fore", ChatColor.WHITE + "Shoot fireballs at your enemies");

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().isSimilar(wandOfFire)) {
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
