package com.tlglearning.gui.button;


import com.tlglearning.gui.MainWindow;
import com.tlglearning.middleware.commandGateObject;

import javax.swing.*;

/**
 * CommandButton Generates buttons with a event actionlistener that sends
 * Command to MainWindow -> Redirect -> Transport Application
 */
public class CommandButton {

    /**
     * command: String that is sent as a command to applicaiton.
     */
    private String command;
    private JButton button;
    MainWindow mainWindow;

    /**
     * displayName is text that shows on the button itself.
     */
    private String displayName = "";

    /**
     * Constructor of CommandButton
     * @param displayName
     * @param command
     */
    public CommandButton(MainWindow ref, String displayName, String command) {
        this.mainWindow = ref;
        setCommand(command.toLowerCase());
        setButton(displayName);
    }

    /**
     * Setters and getters for CommandButton class.
     */
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public JButton getButton() {
        return button;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Action listener that once button is pressed sends command to MainWindow->Redirect-> Transport Application
     * @param displayName
     */
    public void setButton(String displayName) {
        setDisplayName(displayName);
        this.button = new JButton(displayName);

        button.addActionListener(e ->
        {
            new SwingWorker<String, Object>() {
                public String doInBackground() throws InterruptedException {
                    mainWindow.wipe();
                    //create String for the label
                    // Sets commandGateObject command text  field to the user input command.
                    commandGateObject.setCommand(command);

                    // Sends confirmation boolean variable to tell the middleware that command is sent.
                    // Then command string is passed to Transport Application.
                    commandGateObject.setIsCommandSentFromGui(true);
                    return null;
                }
            }.execute();
        });
    }


}
