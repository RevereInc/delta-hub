package dev.revere.hub.feature.spawn;

import dev.revere.hub.DeltaHub;
import dev.revere.hub.config.ConfigHandler;
import dev.revere.hub.utils.LocationUtil;
import dev.revere.hub.utils.chat.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 17:11
 */
@Getter
public class SpawnHandler {

    private Location spawn;

    /**
     * Load the spawn location from the settings.yml file
     */
    public void loadSpawn() {
        FileConfiguration config = DeltaHub.getInstance().getConfigHandler().getSettingsConfig();

        Location location = LocationUtil.deserialize(config.getString("spawn.join-location"));

        if (location == null) {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&4&l(!) SPAWN LOCATION IS NULL (!)"));
            return;
        }

        spawn = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    /**
     * Set the spawn location in the settings.yml file
     *
     * @param location the location to set
     */
    public void setSpawn(Location location) {
        this.spawn = location;
        FileConfiguration config = ConfigHandler.getInstance().getSettingsConfig();

        config.set("spawn.join-location", LocationUtil.serialize(location));

        DeltaHub.getInstance().getConfigHandler().saveConfig(DeltaHub.getInstance().getConfigHandler().getConfigFile("settings.yml"), config);
    }

    /**
     * Teleport the player to the spawn location
     *
     * @param player the player to teleport
     */
    public void teleportToSpawn(Player player) {
        Location spawnLocation = getSpawn();
        if (spawnLocation != null) {
            player.teleport(spawnLocation);
        } else {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&4&l(!) SPAWN LOCATION IS NULL (!)"));
        }
    }
}