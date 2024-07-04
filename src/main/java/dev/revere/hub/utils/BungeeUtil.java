package dev.revere.hub.utils;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.utils.chat.CC;
import dev.revere.hub.utils.chat.Logger;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 10:16
 */
@UtilityClass
public class BungeeUtil {
    public void sendPlayer(Player player, String server) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            Logger.logError("Error sending player to server: " + e.getMessage());
        }
        player.sendMessage(CC.translate(DeltaHub.getInstance().getConfig().getString("join.joining").replace("%server%", server)));
        player.sendPluginMessage(DeltaHub.getInstance(), "BungeeCord", stream.toByteArray());
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(CC.translate(DeltaHub.getInstance().getConfig().getString("join.failed").replace("%server%", server)));
            }
        };
        task.runTaskLater(DeltaHub.getInstance(), 20);
    }
}