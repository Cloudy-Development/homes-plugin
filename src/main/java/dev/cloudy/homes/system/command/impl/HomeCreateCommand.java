package dev.cloudy.homes.system.command.impl;

import dev.cloudy.homes.Homes;
import dev.cloudy.homes.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Homes
 * @date 30/10/2024 - 22:36
 */
public class HomeCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to execute this command!"));
            return true;
        }

        if (strings.length != 1) {
            sender.sendMessage(CC.translate("&cUsage: /home create <name>"));
            return true;
        }

        Player player = (Player) sender;
        String name = strings[0];

        Homes.getInstance().getHomeProvider().createHome(player, name, player.getLocation());
        player.sendMessage(CC.translate("&aYou have successfully created a home with the name &e" + name + "&a!"));
        return true;
    }
}
