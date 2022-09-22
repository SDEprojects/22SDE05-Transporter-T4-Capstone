package com.tlglearning.gui.panelinterface;

import com.sun.tools.javac.Main;
import com.tlglearning.gui.ColorPane;
import com.tlglearning.gui.MainWindow;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

import static com.tlglearning.gui.MainWindow.*;


public class MapPanel extends PanelAbstractMethods implements PanelImageInterface {

    private static ImageIcon MapImageIcon;
    private static String mapImageKey = "";
    private static String lastLocation = "";

    private static JLabel mapPanelLabel;
    private static ColorPane mapPanel_SubPanelAsciiMap;
    private static ColorPane mapPanel_SubPanelAscii;

    private static String color_White = "\u001B[1;36m";
    private static String resetColor = "\u001B[0m";

    public static void setUpMapPanel() {
        ClassLoader cl = Main.class.getClassLoader();

        InputStream input = cl.getResourceAsStream("Destinations.yaml");

        Yaml yaml = new Yaml();

        setDestinationsMap(yaml.load(input));

        input = cl.getResourceAsStream("GamePrompts.yaml");

        setGamePromptsMap(yaml.load(input));

//        MainWindow.mapPanelLabel_setIcon(MapImageIcon);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        mapPanelLabel = MainWindow.getMapPanel();
        MapImageIcon = new ImageIcon(classloader.getResource("photos/intro.png"));
        mapPanelLabel.setIcon(MapImageIcon);
        mapPanel_SubPanelAscii = getMapAsciiJPanel();

        mapPanel_SubPanelAsciiMap = getMapAsciiMapJPanel();
    }

    public static String updateChange(String identity, String updated_key) {
        System.out.println(getLocationName());
        if(updated_key==getKey() ){
            return "No update to Gui.";
        }
//        mapPanel_SubPanelAscii.setText(null);
//        MapImageIcon=null;
//        mapPanelLabel.setIcon(MapImageIcon);
//        mapPanel_SubPanelAscii.setText("");
//        lastLocation=getLocationName();
        setKey(updated_key);
        if(get_PNG_ImagesList().contains(getLocationName())){

            setImageToMapPanel(getKey());
        }else if(!get_Ascii_ImagesList().contains(getKey()) ){


            String prompt = (String) getGamePromptsMap().get(getKey());
            mapPanel_SubPanelAscii.setText("");
            mapPanel_SubPanelAscii.append(Color.red,prompt);
        }else if(get_Ascii_ImagesList().contains(getKey())){
            MapImageIcon=new ImageIcon();
            mapPanelLabel.setIcon(MapImageIcon);

            String prompt = (String) getGamePromptsMap().get(getLocationName());
            mapPanel_SubPanelAsciiMap.setText("");
            mapPanel_SubPanelAsciiMap.append(Color.green,prompt);

        }

//        if (getGamePromptsMap().containsKey(updated_key) ){//&& getKey() != updated_key) {
//            setKey(updated_key.strip().toLowerCase());
//            String prompt = (String) getGamePromptsMap().get(getKey());
//
//            // Checks if list contained in Panel Abstract contains key,
//            // if contains key then image exist in resource and will set
//            // map panel as image.
//            if (get_PNG_ImagesList().contains(getKey())) {
//                setImageToMapPanel(getKey());
//            }
//            // Checks to see if map exists as ascii and
//            else if (Ascii_ImagesList.contains(getKey())) {
//                MapImageIcon=null;
//                mapPanelLabel.setIcon(MapImageIcon);
//
//                mapPanel_SubPanelAscii.append(Color.red,"#"+identity+": "+prompt);
//                System.out.println("Here it is");
//                System.out.println(updated_key);
//                System.out.println(prompt);
////                mainWindow.setMap("#M" + identity + ": " + prompt);
//            } else {
//                System.out.println("Here its not");
//                System.out.println(updated_key);
//                System.out.println(prompt);
//                MapImageIcon=null;
//                mapPanelLabel.setIcon(MapImageIcon);
//
//                mapPanel_SubPanelAscii.append(Color.red,"#"+identity+": "+prompt);
//            }
//        }
        return "Gui Updated";
    }

    public static void setTextToMapPanel(String Map_Panel_ASCII) {

        sleep();
        MapImageIcon = null;
        mapPanelLabel.setIcon(MapImageIcon);
        mapPanel_SubPanelAscii.setText(Map_Panel_ASCII);

    }

    public static void setImageToMapPanel(String key) {
        if (mapImageKey != key) {

            mapImageKey = key;
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();

            switch (key) {

                case "truck":

                    MapImageIcon = new ImageIcon(
                            new ImageIcon(classloader.getResource("photos/" + key + ".png"))
                                    .getImage()
                                    .getScaledInstance(900, 186, Image.SCALE_DEFAULT));
                    mapPanelLabel.setIcon(MapImageIcon);

                    break;

                default:

                    MapImageIcon = new ImageIcon(classloader.getResource("photos/" + key + ".png"));

                    mapPanelLabel.setIcon(MapImageIcon);

                    break;
            }
        }
    }

    public static String getKey() {
        return mapImageKey;
    }

    public static void setKey(String key) {
        mapImageKey = key;
    }
}
