package com.APP.MainHome;

import java.awt.*;
import javax.swing.*;
import java.awt.EventQueue;
import com.APP.NavBar.*;
import com.APP.NavBar.NavWindowCtrl.NavigationController;
import com.APP.NavBar.NavWindowCtrl.PageOpen;
import com.APP.UserInfo.AppUserInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.APP.CalcuCalories.UserCalGoal;
import com.APP.Calendar.AppCalendar;
import com.APP.Common.GlobalConstants;
import com.APP.Common.JPanelSize;
import com.APP.Common.ThreadCustom;
import com.APP.Common.UserManager;
import com.APP.DayCalories.FoodManager;

//https://www.figma.com/proto/iHIJ3wf1ICBkt4MvZ8J0xj/%EC%B9%BC%EB%A1%9C%EB%A6%AC%EC%95%B1?node-id=18%3A1899&scaling=scale-down&page-id=0%3A1&starting-point-node-id=5%3A182
public class AppMain extends JFrame implements GlobalConstants{

  UserManager um;

  public AppMain() {
    um = new UserManager("20230117");
    initialize();
  }

  private void initialize() {  
      setTitle("Home"); // 메인페이지
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      setUIFont (new javax.swing.plaf.FontUIResource("굴림",Font.BOLD,12));
      // pack();
      pack();
      // setLocationRelativeTo(null);
      // System.out.println(SwingUtilities.getWindowAncestor(this));

      // container set
      Container container = getContentPane();
      // container.setBackground(PRIMARY_COLOR);
      // container.setBackground(new Color(255, 247, 225)); // HEX color #FFF7E1, Color name: Corn Silk

      // Size and layout
      JPanelSize jPanelSize = new JPanelSize();
      int BasicWidth, BasicHeight;
      BasicWidth = jPanelSize.getWidth();
      BasicHeight = jPanelSize.getHeight();

      setSize(BasicWidth,BasicHeight);
      setLayout(new BorderLayout());

      // JPanel setting
      JPanel TopUserInfoPane = new TopUserInfoPane(BasicWidth, (BasicHeight/8)*2, um, this);
      JPanel MainWeightGoal = new MainWeightGoal(BasicWidth, (BasicHeight/8)*2, um, this);
      JPanel MainDayCalories = new MainDayCalories(BasicWidth, (BasicHeight/8)*2, um, this);
      JPanel MainDayThread = new MainDayThread(BasicWidth, BasicHeight/8);
      JPanel bottomMenuPane = new bottomMenuPane(BasicWidth, BasicHeight/8, this);

      JPanel MainjPanel = new JPanel();
      MainjPanel.setLayout(new BorderLayout());

      container.add(TopUserInfoPane, BorderLayout.NORTH);
      container.add(MainjPanel, BorderLayout.CENTER);
      container.add(bottomMenuPane, BorderLayout.SOUTH);

      MainjPanel.add(MainWeightGoal, BorderLayout.NORTH);
      MainjPanel.add(MainDayCalories, BorderLayout.CENTER);
      MainjPanel.add(MainDayThread, BorderLayout.SOUTH);

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
class TopUserInfoPane extends JPanel implements ActionListener, GlobalConstants {
  private String UserName, UserSex, UserAge, UserHeight;

  private JButton btnInfoChange;
  private JFrame jFrame;
  public TopUserInfoPane(int BasicWidth, int BasicHeight, UserManager um, JFrame jFrame) {
    this.jFrame = jFrame;
    setPreferredSize(new Dimension(BasicWidth,BasicHeight));
    setBackground(PRIMARY_COLOR);
    // setBackground(new Color(255, 247, 225)); // HEX color #FFF7E1, Color name: Corn Silk
    // setOpaque(false);
    // setBorder(BorderFactory.createLineBorder(Color.BLACK));
    setLayout(null);
    setBorder(null);

    JPanel Userinfo = new JPanel(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    // Userinfo.setBackground(new Color(245,218,223)); // HEX COLOR: #F5DADF, Color name: Light pink
    // Userinfo.setBackground(new Color(62,62,66)); // #3e3e42	(62,62,66)
    // Userinfo.setBackground(new Color(0,122,204)); // #007acc	(0,122,204)
    Userinfo.setBackground(SECONDARY_COLOR); // HEX color #7fb9ff, Color name:
    Userinfo.setBounds(20,30,300,150);
    add(Userinfo);

    JButton jButton = new JButton();
    ImageIcon imageIcon = new ImageIcon(AdminUserIcon);
    Image img = imageIcon.getImage();
    Image changeImg = img.getScaledInstance(80,80, Image.SCALE_SMOOTH);
    jButton.setIcon(new ImageIcon(changeImg));
    
    // gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(0,10,0,0);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.LINE_START;
    Userinfo.add(jButton, gridBagConstraints);

    JLabel vital = new JLabel();
    ThreadCustom threadCustom = new ThreadCustom();
    threadCustom.Vital();
    Timer timer = new Timer(1000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (VitalConstants.getVitalValue() > 120) {
          vital.setForeground(Color.RED);
        } else if (VitalConstants.getVitalValue() > 100) {
          vital.setForeground(Color.BLUE);
        } else {
          vital.setForeground(Color.GREEN);
        }
        vital.setText("Vital: " + VitalConstants.getVitalValue());
      }
    });
    timer.start();
    gridBagConstraints.insets = new Insets(0,10,0,0);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    Userinfo.add(vital, gridBagConstraints);
    

    JPanel jPanUserInfoDesp = new JPanel(new GridBagLayout());
    jPanUserInfoDesp.setOpaque(false);
    gridBagConstraints.insets = new Insets(0,0,0,0);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    Userinfo.add(jPanUserInfoDesp, gridBagConstraints);

    UserAge = String.valueOf(um.getUserAge());
    UserSex = um.getUserGender();
    
    JLabel jLabUserInfo = new JLabel(UserAge+" age | "+ UserSex);
    // JLabel jLabUserInfo = new JLabel(UserAge+" age | "+ UserSex + " | " + UserHeight + " cm");
    gridBagConstraints.insets = new Insets(20,10,0,0);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
    jPanUserInfoDesp.add(jLabUserInfo, gridBagConstraints);

    btnInfoChange = new JButton("<html><u>Update >></u></html>");
    btnInfoChange.setBorderPainted(false);
    btnInfoChange.setContentAreaFilled(false);
    btnInfoChange.setFocusPainted(true);
    btnInfoChange.setOpaque(false);
    btnInfoChange.addActionListener(this);
    
    gridBagConstraints.insets = new Insets(0,0,0,0);
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    // gridBagConstraints.weighty = 1.0;   //request any extra vertical space
    gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END; //bottom of space
    jPanUserInfoDesp.add(btnInfoChange, gridBagConstraints);
    
    UserName = um.getUserName();
    JLabel jLabUserName = new JLabel(UserName);
    jLabUserName.setFont(new Font("굴림",Font.BOLD,30));
    gridBagConstraints.insets = new Insets(0,0,0,0);
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
    jPanUserInfoDesp.add(jLabUserName, gridBagConstraints);
  }

  @Override
  public void actionPerformed(ActionEvent ae){
    Object obj = ae.getSource();
    
    if (obj instanceof JButton){
        JButton eventBtn = (JButton)obj;

        if(eventBtn.equals(btnInfoChange)){
          AppUserInfo appUserInfo = new AppUserInfo();
          appUserInfo.setVisible(true);
          appUserInfo.setLocationRelativeTo(jFrame);
          jFrame.dispose();    
      }
    }
  }
}

class MainCard extends JPanel implements GlobalConstants, ActionListener {
  private String Title, StatusValue, StatusCalc, strRecord;
  private int UserWeight, UserWeightGoal, UserDiet;
  private JButton btnRecord;
  String type;
  JFrame parentFrame;

