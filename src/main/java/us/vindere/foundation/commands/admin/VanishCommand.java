package us.vindere.foundation.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.placeholders.commands.admin.phVanish;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.Tools;

public class VanishCommand implements CommandExecutor {
    Foundation main;
    public VanishCommand(Foundation instance) { main = instance; }
    phError error = new phError();
    phVanish vanish = new phVanish();

    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        Config config = new Config(this);
        Player player = Tools.getPlayer(sender);
        Permission selfVanish = new Permission("f.vanish.self");
        Permission othersVanish = new Permission("f.vanish.others");

        if (player != null) {
            if (args.length < 1) {
                if (sender.hasPermission(selfVanish)) {
                    for (Player online : Bukkit.getOnlinePlayers()){
                        if (online.canSee(player)){
                            online.hidePlayer(player);
                        } else {
                            online.showPlayer(player);
                        }
                    }
                    return true;
                } else {
                    error.noPerm(main, sender, selfVanish);
                    return false;
                }
            } else if (args.length >= 1) {
                Player player2 = Bukkit.getPlayer(args[0]);
                if (player == player2){
                    if (sender.hasPermission(selfVanish)) {
                        // TODO vanish self
                        return true;
                    } else {
                        error.noPerm(main, sender, selfVanish);
                        return false;
                    }
                }
                if (player2 != null) {
                    if (sender.hasPermission(othersVanish)) {
                        // TODO vanish others
                        return true;
                    } else {
                        error.noPerm(main, sender, othersVanish);
                        return false;
                    }
                } else {
                    error.playerOffline(main, sender, args[0]);
                    return false;
                }
            }
        } else {
            if (args.length < 1) {
                //vanish.consoleError(main, sender);
                return false;
            } else if (args.length >= 1) {
                Player player2 = Bukkit.getPlayer(args[0]);
                if (player2 != null) {

                    return true;
                } else {
                    error.playerOffline(main, sender, args[0].toString());
                    return false;
                }
            }
        }
        return false;
    }
}
