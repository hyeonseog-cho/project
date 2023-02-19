package com.APP.CalcuCalories;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Container;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.APP.ConnectionManager;
import com.APP.Common.GlobalConstants;
import com.APP.MainHome.AppMain;
import com.APP.NavBar.bottomMenuPane;
public class UserCalGoal extends JFrame {
    JScrollPane sp =null;

    public UserCalGoal(){
        initialize();
    }
    private void initialize(){
        try {
            Container container = getContentPane();
            container.setLayout(new BorderLayout());
            //setResizable(false);
            setSize(560, 840);
            setTitle("목표 칼로리 계산");
    
            JPanel mainUserSelectPane = new mainUserSelectPane(this);
            JPanel bottomMenuPane = new bottomMenuPane(560, 840 / 8, this);
            
            // sp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            // sp.setViewportView(mainUserSelectPane);
            // sp.setPreferredSize(new Dimension(getWidth(), getHeight()-40));
            // sp.getVerticalScrollBar().setUnitIncrement(16);
            // sp.setBorder(null);
            // container.add(sp, BorderLayout.CENTER);
            container.add(mainUserSelectPane, BorderLayout.CENTER);
            container.add(bottomMenuPane, BorderLayout.SOUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class mainUserSelectPane extends JPanel implements ActionListener, GlobalConstants {
        JComboBox<String> UserSex;
        JPanel[] TitleBorder;
        JTextField[] UserInputField;
        JButton btnRecord, btnSubmit;   //Calculated Calories, submit
        JPanel GoalBorder;
        JComboBox<String> userGoal;     //현상유지, 적당한 다이어트, 빠른 다이어트
        JPanel calcuCalBtnPanel;
        JButton calcuCalBtn;
        JPanel userCalGoalPanel;
        JTextField userCalGoal;
        JPanel userSubmitBtnPanel;
        JButton userSubmitBtn;
        JPanel ActiveBorder;
        JComboBox<String> userActiveBox; 

        Double MifflinStJeor;
        Double DayNeedCal;
        Double exVal;

        String calgoalinput;
        
        
    
        String[] Sex = {"M", "F"};
        String[] UserTitle = {"Gender", "Age", "Height", "Weight"};
        String[] UserGoal={"현상유지", "적당한 다이어트(-0.5kg/주)", "빠른 다이어트(-1kg/주)"}; 
        String[] UserActive={"비활동적","저활동적","활동적","매우활동적"};
    
        String BtnStr = "<html><u>Calculated Calories</u></html>";
        JFrame jFrame;
        public mainUserSelectPane(JFrame jFrame) throws SQLException, IOException {
            if (calgoalinput == null) {
                calgoalinput = "0";
            }

            this.jFrame = jFrame;

            setPreferredSize(new Dimension(560,840));
            setBackground(new Color(45,99,255));
    //        setBackground(PRIMARY_COLOR);
    
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            setLayout(new GridBagLayout());
            TitleBorder = new JPanel[UserTitle.length];
            UserInputField = new JTextField[UserTitle.length];
            for (int i=0; i<TitleBorder.length; i++) {  
    
                TitleBorder[i] = new JPanel(new GridBagLayout());
                TitleBorder[i].setOpaque(true);
                TitleBorder[i].setBorder(BorderFactory.createTitledBorder(UserTitle[i]));
                TitleBorder[i].setBackground(new Color(127,185,255));
                gridBagConstraints.ipadx = 400;
                gridBagConstraints.ipady = 0;
                gridBagConstraints.insets = new Insets(0,0,10,0);
                gridBagConstraints.gridx = 0; 
                gridBagConstraints.gridy = i; 
                if (i == 0) {
                    UserSex = new JComboBox<String>(Sex);
                    UserSex.setPreferredSize(new Dimension(300, 20));
                    gridBagConstraints.ipadx = 340; 
                    TitleBorder[i].add(UserSex);
                } else {
                    UserInputField[i] = new JTextField();
                    UserInputField[i].setColumns(35);
                    UserInputField[i].setFont(new Font("맑은 고딕", Font.BOLD, 12));
                    TitleBorder[i].add(UserInputField[i]);
                }
                add(TitleBorder[i], gridBagConstraints);
            }

            //활동계수
            gridBagConstraints.ipadx = 400;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.insets = new Insets(0,0,10,0);
            gridBagConstraints.gridx = 0; 
            gridBagConstraints.gridy = 6; 
            ActiveBorder = new JPanel(new GridBagLayout());
            ActiveBorder.setOpaque(true);
            ActiveBorder.setBorder(BorderFactory.createTitledBorder("활동계수"));
            ActiveBorder.setBackground(new Color(127,185,255));
            userActiveBox = new JComboBox<String>(UserActive);
            userActiveBox.setPreferredSize(new Dimension(300, 20));
            gridBagConstraints.ipadx = 310; 
            ActiveBorder.add(userActiveBox);
            add(ActiveBorder, gridBagConstraints);

            //다이어트 목표
            gridBagConstraints.ipadx = 400;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.insets = new Insets(0,0,10,0);
            gridBagConstraints.gridx = 0; 
            gridBagConstraints.gridy = 7; 
            GoalBorder = new JPanel(new GridBagLayout());
            GoalBorder.setOpaque(true);
            GoalBorder.setBorder(BorderFactory.createTitledBorder("다이어트 목표"));
            GoalBorder.setBackground(new Color(127,185,255));
            userGoal = new JComboBox<String>(UserGoal);
            userGoal.setPreferredSize(new Dimension(300, 20));
            gridBagConstraints.ipadx = 225; 
            GoalBorder.add(userGoal);
            add(GoalBorder, gridBagConstraints);

    
            //
            calcuCalBtn=new JButton("칼로리 계산");
            gridBagConstraints.ipadx = 400;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.insets = new Insets(0,300,10,0);
            gridBagConstraints.gridx = 0; 
            gridBagConstraints.gridy = 8; 
            calcuCalBtnPanel = new JPanel(new GridBagLayout());
            calcuCalBtn.setPreferredSize(new Dimension(60, 40));
            calcuCalBtn.setOpaque(true);
            calcuCalBtn.setBackground(new Color(234,239,255));
            calcuCalBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            calcuCalBtnPanel.add(calcuCalBtn);
            calcuCalBtnPanel.setBackground(new Color(45,99,255));
            add(calcuCalBtnPanel, gridBagConstraints);


            //
            userCalGoalPanel = new JPanel(new GridBagLayout());
            userCalGoalPanel.setOpaque(true);
            userCalGoalPanel.setBorder(BorderFactory.createTitledBorder("칼로리 목표"));
            userCalGoalPanel.setBackground(new Color(127,185,255));
            gridBagConstraints.ipadx = 400;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.insets = new Insets(0,0,15,0);
            gridBagConstraints.gridx = 0; 
            gridBagConstraints.gridy = 9; 
            userCalGoal=new JTextField(35);
            userCalGoal.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            //userCalGoal.setPreferredSize(new Dimension(300, 20));
            userCalGoalPanel.add(userCalGoal);
            add(userCalGoalPanel, gridBagConstraints);
            

              //
              userSubmitBtn=new JButton("확인");
              gridBagConstraints.ipadx = 400;
              gridBagConstraints.ipady = 0;
              gridBagConstraints.insets = new Insets(0,340,10,0);
              gridBagConstraints.gridx = 0; 
              gridBagConstraints.gridy = 10;
              userSubmitBtnPanel = new JPanel(new GridBagLayout());
              userSubmitBtn.setPreferredSize(new Dimension(60, 40));
              userSubmitBtn.setOpaque(true);
              userSubmitBtn.setBackground(new Color(234,239,255));
              userSubmitBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
              userSubmitBtnPanel.setBackground(new Color(45,99,255));
              userSubmitBtnPanel.add(userSubmitBtn);
              add(userSubmitBtnPanel, gridBagConstraints);
  
              calcuCalBtn.addActionListener(this);
              userSubmitBtn.addActionListener(this);
                }
                
        @Override
        public void actionPerformed(ActionEvent e) { 
           
            Object obj=e.getSource();
            if(obj instanceof JButton){
                JButton jbutton=(JButton)obj;
                if(jbutton.equals(calcuCalBtn)){

                    String user_gender = (String)UserSex.getSelectedItem();
                    int user_age=Integer.parseInt(UserInputField[1].getText());
                    double user_height = Double.parseDouble(UserInputField[2].getText());
                    double user_weight = Double.parseDouble(UserInputField[3].getText());
                    String user_active=(String)userActiveBox.getSelectedItem();
                   
                    if (user_gender.equals("M")) {
                            MifflinStJeor = 6.25 * user_height + (10 * user_weight) - (5 * user_age) + 5;
                            if(user_active.equals("비활동적")) {
                                exVal = 1.0;
                            } else if (user_active.equals("저활동적")) {
                                exVal = 1.11;
                            } else if (user_active.equals("활동적")) {
                                exVal = 1.25;
                            } else if (user_active.equals("매우활동적")) {
                                exVal = 1.48;
                            } 
                        } else if (user_gender.equals("F")) {
                            MifflinStJeor = 6.25 * user_height + (10 * user_weight) - (5 * user_age) -161;
                            if(user_active.equals("비활동적")) {
                                exVal = 1.0;
                            } else if (user_active.equals("저활동적")) {
                                exVal = 1.12;
                            } else if (user_active.equals("활동적")) {
                                exVal = 1.27;
                            } else if (user_active.equals("매우활동적")) {
                                exVal = 1.45;
                            } 
                        }
                        DayNeedCal = exVal* MifflinStJeor;
                        String user_cal_goal= Integer.toString((int) Math.round(DayNeedCal));
                        userCalGoal.setText(user_cal_goal);
                        calgoalinput = user_cal_goal;
                }
                else if(jbutton.equals(userSubmitBtn)){
                    String bb = userCalGoal.getText();                   

                    //update문
                    //String user_id=??
                    String sql="update cal_user set user_cal_goal= ? where user_id = 20230117";
                   
                    System.out.println("bb: " +bb);
                    //System.out.println("user_id: " +user_id);
                    try {
                        ConnectionManager connectionManager = new ConnectionManager();
                        Connection conn = connectionManager.get();
                        PreparedStatement pstmt=null;
                        
                        pstmt=conn.prepareStatement(sql);
                        pstmt.setInt(1,Integer.parseInt(bb));
                        //pstmt.setString(2,user_id);
                        int res=pstmt.executeUpdate();
                            
                        pstmt.close();
                        conn.close();
                        if(res==1){
                            JOptionPane.showMessageDialog(null, "업데이트 성공", "성공", JOptionPane.PLAIN_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "에러", "에러", JOptionPane.ERROR_MESSAGE);
                        }

                        //UserInputTxt.userInputTxt.set(6, bb);
                        AppMain mainFrame = new AppMain();
                        mainFrame.setLocationRelativeTo(jFrame);
                        mainFrame.setVisible(true);
                        jFrame.dispose();

                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }


                }
            }
        }
     } 
            
    public static void main(String[] args) throws SQLException, IOException{
		
		UserCalGoal userCalGoal = new UserCalGoal();
        userCalGoal.setVisible(true);
		
	}
}

// Double MifflinStJeor;
// Double DayNeedCal;
// Double exVal;
// if (남자) {
//     MifflinStJeor = 6.25 * 키 + (10 * 체중) - (5 * 나이) + 5;
//     if(비동적) {
//         exVal = 1.0;
//     } else if (저활동적) {
//         exval = 1.11;
//     } else if (활동적) {
//         exval = 1.25;
//     } else if (매우 활동적) {
//         exval = 1.48;
//     } 
// } else if (여자) {
//     MifflinStJeor = 6.25 * 키 + (10 * 체중) - (5 * 나이) -161;
//     if(비동적) {
//         exVal = 1.0;
//     } else if (저활동적) {
//         exval = 1.12;
//     } else if (활동적) {
//         exval = 1.27;
//     } else if (매우 활동적) {
//         exval = 1.45;
//     } 
// }
// DayNeedCal = exVal* MifflinStJeor;
