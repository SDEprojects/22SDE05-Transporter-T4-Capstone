package com.tlglearning.client;

import com.tlglearning.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.tlglearning.util.GameState.action;
import static com.tlglearning.util.GameState.runCommand;

public class TransporterClient {

    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(System.in);
        ArrayList<String> itemsNeeded = new ArrayList<>();
        Location startingLocation = new Location();
        Inventory backpack = new Inventory();
        ScenarioGenerator startingScenario = new ScenarioGenerator(
                "New Mexico",
                "Arizona",
                "Washington", itemsNeeded);

        startingLocation.setLocationName("Truck");
        startingLocation.setEast("warehouse");
        itemsNeeded.add("Logbook");
        itemsNeeded.add("Keys");
        itemsNeeded.add("Folder");
        itemsNeeded.add("Truck Key");




        TitleScreen start = new TitleScreen();
        start.titleScreen();

        GameState gameStart = new GameState();
        gameStart.gameInput(read, startingLocation, backpack, startingScenario);

        BufferedReader in;
        String userInput;
        List<String> toPlayer;
        //get users input and go through run command
        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("\nEnter command >>> ");
            userInput = in.readLine();
            toPlayer = runCommand(userInput);
            if (!toPlayer.isEmpty()) {
                action(toPlayer, startingLocation, backpack);
            }
        } while (!"q".equals(userInput));

    }
}
