package dev.revere.hub.profile.listener;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.feature.hotbar.HotbarUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:22
 */
public class ProfileListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setFlySpeed(1 * 0.1F);
        player.setWalkSpeed(2 * 0.1F);

        if (DeltaHub.getInstance().getConfigHandler().getSettingsConfig().getBoolean("double-jump.enabled")) {
            player.setAllowFlight(true);
        }


        player.getInventory().setArmorContents(new ItemStack[4]);
        player.getInventory().setContents(new ItemStack[36]);
        player.getInventory().clear();

        event.setJoinMessage(null);

        HotbarUtility.applySpawnItems(player);
    }
}