package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

import static com.tlglearning.util.JacksonParser.*;

public class Player {
    private final JsonNode moveLocation;
    private final JsonNode exploreLocation;
    private final JsonNode items;
    private final JsonNode stateLocation;

    public Player(){
        try {
            File locationJson = new File("src/main/resources/location.json");
            moveLocation = parse(locationJson);
            File exploreLocationJson = new File("src/main/resources/exploreLocation.json");
            exploreLocation = parse(exploreLocationJson);
            File itemJson = new File("src/main/resources/items.json");
            items = parse(itemJson);
            File stateLocationJson = new File("src/main/resources/states.json");
            stateLocation = parse(stateLocationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void move(String current, String nextLocation, Location currentLocation) {
        String newLocation = locationFinder(current, nextLocation, moveLocation);
        if (newLocation == null || newLocation.equals("null")) {
            System.out.println(PrettyText.RED.getColor()+
                    "That location is invalid."+
                    PrettyText.RESET.getColor());
        } else {
            currentLocation.setLocationName(newLocation);
            currentLocation.setNorth(getDescription(newLocation, "north", moveLocation));
            currentLocation.setSouth(getDescription(newLocation, "south", moveLocation));
            currentLocation.setEast(getDescription(newLocation, "east", moveLocation));
            currentLocation.setWest(getDescription(newLocation, "west", moveLocation));

            System.out.println(getDescription(newLocation, "description", moveLocation));
        }
    }

    public void explore(String current, String interestLocation, Inventory backpack) {
        String newExploreLocation = locationFinder(current, interestLocation, exploreLocation);
        if (newExploreLocation == null) {
            System.out.println(PrettyText.RED.getColor()+
                    "That location is not explorable."+
                    PrettyText.RESET.getColor());
        }
        if (newExploreLocation != null) {
            if (interestLocation.equals("cabinet") || interestLocation.equals("closet") || interestLocation.equals("locker")) {
                boolean hasKey = backpack.getBackpack().contains("key");
                if (hasKey) {
                    System.out.println(newExploreLocation);
                } else {
                    System.out.println(PrettyText.RED.getColor()+
                            "You need the key in the warehouse desk to open this"+
                            PrettyText.RESET.getColor());
                }
            } else {
                System.out.println(newExploreLocation);
            }
        }
    }

    public void get(String current, String item, Inventory backpack) {
        String newItem = locationFinder(current, item, items);
        if (newItem == null) {
            System.out.println(PrettyText.RED.getColor()+
                    "That item is invalid."+
                    PrettyText.RESET.getColor());
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
    public void drive(String current, String nextLocation, Location currentLocation) throws IOException {
        InputHandling gameStart = new InputHandling();
        String newLocation = locationFinder(current, nextLocation, stateLocation);

        if(newLocation == null || newLocation.equals("null")) {
            System.out.println("Cannot travel there!!!");
            return;
        } else if(newLocation.equals("mexico") || newLocation.equals("canada")) {
            System.out.println("You cross the international border without passport. You were not able to return back. GAME OVER!!!!!");
            gameStart.gameStart();
        } else {
            currentLocation.setLocationName(newLocation);
            currentLocation.setNorth(getDescription(newLocation, "north", stateLocation));
            currentLocation.setSouth(getDescription(newLocation, "south", stateLocation));
            currentLocation.setEast(getDescription(newLocation, "east", stateLocation));
            currentLocation.setWest(getDescription(newLocation, "west", stateLocation));

            System.out.println(getDescription(newLocation, "description", stateLocation));
        }

    }

    public void initializeDrive(Location currentLocation, ScenarioGenerator scenario) {
        String newLocation = scenario.getOfficeLocation().replaceAll("\"", "");
        currentLocation.setLocationName(newLocation);
        currentLocation.setNorth(getDescription(newLocation, "north", stateLocation));
        currentLocation.setSouth(getDescription(newLocation, "south", stateLocation));
        currentLocation.setEast(getDescription(newLocation, "east", stateLocation));
        currentLocation.setWest(getDescription(newLocation, "west", stateLocation));

        System.out.println(getDescription(newLocation, "description", stateLocation));
    }
}
