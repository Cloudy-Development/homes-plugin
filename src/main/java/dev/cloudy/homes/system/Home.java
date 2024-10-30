package dev.cloudy.homes.system;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

/**
 * @author Emmy
 * @project Homes
 * @date 30/10/2024 - 22:23
 */
@Getter
@Setter
public class Home {
    private String name;
    private String world;
    private Location location;

    /**
     * Constructor of the Home class.
     *
     * @param name        the name of the home
     * @param world       the world of the home
     * @param location    the location of the home
     */
    public Home(String name, String world, Location location) {
        this.name = name;
        this.world = world;
        this.location = location;
    }
}