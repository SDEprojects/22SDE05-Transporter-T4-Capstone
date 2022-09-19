package com.tlglearning.middleware;

public class commandObject {
    static Boolean commandReady=false;
    static String command="";

    public static Boolean isCommandSentFromGui(){
        return commandReady;
    }

    public static void setIsCommandSentFromGui(boolean ready){
        commandReady=ready;
    }

    public static String getCommand(){
        return command;
    }

    public static void setCommand(String com){
        command=com;
    }
}
