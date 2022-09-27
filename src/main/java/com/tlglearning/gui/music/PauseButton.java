package com.tlglearning.gui.music;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PauseButton extends JFrame implements ActionListener {
    JButton pauseButton;
    String status;
    Long currentFrame;
    Clip clip;
    PauseButton(){

        ImageIcon icon = new ImageIcon("photos/pause.png");
        pauseButton = new JButton();
        pauseButton.setOpaque(true);
        pauseButton.setBounds(200, 100, 75, 50);
        pauseButton.addActionListener(this);
        pauseButton.setText("pause");
        pauseButton.setFocusable(false);
        pauseButton.setIcon(icon);
        pauseButton.setForeground(Color.white);
        pauseButton.setBackground(Color.blue);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.add(pauseButton);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
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
    public static void main (String[]args){
        new PauseButton();
    }
}







