package us.vindere.foundation.placeholders.commands.core;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.PlayerData;
import us.vindere.foundation.utils.Time;
import us.vindere.foundation.utils.Tools;
import java.util.Date;

public class phIdentity {
    public void usage(Foundation main, CommandSender sender) {
        String usage = main.getConfig().getString("messages.command.identity.usage");
        sender.sendMessage(Tools.tranColor(usage));
    }
    public void consoleError(Foundation main, CommandSender sender) {
        String consoleError = main.getConfig().getString("messages.command.identity.console_error");
        sender.sendMessage(Tools.tranColor(consoleError));
    }

    public void regularFormat(Foundation main, CommandSender sender, String player) {
        Config config = new Config(this);
        PlayerData data = new PlayerData();
        String uuid = (String) data.fromString(player)[0];
        String user = (String) data.fromString(player)[1];
        String displayname = (String) data.fromString(player)[2];
        Integer balance = (Integer) data.fromString(player)[3];
        String ip = (String) data.fromString(player)[4];
        String first_login = Tools.reformatDate(main.getConfig().getString("identity.date_format"), "MM/dd/yyyy HH:mm:ss Z", (String) data.fromString(player)[5]);
        String last_login = Tools.reformatDate(main.getConfig().getString("identity.date_format"), "MM/dd/yyyy HH:mm:ss Z", (String) data.fromString(player)[6]);

        Date d1 = Tools.parseDate("short", Tools.getDate("short"));
        Date d2 = Tools.parseDate("short", (String) data.fromString(player)[6]);
        long dl1 = d1.getTime();
        long dl2 = d2.getTime();
        long seconds = ((dl1 - dl2) / 1000);

        String color;
        String status;
        if(Bukkit.getPlayer(player) != null) {
            color = main.getConfig().getString("messages.command.identity.color.online");
            status = "Online";
        } else {
            color = main.getConfig().getString("messages.command.identity.color.offline");
            status = "Offline";
        }

        for (String format : main.getConfig().getStringList("messages.command.identity.regular_format")) {
            format = format.replaceAll("#player#", user);
            if (user == displayname) {
                format = format.replaceAll("#displayname#", "N/A");
            } else {
                format = format.replaceAll("#displayname#", displayname);
            }

            format = format.replaceAll("#status#", color + status);
            format = format.replaceAll("#ip#", ip);
            format = format.replaceAll("#first_date#", first_login);
            format = format.replaceAll("#last_date#", last_login);
            format = format.replaceAll("#time_since#", Time.getLongTime(seconds));
            sender.sendMessage(Tools.tranColor(format));
        }
    }

    public void privilegedFormat(Foundation main, CommandSender sender, String player) {
        Config config = new Config(this);
        PlayerData data = new PlayerData();
        String uuid = (String) data.fromString(player)[0];
        String user = (String) data.fromString(player)[1];
        String displayname = (String) data.fromString(player)[2];
        Integer balance = (Integer) data.fromString(player)[3];
        String ip = (String) data.fromString(player)[4];
        String first_login = Tools.reformatDate(main.getConfig().getString("identity.date_format"), "MM/dd/yyyy HH:mm:ss Z", (String) data.fromString(player)[5]);
        String last_login = Tools.reformatDate(main.getConfig().getString("identity.date_format"), "MM/dd/yyyy HH:mm:ss Z", (String) data.fromString(player)[6]);

        Date d1 = Tools.parseDate("short", Tools.getDate("short"));
        Date d2 = Tools.parseDate("short", (String) data.fromString(player)[6]);
        long dl1 = d1.getTime();
        long dl2 = d2.getTime();
        long seconds = ((dl1 - dl2) / 1000);

        String color;
        String status;
        if(Bukkit.getPlayer(player) != null) {
            color = main.getConfig().getString("messages.command.identity.color.online");
            status = "Online";
        } else {
            color = main.getConfig().getString("messages.command.identity.color.offline");
            status = "Offline";
        }

        for (String format : main.getConfig().getStringList("messages.command.identity.privileged_format")) {
            format = format.replaceAll("#player#", user);
            if (user == displayname) {
                format = format.replaceAll("#displayname#", "N/A");
            } else {
                format = format.replaceAll("#displayname#", displayname);
            }

            format = format.replaceAll("#status#", color + status);
            format = format.replaceAll("#ip#", ip);
            format = format.replaceAll("#first_date#", first_login);
            format = format.replaceAll("#last_date#", last_login);
            format = format.replaceAll("#time_since_last_login#", Time.getLongTime(seconds));

            sender.sendMessage(Tools.tranColor(format));
        }
    }
}
