package org.griblmc.coinsapi.mysql;
import org.griblmc.coinsapi.CoinsAPI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private Connection connection;
    public synchronized void openConnection() {
        try {
            this.connection = DriverManager.getConnection
                    ("jdbc:mysql://" + CoinsAPI.getInstance().getConfig().getString("Host") + ":" +
                            CoinsAPI.getInstance().getConfig().getString("Port") + "/" +
                            CoinsAPI.getInstance().getConfig().getString("Database"),
                            CoinsAPI.getInstance().getConfig().getString("Username"),
                            CoinsAPI.getInstance().getConfig().getString("Password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void closeConnection() {
        try {
            if (!this.connection.isClosed() || this.connection != null)
                this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void checkTable() {
        try {
            getCurrentConnection().createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS `CoinsAPI` (\n`UUID` varchar(36) NOT NULL,\n`Coins` int(11) unsigned NOT NULL,\nPRIMARY KEY  (`UUID`)\n)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCurrentConnection() {
        try {
            if (this.connection == null || this.connection.isClosed())
                this.connection = DriverManager.getConnection
                        ("jdbc:mysql://" + CoinsAPI.getInstance().getConfig().getString("Host") + ":" +
                                        CoinsAPI.getInstance().getConfig().getString("Port") + "/" +
                                        CoinsAPI.getInstance().getConfig().getString("Database"),
                                CoinsAPI.getInstance().getConfig().getString("Username"),
                                CoinsAPI.getInstance().getConfig().getString("Password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }
}