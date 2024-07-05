package dev.revere.hub.enderbutt;

import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.hotbar.HotbarItem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 05/07/2024 - 20:48
 */
public class EnderPearlListener implements Listener {

    @EventHandler
    private void onPearl(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (!ConfigHandler.getInstance().getSettingsConfig().getBoolean("enderbutt.enabled")) return;

        if (!player.getItemInHand().equals(HotbarItem.ENDER_BUTT.getItem())) return;

        player.setVelocity(player.getLocation().getDirection().normalize().multiply(2.5F));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1.0F, 1.0F);
        event.setCancelled(true);
    }
}
