package com.tlglearning.gui.interactwarehouse;

import com.tlglearning.middleware.commandGateObject;
import com.tlglearning.util.Location;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tlglearning.client.TransporterClient.mainWindow;

//import static com.tlglearning.client.TransporterClient.mainWindow;

public class WarehouseButtonListener implements MouseListener {

    ImageIcon orginalIcon;
    ImageIcon showItemIcon;
    JButton button;

    private static boolean hasKey=false;




    public WarehouseButtonListener(JButton button,ImageIcon orginal, ImageIcon showItem) {
        this.orginalIcon=orginal;
        this.orginalIcon.setDescription(orginal.getDescription());
        this.showItemIcon=showItem;
        this.showItemIcon.setDescription(showItem.getDescription());
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
        String command="";
        if (!commandGateObject.getWait()) {
            if (button.getIcon().equals(orginalIcon)) {
                if ((!orginalIcon.getDescription().equalsIgnoreCase("Explore Cabinet") || hasKey)) {
                    command = orginalIcon.getDescription();
                    button.setIcon(showItemIcon);
                }


            } else {
                command = showItemIcon.getDescription();
                button.setIcon(orginalIcon);
                showItemIcon = orginalIcon;
            }


            if (command.equalsIgnoreCase("Get Key")) {
                hasKey = true;
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
