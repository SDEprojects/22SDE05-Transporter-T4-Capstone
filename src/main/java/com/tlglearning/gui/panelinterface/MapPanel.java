package com.tlglearning.gui.panelinterface;

import com.sun.tools.javac.Main;
import com.tlglearning.gui.ColorPane;
import com.tlglearning.gui.MainWindow;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

import static com.tlglearning.gui.MainWindow.getMapAsciiJPanel;
import static com.tlglearning.gui.MainWindow.getMapAsciiMapJPanel;


public class MapPanel extends PanelAbstractMethods implements PanelImageInterface {

    private static ImageIcon MapImageIcon;
    private static String mapImageKey = "";
    private static String lastLocation = "";

    private static JLabel mapPanelLabel;
    private static ColorPane mapPanel_SubPanelAsciiMap;
    private static ColorPane mapPanel_SubPanelAscii;


    private static String color_White = "\u001B[1;36m";
    private static String resetColor = "\u001B[0m";

    public static void setUpMapPanel(){

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
        setKey(updated_key);
        if(get_PNG_ImagesList().contains(getLocationName())){
            mapPanel_SubPanelAscii.setText("");
            mapPanel_SubPanelAsciiMap.setText("");
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            MapImageIcon = new ImageIcon(classloader.getResource("photos/" + getKey() + ".png"));
            mapPanelLabel.setIcon(MapImageIcon);
//            setImageToMapPanel(getKey());
        }

        else if(!get_Ascii_ImagesList().contains(getKey()) ){
            String prompt = (String) getGamePromptsMap().get(getKey());
            if(getLocationsCommands().containsKey(getLocationName())){
                prompt=prompt+getLocationsCommands().get(getLocationName());
            }
            mapPanel_SubPanelAscii.setText("");
            prompt=formatStringForPanel(prompt,120);
            mapPanel_SubPanelAscii.append(Color.red,prompt);
        }
        else if(get_Ascii_ImagesList().contains(getKey())){
            MapImageIcon=new ImageIcon();
            mapPanelLabel.setIcon(MapImageIcon);
            String prompt = (String) getGamePromptsMap().get(getKey());
            mapPanel_SubPanelAsciiMap.setText("");
            mapPanel_SubPanelAsciiMap.append(Color.green,prompt);

        }
        return "Gui Updated";
    }

    public static String formatStringForPanel(String string, int width){
        String updateString="";
        char[] charString=string.toCharArray();
        for(int  i=0; i<charString.length; i++){
            if(i%width==0){
                updateString+="\n";
            }
            updateString+=charString[i];

        }
        return updateString;
    }

    public static String getKey() {
        return mapImageKey;
    }

    public static void setKey(String key) {
        mapImageKey = key;
    }


}
