package us.vindere.foundation.commands;

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
import us.vindere.foundation.placeholders.phNickname;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.Tools;

public class NicknameCommand implements CommandExecutor {
    Foundation main;
    public NicknameCommand(Foundation instance){ main = instance; }
    phError error = new phError();
    phNickname nickname = new phNickname();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Config config = new Config(this);
        Permission nickSelf = new Permission("f.nickname.self");
        Permission nickOthers = new Permission("f.nickname.others");
        Player player = Tools.getPlayer(sender);
        Bson playerDoc;

        if (player != null) {
            playerDoc = config.getDocument(player);
        }

        // Player sender only
        if (player != null && args.length == 1) {
            if (sender.hasPermission(nickSelf)) {
                changeNick(player, sender, args[0].toString());
                return true;
            } else {
                error.noPerm(main, sender, nickSelf);
                return false;
            }
        } else if (player != null && args.length >= 2) {
            Player player2 = Bukkit.getPlayer(args[0].toString());
            if (player2 != null) {
                if (player.getName().equals(player2.getName())) {
                    if (sender.hasPermission(nickSelf)) {
                        changeNick(player, sender, args[1].toString());
                        return true;
                    } else {
                        error.noPerm(main, sender, nickSelf);
                        return false;
                    }
                } else {
                    if (sender.hasPermission(nickOthers)) {
                        changeNick(player2, sender, args[1].toString());
                        return true;
                    } else {
                        error.noPerm(main, sender, nickOthers);
                        return false;
                    }
                }
            } else {
                error.playerOffline(main, sender, args[0].toString());
                return false;
            }
        } else if (player != null && args.length < 1) {
            nickname.usage(main, sender);
            return true;

            //Console sender only
        } else if (player == null && args.length < 1){
            nickname.usage(main, sender);
            return true;
        } else if (player == null && args.length == 1) {
            nickname.consoleError(main, sender);
            return false;
        } else if (player == null && args.length >= 2) {
            Player player2 = Bukkit.getPlayer(args[0].toString());
            if (player2 != null) {
                changeNick(player2, sender, args[1].toString());
                return true;
            } else {
                error.playerOffline(main, sender, args[0].toString());
                return false;
            }
        }
        return false;
    }

    private void changeNick(Player player, CommandSender sender, String nick){
        Config config = new Config(this);

        if (Tools.isPlayer(sender) == true) {
            Document playerDoc = config.getDocument(player);
            Bson updateNick = new Document("displayname", nick);
            Bson updateOperation = new Document("$set", updateNick);
            Config.collection.updateOne(playerDoc, updateOperation);
            if (sender.getName().equals(player.getName())) {
                nickname.nicknamed(main, sender, nick);
                player.setDisplayName(Tools.tranColor(nick) + Tools.tranColor("&r"));
            } else {
                nickname.nicknamer(main, sender,player,nick);
                nickname.nicknamee(main, sender, player, nick);
                player.setDisplayName(Tools.tranColor(nick) + Tools.tranColor("&r"));
            }
        } else {
            Document playerDoc = config.getDocument(player);
            Bson updateNick = new Document("displayname", nick);
            Bson updateOperation = new Document("$set", updateNick);
            Config.collection.updateOne(playerDoc, updateOperation);
            nickname.nicknamer(main, sender,player,nick);
            nickname.nicknamee(main, sender, player, nick);
            player.setDisplayName(Tools.tranColor(nick) + Tools.tranColor("&r"));
        }
    }
}
