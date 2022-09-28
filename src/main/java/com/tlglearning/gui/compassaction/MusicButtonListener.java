package com.tlglearning.gui.compassaction;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MusicButtonListener implements MouseListener {

    static ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    JButton button;

    public MusicButtonListener(JButton button) {
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        // to be changed
        button.setIcon(null);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
