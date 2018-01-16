package us.vindere.foundation.commands.game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.utils.Config;

public class SpawnCommand implements CommandExecutor {
    private Foundation main;
    public SpawnCommand(Foundation instance) { main = instance; }
    private phError error = new phError();

    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        Config config = new Config(this);
        Permission _teleport = new Permission("f.spawn");
        Permission _set = new Permission("f.spawn.set");

        if (sender instanceof Player) {
            if (args.length > 0) {

            } else {
                if (args[0] == "set") {
                    if (sender.hasPermission(_set)){

                    }
                    error.noPerm(main, sender, _set);
                    return false;
                }
                return false;
            }
        }
        // TODO console error
        return false;
    }
}
