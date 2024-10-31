package dev.cloudy.homes.repository;

import dev.cloudy.homes.Homes;
import dev.cloudy.homes.object.Cooldown;
import dev.cloudy.homes.util.CC;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * CooldownRepository class to manage player cooldowns.
 *
 * @author Emmy
 * @project Homes
 * @date 31/10/2024 - 10:39
 */
@Getter
public class CooldownRepository {
    private final Set<Cooldown> cooldowns;

    public CooldownRepository() {
        this.cooldowns = Collections.synchronizedSet(new HashSet<>());
    }

    /**
     * Add a cooldown to the repository.
     *
     * @param cooldown the cooldown to add
     */
    public void addCooldown(Cooldown cooldown) {
        cooldowns.add(cooldown);
    }

    /**
     * Remove a cooldown from the repository.
     *
     * @param cooldown the cooldown to remove
     */
    public void removeCooldown(Cooldown cooldown) {
        cooldowns.remove(cooldown);
    }

    /**
     * Get the cooldown of a player.
     *
     * @param player the player
     * @return the cooldown of the player
     */
    public Cooldown getCooldown(Player player) {
        return cooldowns.stream().filter(cooldown -> cooldown.getPlayer().equals(player)).findFirst().orElse(null);
    }

    /**
     * Get the remaining time of a player's cooldown.
     *
     * @param player the player
     * @return the remaining time of the player's cooldown
     */
    public long getRemaining(Player player) {
        Cooldown cooldown = getCooldown(player);
        return cooldown == null ? 0 : cooldown.getRemaining();
    }

    /**
     * Expire a cooldown.
     *
     * @param cooldown the cooldown to expire
     */
    public void expireCooldown(Cooldown cooldown) {
        cooldowns.remove(cooldown);
    }

    /**
     * Check if a player is on cooldown.
     *
     * @param player the player
     * @return true if the player is on cooldown, false otherwise
     */
    public boolean isOnCooldown(Player player) {
        Cooldown cooldown = getCooldown(player);
        return cooldown != null && !cooldown.hasExpired();
    }

    public void expireAsynchronously() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (cooldowns.isEmpty()) {
                    return;
                }

                cooldowns.removeIf(cooldown -> {
                    if (cooldown.hasExpired()) {
                        cooldown.getPlayer().sendMessage(CC.translate("&aYou are no longer on a home teleport cooldown."));
                        return true;
                    }
                    return false;
                });
            }
        }.runTaskTimerAsynchronously(Homes.getInstance(), 0L, 20L);
    }
}