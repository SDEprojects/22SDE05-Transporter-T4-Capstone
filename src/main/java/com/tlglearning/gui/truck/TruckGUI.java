package com.tlglearning.gui.truck;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TruckGUI {

    JFrame fr;
    JPanel truckPanel;
    TruckHandler handler;
    BufferedImage iconOrig;
    ImageIcon truckRight;
    ImageIcon truckLeft;
    ImageIcon truckDown;
    ImageIcon truckUp;
    JLabel truckLabel;


    public TruckGUI() throws IOException {
        handler = new TruckHandler(this);
        init();
    }

    private void init() throws IOException {
        fr = new JFrame("Transporter");
        fr.setLayout(null);
        fr.getContentPane().setBackground(Color.DARK_GRAY);
        truckPanel = new JPanel();
        truckPanel.setSize(305,305);
        ClassLoader cldr = this.getClass().getClassLoader();
        iconOrig = ImageIO.read(cldr.getResourceAsStream("game-truck.png"));;

        truckDown = new ImageIcon(
            new ImageIcon(rotateTruck(iconOrig, 90.d))
                .getImage()
                .getScaledInstance(62, 300, Image.SCALE_DEFAULT)
        );

        truckLeft = new ImageIcon(
            new ImageIcon(rotateTruck(iconOrig, 180.d))
                .getImage()
                .getScaledInstance(300, 62, Image.SCALE_DEFAULT)
        );

        truckUp = new ImageIcon(
            new ImageIcon(rotateTruck(iconOrig, 270.d))
                .getImage()
                .getScaledInstance(62, 300, Image.SCALE_DEFAULT)
        );

        truckRight = new ImageIcon(
                new ImageIcon(iconOrig)
                        .getImage()
                        .getScaledInstance(300, 62, Image.SCALE_DEFAULT)
        );

        truckLabel = new JLabel(truckUp);
        truckPanel.add(truckLabel);
        truckPanel.setLocation(350,275);
        truckPanel.setBackground(Color.blue);
//        truckPanel.setOpaque(false);
        fr.add(truckPanel);
        fr.setSize(800,800);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
        fr.addKeyListener(handler);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static BufferedImage rotateTruck(BufferedImage truck, Double deg) {
        double rad = Math.toRadians(deg);
        double sin = Math.abs(Math.sin(rad));
        double cos = Math.abs(Math.cos(rad));
        int w = (int) Math.round(truck.getWidth() * cos + truck.getHeight() * sin);
        int h = (int) Math.round(truck.getWidth() * sin + truck.getHeight() * cos);
        BufferedImage truckNew = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = truckNew.createGraphics();
        int x = (w - truck.getWidth()) / 2;
        int y = (h - truck.getHeight()) / 2;
        AffineTransform at = new AffineTransform();
        at.setToRotation(rad, x + (truck.getWidth() / 2), y + (truck.getHeight() / 2));
        at.translate(x, y);
        graphics2D.setTransform(at);
        graphics2D.drawImage(truck, 0, 0, null);
        graphics2D.dispose();
        return truckNew;
    }



}
