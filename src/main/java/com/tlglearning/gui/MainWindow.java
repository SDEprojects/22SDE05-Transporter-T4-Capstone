package com.tlglearning.gui;

import com.tlglearning.gui.Office.actionOffice;
import com.tlglearning.gui.compassaction.Compass;
import com.tlglearning.gui.interactHrOffice.actionHrOffice;
import com.tlglearning.gui.interactOffice.actionOffice;
import com.tlglearning.gui.interactTechRoom.actionTechRoom;
import com.tlglearning.gui.interactbossoffice.actionBossOffice;
import com.tlglearning.gui.interactbreakroom.actionBreakRoom;
import com.tlglearning.gui.interactgasstation.actionGasStation;
import com.tlglearning.gui.interactwarehouse.actionWarehouse;
import com.tlglearning.gui.music.RadioButton;
import com.tlglearning.middleware.commandGateObject;
import com.tlglearning.interactStates.actionStates;
import com.tlglearning.gui.states.StatesMaps;
import com.tlglearning.gui.states.StatesPanel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
//import static com.tlglearning.gui.button.Compass.getPanel;


public class MainWindow {
    JLayeredPane wareHousePane = actionWarehouse.getPanel();
    JLayeredPane frontOfficePane = actionOffice.getPanel();
    JLayeredPane bossOfficePane = actionBossOffice.getPanel();
    JLayeredPane breakRoomPane = actionBreakRoom.getPanel();

    JLayeredPane techRoomPane = actionTechRoom.getPanel();

    JLayeredPane hrOfficePane = actionHrOffice.getPanel();

    JLayeredPane gasStationPane = actionGasStation.getPanel();
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
    private static boolean gameStarted = false;
    private static ImageIcon MapImageIcon;
    static JLabel mapPanelLabel = new JLabel();
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private static final PromptContainer promptContainer = new PromptContainer();
    private static Countdown countdown;
    private static RadioButton radioButton;
    private  static BaseLayer baseLayer;
    private static Title title;
    private static boolean isIntro=true;
    private static StatesPanel statePanel;


    public MainWindow() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
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
    public void initialize() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        /* Create a main window panel and set attributes. */
        APP_CONTAINER.setLayout(null);
        APP_CONTAINER.setTitle("Transporter");
        APP_CONTAINER.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        APP_CONTAINER.setSize(1220, 690);
        APP_CONTAINER.setResizable(false);
        APP_CONTAINER.setLocationRelativeTo(null);

        /* Element containers */
        MAP_CONTAINER.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        MAP_CONTAINER.setBackground(Color.BLACK);
        //MAP_CONTAINER.setSize(500, 500);
//
//        PROMPT_CONTAINER.setLayout(new BorderLayout(0, 0));
//        PROMPT_CONTAINER.setBackground(Color.BLACK);
//        PROMPT_CONTAINER.setMinimumSize(new Dimension(600, 200));

        TITLE_CONTAINER.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
//        TITLE_CONTAINER.setBackground(Color.BLACK);
        TITLE_CONTAINER.setOpaque(false);

        TITLE_CONTAINER.setSize(new Dimension(1220, 187));
        TITLE_CONTAINER.setPreferredSize(new Dimension(1220, 187));
        TITLE_CONTAINER.setMaximumSize(new Dimension(1220, 187));
        TITLE_CONTAINER.add(new JLabel(new ImageIcon(classloader.getResource("photos/title.png"))));

        /* P1 is JTextArea - will populate title */
        P1.setFont(new Font("Courier New", Font.PLAIN, 12));
//        P1.setForeground(Color.WHITE);
        P1.setBackground(Color.BLACK);

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

        /* P4 is JTextArea - will populate officeMap */
        P4.setFont(new Font("Courier New", Font.PLAIN, 12));
        P4.setForeground(Color.WHITE);
        P4.setBackground(Color.BLACK);

        /*submitText */

        commandTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendCommandToApp();
                }
            }
        });

        MapImageIcon = new ImageIcon(classloader.getResource("photos/intro.png"));

        ImageIcon MapImageIcon2 = new ImageIcon(
                new ImageIcon(classloader.getResource("photos/intro.png"))
                        .getImage()
                        .getScaledInstance(1220, 686, Image.SCALE_DEFAULT)
        );

        baseLayer = new BaseLayer(MapImageIcon2);


        title = new Title();
        title.setSize(new Dimension(1220, 187));
        title.setPreferredSize(new Dimension(756, 187));
        title.setMaximumSize(new Dimension(756, 187));
        title.setLocation((1220 - 756) / 2, -20);
        title.setOpaque(false);


        baseLayer.add(title);

        JPanel textBoxPanel = new JPanel();
        textBoxPanel.setSize(new Dimension(1220, 187));
        textBoxPanel.setLocation(460, 600);
        textBoxPanel.setOpaque(false);
        textBoxPanel.setBackground(new Color(0, 0, 0, 0));
        textBoxPanel.add(commandTextField);

        radioButton = new RadioButton(this);

        baseLayer.add(textBoxPanel);
        baseLayer.add(Compass.getPanel());
        baseLayer.add(promptContainer.getPanel());
        baseLayer.addModal(radioButton.getPanel());

        APP_CONTAINER.add(baseLayer.getPanel());
//        baseLayer.add(gasStationPane);
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


    public void setGameStarted() {
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
            promptContainer.setPrompt(text);
        }
        if (str.contains("New game started.") || str.contains(savedGameStartPrompt)) {
            title.stop();
            promptContainer.wipe();
            promptContainer.positionSouth();
            setGameStarted();
            setPromptText(str);
            promptContainer.setPrompt(text);
        } else if (text.charAt(text.length() - 3) == '>') {
            promptContainer.wipe();
            setPromptText(str);
            promptContainer.setPrompt(text);
        } else if (gameStarted) {
            setPromptText(str);
            promptContainer.setPrompt(text);
        }

        if (str.contains(savedGameStartPrompt)) {
            countdown = new Countdown();
            baseLayer.addModal(countdown.getPanel());
            baseLayer.revalidate();
        }
//        PROMPT_CONTAINER.revalidate();
//        PROMPT_CONTAINER.repaint();
        sleep();
        P3.setEditable(false);
    }


    public void appendOfficeMap(String officeMap) {
        P4.append(officeMap);
        mapPanelLabel.setIcon(null);
        P2.setText(null);
        sleep();
    }

    public void setPhotoToMapPanel(String key) {

        if (key.equalsIgnoreCase("warehouse")) {


            baseLayer.add(wareHousePane);
            System.out.println(key);
        } else if (key.equalsIgnoreCase("front office")) {
            baseLayer.add(frontOfficePane);

        } else if (key.equalsIgnoreCase("boss office")) {
            baseLayer.add(bossOfficePane);
        } else if (key.equalsIgnoreCase("break room")) {
            baseLayer.add(breakRoomPane);
        } else if (key.equalsIgnoreCase("hr office")) {
            baseLayer.add(hrOfficePane);
        } else if (key.equalsIgnoreCase("tech room")) {
            baseLayer.add(techRoomPane);
        } else if (key.equalsIgnoreCase("gas station")) {
            baseLayer.add(gasStationPane);
        } else if (P4.getText().length() == 0) {
            // Set to editable
            P2.setEditable(true);
            P2.setText("");
            //Get the resource from resources Photos
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            switch (key) {
                case "truck":
                    for (int i = 0; i < 21; i++) {
                        baseLayer.setBG(new ImageIcon(classloader.getResource("photos/animate/game-truck" + i + ".jpg")));
                    }
                    isIntro=false;
                    break;


                default:
                    MapImageIcon = new ImageIcon(
                            new ImageIcon(classloader.getResource("photos/" + key + ".png"))
                                    .getImage()
                                    .getScaledInstance(1220, 686, Image.SCALE_DEFAULT));
                    // Set Icon to new image Icon
                    baseLayer.setBG(MapImageIcon);
                    break;
            }
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

    public static JPanel getActionButtonContainer() {
        return BUTTON_ACTION_CONTAINER;
    }

    public void wipe() {
        P4.setText(null);
        P2.setText(null);
    }
    public static void setStateImages(String mapImages) {
        statePanel.setIcon(mapImages);
        baseLayer.revalidate();
    }
}