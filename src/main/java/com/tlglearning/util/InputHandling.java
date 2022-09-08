package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.tlglearning.util.GameState.newGame;
import static com.tlglearning.util.JacksonParser.parse;
import static com.tlglearning.util.JacksonParser.userInputHandling;
import static com.tlglearning.util.Menu.helpMenu;


public class InputHandling {
    private static GamePrompt prompt = new GamePrompt();
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
                prompt.runPromptCyan("newGame");
                clearScreen();
                newGame();

                break;
            default:
                prompt.runPromptRed("error");
                gameStart();
        }
    }


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
                newGame();
            } else {
                listOfWords = commandWords(lowstr);
                toPlayer = processUserInput(listOfWords);
            }
        }
        return toPlayer;
    }

    private static List<String> commandWords(String input) {
        String[] words = input.split(" ");

        return new ArrayList<>(Arrays.asList(words));
    }

    private static List<String> processUserInput(List<String> wordlist) throws IOException {
        String verb;
        String noun;
        List<String> command = new ArrayList<>();


        if (wordlist.size() < 2) {
            prompt.runPromptRed("twoError");
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

    public static void clearScreen() {
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

}
