package de.untrusteduser.customweapons.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GiveWeaponCmpl implements TabCompleter {
    List<String> arguments0 = new ArrayList<>();
    List<String> arguments1 = new ArrayList<>();
    List<String> arguments2 = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (Bukkit.getOnlinePlayers().size() > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                arguments0.add(p.getName());
            }
        }
        if (arguments1.isEmpty()) {
            arguments1.add("wand");
            arguments1.add("item");
        }
        if (arguments2.isEmpty()) {
            arguments1.add("wand_of_fire");
            arguments1.add("wand_of_teleportation");
            arguments1.add("wand_of_thunder");
        }

        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            for (String s : arguments0) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    results.add(s);
                }
            }
            return results;
        }
        else if (args.length == 2) {
            for (String s : arguments1) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    results.add(s);
                }
            }
            return results;
        }
        else if (args.length == 3) {
            for (String s : arguments2) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    results.add(s);
                }
            }
            return results;
        }

        return null;
    }
}
