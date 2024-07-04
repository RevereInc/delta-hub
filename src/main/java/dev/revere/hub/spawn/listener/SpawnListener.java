package dev.revere.hub.spawn.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 21:11
 */
public class SpawnListener implements Listener {

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        
        if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        

        if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onItemPickUp(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        

        if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        

        if (player.getGameMode() == GameMode.SURVIVAL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onHunger(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onMoveItem(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            

            if (player.getGameMode() == GameMode.SURVIVAL) {
                if (event.getClickedInventory() != null && event.getClickedInventory().equals(player.getInventory())) {
                    event.setCancelled(true);
                }

                if (event.getSlotType() == InventoryType.SlotType.CRAFTING || event.isShiftClick() || event.getClick().isKeyboardClick()) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }
}