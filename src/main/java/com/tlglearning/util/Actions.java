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
    GamePrompt prompt = new GamePrompt();

    public Actions() throws IOException {
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
            prompt.runPromptRed("invalidLocation");
        } else {
            updateLocationDetails(currentLocation, newLocation, moveLocation);
        }
    }
    public void explore(String current, String interestLocation, Inventory backpack) {
        String newExploreLocation = locationFinder(current, interestLocation, exploreLocation);
        if (newExploreLocation == null) {
            prompt.runPromptRed("invalidExplore");
        }
        if (newExploreLocation != null) {
            if (interestLocation.equals("cabinet") || interestLocation.equals("closet") || interestLocation.equals("locker")) {
                boolean hasKey = backpack.getBackpack().contains("key");
                if (hasKey) {
                    System.out.println(newExploreLocation);
                } else {
                    prompt.runPromptRed("keyNeeded");
                }
            } else {
                System.out.println(newExploreLocation);
            }
        }
    }

    public void get(String current, String item, Inventory backpack) {
        String newItem = locationFinder(current, item, items);
        if (newItem == null) {
            prompt.runPromptRed("invalidItem");
        }
        if (item != null) {
            if (item.equals("coffee")) {
                boolean hasKey = backpack.getBackpack().contains("thermos");
                if (hasKey) {
                    System.out.println(newItem);
                    backpack.setBackpack(item);
                } else {
                    prompt.runPromptRed("thermosNeeded");
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
            prompt.runPromptRed("canNotTravel");
        } else if(newLocation.equals("mexico") || newLocation.equals("canada")) {
            prompt.runPromptRed("passportError");
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
            prompt.runPromptCyan("successPickUp");
            loadPickedUp = true;
        }else {
           prompt.runPromptRed("pickUpLocationError");
        }
    }

    public void dropoff(String locationName, ScenarioGenerator scenario) {
        String dropoffLocation = scenario.getDeliveryLocation().replaceAll("\"", "");
        if (dropoffLocation.equals(locationName)){
            if (loadPickedUp){
                prompt.runPromptCyan("deliverySuccess");
                loadDelivered = true;
            } else {
                prompt.runPromptRed("missingLoadError");
            }
        }else{
            prompt.runPromptRed("deliveryLocationError");
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

