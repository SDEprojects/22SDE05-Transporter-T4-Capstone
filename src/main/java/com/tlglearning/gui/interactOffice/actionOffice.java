package com.tlglearning.gui.interactOffice;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionOffice extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 1220;

    static int layerPaneHeight = layerPaneWidth * 9 / 16;

    public static JLayeredPane getPanel() {

        JLayeredPane layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        Image orginalImage = new ImageIcon(classloader.getResource("photos/actionfrontoffice/Office.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon orginalIcon = new ImageIcon(orginalImage);

        JLabel officeJLabel = new JLabel(orginalIcon);

        officeJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());

        officeJLabel.setLocation(0, 0);

        layeredPane.add(officeJLabel , 1);

        JButton buttonDesk = getDeskButton();
        JButton buttonBoard = getBoardButton();
        JButton buttonLocker = getLockerButton();
        layeredPane.add(buttonBoard , 0);
//        layeredPane.add(buttonCabinet, 0);
        layeredPane.add(buttonDesk, 0);
        layeredPane.add(buttonLocker, 0);
        layeredPane.setOpaque(true);

        layeredPane.setSize(layerPaneWidth, layerPaneHeight);
        return layeredPane;
    }


    public static JButton getDeskButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        int x_loc = 800;
        int y_loc = 0;
        int width = 400;
        int height = 350;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionfrontoffice/officedesk.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionfrontoffice/actiondesk.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore desk");
        cabinetlayerImageCrop.setDescription("Get truck key");
        button.addMouseListener(new OfficeButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc,y_loc);

        return button;

    }
    public static JButton getBoardButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 50;
        int y_loc = 350;
        int width = 150;
        int height = 150;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionfrontoffice/officeboard.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionfrontoffice/officeboard.png"));
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore board");
        cabinetlayerImageCrop.setDescription("Explore board");
        button.addMouseListener(new OfficeButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);


        return button;
    }

    public static JButton getLockerButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionfrontoffice/officelocker.png"));
        int x_loc = 450;
        int y_loc = 100;
        int width = 250;
        int height = 250;
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionfrontoffice/officelocker.png"));
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Locker");
        cabinetlayerImageCrop.setDescription("Explore locker");
        button.addMouseListener(new OfficeButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(0, 0);

        return button;
    }
}

