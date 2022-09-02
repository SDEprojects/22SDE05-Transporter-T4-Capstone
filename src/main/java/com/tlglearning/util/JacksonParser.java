package com.tlglearning.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;

public class JacksonParser {





    //using Jackson to create a JsonNode object to parse through File objects
    public static JsonNode parse(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        JsonNode jsonNode = objectMapper.readTree(file);
        return jsonNode;
    }

    //use locationFinder method to use current location and return next location
    public static String locationFinder(String current, String direction, JsonNode locations) {
        JsonNode currentLoc = locations.findValue(current);
        String nextLoc = currentLoc.findValue(direction).toString();
        String realNextLoc = nextLoc.replaceAll("\"", "");
        return (realNextLoc);
    }

    public static String getDescription(String newlocation, String desc, JsonNode locations) {
        JsonNode newLoc = locations.findValue(newlocation);
        String description = newLoc.findValue(desc).toString();
        return (description);
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

//    public static void main(String[] args) throws IOException {
//        // variables
//        String current = "Front Office";
//        String direction = "east";
//        File locationJson = new File("src/main/resources/location.json");
//        JsonNode locations = parse(locationJson);
//
//        String randomStringName = locationFinder(current, direction, locations);
//        System.out.println(randomStringName);
//
//        //test verb method
//        String verbTest = "go";
//        File commandJson = new File("src/main/resources/command.json");
//        JsonNode verbage = parse(commandJson);
//        String randomTest = userInputHandling(verbTest, verbage);
//        System.out.println(randomTest);
//
//
//        //test parse
//
//
//
//    }

}