package de.untrusteduser.customweapons.weapons.melee;

import de.untrusteduser.customweapons.CustomWeapons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class FireRod implements Listener
{
    private ArrayList<String> shotFireBall;

    public static ItemStack initFireRod(Player player)
    {
        ItemStack fireRod = new ItemStack(Material.BLAZE_ROD);
        ItemMeta fireRodMeta = fireRod.getItemMeta();
        fireRodMeta.setDisplayName(ChatColor.GOLD + "Fire Rod");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Shoot fireballs at your enemies");
        fireRodMeta.setLore(lore);
        fireRod.setItemMeta(fireRodMeta);
        return fireRod;
    }

    @EventHandler
    public void FireRodShot(PlayerInteractEvent event)
    {
        if (Objects.requireNonNull(event.getItem()).getLore() != initFireRod(event.getPlayer()).getLore())
            return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR)
            return;
        if (shotFireBall.contains(event.getPlayer().getName()))
            return;

        shotFireBall.add(event.getPlayer().getName());
        Bukkit.getScheduler().scheduleSyncDelayedTask(CustomWeapons.getPlugin(), () -> shotFireBall.remove(event.getPlayer().getName()), 40);
        Fireball fireBall = event.getPlayer().launchProjectile(Fireball.class);
        fireBall.setIsIncendiary(true);
        fireBall.setYield(0f);
    }
}
