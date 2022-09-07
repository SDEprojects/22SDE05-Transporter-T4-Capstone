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
        return objectMapper.readTree(file);
    }

    //use locationFinder method to use current location and return next location
    public static String locationFinder(String current, String direction, JsonNode locations) {
        JsonNode currentLoc = null;
        String nextLoc = null;
        try {
            currentLoc = locations.findValue(current);
            nextLoc = (currentLoc.findValue(direction).toString()).replaceAll("\"", "");
        } catch (Exception e) {
            System.out.println("Not a valid command! Please try the command again or type 'h' for " +
                    "help and to see list of valid commands");
        }
        return nextLoc;
    }

    public static String getDescription(String newlocation, String desc, JsonNode locations) {
        JsonNode newLoc = locations.findValue(newlocation);
        return (newLoc.findValue(desc).toString());
    }

    public static JsonNode getScenario(String rand, JsonNode locations){
        return locations.findValue(rand);
    }

    //user input handling for verb
    public static String userInputHandling(String verb, JsonNode verbs) {
        JsonNode verbNode = verbs.findValue(verb);
        if (verbNode == null) {
            return null;
        }
        return verbNode.toString();
    }
}