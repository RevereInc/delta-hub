package dev.revere.hub.feature.fireworklauncher;

import dev.revere.hub.DeltaHub;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 07/07/2024 - 19:35
 */
public class FireworkLauncherListener implements Listener {

    private final DeltaHub plugin = DeltaHub.getInstance();

    private Firework currentFirework = null;
    private BukkitTask followEffectTask = null;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (plugin.getConfigHandler().getSettingsConfig().getBoolean("firework-launcher.enabled")) {
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                if (item != null && item.getType() == Material.FIREWORK_ROCKET) {
                    if (currentFirework != null) {
                        currentFirework.remove();
                        followEffectTask.cancel();
                    }

                    currentFirework = player.getWorld().spawn(player.getLocation(), Firework.class);

                    FireworkEffect effect = FireworkEffect.builder()
                            .withColor(Color.RED, Color.GREEN, Color.BLUE)
                            .withFade(Color.YELLOW)
                            .with(FireworkEffect.Type.BALL_LARGE)
                            .trail(true)
                            .flicker(true)
                            .build();

                    FireworkMeta meta = currentFirework.getFireworkMeta();
                    meta.addEffect(effect);
                    meta.setPower(2);
                    currentFirework.setFireworkMeta(meta);

                    currentFirework.setPassenger(player);

                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0F, 1.0F);

                    followEffectTask = new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!player.isOnline() || currentFirework.isDead() || !currentFirework.isValid() || currentFirework.getPassenger() == null || !currentFirework.getPassenger().equals(player)) {
                                if (currentFirework != null) {
                                    currentFirework.remove();
                                    currentFirework = null;
                                }
                                if (followEffectTask != null) {
                                    followEffectTask.cancel();
                                    followEffectTask = null;
                                }
                            }
                        }
                    }.runTaskTimer(plugin, 1L, 1L);
                }
            }
        }
    }
}
