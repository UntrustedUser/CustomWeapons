package de.tosoxdev.customweapons.listener.player;

import de.tosoxdev.customweapons.items.MagicEnderPearl;
import de.tosoxdev.customweapons.items.MagicEyeOfEnder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                if ((event.getItem().isSimilar(MagicEnderPearl.magicEnderPearl)) || (event.getItem().isSimilar(MagicEyeOfEnder.magicEyeOfEnder))) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
