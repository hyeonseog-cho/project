package com.APP.NavBar;

import com.APP.Calendar.AppCalendar;
import com.APP.Common.GlobalConstants;
import com.APP.DayCalories.AppDayCalories;
import com.APP.Drink.AppDrink;
import com.APP.FoodSearch.AppFoodSearch;
import com.APP.MainHome.AppMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

// section_4: Bottom
//          0) name: navigation bar
//          1) position : Bottom
//          2) type: JPanel, JButton
//          3) object: "convert Frame" using JButton in navigation bar
public class bottomMenuPane extends JPanel implements GlobalConstants, ActionListener, WindowConstants, NavWindowCtrl{
    JButton btnNavMain, btnNavDayCal, btnNavFood, btnNavCalendar, btnNavDrink;
    JFrame parentFrame;

    public bottomMenuPane(int width, int height, JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setPreferredSize(new Dimension(width, height));
        setBackground(PRIMARY_COLOR);
        // setBackground(new Color(255, 247, 225)); // HEX color #FFF7E1, Color name: Corn Silk
        setLayout(new GridLayout(1, 5, 20, 20));

        btnNavMain = new navBtn(new ImageIcon(navBtnHome));
        btnNavDayCal = new navBtn(new ImageIcon(navBtnDayCal));
        btnNavFood = new navBtn(new ImageIcon(navBtnFood));
        btnNavCalendar = new navBtn(new ImageIcon(navBtnCalendar));
        btnNavDrink = new navBtn(new ImageIcon(navBtnDrink));

        for (JButton jButton : Arrays.asList(btnNavMain, btnNavDayCal, btnNavFood, btnNavCalendar, btnNavDrink)) {
            jButton.addActionListener(this);
            add(jButton);
        }

    }
    @Override
    public void actionPerformed(ActionEvent ae){
        Object obj = ae.getSource();
            if (obj instanceof JButton){
                JButton eventBtn = (JButton)obj;

                if(eventBtn.equals(btnNavMain)){
                    AppMain appMain = new AppMain();
                    appMain.setVisible(true);
                    appMain.setLocationRelativeTo(parentFrame);
                    // NavigationController.setCurrentWindow(0);
                    // System.out.println(NavigationController.getCurrentWindow());
                    // PageOpen pageOpen = new PageOpen();
                    
                } else if (eventBtn.equals(btnNavDayCal)) {
                    AppDayCalories appDayCalories = new AppDayCalories();
                    appDayCalories.setVisible(true);
                    appDayCalories.setLocationRelativeTo(parentFrame);
                    // NavigationController.setCurrentWindow(1);
                    // System.out.println(NavigationController.getCurrentWindow());
                    // PageOpen pageOpen = new PageOpen();
                } else if (eventBtn.equals(btnNavFood)) {
                    AppFoodSearch appFoodSearch = new AppFoodSearch();
                    appFoodSearch.setVisible(true);
                    appFoodSearch.setLocationRelativeTo(parentFrame);
                    // try {
                        
                        // NavigationController.setCurrentWindow(2);
                        // System.out.println(NavigationController.getCurrentWindow());
                        // PageOpen pageOpen = new PageOpen();
                    // } catch (Exception e) {
                    //     e.printStackTrace();
                    // }
                } else if (eventBtn.equals(btnNavCalendar)) {
                    AppCalendar appCalendar = new AppCalendar();
                    appCalendar.setVisible(true);
                    appCalendar.setLocationRelativeTo(parentFrame);
                    // NavigationController.setCurrentWindow(3);
                    // System.out.println(NavigationController.getCurrentWindow());
                    // PageOpen pageOpen = new PageOpen();
                
                } else if(eventBtn.equals(btnNavDrink)){
                    AppDrink appDrink = new AppDrink();
                    appDrink.setVisible(true);
                    appDrink.setLocationRelativeTo(parentFrame);
                    // NavigationController.setCurrentWindow(4);
                    // System.out.println(NavigationController.getCurrentWindow());
                    // PageOpen pageOpen = new PageOpen();
                }
                parentFrame.dispose();
            }
        }
    
    // public static void main(String[] args) {
    //     EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             try {
    //                 AppMain appMain = new AppMain();
    //                 appMain.setVisible(true);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     });
    // }
}
