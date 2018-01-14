package us.vindere.foundation.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.vindere.foundation.Foundation;

import org.bukkit.ChatColor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static boolean isPlayer(CommandSender sender){
        if(sender instanceof Player){
            return true;
        } else {
            return false;
        }
    }
    public static Player getPlayer(CommandSender sender){
        if (isPlayer(sender)){
            Player player = ((Player) sender).getPlayer();
            return player;
        } else {
            return null;
        }
    }

    public static String getDate(String type){
        if (type == "short"){
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
            Date date = new Date();
            String dateString = format.format(date);
            return dateString;
        } else if (type == "full") {
            SimpleDateFormat format = new SimpleDateFormat("EEEEE, MMMMM d, yyyy HH:mm:ss Z");
            Date date = new Date();
            String dateString = format.format(date);
            return dateString;
        }
        return null;
    }

    public static Date parseDate(String type, String dateString){
        if (type == "short"){
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
            Date date;
            try {
                date = format.parse(dateString);
                return date;
            } catch (ParseException e){
                e.printStackTrace();
            }
        } else if (type == "full") {
            SimpleDateFormat format = new SimpleDateFormat("EEEEE, MMMMM d, yyyy HH:mm:ss Z");
            Date date;
            try {
                date = format.parse(dateString);
                return date;
            } catch (ParseException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String reformatDate (String type, String old_format, String old_date) {
        if (type.equals("short")){
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
            SimpleDateFormat formatOld = new SimpleDateFormat(old_format);
            Date date;
            try {
                date = formatOld.parse(old_date);
                return format.format(date);
            } catch (ParseException e){
                e.printStackTrace();
            }
        } else if (type.equals("full")) {
            SimpleDateFormat format = new SimpleDateFormat("EEEEE, MMMMM d, yyyy HH:mm:ss Z");
            SimpleDateFormat formatOld = new SimpleDateFormat(old_format);
            Date date;
            try {
                date = formatOld.parse(old_date);
                return format.format(date);
            } catch (ParseException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
