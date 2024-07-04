package dev.revere.hub.profile.listener;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.hotbar.Hotbar;
import dev.revere.hub.utils.chat.Logger;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:22
 */
public class ProfileListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player joinedPlayer = event.getPlayer();

        joinedPlayer.setFlySpeed(1 * 0.1F);
        joinedPlayer.setWalkSpeed(2 * 0.1F);

        if (joinedPlayer.hasPermission("deltahub.donator.fly")) {
            joinedPlayer.setAllowFlight(true);
            joinedPlayer.setFlying(true);
        }

        event.setJoinMessage(null);
        Hotbar.applySpawnItems(joinedPlayer);
        DeltaHub.getInstance().getSpawnHandler().teleportToSpawn(joinedPlayer);
    }
}