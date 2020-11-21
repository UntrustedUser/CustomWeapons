package de.tosoxdev.customweapons.weapons.range;

import de.tosoxdev.customweapons.CustomWeapons;
import de.tosoxdev.customweapons.items.EndStick;
import de.tosoxdev.customweapons.items.MagicEyeOfEnder;
import de.tosoxdev.customweapons.utils.ItemCreator;
import org.bukkit.*;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;

public class WandOfTeleportation implements Listener {
    private final ArrayList<String> wandOfTeleportationCooldown = new ArrayList<>();
    private Player thrower;
    public static ItemStack wandOfTeleportation = ItemCreator.createItem(Material.DIAMOND_HOE, 44041,
            ChatColor.BLUE + "Wand of Teleportation", ChatColor.WHITE + "Throw enderpearls with one click");

    @Deprecated
    public static ShapedRecipe addWandRecipe() {
        NamespacedKey key = new NamespacedKey(CustomWeapons.getPlugin(), "wand_of_teleportation");
        RecipeChoice endStick = new RecipeChoice.ExactChoice(EndStick.endStick);
        RecipeChoice magicEyeOfEnder = new RecipeChoice.ExactChoice(MagicEyeOfEnder.magicEyeOfEnder);
        ShapedRecipe recipe = new ShapedRecipe(key, wandOfTeleportation);
        recipe.shape("*E*", "*S*", "*S*");
        recipe.setIngredient('E', magicEyeOfEnder);
        recipe.setIngredient('S', endStick);
        recipe.setIngredient('*', Material.AIR);
        return recipe;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().isSimilar(wandOfTeleportation)) {
                if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    if (!wandOfTeleportationCooldown.contains(event.getPlayer().getName())) {
                        wandOfTeleportationCooldown.add(event.getPlayer().getName());
                        Bukkit.getScheduler().scheduleSyncDelayedTask(CustomWeapons.getPlugin(), () -> wandOfTeleportationCooldown.remove(event.getPlayer().getName()), 40);
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
