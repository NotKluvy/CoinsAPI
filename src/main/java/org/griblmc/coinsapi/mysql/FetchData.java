package org.griblmc.coinsapi.mysql;
import org.bukkit.entity.Player;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchData {
    MySQL mysql = new MySQL();
    public int getCoins(Player p) {
        try {
            ResultSet r = this.mysql.getCurrentConnection().createStatement().executeQuery
                    ("SELECT Coins FROM CoinsAPI WHERE UUID = '" + p.getUniqueId().toString() + "'");
            if (r.next())
                return r.getInt("Coins");
            r.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isInDatabase(Player p) {
        try {
            ResultSet r = this.mysql.getCurrentConnection().createStatement().executeQuery
                    ("SELECT * FROM CoinsAPI WHERE UUID = '" + p.getUniqueId().toString() + "'");
            boolean contains = r.next();
            r.close();
            return contains;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}