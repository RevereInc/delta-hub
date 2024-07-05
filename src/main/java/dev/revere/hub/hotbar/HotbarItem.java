package dev.revere.hub.hotbar;

import dev.revere.hub.api.menu.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 22:34
 */
@Getter
public enum HotbarItem {

    SERVER_SELECTOR(Material.COMPASS, "serverselector", "&bServer Selector &7(Right Click)", 3),
    HUB_SELECTOR(Material.NETHER_STAR, "hubselector", "&bHub Selector &7(Right Click)", 5),
    ENDER_BUTT(Material.ENDER_PEARL, "&bEnder Butt &7(Right Click)", 0),

    ;

    private final Material material;
    private final String command;
    private final String name;
    private final int slot;

    HotbarItem(Material material, String command, String name, int slot) {
        this.material = material;
        this.command = command;
        this.name = name;
        this.slot = slot;
    }

    HotbarItem(Material material, String name, int slot) {
        this(material, null, name, slot);
    }

    public ItemStack getItem() {
        return new ItemBuilder(material)
                .name(name)
                .build();
    }

    public static HotbarItem getItem(ItemStack item) {
        for (HotbarItem hotbarItem : values()) {
            if (hotbarItem.getItem().equals(item)) {
                return hotbarItem;
            }
        }
        return null;
    }
}