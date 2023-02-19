package com.APP.Drink;

import com.APP.Common.GlobalConstants;
import com.APP.Common.JPanelSize;
import com.APP.NavBar.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import static javax.swing.JLabel.*;

public class AppDrink extends JFrame implements GlobalConstants {
    // JPanel[] titleBorder;
    // String[] title = {"[ Units ]","[ mL ]","[ Result ]"};

    // JLabel[] cntDrinkjLabels;
    // JTextField[] cntDrinkjTextFields;

    // UserInputAction userInputAction = new UserInputAction();

    // JButton btnSubmit;

    public AppDrink() {
        initialize();
      }

    private void initialize() {
        setTitle("Drink page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUIFont (new javax.swing.plaf.FontUIResource("굴림",Font.BOLD,12));

        // container set
        Container container = getContentPane();

        // Size and layout
        JPanelSize jPanelSize = new JPanelSize();
        int BasicWidth = jPanelSize.getWidth();
        int BasicHeight = jPanelSize.getHeight();

        setSize(BasicWidth,BasicHeight);
        setLayout(new BorderLayout());

        // JPanel setting
        JPanel TopSysTimePane = new TopSysTimePane(BasicWidth, BasicHeight/8);
        JPanel MainDrinkPane = new MainDrinkPane();
        // JPanel MainResultPane1 = new MainResultPane1(BasicWidth, (BasicHeight/8)*1);
        // JPanel MainResultPane2 = new MainResultPane2(BasicWidth, (BasicHeight/8)*2);
        JPanel bottomMenuPane = new bottomMenuPane(BasicWidth, BasicHeight/8, this);

        JPanel MainjPanel = new MainjPanel();
        MainjPanel.setBackground(new Color(45,99,255));
        TopSysTimePane.setBackground(new Color(45,99,255));
        MainDrinkPane.setBackground(new Color(45,99,255));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        container.add(TopSysTimePane, BorderLayout.NORTH);
        container.add(MainjPanel, BorderLayout.CENTER);
        container.add(bottomMenuPane, BorderLayout.SOUTH);

        gridBagConstraints.ipady = 50;
        gridBagConstraints.gridx = 0; // Grid x position
        gridBagConstraints.gridy = 0; // Grid y position
        MainjPanel.add(MainDrinkPane, gridBagConstraints);
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
    //                 AppDrink appDrink = new AppDrink();
    //                 appDrink.setVisible(true);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     });
    // }
}

// section_1: TOP
//          0) name: SysInfo
//          1) position : TOP
//          2) type: JPanel
//          3) object: SystemDate (Update real-time)
class TopSysTimePane extends JPanel {
    
    Timer SimpleTimer = new Timer(1000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleTimer.start();
        }
    });

    TopSysTimePane(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
        Calendar time = Calendar.getInstance();
        String SysDate = dateFormat.format(time.getTime());
        setLayout(new GridLayout(2,1));

        JLabel timeLabel = new JLabel(SysDate);
        JLabel unitInfo = new JLabel("% BAC(혈중알코올농도, 단위:%)");
        unitInfo.setForeground(new Color(0,0,0));

        add(timeLabel);
        add(unitInfo);

        timeLabel.setFont(new Font("굴림",Font.BOLD,30));
        unitInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

        timeLabel.setHorizontalAlignment(CENTER);
        unitInfo.setHorizontalAlignment(CENTER);

        setBorder(BorderFactory.createEmptyBorder(20 , 0, 20 , 0));

    }
}

class MainDrinkPane extends JPanel implements GlobalConstants {
    private JButton btnSoju, btnBeer, btnWine, btnMakgl;

    MainDrinkPane() {
        // setPreferredSize(new Dimension(width, height));
        setLayout(new GridLayout(1,4));
        btnSoju = new btnDrink("Soju", new ImageIcon(drinkBtnSoju));
        btnSoju.setForeground(new Color(0,0,0));
        btnBeer = new btnDrink("Beer", new ImageIcon(drinkBtnBeer));
        btnBeer.setForeground(new Color(0,0,0));
        btnWine = new btnDrink("Wine", new ImageIcon(drinkBtnWine));
        btnWine.setForeground(new Color(0,0,0));
        btnMakgl = new btnDrink("Makgeolli", new ImageIcon(drinkBtnMakgl));
        btnMakgl.setForeground(new Color(0,0,0));

        for (JButton jButton : Arrays.asList(btnSoju, btnBeer, btnWine, btnMakgl)) {
            jButton.setOpaque(true);
            jButton.setBackground(new Color(45,99,255));
            add(jButton);
        }
    }

