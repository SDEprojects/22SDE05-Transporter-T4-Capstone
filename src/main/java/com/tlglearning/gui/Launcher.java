package com.tlglearning.gui;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {

        /**
         * SwingUtilities.invokeLater() - will run the piece of code on the AWT thread,
         * which lets you modify the GUI from other threads.
         */
        SwingUtilities.invokeLater(new Runnable() {

            /**
             * run() - Override creates a new instance of our main window class.
             */
            @Override
            public void run() {
                MainWindow main = new MainWindow();
                main.show();
            }

        });

    }

}
