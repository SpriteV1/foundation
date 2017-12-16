package us.vindere.foundation.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.vindere.foundation.Foundation;

import org.bukkit.ChatColor;

public class Tools {
    // Semds the specified message to the console with a prefix.
    public static void sendConsole(String message) {
        Foundation.plugin.getServer().getConsoleSender().sendMessage("[Foundation] " + message);
    }

    // Sends the specified message to the console without a prefix.
    public static void sendNPConsole(String message) {
        Foundation.plugin.getServer().getConsoleSender().sendMessage(message);
    }

    // Converts the ampersands in the specified message into valid color codes.
    public static String tranColor(String message) {
        String colorMsg = ChatColor.translateAlternateColorCodes('&', message);
        return colorMsg;
    }

    public static boolean isPlayer(CommandSender sender){
        if(sender instanceof Player){
            return true;
        } else {
            return false;
        }
    }
    public static Player getPlayer(CommandSender sender){
        if (isPlayer(sender) == true){
            Player player = ((Player) sender).getPlayer();
            return player;
        } else {
            return null;
        }
    }
}
