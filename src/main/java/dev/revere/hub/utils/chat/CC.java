package dev.revere.hub.utils.chat;

import dev.revere.hub.DeltaHub;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Emmy
 * @project DeltaHub
 * @date 04/07/2024 - 12:10
 */
@UtilityClass
public class CC {
    @Getter private final String prefix = "&8[&bDelta&8] ";

    public static final String MENU_BAR = ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------";

    private static final String[] SMALL_CHARS = {
            "ᴀ", "ʙ", "ᴄ", "ᴅ", "ᴇ", "ꜰ", "ɢ", "ʜ", "ɪ", "ᴊ", "ᴋ", "ʟ", "ᴍ", "ɴ", "ᴏ", "ᴘ", "ǫ", "ʀ", "ѕ", "ᴛ", "ᴜ", "ᴠ", "ᴡ", "х", "ʏ", "ᴢ",
            "ᴀ", "ʙ", "ᴄ", "ᴅ", "ᴇ", "ꜰ", "ɢ", "ʜ", "ɪ", "ᴊ", "ᴋ", "ʟ", "ᴍ", "ɴ", "ᴏ", "ᴘ", "ǫ", "ʀ", "ѕ", "ᴛ", "ᴜ", "ᴠ", "ᴡ", "х", "ʏ", "ᴢ"
    };

    /**
     * Translates a string with color codes
     *
     * @param text the text to translate
     * @return the translated text
     */
    public static String translate(String text) {
        text = translateHexColors(text);
        text = translateGradients(text);
        text = ChatColor.translateAlternateColorCodes('&', text);

        return text;
    }

