package com.tlglearning.gui.button;


import com.tlglearning.gui.MainWindow;
import com.tlglearning.middleware.commandGateObject;
import com.tlglearning.util.Location;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    private static JPanel BUTTON_ACTION_CONTAINER = MainWindow.getActionButtonContainer();

    private static Location location;
    /**
     * displayName is text that shows on the button itself.
     */
    private String displayName = "";
    private static List<CommandButton> buttonList = new ArrayList<>();
    /**
     * Constructor of CommandButton
     *
     * @param displayName
     * @param command
     */

    public CommandButton(MainWindow ref, String displayName, String command) {
        buttonList.add(this);
        this.mainWindow = ref;
        setCommand(command.toLowerCase());
        setButton(displayName);
        setResetButtons();
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
     *
     * @param displayName
     */
    public void setButton(String displayName) {



        setDisplayName(displayName);
        this.button = new JButton(displayName);

        setUpButtonImage();

        if(!this.displayName.equals("E") && !this.displayName.equals("W") ){
            this.button.setEnabled(false);
            this.button.setVisible(false);
        }

        button.addActionListener(e ->
        {
            new SwingWorker<String, Object>() {
                public String doInBackground() throws InterruptedException {
                    mainWindow.wipe();
                    // create String for the label
                    // Sets commandGateObject command text  field to the user input command.
                    commandGateObject.setCommand(command);

                    // Sends confirmation boolean variable to tell the middleware that command is sent.
                    // Then command string is passed to Transport Application.
                    commandGateObject.setIsCommandSentFromGui(true);


                    Thread.sleep(1000);
                    setResetButtons();
                    return null;
                }
            }.execute();
        });
    }

    public static String setResetButtons() {

        //TODO ADD THESE BUTTON
        // BUTTON_ACTION_CONTAINER.add((new CommandButton(this, "EXPLORE","Explore")).getButton(),BorderLayout.WEST);
        // BUTTON_ACTION_CONTAINER.add((new CommandButton(this, "Get","Get")).getButton(),BorderLayout.EAST);

        if(location==null || CommandButton.buttonList.isEmpty()){
            return "setResetButton invalid";
        }

        for (CommandButton each : buttonList) {
            System.out.println(each.displayName);
            if (each.command.equals("go north")) {

                if (location.getNorth().equals("\"leads to nowhere\"")) {
                    each.button.setEnabled(false);
//                    each.button.setText("No Go");
                    each.button.setVisible(false);

                }else{
                    each.button.setEnabled(true);
                    each.button.setVisible(true);
                    each.button.setText(each.displayName);
                }
            } else if (each.command.equals("go south")) {
                if (location.getSouth().equals("\"leads to nowhere\"")) {
                    each.button.setEnabled(false);
                    each.button.setVisible(false);
                } else{
                    each.button.setEnabled(true);
                    each.button.setVisible(true);
                    each.button.setText(each.displayName);
                }
            } else if (each.command.equals("go east")) {
                if (location.getEast().equals("\"leads to nowhere\"")) {
                    each.button.setEnabled(false);
                    each.button.setVisible(false);
                }else{
                    each.button.setEnabled(true);
                    each.button.setVisible(true);
                    each.button.setText(each.displayName);
                }
            } else if (each.command.equals("go west")) {
                if (location.getWest().equals("\"leads to nowhere\"")) {
                    each.button.setEnabled(false);
                    each.button.setVisible(false);
                } else {
                    each.button.setEnabled(true);
                    each.button.setVisible(true);
                    each.button.setText(each.displayName);
                }
            } else if (each.command.equals("EXPLORE")) {
                if (location.getNorth().equals("\"leads to nowhere\"")) {
                    each.button.setEnabled(false);

                    each.button.setVisible(false);
                } else {
                    each.button.setEnabled(true);
                    each.button.setVisible(true);
                    each.button.setText(each.displayName);
                }
            }else if (each.command.equals("Get")) {
                if (location.getNorth().equals("\"leads to nowhere\"")) {
                    each.button.setEnabled(false);
                    each.button.setVisible(false);
                } else {
                    each.button.setEnabled(true);
                    each.button.setVisible(true);
                    each.button.setText(each.displayName);
                }
            }
        }
        return "setResetButton valid";
    }

    private void setUpButtonImage(){
        if(this.displayName.equalsIgnoreCase("N")){
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            ImageIcon NorthArrowIcon = new ImageIcon(
                    new ImageIcon(classloader.getResource("photos/northBTN.png"))
                            .getImage()
                            .getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            this.button.setBackground(Color.BLACK);
            this.button.setBorderPainted(false);
            this.button.setIcon(NorthArrowIcon );
        } else if(this.displayName.equalsIgnoreCase("S")){
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            ImageIcon NorthArrowIcon = new ImageIcon(
                    new ImageIcon(classloader.getResource("photos/southBTN.png"))
                            .getImage()
                            .getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            this.button.setBackground(Color.BLACK);
            this.button.setBorderPainted(false);
            this.button.setIcon(NorthArrowIcon );
        }else if(this.displayName.equalsIgnoreCase("W")){
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            ImageIcon NorthArrowIcon = new ImageIcon(
                    new ImageIcon(classloader.getResource("photos/westBTN.png"))
                            .getImage()
                            .getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            this.button.setBackground(Color.BLACK);
            this.button.setBorderPainted(false);
            this.button.setIcon(NorthArrowIcon );
        }else if(this.displayName.equalsIgnoreCase("E")){
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            ImageIcon NorthArrowIcon = new ImageIcon(
                    new ImageIcon(classloader.getResource("photos/eastBTN.png"))
                            .getImage()
                            .getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            this.button.setBackground(Color.BLACK);
            this.button.setBorderPainted(false);
            this.button.setIcon(NorthArrowIcon );
        }
    }
    public static void setLocation(Location loc) {
        location = loc;
    }
}
