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




}
