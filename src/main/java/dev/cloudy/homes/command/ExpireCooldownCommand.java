package dev.cloudy.homes.command;

import dev.cloudy.homes.Homes;
import dev.cloudy.homes.object.Cooldown;
import dev.cloudy.homes.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Homes
 * @date 31/10/2024 - 11:05
 */
public class ExpireCooldownCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to execute this command!"));
            return true;
        }

        Player player = (Player) sender;
        if (strings.length != 1) {
            player.sendMessage(CC.translate("&cUsage: /expirecooldown <player>"));
            return true;
        }

        Player target = Bukkit.getPlayer(strings[0]);
        if (target == null) {
            player.sendMessage(CC.translate("&cPlayer not found!"));
            return true;
        }

        Cooldown cooldown = Homes.getInstance().getCooldownRepository().getCooldown(player);
        if (cooldown == null) {
            player.sendMessage(CC.translate("&cThis player is not on a cooldown!"));
            return true;
        }

        Homes.getInstance().getCooldownRepository().removeCooldown(cooldown);

        player.sendMessage(CC.translate("&aYou have successfully removed the cooldown of &e" + target.getName() + "&a!"));

        if (target == player) {
            return true;
        }

        target.sendMessage(CC.translate("&aYour cooldown has been removed by &e" + player.getName() + "&a!"));
        return true;
    }
}
