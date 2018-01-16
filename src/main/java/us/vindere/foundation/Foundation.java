package us.vindere.foundation;

import us.vindere.foundation.commands.admin.VanishCommand;
import us.vindere.foundation.commands.core.IdentityCommand;
import us.vindere.foundation.commands.core.NicknameCommand;
import us.vindere.foundation.commands.core.UptimeCommand;
import us.vindere.foundation.commands.game.FeedCommand;
import us.vindere.foundation.commands.game.GamemodeCommand;
import us.vindere.foundation.commands.game.HealCommand;
import us.vindere.foundation.events.PlayerJoin;
import us.vindere.foundation.events.PlayerQuit;
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

        this.getCommand("feed").setExecutor(new FeedCommand(plugin));
        this.getCommand("gamemode").setExecutor(new GamemodeCommand(plugin));
        this.getCommand("heal").setExecutor(new HealCommand(plugin));
        this.getCommand("identity").setExecutor(new IdentityCommand(plugin));
        this.getCommand("nickname").setExecutor(new NicknameCommand(plugin));
        this.getCommand("uptime").setExecutor(new UptimeCommand(plugin));
        this.getCommand("vanish").setExecutor(new VanishCommand(plugin));
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
