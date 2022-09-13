package com.tlglearning.util;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.tlglearning.util.GameState.action;
import static com.tlglearning.util.InputHandling.runCommand;
import static com.tlglearning.util.Menu.inOffice;

public class LoadGame {
    public static void load() throws IOException {

        GamePrompt prompt = new GamePrompt();

        File file = new File("src/main/resources/currentSave.json");
        ObjectMapper loadMapper = new ObjectMapper();
        ObjectReader loadReader = loadMapper.reader(Location.class);
        Location currentLocation = loadReader.readValue(loadMapper.readTree(file).findValue("location"));

        // Location currentLocation = (Location) loadMap.get("location");
        System.out.println("It loaded:\nLoc: " + currentLocation);
        loadReader = loadMapper.reader(ScenarioGenerator.class);
        ScenarioGenerator startingScenario = loadReader.readValue(loadMapper.readTree(file).findValue("scenario"));
        System.out.println("Scenario: " + startingScenario);
        loadReader = loadMapper.reader(Inventory.class);
        Inventory backpack = loadReader.readValue(loadMapper.readTree(file).findValue("inventory"));



        System.out.println("Backpack: " + backpack);
        Actions player = new Actions();

        BufferedReader in;
        String userInput;
        List<String> toPlayer;
        //get users input and go through run command
        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            if (inOffice.contains(currentLocation.getLocationName())) {
                String map = currentLocation.getLocationName();
                prompt.runPrompt(map);
            } else {
                System.out.println("Your available directions of travel are:\nNorth= " + currentLocation.getNorth() +
                        "\nSouth= " + currentLocation.getSouth() +
                        "\nEast= " + currentLocation.getEast() +
                        "\nWest= " + currentLocation.getWest());
            }
            prompt.runPromptCyan("enterCommand");
            userInput = in.readLine();
            toPlayer = runCommand(userInput, currentLocation, backpack, startingScenario);
            if (!toPlayer.isEmpty()) {
                action(toPlayer, currentLocation, backpack, startingScenario, player);
            }
        } while (!"q".equals(userInput));
        prompt.runPromptCyan("quit");
    }
}