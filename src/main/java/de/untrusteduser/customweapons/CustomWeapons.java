package de.untrusteduser.customweapons;

import de.untrusteduser.customweapons.commands.GiveWeaponCmd;
import de.untrusteduser.customweapons.weapons.melee.FireRod;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CustomWeapons extends JavaPlugin
{
    public Logger logger;
    private static CustomWeapons plugin;

    @Override
    public void onEnable()
    {
        plugin = this;
        logger = this.getLogger();
        PluginManager pluginManager = Bukkit.getPluginManager();

        logger.info("Enabling Plugin");

        getCommand("cwg").setExecutor(new GiveWeaponCmd());

        pluginManager.registerEvents(new FireRod(), this);
    }

    @Override
    public void onDisable()
    {

    }

    public static CustomWeapons getPlugin()
    {
        return plugin;
    }
}
