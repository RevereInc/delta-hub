package dev.revere.hub.menus.hubselector;

import dev.revere.hub.api.menu.Button;
import dev.revere.hub.api.menu.Menu;
import dev.revere.hub.api.menu.pagination.ItemBuilder;
import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.utils.chat.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 05/07/2024 - 20:25
 */
public class HubSelectorMenu extends Menu {
    @Override
    public String getTitle(Player player) {
        return CC.translate(ConfigHandler.getInstance().getHubSelectorConfig().getString("title"));
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        FileConfiguration config = ConfigHandler.getInstance().getHubSelectorConfig();
        ConfigurationSection section = config.getConfigurationSection("items");

        for (String key : Objects.requireNonNull(section).getKeys(false)) {
            ConfigurationSection buttonSection = section.getConfigurationSection(key);

            int slot = Objects.requireNonNull(buttonSection).getInt("slot");
            String title = buttonSection.getString("name");
            Material material = Material.valueOf(buttonSection.getString("material"));
            List<String> lore = buttonSection.getStringList("lore");
            String command = buttonSection.getString("command");

            buttons.put(slot, new HubSelectorButton(title, material, lore, command));
        }

        addGlass(buttons);

        return buttons;
    }

    @Override
    public int getSize() {
        return ConfigHandler.getInstance().getHubSelectorConfig().getInt("rows") * 9;
    }

    @RequiredArgsConstructor
    private static class HubSelectorButton extends Button {
        private final String title;
        private final Material material;
        private final List<String> lore;
        private String command;

        public HubSelectorButton(String title, Material material, List<String> lore, String command) {
            this.title = title;
            this.material = material;
            this.lore = lore;
            this.command = command;
        }

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(material)
                    .name(title)
                    .lore(lore)
                    .build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            if (clickType != ClickType.LEFT) return;
            player.performCommand(command);

            playNeutral(player);
        }
    }
}
