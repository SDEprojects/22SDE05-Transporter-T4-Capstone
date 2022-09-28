//package com.tlglearning.gui.music;
//
//import com.tlglearning.gui.crop.Crop;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.swing.*;
//import java.awt.*;
//import java.io.IOException;
//
//public class actionRadio extends JFrame {
//    static Crop cropImage = new Crop();
//
//    static int layerPaneWidth = 400;
//
//    static int layerPaneHeight = 400;
//
//
//
//    public actionRadio() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
////       audioPlayer =
////                new SimpleAudioPlayer();
//    }
//
//    public static JLayeredPane getPanel() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        radioButtonListener.setUpRadio();
//
//
//
//        JLayeredPane layeredPane = new JLayeredPane();
//
//
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//
//        // Retrieves image crop
//        JButton buttonCabinet = setActionButton(35, 90, 0, 0, "0", "1");
//
//        JButton playButton = setActionButton(35, 90, 0, 0, "0", "1");
//
//        JButton pauseButton = setActionButton(35, 90, 0, 0, "0", "1");
//
//        Image orginalImage = new ImageIcon(classloader.getResource("photos/changeStation.png")).getImage().getScaledInstance(layerPaneWidth, layerPaneHeight, Image.SCALE_DEFAULT);
//        ImageIcon orginalIcon=new ImageIcon(orginalImage);
//        orginalIcon.setDescription("changeStation");
//
//        JLabel wareJLabel = new JLabel(orginalIcon);
//
//        wareJLabel.setLocation(buttonCabinet.getX(), buttonCabinet.getY());
//        wareJLabel.setLocation(playButton.getX(), playButton.getY());
//        wareJLabel.setSize(orginalIcon.getIconWidth(), orginalIcon.getIconHeight());
//
//        // Shift labels towards center of display.
//        wareJLabel.setLocation(680,20);
//        buttonCabinet.setLocation(705,320);
//        playButton.setLocation(805, 320);
//        pauseButton.setLocation(905,320);
//        layeredPane.add(wareJLabel, 1);
//        layeredPane.add(buttonCabinet, 0);
//        layeredPane.add(playButton, 2);
//        layeredPane.setOpaque(true);
////        layeredPane.setBackground(Color.BLACK);
//        layeredPane.setSize(1500,1500);
//        return layeredPane;
//    }
//
//    public static JButton setActionButton(int width, int height, int X_Location, int Y_Location, String orginalImagePath, String actionImagePath) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        Image orginalImage = new ImageIcon(classloader.getResource("photos/stop.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
//        ImageIcon orginalIcon = new ImageIcon(orginalImage);
//        Image actionImage = new ImageIcon(classloader.getResource("photos/stop.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//
//        new ImageIcon(classloader.getResource("photos/play.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
//        new ImageIcon(classloader.getResource("photos/play.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//
//        new ImageIcon(classloader.getResource("photos/pause.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
//        new ImageIcon(classloader.getResource("photos/pause.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//
//
//        ImageIcon actionIcon = new ImageIcon(actionImage);
//
//        ImageIcon stoplayerImageCrop = actionIcon;
//
//        JButton button = new JButton();
//        button.setSize(width, height);
//
//        button.addMouseListener(new radioButtonListener(button, orginalIcon, stoplayerImageCrop));
//
//        return button;
//    }
//
//
//}
//
