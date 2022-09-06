package com.tlglearning.util;

import java.util.*;

public class GameState {
    //create scanner obj to read user input


    //CTOR
    public GameState() {
    }

    static void newGame() {
        TitleScreen gameStart = new TitleScreen();
        gameStart.intro();
    }

    public static void action(List<String> toPlayer, Location currentLocation, Inventory backpack){
        String verb = null;
        if (toPlayer.get(0) != null) {
            verb = toPlayer.get(0).replaceAll("\"", "");
        }
        String noun = null;
        if (toPlayer.get(1) != null) {
            noun = toPlayer.get(1).replaceAll("\"", "");
        }
        Player player = new Player();

        if (verb != null) {
            switch (verb){
                case "go":
                    player.move(currentLocation.getLocationName(), noun, currentLocation);
                    break;
                case "explore":
                    player.explore(currentLocation.getLocationName(), noun, backpack);
                    break;
                case "get":
                    player.get(currentLocation.getLocationName(), noun, currentLocation, backpack);
                    break;
                default:
                    System.out.println("Not a valid command, use go, explore, or get");
            }
        } else {
            System.out.println("Not a valid command! Please try the command again or type 'h' for " +
                    "help and to see list of valid commands");
        }

    }


}


