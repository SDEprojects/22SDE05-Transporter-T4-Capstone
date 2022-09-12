package com.tlglearning.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveGame {

    //location backpack inventory and scenario
    public String Location;
    public ArrayList<String> backpack;

    public static void save(Location currentLocation, Inventory backpack, ScenarioGenerator startingScenario) {
        File file = new File("src/main/resources/currentSave.json");
        ObjectMapper save = new ObjectMapper();

        try {
            List<Object> list = new ArrayList<>();
            list.add(new Location(currentLocation));
            list.add(new Inventory(backpack));
            list.add(new ScenarioGenerator(startingScenario));
            ObjectWriter writer = save.writer(new DefaultPrettyPrinter());
            writer.writeValue(file,list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//objectMapper.writeValue(new File("target/car.json"),car);

