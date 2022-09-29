package com.tlglearning.gui.music;

// Java program to play an Audio
// file using Clip Object

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;

public class SimpleAudioPlayer {

    public static final JFrame RADIO_CONTAINER = new JFrame();
    public static final JPanel BUTTONS_CONTAINER = new JPanel();

    // to store current position
    Long currentFrame;
    public Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath = "music/hittheroadjack.wav";

    // constructor to initialize streams and clip
    public SimpleAudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(classloader.getResource("music/hittheroadjack.wav").getFile()));

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        FloatControl volControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volControl.setValue(-25.0f); // Reduce vol by 10 decibels

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.stop();
    }

    public static void radioPlayer() {
        try
        {
            filePath = "hittheroadjack.wav";
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();

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
        }
    }

    // Method to play the audio
    public void play() {

        //start the clip
        clip.start();
        status = "play";
    }

    // Method to pause the audio
    public void pause() {

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

        currentFrame = 0L;

        clip.stop();
        clip.close();
    }
}
