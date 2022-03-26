package org.griblmc.coinsapi.listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.griblmc.coinsapi.mysql.Data;
import org.griblmc.coinsapi.mysql.FetchData;

public class Register implements Listener {
    Data data = new Data();
    FetchData fetchData = new FetchData();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!this.fetchData.isInDatabase(p))
            this.data.giveCoins(p, 0);
    }
}