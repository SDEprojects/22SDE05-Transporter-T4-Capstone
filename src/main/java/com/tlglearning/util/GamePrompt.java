package com.tlglearning.util;

import com.tlglearning.middleware.Redirect;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static com.tlglearning.util.JacksonParser.parseToMap;

public class GamePrompt {
    // Variables
    private InputStream gameJson = InputHandling.class.getClassLoader().getResourceAsStream("gameprompt.json");

    public HashMap gameInput;

    {
        try {
            gameInput = parseToMap(gameJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // CTOR
    public GamePrompt() {

    }

    //get text from gameInput and color it White
    public void runPrompt(String key) {
        System.out.println("\n" +
                PrettyText.RESET.getColor() +
                gameInput.get(key));
        Redirect.getPromptKey_DictLookUp_PromptToGui("12",key);
    }

    //get text from gameInput and color it Cyan
    public void runPromptCyan(String key) {
        System.out.println(("\n" +
                PrettyText.CYAN.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor()));
        Redirect.getPromptCyan_DictLookUp_PromptToGui("13",key);

    }

    //get text from gameInput and color it Red
    public void runPromptRed(String key) {
        System.out.println(("\n" +
                PrettyText.RED.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor()));
        Redirect.getPromptRed_DictLookUp_PromptToGui("14",key);
    }

    public void runPromptWithLocation(String key, String nextLocation) {
        Redirect.SendLocationInfoToGui("77",  PrettyText.RESET.getColor() + //TODO: THIS IS WHAT I ADDED IN THIS COMMIT
                        gameInput.get(key) +
                nextLocation);
        System.out.println((
                PrettyText.RESET.getColor() +
                gameInput.get(key) +
                nextLocation));
    }

//This is where the officeMap comes from
    public String getMap(String key) {
        Redirect.sendLocationImagesToGui("16",key);
        return "\n" +
                PrettyText.RESET.getColor() +
                gameInput.get(key);
    }


}