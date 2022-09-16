package com.tlglearning.gui;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MainWindow {

    private static final JTextArea P1 = new JTextArea(6,94);
    private static final JTextArea P2 = new JTextArea(180,40);
    private static final ColorPane P3 = new ColorPane();
    private String titleText;
    private String map;
    private String text;
    private static final JFrame APP_CONTAINER = new JFrame();
    private static final JPanel TITLE_CONTAINER = new JPanel();
    private static final JPanel MAP_CONTAINER = new JPanel();
    private static final JPanel PROMPT_CONTAINER = new JPanel();


    public MainWindow(){
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
        APP_CONTAINER.setLayout(new BorderLayout(10,5));
        APP_CONTAINER.setTitle("Transporter");
        APP_CONTAINER.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        APP_CONTAINER.setSize(800,800);
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
        P3.setCharacterAttributes(att, true);
        P3.setFont(new Font("Courier New", Font.PLAIN, 12));
        P3.setOpaque(false);

        /* Add basic GUI elements to their containers */
        TITLE_CONTAINER.add(P1);
        MAP_CONTAINER.add(P2);
        PROMPT_CONTAINER.add(P3);

        /* Add elements container to the main application */
        APP_CONTAINER.add(TITLE_CONTAINER, BorderLayout.NORTH);
        APP_CONTAINER.add(MAP_CONTAINER, BorderLayout.CENTER);
        APP_CONTAINER.add(PROMPT_CONTAINER, BorderLayout.SOUTH);

        /* Setting GUI visibility */
        show();
    }



/**
 * (NOT GUI!!) FIELD SETTER METHODS BELOW  ----------------------------------------------------------------------------|
 * --------------------------------------------------------------------------------------------------------------------|
 * --------------------------------------------------------------------------------------------------------------------|
 */

    public void setTitleText(String title) {this.titleText = title;}


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
            P2.replaceRange(map,0,end);

        } else {
            setMapChars(str);
            P2.append(map);
        }
        /* Sleep gui thread for .1 seconds for synchronicity */
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
            System.out.println("An Exception occurred: " + e);
        }
        P2.setEditable(false);
    }

    /**
     * setPrompt() - calls setPromptText and appends text to P3 JColorPane
     */
    public void setPrompt(String str) {
        P3.setEditable(true);
        /* TODO: if statement is a dirty fix to allow use of appendANSI method and needs replacing*/
        if (text == null) {
            P3.setText(".");
        }
        setPromptText(str);
        P3.appendANSI(text);
        PROMPT_CONTAINER.revalidate();
        PROMPT_CONTAINER.repaint();
        try {
            Thread.sleep(100); // sleep/stop a thread for .1 second
        } catch(InterruptedException e) {
            System.out.println("An Exception occurred: " + e);
        }
        P3.setEditable(false);
    }

}