package com.tlglearning.interactStates;

import com.tlglearning.gui.states.StatesPanel;
import com.tlglearning.middleware.commandGateObject;
import com.tlglearning.util.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import static com.tlglearning.client.TransporterClient.mainWindow;
import static com.tlglearning.interactStates.actionStates.changeMapDisplay;





public class StatesButtonListener implements MouseListener {


    ImageIcon insideTruckIcon;
    ImageIcon insideTruckIconAction;

    ImageIcon currentIcon;
    JButton button;
    private static JPanel statesMapPanelDisplay;
    private static Location location;

    StatesPanel statePanel;

    boolean onTheRoad=false;




    public StatesButtonListener(JButton button, ImageIcon orginal, ImageIcon showItem, JPanel mapDisplay, StatesPanel stPanel) {
        statePanel=stPanel;
        statesMapPanelDisplay = mapDisplay;
        this.insideTruckIcon = orginal;
        this.insideTruckIcon.setDescription(orginal.getDescription());

        this.insideTruckIconAction = showItem;
        this.insideTruckIconAction.setDescription(showItem.getDescription());

        this.currentIcon = orginal;
        this.currentIcon.setDescription(orginal.getDescription());
        button.setIcon(currentIcon);

        this.button = button;

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

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(currentIcon.getDescription());



    if (currentIcon.getDescription().equalsIgnoreCase("open map")) {

        try {

            statePanel.setIcon(location.getLocationName());

            currentIcon= insideTruckIconAction;

            changeMapDisplay(true);


        }catch(NullPointerException ex){

        }


            //TODO close Map


        } else if (currentIcon.getDescription().equalsIgnoreCase("close map")) {
            currentIcon= insideTruckIcon;
            changeMapDisplay(false);

            //TODO close Map

        } else if (currentIcon.getDescription().equalsIgnoreCase("start drive")) {

            String command = currentIcon.getDescription();

            mainWindow.wipe();

            commandGateObject.setCommand(command);

            commandGateObject.setIsCommandSentFromGui(true);



        }


    }


    @Override
    public void mouseReleased(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void setLocationo(Location loc){
        location=loc;

    }


}
