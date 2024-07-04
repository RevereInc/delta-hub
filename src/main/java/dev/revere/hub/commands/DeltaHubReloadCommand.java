package dev.revere.hub.commands;

import dev.revere.hub.api.command.BaseCommand;
import dev.revere.hub.api.command.CommandArgs;
import dev.revere.hub.api.command.annotation.Command;
import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.utils.chat.CC;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 05/07/2024 - 00:58
 */
public class DeltaHubReloadCommand extends BaseCommand {
    @Override
    @Command(name = "deltahubreload", permission = "deltahub.command.reload")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        player.sendMessage(CC.translate("&cReloading..."));
        ConfigHandler.getInstance().reloadConfigs();
        player.sendMessage(CC.translate(CC.getPrefix() + "&aSuccessfully reloaded the configuration files."));
    }
}
