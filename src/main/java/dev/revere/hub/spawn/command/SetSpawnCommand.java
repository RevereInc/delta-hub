package dev.revere.hub.spawn.command;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.utils.chat.CC;
import dev.revere.hub.api.command.BaseCommand;
import dev.revere.hub.api.command.CommandArgs;
import dev.revere.hub.api.command.annotation.Command;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:15
 */
public class SetSpawnCommand extends BaseCommand {
    @Override
    @Command(name = "setspawn", permission = "deltahub.command.setspawn")
    public void onCommand(CommandArgs cmd) {
        Player player = (Player) cmd.getSender();
        Location location = player.getLocation();
        DeltaHub.getInstance().getSpawnHandler().setSpawn(location);

        String message = ConfigHandler.getInstance().getMessagesConfig().getString("spawn.spawn-set");
        message = message.replace("{world}", location.getWorld().getName())
                .replace("{x}", String.format("%.2f", location.getX()))
                .replace("{y}", String.format("%.2f", location.getY()))
                .replace("{z}", String.format("%.2f", location.getZ()))
                .replace("{yaw}", String.format("%.2f", location.getYaw()))
                .replace("{pitch}", String.format("%.2f", location.getPitch()));

        player.sendMessage(CC.translate(message));
    }
}
