package com.tlglearning.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class JacksonParser {
    //using Jackson to create a JsonNode object to parse through File objects
    public static JsonNode parse(InputStream file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return objectMapper.readTree(file);

    }

    //using Jackson to create a HashMap to parse through text output
    public static HashMap parseToMap(InputStream file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, HashMap.class);
    }
}
