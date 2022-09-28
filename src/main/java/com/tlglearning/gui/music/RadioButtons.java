package com.tlglearning.gui.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

public abstract class RadioButtons extends JFrame implements MouseListener {
    public static final JFrame RADIO_CONTAINER = new JFrame();
    public static final JPanel BUTTONS_CONTAINER = new JPanel();

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    Clip clip;
    JButton stopButton;
    JButton playButton;
    JButton pauseButton;
    Long currentFrame;

    String status;
    Scanner sc = new Scanner(System.in);

//    this.currentFrame = this.clip.getMicrosecondPosition();

    RadioButtons() {

        //STOP BUTTON
        ImageIcon icon = new ImageIcon("photos/stop.png");
        stopButton = new JButton();
        stopButton.setOpaque(true);
        stopButton.setBounds(200, 100, 75, 50);
        stopButton.addActionListener(e -> sc.close());
        stopButton.setText("STOP");
        stopButton.setFocusable(false);
        stopButton.setIcon(icon);
        stopButton.setForeground(Color.black);
        stopButton.setBackground(Color.red);
        RADIO_CONTAINER.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RADIO_CONTAINER.setLayout(null);
        RADIO_CONTAINER.setSize(500, 500);
        RADIO_CONTAINER.add(stopButton);
        RADIO_CONTAINER.setVisible(true);

        //PLAY BUTTON
        ImageIcon icon2 = new ImageIcon("photos/play.png");
        playButton = new JButton();
        playButton.setOpaque(true);
        playButton.setBounds(200, 100, 75, 50);
//        playButton.addActionListener(e -> SimpleAudioPlayer.radioPlayer());
        playButton.setText("PLAY");
        playButton.setFocusable(false);
        playButton.setIcon(icon2);
        playButton.setForeground(Color.white);
        playButton.setBackground(Color.green);
        RADIO_CONTAINER.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RADIO_CONTAINER.setLayout(null);
        RADIO_CONTAINER.setSize(500,500);
        RADIO_CONTAINER.add(playButton);
        RADIO_CONTAINER.setVisible(true);

        //PAUSE BUTTON
        ImageIcon icon3 = new ImageIcon("photos/pause.png");
        pauseButton = new JButton();
        pauseButton.setOpaque(true);
        pauseButton.setBounds(200, 100, 75, 50);
        pauseButton.addActionListener(e -> clip.stop());
        pauseButton.setText("pause");
        pauseButton.setFocusable(false);
        pauseButton.setIcon(icon3);
        pauseButton.setForeground(Color.white);
        pauseButton.setBackground(Color.blue);
        RADIO_CONTAINER.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RADIO_CONTAINER.setLayout(null);
        RADIO_CONTAINER.setSize(500, 500);
        RADIO_CONTAINER.add(pauseButton);
        RADIO_CONTAINER.setVisible(true);

        BUTTONS_CONTAINER.setVisible(true);

        RADIO_CONTAINER.setSize(700, 700);

        RADIO_CONTAINER.setVisible(true);

        RADIO_CONTAINER.add(BUTTONS_CONTAINER);

        BUTTONS_CONTAINER.add(stopButton);

        BUTTONS_CONTAINER.add(playButton);

        BUTTONS_CONTAINER.add(pauseButton);

        stopButton = new JButton(new AbstractAction("stopButton") {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame = 0L;
                clip.stop();
                clip.close();
            }
        });

        playButton = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clip.start();
            }
        });

        pauseButton = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status.equals("paused"))
                {
                    System.out.println("audio is already paused");
                    return;
                }
                clip.stop();
            }

        });

    }

    public static void main(String[] args) {

    }

}
