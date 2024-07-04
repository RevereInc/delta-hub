package dev.revere.hub.commands.global;

import dev.revere.hub.locale.Locale;
import dev.revere.hub.utils.chat.CC;
import dev.revere.hub.api.command.BaseCommand;
import dev.revere.hub.api.command.CommandArgs;
import dev.revere.hub.api.command.annotation.Command;
import org.bukkit.command.CommandSender;


/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:17
 */
public class ProfileCommand extends BaseCommand {
    @Override
    @Command(name = "profile")
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();

        sender.sendMessage(CC.translate(Locale.DEBUG_CMD));
    }
}