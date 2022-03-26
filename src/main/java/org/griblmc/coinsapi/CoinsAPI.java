package org.griblmc.coinsapi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.griblmc.coinsapi.commands.CoinsCMD;
import org.griblmc.coinsapi.listener.Register;
import org.griblmc.coinsapi.mysql.MySQL;
import org.griblmc.coinsapi.mysql.MySQLCoinsAPI;

public final class CoinsAPI extends JavaPlugin {
    private final MySQLCoinsAPI api = new MySQLCoinsAPI();
    private final MySQL mysql = new MySQL();
    private static CoinsAPI instance;

    public void onEnable() {
        instance = this;
        getLogger().info("Loading listeners...");
        loadListeners();
        getLogger().info("Loading commands...");
        loadCommands();
        getLogger().info("Loading config...");
        saveDefaultConfig();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getServer().getPluginManager().disablePlugin(this);
            getLogger().severe("Disabled the plugin!");
            getLogger().severe("Make sure to install PlaceholderAPI first!");
        }
        this.mysql.checkTable();
    }

    public void onDisable() {
        instance = null;
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new Register(), this);
    }

    private void loadCommands() {
        getCommand("coins").setExecutor(new CoinsCMD());
    }

    public static CoinsAPI getInstance() {
        return instance;
    }

    public MySQLCoinsAPI getAPI() {
        return this.api;
    }
}