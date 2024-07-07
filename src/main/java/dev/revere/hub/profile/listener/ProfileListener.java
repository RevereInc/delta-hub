package dev.revere.hub.profile.listener;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.feature.hotbar.HotbarUtility;
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

        if (DeltaHub.getInstance().getConfigHandler().getSettingsConfig().getBoolean("double-jump.enabled")) {
            joinedPlayer.setAllowFlight(true);
        }

        event.setJoinMessage(null);

        HotbarUtility.applySpawnItems(joinedPlayer);

        if (DeltaHub.getInstance().getConfigHandler().getSettingsConfig().getBoolean("spawn.enable-tp")) {
            DeltaHub.getInstance().getSpawnHandler().teleportToSpawn(joinedPlayer);
        }
    }
}