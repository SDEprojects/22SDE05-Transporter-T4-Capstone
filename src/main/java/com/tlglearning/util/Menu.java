package com.tlglearning.util;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    public static String helpMenu(Location location, Inventory inventory, ScenarioGenerator scenario){
        String currentLocation = "Current Location: " + location.getName();
        String exitN = "Exit North: " + location.getExit1To();
        String exitS = "Exit South: " + location.getExit2To();
        String exitE = "Exit East: " + location.getExit3To();
        String exitW = "Exit West: " + location.getExit4To();
        String availableCMD = "Available Commands:\n" +
                ">> move/go + <direction> will change your current location\n" +
                ">> explore + <specific place> will inspect specific parts of a location\n" +
                ">> get/pickup + <item name> will add the item to your backpack\n";
        List<String> backpack = inventory.getBackpack();
        String backpackStr = "\nYour backpack has the following items: \n" + backpack + "\n";
        String currentScenario = "Home Office: " + scenario.getOfficeLocation() +
               "\nPickup Location: " + scenario.getPickupLocation() +
               "\nDelivery Location " + scenario.getDeliveryLocation() +
               "\n\nItems needed to get on the road:\n" + scenario.getItemsNeeded() + "\n";

        return "*************************HELP MENU*************************\n" +
                currentLocation + '\n' +
                currentScenario + '\n' +
                exitN + '\n' +
                exitS + '\n' +
                exitE + '\n' +
                exitW + '\n' +
                backpackStr + '\n' +
                availableCMD +
                "***********************************************************";
    }
}
