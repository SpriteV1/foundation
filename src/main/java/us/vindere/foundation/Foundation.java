package us.vindere.foundation;

import us.vindere.foundation.commands.FeedCommand;
import us.vindere.foundation.commands.GamemodeCommand;
import us.vindere.foundation.commands.HealCommand;
import us.vindere.foundation.commands.NicknameCommand;
import us.vindere.foundation.events.PlayerJoin;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.placeholders.phGamemode;
import us.vindere.foundation.placeholders.phNickname;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Foundation extends JavaPlugin {

    public static Foundation plugin;
    public HashMap<UUID, PlayerData> playerDataHash;

    @Override
    public void onEnable() {
        // Initializes plugin.
        plugin = this;

        playerDataHash = new HashMap();
        Config config = new Config(plugin);

        // Connects to MongoDB database as stated in config.yml.
        config.establishConfig();

        setEvents();

        phError phError = new phError();
        phNickname phNickname = new phNickname();
        phGamemode phGamemode = new phGamemode();

        this.getCommand("feed").setExecutor(new FeedCommand(plugin));
        this.getCommand("gamemode").setExecutor(new GamemodeCommand(plugin));
        this.getCommand("heal").setExecutor(new HealCommand(plugin));
        this.getCommand("nickname").setExecutor(new NicknameCommand(plugin));
    }
    @Override
    public void onDisable() {
        // Sets instance to null on disable.
        plugin = null;
    }

    private void setEvents(){
        PlayerJoin playerjoin = new PlayerJoin(plugin);
        Bukkit.getPluginManager().registerEvents(playerjoin, plugin);
    }
}
