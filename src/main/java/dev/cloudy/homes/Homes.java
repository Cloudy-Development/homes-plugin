package dev.cloudy.homes;

import dev.cloudy.homes.system.HomeProvider;
import dev.cloudy.homes.util.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

@Getter
public class Homes extends JavaPlugin {

    @Getter
    private static Homes instance;

    private HomeProvider homeProvider;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.homeProvider = new HomeProvider();

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
