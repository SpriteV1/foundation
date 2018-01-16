package us.vindere.foundation.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.utils.Config;

public class PlayerQuit implements Listener {

    Foundation main;
    public PlayerQuit(Foundation instance){
        main = instance;
    }
    Config config = new Config(this);

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent event){
        Boolean messages_enable = main.getConfig().getBoolean("playerjoin.join_message_enable");
        String message_type = main.getConfig().getString("playerjoin.join_message_type");
        String format_playername = main.getConfig().getString("playerjoin.message_format.playername");
        String format_displayname = main.getConfig().getString("playerjoin.message_format.displayname");

        if(messages_enable == true){

        } else {
            event.setQuitMessage(null);
        }
    }
}
