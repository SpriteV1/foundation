package us.vindere.foundation.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.placeholders.commands.phUptime;

public class UptimeCommand implements CommandExecutor{

    Foundation main;
    public UptimeCommand(Foundation instance) { main = instance; }
    phError error = new phError();
    phUptime uptime = new phUptime();

    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        Permission _uptime = new Permission("f.uptime");

        if(sender.hasPermission(_uptime)){
            uptime.time(main, sender);
            return true;
        }
        error.noPerm(main, sender, _uptime);
        return false;
    }
}
