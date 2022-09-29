package com.tlglearning.middleware;



import com.tlglearning.gui.compassaction.ButtonListener;
import com.tlglearning.util.Location;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * commandObject
 * Assists in communication between GUI action listener and middle in reference to command being sent, or to wait for command.
 */
public class commandGateObject {
    static ExecutorService executor = Executors.newFixedThreadPool(2);
    static String currentLocation="";

    /**
     * commandObject field variables
     */

    // When command is sent by GUI command ready is set to true in class in MainWindow attached to action listener.
    static Boolean commandReady = false;
    static Boolean commandWait= true;

    // command is set to user input by MainWindow action listener button.
    static String command = "";
    static Location location;
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

    public static void runThreadUpdater(Location loc){
        location=loc;
        currentLocation=location.getLocationName();

        List<Callable<Void>> taskList = new ArrayList<Callable<Void>>();

        Callable<Void> callable1 = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                updateAll();
                return null;
            }
        };
        taskList.add(callable1);
        try {
            //start the threads and wait for them to finish
            executor.invokeAll(taskList);
        } catch (InterruptedException ie) {

        }
    }
    private static void updateAll() throws InterruptedException {

        while(true) {
            if(currentLocation!=location.getLocationName()){
                currentLocation=location.getLocationName();
                ButtonListener.setResetButtons();
            }
            Thread.sleep(100);

        }
    }


}
