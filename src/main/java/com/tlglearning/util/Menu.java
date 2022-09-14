package com.tlglearning.util;

import com.tlglearning.middleware.Redirect;

import java.util.*;

public class Menu {
    //method to display the help menu to the user
    private static final GamePrompt prompt = new GamePrompt();
    static final ArrayList<String> inOffice = new ArrayList<>(
            Arrays.asList("truck", "warehouse", "boss office", "tech room", "gas station", "front office", "break room", "hr office"));

    public static void helpMenu(Scanner read, Location location, Inventory inventory, ScenarioGenerator scenario){
        if (inOffice.contains(location.getLocationName())) {
            System.out.println("\nWhat do you need help with today?\n" +
                    "'1' - for a list of available commands.\n" +
                    "'2' - to see items in your inventory.\n" +
                    "'3' - to see your current location, available exits, and a map of the office.\n" +
                    "'4' - to see items needed to get on the road.\n >>>");
            int input = Integer.parseInt(read.next());
            switch (input) {
                case 1:
                    Redirect.sendAppToGui(availableCMD(location));
                    break;
                case 2:
                    Redirect.sendAppToGui("\nYour backpack has the following items: \n" + showBackpack(inventory) + "\n");
                    break;
                case 3:
                    Redirect.sendAppToGui(locationData(location));
                    break;
                case 4:
                    Redirect.sendAppToGui(showScenarioDetails(scenario, location));
                    break;
                default:
                    prompt.runPromptRed("error");
            }
        } else {
            Redirect.sendAppToGui("\nWhat do you need help with today?\n" +
                    "'1' - for a list of available commands.\n" +
                    "'2' - to see route plan.\n >>>");
            int input = Integer.parseInt(read.next());
            switch (input) {
                case 1:
                    Redirect.sendAppToGui(availableCMD(location));
                    break;
                case 2:
                    Redirect.sendAppToGui(showScenarioDetails(scenario, location));
                    break;
                default:
                    prompt.runPromptRed("error");
            }
        }
    }
    //provides the location data for option 3 using the location object that is passed in
    private static String locationData(Location location){
        String currentLocation = "Current Location: " + location.getLocationName();
        String description = "Description: " + location.getDescription();
        String exitN = "North= " + location.getNorth();
        String exitS = "South= " + location.getSouth();
        String exitE = "East= " + location.getEast();
        String exitW = "West= " + location.getWest();
        String officeMap = prompt.getMap("officeMap");
        return "*************************Location*************************\n" +
                currentLocation + '\n' +
                description + '\n' +
                officeMap + '\n' +
                exitN + '\n' +
                exitS + '\n' +
                exitE + '\n' +
                exitW + '\n' +
                "**********************************************************";
    }
    //displays all available commands for the user
    private static String availableCMD(Location location){
        if (inOffice.contains(location.getLocationName())) {
            return "********************Available Commands********************\n" +
                    ">> move/go + <direction> will change your current location\n" +
                    ">> explore + <specific place> will inspect specific parts of a location\n" +
                    ">> get/grab + <item name> will add the item to your backpack\n" +
                    ">> start driving will allow you to start driving if you have met the required conditions\n" +
                    ">> Pressing 'q' at anytime will exit you from the game\n" +
                    "**********************************************************";
        }else {
            return "********************Available Commands********************\n" +
                    ">> drive + <direction> will will change your current location\n" +
                    ">> deliver/pickup + load will pickup or delivery your load\n" +
                    ">> Pressing 'q' at anytime will exit you from the game\n" +
                    "**********************************************************";
        }
    }
    //shows the current inventory the player has acquired
    private static String showBackpack(Inventory inventory){
        List<String> backpack = inventory.getBackpack();
        return backpack.toString();
    }
    //shows details of the randomly generated scenario so the user knows where their start/pickup/delivery locations are
    private static String showScenarioDetails(ScenarioGenerator scenario,Location location) {
        if (inOffice.contains(location.getLocationName())) {
            return "***********************Game Details***********************\n" +
                    "Items needed to get on the road:\n" + scenario.getItemsNeeded() + "\n" +
                    "**********************************************************";
        } else {
            return "***********************Game Details***********************\n" +
                    "Home Office: " + scenario.getOfficeLocation() +
                    "\nPickup Location 1: " + scenario.getPickupLocation1() +
                    "\nDelivery Location 1: " + scenario.getDeliveryLocation1() +
                    "\nDelivery Location 1b: " + scenario.getDeliveryLocation1b() +
                    "\nPickup Location 2: " + scenario.getPickupLocation2() +
                    "\nDelivery Location 2: " + scenario.getDeliveryLocation2() +
                    "\nDelivery Location 2b: " + scenario.getDeliveryLocation2b() + "\n" +
                    "**********************************************************";
        }
    }
}

