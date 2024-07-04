package dev.revere.hub.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:25
 */
public class EnchantUtil {
    private static final Map<String, String> enchantment = new HashMap<>();

    static {
        enchantment.put("sharpness", "DAMAGE_ALL");
        enchantment.put("efficiency", "DIG_SPEED");
        enchantment.put("unbreaking", "DURABILITY");
        enchantment.put("fortune", "LOOT_BONUS_BLOCKS");
        enchantment.put("power", "ARROW_DAMAGE");
        enchantment.put("punch", "ARROW_KNOCKBACK");
        enchantment.put("flame", "ARROW_FIRE");
        enchantment.put("infinity", "ARROW_INFINITE");
    }

    public static String getEnchantment(String key) {
        return enchantment.get(key.toLowerCase());
    }
}

