package de.untrusteduser.customweapons.commands;

import de.untrusteduser.customweapons.weapons.range.WandOfTeleportation;
import de.untrusteduser.customweapons.weapons.range.WandOfFire;
import de.untrusteduser.customweapons.weapons.range.WandOfThunder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveWeaponCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("customweapons.give")) {
                if (args.length == 3) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        switch (args[1].toLowerCase()) {
                            case "wand":
                            case "item":
                            default:
                                break;
                        }
                        switch (args[2].toLowerCase()) {
                            case "wand_of_fire":
                                player.getInventory().addItem(WandOfFire.wandOfFire);
                                break;
                            case "wand_of_teleportation":
                                player.getInventory().addItem(WandOfTeleportation.wandOfTeleportation);
                                break;
                            case "wand_of_thunder":
                                player.getInventory().addItem(WandOfThunder.wandOfThunder);
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.BLUE + "[CustomWeapons] " + ChatColor.RED + "Please use " + ChatColor.GOLD + "/cwg <WEAPON>" + ChatColor.RED + "!");
                }
            } else {
                player.sendMessage(ChatColor.BLUE + "[CustomWeapons] " + ChatColor.RED + "You don't have the permissions to do that!");
            }
        } else {
            sender.sendMessage(ChatColor.BLUE + "[CustomWeapons] " + ChatColor.RED + "This command is only available for players!");
        }
        return false;
    }
}
