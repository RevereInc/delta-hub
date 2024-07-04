package dev.revere.hub.utils.chat;

/**
 * Created by somebody unknown
 * Project: FlowerCore
 * Discord: dsc.gg/emmiesa
 */

public class StringUtil {
    public static String repeat(char character, int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(character);
        }
        return result.toString();
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
