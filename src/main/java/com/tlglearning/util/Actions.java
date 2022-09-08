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
    //ctor for Actions that reads in and parses JSON files into a JsonNode obj to be used by the other methods
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
    //uses current location and user input along with JsonNode obj to move player from one room to another
    public void move(String current, String nextLocation, Location currentLocation) {
        String newLocation = InputHandling.locationFinder(current, nextLocation, moveLocation);
        if (newLocation == null || newLocation.equals("null")) {
            prompt.runPromptRed("invalidLocation");
        } else {
            updateLocationDetails(currentLocation, newLocation, moveLocation);
            System.out.println(InputHandling.getDescription(newLocation, "description", moveLocation));
        }
    }
    //uses current location and user input to explore a location within a room, also checks for locked locations
    public void explore(String current, String interestLocation, Inventory backpack) {
        String newExploreLocation = InputHandling.locationFinder(current, interestLocation, exploreLocation);
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
    //uses current location and user input to add items to inventory, checks for items in inventory for required conditions.
    public void get(String current, String item, Inventory backpack) {
        String newItem = InputHandling.locationFinder(current, item, items);
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
    //uses current location, user input to allow player to drive from state to state.
    public void drive(String current, String nextLocation, Location currentLocation) throws IOException {
        InputHandling gameStart = new InputHandling();
        String newLocation = InputHandling.locationFinder(current, nextLocation, stateLocation);

        if(newLocation == null || newLocation.equals("null")) {
            prompt.runPromptRed("canNotTravel");
        } else if(newLocation.equals("mexico") || newLocation.equals("canada")) {
            prompt.runPromptRed("passportError");
            gameStart.gameStart();
        } else if (newLocation.equals("ocean")) {
            System.out.println("Do you think you are an Aquaman?? You just sank your truck and yourself in the ocean. You are dead. START OVER AGAIN!!!!");
            gameStart.gameStart();
        } else {
            updateLocationDetails(currentLocation, newLocation, stateLocation);
            System.out.println(InputHandling.getDescription(newLocation, "description", stateLocation));
        }

    }
    //switches the location details to the 'home office' location from the scenario and sets valid directions
    public void initializeDrive(Location currentLocation, ScenarioGenerator scenario) {
        String newLocation = scenario.getOfficeLocation().replaceAll("\"", "");
        updateLocationDetails(currentLocation, newLocation, stateLocation);
        System.out.println("Your current location is " + currentLocation.getLocationName());

    }
    //allows player to pick up load as long as they are in the pickup location determined by the scenario
    public void pickup(String locationName, ScenarioGenerator scenario) {
        String pickupLocation = scenario.getPickupLocation().replaceAll("\"", "");
        if (pickupLocation.equals(locationName)){
            prompt.runPromptCyan("successPickUp");
            loadPickedUp = true;
        }else {
           prompt.runPromptRed("pickUpLocationError");
        }
    }
    //allows player to deliver load as long as they are in the  location determined by the scenario
    public void deliver(String locationName, ScenarioGenerator scenario) {
        String deliveryLocation = scenario.getDeliveryLocation().replaceAll("\"", "");
        if (deliveryLocation.equals(locationName)){
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
//HELPER METHOD
    //updates the location name and the directions in the location object
    private void updateLocationDetails(Location currentLocation, String newLocation, JsonNode jsonNodeObj) {
        currentLocation.setLocationName(newLocation);
        currentLocation.setNorth(InputHandling.getDescription(newLocation, "north", jsonNodeObj));
        currentLocation.setSouth(InputHandling.getDescription(newLocation, "south", jsonNodeObj));
        currentLocation.setEast(InputHandling.getDescription(newLocation, "east", jsonNodeObj));
        currentLocation.setWest(InputHandling.getDescription(newLocation, "west", jsonNodeObj));
    }

}

