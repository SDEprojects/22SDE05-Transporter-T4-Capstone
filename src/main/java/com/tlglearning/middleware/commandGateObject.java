package com.tlglearning.middleware;



import java.util.ArrayList;

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
    static Boolean commandWait= true;

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

        if(commandReady ){
            return command;
        }
        return "";
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
public static void setWait(boolean comWait){

        commandWait=comWait;
}

    public static boolean getWait(){
        return commandWait;

    }
}
