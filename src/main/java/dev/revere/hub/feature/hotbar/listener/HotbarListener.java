package dev.revere.hub.feature.hotbar.listener;

import dev.revere.hub.feature.hotbar.HotbarItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 05/07/2024 - 00:00
 */
public class HotbarListener implements Listener {

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getItem() == null) {
            return;
        }

        HotbarItem hotbarItem = HotbarItem.getItem(event.getItem());
        if (hotbarItem == null) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (hotbarItem.getCommand() == null) {
            return;
        }

        player.performCommand(hotbarItem.getCommand());
    }
}
