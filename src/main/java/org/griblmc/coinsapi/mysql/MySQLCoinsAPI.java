package org.griblmc.coinsapi.mysql;
import org.bukkit.entity.Player;

public class MySQLCoinsAPI {
    private final Data data = new Data();
    private final FetchData fetchData = new FetchData();

    public int getCoins(Player p) {
        return this.fetchData.getCoins(p);
    }

    public boolean hasCoins(Player p) {
        return this.fetchData.isInDatabase(p);
    }

    public void resetCoins(Player p) {
        this.data.resetCoins(p);
    }

    public void giveCoins(Player p, int coins) {
        this.data.giveCoins(p, coins);
    }

    public void removeCoins(Player p, int coins) {
        this.data.removeCoins(p, coins);
    }
}