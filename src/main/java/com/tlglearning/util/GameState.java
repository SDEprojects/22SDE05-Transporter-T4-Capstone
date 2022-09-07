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
        itemsNeeded.add("logbook");
        itemsNeeded.add("key");
        itemsNeeded.add("folder");
        itemsNeeded.add("truck key");

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
                action(toPlayer, currentLocation, backpack, startingScenario);
            }
        } while (!"q".equals(userInput));
        System.out.println("Thanks for playing, exiting.....");


    }

    private static void action(List<String> toPlayer, Location currentLocation, Inventory backpack, ScenarioGenerator scenario) {
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
            if (verb.equals("start")) {
                startDriving(currentLocation, backpack, scenario);
            } else {
                switch (verb) {
                    case "go":
                        player.move(currentLocation.getLocationName(), noun, currentLocation);
                        break;
                    case "explore":
                        player.explore(currentLocation.getLocationName(), noun, backpack);
                        break;
                    case "get":
                        player.get(currentLocation.getLocationName(), noun, currentLocation, backpack);
                        break;
                    case "drive":
                        truck.drive(scenario.getOfficeLocation(), noun, scenario);
                        break;
                    default:
                        System.out.println("Not a valid command, use go, explore, or get");
                }
            }
        } else {
            System.out.println("Not a valid command! Please try the command again or type 'h' for " +
                    "help and to see list of valid commands");
        }
    }


    private static void startDriving(Location currentLocation,Inventory backpack, ScenarioGenerator scenario) {
        List<String> inventory = new ArrayList<>(backpack.getBackpack());
        List<String> required = new ArrayList<>(scenario.getItemsNeeded());
        List<String> needed = new ArrayList<>();

        if (currentLocation.getLocationName().equals("truck")){
            for (String item : required) {
                if (inventory.contains(item)) {
                    //do nothing
                } else {
                    needed.add(item);
                }
            }
            if (needed.isEmpty()) {
                System.out.println("Congrats you are on your way!!!!");
                //call driving class or function here
            } else {
                System.out.println("You cannot start driving you still need to find:\n " + needed);
                needed.clear();
            }
        }else {
            System.out.println("You must be at the truck to start driving");
        }
    }
}


