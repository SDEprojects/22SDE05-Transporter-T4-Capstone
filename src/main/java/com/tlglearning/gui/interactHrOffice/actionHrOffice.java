package com.tlglearning.gui.interactHrOffice;

import com.tlglearning.gui.crop.Crop;

import javax.swing.*;
import java.awt.*;


public class actionHrOffice extends JFrame {
    static Crop cropImage = new Crop();

    static int layerPaneWidth = 1200;

    static int layerPaneHeight = layerPaneWidth * 9 / 16;

    public static JLayeredPane getPanel() {

        JLayeredPane layeredPane = new JLayeredPane();


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Retrieves image crop
        Image orginalImage = new ImageIcon(classloader.getResource("photos/actionHROffice/HROffice.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);


        ImageIcon orginalIcon = new ImageIcon(orginalImage);

        JLabel breakJLabel = new JLabel(orginalIcon);

        breakJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());

        breakJLabel.setLocation(0, 0);

        layeredPane.add(breakJLabel, 1);

        JButton buttonTrash =  getTrashButton() ;
        JButton buttonBear = getTeddyBearButton();
        JButton buttonComplaint=getComplaintButton();

        layeredPane.add(buttonTrash , 0);
        layeredPane.add(buttonBear, 0);
        layeredPane.add(buttonComplaint, 0);
        layeredPane.setOpaque(true);

        layeredPane.setSize(layerPaneWidth, layerPaneHeight);
        return layeredPane;
    }


    public static JButton getTrashButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        int x_loc = 0;
        int y_loc = 0;
        int width = 200;
        int height = 150;

        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionHROffice/wastebin.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionHROffice/fired.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore trash can");
        cabinetlayerImageCrop.setDescription("Explore trash can");
        button.addMouseListener(new HROfficeButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());
        button.setLocation(x_loc, y_loc);


        return button;

    }
    public static JButton getTeddyBearButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 800;
        int y_loc = 150;
        int width = 400;
        int height = 400;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionHROffice/teddy.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionHROffice/teddy.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Teddy Bear");
        cabinetlayerImageCrop.setDescription("Explore Teddy Bear");
        button.addMouseListener(new HROfficeButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc-10, y_loc-5);

        return button;
    }

    public static JButton getComplaintButton() {
        JButton button = new JButton();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        int x_loc = 1000;
        int y_loc = 0;
        int width = 200;
        int height = 200;
        ImageIcon orginalIconCrop = new ImageIcon(classloader.getResource("photos/actionHROffice/complaints.png"));
        orginalIconCrop = cropImage.crop(orginalIconCrop, x_loc, y_loc, width, height);
        ImageIcon cabinetlayerImageCrop = new ImageIcon(classloader.getResource("photos/actionHROffice/complaints.png"));// actionIcon;//cropImage.crop(actionIcon, 0, 0, width, height);
        cabinetlayerImageCrop = cropImage.crop(cabinetlayerImageCrop, x_loc, y_loc, width, height);
        orginalIconCrop.setDescription("Explore Complaint Box");
        cabinetlayerImageCrop.setDescription("Explore Complaint Box");
        button.addMouseListener(new HROfficeButtonListener(button, orginalIconCrop, cabinetlayerImageCrop));
        button.setSize(orginalIconCrop.getIconWidth(), orginalIconCrop.getIconHeight());

        button.setLocation(x_loc, y_loc);

        return button;
    }


}

