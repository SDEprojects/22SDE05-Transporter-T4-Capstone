// PACKAGES
package com.tlglearning.gui.states;

// IMPORTS
import com.sun.tools.javac.Main;
import com.tlglearning.util.Location;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.HashMap;

// CLASS
public class StatesMaps {

// FIELDS
    static Location location;
    static HashMap<String, Object> StatesMap;

// METHOD TO LOAD STATE MAPS
    public static void generateMaps() {

        ClassLoader cl = Main.class.getClassLoader();

        InputStream input = cl.getResourceAsStream("Destinations.yaml");

        Yaml yaml = new Yaml();

        StatesMap = yaml.load(input);

    }
}
