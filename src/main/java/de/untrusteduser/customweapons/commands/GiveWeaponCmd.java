package de.untrusteduser.customweapons.commands;

import de.untrusteduser.customweapons.tools.range.TeleportRod;
import de.untrusteduser.customweapons.weapons.range.FireRod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveWeaponCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player)sender;
            if (player.hasPermission("customweapons.give"))
            {
                if (args.length == 1)
                {
                    switch (args[0].toLowerCase())
                    {
                        case "firerod":
                            player.getInventory().addItem(FireRod.initFireRod());
                            break;
                        case "teleportrod":
                            player.getInventory().addItem(TeleportRod.initTeleportRod());
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    player.sendMessage(ChatColor.BLUE + "[MCChallenges] " + ChatColor.RED + "Please use " + ChatColor.GOLD + "/cwg <WEAPON>" + ChatColor.RED + "!");
                }
            }
            else
            {
                player.sendMessage(ChatColor.BLUE + "[MCChallenges] " + ChatColor.RED + "You don't have the permissions to do that!");
            }
        }
        else
        {
            sender.sendMessage(ChatColor.BLUE + "[CustomWeapons] " + ChatColor.RED + "This command is only available for players!");
        }
        return false;
    }
}
