package org.griblmc.coinsapi;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class Expansion extends PlaceholderExpansion {
    public CoinsAPI instance;

    public Expansion(CoinsAPI plugin) {
        this.instance = plugin;
    }

    public String getIdentifier() {
        return "CoinsAPI";
    }

    public String getAuthor() {
        return this.instance.getDescription().getAuthors().toString();
    }

    public String getVersion() {
        return this.instance.getDescription().getVersion();
    }

    public boolean canRegister() {
        return true;
    }

    public boolean persist() {
        return true;
    }

    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null)
            return "";
        if (identifier.equals("balance"))
            return String.valueOf(CoinsAPI.getInstance().getAPI().getCoins(p));
        return null;
    }
}