  // Arguments for MainCard constructor: Title, StatusValue, StatusCalc, strRecord
  public MainCard(String Title, String StatusValue, String StatusCalc, String strRecord, UserManager um, String type, JFrame parentFrame) {

    this.parentFrame = parentFrame;
    // Member set
    this.Title = Title;
    this.StatusValue = StatusValue;
    this.StatusCalc = StatusCalc;
    this.strRecord = strRecord;
    this.type = type;
    // UIManager.put("ProgressBar.foreground", Color.blue);

    // Set layout (> GridBagConstraints)
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    setLayout(new GridBagLayout());
    setOpaque(false); // transparent setting for background
    setBounds(20,30,520,150); // Set bounds
    setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, 2)); // Set Border

    JLabel jLabTitle = new JLabel(Title);
    jLabTitle.setFont(new Font("굴림",Font.BOLD,30)); // TitleFont
    gridBagConstraints.insets = new Insets(20,30,0,10); // Padding for GridBag
    gridBagConstraints.gridx = 0; // Grid x position
    gridBagConstraints.gridy = 0; // Grid y position
    gridBagConstraints.anchor = GridBagConstraints.PAGE_START; // Grid anchor position

    // gridBagConstraints.fill = GridBagConstraints.HORIZONTAL; // ReSizable
    // gridBagConstraints.ipady = 0;
    // gridBagConstraints.gridwidth = 1;
    // gridBagConstraints.weighty = 1.0;   //request any extra vertical space
    add(jLabTitle, gridBagConstraints); // add Title with GridBagconstraints

