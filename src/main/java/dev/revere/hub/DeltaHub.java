package dev.revere.hub;

import dev.revere.hub.api.command.CommandManager;
import dev.revere.hub.api.menu.MenuListener;
import dev.revere.hub.commands.DeltaHubCommand;
import dev.revere.hub.commands.global.ProfileCommand;
import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.feature.doublejump.DoubleJumpListener;
import dev.revere.hub.feature.enderbutt.EnderPearlListener;
import dev.revere.hub.feature.fireworklauncher.FireworkLauncherListener;
import dev.revere.hub.feature.hotbar.listener.HotbarListener;
import dev.revere.hub.profile.listener.ProfileListener;
import dev.revere.hub.feature.spawn.SpawnHandler;
import dev.revere.hub.feature.spawn.command.SetSpawnCommand;
import dev.revere.hub.feature.spawn.listener.SpawnListener;
import dev.revere.hub.utils.ServerUtil;
import dev.revere.hub.utils.chat.CC;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 20:11
 */
@Getter
@Setter
public class DeltaHub extends JavaPlugin {

    @Getter
    private static DeltaHub instance;

    private CommandManager commandManager;
    private ConfigHandler configHandler;
    private SpawnHandler spawnHandler;

    @Override
    public void onEnable() {
        instance = this;

        long start = System.currentTimeMillis();

        checkDescription();
        registerHandlers();
        registerManagers();
        registerListeners();
        registerCommands();

        long end = System.currentTimeMillis();
        long timeTaken = end - start;

        CC.pluginEnabled(timeTaken);
    }

    @Override
    public void onDisable() {
        ServerUtil.disconnectPlayers();
        CC.pluginDisabled();
    }

    private void checkDescription() {
        String author = getDescription().getAuthors().get(0);
        String expectedAuthor = "Revere Development";

        Bukkit.getConsoleSender().sendMessage(CC.translate(CC.getPrefix() + "Expected author: &a" + expectedAuthor + "&f, Retrieved author: &c" + author));

        if (!author.equalsIgnoreCase(expectedAuthor)) {
            System.exit(0);
        }
    }

    private void registerHandlers() {
        this.configHandler = new ConfigHandler();

        this.spawnHandler = new SpawnHandler();
        this.spawnHandler.loadSpawn();
    }

    private void registerManagers() {
        this.commandManager = new CommandManager(this);
        this.commandManager.registerCommandsInPackage("dev.revere.hub");
    }

    private void registerListeners() {
        Arrays.asList(
                new EnderPearlListener(),
                new ProfileListener(),
                new HotbarListener(),
                new SpawnListener(),
                new DoubleJumpListener(),
                new FireworkLauncherListener(),
                new MenuListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        new DeltaHubCommand();
        new SetSpawnCommand();
        new ProfileCommand();
    }

    private void registerScoreboard() {

    }

    public FileConfiguration getConfig(String fileName) {
        File configFile = new File(getDataFolder(), fileName);
        return YamlConfiguration.loadConfiguration(configFile);
    }
}
