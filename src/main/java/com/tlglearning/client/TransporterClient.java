package com.tlglearning.client;

import com.tlglearning.gui.MainWindow;
import com.tlglearning.gui.music.SimpleAudioPlayer;
import com.tlglearning.middleware.Redirect;
import com.tlglearning.util.InputHandling;
import com.tlglearning.util.TitleScreen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TransporterClient {
    public static MainWindow mainWindow;

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        Redirect.generateMaps();

        SwingUtilities.invokeAndWait(new Runnable () {
            /**
             * run() - Override creates a new instance of our main window class.
             */
            @Override
            public void run() {
                try {
                    mainWindow = new MainWindow();
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //shows the title screen and introduction
        TitleScreen start = new TitleScreen();

        //initializes input handling and calls the gameStart method to start new game or quit
        InputHandling gameStart = new InputHandling();

        start.titleScreen();

        start.intro();

        try {
            gameStart.gameStart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
