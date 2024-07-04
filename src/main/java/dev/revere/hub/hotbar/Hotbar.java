package dev.revere.hub.hotbar;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 22:53
 */
@UtilityClass
public class Hotbar {
    public void applySpawnItems(Player player) {
        player.getInventory().setItem(HotbarItem.SERVER_SELECTOR.getSlot(), HotbarItem.SERVER_SELECTOR.getItem());
        player.getInventory().setItem(HotbarItem.HUB_SELECTOR.getSlot(), HotbarItem.HUB_SELECTOR.getItem());
    }
}
