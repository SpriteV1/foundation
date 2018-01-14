package us.vindere.foundation;

import us.vindere.foundation.commands.*;
import us.vindere.foundation.events.PlayerJoin;
import us.vindere.foundation.events.PlayerQuit;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.placeholders.commands.phGamemode;
import us.vindere.foundation.placeholders.commands.phNickname;
import us.vindere.foundation.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Foundation extends JavaPlugin {

    public static Foundation plugin;

    @Override
    public void onEnable(){
        // Initializes plugin.
        plugin = this;

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
        this.getCommand("identity").setExecutor(new IdentityCommand(plugin));
        this.getCommand("nickname").setExecutor(new NicknameCommand(plugin));
        this.getCommand("uptime").setExecutor(new UptimeCommand(plugin));
    }
    @Override
    public void onDisable(){
        // Sets instance to null on disable.
        plugin = null;
    }

    private void setEvents(){
        PlayerJoin playerJoin = new PlayerJoin(plugin);
        PlayerQuit playerQuit = new PlayerQuit(plugin);
        Bukkit.getPluginManager().registerEvents(playerJoin, plugin);
        Bukkit.getPluginManager().registerEvents(playerQuit, plugin);
    }
}
