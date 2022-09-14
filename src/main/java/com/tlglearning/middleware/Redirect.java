package com.tlglearning.middleware;
import java.util.Scanner;  // Import the Scanner class

/*
    Messages are sent from app to Gui and Gui to app. This class will function as a middleware.
 */

public class Redirect {

    /**
     * sendAppToGui redirects messages from App to Gui interface
     * @param output
     */
    public static void sendAppToGui(String output){

        System.out.println(output);

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

        return messageToApp ;
    }


}
