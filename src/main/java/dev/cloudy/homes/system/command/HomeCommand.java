package dev.cloudy.homes.system.command;

import dev.cloudy.homes.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Homes
 * @date 30/10/2024 - 22:33
 */
public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to execute this command!"));
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(CC.translate("&b&m---------------------------------"));
        player.sendMessage(CC.translate("&f/ &b/home create &3<name> &8- &7Create a new home"));
        player.sendMessage(CC.translate("&f/ &b/home delete &3<name> &8- &7Delete a home"));
        player.sendMessage(CC.translate("&f/ &b/home list &8- &7List all your homes"));
        player.sendMessage(CC.translate("&f/ &b/home teleport &3<name> &8- &7Teleport to a home"));
        player.sendMessage(CC.translate("&f/homes &8- &7Open the homes GUI to manage your homes"));
        player.sendMessage(CC.translate("&b&m---------------------------------"));
        return true;
    }
}
