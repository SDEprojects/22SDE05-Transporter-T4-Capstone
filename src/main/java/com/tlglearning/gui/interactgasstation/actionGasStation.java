package com.tlglearning.gui.interactgasstation;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionGasStation extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 1220;

    static int layerPaneHeight = layerPaneWidth * 9 / 16;

    public static JLayeredPane getPanel() {

        JLayeredPane layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        Image orginalImage = new ImageIcon(classloader.getResource("photos/actiongasstation/gas-station.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon orginalIcon = new ImageIcon(orginalImage);

        JLabel breakJLabel = new JLabel(orginalIcon);

        breakJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());

        breakJLabel.setLocation(0, 0);

        layeredPane.add(breakJLabel, 1);

         JButton buttonAttendant = getAttendantButton() ;
        JButton buttonSoda = getSodaButton() ;
        JButton buttonSnacks=getSnackButton() ;
//
//        layeredPane.add(buttonBossSleeping, 0);
        layeredPane.add(buttonSoda, 0);
        layeredPane.add(buttonAttendant, 0);
        layeredPane.add(buttonSnacks, 0);
        layeredPane.setOpaque(true);

        layeredPane.setSize(layerPaneWidth, layerPaneHeight);
        return layeredPane;
    }


    public static JButton getAttendantButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        int x_loc = 0;
        int y_loc = 150;
        int width = 400;
        int height = 700;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actiongasstation/gas-station-attendant.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actiongasstation/gas-station-attendant.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore attendant");
        cabinetlayerImageCrop.setDescription("Explore attendant");
        button.addMouseListener(new gasRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc, y_loc);


        return button;

    }
    public static JButton getSodaButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 900;
        int y_loc = 100;
        int width = 300;
        int height = 600;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actiongasstation/gas-station-drink-aisle.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actiongasstation/gas-station-soda.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore soda");
        cabinetlayerImageCrop.setDescription("Get soda");
        button.addMouseListener(new gasRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);

        return button;
    }

    public static JButton getSnackButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 600;
        int y_loc = 250;
        int width = 300;
        int height = 300;


        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actiongasstation/gas-station-snack-aisle.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actiongasstation//gas-station-snacks.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore snacks");
        cabinetlayerImageCrop.setDescription("Get snacks");
        button.addMouseListener(new gasRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc-17, y_loc-4);

        return button;
    }


}

