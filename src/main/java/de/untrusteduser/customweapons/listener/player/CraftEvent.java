package de.untrusteduser.customweapons.listener.player;

import de.untrusteduser.customweapons.items.EndStick;
import de.untrusteduser.customweapons.items.MagicEnderPearl;
import de.untrusteduser.customweapons.items.MagicEyeOfEnder;
import de.untrusteduser.customweapons.weapons.range.WandOfFire;
import de.untrusteduser.customweapons.weapons.range.WandOfTeleportation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class CraftEvent implements Listener {
    private final ArrayList<ItemStack> itemExceptions = new ArrayList<>(Arrays.asList(WandOfFire.wandOfFire, WandOfTeleportation.wandOfTeleportation,
            EndStick.getItem(), MagicEnderPearl.getItem(), MagicEyeOfEnder.getItem()));
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
