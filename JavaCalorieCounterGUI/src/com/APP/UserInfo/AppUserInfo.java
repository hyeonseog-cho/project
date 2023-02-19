package com.APP.UserInfo;

import java.awt.*;
import javax.swing.*;
import com.APP.NavBar.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.APP.ConnectionManager;
import com.APP.CalcuCalories.UserCalGoal;
import com.APP.Common.GlobalConstants;
import com.APP.Common.JPanelSize;
import com.APP.Common.UserManager;
import com.APP.MainHome.AppMain;

public class AppUserInfo extends JFrame implements GlobalConstants{

    public AppUserInfo()  {
        initialize();
    }

    private void initialize() {
  //        initialize();
        setTitle("User Info");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setUIFont (new javax.swing.plaf.FontUIResource("굴림",Font.BOLD,12));
        
        try {
        // container set
        Container container = getContentPane();
        // container.setBackground(new Color(255, 247, 225)); // HEX color #FFF7E1, Color name: Corn Silk
  
        // Size and layout
        JPanelSize jPanelSize = new JPanelSize();
        int BasicWidth, BasicHeight;
        BasicWidth = jPanelSize.getWidth();
        BasicHeight = jPanelSize.getHeight();
  
        setSize(BasicWidth,BasicHeight);
        setLayout(new BorderLayout());
        // JPanel setting
        JPanel mainUserSelectPane = new mainUserSelectPane(BasicWidth, (BasicHeight/8)*7, this);
        JPanel bottomMenuPane= new bottomMenuPane(BasicWidth, BasicHeight/8, this);
  
        container.add(mainUserSelectPane, BorderLayout.CENTER);
        container.add(bottomMenuPane, BorderLayout.SOUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put (key, f);
      }
    }
    // main script(super)
    // public static void main(String[] args) {
    //     EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             try {
    //                 AppUserInfo appUserInfo = new AppUserInfo();
    //                 appUserInfo.setVisible(true);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     });
    // }
}

class mainUserSelectPane extends JPanel implements ActionListener, GlobalConstants {

    //
    // private PreparedStatement statement= null;
    JComboBox<String> UserSex;
    JPanel[] TitleBorder;
    JTextField[] UserInputField;
    JButton btnRecord, btnSubmit;   //Calculated Calories, submit

    String userid;
    UserManager um;

    String[] Sex = {"M", "F"};
    String[] UserTitle = {"이름", "성별(M:남|F:여)", "나이", "키", "몸무게", "목표 체중", "목표 칼로리"};

