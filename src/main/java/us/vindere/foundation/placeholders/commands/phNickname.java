package us.vindere.foundation.placeholders.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Tools;

public class phNickname {
    public void usage(Foundation main, CommandSender sender){
        String usage = main.getConfig().getString("messages.command.nickname.usage");
        sender.sendMessage(Tools.tranColor(usage));
    }
    public void consoleError(Foundation main, CommandSender sender){
        String consoleError = main.getConfig().getString("messages.command.nickname.console_error");
        sender.sendMessage(Tools.tranColor(consoleError));
    }
    public void nicknamer (Foundation main, CommandSender sender, String player, String nickname) {
        String nicknamer_message = main.getConfig().getString("messages.command.nickname.nicknamer_message");
        nicknamer_message = nicknamer_message.replaceAll("#nicknamee#", player);
        nicknamer_message = nicknamer_message.replaceAll("#nickname#", nickname);
        sender.sendMessage(Tools.tranColor(nicknamer_message));
    }
    public void nicknamee (Foundation main, CommandSender sender, Player player, String nickname) {
        String nicknamee_message = main.getConfig().getString("messages.command.nickname.nicknamee_message");
        nicknamee_message = nicknamee_message.replaceAll("#nicknamer#", sender.getName());
        nicknamee_message = nicknamee_message.replaceAll("#nickname#", nickname);
        player.sendMessage(Tools.tranColor(nicknamee_message));
    }
    public void nicknamed (Foundation main, CommandSender sender, String nickname) {
        String nicknamed_message = main.getConfig().getString("messages.command.nickname.nicknamed_message");
        nicknamed_message = nicknamed_message.replaceAll("#nickname#", nickname);
        sender.sendMessage(Tools.tranColor(nicknamed_message));
    }
}
