package us.vindere.foundation.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.placeholders.phHeal;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.Tools;

public class HealCommand implements CommandExecutor{
    Foundation main;
    public HealCommand(Foundation instance) { main = instance; }
    phError error = new phError();
    phHeal heal = new phHeal();

    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        Config config = new Config(this);
        Player player = Tools.getPlayer(sender);
        Permission selfHeal = new Permission("f.heal.self");
        Permission othersHeal = new Permission("f.heal.others");

        if (player != null) {
            if (args.length < 1) {
                if (sender.hasPermission(selfHeal)) {
                    player.setHealth(20);
                    heal.healed(main, sender);
                    return true;
                } else {
                    error.noPerm(main, sender, selfHeal);
                    return false;
                }
            } else if (args.length >= 1) {
                Player player2 = Bukkit.getPlayer(args[0].toString());
                if (player2 != null) {
                    if (sender.hasPermission(othersHeal)) {
                        player2.setHealth(20);
                        heal.healer(main, sender, player2);
                        heal.healee(main, sender, player2);
                        return true;
                    } else {
                        error.noPerm(main, sender, othersHeal);
                        return false;
                    }
                } else {
                    error.playerOffline(main, sender, args[0].toString());
                    return false;
                }
            }
        } else {
            if (args.length < 1) {
                heal.consoleError(main, sender);
                return false;
            } else if (args.length >= 1) {
                Player player2 = Bukkit.getPlayer(args[0].toString());
                if (player2 != null) {
                    player2.setHealth(20);
                    heal.healer(main, sender, player2);
                    heal.healee(main, sender, player2);
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
