package com.tlglearning.gui.music;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
public class StopButton extends JFrame implements ActionListener {
    JButton stopButton;
    JButton playButton;
    Long currentFrame;
    Clip clip;
    StopButton(){

            ImageIcon icon = new ImageIcon("photos/stop.png");
            stopButton = new JButton();
            stopButton.setOpaque(true);
            stopButton.setBounds(200, 100, 75, 50);
            stopButton.addActionListener(this);
            stopButton.setText("STOP");
            stopButton.setFocusable(false);
            stopButton.setIcon(icon);
            stopButton.setForeground(Color.black);
            stopButton.setBackground(Color.red);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(500, 500);
            this.add(stopButton);
            this.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        Scanner sc = new Scanner(System.in);
        if (e.getSource() == stopButton) {
            sc.close();
        }
    }
    public static void main (String[]args){
        new StopButton();
    }
    public void play(){
        clip.start();
    }
}







