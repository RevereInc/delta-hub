package dev.revere.hub.menus.serverselector;

import dev.revere.hub.api.menu.Button;
import dev.revere.hub.api.menu.pagination.ItemBuilder;
import dev.revere.hub.config.ConfigHandler;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 05/07/2024 - 00:13
 */
public class ServerSelectorButton extends Button {
    private final String title;
    private final Material material;
    private final List<String> lore;
    private String command;

    public ServerSelectorButton(String title, Material material, List<String> lore, String command) {
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
        FileConfiguration config = ConfigHandler.getInstance().getServerSelectorConfig();
        if (clickType != ClickType.LEFT) return;
        player.performCommand(command);

        playNeutral(player);
    }
}
