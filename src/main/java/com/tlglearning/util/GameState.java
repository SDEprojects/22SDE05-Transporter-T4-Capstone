package com.tlglearning.util;

import com.tlglearning.util.TitleScreen;

import java.util.Scanner;

public class GameState {
    //create scanner obj to read user input
    Scanner read = new Scanner(System.in);


    //CTOR
    public GameState() {

    }

    public void gameInput() {
        System.out.println("You may use the inputs 'N' to start a new game. 'Q' to quit game. Also Type 'H' to look at" +
                " instructions.");
        String input = read.next().toLowerCase();
        //switch case to get user input and perform the necessary commands
        switch (input) {
            case "q":
                System.out.println("quitting....");
                System.exit(0);
                break;
            case "n":
                System.out.println("New game started");
                newGame();
                break;
            case "h":
                //pull help tutorial from kyle using call
                System.out.println("Help tutorial");
                break;
            default:
                System.out.println("Not a valid input");
        }

    }

    private void helpTutorial() {

    }

    private void newGame() {
        //call intro
        //TitleScreen.intro();
    }


}
