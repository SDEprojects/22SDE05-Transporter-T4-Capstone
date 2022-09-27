package com.tlglearning.gui.music;

// Java program to play an Audio
// file using Clip Object

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.*;
import javax.swing.*;

public class SimpleAudioPlayer {


    JButton stopButton;
    JButton playButton;
    JButton pauseButton;
    public static final JFrame RADIO_CONTAINER = new JFrame();
    public static final JPanel BUTTONS_CONTAINER = new JPanel();

    ArrayList<String>  radioSongs = new ArrayList<>();

    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath = "music/hittheroadjack.wav";

    // constructor to initialize streams and clip
    public SimpleAudioPlayer()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(classloader.getResource("music/hittheroadjack.wav").getFile()));

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void radioPlayer()  /** OLD MAIN METHOD HERE **//** OLD MAIN METHOD HERE **//** OLD MAIN METHOD HERE **//** OLD MAIN METHOD HERE **//** OLD MAIN METHOD HERE **//** OLD MAIN METHOD HERE **/
    {
        try
        {
            filePath = "hittheroadjack.wav";
            SimpleAudioPlayer audioPlayer =
                    new SimpleAudioPlayer();

            audioPlayer.play();
            Scanner sc = new Scanner(System.in);

            while (true)
            {
                System.out.println("1. play");
                System.out.println("2. pause");
                System.out.println("3. stop");
                System.out.println("4. volUp");
                System.out.println("5. volDown");
                System.out.println("6. changeStation");
                int c = sc.nextInt();
                audioPlayer.gotoChoice(c);
                if (c == 3)
                    break;
            }
            sc.close();
        }

        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }
    }

    // Work as the user enters his choice

    public void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (c)
        {
            case 1:
                play();
                break;
            case 2:
                pause();
                break;
            case 3:
                stop();
                break;
//            case 4:
//                volUp();
//                break;
//            case 5:
//                volDown();
//            case 6:
//                changeStation();
//                break;
        }
    }

    // Method to play the audio
    public void play() {

        //PLAY BUTTON
        ImageIcon icon2 = new ImageIcon("photos/play.png");
        playButton = new JButton();
        playButton.setOpaque(true);
        playButton.setBounds(200, 100, 75, 50);
        playButton.addActionListener(e -> SimpleAudioPlayer.radioPlayer());
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

        //start the clip
        clip.start();
        status = "play";
    }

    // Method to pause the audio
    public void pause() {

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
        RADIO_CONTAINER.setSize(700, 700);
        RADIO_CONTAINER.setVisible(true);
        RADIO_CONTAINER.add(BUTTONS_CONTAINER);
        BUTTONS_CONTAINER.add(stopButton);
        BUTTONS_CONTAINER.add(playButton);
        BUTTONS_CONTAINER.add(pauseButton);
        BUTTONS_CONTAINER.setVisible(true);



        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to stop the audio
    public void stop() {

        Scanner sc = new Scanner(System.in);
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
        currentFrame = 0L;

        clip.stop();
        clip.close();
    }
}



