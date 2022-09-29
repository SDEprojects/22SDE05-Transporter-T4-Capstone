package com.tlglearning.gui.music;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;

//import static com.tlglearning.client.TransporterClient.mainWindow;

public class radioButtonListener extends JFrame implements MouseListener {

    ImageIcon orginalIcon;
    ImageIcon showItemIcon;
    JButton musicButton;

    static SimpleAudioPlayer audioPlayer;
    static HashMap<String, Object> DestinationsMap;

    public radioButtonListener(JButton volumeButton, SimpleAudioPlayer audioPlayer) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.musicButton = musicButton;
        this.audioPlayer = audioPlayer;
    }


    public radioButtonListener(JButton musicButton, ImageIcon orginal, ImageIcon showItem, SimpleAudioPlayer audioPlayer) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.orginalIcon = orginal;
        this.showItemIcon = showItem;
        musicButton.setIcon(orginal);
        this.musicButton = musicButton;
        this.audioPlayer = audioPlayer;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(musicButton.getIcon().equals(orginalIcon)){
            audioPlayer.clip.start();
            musicButton.setIcon(showItemIcon);
        } else{
            audioPlayer.clip.stop();
            musicButton.setIcon(orginalIcon);
        }
        musicButton.setOpaque(false);
        musicButton.setContentAreaFilled(false);
        musicButton.setBackground(new Color(0,0,0,0));
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

//        label.setIcon(active);
        musicButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    @Override
    public void mouseExited(MouseEvent e) {
//        label.setIcon(inactive);
    }

    public static String setResetButtons() {


        return "setResetButton valid";
    }

}
