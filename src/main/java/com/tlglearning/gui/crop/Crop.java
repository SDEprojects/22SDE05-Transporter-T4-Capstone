package com.tlglearning.gui.crop;

import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class Crop extends JFrame{

    public ImageIcon crop(ImageIcon image, int x,int y,int width,int height) {
        Image cabinetImage = createImage(new FilteredImageSource(image.getImage().getSource(), new CropImageFilter(x, y, width , height )));
        ImageIcon tempIcon = new ImageIcon();
        tempIcon.setImage(cabinetImage);
        return tempIcon;

    }
}
