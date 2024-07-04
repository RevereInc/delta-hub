package dev.revere.hub.utils;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.utils.chat.CC;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:25
 */
@UtilityClass
public class ServerUtil {
    public void disconnectPlayers() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(CC.getPrefix() + "&cKicked all players due to a server restart."));
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer(CC.translate("&cThe server is restarting.")));
    }
}
