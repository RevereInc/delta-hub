package dev.revere.hub.commands;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.utils.chat.CC;
import dev.revere.hub.api.command.BaseCommand;
import dev.revere.hub.api.command.CommandArgs;
import dev.revere.hub.api.command.annotation.Command;
import org.bukkit.command.CommandSender;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:16
 */
public class DeltaHubCommand extends BaseCommand {
    @Override
    @Command(name = "DeltaHub", aliases = {"dhub", "dh"}, inGameOnly = false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        sender.sendMessage(" ");
        sender.sendMessage(CC.MENU_BAR);
        sender.sendMessage(CC.translate("  &b&l   DeltaHub"));
        sender.sendMessage(CC.translate("      &f┃ Author: &b" + String.join(", ", DeltaHub.getInstance().getDescription().getAuthors()) + " &f&b"));
        sender.sendMessage(CC.translate("      &f┃ Version: &b" + DeltaHub.getInstance().getDescription().getVersion()));
        sender.sendMessage(CC.translate(" "));
        sender.sendMessage(CC.translate("  &b&l   Description:"));
        sender.sendMessage(CC.translate("      &f┃ " + DeltaHub.getInstance().getDescription().getDescription()));
        sender.sendMessage(CC.MENU_BAR);
        sender.sendMessage(" ");
    }
}
