package com.APP.NavBar;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.APP.CalcuCalories.UserCalGoal;
import com.APP.Calendar.AppCalendar;
import com.APP.DayCalories.AppDayCalories;
import com.APP.Drink.AppDrink;
import com.APP.FoodSearch.AppFoodSearch;
import com.APP.FoodSearch.UploadFoodInfo.UploadFoodInfo;
import com.APP.MainHome.AppMain;
import com.APP.UserInfo.AppUserInfo;

public interface NavWindowCtrl{
    AppMain appMain = new AppMain();
    AppDayCalories appDayCalories = new AppDayCalories();
    AppCalendar appCalendar = new AppCalendar();
    AppDrink appDrink = new AppDrink();
    AppFoodSearch appFoodSearch = new AppFoodSearch();
    AppUserInfo appUserInfo = new AppUserInfo();
    UploadFoodInfo uploadFoodInfo = new UploadFoodInfo();
    UserCalGoal userCalGoal = new UserCalGoal();
    

    public class NavigationController implements ActionListener{

        static int CurrentWindow = 0;

        public static int getCurrentWindow() {
            return CurrentWindow;
        }

        public static void setCurrentWindow(int currentWindow) {
            CurrentWindow = currentWindow;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    public class PageOpen extends NavigationController {
        // static int windowStatus;
        // [ number]            [Window]
        //     0                AppMain
        //     1                AppDayCalories
        //     2                AppFoodSearch
        //     3                AppCalendar
        //     4                AppDrink
        //     5                AppUserInfo
        //     6                UploadFoodInfo
        //     7                UserCalGoal
        public PageOpen() {
            super();
            try {
                if (CurrentWindow == 0) {
                    appMain.setLocationRelativeTo(null);
                    appMain.setVisible(true);
                    
                    // appMain.dispose();
                    appDayCalories.dispose();
                    appFoodSearch.dispose();
                    appCalendar.dispose();
                    appDrink.dispose();
                    appUserInfo.dispose();
                    uploadFoodInfo.dispose();
                    userCalGoal.dispose();
                    
                } else if (CurrentWindow == 1) {
                    appDayCalories.setLocationRelativeTo(null);
                    appDayCalories.setVisible(true);
                    
                    appMain.dispose();
                    // appDayCalories.dispose();
                    appFoodSearch.dispose();
                    appCalendar.dispose();
                    appDrink.dispose();
                    appUserInfo.dispose();
                    uploadFoodInfo.dispose();
                    userCalGoal.dispose();
                    
                } else if (CurrentWindow == 2) {
                    appFoodSearch.setLocationRelativeTo(null);
                    appFoodSearch.setVisible(true);
                    
                    appMain.dispose();
                    appDayCalories.dispose();
                    // appFoodSearch.dispose();
                    appCalendar.dispose();
                    appDrink.dispose();
                    appUserInfo.dispose();
                    uploadFoodInfo.dispose();
                    userCalGoal.dispose();
                    
                } else if (CurrentWindow == 3) {
                    appCalendar.setLocationRelativeTo(null);
                    appCalendar.setVisible(true);
                    
                    appMain.dispose();
                    appDayCalories.dispose();
                    appFoodSearch.dispose();
                    // appCalendar.dispose();
                    appDrink.dispose();
                    appUserInfo.dispose();
                    uploadFoodInfo.dispose();
                    userCalGoal.dispose();

                } else if (CurrentWindow == 4) {
                    appDrink.setLocationRelativeTo(null);
                    appDrink.setVisible(true);
                    
                    appMain.dispose();
                    appDayCalories.dispose();
                    appFoodSearch.dispose();
                    appCalendar.dispose();
                    // appDrink.dispose();
                    appUserInfo.dispose();
                    uploadFoodInfo.dispose();
                    userCalGoal.dispose();

                } else if (CurrentWindow == 5) {
                    appUserInfo.setLocationRelativeTo(null);
                    appUserInfo.setVisible(true);
                    
                    appMain.dispose();
                    appDayCalories.dispose();
                    appFoodSearch.dispose();
                    appCalendar.dispose();
                    appDrink.dispose();
                    // appUserInfo.dispose();
                    uploadFoodInfo.dispose();
                    userCalGoal.dispose();

                } else if (CurrentWindow == 6) {
                    uploadFoodInfo.setLocationRelativeTo(null);
                    uploadFoodInfo.setVisible(true);
                    
                    appMain.dispose();
                    appDayCalories.dispose();
                    appFoodSearch.dispose();
                    appCalendar.dispose();
                    appDrink.dispose();
                    appUserInfo.dispose();
                    // uploadFoodInfo.dispose();
                    userCalGoal.dispose();

                } else if (CurrentWindow == 7) {
                    userCalGoal.setLocationRelativeTo(null);
                    userCalGoal.setVisible(true);

                    appMain.dispose();
                    appDayCalories.dispose();
                    appFoodSearch.dispose();
                    appCalendar.dispose();
                    appDrink.dispose();
                    appUserInfo.dispose();
                    uploadFoodInfo.dispose();
                    // UserCalGoal.dispose();

                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
