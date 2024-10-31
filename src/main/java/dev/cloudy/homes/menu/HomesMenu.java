package dev.cloudy.homes.menu;

import dev.cloudy.homes.Homes;
import dev.cloudy.homes.object.Home;
import dev.cloudy.homes.repository.HomeRepository;
import dev.cloudy.homes.util.CC;
import dev.cloudy.homes.util.ItemBuilder;
import dev.cloudy.homes.util.menu.Button;
import dev.cloudy.homes.util.menu.Menu;
import dev.cloudy.homes.util.menu.button.BuilderButton;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Emmy
 * @project Homes
 * @date 31/10/2024 - 11:21
 */
@AllArgsConstructor
public class HomesMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Homes";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        HomeRepository homeRepository = Homes.getInstance().getHomeRepository();
        if (homeRepository.getHomes(player).isEmpty()) {
            buttons.put(0, new BuilderButton("&cYou don't have any homes!", new ItemStack(Material.BARRIER), 0, Arrays.asList(
                    "&7&m--------------------------------",
                    "&7You don't have any homes set.",
                    "&7&m--------------------------------"
            )));
        } else {
            Homes.getInstance().getHomeRepository().getHomes(player).forEach(home -> {
                buttons.put(buttons.size(), new HomesButton(home));
            });
        }

        return buttons;
    }

    @AllArgsConstructor
    private static class HomesButton extends Button {
        private Home home;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.PAPER)
                    .name("&b&l" + home.getName())
                    .lore(Arrays.asList(
                            "&7&m--------------------------------",
                            "&7World: &f" + home.getLocation().getWorld().getName(),
                            "&7X: &f" + home.getLocation().getBlockX(),
                            "&7Y: &f" + home.getLocation().getBlockY(),
                            "&7Z: &f" + home.getLocation().getBlockZ(),
                            "",
                            "&aTeleport to home &b[Left-Click]",
                            "&cDelete this home &b[Right-Click]",
                            "&7&m--------------------------------",
                            ""
                            )
                    )
                    .build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            if (clickType == ClickType.LEFT) {
                if (Homes.getInstance().getHomeRepository().teleportToHome(player, home)) return;
                player.closeInventory();
            } else if (clickType == ClickType.RIGHT) {
                new ConfirmMenu(home).openMenu(player);
            }
        }
    }
}
