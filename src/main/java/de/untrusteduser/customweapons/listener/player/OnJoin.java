package de.untrusteduser.customweapons.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    @Deprecated
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().setResourcePack("https://drive.google.com/uc?export=download&id=1bgPFhVxL-2Tw0kS9KvSSA9gC4D_u11ki");
    }
}
