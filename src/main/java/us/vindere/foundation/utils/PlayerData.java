package us.vindere.foundation.utils;

public class PlayerData {

    private String uuid;
    private String user;
    private String displayname;
    private int balance;
    private String rank;

    public PlayerData(String uuid, String user, String displayname, int balance){
        this.uuid = uuid;
        this.user = user;
        this.displayname = displayname;
        this.balance = balance;
        this.rank = rank;
    }

    // Getter and Setter for uuid
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    // Getter and Setter for user
    public String getUser() {
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }

    // Getter and Setter for displayname
    public String getDisplayname() {
        return displayname;
    }
    public void setDisplayname(String displayname){
        this.displayname = displayname;
    }

    // Getter and Setter for balance
    public int getBalance(){
        return balance;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }
}
