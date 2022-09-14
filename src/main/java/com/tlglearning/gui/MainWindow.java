package com.tlglearning.gui;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class MainWindow {
    private JFrame window;
    private JTextArea app = new JTextArea(10,60);
    private String text;
    public MainWindow(){
        initialize();
    }

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
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
    panel.setBackground(Color.WHITE);

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
    this.text = "JTextArea uses these properties to indicate the preferred size of the viewport when placed" +
            "\nJTextArea uses these properties to indicate the preferred size of the viewport when placed" +
            "\nJTextArea uses these properties to indicate the preferred size of the viewport when placed" +
            "\nJTextArea uses these properties to indicate the preferred size of the viewport when placed" +
            "\nJTextArea uses these properties to indicate the preferred size of the viewport when placed";
    app.setLineWrap(true);
    app.setWrapStyleWord(true);
    app.append(text);
    panel.add(app);


    /**
     * Add JPanel to window.
     */
    window.add(panel, BorderLayout.CENTER);
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

    public void setMap(String str) {
        int end = this.text.length();
        setText(str);
        app.replaceRange(this.text,0,end);
    }
}
