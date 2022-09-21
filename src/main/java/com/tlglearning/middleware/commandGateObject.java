package com.tlglearning.middleware;


import com.sun.tools.javac.Main;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * commandObject
 * Assists in communication between GUI action listener and middle in reference to command being sent, or to wait for command.
 */
public class commandGateObject {

    /**
     * commandObject field variables
     */

    // When command is sent by GUI command ready is set to true in class in MainWindow attached to action listener.
    static Boolean commandReady = false;

    // command is set to user input by MainWindow action listener button.
    static String command = "";

    static final List<String> commandHistory=new ArrayList<>();


    /**
     * isCommandSentFromGui Lets redirect know when to sent string command to app.
     *
     * @return commandReady
     */
    public static Boolean isCommandSentFromGui() {
        return commandReady;
    }

    /**
     * setIsCommandSentFromGui sets whether a command has been sent or false if not.
     * Used to tell whether command string is ready to be read as a new command. Else wait.
     *
     * @param ready
     */
    public static void setIsCommandSentFromGui(boolean ready) {
        commandReady = ready;
    }

    /**
     * getCommand
     *
     * @return command
     */
    public static String getCommand() {
        return command;
    }

    /**
     * setCommand sets the command string in class variable to be then retrieved by middleware and sent to App
     * for parsing and processing.
     *
     * @param com
     */
    public static void setCommand(String com) {

        commandHistory.add(com);
        command = com;
    }


    // saveGame saves the game
    //Todo text save game and add additional information including game time
    // money on hand and scenerio of game.
    public static void saveGame() throws FileNotFoundException {

        ClassLoader cl = Main.class.getClassLoader();
        URL saveURL = cl.getResource("savefile/saveFile.yaml");
        PrintWriter writer = new PrintWriter(new File(String.valueOf(saveURL)));
        Yaml yaml = new Yaml();
        yaml.dump(commandHistory, writer);

    }

    // TODO add additional functionality that sends loaded game command into application.

    public static void loadGame() throws FileNotFoundException {
        ClassLoader cl = Main.class.getClassLoader();

        InputStream input = cl.getResourceAsStream("savefile/saveFile.yaml");

        Yaml yaml = new Yaml();

        HashMap<String,Object> DestinationsMap = yaml.load(input);
        System.out.println(DestinationsMap);





    }
}
