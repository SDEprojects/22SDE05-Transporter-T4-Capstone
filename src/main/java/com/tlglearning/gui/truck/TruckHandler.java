package com.tlglearning.gui.truck;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TruckHandler implements KeyListener {
    TruckGUI truck;

    public TruckHandler(TruckGUI ref) {
        this.truck = ref;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        int x = truck.truckPanel.getX();
        int y = truck.truckPanel.getY();
        JPanel truckPanel = truck.truckPanel;
        switch(code){
            case 37:
                truck.truckLabel.setIcon(truck.truckLeft);
                truckPanel.setLocation(x - 10, y);
                break;
            case 38:
                truckPanel.setLocation(x, y - 10);
                truck.truckLabel.setIcon(truck.truckUp);
                break;
            case 39:
                truckPanel.setLocation(x + 10, y);
                truck.truckLabel.setIcon(truck.truckRight);

                break;
            case 40:
                truckPanel.setLocation(x, y + 10);
                truck.truckLabel.setIcon(truck.truckDown);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
