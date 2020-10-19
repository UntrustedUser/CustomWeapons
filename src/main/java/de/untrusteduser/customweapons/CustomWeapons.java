package de.untrusteduser.customweapons;

import de.untrusteduser.customweapons.commands.GiveWeaponCmd;
import de.untrusteduser.customweapons.items.MagicEnderPearl;
import de.untrusteduser.customweapons.items.EndStick;
import de.untrusteduser.customweapons.items.MagicEyeOfEnder;
import de.untrusteduser.customweapons.listener.player.CraftEvent;
import de.untrusteduser.customweapons.listener.player.InteractEvent;
import de.untrusteduser.customweapons.listener.player.JoinEvent;
import de.untrusteduser.customweapons.weapons.range.WandOfTeleportation;
import de.untrusteduser.customweapons.weapons.range.WandOfFire;
import de.untrusteduser.customweapons.weapons.range.WandOfThunder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CustomWeapons extends JavaPlugin {
    public Logger logger;
    private static CustomWeapons plugin;

    @Override
    @Deprecated
    public void onEnable() {
        plugin = this;
        logger = this.getLogger();
        PluginManager pluginManager = Bukkit.getPluginManager();

        logger.info("Enabling Plugin");

        getServer().addRecipe(EndStick.addItemRecipe());
        getServer().addRecipe(MagicEnderPearl.addItemRecipe());
        getServer().addRecipe(MagicEyeOfEnder.addItemRecipe());
        getServer().addRecipe(WandOfTeleportation.addWandRecipe());

        getCommand("cwg").setExecutor(new GiveWeaponCmd());

        pluginManager.registerEvents(new JoinEvent(), this);
        pluginManager.registerEvents(new CraftEvent(), this);
        pluginManager.registerEvents(new InteractEvent(), this);
        pluginManager.registerEvents(new WandOfFire(), this);
        pluginManager.registerEvents(new WandOfTeleportation(), this);
        pluginManager.registerEvents(new WandOfThunder(), this);
    }

    @Override
    public void onDisable() {

    }

    public static CustomWeapons getPlugin() {
        return plugin;
    }
}
