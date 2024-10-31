package dev.cloudy.homes.command;

import dev.cloudy.homes.menu.HomesMenu;
import dev.cloudy.homes.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Homes
 * @date 31/10/2024 - 09:54
 */
public class HomesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&cYou must be a player to execute this command!"));
            return true;
        }

        Player player = (Player) commandSender;

        new HomesMenu().openMenu(player);
        return true;
    }
}
