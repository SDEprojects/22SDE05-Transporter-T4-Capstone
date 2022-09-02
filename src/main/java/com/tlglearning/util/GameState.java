package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.tlglearning.util.TitleScreen;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.tlglearning.util.JacksonParser.parse;
import static com.tlglearning.util.JacksonParser.userInputHandling;
import static com.tlglearning.util.Menu.helpMenu;

public class GameState {
    //create scanner obj to read user input
    Scanner read = new Scanner(System.in);
    ArrayList<String> itemsNeeded = new ArrayList<>();
    Location startingLocation = new Location();
    Inventory backpack = new Inventory();
    ScenarioGenerator startingScenario = new ScenarioGenerator(
            "New Mexico",
            "Arizona",
            "Washington", itemsNeeded);

    //CTOR
    public GameState() {
        startingLocation.setLocationName("Truck");
        startingLocation.setEast("Warehouse");
        itemsNeeded.add("Logbook");
        itemsNeeded.add("Keys");
        itemsNeeded.add("Folder");
        itemsNeeded.add("Truck Key");

    }

    public void gameInput() {
        System.out.println("\nYou may use the inputs 'N' to start a new game. 'Q' to quit game. Also Type 'H' to look at" +
                " instructions.\n>>>");
        String input = read.next().toLowerCase();
        //switch case to get user input and perform the necessary commands
        switch (input) {
            case "q":
                System.out.println("quitting....");
                System.exit(0);
                break;
            case "n":
                System.out.println("New game started");
                newGame();
                break;
            case "h":
                System.out.println(helpMenu(startingLocation, backpack, startingScenario));
                break;
            default:
                System.out.println("Not a valid input");
        }

    }

    private void newGame() {
        TitleScreen gameStart = new TitleScreen();
        gameStart.intro();
    }

    public static List<String> commandWords(String input) {
        List<String> listOfUserInput = new ArrayList<>();
        String[] words = input.split(" ");

        for (String word : words) {
            listOfUserInput.add(word);
        }
        return listOfUserInput;
    }

    public static List<String> runCommand(String input) throws IOException {
        List<String> listOfWords;
        List<String> toPlayer = new ArrayList<>();
        String lowstr = input.trim().toLowerCase();

        if (!lowstr.equals("q")) {
            if (lowstr.equals(" ")) {
                System.out.println("You must enter a command");
            } else {
                listOfWords = commandWords(lowstr);
                toPlayer = processUserInput(listOfWords);
            }
        }
        return toPlayer;
    }

    public static List<String> processUserInput(List<String> wordlist) throws IOException {
        String verb;
        String noun;
        List<String> command = new ArrayList<>();


        if (wordlist.size() < 2) {
            System.out.println("We need more than one word.");
        } else {
            verb = wordlist.get(0);
            File commandJson = new File("src/main/resources/command.json");
            JsonNode verbage = parse(commandJson);
            String verbHandler = userInputHandling(verb, verbage);

            wordlist.remove(0);
            noun = String.join(" ", wordlist);

            command.add(verbHandler);
            command.add(noun);
        }
        return command;
    }




}


