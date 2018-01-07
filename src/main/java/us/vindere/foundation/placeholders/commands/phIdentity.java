package us.vindere.foundation.placeholders.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Tools;

public class phIdentity {
    public void usage(Foundation main, CommandSender sender) {
        String usage = main.getConfig().getString("messages.command.identity.usage");
        sender.sendMessage(Tools.tranColor(usage));
    }
    public void regularFormat(Foundation main, CommandSender sender, Player player) {
        for (String format : main.getConfig().getStringList("messages.command.identity.regular_format")) {
            format = format.replaceAll("#player#", player.getName());
            if (player.getName() == player.getDisplayName()) {
                format = format.replaceAll("#displayname#", "N/A");
            } else {
                format = format.replaceAll("#displayname#", player.getDisplayName());
            }
            sender.sendMessage(Tools.tranColor(format));
        }
    }
    public void privilegedFormat(Foundation main, CommandSender sender, Player player) {
        for (String format : main.getConfig().getStringList("messages.command.identity.privileged_format")) {
            format = format.replaceAll("#player#", player.getName());
            if (player.getName() == player.getDisplayName()) {
                format = format.replaceAll("#displayname#", "N/A");
            } else {
                format = format.replaceAll("#displayname#", player.getDisplayName());
            }
            sender.sendMessage(Tools.tranColor(format));
        }
    }
}
