package us.vindere.foundation.placeholders.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Tools;

public class phGamemode {
    public void usage(Foundation main, CommandSender sender){
        String usage = main.getConfig().getString("messages.command.gamemode.usage");
        sender.sendMessage(Tools.tranColor(usage));
    }
    public void consoleError(Foundation main, CommandSender sender){
        String consoleError = main.getConfig().getString("messages.command.gamemode.console_error");
        sender.sendMessage(Tools.tranColor(consoleError));
    }
    public void invalidGamemode(Foundation main, CommandSender sender, String gamemode) {
        Boolean lowercase = main.getConfig().getBoolean("gamemode.use_lowercase_names");
        String invalidGamemode = main.getConfig().getString("messages.command.gamemode.invalid_gamemode");
        if (lowercase == true) {
            gamemode = gamemode.toLowerCase();
        }
        invalidGamemode = invalidGamemode.replaceAll("#gamemode#", gamemode);
        sender.sendMessage(Tools.tranColor(invalidGamemode));
    }
    public void gamemoder (Foundation main, CommandSender sender, Player player, String gamemode) {
        Boolean lowercase = main.getConfig().getBoolean("gamemode.use_lowercase_names");
        String gamemoder_message = main.getConfig().getString("messages.command.gamemode.gamemoder_message");
        if (lowercase == true) {
            gamemode = gamemode.toLowerCase();
        }
        gamemoder_message = gamemoder_message.replaceAll("#gamemodee#", player.getName());
        gamemoder_message = gamemoder_message.replaceAll("#gamemode#", gamemode);
        sender.sendMessage(Tools.tranColor(gamemoder_message));
    }
    public void gamemodee (Foundation main, CommandSender sender, Player player, String gamemode) {
        Boolean lowercase = main.getConfig().getBoolean("gamemode.use_lowercase_names");
        String gamemodee_message = main.getConfig().getString("messages.command.gamemode.gamemodee_message");
        if (lowercase == true) {
            gamemode = gamemode.toLowerCase();
        }
        gamemodee_message = gamemodee_message.replaceAll("#gamemoder#", sender.getName());
        gamemodee_message = gamemodee_message.replaceAll("#gamemode#", gamemode);
        player.sendMessage(Tools.tranColor(gamemodee_message));
    }
    public void gamemoded (Foundation main, CommandSender sender, String gamemode) {
        Boolean lowercase = main.getConfig().getBoolean("gamemode.use_lowercase_names");
        String gamemoded_message = main.getConfig().getString("messages.command.gamemode.gamemoded_message");
        if (lowercase == true) {
            gamemode = gamemode.toLowerCase();
        }
        gamemoded_message = gamemoded_message.replaceAll("#gamemode#", gamemode);
        sender.sendMessage(Tools.tranColor(gamemoded_message));
    }
}
