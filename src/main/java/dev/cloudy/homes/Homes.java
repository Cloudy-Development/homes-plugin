package dev.cloudy.homes;

import dev.cloudy.homes.command.ExpireCooldownCommand;
import dev.cloudy.homes.repository.CooldownRepository;
import dev.cloudy.homes.repository.HomeRepository;
import dev.cloudy.homes.command.HomeCommand;
import dev.cloudy.homes.command.HomesCommand;
import dev.cloudy.homes.util.CC;
import dev.cloudy.homes.util.menu.listener.MenuListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

@Getter
public class Homes extends JavaPlugin {

    @Getter
    private static Homes instance;

    private HomeRepository homeRepository;
    private CooldownRepository cooldownRepository;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.homeRepository = new HomeRepository();
        this.cooldownRepository = new CooldownRepository();
        this.cooldownRepository.expireAsynchronously();

        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("homes").setExecutor(new HomesCommand());
        this.getCommand("expirecooldown").setExecutor(new ExpireCooldownCommand());

        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);

        List<String> messages = Arrays.asList(
                "&aHomes plugin has been enabled!",
                "&aAuthor: &b" + getDescription().getAuthors().get(0),
                "&aVersion: &b" + getDescription().getVersion()
        );
        messages.forEach(message -> Bukkit.getConsoleSender().sendMessage(CC.translate(message)));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(CC.translate("&cHomes plugin has been disabled!"));
    }
}
