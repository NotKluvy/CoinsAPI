package org.griblmc.coinsapi.handler;
import org.bukkit.entity.Player;
import org.griblmc.coinsapi.utils.Colors;

public class CoinsHandler {
    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void giveCoinsMessage(Player p, Player target, int coins) {
        p.sendMessage(Colors.chat("&e&lCoins &8» &7You have given &e" + coins + " coins &7to &e" +
                target.getName() + "&7."));
        target.sendMessage(Colors.chat("&e&lCoins &8» &e" + p.getName() + " &7has given you &e" +
                coins + " coins&7."));
    }

    public void removeCoinsMessage(Player p, Player target, int coins) {
        p.sendMessage(Colors.chat("&e&lCoins &8» &7You have taken &e" + coins + " coins &7from &e" +
                target.getName() + "'s &7account."));
        target.sendMessage(Colors.chat("&e&lCoins &8» &e" + p.getName() + " &7has taken &e" + coins +
                " coins &7from your account."));
    }

    public void playerOfflineMessage(Player p, String s) {
        p.sendMessage(Colors.chat("&e&lCoins &8» &e" + s + " &7is not online."));
    }

    public void coinsHelpMessage(Player p) {
        p.sendMessage(Colors.chat("&7&m====================================="));
        p.sendMessage(Colors.chat("&7"));
        p.sendMessage(Colors.chat("&8» &e/tokens"));
        p.sendMessage(Colors.chat("&8» &e/coins reset &f(player)"));
        p.sendMessage(Colors.chat("&8» &e/coins give &f(player) (amount)"));
        p.sendMessage(Colors.chat("&8» &e/coins remove &f(player) (amount)"));
        p.sendMessage(Colors.chat("&7"));
        p.sendMessage(Colors.chat("&7&m====================================="));
    }
}