package org.griblmc.coinsapi.utils;
import org.bukkit.ChatColor;

public class Colors {
    public static String chat(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}