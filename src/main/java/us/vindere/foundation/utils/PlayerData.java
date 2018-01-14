package us.vindere.foundation.utils;

import org.bson.Document;
import org.bukkit.Bukkit;

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

}
