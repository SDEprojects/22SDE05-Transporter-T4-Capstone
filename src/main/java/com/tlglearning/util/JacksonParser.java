package com.tlglearning.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;

public class JacksonParser {
    //using Jackson to create a JsonNode object to parse through File objects
    public static JsonNode parse(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return objectMapper.readTree(file);
    }
}