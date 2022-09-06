package com.tlglearning.util;

import java.util.*;

public class Menu {

    public static void helpMenu(Scanner read, Location location, Inventory inventory, ScenarioGenerator scenario){
        System.out.println("\nWhat do you need help with today?\n" +
                "'1' - for a list of available commands.\n" +
                "'2' - to see items in your inventory.\n" +
                "'3' - to see your current location and available exits.\n" +
                "'4' - to see game details and the items needed.\n >>>");
        int input = Integer.parseInt(read.next());
        switch (input) {
            case 1:
                System.out.println(availableCMD());
                break;
            case 2:
                System.out.println("\nYour backpack has the following items: \n" + showBackpack(inventory) + "\n");
                break;
            case 3:
                System.out.println(locationData(location));
                break;
            case 4:
                System.out.println(showScenarioDetails(scenario));
                break;
            default:
                System.out.println("Not a valid input");
        }
    }

    private static String locationData(Location location){
        String currentLocation = "Current Location: " + location.getLocationName();
        String exitN = "Exit North: " + location.getNorth();
        String exitS = "Exit South: " + location.getSouth();
        String exitE = "Exit East: " + location.getEast();
        String exitW = "Exit West: " + location.getWest();
        return "*************************Location*************************\n" +
                currentLocation + '\n' +
                exitN + '\n' +
                exitS + '\n' +
                exitE + '\n' +
                exitW + '\n' +
                "***********************************************************";
    }

    private static String availableCMD(){
        return "Available Commands:\n" +
                ">> move/go + <direction> will change your current location\n" +
                ">> explore + <specific place> will inspect specific parts of a location\n" +
                ">> get/pickup + <item name> will add the item to your backpack\n" +
                ">> Pressing 'q' at anytime will exit you from the game\n";
    }

    private static String showBackpack(Inventory inventory){
        List<String> backpack = inventory.getBackpack();
        return backpack.toString();
    }

    private static String showScenarioDetails(ScenarioGenerator scenario){
        return "Home Office: " + scenario.getOfficeLocation() +
                "\nPickup Location: " + scenario.getPickupLocation() +
                "\nDelivery Location " + scenario.getDeliveryLocation() +
                "\n\nItems needed to get on the road:\n" + scenario.getItemsNeeded() + "\n";
    }
}
