package de.tosoxdev.customweapons.commands;

import de.tosoxdev.customweapons.items.EndStick;
import de.tosoxdev.customweapons.items.MagicEnderPearl;
import de.tosoxdev.customweapons.items.MagicEyeOfEnder;
import de.tosoxdev.customweapons.weapons.range.WandOfTeleportation;
import de.tosoxdev.customweapons.weapons.range.WandOfFire;
import de.tosoxdev.customweapons.weapons.range.WandOfThunder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GiveWeaponCmd implements CommandExecutor, TabCompleter {
    List<String> arguments0 = new ArrayList<>();
    List<String> arguments1 = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("customweapons.give")) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        switch (args[1].toLowerCase()) {
                            case "wand_of_fire":
                                player.getInventory().addItem(WandOfFire.wandOfFire);
                                break;
                            case "wand_of_teleportation":
                                player.getInventory().addItem(WandOfTeleportation.wandOfTeleportation);
                                break;
                            case "wand_of_thunder":
                                player.getInventory().addItem(WandOfThunder.wandOfThunder);
                                break;
                            case "end_stick":
                                player.getInventory().addItem(EndStick.endStick);
                                break;
                            case "magic_ender_pearl":
                                player.getInventory().addItem(MagicEnderPearl.magicEnderPearl);
                                break;
                            case "magic_eye_of_ender":
                                player.getInventory().addItem(MagicEyeOfEnder.magicEyeOfEnder);
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.BLUE + "[CustomWeapons] " + ChatColor.RED + "Please use " + ChatColor.GOLD + "/cwg <PLAYER> <ITEM>" + ChatColor.RED + "!");
                }
            } else {
                player.sendMessage(ChatColor.BLUE + "[CustomWeapons] " + ChatColor.RED + "You don't have the permissions to do that!");
            }
        } else {
            sender.sendMessage(ChatColor.BLUE + "[CustomWeapons] " + ChatColor.RED + "This command is only available for players!");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (Bukkit.getOnlinePlayers().size() > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                arguments0.add(p.getName());
            }
        }
        if (arguments1.isEmpty()) {
            arguments1.add("wand_of_fire");
            arguments1.add("wand_of_teleportation");
            arguments1.add("wand_of_thunder");
            arguments1.add("end_stick");
            arguments1.add("magic_ender_pearl");
            arguments1.add("magic_eye_of_ender");
        }

        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            for (String s : arguments0) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    results.add(s);
                }
            }
            return results;
        } else if (args.length == 2) {
            for (String s : arguments1) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    results.add(s);
                }
            }
            return results;
        }

        return null;
    }
}
