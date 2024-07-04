package dev.revere.hub.spawn.command;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.utils.chat.CC;
import dev.revere.hub.api.command.BaseCommand;
import dev.revere.hub.api.command.CommandArgs;
import dev.revere.hub.api.command.annotation.Command;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 21:23
 */
public class SpawnCommand extends BaseCommand {
    @Override
    @Command(name = "spawn", permission = "deltahub.command.spawn")
    public void onCommand(CommandArgs args) {
        Player player = args.getPlayer();

        DeltaHub.getInstance().getSpawnHandler().teleportToSpawn(player);
        player.sendMessage(CC.translate(ConfigHandler.getInstance().getMessagesConfig().getString("spawn.teleported")));
    }
}