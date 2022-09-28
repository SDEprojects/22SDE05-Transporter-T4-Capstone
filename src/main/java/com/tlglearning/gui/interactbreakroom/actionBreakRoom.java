package com.tlglearning.gui.interactbreakroom;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionBreakRoom extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 1200;

    static int layerPaneHeight = layerPaneWidth * 9 / 16;

    public static JLayeredPane getPanel() {

        JLayeredPane layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        Image orginalImage = new ImageIcon(classloader.getResource("photos/actionbreakroom/breakroom.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon orginalIcon = new ImageIcon(orginalImage);

        JLabel breakJLabel = new JLabel(orginalIcon);

        breakJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());

        breakJLabel.setLocation(0, 0);

        layeredPane.add(breakJLabel, 1);

        JButton buttonBossSleeping = getCoffeeButton();
        JButton buttonDesk = getFridgeButton();
        JButton buttonLocker=getStorageButton();

        layeredPane.add(buttonBossSleeping, 0);
        layeredPane.add(buttonDesk, 0);
        layeredPane.add(buttonLocker, 0);
        layeredPane.setOpaque(true);

        layeredPane.setSize(layerPaneWidth, layerPaneHeight);
        return layeredPane;
    }


    public static JButton getCoffeeButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        int x_loc = 5;
        int y_loc = 100;
        int width = 200;
        int height = 200;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionbreakroom/coffee.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionbreakroom/coffeeCup.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore coffee maker");
        cabinetlayerImageCrop.setDescription("Get Coffee");
        button.addMouseListener(new breakRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc, y_loc);


        return button;

    }
    public static JButton getFridgeButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 600;
        int y_loc = 0;
        int width = 300;
        int height = 300;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionbreakroom/kitchen.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionbreakroom/kitchen.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore refrigerator");
        cabinetlayerImageCrop.setDescription("Explore refrigerator");
        button.addMouseListener(new breakRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);

        return button;
    }

    public static JButton getStorageButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 10;
        int y_loc = 300;
        int width = 400;
        int height = 400;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionbreakroom/coffeestore.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionbreakroom/breakroomTherm.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc+200, y_loc, width, height);
        orginalIconCrop.setDescription("Explore locker");
        cabinetlayerImageCrop.setDescription("Get thermos");
        button.addMouseListener(new breakRoomButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);

        return button;
    }


}

