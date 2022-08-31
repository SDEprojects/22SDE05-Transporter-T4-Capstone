package com.tlglearning.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleScreen {

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
}
