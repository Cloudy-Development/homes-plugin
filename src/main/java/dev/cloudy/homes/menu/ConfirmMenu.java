package dev.cloudy.homes.menu;

import dev.cloudy.homes.Homes;
import dev.cloudy.homes.object.Home;
import dev.cloudy.homes.util.CC;
import dev.cloudy.homes.util.ItemBuilder;
import dev.cloudy.homes.util.menu.Button;
import dev.cloudy.homes.util.menu.Menu;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Emmy
 * @project Homes
 * @date 31/10/2024 - 11:28
 */
@AllArgsConstructor
public class ConfirmMenu extends Menu {
    private Home home;
    
    @Override
    public String getTitle(Player player) {
        return "Delete your home?";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(3, new ConfirmButton(home));
        buttons.put(5, new CancelButton());

        return buttons;
    }

    @AllArgsConstructor
    private static class ConfirmButton extends Button {
        private Home home;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.EMERALD_BLOCK)
                    .name("&a&lConfirm Deletion")
                    .lore(Arrays.asList(
                                    "&7&m--------------------------------",
                                    "&7Are you sure you want to delete this home?",
                                    "&7&m--------------------------------"
                            )
                    )
                    .build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            if (clickType != ClickType.LEFT) return;

            Homes.getInstance().getHomeRepository().deleteHome(player, home);
            player.sendMessage(CC.translate("&cYou have successfully deleted the home &e" + home.getName() + "&c!"));
            player.closeInventory();
        }
    }

    private static class CancelButton extends Button {
        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.REDSTONE_BLOCK)
                    .name("&c&lCancel Deletion")
                    .lore(Arrays.asList(
                                    "&7&m--------------------------------",
                                    "&7Cancel the deletion.",
                                    "&7&m--------------------------------"
                            )
                    )
                    .build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            if (clickType != ClickType.LEFT) {
                return;
            }

            player.closeInventory();
        }
    }
}
