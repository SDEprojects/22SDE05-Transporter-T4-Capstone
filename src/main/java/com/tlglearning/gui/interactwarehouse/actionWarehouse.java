package com.tlglearning.gui.interactwarehouse;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionWarehouse extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 1220;

    static int layerPaneHeight = layerPaneWidth * 9 / 16;

    public static JLayeredPane getPanel() {

        JLayeredPane layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        Image orginalImage = new ImageIcon(classloader.getResource("photos/actionwarehouse/warehouse.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon orginalIcon = new ImageIcon(orginalImage);

        JLabel wareJLabel = new JLabel(orginalIcon);

        wareJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());

        wareJLabel.setLocation(0, 0);

        layeredPane.add(wareJLabel, 1);

        JButton buttonCabinet = getCabinetButton();
        JButton buttonDesk = getdeskButton();
        JButton buttonCloset = getclosetButton();
        layeredPane.add(buttonCloset, 0);
        layeredPane.add(buttonCabinet, 0);
        layeredPane.add(buttonDesk, 0);
        layeredPane.setOpaque(true);

        layeredPane.setSize(layerPaneWidth, layerPaneHeight);
        return layeredPane;
    }


    public static JButton getCabinetButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        int x_loc = 0;
        int y_loc = 0;
        int width = 200;
        int height = 200;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionwarehouse/cabinet.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionwarehouse/actioncabinet.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Cabinet");
        cabinetlayerImageCrop.setDescription("Get logbook");
        button.addMouseListener(new WarehouseButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        return button;

    }
    public static JButton getdeskButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 0;
        int y_loc = 350;
        int width = 100;
        int height = 300;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionwarehouse/desk.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionwarehouse/actiondesk.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Desk");
        cabinetlayerImageCrop.setDescription("Get key");
        button.addMouseListener(new WarehouseButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);

        return button;
    }

    public static JButton getclosetButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionwarehouse/closet.png"));
        int x_loc = 900;
        int y_loc = 0;
        int width = 400;
        int height = 200;
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionwarehouse/tumbleWeed.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, 800, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Closet");
        cabinetlayerImageCrop.setDescription("Explore Closet");
        button.addMouseListener(new WarehouseButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc, y_loc);

        return button;
    }
}

