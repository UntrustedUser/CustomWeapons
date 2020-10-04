package de.untrusteduser.customweapons.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftEvent implements Listener {
    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getCurrentItem() != null) {
            if (event.getCurrentItem().getItemMeta().hasCustomModelData()) {
                event.setCancelled(true);
            }
        }
    }
}
