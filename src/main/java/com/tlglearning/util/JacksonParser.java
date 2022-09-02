package com.tlglearning.util;

import com.fasterxml.jackson.databind.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JacksonParser {
    Scanner read = new Scanner(System.in);

    public static void parseUserInput(List<String> wordlist) throws IOException {
        String verb;
        String noun;
        List<String> nounParse = new ArrayList<>(Arrays.asList("north", "south", "east", "west"));

        if (wordlist.size() < 2) {
            System.out.println("We need more than one word.");
        } else {
            verb = wordlist.get(0);
            File commandJson = new File("src/main/resources/command.json");
            JsonNode verbage = parse(commandJson);
            String verbHandler = userInputHandling(verb, verbage);

            noun = wordlist.get(1);

            if (!nounParse.contains(noun)) {
                System.out.println(noun + " is not a noun.");
            }
        }

    }

    public static List<String> commandWords(String input) {
        List<String> listOfUserInput = new ArrayList<>();
        String[] words = input.split(" ");

        for (String word : words) {
            listOfUserInput.add(word);
        }
        return listOfUserInput;
    }

    public static String runCommand(String input) throws IOException {
        List<String> listOfWords;
        String s = "filler";
        String lowstr = input.trim().toLowerCase();

        if (!lowstr.equals("q")) {
            if (lowstr.equals(" ")) {
                s = "Enter a command";
            } else {
                listOfWords = commandWords(lowstr);
                parseUserInput(listOfWords);
                return s = "done";
            }
        }
        return s;
    }


    //using Jackson to create a JsonNode object to parse through File objects
    public static JsonNode parse(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        return jsonNode;
    }

    //use locationFinder method to use current location and return next location
    public static String locationFinder(String current, String direction, JsonNode locations) {
        JsonNode currentLoc = locations.findValue(current);
        String nextLoc = currentLoc.findValue(direction).toString();
        return (nextLoc);
    }

    //user input handling for verb
    public static String userInputHandling(String verb, JsonNode verbs) {
        JsonNode verbNode = verbs.findValue(verb);
        if (verbNode == null) {
            System.out.println("Not a valid command! Look at the Help tutorial for guidance.");
            return null;
        }
        return verbNode.toString();
    }

    public static void main(String[] args) throws IOException {
        // variables
        String current = "Front Office";
        String direction = "east";
        File locationJson = new File("src/main/resources/location.json");
        JsonNode locations = parse(locationJson);

        String randomStringName = locationFinder(current, direction, locations);
        System.out.println(randomStringName);

        //test verb method
        String verbTest = "go";
        File commandJson = new File("src/main/resources/command.json");
        JsonNode verbage = parse(commandJson);
        String randomTest = userInputHandling(verbTest, verbage);
        System.out.println(randomTest);


        //test parse
        BufferedReader in;
        String userInput;
        String output;
        //get users input and go through run command
        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("Enter command");
            userInput = in.readLine();
            output = runCommand(userInput);
            System.out.println(output);
        } while (!"q".equals(userInput));


    }

}