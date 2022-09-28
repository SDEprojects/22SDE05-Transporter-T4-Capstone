// PACKAGES
package com.tlglearning.gui.states;

// IMPORTS
import javax.swing.*;
import java.awt.*;

// CLASS
public class StatesPanel {

    // FIELDS
    static ClassLoader c = Thread.currentThread().getContextClassLoader();
    static ImageIcon stateAlabama = new ImageIcon (c.getResource("photos/DrivingStates/alabama.jpg"));
    static final JPanel panel = new JPanel();

    JButton stateButton;

    // CONSTRUCTOR
    StatesPanel() {

        init();
    }

    // INIT METHOD
    public static JPanel init() {


        JPanel panel = new JPanel();

        JButton stateButton = new JButton();

        panel.setSize(2500,2500);
        panel.setBounds(0,0, 250, 250);
        panel.setLocation(20, 20);
        panel.setOpaque(false);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(new BorderLayout(0,0));

        stateButton.setBackground(Color.black);
        stateButton.setBorderPainted(false);
        stateButton.setIcon(stateAlabama);
        stateButton.setFocusPainted(false);

        panel.add(stateButton);

        panel.revalidate();

        return panel;
    }

    // GETPANEL METHOD:
    public static JPanel getPanel() {

        return panel;
    }
}
