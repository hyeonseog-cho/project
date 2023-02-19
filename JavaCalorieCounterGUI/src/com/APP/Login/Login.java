package com.APP.Login;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.APP.MainHome.AppMain;
import com.APP.UserInfo.AppUserInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

   public Login() throws SQLException, IOException{
      initalize();
   }
   JLabel login = new JLabel("Log in");
   JTextField textID = new JTextField();
   JPasswordField textPW = new JPasswordField();
   JButton jbutton1 = new JButton("Log in");
   JButton jbutton2 = new JButton("or,sign up");
   
   private void initalize() {
      setTitle("Home"); // 메인페이지
      setDefaultCloseOperation(EXIT_ON_CLOSE);
    //   setLocationRelativeTo(null);
      setLayout(null);
      setSize(540,735);
      Container container = getContentPane();

      // 로그인 라벨
      login.setBounds(90, 130, 200, 100);
      login.setFont(new Font("궁서", Font.BOLD, 50));
      container.add(login);

      // 아이디창
      textID.setBounds(70, 230, 400, 60);
      container.add(textID);

      // 비밀번호창
      textPW.setBounds(70, 330, 400, 60);
      container.add(textPW);

      // 로그인 버튼
      jbutton1.setBounds(60, 430, 400, 30);
      jbutton1.setBackground(new Color(0, 204, 204));
      container.add(jbutton1);
      jbutton1.addActionListener(this);

      // 회원가입 버튼
      jbutton2.setBounds(60, 465, 400, 30);
      container.add(jbutton2);
      jbutton2.addActionListener(this);

   }

   
    @Override
  public void actionPerformed(ActionEvent ae){
    Object obj = ae.getSource();
    
    if (obj instanceof JButton){
        JButton eventBtn = (JButton)obj;
        try {
            if(eventBtn.equals(jbutton1)){
                AppMain appMain = new AppMain();
                appMain.setVisible(true);
                this.dispose();
            } else if(eventBtn.equals(jbutton2)){
                AppUserInfo appUserInfo = new AppUserInfo();
                appUserInfo.setVisible(true);
                this.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  }

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Login login = new Login();
               login.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
}

// 구현해야하는 기능
// 0. 회원 가입을 했을 때 userInfo 화면으로 넘어가게
// 1. 회원 가입 했을 때 아이디가 이미 있으면, 이미 있다고 알려준다. ( 형민씨가 해결했음 )

// 3. 회원 가입을 했을 때 userInfo 화면으로 넘어가게 (구현 완료.)
// 4. 회원 가입으로 잘 못 들어갔을 때 다시 로그인 페이지로 올 수 있게 .. 버튼... 뭐든 만들어야되는데 (생략)

// 급한 것.
// 2. 회원 가입 했을 때 DB에 정보를 넘겨줘서 DB에 저장되게. >> 트리거써서. 회원가입 시간이 로그가 남게.!!! >>cal_user 바뀌어야함.
// 6. 로그인 했을 때 DB에 정보를 넘겨줘서 DB에 저장되게. >> 트리거써서. 로그인 시간이 로그에 남게 !!!
// 7. 로그인 했을 때 1) UserInfoPage에 정보가 저장될 수 있게 2) 1)이 되면 메인이 자동으로 바껴. >> 커서. //cal_user table
// 5. 로그인을 했을 때 "정보가 맞으면", 로그인이 되게 "정보가 맞지 않으면: 오류 메세지를 띄워야함." -> 메인페이지로 이동할 수 있게.
// USER_ID(pk:겹치면 안됨.) '20230113104717', USER_NAME 'jongjin', USER_GENDER 'M', USER_AGE '24', USER_HEIGHT '177', USER_WEIGHT '78', USER_WEI_GOAL '100', USER_CAL_GOAL '1500' (꼭 들어가야 하는 값?? 일단 보류)
// Varchar2(512);
// Int;