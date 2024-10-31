package dev.cloudy.homes.command;

import dev.cloudy.homes.Homes;
import dev.cloudy.homes.object.Cooldown;
import dev.cloudy.homes.object.Home;
import dev.cloudy.homes.util.CC;
import dev.cloudy.homes.util.CharUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

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

        if (strings.length == 0) {
            Player player = (Player) sender;
            player.sendMessage("");
            player.sendMessage(CC.translate("&b&lHome Commands:"));
            player.sendMessage(CC.translate(" &f- &b/home create &3<name> &8- &7Create a new home"));
            player.sendMessage(CC.translate(" &f- &b/home delete &3<name> &8- &7Delete a home"));
            player.sendMessage(CC.translate(" &f- &b/home list &8- &7List all your homes"));
            player.sendMessage(CC.translate(" &f- &b/home teleport &3<name> &8- &7Teleport to a home"));
            player.sendMessage(CC.translate(" &f- &b/homes &8- &7View and manage your homes"));
            player.sendMessage("");
            return true;
        } else if (strings[0].equalsIgnoreCase("create")) {
            if (strings.length != 2) {
                sender.sendMessage(CC.translate("&cUsage: /home create <name>"));
                return true;
            }

            Player player = (Player) sender;
            String name = strings[1];

            if (name.contains(Arrays.stream(CharUtil.nonAllowedChars).toString())) {
                player.sendMessage(CC.translate("&cYou cannot use special characters in the home name!"));
                return true;
            }

            Homes.getInstance().getHomeRepository().createHome(player, name, player.getLocation());
            player.sendMessage(CC.translate("&aYou have successfully created a home with the name &e" + name + "&a!"));
            return true;
        } else if (strings[0].equalsIgnoreCase("delete")) {
            if (strings.length != 2) {
                sender.sendMessage(CC.translate("&cUsage: /home delete <name>"));
                return true;
            }

            Player player = (Player) sender;
            String name = strings[1];

            Home home = Homes.getInstance().getHomeRepository().getHome(player, name);
            if (home == null) {
                player.sendMessage(CC.translate("&cA home with the name &e" + name + " &cdoes not exist!"));
                return true;
            }

            Homes.getInstance().getHomeRepository().removeHome(player, home);
            player.sendMessage(CC.translate("&aYou have successfully deleted the home with the name &e" + name + "&a!"));
            return true;
        } else if (strings[0].equalsIgnoreCase("list")) {
            Player player = (Player) sender;
            List<Home> homes = Homes.getInstance().getHomeRepository().getHomes(player);

            player.sendMessage("");
            player.sendMessage(CC.translate("&b&lYour Homes:"));
            if (!homes.isEmpty()) {
                homes.forEach(home -> player.sendMessage(CC.translate("&f- &b" + home.getName()) + CC.translate(" &8- &7" + home.getLocation().getBlockX() + ", " + home.getLocation().getBlockY() + ", " + home.getLocation().getBlockZ())));
            } else {
                player.sendMessage(CC.translate("&7You do not have any homes!"));
            }
            player.sendMessage("");

            return true;
        } else if (strings[0].equalsIgnoreCase("teleport")) {
            if (strings.length != 2) {
                sender.sendMessage(CC.translate("&cUsage: /home teleport <name>"));
                return true;
            }

            Player player = (Player) sender;
            String name = strings[1];

            Home home = Homes.getInstance().getHomeRepository().getHome(player, name);
            if (home == null) {
                player.sendMessage(CC.translate("&cYou do not own a home named &4" + name + "&c!"));
                return true;
            }

            if (!Homes.getInstance().getCooldownRepository().isOnCooldown(player)) {
                Cooldown cooldown = new Cooldown(player, 600000L);
                Homes.getInstance().getCooldownRepository().addCooldown(cooldown);

                player.sendMessage(CC.translate("&cYou are now on a cooldown of 10 minutes before you can teleport to another home."));
            } else {
                player.sendMessage(CC.translate("&cPlease wait before teleporting again. (" + Homes.getInstance().getCooldownRepository().getCooldown(player).getRemainingFormatted() + "s)"));
                return true;
            }

            player.teleport(home.getLocation());
            player.sendMessage(CC.translate("&aYou have successfully teleported to the home with the name &e" + name + "&a!"));
            return true;
        }

        return true;
    }
}