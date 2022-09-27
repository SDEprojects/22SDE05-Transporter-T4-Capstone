package com.tlglearning.gui;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class PromptContainer {

    private int row;
    private static final GridBagConstraints gbc = new GridBagConstraints();
    private static final ScrollablePanel content = new ScrollablePanel(new GridBagLayout());
    private static final JPanel panel = new JPanel();
    private static JScrollPane scrollPane;

    public PromptContainer() {
        init();
    }

    public void init() {
        content.setScrollableWidth( ScrollablePanel.ScrollableSizeHint.FIT );
        content.setOpaque(false);
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        scrollPane = new JScrollPane(content);
        scrollPane.setBorder(createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(600, 500));
        scrollPane.setMaximumSize(new Dimension(600,500));
//        scrollPane.setLocation(100,600);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        panel.setSize(new Dimension(1220, 500));
        panel.setPreferredSize(new Dimension(600, 500));
        panel.setMaximumSize(new Dimension(600, 500));
        panel.setLocation(0,186);
        panel.setOpaque(false);
        panel.add(scrollPane);
        panel.revalidate();
    }

    public void setPrompt(String prompt) {
        JPanel p = new JPanel(new BorderLayout())
        {
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };

        p.setBackground(new Color(255, 255, 255, 90));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        p.setOpaque(true);
        JTextArea text = new JTextArea();
        text.setOpaque(false);
        text.append(prompt);
        text.setLineWrap( true );
        text.setWrapStyleWord(true);
        text.setEditable(false);
        p.add(text);
        gbc.gridy = row++;
        content.add(p, gbc);
        content.revalidate();

        text.setFont(text.getFont().deriveFont(Font.BOLD, 16f));
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static void positionSouth() {
        scrollPane.setPreferredSize(new Dimension(600, 200));
        scrollPane.setMaximumSize(new Dimension(600,200));
//        scrollPane.setLocation(100,600);
        panel.setSize(new Dimension(1220, 500));
        panel.setLocation(0,450);
    }

    public static void wipe() {
        content.removeAll();
        content.revalidate();
    }

    public static void main(String[] args) throws Exception {
        new PromptContainer();
    }
}
