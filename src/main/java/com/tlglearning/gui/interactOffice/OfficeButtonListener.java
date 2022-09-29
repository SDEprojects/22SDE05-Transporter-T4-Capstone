package com.tlglearning.gui.interactOffice;

import com.tlglearning.middleware.commandGateObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import static com.tlglearning.client.TransporterClient.mainWindow;

//import static com.tlglearning.client.TransporterClient.mainWindow;

public class OfficeButtonListener implements MouseListener {

    ImageIcon orginalIcon;
    ImageIcon showItemIcon;
    JButton button;


    static HashMap<String, Object> DestinationsMap;


    public OfficeButtonListener(JButton button, ImageIcon orginal, ImageIcon showItem) {
        this.orginalIcon = orginal;
        this.orginalIcon.setDescription(orginal.getDescription());
        this.showItemIcon = showItem;
        this.showItemIcon.setDescription(showItem.getDescription());
        button.setIcon(orginal);

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
        if (!commandGateObject.getWait()) {
            String command = "";
            if (button.getIcon().equals(orginalIcon)) {
                command = orginalIcon.getDescription();
                button.setIcon(showItemIcon);


            } else {
                command = showItemIcon.getDescription();
                button.setIcon(orginalIcon);
                showItemIcon = orginalIcon;
            }

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

    public static String setResetButtons() {


        return "setResetButton valid";
    }

}
