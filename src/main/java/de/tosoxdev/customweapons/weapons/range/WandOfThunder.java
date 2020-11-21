package de.tosoxdev.customweapons.weapons.range;

import de.tosoxdev.customweapons.CustomWeapons;
import de.tosoxdev.customweapons.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class WandOfThunder implements Listener {
    private final ArrayList<String> wandOfThunderCooldown = new ArrayList<>();
    public static ItemStack wandOfThunder = ItemCreator.createItem(Material.BLAZE_ROD, 44045,
            ChatColor.BLUE + "Wand of Thunder", ChatColor.WHITE + "Summon lightning bolts to burn your enemies");

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().isSimilar(wandOfThunder)) {
                if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    if (!wandOfThunderCooldown.contains(event.getPlayer().getName())) {
                        wandOfThunderCooldown.add(event.getPlayer().getName());
                        Bukkit.getScheduler().scheduleSyncDelayedTask(CustomWeapons.getPlugin(), () -> wandOfThunderCooldown.remove(event.getPlayer().getName()), 80);
                        Location block = event.getPlayer().getTargetBlock(300).getLocation();
                        if (block != null) {
                            event.getPlayer().getWorld().strikeLightning(block);
                        }
                    }
                }
            }
        }
    }
}
