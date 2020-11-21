package de.tosoxdev.customweapons.listener.player;

import de.tosoxdev.customweapons.items.EndStick;
import de.tosoxdev.customweapons.items.MagicEnderPearl;
import de.tosoxdev.customweapons.items.MagicEyeOfEnder;
import de.tosoxdev.customweapons.weapons.range.WandOfFire;
import de.tosoxdev.customweapons.weapons.range.WandOfTeleportation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class CraftEvent implements Listener {
    private final ArrayList<ItemStack> itemExceptions = new ArrayList<>(Arrays.asList(WandOfFire.wandOfFire, WandOfTeleportation.wandOfTeleportation,
            EndStick.endStick, MagicEnderPearl.magicEnderPearl, MagicEyeOfEnder.magicEyeOfEnder));
    private final ArrayList<String> confirmed = new ArrayList<>();

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getCurrentItem() != null) {
            if (itemExceptions.contains(event.getCurrentItem())) {
                for (ItemStack item : itemExceptions) {
                    if (event.getRecipe().getResult().isSimilar(item)) {
                        confirmed.add("true");
                    } else {
                        confirmed.add("false");
                    }
                }
                if (!confirmed.contains("true")) {
                    event.setCancelled(true);
                }
                confirmed.clear();
            }
        }
    }
}
