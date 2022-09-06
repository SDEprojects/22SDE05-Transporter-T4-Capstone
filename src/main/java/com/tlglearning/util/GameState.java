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


    //CTOR
    public GameState() {
    }

    public void gameInput(Scanner read, Location startingLocation, Inventory backpack,ScenarioGenerator startingScenario) {
        System.out.println("\nYou may use the inputs 'N' to start a new game. 'Q' to quit game. Also Type 'H' to look at" +
                " instructions.\n>>> ");
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
               return;
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

    public static List<String> runCommand(String input, Location currentLocation, Inventory backpack,ScenarioGenerator startingScenario) throws IOException {
        List<String> listOfWords;
        List<String> toPlayer = new ArrayList<>();
        String lowstr = input.trim().toLowerCase();

        if (!lowstr.equals("q")) {
            if (lowstr.equals("h")) {
                System.out.println(helpMenu(currentLocation, backpack, startingScenario));
            }else {
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
            File commandJson = new File("src/main/resources/command.json");
            JsonNode verbage = parse(commandJson);

            verb = wordlist.get(0);
            String verbHandler = userInputHandling(verb, verbage);

            wordlist.remove(0);
            noun = String.join(" ", wordlist);
            String nounHandler = userInputHandling(noun, verbage);

            command.add(verbHandler);
            command.add(nounHandler);
        }
        return command;
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


