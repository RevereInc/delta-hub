package dev.revere.hub.handler;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.utils.chat.Logger;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:18
 */
@Getter
public class ConfigHandler {

    @Getter private static ConfigHandler instance;

    private final DeltaHub plugin = DeltaHub.getInstance();

    private final Map<String, File> configFiles = new HashMap<>();
    private final Map<String, FileConfiguration> fileConfigurations = new HashMap<>();

    private final String[] configFileNames = {
            "settings.yml", "messages.yml", "scoreboard.yml"
    };

    private final FileConfiguration settingsConfig;
    private final FileConfiguration messagesConfig;
    private final FileConfiguration scoreboardConfig;

    public ConfigHandler() {
        instance = this;

        for (String fileName : configFileNames) {
            loadConfig(fileName);
        }

        settingsConfig = getConfig("settings.yml");
        messagesConfig = getConfig("messages.yml");
        scoreboardConfig = getConfig("scoreboard.yml");
    }

    private void loadConfig(String fileName) {
        File configFile = new File(plugin.getDataFolder(), fileName);
        configFiles.put(fileName, configFile);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        fileConfigurations.put(fileName, config);
    }

    public void reloadConfigs() {
        for (String fileName : configFileNames) {
            loadConfig(fileName);
        }
    }

    public void saveConfigs() {
        for (Map.Entry<String, FileConfiguration> entry : fileConfigurations.entrySet()) {
            String fileName = entry.getKey();
            FileConfiguration config = entry.getValue();
            File configFile = configFiles.get(fileName);
            saveConfig(configFile, config);
        }
    }

    public void saveConfig(File configFile, FileConfiguration fileConfiguration) {
        try {
            fileConfiguration.save(configFile);
            fileConfiguration.load(configFile);
        } catch (Exception e) {
            Logger.logError("Error occurred while saving config: " + configFile.getName());
        }
    }

    public FileConfiguration getConfig(String fileName) {
        return fileConfigurations.get(fileName);
    }

    public File getConfigFile(String fileName) {
        return configFiles.get(fileName);
    }
}