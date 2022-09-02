package com.tlglearning.client;

import com.tlglearning.util.GameState;
import com.tlglearning.util.TitleScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.tlglearning.util.GameState.runCommand;

public class TransporterClient {

    public static void main(String[] args) throws IOException {
        TitleScreen start = new TitleScreen();
        start.titleScreen();

        GameState gameStart = new GameState();
        gameStart.gameInput();

        BufferedReader in;
        String userInput;
        //get users input and go through run command
        in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("\nEnter command >>> ");
            userInput = in.readLine();
            runCommand(userInput);
        } while (!"q".equals(userInput));

    }
}
