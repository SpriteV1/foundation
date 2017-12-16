package us.vindere.foundation.placeholders;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Tools;

public class phFeed {
    public void usage(Foundation main, CommandSender sender){
        String usage = main.getConfig().getString("messages.command.feed.usage");
        sender.sendMessage(Tools.tranColor(usage));
    }
    public void consoleError(Foundation main, CommandSender sender){
        String consoleError = main.getConfig().getString("messages.command.feed.console_error");
        sender.sendMessage(Tools.tranColor(consoleError));
    }
    public void feeder (Foundation main, CommandSender sender, Player player) {
        String feeder_message = main.getConfig().getString("messages.command.feed.feeder_message");
        feeder_message = feeder_message.replaceAll("#feedee#", player.getName());
        sender.sendMessage(Tools.tranColor(feeder_message));
    }
    public void feedee (Foundation main, CommandSender sender, Player player) {
        String feedee_message = main.getConfig().getString("messages.command.feed.feedee_message");
        feedee_message = feedee_message.replaceAll("#feeder#", sender.getName());
        player.sendMessage(Tools.tranColor(feedee_message));
    }
    public void feeded (Foundation main, CommandSender sender) {
        String feeded_message = main.getConfig().getString("messages.command.feed.feeded_message");
        sender.sendMessage(Tools.tranColor(feeded_message));
    }
}
