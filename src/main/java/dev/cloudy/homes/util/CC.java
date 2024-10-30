package dev.cloudy.homes.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

/**
 * @author Emmy
 * @project Homes
 * @date 30/10/2024 - 22:17
 */
@UtilityClass
public class CC {
    /**
     * Translate the message to the color code
     * Replace the & with the color code
     *
     * @param message the message to translate
     * @return the translated message
     */
    public String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
