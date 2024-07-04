package dev.revere.hub.menus.serverselector;

import dev.revere.hub.api.menu.Button;
import dev.revere.hub.api.menu.Menu;
import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.utils.chat.CC;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 05/07/2024 - 00:13
 */
public class ServerSelectorMenu extends Menu {
    @Override
    public String getTitle(Player player) {
        return CC.translate(ConfigHandler.getInstance().getServerSelectorConfig().getString("title"));
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        FileConfiguration config = ConfigHandler.getInstance().getServerSelectorConfig();
        ConfigurationSection section = config.getConfigurationSection("items");

        for (String key : section.getKeys(false)) {
            ConfigurationSection buttonSection = section.getConfigurationSection(key);

            int slot = buttonSection.getInt("slot");
            String title = buttonSection.getString("name");
            Material material = Material.valueOf(buttonSection.getString("material"));
            List<String> lore = buttonSection.getStringList("lore");
            String command = buttonSection.getString("command");

            buttons.put(slot, new ServerSelectorButton(title, material, lore, command));
        }

        addGlass(buttons);

        return buttons;
    }

    @Override
    public int getSize() {
        return 3 * 9;
    }
}
