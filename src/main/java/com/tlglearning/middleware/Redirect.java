package com.tlglearning.middleware;

import com.sun.tools.javac.Main;
import com.tlglearning.gui.panelinterface.MapPanel;
import com.tlglearning.gui.panelinterface.PanelAbstractMethods;
import com.tlglearning.util.Location;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tlglearning.client.TransporterClient.mainWindow;

/*
    Messages are sent from app to Gui and Gui to app. This class will function as a middleware.
 */

public class Redirect {

    /**
     * sendPromptToGui redirects messages from App to Gui interface
     *
     * @param messageToGui
     */

    private static final String ANSI_BLUE = "\u001B[34m";

    private static final String ANSI_RESET = "\u001B[0m";

    private static final String CYAN = "\u001B[36m";

    private static final String RED = "\u001B[31m";

    private static final String WHITE = "\u001B[37m";
    static Location location;
    static HashMap<String, Object> DestinationsMap;
    static HashMap<String, Object> GamePromptsMap;



    static final List<String> gameMapImages = new ArrayList<String>() {
        {
            add("truck");
            add("warehouse");
            add("front office");
            add("boss office");
            add("break room");
            add("hr office");
            add("tech room");
        }
    };

    static final List<String> gamePhotoImages = new ArrayList<String>() {
        {
            add("truck");
//            add("front office");
//            add("boss office");
//            add("break room");
//            add("hr office");
//            add("tech room");
        }
    };



    public Redirect() {

    }

    public static void generateMaps() {
        ClassLoader cl = Main.class.getClassLoader();

        InputStream input = cl.getResourceAsStream("Destinations.yaml");

        Yaml yaml = new Yaml();

        DestinationsMap = yaml.load(input);

        input = cl.getResourceAsStream("GamePrompts.yaml");

        GamePromptsMap = yaml.load(input);

    }

    public static void sendPromptToGui(String identity,String messageToGui) {
        mainWindow.setPrompt("#"+identity+": "+messageToGui);
        // Send Destination information to Gui. Destinations allow include button information.
        if (location != null) {
//            System.out.println(DestinationsMap.get(location.getLocationName()));
        }

//        mainWindow.setMap(messageToGui);
    }

    public static void sendTitleToGui(String identity,String title) {
        mainWindow.setTitle(title);
    }

    /**
     * sendsHelpMenuToGui sends
     *
     * @param helpMenu
     */
    //Redirecting the help menu to the GUI
    public static void sendHelpMenuToGui (String identity,String helpMenu){
        mainWindow.setPrompt("#"+identity+": "+helpMenu);
    }

    public static void sendDescriptionToGui(String identity,String description){
        mainWindow.setPrompt("#"+identity+": "+description);
    }


    //TODO: THIS IS A LIST OF TODO'S FOR THE REDIRECT TO THE GUI________________________________________________________
    //TODO: CREATE A FUNCTION TO CHANGE COLOR OF TEXT
    public static void sendExploreTextToGui(String identity,String exploreText) {
        mainWindow.setPrompt("#"+identity+": "+exploreText);
    }
    public static void sendLocationImagesToGui(String identity,String key) {
//        mainWindow.appendOfficeMap("#"+identity+": "+(String) GamePromptsMap.get(key));
    }
    public static void sendItemTextToGui(String identity,String itemText) {
        mainWindow.setPrompt("#"+identity+": "+itemText);
    }


    /**
     * sendprintfAppToGui Allows for communication from App To Gui interface
     *
     * @param format
     * @param messageToGui
     */
    public static void sendprintfAppToGui(String identity,String format, String messageToGui) {
        System.out.printf(format, messageToGui);
        String prompt = String.format(format, messageToGui);
        mainWindow.setPrompt("#"+identity+": "+prompt);
    }

    /**
     * sendGuiToApp Allows for userInput from Gui to Application.
     * If command is not sent then wait, wait is represented by the while loop.
     *
     * @return messageToApp
     */
    public static String sendGuiCommandToApp() {

        while (!commandGateObject.isCommandSentFromGui()) {

            // Sleep slows down the while loop from processing.
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                System.out.println("An Exception occurred: " + e);
            }
        }
//        command=commandObject.getCommand();
        commandGateObject.setIsCommandSentFromGui(false);
        return commandGateObject.getCommand();
    }

    public Location getLocation() {

        return location;
    }

    public static void getPromptKey_DictLookUp_PromptToGui(String identity, String key) {
        MapPanel.updateChange(identity,key);
        System.out.println(identity+key);

    }


    public static void getPromptCyan_DictLookUp_PromptToGui(String identity,String key) {
        String prompt = (String) GamePromptsMap.get(key);
        mainWindow.setPrompt("#"+identity+": "+prompt);
    }

    public static void getPromptRed_DictLookUp_PromptToGui(String identity,String key) {
        String prompt = (String) GamePromptsMap.get(key);
        mainWindow.setPrompt("#"+identity+": "+prompt);
    }

    public static void getPromptWithLocation(String identity,String key, String nextLocation) {
        String prompt = (String) GamePromptsMap.get(key);
        mainWindow.setPrompt("#"+identity+": "+prompt);
    }



    public static void setLocation(Location loc) {

        location = loc;
        PanelAbstractMethods.setLocation(loc);
    }


}