package com.tlglearning.gui;

import com.tlglearning.gui.compassaction.Compass;
import com.tlglearning.gui.interactwarehouse.actionWarehouse;
import com.tlglearning.middleware.commandGateObject;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.tlglearning.gui.compassaction.Compass.getPanel;

public class MainWindow {

    private static final JTextArea P1 = new JTextArea(6, 94);
    private static final JTextArea P2 = new JTextArea();
    private static final ColorPane P3 = new ColorPane();
    private static final JTextArea P4 = new JTextArea();
    // commandTextField is the user input area to be sent to application after button.
    private static final JTextField commandTextField = new JTextField(10);
    private static final JPanel BUTTON_ACTION_CONTAINER = new JPanel(new BorderLayout());
    private static final JPanel BUTTON_GO_CONTAINER = new JPanel(new BorderLayout());
    private String titleText;
    private String map;
    private String text;
    private static final JFrame APP_CONTAINER = new JFrame();
    private static final JPanel TITLE_CONTAINER = new JPanel();
    private static final JPanel MAP_CONTAINER = new JPanel();
    private static final JPanel PROMPT_CONTAINER = new JPanel();
    private static boolean gameStarted = false;
    private static ImageIcon MapImageIcon;
    static JLabel mapPanelLabel = new JLabel();

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
    public static void show() {
        APP_CONTAINER.setVisible(true);
    }

    /**
     * initialize() - setup and customize main gui panels & elements
     */
    public void initialize() {

        /* Create a main window panel and set attributes. */
        APP_CONTAINER.setLayout(new BorderLayout(0, 0));
        APP_CONTAINER.setTitle("Transporter");
        APP_CONTAINER.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        APP_CONTAINER.setSize(1500, 1500);
        APP_CONTAINER.setExtendedState(JFrame.MAXIMIZED_BOTH);
        APP_CONTAINER.setResizable(true);
        APP_CONTAINER.setLocationRelativeTo(null);

        /* Element containers */
        MAP_CONTAINER.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        MAP_CONTAINER.setBackground(Color.BLACK);
        //MAP_CONTAINER.setSize(500, 500);

        PROMPT_CONTAINER.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        PROMPT_CONTAINER.setBackground(Color.BLACK);
        //PROMPT_CONTAINER.setSize(600, 600);

        TITLE_CONTAINER.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        TITLE_CONTAINER.setBackground(Color.BLACK);
        //TITLE_CONTAINER.setSize(400, 400);

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
        //P3.setPreferredSize(new Dimension(600, 350));
        P3.setCharacterAttributes(att, true);
        P3.setFont(new Font("Courier New", Font.PLAIN, 12));
        P3.setOpaque(false);

        /* P4 is JTextArea - will populate officeMap */
        P4.setFont(new Font("Courier New", Font.PLAIN, 12));
        P4.setForeground(Color.WHITE);
        P4.setBackground(Color.BLACK);

        /*submitText */

        commandTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendCommandToApp();
                }
            }
        });

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        MapImageIcon= new ImageIcon(classloader.getResource("photos/intro.png"));

        /* Add basic GUI elements to their containers */
        TITLE_CONTAINER.add(P1);
        MAP_CONTAINER.add(P2, gbc);
        MAP_CONTAINER.add(P4, gbc);
        PROMPT_CONTAINER.add(P3);
        PROMPT_CONTAINER.add(commandTextField);

        BUTTON_GO_CONTAINER.add(commandTextField, BorderLayout.CENTER);

        PROMPT_CONTAINER.add(BUTTON_GO_CONTAINER,BorderLayout.NORTH);
        PROMPT_CONTAINER.add(BUTTON_ACTION_CONTAINER,BorderLayout.SOUTH);

        /* Add elements container to the main application */
        APP_CONTAINER.add(TITLE_CONTAINER, BorderLayout.NORTH);
        APP_CONTAINER.add(MAP_CONTAINER, BorderLayout.CENTER);
        APP_CONTAINER.add(PROMPT_CONTAINER, BorderLayout.SOUTH);
        mapPanelLabel.setIcon(MapImageIcon);
        MAP_CONTAINER.add(mapPanelLabel, gbc);
//        APP_CONTAINER.setResizable();



      BUTTON_ACTION_CONTAINER.add(Compass.getPanel());
//        APP_CONTAINER.add(actionWarehouse.getPanel());

        /* Setting GUI visibility */
        show();
    }


/**
 * (NOT GUI!!) FIELD SETTER METHODS BELOW  ----------------------------------------------------------------------------|
 * --------------------------------------------------------------------------------------------------------------------|
 * --------------------------------------------------------------------------------------------------------------------|
 */

    public void setMapChars(String map) {
        this.map = map;
    }


    public void setPromptText(String text) {
        this.text = text;
    }


    public void setTitleText(String title) {
        this.titleText = title;
    }


    public void setGameStarted(){
        gameStarted = true;
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
        if (P4.getText().length() == 0) {
            P2.setEditable(true);
            mapPanelLabel.setIcon(null);
            setMapChars(str);
            P2.setText(map);
            /* Sleep gui thread for .1 seconds for synchronicity */
            sleep();
            P2.setEditable(false);
        }
    }


    /**
     * setPrompt() - calls sleep, setPromptText and appends text to P3 JColorPane
     */
    public void setPrompt(String str) {
        String savedGameStartPrompt = "The map above the prompt, shows you what room you are in, what locations are explorable in the room, and the available exits, to see a full map type 'h' and select option 3";
        P3.setEditable(true);
        sleep();
        if (!gameStarted) {
            setPromptText(str);
            P3.appendANSI("\n" + text);
        }
        if (str.contains("New game started.") || str.contains(savedGameStartPrompt)) {
            //P3.setPreferredSize(new Dimension(600, 150));
            setGameStarted();
            setPromptText(str);
            P3.setText(text);
        } else if (P3.getText().charAt(P3.getText().length() - 3) == '>') {
            setPromptText(str);
            P3.setText("\n" + text);
        } else if (gameStarted) {
            setPromptText(str);
            P3.appendANSI("\n" + text);
        }
        PROMPT_CONTAINER.revalidate();
        PROMPT_CONTAINER.repaint();
        sleep();
        P3.setEditable(false);
    }


    public void appendOfficeMap(String officeMap){
        P4.append(officeMap);
        mapPanelLabel.setIcon(null);
        P2.setText(null);
        sleep();
    }


    public void setPhotoToMapPanel(String key)  {


        if (P4.getText().length() == 0){
            // Set to editable
            P2.setEditable(true);
            P2.setText("");
            //Get the resource from resources Photos
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            switch(key){
                case "truck":
                    MapImageIcon = new ImageIcon(
                        new ImageIcon(classloader.getResource("photos/"+key+".png"))
                            .getImage()
                            .getScaledInstance(900, 186, Image.SCALE_DEFAULT));
                    break;
                default:
                    MapImageIcon= new ImageIcon(classloader.getResource("photos/"+key+".png"));
                    break;
            }

            // Set Icon to new image Icon
            mapPanelLabel.setIcon(MapImageIcon);

            /* Sleep gui thread for .1 seconds for synchronicity */
            sleep();
            P2.setEditable(false);
        }
    }


    /**
     * sendCommandToApp changes the value in commandObject to record command is sent and set command Variable to command text.
     */

    public static void sendCommandToApp() {
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

    public static JPanel getActionButtonContainer(){
        return  BUTTON_ACTION_CONTAINER;
    }

    public void wipe() {
        P4.setText(null);
        P2.setText(null);
    }
}