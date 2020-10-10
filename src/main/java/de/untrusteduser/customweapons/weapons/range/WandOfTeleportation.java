package de.untrusteduser.customweapons.weapons.range;

import de.untrusteduser.customweapons.CustomWeapons;
import de.untrusteduser.customweapons.items.EndStick;
import de.untrusteduser.customweapons.items.MagicEyeOfEnder;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WandOfTeleportation implements Listener {
    private final ArrayList<String> teleportRodCooldown = new ArrayList<>();
    private Player thrower;

    @Deprecated
    public static ShapedRecipe addWandRecipe() {
        NamespacedKey key = new NamespacedKey(CustomWeapons.getPlugin(), "wand_of_teleportation");
        RecipeChoice endStick = new RecipeChoice.ExactChoice(EndStick.getItem());
        RecipeChoice magicEyeOfEnder = new RecipeChoice.ExactChoice(MagicEyeOfEnder.getItem());
        ShapedRecipe recipe = new ShapedRecipe(key, getWand());
        recipe.shape("*E*", "*S*", "*S*");
        recipe.setIngredient('E', magicEyeOfEnder);
        recipe.setIngredient('S', endStick);
        recipe.setIngredient('*', Material.AIR);
        return recipe;
    }

    public static ItemStack getWand() {
        ItemStack item = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(44041);
        meta.setDisplayName(ChatColor.BLUE + "Wand of Teleportation");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Throw enderpearls with one click");
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
                if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    if (!teleportRodCooldown.contains(event.getPlayer().getName())) {
                        teleportRodCooldown.add(event.getPlayer().getName());
                        Bukkit.getScheduler().scheduleSyncDelayedTask(CustomWeapons.getPlugin(), () -> teleportRodCooldown.remove(event.getPlayer().getName()), 40);
                        event.getPlayer().launchProjectile(EnderPearl.class);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 8, 1);
                        thrower = event.getPlayer();
                    }
                }
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (thrower != null) {
            if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.ENDER_PEARL)) {
                if (event.getEntityType().equals(EntityType.ENDERMITE)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (player == thrower) {
                thrower = null;
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 8, 1);
                event.setCancelled(true);
            }
        }
    }
}
