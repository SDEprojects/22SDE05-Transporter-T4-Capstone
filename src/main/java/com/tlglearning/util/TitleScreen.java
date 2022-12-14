package com.tlglearning.util;

import com.tlglearning.middleware.Redirect;

import static com.tlglearning.util.InputHandling.clearScreen;

public class TitleScreen extends GamePrompt {
    private static GamePrompt prompt = new GamePrompt();
    String story = "This is a story of Jimbo, a Truck Driver who works really hard to feed his family. Jimbo needs your help to get on the road and make his deliveries";
    String objective = "Your goal is to pickup the payloads from specific location and deliver it to the destinations. \nBefore you head out for hitting the road, you need to collect certain require item else you won't be able to drive.";
    String win_game = "You need to make all of your deliveries to win the game.";
    String play = "Before you can get on the road, you need to go room by room at your office to collect the items.\n" +
            "Once you have all of your items your can start driving state to state to make your pickups and deliveries";

    public void titleScreen() {
        clearScreen();
        GamePrompt prompt2 = new GamePrompt() {
            @Override
            public void runPrompt(String title) {
                Redirect.sendTitleToGui("32",title);
            }
        };

        String title = prompt2.gameInput.get("title").toString();
        prompt2.runPrompt(title);
    }

    public void intro() {

        Redirect.sendprintfAppToGui("33","STORY: %s", story);
        Redirect.sendprintfAppToGui("34","\nOBJECTIVE: %s", objective);
        Redirect.sendprintfAppToGui("35","\nHOW TO PLAY: %s", play);
        Redirect.sendprintfAppToGui("36","\nHOW TO WIN: %s\n", win_game);
    }

}
