package com.tlglearning.gui.interactwarehouse;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionWarehouse extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 700;

    static int layerPaneHeight = layerPaneWidth*9/16;

    public static JLayeredPane getPanel() {

        JLayeredPane layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        JButton buttonCabinet = setActionButton(35, 90, 0, 0, "0", "1");

        Image orginalImage = new ImageIcon(classloader.getResource("photos/actionwarehouse/warehouse.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon orginalIcon=new ImageIcon(orginalImage);

        JLabel wareJLabel = new JLabel(orginalIcon);

        wareJLabel.setLocation(buttonCabinet.getX(), buttonCabinet.getY());
        wareJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());

        // Shift labels towards center of display.
        wareJLabel.setLocation(680,20);
        buttonCabinet.setLocation(680,20);
        layeredPane.add(wareJLabel, 1);
        layeredPane.add(buttonCabinet, 0);
        layeredPane.setOpaque(true);

        layeredPane.setSize(1500,1500);
        return layeredPane;
    }

    public static JButton setActionButton(int width, int height, int X_Location, int Y_Location, String orginalImagePath, String actionImagePath) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Image orginalImage = new ImageIcon(classloader.getResource("photos/actionwarehouse/cabinet.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon originalIcon = new ImageIcon(orginalImage);
        originalIcon.setDescription("Explore Cabinet");
        Image actionImage = new ImageIcon(classloader.getResource("photos/actionwarehouse/actioncabinet.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);

        ImageIcon actionIcon = new ImageIcon(actionImage);
        actionIcon.setDescription("Get Key");
        ImageIcon orginalIconCrop =new ImageIcon(classloader.getResource("photos/actionwarehouse/cabinet.png"));// originalIcon ;//cropImage.crop(orginalIcon, X_Location, X_Location, width, height);
        ImageIcon cabinetlayerImageCrop =new ImageIcon(classloader.getResource("photos/actionwarehouse/actioncabinet.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);

        JButton button = new JButton();
        button.setSize(width, height);

        button.addMouseListener(new WarehouseButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));

        return button;
    }


}