    // @Override
    // public void actionPerformed(ActionEvent ae) {
    //     Object obj = ae.getSource();
    //     int i = 0;
    
    //     if (obj instanceof JButton){
    //         JButton eventBtn = (JButton)obj;
    
    //         if(eventBtn.equals(btnSoju)){
    //             i = i+1;
    //             // UserInputAction.setCntUnitDrinkItem(1, i);
    //         }
    //     }
        
    // }
}
class btnDrink extends JButton {
    btnDrink(String name, ImageIcon icon) {
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        setHorizontalTextPosition(btnDrink.CENTER);
        setVerticalTextPosition(btnDrink.BOTTOM);
        setIcon(new ImageIcon(changeImg));
        setText(name);
        if (name == "Makgeolli") {
            setFont(new Font("굴림",Font.BOLD,21));    
        } else {
            setFont(new Font("굴림",Font.BOLD,30));
        }

        setBorderPainted(false);
        setContentAreaFilled(false);

        // setOpaque(true);

    }
}
class MainjPanel extends JPanel implements ActionListener, GlobalConstants {
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    JPanel[] titleBorder;
    String[] title = {"[ Alcohol Percentage (%) ]","[ mL ]","[ Result ]"};
    JTextField[] cntDrinkunitjTextFields = new JTextField[AlcoholConcentration.getAlcoholpercentage().size()];
    JTextField[] cntDrinkjTextFields = new JTextField[UserInputAction.getCntMlDrink().size()];
    private JButton btnSubmit = new JButton();
    private JButton btnReset = new JButton();

