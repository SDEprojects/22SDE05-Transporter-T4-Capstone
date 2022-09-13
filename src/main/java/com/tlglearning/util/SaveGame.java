package com.tlglearning.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveGame {

    public static void save(Location currentLocation, Inventory backpack, ScenarioGenerator startingScenario) {
//        String dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toString().replace(':','-');
//        File file = new File(String.format("src/main/resources/currentSave_%s.json", dateTime));
        File file = new File("currentSave.json");
        ObjectMapper save = new ObjectMapper();
        try {
            Map<String,Object> saveHashMap = new HashMap<>();
            saveHashMap.put("location",currentLocation);
            saveHashMap.put("inventory",backpack);
            saveHashMap.put("scenario",startingScenario);
            ObjectWriter writer = save.writer(new DefaultPrettyPrinter());
            writer.writeValue(file,saveHashMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}