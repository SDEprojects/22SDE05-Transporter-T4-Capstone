package com.tlglearning.util;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonParser {
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

        return(nextLoc);
    }

//    public static String verb(){
//
//    }

    public static void main(String[] args) throws IOException {
        // variables
        String current = "Truck";
        String direction= "east" ;
        File locationJson = new File("src/main/resources/location.json");
        JsonNode locations = parse(locationJson);

        String randomStringName = locationFinder(current,direction,locations);
        System.out.println(randomStringName);

    }

}