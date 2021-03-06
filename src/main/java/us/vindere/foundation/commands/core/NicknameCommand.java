package us.vindere.foundation.commands.core;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.placeholders.commands.core.phNickname;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.PlayerData;
import us.vindere.foundation.utils.Tools;

public class NicknameCommand implements CommandExecutor {
    private Foundation main;
    public NicknameCommand(Foundation instance){ main = instance; }
    private phError error = new phError();
    private phNickname nickname = new phNickname();
    private PlayerData data = new PlayerData();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Permission nickSelf = new Permission("f.nickname.self");
        Permission nickOthers = new Permission("f.nickname.others");
        Player player = Tools.getPlayer(sender);

        // Player sender only
        if (player != null && args.length == 1) {
            if (sender.hasPermission(nickSelf)) {
                changeNick(player.getName(), sender, args[0]);
                return true;
            } else {
                error.noPerm(main, sender, nickSelf);
                return false;
            }
        } else if (player != null && args.length >= 2) {
            Player player2 = Bukkit.getPlayer(args[0]);
            if (player2 != null) {
                if (player.getName().equals(player2.getName())) {
                    if (sender.hasPermission(nickSelf)) {
                        changeNick(player.getName(), sender, args[1]);
                        return true;
                    } else {
                        error.noPerm(main, sender, nickSelf);
                        return false;
                    }
                } else {
                    if (sender.hasPermission(nickOthers)) {
                        changeNick(args[0], sender, args[1]);
                        return true;
                    } else {
                        error.noPerm(main, sender, nickOthers);
                        return false;
                    }
                }
            } else {
                changeNick(args[0], sender, args[1]);
                return true;
            }
        } else if (player != null) {
            nickname.usage(main, sender);
            return true;

            //Console sender only
        } else if (args.length < 1){
            nickname.usage(main, sender);
            return true;
        } else if (args.length == 1) {
            nickname.consoleError(main, sender);
            return false;
        } else {
            changeNick(args[0], sender, args[1]);
            return true;
        }
    }

    private void changeNick(String player, CommandSender sender, String nick) {
        Config config = new Config(this);
        Player playerObject = Bukkit.getPlayer(player);

        if (playerObject != null) {
            Document playerDoc = config.getDocument(playerObject);
            Bson updateNick = new Document("displayname", nick);
            Bson updateOperation = new Document("$set", updateNick);
            Config.collection.updateOne(playerDoc, updateOperation);
            if (sender.getName().equals(player)) {
                data.setString(playerObject.getUniqueId(), null, "displayname", nick);
                nickname.nicknamed(main, sender, nick);
                playerObject.setDisplayName(Tools.tranColor(nick) + Tools.tranColor("&r"));
            } else {
                data.setString(playerObject.getUniqueId(), null, "displayname", nick);
                nickname.nicknamer(main, sender, player, nick);
                nickname.nicknamee(main, sender, playerObject, nick);
                playerObject.setDisplayName(Tools.tranColor(nick) + Tools.tranColor("&r"));
            }
        } else {
            Document playerDoc = config.getOfflineDocument(player);
            if (playerDoc != null) {
                data.setString(null, player, "displayname", nick);
                nickname.nicknamer(main, sender, player, nick);
            } else {
                error.playerMissing(main, sender, player);
            }
        }
    }
}
