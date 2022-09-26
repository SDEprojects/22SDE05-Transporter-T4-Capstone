package com.tlglearning.gui.interactwarehouse;

import com.tlglearning.util.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import static com.tlglearning.client.TransporterClient.mainWindow;

public class WarehouseButtonListener implements MouseListener {
    int tile;

//    static ImageIcon[] pics2 = actionWarehouse.compass;

    ImageIcon orginalIcon;
    ImageIcon showItemIcon;
    JButton button;


    static HashMap<String, Object> DestinationsMap;


    public WarehouseButtonListener(JButton button,ImageIcon orginal, ImageIcon showItem) {
        this.orginalIcon=orginal;
        this.showItemIcon=showItem;
        button.setIcon(orginal);

        this.button=button;

        // Button display settings
        button.setBackground(Color.BLACK);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(button.getIcon().equals(orginalIcon)){
            button.setIcon(showItemIcon);
        } else{
            button.setIcon(orginalIcon);
        }




    }

    @Override
    public void mouseReleased(MouseEvent e) {
//
//        label.setIcon(inactive);
//        mainWindow.wipe();
//        // create String for the label
//        // Sets commandGateObject command text  field to the user input command.
//        commandGateObject.setCommand(active.getDescription());
//
//        // Sends confirmation boolean variable to tell the middleware that command is sent.
//        // Then command string is passed to Transport Application.
//        commandGateObject.setIsCommandSentFromGui(true);
//
//
//        try {
//            Thread.sleep(130);
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }
//        setResetButtons();

    }

    @Override
    public void mouseEntered(MouseEvent e) {


//        label.setIcon(active);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    @Override
    public void mouseExited(MouseEvent e) {
//        label.setIcon(inactive);
    }



    public static String setResetButtons() {

//        //TODO ADD THESE BUTTON
//        // BUTTON_ACTION_CONTAINER.add((new CommandButton(this, "EXPLORE","Explore")).getButton(),BorderLayout.WEST);
//        // BUTTON_ACTION_CONTAINER.add((new CommandButton(this, "Get","Get")).getButton(),BorderLayout.EAST);
//
//        if (location == null || buttonsList.isEmpty()) {
//            return "setResetButton invalid";
//        }
//
//        for (JButton each : buttonsList) {
//            if (((ImageIcon)each.getIcon()).getDescription().equalsIgnoreCase("go north")) {
//                if (location.getNorth().equalsIgnoreCase("\"leads to nowhere\"")) {
//                    each.setEnabled(false);
//                    each.setVisible(false);
//
//                } else {
//                    each.setEnabled(true);
//                    each.setVisible(true);
//                }
//            } else if (((ImageIcon)each.getIcon()).getDescription().equalsIgnoreCase("go south")) {
//                if (location.getSouth().equalsIgnoreCase("\"leads to nowhere\"")) {
//                    each.setEnabled(false);
//                    each.setVisible(false);
//                } else {
//                    each.setEnabled(true);
//                    each.setVisible(true);
//                }
//            } else if (((ImageIcon)each.getIcon()).getDescription().equalsIgnoreCase("go east")) {
//                if (location.getEast().equalsIgnoreCase("\"leads to nowhere\"")) {
//                    each.setEnabled(false);
//                    each.setVisible(false);
//                } else {
//                    each.setEnabled(true);
//                    each.setVisible(true);
//                }
//            } else if (((ImageIcon)each.getIcon()).getDescription().equalsIgnoreCase("go west")) {
//                if (location.getWest().equalsIgnoreCase("\"leads to nowhere\"")) {
//                    each.setEnabled(false);
//                    each.setVisible(false);
//                } else {
//                    each.setEnabled(true);
//                    each.setVisible(true);
//                }
//            }
//        }
        return "setResetButton valid";
    }

}
