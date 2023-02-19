package com.APP.DayCalories;

import com.APP.Calendar.AppCalendar;
import com.APP.Common.GlobalConstants;
import com.APP.Common.UserManager;
import com.APP.NavBar.bottomMenuPane;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
public class AppDayCalories extends JFrame implements GlobalConstants {

    String date;
    String userid;

    /**
     * 음식 추가 버튼을 눌렀을때 행동을 처리합니다.
     */
    class addFoodListener implements ActionListener {
        String recordType;
        JFrame parentJFrame;

        /**
         * 후에 DB에 넣을 때 recordType을 결정합니다.
         * @param recordType "아침", "점심", "저녁"
         * @param parentJFrame 창 닫기를 위해 부모가 되는 Frame을 넘겨줍니다.
         */
        addFoodListener(String recordType, JFrame parentJFrame) {
            this.recordType = recordType;
            this.parentJFrame = parentJFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                AppFoodSearchAdd foodSearchAdd = new AppFoodSearchAdd(date, recordType);
                foodSearchAdd.setLocationRelativeTo(parentJFrame);
                parentJFrame.dispose();
                foodSearchAdd.setVisible(true);
            } catch (SQLException | IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 날짜 버튼을 눌렀을때 행동을 처리합니다.
     */
    class DateChangeListener implements MouseInputListener {

        AppDayCalories appDayCalories;
        
        DateChangeListener(AppDayCalories appDayCalories) {
            this.appDayCalories = appDayCalories;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            AppCalendar ac = new AppCalendar();
            ac.setLocationRelativeTo(appDayCalories);
            ac.setVisible(true);
            appDayCalories.dispose();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseClicked(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            return;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            return;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            return;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            return;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            return;
        }

    }


    public AppDayCalories() {
        initialize();
    }

    public AppDayCalories(String date) {
        this.date = date;
        initialize();
    }

    /**
     * 클래스가 생성되었을때 화면 처리를 하는 메소드입니다.
     */
    private void initialize() {

        setTitle("기록 페이지");
        //기본 폰트 지정
        setUIFont(new javax.swing.plaf.FontUIResource("맑은 고딕", Font.BOLD, 18));

        // 메인페이지
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // container set
        Container container = getContentPane();
        

        // Size and layout
        setSize(560,840);
        setLayout(new BorderLayout());

        // Panel들 생성
        JPanel topPanel = new JPanel();
        topPanel.setBackground(GlobalConstants.SECONDARY_COLOR);
        JPanel mainJPanel = new JPanel();
        mainJPanel.setBackground(new Color(45,99,255));
        JPanel mainInfoJPanel = new JPanel();
        mainInfoJPanel.setBackground(new Color(45,99,255));

        // 레이아웃 설정
        BoxLayout boxLayout = new BoxLayout(mainInfoJPanel, BoxLayout.Y_AXIS);
        mainInfoJPanel.setLayout(boxLayout);

        // GridBagConstraints constraints = new GridBagConstraints();

        // 하단 메뉴 생성
        JPanel bottomMenuPane = new bottomMenuPane(560, 105, this);

        // 날짜 없을시 날짜 지정
        if (date == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
            Calendar time = Calendar.getInstance();
            date = dateFormat.format(time.getTime());
        }

        userid = "20230117";
        
        
        
        // 상단 패널에 날짜 작성
        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        DateChangeListener dcl = new DateChangeListener(this);

        dateLabel.addMouseListener(dcl);
        ImageIcon calimg = new ImageIcon("src/img/Calendar.png");
        JLabel iconLabel = new JLabel(calimg);
        iconLabel.addMouseListener(dcl);
        topPanel.add(dateLabel);
        topPanel.add(iconLabel);
        


        // 날짜에 맞는 정보를 DB에서 가져오는 FoodManager 생성
        FoodManager foodManager = new FoodManager(date);
        // 테스트용 프린트
        // foodManager.print();

        // FoodManager 안의 리스트 가져오기
        ArrayList<Food> foodList = foodManager.getList();

        double todayCalorie = foodManager.getDayAllCalorie();

        // 아침, 점심, 저녁 RecordPane 생성
        RecordPane breakfastRecordPane = new RecordPane(foodList, todayCalorie, "아침", this);
        RecordPane lunchRecordPane = new RecordPane(foodList, todayCalorie, "점심", this);
        RecordPane dinnerRecordPane = new RecordPane(foodList, todayCalorie, "저녁", this);

        // Border 생성
      
        // 간격 지정
        final int BLANK_HEIGHT = 30;
        Dimension blankDim = new Dimension(0, BLANK_HEIGHT);

        // 아침, 점심, 저녁 패널 더하기
        mainInfoJPanel.add(breakfastRecordPane);
        // 간격 (빈 공간 더 하기)
        mainInfoJPanel.add(Box.createRigidArea(blankDim));

        mainInfoJPanel.add(lunchRecordPane);
        mainInfoJPanel.add(Box.createRigidArea(blankDim));

        mainInfoJPanel.add(dinnerRecordPane);
        mainInfoJPanel.add(Box.createRigidArea(blankDim));
        
        // 요약 패널 더하기
        SummaryPane summaryPane = new SummaryPane(foodList, userid);
        mainInfoJPanel.add(summaryPane);

        // 필요한 패널들을 메인 패널에 더하기
        mainJPanel.add(mainInfoJPanel, BorderLayout.NORTH);

        // 메인 패널을 스크롤페인에 더하기
        JScrollPane jScrollPane = new JScrollPane(mainJPanel);

        // 스크롤페인의 설정
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 콘테이너에 생성된 패널 더하기
        container.add(topPanel, BorderLayout.NORTH);
        container.add(jScrollPane, BorderLayout.CENTER);
        container.add(bottomMenuPane, BorderLayout.SOUTH);       
    }

    /**
     * 일정에 맞는 음식을 표시하는 클래스입니다.
     */
    class RecordPane extends JPanel {
        /**
         * 음식 리스트를 받아 recordType에 맞는 음식만 표시합니다.
         * 
         * @param foodList 오늘 먹은 음식의 리스트
         * @param recordType "아침", "점심", "저녁" 등으로 지정해줍니다.
         */
        public RecordPane(ArrayList<Food> foodList, double todayCalorie, String recordType, JFrame parentJFrame) {
            JPanel infoPanel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);
            infoPanel.setLayout(boxLayout);

            infoPanel.add(new JLabel(recordType));

            // 음식 추가 버튼
            JButton sizeaddButton = new JButton();
            JButton addButton = new JButton("먹은 음식 추가");
            sizeaddButton.setBackground(new Color(127,185,255));
            sizeaddButton.setBorder(BorderFactory.createRaisedBevelBorder());
            addButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
            addButton.setOpaque(false);
            addButton.addActionListener(new addFoodListener(recordType, parentJFrame));
            
            
            BoxLayout boxLayout2 = new BoxLayout(this, BoxLayout.Y_AXIS);
            this.setLayout(boxLayout2);
            this.add(sizeaddButton);
            sizeaddButton.add(addButton);
            

            // 패널마다 음식 정보를 담는 방법 추가

            for (Food k: foodList) {
                if (k.getRecord_type().equals(recordType)) {

                    double percent = k.getallCalorie() / todayCalorie * 100;
                    NumberFormat nf = new DecimalFormat("##.#");

                    // 패널에 표시되는 값
                    String labelString = "<html>" + k.getFood_name() + " : " + nf.format(k.getRecord_food_amount()) +
                    k.getFood_metricname() + " / " + String.format("%.0f", k.getallCalorie()) + "kcal (" + String.format("%.0f", percent) + "%)"
                    +"<br><small>탄수화물:" + String.format("%.0f", k.getFood_carb()) + "g 단백질:" + String.format("%.0f", k.getFood_protein())
                    + "g 지방:" + String.format("%.0f", k.getFood_fat()) + "g" + "</small></html>";

                    // 패널 만들기
                    infoPanel.add(new FoodLabelPanel(labelString, true, k, recordType, parentJFrame));
                }
            }

            this.setBackground(new Color(202,226,255));
            infoPanel.setBackground(new Color(202,226,255));

            this.setMinimumSize(new Dimension (800, 300));
            this.add(infoPanel);
            this.setVisible(true);
        }
    }

    /**
     * 칼로리의 요약을 보여주는 클래스입니다.
     */
    class SummaryPane extends JPanel {
        /**
         * 요약 보여주는 클래스를 생성하기 위해 음식 클래스가 있는 리스트를 받습니다.
         * @param foodList
         */
        public SummaryPane(ArrayList<Food> foodList, String userid) {
            JPanel infoPanel = new JPanel();
            BoxLayout boxLayout = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);
            infoPanel.setLayout(boxLayout);

            double eatenCalorie = 0.0;
            for (Food k: foodList) {
                eatenCalorie += k.getallCalorie();
            }
            
            infoPanel.add(new FoodLabelPanel("오늘 먹은 칼로리 : " + String.format("%.0f", eatenCalorie) + "kcal"));

            UserManager um = new UserManager(userid);
            //System.out.println("칼로리 목표: " + um.getCalorieGoal());
            int calorieGoal = Integer.parseInt(um.getCalorieGoal());
            um.close();
            infoPanel.add(new FoodLabelPanel("목표 칼로리: " + calorieGoal + "kcal"));

            double nowCalorie = calorieGoal * 1.0 - eatenCalorie;
            
            JPanel nowCaloriePanel = new JPanel();
            JLabel nowCalorieLabel = new JLabel("<html><b>남은 칼로리</b> : " + String.format("%.0f", nowCalorie) + "kcal</html>");

            nowCaloriePanel.add(nowCalorieLabel);
            
            // 남은칼로리가 0보다 적을시 빨간 글씨로 출력
            nowCalorieLabel.setForeground(nowCalorie < 0? Color.RED: Color.BLUE);
            nowCaloriePanel.setBackground(new Color(127,185,255));

            infoPanel.add(nowCaloriePanel);

            double eatenCarb = 0.0;
            for (Food k: foodList) {
                eatenCarb += k.getFood_carb();
            }

            double eatenProtein = 0.0;
            for (Food k: foodList) {
                eatenProtein += k.getFood_protein();
            }

            double eatenFat = 0.0;
            for (Food k: foodList) {
                eatenFat += k.getFood_fat();
            }

            double eatenSodium = 0.0;
            for (Food k: foodList) {
                eatenSodium += k.getFood_sodium();
            }

            double eatenSugar = 0.0;
            for (Food k: foodList) {
                eatenSugar += k.getFood_sugar();
            }

            double totalCPF = eatenCarb + eatenProtein + eatenFat;
            
            double carbPercent = eatenCarb / totalCPF * 100;
            double proteinPercent = eatenProtein / totalCPF * 100;
            double fatPercent = eatenFat / totalCPF * 100;

            NumberFormat nf = new DecimalFormat("##.#");

            infoPanel.add(new FoodLabelPanel("<html><탄수화물: " + nf.format(eatenCarb) + "g (" + nf.format(carbPercent) + "%)<br>"
            + " 단백질: " + nf.format(eatenProtein) + "g (" + nf.format(proteinPercent) + "%)<br>" 
            + " 지방: " + String.format("%.1f", eatenFat) + "g (" + nf.format(fatPercent) + "%)</html>"));
            infoPanel.add(new FoodLabelPanel("나트륨: " + String.format("%.0f", eatenSodium) + "mg" + " 총 당류: " + String.format("%.1f", eatenSugar) + "g"));
            

            this.setBackground(new Color(127,185,255));
            this.add(infoPanel);
            this.setVisible(true);
        }
    }

