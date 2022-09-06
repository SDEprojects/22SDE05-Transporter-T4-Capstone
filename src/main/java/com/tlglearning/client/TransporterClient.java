package com.tlglearning.client;

import com.tlglearning.util.*;

import java.io.IOException;

public class TransporterClient {

    public static void main(String[] args) throws IOException {

        TitleScreen start = new TitleScreen();
        start.titleScreen();
        start.intro();

        InputHandling gameStart = new InputHandling();
        gameStart.gameStart();




    }
}
