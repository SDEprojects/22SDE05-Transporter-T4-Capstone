package com.tlglearning.util;

import com.tlglearning.middleware.Redirect;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.tlglearning.util.InputHandling.clearScreen;

public class TitleScreen {
    private static GamePrompt prompt = new GamePrompt();
    String story = "This is a story of Jimbo, a Truck Driver who works really hard to feed his family. Jimbo needs your help to get on the road and make his deliveries";
    String objective = "Your goal is to pickup the payloads from specific location and deliver it to the destinations. \n\tBefore you head out for hitting the road, you need to collect certain require item else you won't be able to drive.";
    String win_game = "You need to make all of your deliveries to win the game.";
    String play = "Before you can get on the road, you need to go room by room at your office to collect the items.\n\t" +
            "Once you have all of your items your can start driving state to state to make your pickups and deliveries";

    public void titleScreen() {
        clearScreen();
        prompt.runPrompt("title");
    }

    public void intro() {

        Redirect.sendprintfAppToGui("\n\nSTORY: %s", story);
        Redirect.sendprintfAppToGui("\n\nOBJECTIVE: %s", objective);
        Redirect.sendprintfAppToGui("\n\nHOW TO PLAY: %s", play);
        Redirect.sendprintfAppToGui("\n\nHOW TO WIN: %s\n", win_game);
    }
}
