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

    public void gameStart() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(PrettyText.CYAN.getColor() +
                "\n\nYou may use the inputs 'N' to start a new game. 'Q' to quit game.\n>>> "
                + PrettyText.RESET.getColor());
        String input = in.readLine().toLowerCase();

        //switch case to get user input and perform the necessary commands
        switch (input) {
            case "q":
                System.out.println(PrettyText.CYAN.getColor() +
                        "quitting...." +
                        PrettyText.RESET.getColor());
                System.exit(0);
                break;
            case "n":
                System.out.println(PrettyText.CYAN.getColor() +
                        "New game started" +
                        PrettyText.RESET.getColor());
                clearScreen();
                newGame();

                break;
            default:
                System.out.println(PrettyText.RED.getColor() +
                        "Not a valid input" +
                        PrettyText.RESET.getColor());
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
                System.out.println(PrettyText.CYAN.getColor()
                        + "New game started" +
                        PrettyText.RESET.getColor());
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
            System.out.println(PrettyText.RED.getColor() +
                    "We need more than one word."
                    + PrettyText.RESET.getColor());
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
