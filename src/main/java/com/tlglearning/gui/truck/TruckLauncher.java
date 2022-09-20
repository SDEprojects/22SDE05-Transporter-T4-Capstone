package com.tlglearning.gui.truck;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TruckLauncher {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                try {
                    TruckGUI truck = new TruckGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
