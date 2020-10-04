package de.untrusteduser.customweapons;

import de.untrusteduser.customweapons.commands.GiveWeaponCmd;
import de.untrusteduser.customweapons.items.EnchantedEnderEye;
import de.untrusteduser.customweapons.items.EnderStick;
import de.untrusteduser.customweapons.items.MagicEnderEye;
import de.untrusteduser.customweapons.listener.player.OnJoin;
import de.untrusteduser.customweapons.weapons.range.WandOfTeleportation;
import de.untrusteduser.customweapons.weapons.range.WandOfFire;
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
        getServer().addRecipe(EnchantedEnderEye.addEnchantedEnderEyeRecipe());
        getServer().addRecipe(MagicEnderEye.addMagicEnderEyeRecipe());
        getServer().addRecipe(WandOfTeleportation.addWandRecipe());

        getCommand("cwg").setExecutor(new GiveWeaponCmd());

        pluginManager.registerEvents(new OnJoin(), this);
        pluginManager.registerEvents(new WandOfFire(), this);
        pluginManager.registerEvents(new WandOfTeleportation(), this);
    }

    @Override
    public void onDisable() {

    }

    public static CustomWeapons getPlugin() {
        return plugin;
    }
}