    JLabel jLabStatusValue = new JLabel(StatusValue); // StatusValueFont
    gridBagConstraints.insets = new Insets(20,30,0,0); // Padding for GridBag
    gridBagConstraints.gridx = 0; // Grid x position
    gridBagConstraints.gridy = 1; // Grid y position
    gridBagConstraints.anchor = GridBagConstraints.LINE_START; // Grid anchor position
    add(jLabStatusValue, gridBagConstraints);
    
    JLabel jLabStatusCalc = new JLabel(StatusCalc);
    jLabStatusCalc.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, 1)); // Set Border
    // jLabStatusCalc.setBackground(SECONDARY_COLOR);
    // jLabStatusCalc.setBorder(new BevelBorder(BevelBorder.RAISED)); // 3D border
    gridBagConstraints.insets = new Insets(20,150,0,50);
    gridBagConstraints.gridx = 1; // Grid x position
    gridBagConstraints.gridy = 0; // Grid y position
    gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END; // Grid anchor position
    add(jLabStatusCalc, gridBagConstraints);

    JProgressBar statusBar = new JProgressBar(0, 100);
    gridBagConstraints.insets = new Insets(10,30,0,0);
    statusBar.setStringPainted(true);
    if (type.equals("weight")) {
      UserWeight = um.getUserWeight();
      UserWeightGoal = um.getUserWeiGoal();
      
    } else {
      FoodManager fm1 = new FoodManager(type);
      UserWeight = (int) fm1.getDayAllCalorie();
      fm1.close();
      UserWeightGoal = Integer.parseInt(um.getCalorieGoal());
    }

    UserDiet = Math.abs(UserWeightGoal - UserWeight);
    // statusBar.setMaximum(100);
    statusBar.setValue(100-(UserDiet * 100/ UserWeightGoal)); // Set value of progress bar
    statusBar.setForeground(Color.blue);
    gridBagConstraints.ipadx = 200; // Size setting of statusBar using x-width
    gridBagConstraints.gridx = 0; // Grid x position
    gridBagConstraints.gridy = 2; // Grid y position
    gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START; // Grid anchor position
    add(statusBar, gridBagConstraints);
    
    btnRecord = new JButton(strRecord);
    btnRecord.setBorderPainted(false); // remove border
    btnRecord.setContentAreaFilled(false); // remove fill
    btnRecord.setFocusPainted(true); // remove mouse focus
    btnRecord.setOpaque(false); // transparent

    gridBagConstraints.insets = new Insets(0,50,0,0);
    gridBagConstraints.ipadx = 100; // Size setting of button using x-width
    gridBagConstraints.gridx = 1; // Grid x position
    gridBagConstraints.gridy = 2; // Grid y position
    gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END; // Grid anchor position
    add(btnRecord, gridBagConstraints);
    btnRecord.addActionListener(this);
    
  }

  @Override
  public void actionPerformed(ActionEvent ae){
    Object obj = ae.getSource();
    
    if (obj instanceof JButton){
        JButton eventBtn = (JButton)obj;

        if(eventBtn.equals(btnRecord)){
          if (type.equals("weight")) {
            AppCalendar appCalendar = new AppCalendar();
            appCalendar.setVisible(true);
            appCalendar.setLocationRelativeTo(parentFrame);
            parentFrame.dispose();
          } else {
            UserCalGoal userCalGoal = new UserCalGoal();
            userCalGoal.setVisible(true);
            userCalGoal.setLocationRelativeTo(parentFrame);
            parentFrame.dispose();
          }
        }
    }
  }
}

