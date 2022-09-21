package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.tlglearning.middleware.Redirect;

import java.io.IOException;
import java.io.InputStream;

import static com.tlglearning.util.JacksonParser.parse;

public class Actions {
    private final JsonNode moveLocation;
    private final JsonNode exploreLocation;
    private final JsonNode items;
    private final JsonNode stateLocation;
    boolean load1PickedUp = false;
    boolean load2PickedUp = false;
    boolean aLoadDelivered = false;
    boolean bLoadDelivered = false;
    boolean cLoadDelivered = false;
    boolean dLoadDelivered = false;
    boolean needGas = false;

    GamePrompt prompt = new GamePrompt();
    InputHandling gameStart = new InputHandling();

    public Actions() {
        //ctor for Actions that reads in and parses JSON files into a JsonNode obj to be used by the other methods
        try {
            InputStream locationJson = Actions.class.getClassLoader().getResourceAsStream("location.json");
            moveLocation = parse(locationJson);
            InputStream exploreLocationJson = Actions.class.getClassLoader().getResourceAsStream("exploreLocation.json");
            exploreLocation = parse(exploreLocationJson);
            InputStream itemJson = Actions.class.getClassLoader().getResourceAsStream("items.json");
            items = parse(itemJson);
            InputStream stateLocationJson = Actions.class.getClassLoader().getResourceAsStream("states.json");
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
        } else if (newLocation.equals("warehouse")) {
            updateLocationDetails(currentLocation, newLocation, moveLocation);
            System.out.println(InputHandling.getDescription(newLocation, "description", moveLocation));
            Redirect.sendDescriptionToGui(InputHandling.getDescription(newLocation, "description", moveLocation));
            prompt.runPrompt("manager approach");
            prompt.runPrompt("manager conv");

        } else {
            updateLocationDetails(currentLocation, newLocation, moveLocation);
            Redirect.sendDescriptionToGui(InputHandling.getDescription(newLocation, "description", moveLocation));
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
                    Redirect.sendExploreTextToGui(newExploreLocation);
                    System.out.println(newExploreLocation);
                } else {
                    prompt.runPromptRed("keyNeeded");
                }
            } else {
                Redirect.sendExploreTextToGui(newExploreLocation);
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
                    Redirect.sendItemTextToGui(newItem);
                    System.out.println(newItem);
                    backpack.addItem(item);
                } else {
                    prompt.runPromptRed("thermosNeeded");
                }
            } else {
                Redirect.sendItemTextToGui(newItem);
                System.out.println(newItem);
                backpack.addItem(item);
            }//do nothing
        }
    }

    //uses current location, user input to allow player to drive from state to state.
    public void drive(String current, String nextLocation, Location currentLocation, ScenarioGenerator scenario) throws IOException {
        String newLocation = InputHandling.locationFinder(current, nextLocation, stateLocation);

        //Checking for game winning condition
        String officeLocation = scenario.getOfficeLocation().replaceAll("\"", "");
        if (dLoadDelivered && newLocation.equals(officeLocation)) {
            prompt.runPromptCyan("winScreen");
            gameStart.gameStart();
        }

        if (needGas) {
            prompt.runPromptRed("need gas");
        } else if (newLocation == null || newLocation.equals("null")) {
            prompt.runPromptRed("canNotTravel");
        } else if (newLocation.equals("mexico") || newLocation.equals("canada")) {
            prompt.runPromptRed("passportError");
            prompt.runPromptRed("loseScreen");
            gameStart.gameStart();
        } else if (newLocation.equals("ocean")) {
            prompt.runPromptRed("oceanError");
            prompt.runPromptRed("loseScreen");
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
        prompt.runPromptWithLocation("initializeDrive", scenario.getOfficeLocation());
        prompt.runPromptWithLocation("firstPickup", scenario.getPickupLocation1());
    }

    //allows player to pick up load as long as they are in the pickup location determined by the scenario
    public void pickup(String locationName, ScenarioGenerator scenario) {
        String pickupLocation1 = scenario.getPickupLocation1().replaceAll("\"", "");
        String pickupLocation2 = scenario.getPickupLocation2().replaceAll("\"", "");
        if (pickupLocation1.equals(locationName)) {
            prompt.runPromptWithLocation("successPickup", scenario.getDeliveryLocation1());
            load1PickedUp = true;
        } else if (pickupLocation2.equals(locationName)) {
            if (bLoadDelivered) {
                prompt.runPromptWithLocation("successPickup", scenario.getDeliveryLocation2());
                load2PickedUp = true;
            } else {
                prompt.runPromptRed("truckStillFull");
            }
        } else {
            prompt.runPromptRed("pickUpLocationError");
        }
    }

    //allows player to deliver load as long as they are in the  location determined by the scenario
    public void deliver(String locationName, ScenarioGenerator scenario) {
        String deliveryLocation1 = scenario.getDeliveryLocation1().replaceAll("\"", "");
        String deliveryLocation1b = scenario.getDeliveryLocation1b().replaceAll("\"", "");
        String deliveryLocation2 = scenario.getDeliveryLocation2().replaceAll("\"", "");
        String deliveryLocation2b = scenario.getDeliveryLocation2b().replaceAll("\"", "");
        if (deliveryLocation1.equals(locationName)) {
            if (load1PickedUp) {
                prompt.runPromptWithLocation("halfDeliverySuccess", scenario.getDeliveryLocation1b());
                aLoadDelivered = true;
                needGas = true;
            } else {
                prompt.runPromptRed("missingLoadError");
            }
        } else if (deliveryLocation1b.equals(locationName)) {
            if (load1PickedUp && aLoadDelivered) {
                prompt.runPromptWithLocation("bDeliverySuccess", scenario.getPickupLocation2());
                bLoadDelivered = true;
                needGas = true;
            } else {
                prompt.runPromptRed("missingLoadError");
            }
        } else if (deliveryLocation2.equals(locationName)) {
            if (load2PickedUp) {
                prompt.runPromptWithLocation("halfDeliverySuccess", scenario.getDeliveryLocation2b());
                cLoadDelivered = true;
                needGas = true;
            }
        } else if (deliveryLocation2b.equals(locationName)) {
            if (load2PickedUp && cLoadDelivered) {
                prompt.runPromptWithLocation("dDeliverySuccess", scenario.getOfficeLocation());
                dLoadDelivered = true;
                needGas = true;
            } else {
                prompt.runPromptRed("missingLoadError");
            }
        } else {
            prompt.runPromptRed("deliveryLocationError");
        }
    }

    public void getGas() {
        needGas = false;
    }

    public void currentToDestination(Location currentLocation, ScenarioGenerator startingScenario) {

        if (dLoadDelivered) {
            Redirect.sendPromptToGui("Current Location: " + currentLocation.getLocationName() + " --> "
                    + "Home Office Location: " + startingScenario.getOfficeLocation());
        } else if (cLoadDelivered) {
            Redirect.sendPromptToGui("Current Location: " + currentLocation.getLocationName() + " --> "
                    + "Deliver location: " + startingScenario.getDeliveryLocation2b());
        } else if (load2PickedUp) {
            Redirect.sendPromptToGui("Current Location: " + currentLocation.getLocationName() + " --> "
                    + "Deliver location: " + startingScenario.getDeliveryLocation2());
        } else if (bLoadDelivered) {
            Redirect.sendPromptToGui("Current Location: " + currentLocation.getLocationName() + " --> "
                    + "Pickup location: " + startingScenario.getPickupLocation2());
        } else if (aLoadDelivered) {
            Redirect.sendPromptToGui("Current Location: " + currentLocation.getLocationName() + " --> "
                    + "Deliver location: " + startingScenario.getDeliveryLocation1b());
        } else if (load1PickedUp) {
            Redirect.sendPromptToGui("Current Location: " + currentLocation.getLocationName() + " --> "
                    + "Deliver location: " + startingScenario.getDeliveryLocation1());
        } else {
            Redirect.sendPromptToGui("Current Location: " + currentLocation.getLocationName() + " --> "
                    + "Pickup location: " + startingScenario.getPickupLocation1());
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

