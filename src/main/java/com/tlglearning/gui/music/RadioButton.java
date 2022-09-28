package com.tlglearning.gui.music;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RadioButton {

    static ClassLoader c = Thread.currentThread().getContextClassLoader();
    static final JPanel panel = new JPanel();
    static JButton musicButton = new JButton("PLAY");
    static JButton stopButton = new JButton("STOP");
    static ImageIcon play = new ImageIcon (c.getResource("photos/play.png"));
    static ImageIcon stop = new ImageIcon(c.getResource("photos/stop1.png"));
    public RadioButton() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        init();
    }

    public static JPanel init() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        panel.setSize(80,80);
        panel.setBounds(0,0, 80, 80);
        panel.setLocation(125, 560);
        panel.setOpaque(false);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(new BorderLayout(0,0));

        musicButton.setBackground(Color.black);
        musicButton.setBorderPainted(false);
        musicButton.setIcon(play);
        musicButton.setFocusPainted(false);
        musicButton.addMouseListener(new radioButtonListener(musicButton, play, stop));

        stopButton.setIcon(stop);

        panel.add(musicButton);

        panel.revalidate();

        return panel;
    }

    public JPanel getPanel() {
        return panel;
    }
}
