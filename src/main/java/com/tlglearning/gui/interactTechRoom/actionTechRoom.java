package com.tlglearning.gui.interactTechRoom;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionTechRoom extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 1200;

    static int layerPaneHeight = layerPaneWidth * 9 / 16;

    public static JLayeredPane getPanel() {

        JLayeredPane layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        Image orginalImage = new ImageIcon(classloader.getResource("photos/actiontechroom/techRoom.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon orginalIcon = new ImageIcon(orginalImage);

        JLabel breakJLabel = new JLabel(orginalIcon);

        breakJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());

        breakJLabel.setLocation(0, 0);

        layeredPane.add(breakJLabel, 1);

        JButton buttonCabinet =  getCabinetButton();
        JButton buttonCloset= getClosetButton();
        JButton buttonLaptop=getLaptopButton();

        layeredPane.add(buttonCabinet , 0);
        layeredPane.add(buttonCloset, 0);
        layeredPane.add(buttonLaptop, 0);
        layeredPane.setOpaque(true);

        layeredPane.setSize(layerPaneWidth, layerPaneHeight);
        return layeredPane;
    }


    public static JButton getCabinetButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        int x_loc = 900;
        int y_loc = 300;
        int width = 300;
        int height = 400;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actiontechroom/techRoomCabinet.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actiontechroom/techRoomGPS.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Cabinet");
        cabinetlayerImageCrop.setDescription("Get GPS");
        button.addMouseListener(new techRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc, y_loc);


        return button;

    }
    public static JButton getClosetButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 350;
        int y_loc = 200;
        int width = 400;
        int height = 400;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actiontechroom/techRoomCloset.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actiontechroom/techRoomRadio.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Closet");
        cabinetlayerImageCrop.setDescription("Get Radio");
        button.addMouseListener(new techRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);

        return button;
    }

    public static JButton getLaptopButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 0;
        int y_loc = 300;
        int width = 300;
        int height = 300;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actiontechroom/techRoomLaptop.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actiontechroom/techRoomLaptop.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);

        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Laptop");
        cabinetlayerImageCrop.setDescription("Explore Laptop");
        button.addMouseListener(new techRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);

        return button;
    }


}