class MainWeightGoal extends JPanel implements GlobalConstants{
  private int UserWeight, UserWeightGoal;
  private String WeightStr;
  private int UserDiet;
  JFrame parentFrame;
  public MainWeightGoal(int BasicWidth, int BasicHeight, UserManager um, JFrame parentFrame) {
    this.parentFrame = parentFrame;
    setPreferredSize(new Dimension(BasicWidth,BasicHeight));
    setBackground(PRIMARY_COLOR);
    // setBackground(new Color(255, 247, 225)); // HEX color #FFF7E1, Color name: Corn Silk
    // setOpaque(true);
    // setVgap(0);
    setLayout(null);
    setBorder(null);

    UserWeight = um.getUserWeight();
    UserWeightGoal = um.getUserWeiGoal();
    UserDiet = UserWeightGoal - UserWeight;
    if (UserDiet < 0) {
      WeightStr = " kg 다이어트(-)";
    } else if (UserDiet == 0) {
      WeightStr = "현상유지만!";
    } else {
      WeightStr = " kg 찌세요(+)";
    }
    JPanel mainCard = new MainCard("목표 체중",
                                  UserWeight + " kg / " + UserWeightGoal + "kg",
                                  UserDiet + WeightStr,
                                  "<html><u>최신 체중 기록 >></u></html>", um, "weight", parentFrame);
    add(mainCard);
  }
}

class MainDayCalories extends JPanel implements GlobalConstants{
  private Double UserCaloriesGoal;
  private Double cal;
  JFrame parentFrame;

  public MainDayCalories(int width, int height, UserManager um, JFrame parentFrame) {
    this.parentFrame = parentFrame;
    setPreferredSize(new Dimension(width,height));
    setBackground(PRIMARY_COLOR);
    // setBackground(new Color(255, 247, 225)); // HEX color #FFF7E1, Color name: Corn Silk
    // setOpaque(true);
    setLayout(null);

    UserCaloriesGoal = Double.parseDouble(um.getCalorieGoal());

    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
    Calendar time = Calendar.getInstance();
    String today = dateFormat.format(time.getTime());

    //System.out.println("today: " + today);

    FoodManager fm = new FoodManager(today);
    double eatenCalroie = fm.getDayAllCalorie();
    
    NumberFormat nf = new DecimalFormat("##");

    // TODO: cal 제대로 바꾸기
    cal = Math.abs(UserCaloriesGoal - eatenCalroie);
    JPanel mainCard = new MainCard("오늘의 칼로리",
                                    nf.format(eatenCalroie)+  " / "+ nf.format(UserCaloriesGoal) + " kcal", 
                                    nf.format(cal) + " kcal 섭취",
                                    "<html><u>칼로리 목표 변경 >></u></html>", um, today, parentFrame);
    add(mainCard);
    
    
  }
}

class MainDayThread extends JPanel implements ActionListener {
  private JLabel jLabelBanner;

  public MainDayThread(int width, int height) {

    // setPreferredSize(new Dimension(width, height));
    setBackground(new Color(127, 185, 255)); // HEX color #7fb9ff, Color name:
    jLabelBanner = new JLabel("I may not be there yet, but I'm closer than I was yesterday.  ");
    jLabelBanner.setFont(new Font("맑음고딕",Font.PLAIN,18));
    
    Timer t = new Timer(100, this); // set a timer
    t.start();
    add(jLabelBanner, BorderLayout.CENTER);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    String oldText = jLabelBanner.getText();
    String newText= oldText.substring(1)+ oldText.substring(0,1);
    jLabelBanner.setText(newText);
  }
}