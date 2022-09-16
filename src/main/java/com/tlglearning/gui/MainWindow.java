package com.tlglearning.gui;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MainWindow {
    private JFrame window;
    private JTextArea p1 = new JTextArea(6,94);
    private ColorPane p2 = new ColorPane();
    private String text;
    private String titleText;
    public MainWindow(){
        initialize();
    }
    JPanel panel = new JPanel();
    /**
     * initialize() - initializes main window.
     */
    private void initialize() {
        /**
         * Create a main window panel and set attributes.
         */
        window = new JFrame();
        window.setLayout(new BorderLayout(10,5));
        window.setTitle("Transporter");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800,800);
        window.setLocationRelativeTo(null);

        /**
         * new JPanel - put on top of main window to embed elements.
         */

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.BLACK);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel2.setBackground(Color.BLACK);

        /**
         * Create a new button element. Add button to JPanel.
         */
        Button button = new Button("Button");
        button.addActionListener(e -> setMap("Text changed dynamically"));
        panel.add(button);

        /**
         * Create a new text area element. Add text area to label.
         * Add label to panel.
         */
        p1.setFont(new Font("Courier New", Font.PLAIN, 12));
        p1.setForeground(Color.WHITE);
        p1.setBackground(Color.BLACK);
        panel2.setOpaque(true);

        SimpleAttributeSet att = new SimpleAttributeSet();
        StyleConstants.setBold(att, true);
        StyleConstants.setBackground(att, Color.BLACK);
        p2.setCharacterAttributes(att, true);
        p2.setFont(new Font("Courier New", Font.PLAIN, 12));
        p2.setOpaque(false);

        panel.add(p2);
        panel2.add(p1);

        /**
         * Add JPanel to window.
         */
        window.add(panel2, BorderLayout.NORTH);
        window.add(panel, BorderLayout.CENTER);

        show();
    }

    /**
     * show() - display initialized window.
     */
    public void show() {
        window.setVisible(true);
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setTitleText(String title) {this.titleText = title;}

    public void setMap(String str) {

        p2.setEditable(true);
        clearMap();
        setText(str);
//        p2.setText("");
        p2.appendANSI(text);
        panel.revalidate();
        panel.repaint();
        try {
            Thread.sleep(100); // sleep/stop a thread for .1 second
        } catch(InterruptedException e) {
            System.out.println("An Exception occurred: " + e);
        }
        p2.setEditable(false);

    }

    public void setTitle(String str) {
        setTitleText(str);
        p1.append(titleText);
        p1.setEditable(false);
    }

    public void clearMap(){
        p2.setText("");
    }
}
