package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

import static com.tlglearning.util.JacksonParser.*;

public class Player {


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
        }else {
            currentLocation.setLocationName(newLocation);
            System.out.println(getDescription(newLocation, "description", locations));
        }
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
        System.out.println(newExploreLocation);
    }

    public void get(String current, String item, Location currentLocation, Inventory backpack){
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
        System.out.println(newItem);
        backpack.setBackpack(item);
        //loop back to userinput
    }



}
