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
        Redirect.sendAppToGui("\n" +
                PrettyText.RESET.getColor() +
                gameInput.get(key));
    }
    //get text from gameInput and color it Cyan
    public void runPromptCyan(String key) {
        Redirect.sendAppToGui("\n" +
                PrettyText.CYAN.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor());
    }
    //get text from gameInput and color it Red
    public void runPromptRed(String key) {
        Redirect.sendAppToGui("\n" +
                PrettyText.RED.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor());
    }

    public void runPromptWithLocation(String key, String nextLocation) {
        Redirect.sendAppToGui("\n" +
                PrettyText.RESET.getColor() +
                gameInput.get(key) +
                nextLocation);
    }

    public String getMap(String key){
        return "\n" +
                PrettyText.RESET.getColor() +
                gameInput.get(key);
    }


}

