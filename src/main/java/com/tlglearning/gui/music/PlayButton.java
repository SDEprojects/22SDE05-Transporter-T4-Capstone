package com.tlglearning.gui.music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayButton extends JFrame implements ActionListener {


    JButton playButton;

    PlayButton() {
        ImageIcon icon = new ImageIcon("photos/play.png");
        playButton = new JButton();
        playButton.setOpaque(true);
        playButton.setBounds(200, 100, 75, 50);
        playButton.addActionListener(this);
        playButton.setText("PLAY");
        playButton.setFocusable(false);
        playButton.setIcon(icon);
        playButton.setForeground(Color.white);
        playButton.setBackground(Color.green);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this.add(playButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
//            SimpleAudioPlayer.radioPlayer();
        }
    }

    public static void main (String[]args){
        new PlayButton();
    }
}
