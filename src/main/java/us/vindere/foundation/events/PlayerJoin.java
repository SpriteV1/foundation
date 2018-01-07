package us.vindere.foundation.events;

import org.bukkit.Sound;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.PlayerData;
import us.vindere.foundation.utils.Tools;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bson.Document;

public class PlayerJoin implements Listener{

    private Foundation main;
    public PlayerJoin(Foundation instance){
        main = instance;
    }
    private Config config = new Config(this);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Document playerRecord = new Document("uuid", player.getUniqueId().toString());
        Document playerFound = config.getDocument(player);
        Integer starting_balance = main.getConfig().getInt("economy.starting_balance");
        boolean firstJoin;
        long total = Config.collection.count();
        if (playerFound == null){
            playerRecord.append("user", player.getName());
            playerRecord.append("displayname", player.getName());
            playerRecord.append("balance", starting_balance);
            Config.collection.insertOne(playerRecord);
            main.playerDataHash.put(player.getUniqueId(), new PlayerData(player.getUniqueId().toString(), player.getName(), player.getName(), starting_balance));
            firstJoin = true;
        } else {
            String displayname = playerFound.getString("displayname");
            int balance = playerFound.getInteger("balance");
            main.playerDataHash.put(player.getUniqueId(), new PlayerData(player.getUniqueId().toString(), player.getName(), displayname, balance));
            firstJoin = false;
            player.setDisplayName(Tools.tranColor(displayname) + Tools.tranColor("&r"));
        }

        Boolean messages_enable = main.getConfig().getBoolean("playerjoin.join_message_enable");
        String message_type = main.getConfig().getString("playerjoin.join_message_type");
        String format_playername = main.getConfig().getString("playerjoin.message_format.playername");
        String format_displayname = main.getConfig().getString("playerjoin.message_format.displayname");
        String format_firstjoin = main.getConfig().getString("playerjoin.message_format.firstjoin");
        Boolean sounds_enable = main.getConfig().getBoolean("playerjoin.join_sounds_enable");

        if (sounds_enable){
            event.getPlayer().playSound(player.getLocation(), Sound.BLOCK_NOTE_CHIME, 2, 1);
        }

        if(messages_enable && !firstJoin){
            String displayname = playerFound.getString("displayname");
            if(message_type.equals("playername") || displayname.equals(player.getName())){
                format_playername = format_playername.replaceAll("#player#", player.getName());
                event.setJoinMessage(Tools.tranColor(format_playername));
            } else if (message_type.equals("displayname") && !displayname.equals(player.getName())){
                format_displayname = format_displayname.replaceAll("#displayname#", displayname);
                format_displayname = format_displayname.replaceAll("#player#", player.getName());
                event.setJoinMessage(Tools.tranColor(format_displayname));
            } else {
                Tools.sendConsole("Plugin disabled. Invalid join_message_format in congif.yml.");
                main.getServer().getPluginManager().disablePlugin(main);
            }
        } else if (messages_enable) {
            total++;
            format_firstjoin = format_firstjoin.replaceAll("#player#", player.getName());
            format_firstjoin = format_firstjoin.replaceAll("#total#", Long.toString(total));
            event.setJoinMessage(Tools.tranColor(format_firstjoin));
        } else {
            event.setJoinMessage(null);
        }
    }
}
