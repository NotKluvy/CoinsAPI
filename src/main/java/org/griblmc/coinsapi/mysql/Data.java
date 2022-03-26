package org.griblmc.coinsapi.mysql;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.griblmc.coinsapi.CoinsAPI;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Data {
    MySQL mysql = new MySQL();
    FetchData fetchData = new FetchData();
    public void giveCoins(final Player p, final int coins) {
        Bukkit.getScheduler().runTaskAsynchronously(CoinsAPI.getInstance(),
                new Runnable() {
                    public void run() {
                        try {
                            PreparedStatement sql = Data.this.mysql.getCurrentConnection().prepareStatement
                                    ("INSERT INTO `CoinsAPI` (UUID, Coins) VALUES (?,?) ON DUPLICATE KEY UPDATE Coins = ?");
                            sql.setString(1, p.getUniqueId().toString());
                            sql.setInt(2, coins);
                            sql.setInt(3, coins + Data.this.fetchData.getCoins(p));
                            sql.execute();
                            sql.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void removeCoins(final Player p, final int coins) {
        Bukkit.getScheduler().runTaskAsynchronously(CoinsAPI.getInstance(),
                new Runnable() {
                    public void run() {
                        try {
                            PreparedStatement sql = Data.this.mysql.getCurrentConnection().prepareStatement
                                    ("INSERT INTO `CoinsAPI` (UUID, Coins) VALUES (?,?) ON DUPLICATE KEY UPDATE Coins = ?");
                            sql.setString(1, p.getUniqueId().toString());
                            sql.setInt(2, Data.this.fetchData.getCoins(p) - coins);
                            sql.setInt(3, Data.this.fetchData.getCoins(p) - coins);
                            sql.execute();
                            sql.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void resetCoins(final Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(CoinsAPI.getInstance(),
                new Runnable() {
                    public void run() {
                        try {
                            PreparedStatement sql = Data.this.mysql.getCurrentConnection().prepareStatement
                                    ("INSERT INTO `CoinsAPI` (UUID, Coins) VALUES (?,?) ON DUPLICATE KEY UPDATE Coins = ?");
                            sql.setString(1, p.getUniqueId().toString());
                            sql.setInt(2, 0);
                            sql.setInt(3, 0);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}