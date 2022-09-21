package com.tlglearning.gui.panelinterface;

import com.sun.tools.javac.Main;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tlglearning.client.TransporterClient.mainWindow;


public class MapPanel implements ImageInterface{

    String imageURL="";
    static final List<String> gameMapImages = new ArrayList<String>() {
        {
            add("truck");
            add("warehouse");
            add("front office");
            add("boss office");
            add("break room");
            add("hr office");
            add("tech room");
        }
    };

    static final List<String> gamePhotoImages = new ArrayList<String>() {
        {
            add("truck");
            add("warehouse");
//            add("front office");
//            add("boss office");
//            add("break room");
//            add("hr office");
//            add("tech room");
        }
    };

    static HashMap<String, Object> DestinationsMap;
    static HashMap<String, Object> GamePromptsMap;

    static {
        ClassLoader cl = Main.class.getClassLoader();

        InputStream input = cl.getResourceAsStream("Destinations.yaml");

        Yaml yaml = new Yaml();

        DestinationsMap = yaml.load(input);

        input = cl.getResourceAsStream("GamePrompts.yaml");

        GamePromptsMap = yaml.load(input);

    }

    public static void updateMapIfChange(String identity,String key) {
        String prompt = (String) GamePromptsMap.get(key);
        System.out.println(prompt);
        if (gameMapImages.contains(key)) {

            if(gamePhotoImages.contains(key)){
                mainWindow.setPhotoToMapPanel(key);
            }else{
                mainWindow.setMap("#"+identity+": "+prompt);
            }

        } else {
            mainWindow.setPrompt("#"+identity+": "+prompt);
        }
    }
}
