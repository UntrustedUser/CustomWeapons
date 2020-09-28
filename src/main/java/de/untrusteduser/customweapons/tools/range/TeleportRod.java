package de.untrusteduser.customweapons.tools.range;

import de.untrusteduser.customweapons.CustomWeapons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class TeleportRod implements Listener
{
    private final ArrayList<String> teleported = new ArrayList<>();

    public static ItemStack initTeleportRod()
    {
        ItemStack teleportRod = new ItemStack(Material.END_ROD);
        ItemMeta teleportRodMeta = teleportRod.getItemMeta();
        teleportRodMeta.setDisplayName(ChatColor.GOLD + "Teleport Rod");
        ArrayList<String> teleportRodLore = new ArrayList<>();
        teleportRodLore.add(ChatColor.WHITE + "Throw enderpearls with one click");
        teleportRodMeta.setLore(teleportRodLore);
        teleportRod.setItemMeta(teleportRodMeta);
        return teleportRod;
    }

    @EventHandler
    public void TeleportRodUsed(PlayerInteractEvent event)
    {
        if (Objects.requireNonNull(event.getItem()).getLore() != initTeleportRod().getLore())
            return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if (teleported.contains(event.getPlayer().getName()))
            return;

        teleported.add(event.getPlayer().getName());
        Bukkit.getScheduler().scheduleSyncDelayedTask(CustomWeapons.getPlugin(), () -> teleported.remove(event.getPlayer().getName()), 40);
        EnderPearl enderPearl = event.getPlayer().launchProjectile(EnderPearl.class);
    }
}
