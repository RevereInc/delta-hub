package dev.revere.hub.locale;

import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.utils.chat.CC;
import lombok.Getter;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:24
 */
@Getter
public enum Locale {

    NO_PERM("messages.yml", "no-permission");

    private final String configName;
    private final String configString;

    Locale(String configName, String configString) {
        this.configName = configName;
        this.configString = configString;
    }

    public String format() {
        return CC.translate(ConfigHandler.getInstance().getConfig(configName).getString(configString));
    }
}