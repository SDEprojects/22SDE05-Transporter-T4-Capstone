package com.tlglearning.gui.music;

import com.tlglearning.gui.MainWindow;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class RadioButton {
    private static MainWindow mainWindow;
    private static final ClassLoader c = Thread.currentThread().getContextClassLoader();
    private static final JPanel panel = new JPanel();
    private static final JButton musicButton = new JButton();
    private static final JButton volDownButton = new JButton();
    private static final JButton volUpButton = new JButton();
    private static final ImageIcon play = new ImageIcon(new ImageIcon (c.getResource("photos/play.png")).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    private static final ImageIcon stop = new ImageIcon(new ImageIcon(c.getResource("photos/stop1.png")).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    private static final ImageIcon volDown = new ImageIcon(new ImageIcon(c.getResource("photos/volume-down.png")).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    private static final ImageIcon volUp = new ImageIcon(new ImageIcon(c.getResource("photos/volume-up.png")).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    private static SimpleAudioPlayer audioPlayer;

    public RadioButton(MainWindow window) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        init(window);
    }

    public static void init(MainWindow window) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        setWindow(window);
        setUpRadio();
        panel.setSize(80,80);
        panel.setBounds(0,0, 260, 80);
        panel.setLocation(27, 560);
        panel.setOpaque(false);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(new GridLayout(1,3));

        musicButton.setBackground(Color.black);
        musicButton.setBorderPainted(false);
        musicButton.setIcon(play);
        musicButton.setFocusPainted(false);
        musicButton.addMouseListener(new radioButtonListener(musicButton, play, stop, audioPlayer));

        volUpButton.setIcon(volUp);
        volUpButton.setBackground(Color.black);
        volUpButton.setBorderPainted(false);
        volUpButton.setFocusPainted(false);
        volUpButton.addMouseListener(new radioButtonListener(volUpButton, audioPlayer){
            @Override
            public void mousePressed(MouseEvent e) {
                FloatControl volControl = (FloatControl) audioPlayer.clip.getControl(FloatControl.Type.MASTER_GAIN);
                try {
                    volControl.setValue(volControl.getValue()+5.0f); // Reduce vol by 10 decibels
                } catch (IllegalArgumentException ia) {
                    mainWindow.setPrompt("Max Volume Reach");
                }
                volUpButton.setOpaque(false);
                volUpButton.setContentAreaFilled(false);
                volUpButton.setBackground(new Color(0,0,0,0));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                volUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        volDownButton.setIcon(volDown);
        volDownButton.setBackground(Color.black);
        volDownButton.setBorderPainted(false);
        volDownButton.setFocusPainted(false);
        volDownButton.addMouseListener(new radioButtonListener(volDownButton, audioPlayer){
            @Override
            public void mousePressed(MouseEvent e) {
                FloatControl volControl = (FloatControl) audioPlayer.clip.getControl(FloatControl.Type.MASTER_GAIN);
                try {
                    volControl.setValue(volControl.getValue()-5.0f); // Reduce vol by 10 decibels
                } catch (IllegalArgumentException ia) {
                    mainWindow.setPrompt("Min Volume Reach");
                }
                volDownButton.setOpaque(false);
                volDownButton.setContentAreaFilled(false);
                volDownButton.setBackground(new Color(0,0,0,0));
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                volDownButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        panel.add(volDownButton);
        panel.add(musicButton);
        panel.add(volUpButton);
    }

    public static void setUpRadio() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        audioPlayer = new SimpleAudioPlayer();
    }

    private static void setWindow(MainWindow window) {
        mainWindow = window;
    }

    public JPanel getPanel() {
        return panel;
    }
}