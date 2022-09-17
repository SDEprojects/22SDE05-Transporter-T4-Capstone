package com.tlglearning.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.tlglearning.middleware.Redirect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.tlglearning.util.GameState.action;
import static com.tlglearning.util.InputHandling.clearScreen;
import static com.tlglearning.util.InputHandling.runCommand;
import static com.tlglearning.util.Menu.inOffice;

public class LoadGame {
    public static void load() throws IOException {

        GamePrompt prompt = new GamePrompt();

        File file = new File("currentSave.json");
        ObjectMapper loadMapper = new ObjectMapper();
        ObjectReader loadReader = loadMapper.reader(Location.class);
        Location currentLocation = loadReader.readValue(loadMapper.readTree(file).findValue("location"));

        loadReader = loadMapper.reader(ScenarioGenerator.class);
        ScenarioGenerator startingScenario = loadReader.readValue(loadMapper.readTree(file).findValue("scenario"));
        loadReader = loadMapper.reader(Inventory.class);
        Inventory backpack = loadReader.readValue(loadMapper.readTree(file).findValue("inventory"));

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
                Redirect.sendPromptToGui("Items Needed to start driving\n" + startingScenario.getItemsNeeded());
            } else {
                Redirect.sendPromptToGui("Your available directions of travel are:\nNorth= " + currentLocation.getNorth() +
                        "\nSouth= " + currentLocation.getSouth() +
                        "\nEast= " + currentLocation.getEast() +
                        "\nWest= " + currentLocation.getWest());
                player.currentToDestination(currentLocation, startingScenario);
            }
            prompt.runPromptCyan("enterCommand");
            userInput = in.readLine();
            clearScreen();
            toPlayer = runCommand(userInput, currentLocation, backpack, startingScenario);
            if (!toPlayer.isEmpty()) {
                action(toPlayer, currentLocation, backpack, startingScenario, player);
            }
        } while (!"q".equals(userInput));
        prompt.runPromptCyan("quit");
    }
}