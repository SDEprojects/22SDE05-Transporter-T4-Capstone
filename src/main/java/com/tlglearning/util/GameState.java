package com.tlglearning.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static com.tlglearning.util.InputHandling.runCommand;

public class GameState {
    //create scanner obj to read user input


    //CTOR
    public GameState() {
    }

    public static void newGame() throws IOException {
        ArrayList<String> itemsNeeded = new ArrayList<>();
        Location currentLocation = new Location();
        Inventory backpack = new Inventory();
        ScenarioGenerator startingScenario = new ScenarioGenerator(
                "New Mexico",
                "Arizona",
                "Washington", itemsNeeded);

        currentLocation.setLocationName("truck");
        currentLocation.setEast("warehouse");
        itemsNeeded.add("Logbook");
        itemsNeeded.add("Keys");
        itemsNeeded.add("Folder");
        itemsNeeded.add("Truck Key");

        BufferedReader in;
        String userInput;
        List<String> toPlayer;
        //get users input and go through run command
        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("\nEnter command, or type 'h' for help options >>> ");
            userInput = in.readLine();
            toPlayer = runCommand(userInput, currentLocation, backpack, startingScenario);
            if (!toPlayer.isEmpty()) {
                action(toPlayer, currentLocation, backpack);
            }
        } while (!"q".equals(userInput));
        System.out.println("Thanks for playing, exiting.....");


    }

    private static void action(List<String> toPlayer, Location currentLocation, Inventory backpack){
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


