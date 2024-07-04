package dev.revere.hub.locale;

import dev.revere.hub.config.ConfigHandler;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.text.MessageFormat;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:24
 */
@Getter
public enum ConfigLocale {

    NO_PERM("messages.yml", "no-permission");

    private final String configName, configString;

    ConfigLocale(String configName, String configString) {
        this.configName = configName;
        this.configString = configString;
    }

    public String format(Object... objects) {
        return new MessageFormat(ChatColor.translateAlternateColorCodes('&',
                ConfigHandler.getInstance().getConfig(configName).getString(configString))).format(objects);
    }
}