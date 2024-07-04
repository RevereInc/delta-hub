package dev.revere.hub.commands.selectors;

import dev.revere.hub.api.command.BaseCommand;
import dev.revere.hub.api.command.CommandArgs;
import dev.revere.hub.api.command.annotation.Command;
import dev.revere.hub.menus.serverselector.ServerSelectorMenu;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 05/07/2024 - 00:23
 */
public class ServerSelectorCommand extends BaseCommand {
    @Override
    @Command(name = "serverselector")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        new ServerSelectorMenu().openMenu(player);
    }
}
