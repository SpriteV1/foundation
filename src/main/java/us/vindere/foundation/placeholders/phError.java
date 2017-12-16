package us.vindere.foundation.placeholders;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Tools;

public class phError {
    public void noPerm (Foundation main, CommandSender sender, Permission perm) {
        String noPerm = main.getConfig().getString("messages.error.no_perms");
        noPerm = noPerm.replaceAll("#permission#", perm.getName());
        sender.sendMessage(Tools.tranColor(noPerm));
    }
    public void playerOffline (Foundation main, CommandSender sender, String player){
        String playerOffline = main.getConfig().getString("messages.error.player_offline");
        playerOffline = playerOffline.replaceAll("#player#", player);
        sender.sendMessage(Tools.tranColor(playerOffline));
    }
    public void playerMissing (Foundation main, CommandSender sender){
        String playerMissing = main.getConfig().getString("messages.error.player_missing");
        sender.sendMessage(Tools.tranColor(playerMissing));
    }
}