    class FoodLabelPanel extends JPanel {
        
        /**
         * RecordPane 안에서 하나의 음식 정보를 표시할때 사용합니다.
         * @param foodinfo 음식정보
         * @param isButton true = 삭제 버튼 생성.
         */
        public FoodLabelPanel(String foodinfo, Boolean isButton, Food food, String recordType, JFrame parentJFrame) {
            JLabel jLabel = new JLabel(foodinfo);
            this.setBackground(new Color(127,185,255));
            this.add(jLabel);   
            this.setVisible(true);
        
            if (isButton) {
                JButton btn = new JButton("삭제");
                btn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
                btn.setBackground(new Color(156,193,226));
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int result = JOptionPane.showConfirmDialog((Component)null, "삭제합니까?", "기록 삭제", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                        System.out.println("result: " + result);
                        
                        // 예를 누를시 삭제 
                        if (result == 0) {
                            FoodManager fm = new FoodManager(date);
                            fm.deleteFood(food, recordType);

                            // 창 새로 만든 후 지금 창 삭제
                            AppDayCalories appDayCalories = new AppDayCalories();
                            appDayCalories.setLocationRelativeTo(parentJFrame);
                            appDayCalories.setVisible(true);
                            parentJFrame.dispose();
                        }
                    }
                }
                );

                this.add(btn);
            }
        }
        
        /**
         * RecordPane 안에서 하나의 음식 정보를 표시할때 사용합니다.
         * @param foodname
         */
        public FoodLabelPanel(String foodname) {
            this(foodname, false, (Food) null, (String) null, (JFrame) null);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppDayCalories appDayCalories = new AppDayCalories();
                    appDayCalories.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 화면 내 UI의 기본 폰트를 바꾸는 메소드입니다.
     * @param f 폰트
     */
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration<Object> keys = UIManager.getLookAndFeelDefaults().keys();
        while (keys.hasMoreElements()) {
          Object key = keys.nextElement();
          Object value = UIManager.get (key);
          if (value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put (key, f);
          }
        } 
}
