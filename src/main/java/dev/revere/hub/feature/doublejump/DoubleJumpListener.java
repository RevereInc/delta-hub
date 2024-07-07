package dev.revere.hub.feature.doublejump;

import dev.revere.hub.DeltaHub;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 07/07/2024 - 19:17
 */
public class DoubleJumpListener implements Listener {

    @EventHandler
    private void onDoubleJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (DeltaHub.getInstance().getConfigHandler().getSettingsConfig().getBoolean("double-jump.enabled")) {
            if (player.getAllowFlight()) {
                player.setFlying(false);
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                player.setVelocity(player.getLocation().getDirection().normalize().multiply(1.5F).setY(1.0F));
                event.setCancelled(true);
            }
        }
    }
}
