package com.tlglearning.gui;

import javax.swing.*;
import java.awt.*;

public class BaseLayer {
    private static final int WIDTH = 1220;
    private static final int HEIGHT = 690;
    private static JLayeredPane panel;
    private static JLabel backgroundContainerLabel;
    static JLayeredPane backgroundPanel = new JLayeredPane();

    public BaseLayer(ImageIcon background){
        init(background);
    }
    public void init(ImageIcon background) {
        panel = new JLayeredPane();
        backgroundContainerLabel = new JLabel();

        panel.setLayout(null);

        panel.setSize(new Dimension(WIDTH, HEIGHT));
        panel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        panel.setLocation(0,0);


        backgroundContainerLabel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        backgroundContainerLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        backgroundContainerLabel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        backgroundContainerLabel.setIcon(background);
        backgroundContainerLabel.setOpaque(true);

//        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setSize(new Dimension(WIDTH, HEIGHT));
        backgroundPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        backgroundPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        backgroundPanel.setLayout(new BorderLayout(0,0));
        backgroundPanel.setBackground(Color.blue);

        backgroundPanel.add(backgroundContainerLabel);

        panel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

    }
    public JLayeredPane getPanel(){
        return panel;
    }

    public static void setBG(ImageIcon background) {
        backgroundContainerLabel.setIcon(background);
    }

    public static void add(JPanel layer){

        panel.add(layer, JLayeredPane.PALETTE_LAYER);
    }

    public static void add(JLayeredPane layer){
        panel.add(layer, 0);
    }

    public static void addModal(JPanel layer) {
        panel.add(layer, JLayeredPane.MODAL_LAYER);
    }


    public static void revalidate() {
        panel.revalidate();
        panel.repaint();
    }
//    public static void setBackGroudPanel(JLayeredPane backPanel){
//        backgroundPanel=backPanel;
//        panel.add(backgroundPanel);
//        panel=null;
//        System.out.println("asdfsdfsdf");
//
//
//    }

}
