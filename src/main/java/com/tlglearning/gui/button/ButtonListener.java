package com.tlglearning.gui.button;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonListener implements MouseListener {
    int tile;
    static ImageIcon[] pics = Compass.compass_Active;
    static ImageIcon[] pics2 = Compass.compass;
    ImageIcon active;
    ImageIcon inactive;
    JLabel label;
    Compass compass;

    public ButtonListener(JLabel label, int tile) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        this.tile = tile;
        this.label = label;
        switch(tile){
            case 1:
                active = pics[0];
                inactive = pics2[1];
                break;
            case 3:
                active = pics[1];
                inactive = pics2[3];
                break;
            case 5:
                active = pics[2];
                inactive = pics2[5];
                break;
            case 7:
                active = pics[3];
                inactive = pics2[7];
                break;
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        label.setIcon(active);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        label.setIcon(inactive);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label.setIcon(active);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setIcon(inactive);
    }
}
