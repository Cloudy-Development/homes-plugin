package dev.cloudy.homes.object;

import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Homes
 * @date 31/10/2024 - 10:38
 */
@Getter
public class Cooldown {
    private final Player player;
    private final long expireTime;

    public Cooldown(Player player, long duration) {
        this.player = player;
        this.expireTime = System.currentTimeMillis() + duration;
    }

    /**
     * Get the remaining time of the cooldown.
     *
     * @return the remaining time
     */
    public long getRemaining() {
        return expireTime - System.currentTimeMillis();
    }

    /**
     * Check if the cooldown has expired.
     *
     * @return true if the cooldown has expired
     */
    public boolean hasExpired() {
        return getRemaining() <= 0;
    }

    /**
     * Get the remaining time formatted as HH:MM:SS
     *
     * @return the formatted remaining time
     */
    public String getRemainingFormatted() {
        long remaining = getRemaining();
        long seconds = remaining / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
    }
}