    MainjPanel() {
        setLayout(new GridBagLayout());
        titleBorder = new JPanel[title.length];
        for (int i=0; i<titleBorder.length; i++) {
            titleBorder[i] = new JPanel(new GridBagLayout());
            titleBorder[i].setOpaque(true); // transparent setting for background
            titleBorder[i].setBackground(new Color(45,99,255));
            titleBorder[i].setBorder(BorderFactory.createTitledBorder(title[i]));            
            gridBagConstraints.ipadx = 400; // Size setting of JTextField using x-width
            gridBagConstraints.ipady = 30;
            // gridBagConstraints.insets = new Insets(10,0,10,0);
            gridBagConstraints.gridx = 0; // Grid x position
            gridBagConstraints.gridy = i+1; // Grid y position
            
            add(titleBorder[i], gridBagConstraints);
        }

        for (int i = 0; i<cntDrinkunitjTextFields.length; i++) {
                cntDrinkunitjTextFields[i] = new JTextField(){
                @Override
                public void setBorder(Border border) {
                    
                    }
                };
            
            cntDrinkunitjTextFields[i].setText(Double.toString(AlcoholConcentration.getAlcoholpercentage().get(i)));
            cntDrinkunitjTextFields[i].setFont(new Font("굴림", Font.BOLD, 20));
            cntDrinkunitjTextFields[i].setForeground(Color.blue);
            cntDrinkunitjTextFields[i].setHorizontalAlignment(JLabel.CENTER);
            cntDrinkunitjTextFields[i].setBackground(new Color(213,223,255));

            gridBagConstraints.ipadx = 20; // Size setting of JTextField using x-width
            gridBagConstraints.ipady = 0;
            gridBagConstraints.gridx = i;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(0,0,0,0);
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 1;
            titleBorder[0].add(cntDrinkunitjTextFields[i], gridBagConstraints);
        }
        
        for (int i = 0; i<cntDrinkjTextFields.length; i++) {

            cntDrinkjTextFields[i] = new JTextField(){
                @Override
                public void setBorder(Border border) {
                    
                    }
                };
            cntDrinkjTextFields[i].setText(Double.toString(UserInputAction.getCntMlDrink().get(i)));
            cntDrinkjTextFields[i].setFont(new Font("굴림", Font.BOLD, 20));
            cntDrinkjTextFields[i].setHorizontalAlignment(JLabel.CENTER);
            cntDrinkjTextFields[i].setBackground(new Color(213,223,255));
            cntDrinkjTextFields[i].setForeground(new Color(0,0,0));
            // cntDrinkjLabels[i].setForeground(Color.RED);
            // cntDrinkjLabels[i].setOpaque(false);

            gridBagConstraints.ipadx = 20; // Size setting of JTextField using x-width
            gridBagConstraints.ipady = 0;
            gridBagConstraints.gridx = i;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.insets = new Insets(0,0,0,0);
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 1;
            titleBorder[1].add(cntDrinkjTextFields[i], gridBagConstraints);
        }
        btnSubmit.setText("Submit");
        btnSubmit.setPreferredSize(new Dimension(40, 40));
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 30));
        btnSubmit.setForeground(Color.BLUE);
        btnSubmit.setBackground(new Color(170,191,255));
        btnSubmit.setOpaque(true);
        btnSubmit.addActionListener(this);

        

        btnReset.setText("Reset");
        btnReset.setPreferredSize(new Dimension(40, 40));
        btnReset.setFont(new Font("Arial", Font.BOLD, 30));
        btnReset.setForeground(Color.BLUE);
        btnReset.setOpaque(false);
        btnReset.addActionListener(this);

        gridBagConstraints.ipadx = 100;  // Size setting of button using x-width
        gridBagConstraints.gridx = 0; // Grid x position
        gridBagConstraints.gridy = 4; // Grid y position
        // gridBagConstraints.insets = new Insets(20,0,10,0);
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(btnSubmit, gridBagConstraints);

        JPanel MainDayThread= new MainDayThread(560, 100);
        gridBagConstraints.gridx = 0; // Grid x position
        gridBagConstraints.gridy = 5; // Grid y position
        // gridBagConstraints.insets = new Insets(50,0,0,0);
        // gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(MainDayThread, gridBagConstraints);  
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        Object obj = ae.getSource();
    
        if (obj instanceof JButton){
            JButton eventBtn = (JButton)obj;

            if(eventBtn.equals(btnSubmit)){
                titleBorder[2].removeAll();
                
                AlcoholConcentration.setBodyAlcohol(); // 계산해라!!

                Double resultBAC = AlcoholConcentration.getBodyAlcohol();
                int soberTimeSec = (int) AlcoholConcentration.getPerfectBreakTime()*60*60;
                int HH = soberTimeSec/3600;
                int MM = (soberTimeSec % 3600) / 60;
                int SS = soberTimeSec % 60;
                String timeStr = HH +"h "+ MM +"m "+ SS +"s ";
                JLabel BAC = new JLabel("* [Blood Alcohol Level]: " + String.format("%.2f", resultBAC) + " % BAC");
                JLabel Uptime = new JLabel("* [Time to Decipher]: " + timeStr);
                JLabel Life = new JLabel("* [Reduced Lifespan]: " + UserInputAction.getValueLife());
                // BAC.setText("* [Blood Alcohol Level]: " + String.format("%.2f", resultBAC) + " % BAC");
                // BAC.setFont(new Font("굴림", Font.BOLD, 15));
                // BAC.setForeground(Color.BLUE);
                // BAC.setOpaque(false);
                // UserInputAction.setValueBAC(resultBAC);
                for (int i=0; i<cntDrinkjTextFields.length; i++) {
                    UserInputAction.getCntMlDrink().set(i, Double.parseDouble(cntDrinkjTextFields[i].getText()));
                }
                
                for (int i=0; i<cntDrinkunitjTextFields.length; i++) {
                    AlcoholConcentration.getAlcoholpercentage().set(i, Double.parseDouble(cntDrinkunitjTextFields[i].getText()));
                    System.out.println(AlcoholConcentration.getAlcoholpercentage());
                }
                // Math.round(EAST * 100) / 100;

                int j = 0;
                for (JLabel jLabel : Arrays.asList(BAC, Uptime, Life)) {
                    gridBagConstraints.ipadx = 50;
                    gridBagConstraints.gridx = 0; // Grid x position
                    gridBagConstraints.gridy = j+1; // Grid y position
                    // gridBagConstraints.insets = new Insets(0,0,0,0);
                    // gridBagConstraints.anchor = GridBagConstraints.CENTER;
                    titleBorder[2].add(jLabel, gridBagConstraints);
                    j++;
                }
                System.out.println("유저가 먹은 알코올 양" + UserInputAction.getCntMlDrink());
            }
        }
    }
}

class MainDayThread extends JPanel implements ActionListener {
    private JLabel jLabelBanner;
  
    public MainDayThread(int width, int height) {
  
      setPreferredSize(new Dimension(width, height));
      setBackground(new Color(127,185,255)); // HEX color #7fb9ff, Color name:
        // setOpaque(false);
      
      jLabelBanner = new JLabel("Friendship is like wine; The older the better.  ");
      jLabelBanner.setFont(new Font("굴림",Font.BOLD,20));
      
      Timer t = new Timer(2000, this); // set a timer
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
