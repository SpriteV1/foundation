package us.vindere.foundation.commands.game;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.placeholders.commands.game.phFeed;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.Tools;

public class FeedCommand implements CommandExecutor{
    Foundation main;
    public FeedCommand(Foundation instance) { main = instance; }
    phError error = new phError();
    phFeed feed = new phFeed();

    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        Config config = new Config(this);
        Player player = Tools.getPlayer(sender);
        Permission selfFeed = new Permission("f.feed.self");
        Permission othersFeed = new Permission("f.feed.others");

        if (player != null) {
            if (args.length < 1) {
                if (sender.hasPermission(selfFeed)) {
                    player.setExhaustion(0);
                    player.setFoodLevel(20);
                    feed.feeded(main, sender);
                    return true;
                } else {
                    error.noPerm(main, sender, selfFeed);
                    return false;
            }
            } else if (args.length >= 1) {
                Player player2 = Bukkit.getPlayer(args[0].toString());
                if (player2 != null) {
                    if (sender.hasPermission(othersFeed)) {
                        player2.setExhaustion(0);
                        player2.setFoodLevel(20);
                        feed.feeder(main, sender, player2);
                        feed.feedee(main, sender, player2);
                        return true;
                    } else {
                        error.noPerm(main, sender, othersFeed);
                        return false;
                    }
                } else {
                    error.playerOffline(main, sender, args[0].toString());
                    return false;
                }
            }
        } else {
            if (args.length < 1) {
                feed.consoleError(main, sender);
                return false;
            } else if (args.length >= 1) {
                Player player2 = Bukkit.getPlayer(args[0].toString());
                if (player2 != null) {
                    player2.setExhaustion(0);
                    player2.setFoodLevel(20);
                    feed.feeder(main, sender, player2);
                    feed.feedee(main, sender, player2);
                    return true;
                } else {
                    error.playerOffline(main, sender, args[0].toString());
                    return false;
                }
            }
        }
        return false;
    }
}
