package com.APP.Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.APP.ConnectionManager;

public class UserManager {
    String userid;
    String user_cal_goal = "1500";
    String user_name = "DefaultUserName";
    String user_gender = "F";
    int user_age = 28;
    int user_height = 160;
    int user_weight = 80;
    int user_wei_goal = 55;

    Connection con;

    /**
     * 
     * @param userid 유저아이디 (ex.20230117)
     */
    public UserManager(String userid) {
        this.userid = userid;

        try {
            ConnectionManager cm = new ConnectionManager();
            con = cm.get();

            String sql = "SELECT user_cal_goal, user_name, user_gender, user_age, user_height, user_weight, user_wei_goal FROM cal_user WHERE user_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userid);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user_cal_goal = rs.getString(1);
                user_name = rs.getString(2);
                user_gender = rs.getString(3);
                user_age = rs.getInt(4);
                user_height = rs.getInt(5);
                user_weight = rs.getInt(6);
                user_wei_goal = rs.getInt(7);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }  
    }

    public String getCalorieGoal() {
        return user_cal_goal;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserGender() {
        return user_gender;
    }

    public int getUserAge() {
        return user_age;
    }

    public int getUserHeight() {
        return user_height;
    }

    public int getUserWeight() {
        return user_weight;
    }

    public int getUserWeiGoal() {
        return user_wei_goal;
    }

    public void close() {
        try {
            con.close();
        } catch (Exception e) {
            
        }
    }

}