    String BtnStr = "<html><u>칼로리 계산 >></u></html>";
    JFrame jFrame;
    public mainUserSelectPane(int BasicWidth, int BasicHeight, JFrame jFrame) throws SQLException, IOException {
        if (userid == null) {
            userid = "20230117";
        }

        um = new UserManager(userid);

        this.jFrame = jFrame;
        setPreferredSize(new Dimension(BasicWidth,BasicHeight));
        setBackground(PRIMARY_COLOR);
        // setBackground(new Color(255, 247, 225)); // HEX color #FFF7E1, Color name: Corn Silk
        // setOpaque(false);
        // setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(new GridBagLayout());

        TitleBorder = new JPanel[UserTitle.length];
        UserInputField = new JTextField[UserTitle.length];
        //String operSys = System.getProperty("os.name").toLowerCase();
        //System.out.println(operSys);
        for (int i=0; i<TitleBorder.length; i++) {
            
            TitleBorder[i] = new JPanel(new GridBagLayout());
            TitleBorder[i].setOpaque(true); // transparent setting for background
            TitleBorder[i].setBackground(new Color(127,185,255));
            TitleBorder[i].setBorder(BorderFactory.createTitledBorder(UserTitle[i]));
            gridBagConstraints.ipadx = 400; // Size setting of JTextField using x-width
            gridBagConstraints.ipady = 20;
            gridBagConstraints.insets = new Insets(10,0,10,0);
            gridBagConstraints.gridx = 0; // Grid x position
            gridBagConstraints.gridy = i; // Grid y position
            if (i == 1) {
                UserSex = new JComboBox<String>(Sex);
                UserSex.setPreferredSize(new Dimension(300,20));
                gridBagConstraints.ipadx = 310; // Size setting of JTextField using x-width
                TitleBorder[i].add(UserSex);
            } else {
                UserInputField[i] = new JTextField();
                String txt = "default";
                switch (i) {
                    case 0:
                    txt = um.getUserName();
                    break;
                    case 2:
                    txt = String.valueOf(um.getUserAge());
                    break;
                    case 3:
                    txt = String.valueOf(um.getUserHeight());
                    break;
                    case 4:
                    txt = String.valueOf(um.getUserWeight());
                    break;
                    case 5:
                    txt = String.valueOf(um.getUserWeiGoal());
                    break;
                    case 6:
                    txt = um.getCalorieGoal();
                    break;
                }
                UserInputField[i].setText(txt);
                UserInputField[i].setColumns(35);

                TitleBorder[i].add(UserInputField[i]);
            }
            add(TitleBorder[i], gridBagConstraints);
        }
        

    JPanel btnLabel = new JPanel(); // 버튼 크기를 조절하기위한 패널
    btnLabel.setBackground(new Color(45,99,255)); 
    btnSubmit = new JButton("Submit");
    btnSubmit.setOpaque(true); // transparent
    btnSubmit.setBackground(new Color(170,191,255));
    btnSubmit.setForeground(new Color(45,99,255));
    btnSubmit.setPreferredSize(new Dimension(200,40));
    btnSubmit.setFont(new Font("맑은 고딕", Font.BOLD, 22));
    gridBagConstraints.insets = new Insets(0,0,0,0);
    gridBagConstraints.gridx = 0; // Grid x position
    gridBagConstraints.gridy = 7; // Grid y position
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    btnLabel.add(btnSubmit);
    add(btnLabel, gridBagConstraints);
    btnSubmit.addActionListener(this);

    btnRecord = new JButton(BtnStr);
    btnRecord.setBorderPainted(false); // remove border
    btnRecord.setContentAreaFilled(false); // remove fill
    btnRecord.setFocusPainted(true); // remove mouse focus
    btnRecord.setOpaque(false); // transparent
    gridBagConstraints.ipadx = 400;  // Size setting of button using x-width
    gridBagConstraints.insets = new Insets(0,300,0,0);
    gridBagConstraints.gridx = 0; // Grid x position
    gridBagConstraints.gridy = 8; // Grid y position
    gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
    add(btnRecord, gridBagConstraints);
    btnRecord.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae){
      
        Object obj = ae.getSource();
        String user_name,user_gender,user_age,user_height,user_weight,user_wei_goal,user_cal_goal;

        if (obj instanceof JButton){
            JButton eventBtn = (JButton)obj;
    
            if(eventBtn.equals(btnSubmit)) {
                AppMain appMain = new AppMain();
                appMain.setVisible(true);


                Connection con = null;
				PreparedStatement insertPreparedStatement = null;
        
                user_name = UserInputField[0].getText();
                user_gender = (String)UserSex.getSelectedItem();
                user_age = UserInputField[2].getText();
                user_height = UserInputField[3].getText();
                user_weight = UserInputField[4].getText();
                user_wei_goal = UserInputField[5].getText();
                user_cal_goal = UserInputField[6].getText();
                

				String insertQue = "UPDATE cal_user SET user_name = ?, user_gender = ?, user_age = ?, user_height = ?, user_weight = ?, user_wei_goal = ?, user_cal_goal = ? WHERE user_id = ?";
                // System.out.println(insertQue);
                try {
                    ConnectionManager cm = new ConnectionManager();
                    con = cm.get();

                    insertPreparedStatement = con.prepareStatement(insertQue);

                    insertPreparedStatement.setString(1, user_name);
                    insertPreparedStatement.setString(2, user_gender);
                    insertPreparedStatement.setInt(3, Integer.parseInt(user_age));
                    insertPreparedStatement.setInt(4, Integer.parseInt(user_height));
                    insertPreparedStatement.setInt(5, Integer.parseInt(user_weight));
                    insertPreparedStatement.setInt(6, Integer.parseInt(user_wei_goal));
                    insertPreparedStatement.setInt(7, Integer.parseInt(user_cal_goal));
                    insertPreparedStatement.setString(8, userid);
                    insertPreparedStatement.executeUpdate();


                    JOptionPane.showMessageDialog(null, "DB의 정보를 수정했습니다!", "성공!", JOptionPane.PLAIN_MESSAGE);
                    
                    insertPreparedStatement.close();
                    con.close();

                    AppMain mainFrame = new AppMain();
                    mainFrame.setLocationRelativeTo(jFrame);
                    mainFrame.setVisible(true);
                    jFrame.dispose();
                } catch (SQLException | IOException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "올바른 숫자를 넣어주세요");
                }

            } else if (eventBtn.equals(btnRecord)) {
                UserCalGoal userCalGoal = new UserCalGoal();
                userCalGoal.setVisible(true);
                System.out.println("다른 페이지로 이동");
            }
        }
    }
}