package dev.revere.hub.profile;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * @author Remi
 * @project DeltaHub
 * @date 7/4/2024
 */
@Getter
@Setter
public class Profile {

    private final UUID uuid;
    private String name;

    /**
     * Constructor for the Profile class
     *
     * @param uuid the UUID of the profile
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.name = Bukkit.getOfflinePlayer(uuid).getName();
    }
}
