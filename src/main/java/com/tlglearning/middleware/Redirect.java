package com.tlglearning.middleware;
import com.sun.tools.javac.Main;
import com.tlglearning.util.Location;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;  // Import the Scanner class

import static com.tlglearning.client.TransporterClient.mainWindow;

/*
    Messages are sent from app to Gui and Gui to app. This class will function as a middleware.
 */

public class Redirect {

    /**
     * sendAppToGui redirects messages from App to Gui interface
     * @param messageToGui
     */

    static Location location;

    static HashMap<String, Object> DestinationsMap;
    static HashMap<String, Object> GamePromptsMap;

    public Redirect() {

    }

    public static void generateMaps(){
        ClassLoader cl = Main.class.getClassLoader();

        InputStream input = cl.getResourceAsStream("Destinations.yaml");

        Yaml yaml = new Yaml();

        DestinationsMap = yaml.load(input);

        input = cl.getResourceAsStream("GamePrompts.yaml");

        GamePromptsMap = yaml.load(input);

    }

    public static void sendAppToGui(String messageToGui) {
        System.out.println("the message" + messageToGui);

        // Send Destination information to Gui. Destinations allow include button information.
        if(location!=null){
//            System.out.println(DestinationsMap.get(location.getLocationName()));
        }

        mainWindow.setMap(messageToGui);
    }

    public static void sendTitleToGui(String title) {
        System.out.println("the title" + title);
        mainWindow.setTitle(title);
    }
    /**
     * sendsCommmandsToGui sends
     * @param commands
     */
    public static void sendCommandsToGui(String commands){

        System.out.println(commands);

    }

    /**
     * sendprintfAppToGui Allows for communication from App To Gui interface
     * @param format
     * @param messageToGui
     */
    public static void sendprintfAppToGui(String format,String messageToGui){

        System.out.printf(format,messageToGui);
    }

    /**
     * sendGuiToApp Allows for userInput from Gui to Application
     * @return messageToApp
     */
    public static String sendGuiToApp(){

        Scanner scanner = new Scanner(System.in);
        String messageToApp = scanner.nextLine();

        return messageToApp;
    }

    public Location getLocation() {

        return location;
    }

    public static void getPromptKey_DictLookUp_PromptToGui(String key){
        String prompt = (String) GamePromptsMap.get(key);
        //        mainWindow.setPrompt(prompt);
    }
    public static void getPromptCyan_DictLookUp_PromptToGui(String key){
        String prompt = (String) GamePromptsMap.get(key);
        //        mainWindow.setPrompt(prompt);
    }

    public static void getPromptRed_DictLookUp_PromptToGui(String key) {
        String prompt = (String) GamePromptsMap.get(key);
        //        mainWindow.setPrompt(prompt);
    }

    public static void getPromptWithLocation(String key, String nextLocation) {
        String prompt = (String) GamePromptsMap.get(key);
        //        mainWindow.setPrompt(prompt);
    }

    public static void getMapKey_DictLookUp_PromptToGui(String key) {
        String prompt = (String) GamePromptsMap.get(key);
        //        mainWindow.setPrompt(prompt);
    }




    /**
     * getMenu Sends instructions to GUI to create buttons and then sends commends to App after button is pushed.
     */
    public static void getMenu() {


//        if (inOffice.contains(location.getLocationName())) {
//            return "********************Available Commands********************\n" +
//                    ">> move/go + <direction> will change your current location\n" +
//                    ">> explore + <specific place> will inspect specific parts of a location\n" +
//                    ">> get/grab + <item name> will add the item to your backpack\n" +
//                    ">> start driving will allow you to start driving if you have met the required conditions\n" +
//                    ">> Pressing 'q' at anytime will exit you from the game\n" +
//                    "**********************************************************";
//        }else {
//            return "********************Available Commands********************\n" +
//                    ">> drive + <direction> will will change your current location\n" +
//                    ">> deliver/pickup + load will pickup or delivery your load\n" +
//                    ">> Pressing 'q' at anytime will exit you from the game\n" +
//                    "**********************************************************";
//        }

//        if (inOffice.contains(location.getLocationName())) {
//            return "***********************Game Details***********************\n" +
//                    "Items needed to get on the road:\n" + scenario.getItemsNeeded() + "\n" +
//                    "**********************************************************";
//        } else {
//            return "***********************Game Details***********************\n" +
//                    "Home Office: " + scenario.getOfficeLocation() +
//                    "\nPickup Location 1: " + scenario.getPickupLocation1() +
//                    "\nDelivery Location 1: " + scenario.getDeliveryLocation1() +
//                    "\nDelivery Location 1b: " + scenario.getDeliveryLocation1b() +
//                    "\nPickup Location 2: " + scenario.getPickupLocation2() +
//                    "\nDelivery Location 2: " + scenario.getDeliveryLocation2() +
//                    "\nDelivery Location 2b: " + scenario.getDeliveryLocation2b() + "\n" +
//                    "**********************************************************";
//        }

//        return "*************************Location*************************\n" +
//                currentLocation + '\n' +
//                description + '\n' +
//                officeMap + '\n' +
//                exitN + '\n' +
//                exitS + '\n' +
//                exitE + '\n' +
//                exitW + '\n' +
//                "**********************************************************";
    }

    public static void setLocation(Location loc) {

        location = loc;
    }
}
