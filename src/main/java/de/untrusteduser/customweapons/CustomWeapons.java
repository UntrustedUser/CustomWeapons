package de.untrusteduser.customweapons;

import de.untrusteduser.customweapons.commands.GiveWeaponCmd;
import de.untrusteduser.customweapons.items.EnchantedEnderEye;
import de.untrusteduser.customweapons.items.EnderStick;
import de.untrusteduser.customweapons.items.MagicEnderEye;
import de.untrusteduser.customweapons.weapons.range.TeleportRod;
import de.untrusteduser.customweapons.weapons.range.FireRod;
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

        getServer().addRecipe(EnderStick.addEnderStickRecipe());
        logger.info("4");
        getServer().addRecipe(EnchantedEnderEye.addEnchantedEnderEyeRecipe());
        logger.info("3");
        getServer().addRecipe(MagicEnderEye.addMagicEnderEyeRecipe());
        logger.info("2");
        getServer().addRecipe(TeleportRod.addTeleportRodRecipe());
        logger.info("1");

        getCommand("cwg").setExecutor(new GiveWeaponCmd());

        pluginManager.registerEvents(new FireRod(), this);
        pluginManager.registerEvents(new TeleportRod(), this);
    }

    @Override
    public void onDisable() {

    }

    public static CustomWeapons getPlugin() {
        return plugin;
    }
}
