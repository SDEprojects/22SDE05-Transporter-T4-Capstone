package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static com.tlglearning.util.InputHandling.runCommand;
import static com.tlglearning.util.JacksonParser.getScenario;
import static com.tlglearning.util.JacksonParser.parse;

public class GameState {

    //CTOR
    public GameState() {
    }

    public static void newGame() throws IOException {
        Location currentLocation = new Location();
        Inventory backpack = new Inventory();
        ScenarioGenerator startingScenario = newScenario();
        Player player = new Player();

        BufferedReader in;
        String userInput;
        List<String> toPlayer;
        //get users input and go through run command
        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print(PrettyText.CYAN.getColor()+
                    "\nEnter command, or type 'h' for help options >>> "+
                    PrettyText.RESET.getColor());
            userInput = in.readLine();
            toPlayer = runCommand(userInput, currentLocation, backpack, startingScenario);
            if (!toPlayer.isEmpty()) {
                action(toPlayer, currentLocation, backpack, startingScenario, player);
            }
        } while (!"q".equals(userInput));
        System.out.println(PrettyText.CYAN.getColor()+
                "Thanks for playing, exiting....."+
                PrettyText.RESET.getColor());


    }

    private static void action(List<String> toPlayer, Location currentLocation, Inventory backpack, ScenarioGenerator scenario, Player player) {
        String verb = null;
        if (toPlayer.get(0) != null) {
            verb = toPlayer.get(0).replaceAll("\"", "");
        }
        String noun = null;
        if (toPlayer.get(1) != null) {
            noun = toPlayer.get(1).replaceAll("\"", "");
        }

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
                        player.get(currentLocation.getLocationName(), noun, backpack);
                        break;
                    case "drive":
//                        truck.drive(scenario.getOfficeLocation(), noun, scenario);
                        break;
                    default:
                        System.out.println(PrettyText.RED.getColor()+
                                "Not a valid command, use go, explore, or get"+
                                PrettyText.RESET.getColor());
                }
            }
        } else {
            System.out.println(PrettyText.RED.getColor()+
                    "Not a valid command! Please try the command again or type 'h' for " +
                    "help and to see list of valid commands"+
                    PrettyText.RESET.getColor());
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

    public static ScenarioGenerator newScenario(){
        JsonNode locations;
        File locationJson = new File("src/main/resources/scenarios.json");
        try {
            locations = parse(locationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        int rand = 0;
        while (true){
            rand = random.nextInt(6);
            if(rand !=0) break;
        }
        JsonNode newScenario = getScenario(String.valueOf(rand), locations);
        String itemsFromJson = newScenario.findValue("items needed").toString();
        String[] items = itemsFromJson.split(",");
        ArrayList<String> itemsNeeded = new ArrayList<>(Arrays.asList(items));
        return new ScenarioGenerator(
                newScenario.findValue("office location").toString(),
                newScenario.findValue("pickup location").toString(),
                newScenario.findValue("delivery location").toString(),
                itemsNeeded);
    }
}


