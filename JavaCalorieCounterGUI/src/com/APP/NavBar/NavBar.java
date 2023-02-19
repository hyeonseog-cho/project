package com.APP.NavBar;

import javax.swing.*;

import com.APP.MainHome.AppMain;

import java.awt.*;

class navBtn extends JButton {
    navBtn(ImageIcon icon) {
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(70,70, Image.SCALE_SMOOTH);

        setHorizontalTextPosition(navBtn.CENTER);
        setVerticalTextPosition(navBtn.BOTTOM);
        setIcon(new ImageIcon(changeImg));

//        setPreferredSize(new Dimension(btnSize, btnSize));

        setBorderPainted(false);
        setContentAreaFilled(false);

        setOpaque(false);
    }
}
public class NavBar {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppMain appMain = new AppMain();
                    appMain.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