    /**
     * Translates a string into small font
     *
     * @param input the input to translate
     * @return the translated text
     */
    public static String toSmallFont(String input) {
        StringBuilder smallText = new StringBuilder(input.length());
        boolean colorMode = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '§') {
                colorMode = true;
                smallText.append(c);
                continue;
            }
            if (colorMode) {
                smallText.append(c);
                colorMode = false;
                continue;
            }
            if (c >= 'a' && c <= 'z') {
                smallText.append(SMALL_CHARS[c - 'a']);
            } else if (c >= 'A' && c <= 'Z') {
                smallText.append(SMALL_CHARS[c - 'A']);
            } else {
                smallText.append(c);
            }
        }
        return smallText.toString();
    }

    /**
     * Translate the color input to a valid color
     *
     * @param colorInput the color input
     * @return the translated color
     */
    public static String translateColor(String colorInput) {
        if (colorInput.startsWith("<#") && colorInput.endsWith(">")) {
            String hexColor = colorInput.substring(2, 8);
            if (isValidHexColor(hexColor)) {
                return "#" + hexColor;
            } else {
                return null;
            }
        } else {

            return Arrays.stream(ChatColor.values()).noneMatch(chatColor -> chatColor.getName().equalsIgnoreCase(colorInput)) ? null : colorInput;
        }
    }

    /**
     * Check if a hex color is valid
     *
     * @param hexColor the hex color to check
     * @return true if the hex color is valid, false otherwise
     */
    public static boolean isValidHexColor(String hexColor) {
        return hexColor.matches("[0-9A-Fa-f]{6}");
    }

    /**
     * Translates hex colors in the format <#FFFFFF>
     *
     * @param text the text to translate
     * @return the translated text
     */
    private static String translateHexColors(String text) {
        Pattern hexPattern = Pattern.compile("<#([A-Fa-f0-9]{6})>");
        Matcher matcher = hexPattern.matcher(text);
        while (matcher.find()) {
            String color = matcher.group(1);
            text = text.replace(matcher.group(), ChatColor.of("#" + color).toString());
        }
        return text;
    }

    /**
     * Translates gradients in the format <gradient:FFFFFF:000000>text</gradient>
     *
     * @param text the text to translate
     * @return the translated text
     */
    private static String translateGradients(String text) {
        Pattern gradientPattern = Pattern.compile("<gradient:([A-Fa-f0-9]{6}):([A-Fa-f0-9]{6})>(.*?)</gradient>");
        Matcher matcher = gradientPattern.matcher(text);
        while (matcher.find()) {
            String startColor = matcher.group(1);
            String endColor = matcher.group(2);
            String gradientText = matcher.group(3);

            String gradient = applyGradient(startColor, endColor, gradientText);
            text = text.replace(matcher.group(), gradient);
        }
        return text;
    }

    /**
     * Applies a gradient to a string
     *
     * @param startColor the start color of the gradient
     * @param endColor the end color of the gradient
     * @param text the text to apply the gradient to
     * @return the text with the gradient applied
     */
    private static String applyGradient(String startColor, String endColor, String text) {
        int length = text.length();

        int[] startRGB = hexToRgb(startColor);
        int[] endRGB = hexToRgb(endColor);

        StringBuilder gradientText = new StringBuilder();

        boolean bold = false;
        boolean italic = false;
        boolean underline = false;
        boolean strikethrough = false;
        boolean magic = false;

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);

            if (c == '&' && i + 1 < length) {
                char formatCode = text.charAt(i + 1);
                switch (formatCode) {
                    case 'l': bold = true; break;
                    case 'o': italic = true; break;
                    case 'n': underline = true; break;
                    case 'm': strikethrough = true; break;
                    case 'k': magic = true; break;
                    case 'r':
                        bold = italic = underline = strikethrough = magic = false;
                        break;
                }
                i++;
                continue;
            }

            double ratio = (double) i / (length - 1);
            int red = (int) (startRGB[0] + ratio * (endRGB[0] - startRGB[0]));
            int green = (int) (startRGB[1] + ratio * (endRGB[1] - startRGB[1]));
            int blue = (int) (startRGB[2] + ratio * (endRGB[2] - startRGB[2]));

            String hex = String.format("#%02x%02x%02x", red, green, blue);
            gradientText.append(ChatColor.of(hex));

            if (bold) gradientText.append(ChatColor.BOLD);
            if (italic) gradientText.append(ChatColor.ITALIC);
            if (underline) gradientText.append(ChatColor.UNDERLINE);
            if (strikethrough) gradientText.append(ChatColor.STRIKETHROUGH);
            if (magic) gradientText.append(ChatColor.MAGIC);

            gradientText.append(c);
        }

        return gradientText.toString();
    }

    /**
     * Converts a hex string to an RGB array
     *
     * @param hex the hex string to convert
     * @return the RGB array
     */
    private static int[] hexToRgb(String hex) {
        return new int[]{
                Integer.valueOf(hex.substring(0, 2), 16),
                Integer.valueOf(hex.substring(2, 4), 16),
                Integer.valueOf(hex.substring(4, 6), 16)
        };
    }

    /**
     * Broadcast a message to all players.
     *
     * @param string The message to send.
     */
    public void broadcast(String string) {
        Bukkit.broadcastMessage(translate(string));
    }

    /**
     * Send a message to the console when the plugin is enabled.
     *
     * @param timeTaken The time taken to enable the plugin.
     */
    public void pluginEnabled(long timeTaken) {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8&m-----------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Plugin: &b" + DeltaHub.getInstance().getDescription().getName()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Authors: &b" + DeltaHub.getInstance().getDescription().getAuthors().toString().replace("[", "").replace("]", "")));
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Version: &b" + DeltaHub.getInstance().getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Link: &b" + DeltaHub.getInstance().getDescription().getWebsite()));
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Spigot: &b" + Bukkit.getName()));
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Load time: &b" + (timeTaken) + " &bms"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8&m-----------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(" ");
    }

    /**
     * Send a message to the console when the plugin is disabled.
     */
    public void pluginDisabled() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8[&b" + DeltaHub.getInstance().getDescription().getName() + "&8] &fSuccessfully disabled.!"));
        Bukkit.getConsoleSender().sendMessage(" ");
    }
}
