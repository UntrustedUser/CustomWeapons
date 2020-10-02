package de.untrusteduser.customweapons.tools.range;

import de.untrusteduser.customweapons.CustomWeapons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TeleportRod implements Listener {
    private final ArrayList<String> teleportRodCooldown = new ArrayList<>();
    private boolean isImmune = false;

    public static ItemStack getTeleportRod() {
        ItemStack teleportRod = new ItemStack(Material.STICK);
        ItemMeta teleportRodMeta = teleportRod.getItemMeta();
        teleportRodMeta.setDisplayName(ChatColor.GOLD + "Teleport Rod");
        ArrayList<String> teleportRodLore = new ArrayList<>();
        teleportRodLore.add(ChatColor.WHITE + "Throw enderpearls with one click");
        teleportRodMeta.setLore(teleportRodLore);
        teleportRod.setItemMeta(teleportRodMeta);
        return teleportRod;
    }

    @EventHandler
    public void PlayerInteractListener(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().isSimilar(getTeleportRod())) {
                if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    if (!teleportRodCooldown.contains(event.getPlayer().getName())) {
                        teleportRodCooldown.add(event.getPlayer().getName());
                        Bukkit.getScheduler().scheduleSyncDelayedTask(CustomWeapons.getPlugin(), () -> teleportRodCooldown.remove(event.getPlayer().getName()), 40);
                        event.getPlayer().launchProjectile(EnderPearl.class);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 8, 1);
                        isImmune = true;
                    }
                }
            }
        }
    }

    @EventHandler
    public void EntityDamageListener(EntityDamageEvent event) {
        if (isImmune) {
            isImmune = false;
            Player player = (Player) event.getEntity();
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 8, 1);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void CreatureSpawnListener(CreatureSpawnEvent event) {
        if (isImmune) {
            if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.ENDER_PEARL)) {
                if (!event.getEntityType().equals(EntityType.ENDERMITE)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
