package com.tlglearning.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.tlglearning.util.JacksonParser.parseToMap;

public class GamePrompt {
    // Variables
    private File gameJson = new File("src/main/resources/gameprompt.json");

    private HashMap gameInput;
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
    }
    //get text from gameInput and color it Cyan
    public void runPromptCyan(String key) {
        System.out.println("\n" +
                PrettyText.CYAN.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor());
    }
    //get text from gameInput and color it Red
    public void runPromptRed(String key) {
        System.out.println("\n" +
                PrettyText.RED.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor());
    }


}

