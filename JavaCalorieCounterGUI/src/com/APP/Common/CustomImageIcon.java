package com.APP.Common;

import javax.swing.*;
import java.awt.*;

public class CustomImageIcon extends ImageIcon {
    private Image image;

    public Image Main(String str) {
        if (str.equals("MyInfoBtn")) {
            ImageIcon imageIcon = new ImageIcon("src/img/user.png");
            Image img = imageIcon.getImage();
            Image changeImg = img.getScaledInstance(150,150, Image.SCALE_SMOOTH);
            return changeImg;
        }
        return image;
    }
}
