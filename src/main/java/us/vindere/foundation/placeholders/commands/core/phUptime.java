package us.vindere.foundation.placeholders.commands.core;

import org.bukkit.command.CommandSender;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Time;
import us.vindere.foundation.utils.Tools;

public class phUptime {
    public void time(Foundation main, CommandSender sender){
        String format = main.getConfig().getString("messages.command.uptime.format");
        format = format.replaceAll("#uptime#", Time.getUptime());
        sender.sendMessage(Tools.tranColor(format));
    }
}
