package dev.revere.hub.profile;

import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Remi
 * @project DeltaHub
 * @date 7/4/2024
 */
@Getter
public class ProfileRepository {

    private final HashMap<UUID, Profile> profiles = new HashMap<>();

    public void loadProfiles() {

    }

    /**
     * Get a profile by UUID
     *
     * @param uuid the UUID of the profile
     * @return the profile
     */
    public Profile getProfile(UUID uuid) {
        if (!profiles.containsKey(uuid)) {
            Profile profile = new Profile(uuid);
            profile.loadProfile();
            addProfile(profile);
            return profile;
        }
        return profiles.get(uuid);
    }

    public void addProfile(Profile profile) {
        profiles.put(profile.getUuid(), profile);
    }
}
