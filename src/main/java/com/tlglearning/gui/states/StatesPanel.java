// PACKAGES
package com.tlglearning.gui.states;

// IMPORTS
import javax.swing.*;
import java.awt.*;

// CLASS
public class StatesPanel {

    // FIELDS
    static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static String name;
    static ImageIcon icon;
    static final JPanel panel = new JPanel();


    static JButton stateButton;

    // CONSTRUCTOR
    public StatesPanel() {

        init();
    }

    // INIT METHOD
    public static void init() {

        stateButton  = new JButton();

        panel.setSize(250,250);
        panel.setBounds(0,0, 250, 250);
        panel.setLocation(20, 20);
        panel.setOpaque(false);

        stateButton.setBorderPainted(false);
        stateButton.setFocusPainted(false);

        panel.add(stateButton);

        panel.revalidate();
    }

    // GETPANEL METHOD:
    public static JPanel getPanel() {

        return panel;
    }

    public static void setIcon(String name) {
        name=name.strip().toLowerCase();

        icon = new ImageIcon (classLoader.getResource("photos/DrivingStates/rsz_" +name+ ".jpg"));
        stateButton.setBackground(Color.black);
        stateButton.setBorderPainted(false);
        stateButton.setIcon(icon);
        stateButton.setFocusPainted(false);
    }
}
