package us.vindere.foundation.placeholders;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Tools;

public class phHeal {
    public void usage(Foundation main, CommandSender sender){
        String usage = main.getConfig().getString("messages.command.heal.usage");
        sender.sendMessage(Tools.tranColor(usage));
    }
    public void consoleError(Foundation main, CommandSender sender){
        String consoleError = main.getConfig().getString("messages.command.heal.console_error");
        sender.sendMessage(Tools.tranColor(consoleError));
    }
    public void healer (Foundation main, CommandSender sender, Player player) {
        String healer_message = main.getConfig().getString("messages.command.heal.healer_message");
        healer_message = healer_message.replaceAll("#feedee#", player.getName());
        sender.sendMessage(Tools.tranColor(healer_message));
    }
    public void healee (Foundation main, CommandSender sender, Player player) {
        String healee_message = main.getConfig().getString("messages.command.heal.healee_message");
        healee_message = healee_message.replaceAll("#feeder#", sender.getName());
        player.sendMessage(Tools.tranColor(healee_message));
    }
    public void healed (Foundation main, CommandSender sender) {
        String healed_message = main.getConfig().getString("messages.command.heal.healed_message");
        sender.sendMessage(Tools.tranColor(healed_message));
    }
}
