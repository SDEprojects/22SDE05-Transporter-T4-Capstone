package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.function.IntBinaryOperator;

import static com.tlglearning.util.JacksonParser.*;

public class Player {
    
    public void move(String current, String nextLocation, Location currentLocation) {
        JsonNode locations;
        File locationJson = new File("src/main/resources/location.json");
        try {
            locations = parse(locationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String newLocation = locationFinder(current, nextLocation, locations);
        if (newLocation == null || newLocation.equals("null")) {
            System.out.println("That location is invalid.");
            return;
        } else {
            currentLocation.setLocationName(newLocation);
            currentLocation.setNorth(getDescription(newLocation, "north", locations));
            currentLocation.setSouth(getDescription(newLocation, "south", locations));
            currentLocation.setEast(getDescription(newLocation, "east", locations));
            currentLocation.setWest(getDescription(newLocation, "west", locations));

            System.out.println(getDescription(newLocation, "description", locations));
        }
    }

    public void explore(String current, String exploreLocation, Inventory backpack) {
        JsonNode locations;
        File locationJson = new File("src/main/resources/exploreLocation.json");
        try {
            locations = parse(locationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String newExploreLocation = locationFinder(current, exploreLocation, locations);
        if (newExploreLocation == null) {
            System.out.println("That location is not explorable.");
        }
        if (newExploreLocation != null) {
            if (exploreLocation.equals("cabinet") || exploreLocation.equals("closet") || exploreLocation.equals("locker")) {
                boolean hasKey = backpack.getBackpack().contains("key");
                if (hasKey) {
                    System.out.println(newExploreLocation);
                } else {
                    System.out.println("You need the key in the warehouse desk to open this");
                }
            } else {
                System.out.println(newExploreLocation);
            }
        }
    }

    public void get(String current, String item, Location currentLocation, Inventory backpack) {
        JsonNode locations;
        File locationJson = new File("src/main/resources/items.json");
        try {
            locations = parse(locationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String newItem = locationFinder(current, item, locations);
        if (newItem == null) {
            System.out.println("That item is invalid.");
        }
        if (item != null) {
            if (item.equals("coffee")) {
                boolean hasKey = backpack.getBackpack().contains("thermos");
                if (hasKey) {
                    System.out.println(newItem);
                    backpack.setBackpack(item);
                } else {
                    System.out.println("You need something to put coffee in, grab your thermos from your locker");
                }
            } else {
                System.out.println(newItem);
                backpack.setBackpack(item);
            }//do nothing
        }
    }
}
