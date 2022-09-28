package com.tlglearning.interactStates;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionStates extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 1220;

    static int layerPaneHeight = layerPaneWidth * 9 / 16;
    static JLayeredPane layeredPane;
    static JButton gpsButton;
    static JButton keyButton;
    static JLabel insideTruckJLabel;
    static JLabel statesJLabel;
    public static JLayeredPane getPanel() {

        layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        Image insideTruckImage = new ImageIcon(classloader.getResource("photos/StateImages/InsideTruck.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);
        Image statesImage = new ImageIcon(classloader.getResource("photos/StateImages/washington.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon insideTruckIcon = new ImageIcon(insideTruckImage);
        ImageIcon statesIcon = new ImageIcon(statesImage);

        insideTruckJLabel = new JLabel(insideTruckIcon);
        statesJLabel = new JLabel(statesIcon);

        insideTruckJLabel.setSize(insideTruckIcon.getIconWidth(), insideTruckIcon.getIconHeight());
        statesJLabel.setSize(statesIcon.getIconWidth(),statesIcon.getIconHeight());

        insideTruckJLabel.setLocation(0, 0);
        statesJLabel.setLocation(0,0);

        gpsButton = getGPSButton();
        keyButton =getKeyAction();
        layeredPane.add(insideTruckJLabel, 1);
        layeredPane.add(statesJLabel, 2);
        layeredPane.add(gpsButton, 0);
        layeredPane.add(keyButton, 0);

        layeredPane.setOpaque(true);

        layeredPane.setSize(layerPaneWidth, layerPaneHeight);
        return layeredPane;
    }


    public static JButton getGPSButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        int x_loc = 500;
        int y_loc = 300;
        int width = 100;
        int height = 200;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/StateImages/GPS.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/StateImages/GPS.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("open map");
        cabinetlayerImageCrop.setDescription("close map");
        button.addMouseListener(new StatesButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc, y_loc);


        return button;

    }

    /**
     * getKeyAction start the vehicle when your in the vehicle.
     * @return
     */
    public static JButton getKeyAction() {
        JButton button = new JButton();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 400;
        int y_loc = 500;
        int width = 100;
        int height = 100;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/StateImages/truckKey.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/StateImages/truckKey.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);

        orginalIconCrop.setDescription("drive truck");
        cabinetlayerImageCrop.setDescription("drive truck");
        button.addMouseListener(new StatesButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc, y_loc);

        return button;

    }


    public static void setLocationImageBackGround(){

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
      Image statesImage = new ImageIcon(classloader.getResource("photos/StateImages/Oregan.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);



        ImageIcon statesIcon = new ImageIcon(statesImage);


        JLabel statesJLabel = new JLabel(statesIcon);


        statesJLabel.setSize(statesIcon.getIconWidth(),statesIcon.getIconHeight());

        statesJLabel.setLocation(0,0);

        layeredPane.removeAll();
        layeredPane.add(insideTruckJLabel, 1);
        layeredPane.add(statesJLabel, 2);
        layeredPane.add(gpsButton, 0);
        layeredPane.add(keyButton, 0);
    }

}

