package com.tlglearning.gui.compassaction;

import com.tlglearning.middleware.commandGateObject;
import com.tlglearning.util.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tlglearning.client.TransporterClient.mainWindow;
//
//import static com.tlglearning.client.TransporterClient.mainWindow;

public class ButtonListener implements MouseListener {
    int tile;
    static ImageIcon[] pics = Compass.compass_Active;
    static ImageIcon[] pics2 = Compass.compass;
    static List<JButton> buttonsList=new ArrayList<>();
    ImageIcon active;
    ImageIcon inactive;
    JButton label;
    Compass compass;
    static final List<String> notDrivingLocations= new ArrayList<String>() {
        {
            add("truck");
            add("warehouse");
            add("front office");
            add("boss office");
            add("break room");
            add("hr office");
            add("tech room");
            add("gas station");
        }
    };

    static HashMap<String, Object> DestinationsMap;
    private static Location location=null;

    public ButtonListener(JButton label, int tile) {
        buttonsList.add(label);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        this.tile = tile;
        this.label = label;
        switch (tile) {
            case 1:
                active = pics[0];
                active.setDescription("Go North");
                inactive = pics2[1];
                break;
            case 3:
                active = pics[1];
                active.setDescription("Go West");
                inactive = pics2[3];
                break;
            case 5:
                active = pics[2];
                active.setDescription("Go East");
                inactive = pics2[5];
                break;
            case 7:
                active = pics[3];
                active.setDescription("Go South");
                inactive = pics2[7];
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        label.setIcon(active);



    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!commandGateObject.getWait()) {
            label.setIcon(inactive);
            mainWindow.wipe();
            // create String for the label
            // Sets commandGateObject command text  field to the user input command.

            if (!notDrivingLocations.contains(location.getLocationName())) {
                String tempCommand = active.getDescription().toLowerCase().replaceAll("go", "drive");
                commandGateObject.setCommand(tempCommand);
            } else {
                commandGateObject.setCommand(active.getDescription());
            }


            // Sends confirmation boolean variable to tell the middleware that command is sent.
            // Then command string is passed to Transport Application.
            commandGateObject.setIsCommandSentFromGui(true);

            try {
                Thread.sleep(130);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            setResetButtons();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {


        label.setIcon(active);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


    }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setIcon(inactive);
    }



    public static String setResetButtons() {

        //TODO ADD THESE BUTTON
        // BUTTON_ACTION_CONTAINER.add((new CommandButton(this, "EXPLORE","Explore")).getButton(),BorderLayout.WEST);
        // BUTTON_ACTION_CONTAINER.add((new CommandButton(this, "Get","Get")).getButton(),BorderLayout.EAST);

        if (location == null || buttonsList.isEmpty() ) {
            return "setResetButton invalid";
        }


        for (JButton each : buttonsList) {
            if (((ImageIcon)each.getIcon()).getDescription().equalsIgnoreCase("go north")) {
                if (location.getNorth().equalsIgnoreCase("\"leads to nowhere\"")) {
                    each.setEnabled(false);
                    each.setVisible(false);

                } else {
                    each.setEnabled(true);
                    each.setVisible(true);
                }
            } else if (((ImageIcon)each.getIcon()).getDescription().equalsIgnoreCase("go south")) {
                if (location.getSouth().equalsIgnoreCase("\"leads to nowhere\"")) {
                    each.setEnabled(false);
                    each.setVisible(false);
                } else {
                    each.setEnabled(true);
                    each.setVisible(true);
                }
            } else if (((ImageIcon)each.getIcon()).getDescription().equalsIgnoreCase("go east")) {
                if (location.getEast().equalsIgnoreCase("\"leads to nowhere\"")) {
                    each.setEnabled(false);
                    each.setVisible(false);
                } else {
                    each.setEnabled(true);
                    each.setVisible(true);
                }
            } else if (((ImageIcon)each.getIcon()).getDescription().equalsIgnoreCase("go west")) {
                if (location.getWest().equalsIgnoreCase("\"leads to nowhere\"")) {
                    each.setEnabled(false);
                    each.setVisible(false);
                } else {
                    each.setEnabled(true);
                    each.setVisible(true);
                }
            }
        }
        return "setResetButton valid";
    }

    public static void setLocation(Location loc) {
        location = loc;
    }

    public static void setDestinationsMap(HashMap<String, Object> destinationsMap) {
        DestinationsMap = destinationsMap;
    }
}
