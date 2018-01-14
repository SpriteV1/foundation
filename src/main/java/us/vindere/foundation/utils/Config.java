package us.vindere.foundation.utils;

import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import us.vindere.foundation.Foundation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import us.vindere.foundation.commands.*;
import us.vindere.foundation.events.PlayerJoin;
import us.vindere.foundation.events.PlayerQuit;
import us.vindere.foundation.placeholders.commands.phIdentity;

import java.io.File;
import java.util.UUID;

public class Config {
    public static MongoCollection<Document> collection;

    // Get an instance of Foundation.java.
    Foundation main;
    public Config(Foundation instance){
        main = instance;
    }
    NicknameCommand nick;
    public Config(NicknameCommand instance){
        nick = instance;
    }
    GamemodeCommand gm;
    public Config(GamemodeCommand instance){
        gm = instance;
    }
    HealCommand heal;
    public Config(HealCommand instance){ heal = instance; }
    FeedCommand feed;
    public Config(FeedCommand instance){ feed = instance; }
    SpawnCommand spawn;
    public Config(SpawnCommand instance){ spawn = instance; }
    IdentityCommand identity;
    public Config(IdentityCommand instance){ identity = instance; }
    PlayerJoin joinEvent;
    public Config(PlayerJoin instance){
        joinEvent = instance;
    }
    PlayerQuit quitEvent;
    public Config(PlayerQuit instance){
        quitEvent = instance;
    }
    phIdentity identityPlaceholder;
    public Config(phIdentity instance){ identityPlaceholder = instance; }

    public void connect() {
        String mongodb_uri = main.getConfig().getString("mongodb.uri");
        String mongodb_db = main.getConfig().getString("mongodb.database");
        String mongodb_playercollection = main.getConfig().getString("mongodb.player_data_collection");

        if (mongodb_uri.equals("")) {
            Tools.sendConsole("Plugin disabled. You must provide MongoDB information in order to start Foundation.");
            main.getServer().getPluginManager().disablePlugin(main);
        } else if(mongodb_db.equals("")) {
            Tools.sendConsole("Plugin disabled. You must provide MongoDB information in order to start Foundation.");
            main.getServer().getPluginManager().disablePlugin(main);
        } else if(mongodb_playercollection.equals("")) {
            Tools.sendConsole("Plugin disabled. You must provide MongoDB information in order to start Foundation.");
            main.getServer().getPluginManager().disablePlugin(main);
        } else {
            // Connects to MongoDB database as stated in config.yml.
            MongoClientURI clientURI = new MongoClientURI(mongodb_uri);
            MongoClient mongoClient = new MongoClient(clientURI);
            MongoDatabase mongoDatabase = mongoClient.getDatabase(mongodb_db);
            collection = mongoDatabase.getCollection(mongodb_playercollection);
            Tools.sendConsole("Connected to MongoDB database.");
        }
    }

    // Check and establish the configuration file.
    public void establishConfig(){
        try {
            // Checks to see if plugin data folder exists.
            if(!main.getDataFolder().exists()) {
                // If the folder does not exist, create one.
                main.getDataFolder().mkdirs();
            }
            File config = new File(main.getDataFolder(), "config.yml");
            if(!config.exists()){
                main.saveDefaultConfig();
                Tools.sendConsole("Config generated and plugin disabled. To enable, please edit your config.yml to add MongoDB information.");
                main.getServer().getPluginManager().disablePlugin(main);
            }else{
                Tools.sendConsole("Configuration found.");
                connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Document getDocument(Player player){
        Document playerRecord = new Document("uuid", player.getUniqueId().toString());
        Document playerFound = (Document) Config.collection.find(playerRecord).first();
        return playerFound;
    }

    public Document getOfflineDocument(String player) {
        Document playerFound = (Document) collection.find(new Document ("user", player)).first();
        return playerFound;
    }
}
