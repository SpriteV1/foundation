package us.vindere.foundation.utils;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    private String uuid;
    private String user;
    private String displayname;
    private int balance;
    private String ip;
    private String first_login;
    private String last_login;

    public Object[] fromUUID(String uuid){
        Document playerRecord = new Document("uuid", uuid);
        Document playerFound = (Document) Config.collection.find(playerRecord).first();

        this.uuid = uuid;
        this.user = playerFound.getString("user");
        this.displayname = playerFound.getString("displayname");
        this.balance = playerFound.getInteger("balance");
        this.ip = playerFound.getString("ip");
        this.first_login = playerFound.getString("first_login");
        this.last_login = playerFound.getString("last_login");

        Object[] array = new Object[]{
                this.uuid,
                this.user,
                this.displayname,
                this.balance,
                this.ip,
                this.first_login,
                this.last_login
        };
        return array;
    }

    public Object[] fromString(String string){
        Document playerRecord = new Document("user", string);
        Document playerFound = (Document) Config.collection.find(playerRecord).first();

        if (playerFound == null){
            return null;
        }

        this.uuid = playerFound.getString("uuid");
        this.user = string;
        this.displayname = playerFound.getString("displayname");
        this.balance = playerFound.getInteger("balance");
        this.ip = playerFound.getString("ip");
        this.first_login = playerFound.getString("first_login");
        this.last_login = playerFound.getString("last_login");

        Object[] array = new Object[]{
                this.uuid,
                this.user,
                this.displayname,
                this.balance,
                this.ip,
                this.first_login,
                this.last_login
        };
        return array;
    }

    public void setString(UUID uuid, String player, String field, String data){
        Document playerRecord;
        Document playerFound;
        Player playerObject = Bukkit.getPlayer(uuid);

        if (playerObject != null){
            playerRecord = new Document("uuid", uuid);
            playerFound = (Document) Config.collection.find(playerRecord).first();
        } else {
            playerRecord = new Document("user", player);
            playerFound = (Document) Config.collection.find(playerRecord).first();
        }

        Bson updateField = new Document(field, data);
        Bson updateOperation = new Document("$set", updateField);
        Config.collection.updateOne(playerFound, updateOperation);
    }

    public void setInt(String uuid, String player, String field, int data){
        Document playerRecord;
        Document playerFound;

        if (!uuid.equals(null)){
            playerRecord = new Document("uuid", uuid);
            playerFound = (Document) Config.collection.find(playerRecord).first();
        } else {
            playerRecord = new Document("user", player);
            playerFound = (Document) Config.collection.find(playerRecord).first();
        }

        Bson updateField = new Document(field, data);
        Bson updateOperation = new Document("$set", updateField);
        Config.collection.updateOne(playerFound, updateOperation);
    }
}
