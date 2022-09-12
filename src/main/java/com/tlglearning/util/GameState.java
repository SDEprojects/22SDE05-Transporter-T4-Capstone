package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.util.*;

import static com.tlglearning.util.InputHandling.runCommand;
import static com.tlglearning.util.InputHandling.getScenario;
import static com.tlglearning.util.JacksonParser.parse;

public class GameState {
    private static GamePrompt prompt = new GamePrompt();
    //CTOR
    public GameState(){
    }
    //method to start a new game and initialize all necessary components
    public static void newGame() throws IOException {
        Location currentLocation = new Location();
        Inventory backpack = new Inventory();
        ScenarioGenerator startingScenario = newScenario();
        Actions player = new Actions();

        BufferedReader in;
        String userInput;
        List<String> toPlayer;
        //get users input and go through run command
        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            String map = currentLocation.getLocationName();
            prompt.runPrompt(map);
            prompt.runPromptCyan("enterCommand");
            userInput = in.readLine();
            toPlayer = runCommand(userInput, currentLocation, backpack, startingScenario);
            if (!toPlayer.isEmpty()) {
                action(toPlayer, currentLocation, backpack, startingScenario, player);
            }
        } while (!"q".equals(userInput));
        prompt.runPromptCyan("quit");
    }
    //takes the command input and runs the action method that correlates to the verb in the command input
    private static void action(List<String> toPlayer, Location currentLocation, Inventory backpack, ScenarioGenerator scenario, Actions player) throws IOException {
        String verb = null;
        if (toPlayer.get(0) != null) {
            verb = toPlayer.get(0).replaceAll("\"", "");
        }
        String noun = null;
        if (toPlayer.get(1) != null) {
            noun = toPlayer.get(1).replaceAll("\"", "");
        }
        if (verb != null && noun != null ) {
                switch (verb) {
                    case "go":
                        player.move(currentLocation.getLocationName(), noun, currentLocation);
                        break;
                    case "explore":
                        player.explore(currentLocation.getLocationName(), noun, backpack);
                        break;
                    case "get":
                        player.get(currentLocation.getLocationName(), noun, backpack);
                        break;
                    case "start":
                        startDriving(currentLocation, backpack, scenario, player);
                        break;
                    case "drive":
                        player.drive(currentLocation.getLocationName(), noun, currentLocation);
                        break;
                    case "pickup":
                        player.pickup(currentLocation.getLocationName(), scenario);
                        break;
                    case "deliver":
                        player.deliver(currentLocation.getLocationName(), scenario);
                        break;
                    default:
                        prompt.runPromptRed("defaultError");
                }
        } else {
            prompt.runPromptRed("invalidCommand");
        }
    }
    //allows player to start the driving phase of the game as long as they have collected the required items and are at their truck.
    private static void startDriving(Location currentLocation, Inventory backpack, ScenarioGenerator scenario, Actions player) {
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
                prompt.runPrompt("onYourWay");
                player.initializeDrive(currentLocation, scenario);
            } else {
                prompt.runPromptRed("drivingItemsNeed");
                System.out.println(needed);
                needed.clear();
            }
        }else {
            prompt.runPromptRed("noTruckError");
        }
    }
    //generates a random scenario at the start of each new game
    private static ScenarioGenerator newScenario(){
        JsonNode locations;
        InputStream locationJson = GameState.class.getClassLoader().getResourceAsStream("scenarios.json");
        try {
            locations = parse(locationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        int rand;
        do {
            rand = random.nextInt(6);
        } while (rand == 0);
        JsonNode newScenario = getScenario(String.valueOf(rand), locations);
        String itemsFromJson = newScenario.findValue("items needed").toString().replaceAll("\"", "");
        ArrayList<String> itemsNeeded = new ArrayList<>();
        String[] items = itemsFromJson.split(",");
        Collections.addAll(itemsNeeded, items);
        return new ScenarioGenerator(
                newScenario.findValue("office location").toString(),
                newScenario.findValue("pickup location").toString(),
                newScenario.findValue("delivery location").toString(),
                itemsNeeded);
    }
}


