package com.tlglearning.gui.button;

import javax.swing.*;
import java.awt.*;
public class Compass {
    static ImageIcon[] compass = new ImageIcon[9];
    static ImageIcon[] compass_Active = new ImageIcon[4];
    static int counter1 = 0;

    public static JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setSize(360,360);
        panel.setBounds(0,0, 360,360);

        panel.setBackground(Color.black);
        panel.setLayout(new GridLayout(3,3));
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        //tile2
        for (int i = 0; i < 9; i++) {
            int name = i + 1;
            System.out.println(name);

            ImageIcon icon = new ImageIcon(classloader.getResource("photos/buttons/"+name+".png"));

            icon.setDescription("No_Go");
            compass[i] = icon;
        }

        compass[1].setDescription("Go North");
        compass[3].setDescription("Go West");
        compass[5].setDescription("Go East");
        compass[7].setDescription("Go South");
        //tile2_ACTIVE
        for (int i = 2; i < 9; i+=2) {
            int name = i;
            compass_Active[counter1] = new ImageIcon(classloader.getResource("photos/buttons/"+name+"_ACTIVE.png"));
            counter1++;
        }

        for (int i = 0; i < compass.length; i++) {
            if (i % 2 == 0) {
                panel.add(new JLabel(compass[i]));
            } else {
                JButton buttonLabel = new JButton(compass[i]);
                buttonLabel.addMouseListener(new ButtonListener(buttonLabel, i));

                buttonLabel.setBackground(Color.BLACK);
                buttonLabel.setBorderPainted(false);
                buttonLabel.setContentAreaFilled(false);
                buttonLabel.setFocusPainted(false);


                if (!compass[i].getDescription().equalsIgnoreCase("go east")) {
                    buttonLabel.setEnabled(false);
                    buttonLabel.setVisible(false);
                }
                panel.add(buttonLabel);
            }
        }


        return panel;
    }
}