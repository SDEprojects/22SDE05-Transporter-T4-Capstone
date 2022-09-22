package com.tlglearning.gui.panelinterface;

import com.sun.tools.javac.Main;
import com.tlglearning.util.Location;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class PanelAbstractMethods {



    private static  HashMap<String, Object> DestinationsMap;
    private static  HashMap<String, Object> GamePromptsMap;

    private static Location location=new Location();

    protected static  List<String> PNG_ImagesList = new ArrayList<>() {
        {
            add("truck");
//            add("warehouse");
//            add("front office");
//            add("boss office");
//            add("break room");
//            add("hr office");
//            add("tech room");
        }
    };

    public static List<String> Ascii_ImagesList = new ArrayList<>() {
        {
            add("truck");
            add("warehouse");
            add("front office");
            add("boss office");
            add("break room");
            add("hr office");
            add("tech room");
            add("officeMap");
            add("gas station");
        }
    };

    String key="";


    static {
        ClassLoader cl = Main.class.getClassLoader();

        InputStream input = cl.getResourceAsStream("Destinations.yaml");

        Yaml yaml = new Yaml();

        DestinationsMap = yaml.load(input);

        input = cl.getResourceAsStream("GamePrompts.yaml");

        GamePromptsMap = yaml.load(input);

//        MainWindow.mapPanelLabel_setIcon(MapImageIcon);



    }

    public static HashMap<String, Object> getDestinationsMap() {
        return DestinationsMap;
    }

    public static void setDestinationsMap(HashMap<String, Object> destinationsMap) {
        DestinationsMap = destinationsMap;
    }

    public static HashMap<String, Object> getGamePromptsMap() {
        return GamePromptsMap;
    }

    public static void setGamePromptsMap(HashMap<String, Object> gamePromptsMap) {
        GamePromptsMap = gamePromptsMap;
    }





    public static List<String> get_PNG_ImagesList() {
        return PNG_ImagesList;
    }

    public static void set_PNG_ImagesList(List<String> gamePhotoImages) {
        PanelAbstractMethods.PNG_ImagesList = gamePhotoImages;
    }

    public static List<String> get_Ascii_ImagesList() {
        return Ascii_ImagesList;
    }

    public static void set_Ascii_ImagesList(List<String> gameMapImages) {
        PanelAbstractMethods.Ascii_ImagesList = gameMapImages;
    }


    public static void setLocation(Location loc){
        location=loc;

    }

    public static void updateChange(){}

    public static String getLocationName(){
        return location.getLocationName();
    }




}
