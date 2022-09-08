package com.tlglearning.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.tlglearning.util.JacksonParser.parse;
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


    public void runPrompt(String key) {
        System.out.println("\n" +
                PrettyText.WHITE.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor());
    }

    public void runPromptCyan(String key) {
        System.out.println("\n" +
                PrettyText.CYAN.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor());
    }

    public void runPromptRed(String key) {
        System.out.println("\n" +
                PrettyText.RED.getColor() +
                gameInput.get(key) +
                PrettyText.RESET.getColor());
    }


}

