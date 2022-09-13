package com.tlglearning.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleScreen {

    String story = "This is a story of XYZ, a Truck Driver who works really hard to feed his family.";
    String objective = "Your goal is to pickup the payloads from specific location and deliver it to the destination. \n\t\t\tBefore you head out for hitting the road, you need to collect certain require item else you won't be able to drive.";
    String win_game = "You need to make a successful delivery to win the game cannot cross the international border";
    String play = "You need to go room by room to collect the items. When you are inside the room, \nTo check the item you can 'Explore <item>'.\nTo pick item, you can 'Pick <item>.\nTo move, you can do 'Go <direction>'.";

    public void titleScreen() {
        int width = 200;
        int height = 9;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();

        Graphics2D graphics = (Graphics2D) graphic;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("TRANSPORTER", 0, 9);

        for(int i = 0; i < height; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < width; j++) {
                sb.append(image.getRGB(j, i) == -16777216 ? " " : "$");
            }
            if(sb.toString().trim().isEmpty()){
                continue;
            }
            System.out.println(sb);
        }
    }

    public void intro() {

        System.out.printf("\n\nSTORY: %s", story);
        System.out.printf("\nOBJECTIVE: %s", objective);
        System.out.printf("\nHOW TO WIN: %s", win_game);
        System.out.printf("\nHOW TO PLAY: %s", play);
    }
}
