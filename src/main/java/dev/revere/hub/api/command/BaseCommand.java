package dev.revere.hub.api.command;

/**
 * @author Remi
 * @project Delta
 * @date 6/17/2024
 */
public abstract class BaseCommand {

    public BaseCommand() {
        CommandManager.getInstance().registerCommands(this, null);
    }

    public abstract void onCommand(CommandArgs command);
}