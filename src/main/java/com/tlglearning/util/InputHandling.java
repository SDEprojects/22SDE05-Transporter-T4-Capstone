package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.tlglearning.middleware.Redirect;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.tlglearning.util.GameState.newGame;
import static com.tlglearning.util.JacksonParser.parse;
import static com.tlglearning.util.LoadGame.load;
import static com.tlglearning.util.Menu.helpMenu;
import static com.tlglearning.util.SaveGame.save;

public class InputHandling {
    private static GamePrompt prompt = new GamePrompt();
    private static JsonNode commandInput;

    //ctor to read in and parse JSON file into a JsonNode obj to be used by the other methods
    public InputHandling() {
        try {
            InputStream commandJson = InputHandling.class.getClassLoader().getResourceAsStream("command.json");
            commandInput = parse(commandJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Initial user prompt to start new game or quit
    public void gameStart() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        prompt.runPromptCyan("start");
        String input = in.readLine().toLowerCase();
        //switch case to get user input and perform the necessary commands
        switch (input) {
            case "q":
                prompt.runPromptCyan("quit");
                System.exit(0);
                break;
            case "n":
                Redirect.sendAppToGui("Would you like to load your saved data? Type 'y' ");
                BufferedReader loadIn = new BufferedReader(new InputStreamReader(System.in));
                String loadInput = loadIn.readLine().toLowerCase();
                if (loadInput.equals("y")) {
                    prompt.runPromptCyan("newGameHelp");
                    prompt.runPromptCyan("newGameCommands");
                    load();
                } else {
                    clearScreen();
                    prompt.runPromptCyan("newGame");
                    prompt.runPromptCyan("newGameHelp");
                    prompt.runPromptCyan("newGameCommands");
                    newGame();
                }
                break;
            default:
                prompt.runPromptRed("error");
                gameStart();
        }
    }

    //processes command input and returns toPlayer to be used by the action methods
    public static List<String> runCommand(String input, Location currentLocation, Inventory backpack, ScenarioGenerator startingScenario) throws IOException {
        List<String> listOfWords;
        List<String> toPlayer = new ArrayList<>();
        String lowstr = input.trim().toLowerCase();
        Scanner read = new Scanner(System.in);

        if (!lowstr.equals("q")) {
            if (lowstr.equals("h")) {
                clearScreen();
                helpMenu(read, currentLocation, backpack, startingScenario);
            } else if (lowstr.equals("n")) {
                prompt.runPromptCyan("newGame");
                prompt.runPromptCyan("newGameHelp");
                prompt.runPromptCyan("newGameCommands");
                newGame();
            } else {
                listOfWords = commandWords(lowstr);
                toPlayer = processUserInput(listOfWords);
            }
        } else {
            Redirect.sendAppToGui("Want to save? Input y");
            BufferedReader saveIn = new BufferedReader(new InputStreamReader(System.in));
            String saveInput = saveIn.readLine().toLowerCase();
            if (saveInput.equals("y")) {
                save(currentLocation, backpack, startingScenario);
            }
        }
        return toPlayer;
    }

    //use locationFinder method to use current location and return next location
    public static String locationFinder(String current, String direction, JsonNode locations) {
        JsonNode currentLoc = null;
        String nextLoc = null;
        try {
            currentLoc = locations.findValue(current);
            nextLoc = (currentLoc.findValue(direction).toString()).replaceAll("\"", "");
        } catch (Exception e) {
            Redirect.sendAppToGui(PrettyText.RED.getColor() +
                    "Not a valid command! Please try the command again or type 'h' for " +
                    "help and to see list of valid commands" +
                    PrettyText.RESET.getColor());
        }
        return nextLoc;
    }

    //use getDescription to obtain the text description of the current/new location
    public static String getDescription(String newlocation, String desc, JsonNode locations) {
        JsonNode newLoc = locations.findValue(newlocation);
        return (newLoc.findValue(desc).toString());
    }

    //used to access the scenario JsonNode obj
    public static JsonNode getScenario(String rand, JsonNode locations) {
        return locations.findValue(rand);
    }

    //HELPER Methods
    //used to clear screen for player readability
    static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder process = (os.contains("windows")) ?
                new ProcessBuilder("cmd", "/c", "cls") :
                new ProcessBuilder("clear");
        try {
            process.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    //used to process verb synonyms and check verb/noun for validity
    private static List<String> processUserInput(List<String> wordlist) {
        String verb;
        String noun;
        List<String> command = new ArrayList<>();

        if (wordlist.size() < 2) {
            prompt.runPromptRed("twoError");
        } else {
            verb = wordlist.get(0);
            String verbHandler = userInputHandling(verb, commandInput);

            wordlist.remove(0);
            noun = String.join(" ", wordlist);
            String nounHandler = userInputHandling(noun, commandInput);

            command.add(verbHandler);
            command.add(nounHandler);
        }
        return command;
    }

    //user input handling for words (verbs and nouns)
    private static String userInputHandling(String word, JsonNode commands) {
        JsonNode usableCmd = commands.findValue(word);
        if (usableCmd == null) {
            return null;
        }
        return usableCmd.toString();
    }

    //splits the input string for separating verbs and nouns
    private static List<String> commandWords(String input) {
        String[] words = input.split(" ");
        return new ArrayList<>(Arrays.asList(words));
    }

}
