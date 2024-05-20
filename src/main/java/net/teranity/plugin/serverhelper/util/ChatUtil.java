package net.teranity.plugin.serverhelper.util;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String sendColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
