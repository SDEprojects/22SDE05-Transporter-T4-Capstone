package com.tlglearning.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Title extends JPanel implements ActionListener {
    Timer alphaTimer = new Timer(20, this);
    BufferedImage buffy;
    float alphaValue = 1f;

    public Title() {
        loadBufferedImaqe();
        alphaTimer.start();
    }

    public void loadBufferedImaqe() {
        buffy = null;
        try {
            buffy = ImageIO.read(getClass().getClassLoader().getResource("photos/title.png"));
        } catch (IOException e) {
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
        g2d.drawImage(buffy, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        alphaValue = alphaValue - 0.005f;
        if (alphaValue < 0) {
            alphaValue = 1f;
            alphaTimer.start();
        }
        repaint();
    }

    public void stop() {
        alphaValue = 1f;
        alphaTimer.stop();
        repaint();
    }
}
