package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

import static com.tlglearning.util.JacksonParser.locationFinder;
import static com.tlglearning.util.JacksonParser.parse;

public class Player {

    private Inventory backpack;
    public Player(Inventory backpack) {
        this.backpack = backpack;
    }

    public void move(String current, String nextLocation, Location currentLocation){
        JsonNode locations;
        File locationJson = new File("src/main/resources/location.json");
        try {
            locations = parse(locationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String newLocation = locationFinder(current,nextLocation,locations);
        if (newLocation.equals("null")){
            System.out.println("This direction leads to nowhere, please try a different direction");
            // loop back to userinput
        }
        currentLocation.setLocationName(newLocation);
        System.out.println(locationFinder(currentLocation.toString(), "description", locations));
        //loop back to userinput
    }

    public void explore(String current, String exploreLocation, Location currentLocation){
        JsonNode locations;
        File locationJson = new File("src/main/resources/exploreLocation.json");
        try {
            locations = parse(locationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String newExploreLocation = locationFinder(current,exploreLocation,locations);
        if (newExploreLocation == null){
            System.out.println("That location is not explorable, please enter a valid explorable location");
            //loop back to userinput
        }
        System.out.println(locationFinder(currentLocation.toString(), exploreLocation, locations));
        //loop back to userinput
    }

    public void get(String current, String item, Location currentLocation){
        JsonNode locations;
        File locationJson = new File("src/main/resources/items.json");
        try {
            locations = parse(locationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String newItem = locationFinder(current,item,locations);
        if (newItem == null){
            System.out.println("That item is not valid, please enter a valid item to add to your backpack");
            //loop back to userinput
        }
        System.out.println(locationFinder(currentLocation.toString(), item, locations));
        backpack.setBackpack(item);
        //loop back to userinput
    }



}
