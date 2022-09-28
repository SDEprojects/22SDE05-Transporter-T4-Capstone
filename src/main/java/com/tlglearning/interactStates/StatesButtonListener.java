package com.tlglearning.interactStates;

import com.tlglearning.middleware.commandGateObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.tlglearning.client.TransporterClient.mainWindow;
import static com.tlglearning.interactStates.actionStates.setLocationImageBackGround;


public class StatesButtonListener implements MouseListener {


    ImageIcon insideTruckIcon;
    ImageIcon insideTruckIconAction;

    ImageIcon currentIcon;
    JButton button;



    public StatesButtonListener(JButton button, ImageIcon orginal, ImageIcon showItem) {
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


        if (insideTruckIcon.getDescription().equalsIgnoreCase("open map")) {
            this.currentIcon.setDescription(insideTruckIconAction.getDescription());
            button.setIcon(insideTruckIconAction);
            setLocationImageBackGround();
        } else if (insideTruckIcon.getDescription().equalsIgnoreCase("close map")) {
            this.currentIcon.setDescription(insideTruckIcon.getDescription());
            button.setIcon(insideTruckIcon);
            //TODO close Map

        }


       else if (insideTruckIcon.getDescription().equalsIgnoreCase("drive truck") ) {

            String command = insideTruckIcon.getDescription();

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
//        label.setIcon(active);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        label.setIcon(inactive);
    }







}
