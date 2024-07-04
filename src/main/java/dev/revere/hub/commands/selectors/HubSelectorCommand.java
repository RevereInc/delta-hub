package dev.revere.hub.commands.selectors;

import dev.revere.hub.api.command.BaseCommand;
import dev.revere.hub.api.command.CommandArgs;
import dev.revere.hub.api.command.annotation.Command;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 05/07/2024 - 00:24
 */
public class HubSelectorCommand extends BaseCommand {
    @Override
    @Command(name = "hubselector")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        player.sendMessage("e");
    }
}
