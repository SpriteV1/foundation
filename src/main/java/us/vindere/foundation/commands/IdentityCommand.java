package us.vindere.foundation.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.placeholders.commands.phIdentity;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.Tools;

public class IdentityCommand implements CommandExecutor {
    private Foundation main;
    public IdentityCommand(Foundation instance) { main = instance; }
    private phError error = new phError();
    private phIdentity identity = new phIdentity();

    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        Config config = new Config(this);
        Player player = Tools.getPlayer(sender);
        Permission _regularView = new Permission("f.identity.regular");
        Permission _privilegedView = new Permission("f.identity.privileged");

        if (player != null) {
            if (args.length < 1) {
                if (sender.hasPermission(_regularView) || sender.hasPermission(_privilegedView)) {
                    identity.privilegedFormat(main, sender, player.getName());
                    return true;
                } else {
                    error.noPerm(main, sender, _regularView);
                    return false;
                }
            } else {
                if (sender.hasPermission(_privilegedView)) {
                    identity.privilegedFormat(main, sender, args[0]);
                    return true;
                } else if (sender.hasPermission(_regularView)) {
                    identity.regularFormat(main, sender, args[0]);
                    return true;
                } else {
                    error.noPerm(main, sender, _regularView);
                    return false;
                }
            }
        } else {
            if (args.length < 1) {
                identity.consoleError(main, sender);
                return false;
            } else if (args.length >= 1) {
                identity.privilegedFormat(main, sender, args[0]);
                return true;
            }
        }
        return false;
    }
}
