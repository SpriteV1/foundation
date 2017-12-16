package us.vindere.foundation.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import us.vindere.foundation.Foundation;
import us.vindere.foundation.placeholders.phError;
import us.vindere.foundation.placeholders.phGamemode;
import us.vindere.foundation.utils.Config;
import us.vindere.foundation.utils.Tools;

public class GamemodeCommand implements CommandExecutor {
    Foundation main;
    public GamemodeCommand(Foundation instance) { main = instance; }
    phError error = new phError();
    phGamemode gamemode = new phGamemode();

    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        Config config = new Config(this);
        Player player = Tools.getPlayer(sender);
        Permission selfSurvival = new Permission("f.gamemode.self.survival");
        Permission selfCreative = new Permission("f.gamemode.self.creative");
        Permission selfAdventure = new Permission("f.gamemode.self.adventure");
        Permission selfSpectator = new Permission("f.gamemode.self.spectator");
        Permission othersSurvival = new Permission("f.gamemode.others.survival");
        Permission othersCreative = new Permission("f.gamemode.others.creative");
        Permission othersAdventure = new Permission("f.gamemode.others.adventure");
        Permission othersSpectator = new Permission("f.gamemode.others.spectator");

        if (player != null ){
            if (args.length < 1) {
                gamemode.usage(main, sender);
                return true;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
                    if(sender.hasPermission(selfSurvival)){
                        player.setGameMode(GameMode.SURVIVAL);
                        gamemode.gamemoded(main, sender, "Survival");
                        return true;
                    } else {
                        error.noPerm(main, sender, selfSurvival);
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
                    if(sender.hasPermission(selfCreative)){
                        player.setGameMode(GameMode.CREATIVE);
                        gamemode.gamemoded(main, sender, "Creative");
                        return true;
                    } else {
                        error.noPerm(main, sender, selfCreative);
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("0")) {
                    if(sender.hasPermission(selfAdventure)){
                        player.setGameMode(GameMode.ADVENTURE);
                        gamemode.gamemoded(main, sender, "Adventure");
                        return true;
                    } else {
                        error.noPerm(main, sender, selfAdventure);
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("0")) {
                    if(sender.hasPermission(selfSpectator)){
                        player.setGameMode(GameMode.SPECTATOR);
                        gamemode.gamemoded(main, sender, "Spectator");
                    } else {
                        error.noPerm(main, sender, selfSpectator);
                        return false;
                        }
                } else {
                    gamemode.invalidGamemode(main, sender, args[0].toString());
                    return false;
                }
            } else if (args.length >= 2) {
                Player player2 = Bukkit.getPlayer(args[0].toString());
                if (player2 != null) {
                    if (player.getName().equals(player2.getName())){
                        if (args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("0")) {
                            if(sender.hasPermission(selfSurvival)){
                                player.setGameMode(GameMode.SURVIVAL);
                                gamemode.gamemoded(main, sender, "Survival");
                                return true;
                            } else {
                                error.noPerm(main, sender, selfSurvival);
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("creative") || args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("1")) {
                            if(sender.hasPermission(selfCreative)){
                                player.setGameMode(GameMode.CREATIVE);
                                gamemode.gamemoded(main, sender, "Creative");
                                return true;
                            } else {
                                error.noPerm(main, sender, selfCreative);
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("adventure") || args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("0")) {
                            if(sender.hasPermission(selfAdventure)){
                                player.setGameMode(GameMode.ADVENTURE);
                                gamemode.gamemoded(main, sender, "Adventure");
                                return true;
                            } else {
                                error.noPerm(main, sender, selfAdventure);
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("spectator") || args[1].equalsIgnoreCase("sp") || args[1].equalsIgnoreCase("0")) {
                            if(sender.hasPermission(selfSpectator)){
                                player.setGameMode(GameMode.SPECTATOR);
                                gamemode.gamemoded(main, sender, "Spectator");
                            } else {
                                error.noPerm(main, sender, selfSpectator);
                                return false;
                            }
                        } else {
                            gamemode.invalidGamemode(main, sender, args[1].toString());
                            return false;
                        }
                    } else {
                        if (args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("0")) {
                            if(sender.hasPermission(othersSurvival)){
                                player2.setGameMode(GameMode.SURVIVAL);
                                gamemode.gamemoder(main, sender, player2, "Survival");
                                gamemode.gamemodee(main, sender, player2, "Survival");
                                return true;
                            } else {
                                error.noPerm(main, sender, othersSurvival);
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("creative") || args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("1")) {
                            if(sender.hasPermission(othersCreative)){
                                player2.setGameMode(GameMode.CREATIVE);
                                gamemode.gamemoder(main, sender, player2, "Creative");
                                gamemode.gamemodee(main, sender, player2, "Creative");
                                return true;
                            } else {
                                error.noPerm(main, sender, othersCreative);
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("adventure") || args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("0")) {
                            if(sender.hasPermission(othersAdventure)){
                                player2.setGameMode(GameMode.ADVENTURE);
                                gamemode.gamemoder(main, sender, player2, "Adventure");
                                gamemode.gamemodee(main, sender, player2, "Adventure");
                                return true;
                            } else {
                                error.noPerm(main, sender, othersAdventure);
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("spectator") || args[1].equalsIgnoreCase("sp") || args[1].equalsIgnoreCase("0")) {
                            if(sender.hasPermission(othersSpectator)){
                                player2.setGameMode(GameMode.SPECTATOR);
                                gamemode.gamemoder(main, sender, player2, "Spectator");
                                gamemode.gamemodee(main, sender, player2, "Spectator");
                            } else {
                                error.noPerm(main, sender, othersSpectator);
                                return false;
                            }
                        } else {
                            gamemode.invalidGamemode(main, sender, args[0].toString());
                            return false;
                        }
                    }
                } else {
                    error.playerOffline(main, sender, args[0].toString());
                    return false;
                }
            }
        } else {
            if (args.length < 1){
                gamemode.usage(main, sender);
                return true;
            } else if (args.length == 1) {
                gamemode.consoleError(main, sender);
            } else if (args.length >= 2) {
                Player player2 = Bukkit.getPlayer(args[0].toString());
                if (player2 != null){
                    if (args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("0")){
                        player2.setGameMode(GameMode.SURVIVAL);
                        gamemode.gamemoder(main, sender, player2, "Survival");
                        gamemode.gamemodee(main, sender, player2, "Survival");
                        return true;
                    } else if (args[1].equalsIgnoreCase("creative") || args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("1")){
                        player2.setGameMode(GameMode.CREATIVE);
                        gamemode.gamemoder(main, sender, player2, "Creative");
                        gamemode.gamemodee(main, sender, player2, "Creative");
                        return true;
                    } else if (args[1].equalsIgnoreCase("adventure") || args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("2")){
                        player2.setGameMode(GameMode.ADVENTURE);
                        gamemode.gamemoder(main, sender, player2, "Adventure");
                        gamemode.gamemodee(main, sender, player2, "Adventure");
                        return true;
                    } else if (args[1].equalsIgnoreCase("spectator") || args[1].equalsIgnoreCase("sp") || args[1].equalsIgnoreCase("3")){
                        player2.setGameMode(GameMode.SPECTATOR);
                        gamemode.gamemoder(main, sender, player2, "Spectator");
                        gamemode.gamemodee(main, sender, player2, "Spectator");
                        return true;
                    } else {
                        gamemode.invalidGamemode(main, sender, args[1].toString());
                        return false;
                    }
                } else {
                    error.playerOffline(main, sender, args[0].toString());
                    return false;
                }
            }
        }
        return false;
    }
}
