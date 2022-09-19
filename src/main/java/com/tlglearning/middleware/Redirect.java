package com.tlglearning.middleware;

import com.sun.tools.javac.Main;
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

    public static void sendPromptToGui(String messageToGui) {
        System.out.println("the message" + messageToGui);
        mainWindow.setPrompt(messageToGui);
        // Send Destination information to Gui. Destinations allow include button information.
        if (location != null) {
//            System.out.println(DestinationsMap.get(location.getLocationName()));
        }

//        mainWindow.setMap(messageToGui);
    }

    public static void sendTitleToGui(String title) {
        System.out.println("the title" + title);
        mainWindow.setTitle(title);
    }

    /**
     * sendsCommmandsToGui sends
     *
     * @param commands
     */

    public static void sendCommandsToGui(String commands) {
        System.out.println(commands);
    }

    /**
     * sendprintfAppToGui Allows for communication from App To Gui interface
     *
     * @param format
     * @param messageToGui
     */
    public static void sendprintfAppToGui(String format, String messageToGui) {
        System.out.printf(format, messageToGui);
        String prompt = String.format(format, messageToGui);
        mainWindow.setPrompt(prompt);
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

    public static void getPromptKey_DictLookUp_PromptToGui(String key) {
        String prompt = (String) GamePromptsMap.get(key);
        if (gameMapImages.contains(key)) {
            mainWindow.setMap(prompt);
        } else {
            mainWindow.setPrompt(prompt);
        }
    }

    public static void getPromptCyan_DictLookUp_PromptToGui(String key) {
        String prompt = (String) GamePromptsMap.get(key);
        mainWindow.setPrompt(prompt);
    }

    public static void getPromptRed_DictLookUp_PromptToGui(String key) {
        String prompt = (String) GamePromptsMap.get(key);
        mainWindow.setPrompt(prompt);
    }

    public static void getPromptWithLocation(String key, String nextLocation) {
        String prompt = (String) GamePromptsMap.get(key);
        mainWindow.setPrompt(prompt);
    }



    public static void setLocation(Location loc) {

        location = loc;
    }


}