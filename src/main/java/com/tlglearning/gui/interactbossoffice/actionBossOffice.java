package com.tlglearning.gui.interactbossoffice;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionBossOffice extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 1220;

    static int layerPaneHeight = layerPaneWidth * 9 / 16;

    public static JLayeredPane getPanel() {

        JLayeredPane layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        Image orginalImage = new ImageIcon(classloader.getResource("photos/actionbossoffice/bossOffice.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon orginalIcon = new ImageIcon(orginalImage);

        JLabel bossJLabel = new JLabel(orginalIcon);

        bossJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());

        bossJLabel.setLocation(0, 0);

        layeredPane.add(bossJLabel, 1);

        JButton buttonBossSleeping = getBossSleepingButton();
        JButton buttonDesk = getdeskButton();

        layeredPane.add(buttonBossSleeping, 0);
        layeredPane.add(buttonDesk, 0);
        layeredPane.setOpaque(true);

        layeredPane.setSize(layerPaneWidth, layerPaneHeight);
        return layeredPane;
    }


    public static JButton getBossSleepingButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        int x_loc = 800;
        int y_loc = 0;
        int width = 400;
        int height = 200;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionbossoffice/bossSleeping.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionbossoffice/bossSleeping.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore boss");
        cabinetlayerImageCrop.setDescription("Explore boss");
        button.addMouseListener(new BossOfficeButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc, y_loc);
        return button;

    }
    public static JButton getdeskButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 50;
        int y_loc = 0;
        int width = 400;
        int height = 400;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionbossoffice/bossdesk.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionbossoffice/actionbossdesk.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Desk");
        cabinetlayerImageCrop.setDescription("Get folder");
        button.addMouseListener(new BossOfficeButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);

        return button;
    }


}

