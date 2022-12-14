package com.tlglearning.gui.interactbossoffice;

import com.tlglearning.middleware.commandGateObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.tlglearning.client.TransporterClient.mainWindow;
import static com.tlglearning.gui.MainWindow.sleep;

//import static com.tlglearning.client.TransporterClient.mainWindow;

public class BossOfficeButtonListener implements MouseListener {

    ImageIcon orginalIcon;
    ImageIcon showItemIcon;
    JButton button;

    private static boolean hasKey = false;


    public BossOfficeButtonListener(JButton button, ImageIcon orginal, ImageIcon showItem) {
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
        String command = "";
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
