package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

import static com.tlglearning.util.JacksonParser.*;

public class Actions {
    private final JsonNode moveLocation;
    private final JsonNode exploreLocation;
    private final JsonNode items;
    private final JsonNode stateLocation;
    boolean loadPickedUp = false;
    boolean loadDelivered = false;

    public Actions(){
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
            updateLocationDetails(currentLocation, newLocation, moveLocation);
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
        } else if(newLocation.equals("mexico") || newLocation.equals("canada")) {
            System.out.println("You cross the international border without passport. You were not able to return back. GAME OVER!!!!!");
            gameStart.gameStart();
        } else {
            updateLocationDetails(currentLocation, newLocation, stateLocation);
        }

    }

    public void initializeDrive(Location currentLocation, ScenarioGenerator scenario) {
        String newLocation = scenario.getOfficeLocation().replaceAll("\"", "");
        updateLocationDetails(currentLocation, newLocation, stateLocation);
    }
    public void pickup(String locationName, ScenarioGenerator scenario) {
        String pickupLocation = scenario.getPickupLocation().replaceAll("\"", "");
        if (pickupLocation.equals(locationName)){
            System.out.println("You have successfully picked up your load, proceed to delivery location");
            loadPickedUp = true;
        }else {
            System.out.println("You are not in your pickup location, type 'h' and select option 4 to confirm pickup location");
        }
    }

    public void dropoff(String locationName, ScenarioGenerator scenario) {
        String dropoffLocation = scenario.getDeliveryLocation().replaceAll("\"", "");
        if (dropoffLocation.equals(locationName)){
            if (loadPickedUp){
                System.out.println("You have successfully delivered your load, go back to the office!");
                loadDelivered = true;
            } else {
                System.out.println("You need to go back to your pickup location and pickup your load.");
            }
        }else{
            System.out.println("You are not in your delivery location, type 'h' and select option 4 to confirm delivery location");
        }
    }

    private void updateLocationDetails(Location currentLocation, String newLocation, JsonNode jsonNodeObj) {
        currentLocation.setLocationName(newLocation);
        currentLocation.setNorth(getDescription(newLocation, "north", jsonNodeObj));
        currentLocation.setSouth(getDescription(newLocation, "south", jsonNodeObj));
        currentLocation.setEast(getDescription(newLocation, "east", jsonNodeObj));
        currentLocation.setWest(getDescription(newLocation, "west", jsonNodeObj));

        System.out.println(getDescription(newLocation, "description", jsonNodeObj));
    }

}

