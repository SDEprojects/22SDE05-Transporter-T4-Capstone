package com.tlglearning.gui;

import com.tlglearning.middleware.commandGateObject;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MainWindow {

    private static final JTextArea P1 = new JTextArea(6, 94);
    private static final JTextArea P2 = new JTextArea();
    private static final ColorPane P3 = new ColorPane();

    // commandTextField is the user input area to be sent to application
    // After button.
    private static final JTextField commandTextField = new JTextField(10);

    // commandSubmitButton submits commandTextField and is linked to action
    // listener.
    private static final JButton commandSubmitButton = new JButton("Enter");
    private String titleText;
    private String map;
    private String text;
    private static final JFrame APP_CONTAINER = new JFrame();
    private static final JPanel TITLE_CONTAINER = new JPanel();
    private static final JPanel MAP_CONTAINER = new JPanel();
    private static final JPanel PROMPT_CONTAINER = new JPanel();
    private static boolean gameStarted = false;

    private static ImageIcon MapImageIcon;

    JLabel MapPanelLable= new JLabel();

    public MainWindow() {
        initialize();
    }

/**
 * CLASS METHODS BELOW ------------------------------------------------------------------------------------------------|
 * GUI METHODS(show, initialize, setTitle, setMap, setPrompt)----------------------------------------------------------|
 * --------------------------------------------------------------------------------------------------------------------|
 */

    /**
     * show() - display initialized APP_CONTAINER.
     */
    public void show() {
        APP_CONTAINER.setVisible(true);
    }

    /**
     * initialize() - setup and customize main gui panels & elements
     */
    private void initialize() {

        /* Create a main window panel and set attributes. */
        APP_CONTAINER.setLayout(new BorderLayout(0, 0));
        APP_CONTAINER.setTitle("Transporter");
        APP_CONTAINER.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        APP_CONTAINER.setSize(800, 480);
        APP_CONTAINER.setLocationRelativeTo(null);


        /* Element containers */
        MAP_CONTAINER.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        MAP_CONTAINER.setBackground(Color.BLACK);

        PROMPT_CONTAINER.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        PROMPT_CONTAINER.setBackground(Color.BLACK);

        TITLE_CONTAINER.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        TITLE_CONTAINER.setBackground(Color.BLACK);


        /* P1 is JTextArea - will populate title */
        P1.setFont(new Font("Courier New", Font.PLAIN, 12));
        P1.setForeground(Color.WHITE);
        P1.setBackground(Color.BLACK);
        TITLE_CONTAINER.setOpaque(true);

        /* P2 is JTextArea - will populate map */
        P2.setFont(new Font("Courier New", Font.PLAIN, 12));
        P2.setForeground(Color.WHITE);
        P2.setBackground(Color.BLACK);
        MAP_CONTAINER.setOpaque(true);

        /* P3 is Colorpane (extends JTextPane) setting attributes - will populate prompts */
        SimpleAttributeSet att = new SimpleAttributeSet();
        StyleConstants.setBold(att, true);
        StyleConstants.setBackground(att, Color.BLACK);
        P3.setPreferredSize(new Dimension(600, 350));
        P3.setCharacterAttributes(att, true);
        P3.setFont(new Font("Courier New", Font.PLAIN, 12));
        P3.setOpaque(false);

        /*submitText */

        commandSubmitButton.addActionListener(e ->
        {
            new SwingWorker<String, Object>() {
                public String doInBackground() throws InterruptedException {
                    //create String for the label
                    sendCommandToApp();
                    return null;
                }


            }.execute();
        });


        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        MapImageIcon= new ImageIcon(classloader.getResource("photos/intro.png"));

        /* Add basic GUI elements to their containers */
        TITLE_CONTAINER.add(P1);
        MAP_CONTAINER.add(P2);
        PROMPT_CONTAINER.add(P3);
        PROMPT_CONTAINER.add(commandTextField);
        PROMPT_CONTAINER.add(commandSubmitButton);

        /* Add elements container to the main application */
        APP_CONTAINER.add(TITLE_CONTAINER, BorderLayout.NORTH);
        APP_CONTAINER.add(MAP_CONTAINER, BorderLayout.CENTER);
        APP_CONTAINER.add(PROMPT_CONTAINER, BorderLayout.SOUTH);
        MapPanelLable.setIcon(MapImageIcon);
        MAP_CONTAINER.add(MapPanelLable);

        /* Setting GUI visibility */
        show();
    }


    /**
     * (NOT GUI!!) FIELD SETTER METHODS BELOW  ----------------------------------------------------------------------------|
     * --------------------------------------------------------------------------------------------------------------------|
     * --------------------------------------------------------------------------------------------------------------------|
     */
    public void setGameStarted() {
        this.gameStarted = true;
    }

    public void setTitleText(String title) {
        this.titleText = title;
    }

    public void setMapChars(String map) {
        this.map = map;
    }

    public void setPromptText(String text) {
        this.text = text;
    }

/**
 * GUI METHODS BELOW---------------------------------------------------------------------------------------------------|
 * --------------------------------------------------------------------------------------------------------------------|
 * --------------------------------------------------------------------------------------------------------------------|
 */

    /**
     * setTitle() - calls setTitleText and appends titleText to P1 JTextArea
     */
    public void setTitle(String str) {
        setTitleText(str);
        P1.append(titleText);
        P1.setEditable(false);
    }

    /**
     * setMap() - calls setMapChars and appends map to P2 JTextArea
     */
    public void setMap(String str) {
        P2.setEditable(true);
        if (map != null) {
            int end = map.length();
            setMapChars(str);
            P2.replaceRange(map, 0, end);
        } else {
            setMapChars(str);
            P2.append(map);
        }
        /* Sleep gui thread for .1 seconds for synchronicity */
        sleep();
        sleep();
        P2.setEditable(false);
    }

    /**
     * setPrompt() - calls sleep, setPromptText and appends text to P3 JColorPane
     */
    public void setPrompt(String str) {

        P3.setEditable(true);
        sleep();

        if (!gameStarted) {
            setPromptText(str);
            P3.appendANSI("\n" + text);

        }
        if (P3.getText().length() == 808) {
            APP_CONTAINER.setSize(800, 600);
            P3.setPreferredSize(new Dimension(600, 150));
            setGameStarted();
            setPromptText(str);
//            P3.setText(text);
            P3.appendANSI("\n"+text);

        } else if (P3.getText().charAt(P3.getText().length() - 3) == '>') {
            setPromptText(str);
//            P3.setText(text);
            P3.appendANSI("\n"+text);

        } else if (gameStarted) {
            setPromptText(str);
            P3.appendANSI("\n" + text);

        }
        sleep();

        PROMPT_CONTAINER.revalidate();
        PROMPT_CONTAINER.repaint();
        sleep();
        P3.setEditable(false);
    }

    public void setPhotoToMapPanel(String key)  {

        // Set to editable
        P2.setEditable(true);

        // Remove any text from area
        P2.setText("");

        //Get the resource from resources Photos
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        MapImageIcon= new ImageIcon(classloader.getResource("photos/"+key+".png"));

        // Set Icon to new image Icon
        MapPanelLable.setIcon(MapImageIcon);

        /* Sleep gui thread for .1 seconds for synchronicity */
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("An Exception occurred: " + e);
        }
        P2.setEditable(false);
    }

    /**
     * sendCommandToApp changes the value in commandObject to record command is sent and set command Variable to command text.
     */

    public static void sendCommandToApp() {
        P2.setText("");
        P3.setText("");


        // Sets commandGateObject command text  field to the user input command.
        commandGateObject.setCommand(commandTextField.getText().toLowerCase());

        // Sends confirmation boolean variable to tell the middleware that command is sent.
        // Then command string is passed to Transport Application.
        commandGateObject.setIsCommandSentFromGui(true);
    }

    public static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("An Exception occurred: " + e);
        }
    }
}