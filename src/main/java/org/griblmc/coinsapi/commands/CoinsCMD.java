package org.griblmc.coinsapi.commands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.griblmc.coinsapi.CoinsAPI;
import org.griblmc.coinsapi.handler.CoinsHandler;
import org.griblmc.coinsapi.mysql.FetchData;
import org.griblmc.coinsapi.utils.Colors;

public class CoinsCMD implements CommandExecutor {
    FetchData fetchData = new FetchData();
    CoinsHandler coinsHandler = new CoinsHandler();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("coins")) {
                if (args.length == 0) {
                    p.sendMessage(Colors.chat("&e&lCoins &8» &7You have &e" +
                            this.fetchData.getCoins(p) + " coins&7."));
                    return true;
                }
                if (args.length == 2) {
                    if (p.hasPermission("coins.admin")) {
                        if (args[0].equalsIgnoreCase("reset")) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                CoinsAPI.getInstance().getAPI().resetCoins(target);
                                p.sendMessage(Colors.chat("&e&lCoins &8» &7Successfully reseted &e" +
                                        target.getName() + "'s coins&7!"));
                                return true;
                            }
                            this.coinsHandler.playerOfflineMessage(p, args[1]);
                        } else {
                            this.coinsHandler.coinsHelpMessage(p);
                        }
                    }
                } else if (args.length == 3) {
                    if (p.hasPermission("coins.admin")) {
                        if (this.coinsHandler.isInt(args[2])) {
                            Player target = Bukkit.getPlayer(args[1]);
                            int coins = Integer.valueOf(args[2]).intValue();
                            if (args[0].equalsIgnoreCase("give")) {
                                if (target != null) {
                                    CoinsAPI.getInstance().getAPI().giveCoins(target, coins);
                                    this.coinsHandler.giveCoinsMessage(p, target, coins);
                                    target.sendMessage(Colors.chat("&e&lCoins &8» &e" + coins +
                                                    " coins &7have been added to your account."));
                                } else {
                                    this.coinsHandler.playerOfflineMessage(p, args[1]);
                                }
                            } else if (args[0].equalsIgnoreCase("remove")) {
                                if (target != null) {
                                    CoinsAPI.getInstance().getAPI().removeCoins(target, coins);
                                    this.coinsHandler.removeCoinsMessage(p, target, coins);
                                } else {
                                    this.coinsHandler.playerOfflineMessage(p, args[1]);
                                }
                            }
                        } else {
                            p.sendMessage(Colors.chat("&e&lCoins &8» &e" + args[2] + " &7is not a number!"));
                        }
                    }
                }
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("reset")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null)
                    CoinsAPI.getInstance().getAPI().resetCoins(target);
            }
        } else if (args.length == 3) {
            if (this.coinsHandler.isInt(args[2])) {
                Player target = Bukkit.getPlayer(args[1]);
                int coins = Integer.valueOf(args[2]).intValue();
                if (args[0].equalsIgnoreCase("give")) {
                    if (target != null)
                        CoinsAPI.getInstance().getAPI().giveCoins(target, coins);
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (target != null)
                        CoinsAPI.getInstance().getAPI().removeCoins(target, coins);
                }
            } else {
                sender.sendMessage(Colors.chat("&e&lCoins &8» &e" + args[2] + " &7is not a number!"));
            }
        }
        return true;
    }
}