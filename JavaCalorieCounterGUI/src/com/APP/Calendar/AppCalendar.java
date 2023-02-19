package com.APP.Calendar;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.APP.NavBar.bottomMenuPane;
import com.APP.DayCalories.AppDayCalories;
import com.APP.Common.*;

 
public class AppCalendar extends JFrame implements ItemListener, ActionListener, GlobalConstants{
    
    Font fnt = new Font("굴림체", Font.BOLD, 20);

    // 전체 패널
    JPanel Pane = new JPanel(new GridLayout(2,1));
    

    // 세부사항 패널
    JPanel upPane = new JPanel();
        JPanel dagsetningPane = new JPanel(new FlowLayout());
        JLabel dagsetning = new JLabel(findday()); // 날짜 
        ImageIcon calimg = new ImageIcon("src/img/Calendar.png");
        
        JButton calbutton = new JButton(calimg);
        
        
        JPanel weightPane = new JPanel(new FlowLayout());
        JTextField weight = new JTextField(8); // 체중
        JButton record = new JButton("체중 기록"); // 체중 기록 버튼
        
        JPanel infomationPane = new JPanel(new BorderLayout());
        JLabel infomation = new JLabel(); // 세부정보
        
        

        
        

    // 달력 패널
    JPanel calsizePane = new JPanel();
    JPanel calPane = new JPanel(new BorderLayout());

    // 달력 => 검색 패널
    JPanel selectPane = new JPanel();  
        JButton preBtn = new JButton("◀");   
        JButton nexBtn = new JButton("▶");
        JComboBox<Integer> yearCombo = new JComboBox<Integer>(); 
        JComboBox<Integer> monthCombo = new JComboBox<Integer>();    
        JLabel yearLBI = new JLabel("년");   
        JLabel monthLBI = new JLabel("월");   


    // 달력 => 중앙 패널
    JPanel centerPane = new JPanel(new BorderLayout()); 
        JPanel titlePane = new JPanel(new GridLayout(1, 7));  
            String title[] = {"일", "월", "화", "수", "목", "금", "토"};
        JPanel dayPane = new JPanel(new GridLayout(0, 7, 1 ,1));  

     
    Calendar date; 
    int year;   
    int month;   
    int day;

    Calendar subdate;
    int subyear;
    int submonth;
    int subday;


	// ----------------------팝업 달력 세팅----------------------//
	Font subfnt = new Font("굴림체", 1, 15);
	
    JPanel subsizePane = new JPanel(new BorderLayout());
	JPanel suballpanel = new JPanel(new BorderLayout());


	JPanel subselectPane = new JPanel(); 
		JButton subpreBtn = new JButton("◀"); 
		JButton subnexBtn = new JButton("▶"); 
		JComboBox<Integer> subyearCombo = new JComboBox<Integer>();
		JComboBox<Integer> submonthCombo = new JComboBox<Integer>();
		JLabel subyearLBI = new JLabel("년"); 
		JLabel submonthLBI = new JLabel("월");

	JPanel subcenterPane = new JPanel(new BorderLayout()); 
		JPanel subtitlePane = new JPanel(new GridLayout(1, 7)); 
			String subtitle[] = {"일", "월", "화", "수", "목", "금", "토"};
		JPanel subdayPane = new JPanel(new GridLayout(0, 7, 1 ,1)); 

        JLabel subdayset = new JLabel();


    
	//----------------------------------------------------------//

    class ButtonListener implements ActionListener {
        JFrame parentFrame;

        ButtonListener (JFrame parentFrame) {
            this.parentFrame = parentFrame;
        }
        public void actionPerformed(ActionEvent e) {
            int subcalendar = JOptionPane.showConfirmDialog(Pane, subsizePane, "날짜 선택", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(subcalendar == 0){
                AppDayCalories appDayCalories = new AppDayCalories(Recorddatewithcalendar.getsubRecorddate());
                appDayCalories.setLocationRelativeTo(parentFrame);
                appDayCalories.setVisible(true);
                parentFrame.dispose();
            }
            else{
                return;
            }
        }
    }

    class SubmitListener implements ActionListener {
        JFrame parentFrame;

        SubmitListener (JFrame parentFrame) {
            this.parentFrame = parentFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String getweight = weight.getText(); 
            String[] result = getweight.split("kg");
            double findweight;

            //System.out.println("result[0] = " + result[0]);
            
            if(result[0] != null && result[0] != "") {
            
                try {
                    findweight = Double.parseDouble(result[0]);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(parentFrame, "숫자를 넣어주세요. ex) 40.2, 40.2kg", "에러", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            } else {
                findweight = 0;
            }

            // UserInputTxt.getUserInputTxt().set(4, getweight);
            UserDateInputWithCalendar.setUserDateInput(findweight);

            // DB연동을 위해 체중 기록 클릭 발생 시 체중을 저장
            Inputdayweight indayweight = new Inputdayweight();

            // 몸무게를 입력하는 DB연동코드를 위해 클릭날짜를 가져옴
            String outputday = indayweight.getdbday();

            // 저장해둔 날짜와 체중을 가지고 DB에 값 입력 
            Inputweightdb dayweightDB = new Inputweightdb();

            // 아무날짜도 클릭하지않았을 경우에는 현재 날짜에 입력되도록 지정
            if(outputday == null){
                outputday = findday();
            }
            if(findweight != 0){
                dayweightDB.Dayweight(outputday, findweight, parentFrame);
            }
            else{
                JOptionPane.showMessageDialog(Pane, "값을입력하지않음");
            }
            //System.out.println(outputday+" "+findweight);
            
            weight.setText(findweight + "kg");
        }

    }

    public AppCalendar() {
        super("Calender");
        date = Calendar.getInstance(); 
        year = date.get(Calendar.YEAR);  
        month = date.get(Calendar.MONTH)+1;  
        day = date.get(Calendar.DATE);

        subdate = Calendar.getInstance(); 
        subyear = subdate.get(Calendar.YEAR);  
        submonth = subdate.get(Calendar.MONTH)+1;  
        subday = subdate.get(Calendar.DATE);

        // 패널 부착
        add(Pane);
        
        // 패널 => 세부정보, 달력
        Pane.add(upPane);
        Pane.add(calsizePane); 
		

        // 상단 패널 
        dagsetningPane.add(dagsetning);
		dagsetningPane.setBorder(BorderFactory.createEmptyBorder(40 , 0, 0 , 0));

		// 날짜설정 패널
		dagsetning.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
        dagsetning.setFont(new Font("맑은 고딕",Font.PLAIN,30)); // 폰트설정
        TitledBorder dagsetningtitleborder = new TitledBorder("Date(Today)");
        dagsetningtitleborder.setTitleFont(new Font("돋움", 1, 12));
        dagsetning.setBorder(BorderFactory.createTitledBorder(dagsetningtitleborder));
        dagsetning.setPreferredSize(new Dimension(320,60));
        dagsetningPane.add(calbutton);
		calbutton.setOpaque(false);
		calbutton.setBorderPainted(false);
        calbutton.setPreferredSize(new Dimension(35, 35));

		// 체중, 기록 패널
        upPane.add(dagsetningPane);
        upPane.add(weightPane);

        weightPane.add(weight);
        weightPane.add(record);
        weight.setFont(new Font("맑은 고딕",Font.PLAIN,30)); // 폰트설정
        TitledBorder weighttitleborder = new TitledBorder("weight");
        weighttitleborder.setTitleFont(new Font("돋움", 1, 12));
        weight.setBorder(BorderFactory.createTitledBorder(weighttitleborder));
        weight.setBackground(new Color(246,246,253));
        weightPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        record.setPreferredSize(new Dimension(80,40));
        record.setForeground(new Color(255,255,255));
        record.setBackground(new Color(45,99,255));
        record.setFont(new Font("돋움", Font.BOLD, 10));

        FindWeightDB weightdb = new FindWeightDB(findday());

        if (weightdb.getdayweight() == 0.0) {
            weight.setText("기록없음");
        } else {
            weight.setText(weightdb.getdayweight()+"kg");
        }
        
        

        // 세부정보 패널
        infomationPane.add(infomation, BorderLayout.CENTER);
		infomationPane.setBorder(BorderFactory.createEmptyBorder(20 , 0, 0 , 0));
        upPane.add(infomationPane);
        infomation.setOpaque(true);
		infomation.setBorder(BorderFactory.createEmptyBorder(30,30,15,30));
        infomation.setFont(new Font("돋움", 1 ,15));

        FindFoodinfoDB fooddb = new FindFoodinfoDB(findday());

        NumberFormat nf = new DecimalFormat("##.#");

        infomation.setText("<html>칼로리: "+ nf.format(fooddb.getDayAllCalorie()) + "kcal<br><br>"+
        "탄수화물: " + nf.format(fooddb.getDayAllCarb()) + "g "+
        "단백질: "+ nf.format(fooddb.getDayAllProtein()) +"g " + "지방: " + nf.format(fooddb.getDayAllFat()) + "g" + "</html>");
        
        
        

        // 달력 패널
        calsizePane.add(calPane);
        calsizePane.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0 , 0));     


        calPane.add(selectPane, BorderLayout.NORTH);
        calPane.add(centerPane, BorderLayout.CENTER);
        calPane.setBorder(new TitledBorder(new LineBorder(new Color(0,40,162), 2)));

        
        centerPane.add(titlePane, BorderLayout.NORTH);  
        centerPane.add(dayPane, BorderLayout.CENTER);  


        // 달력 => 검색 패널
        selectPane.setBackground(new Color(45,99,255));  // 배경색 설정
        selectPane.add(preBtn); preBtn.setFont(fnt);
        selectPane.add(yearCombo); yearCombo.setFont(fnt);
        selectPane.add(yearLBI); yearLBI.setFont(fnt);

        selectPane.add(monthCombo); monthCombo.setFont(fnt);
        selectPane.add(monthLBI); monthLBI.setFont(fnt);
        selectPane.add(nexBtn); nexBtn.setFont(fnt);

        // 배경색 설정
        upPane.setBackground(new Color(127,185,255));
        calsizePane.setBackground(new Color(127,185,255));
		dagsetning.setBackground(new Color(246,246,253));
		dagsetningPane.setBackground(new Color(127,185,255));
		weightPane.setBackground(new Color(127,185,255));
		upPane.setBackground(new Color(127,185,255));
		infomationPane.setBackground(new Color(127,185,255));
		infomation.setBackground(new Color(246,246,253));
        

        yearCombo.setBackground(new Color(255,255,255));
        monthCombo.setBackground(new Color(255,255,255));
        preBtn.setBackground(new Color(255,255,255));
        nexBtn.setBackground(new Color(255,255,255));

        yearLBI.setForeground(new Color(255,255,255));
        monthLBI.setForeground(new Color(255,255,255));
         

   
        setYear();
        setMonth();
        setDay(); 
        setCalendarTitle();  

        JPanel bottomMenuPane = new bottomMenuPane(560, 105, this);
        add(BorderLayout.SOUTH, bottomMenuPane);

		preBtn.addActionListener(this);
        nexBtn.addActionListener(this);
        yearCombo.addItemListener(this);
        monthCombo.addItemListener(this);

        subsetYear();
		subsetMonth();
        subsetDay(); 
		subsetCalendarTitle();  
		subcenterPane.add(BorderLayout.NORTH, subtitlePane);  
    
		subpreBtn.addActionListener(this);
		subnexBtn.addActionListener(this);
		subyearCombo.addItemListener(this);
		submonthCombo.addItemListener(this);
        // ------------------ Jframe 설정 --------------- //

        setTitle("달력");
        setVisible(true);
        JPanelSize jPanelSize = new JPanelSize();
        setSize(jPanelSize.getWidth(),jPanelSize.getHeight());
        // setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

       
        

		//--------------------- 팝업 달력 부착, 함수호출 ---------------------//


        subsizePane.add(suballpanel, BorderLayout.CENTER);
        subsizePane.add(subdayset, BorderLayout.NORTH);
        subdayset.setText(subfindday());
        subdayset.setFont(new Font("돋움", 1, 20));
        subdayset.setHorizontalAlignment(SwingConstants.CENTER);

        
		suballpanel.add(subselectPane, BorderLayout.NORTH);
		suballpanel.add(subcenterPane, BorderLayout.CENTER);

        subcenterPane.add(subtitlePane, BorderLayout.NORTH);
        subcenterPane.add(subdayPane, BorderLayout.CENTER);


		subselectPane.setBackground(new Color(150, 200, 200));  
		subselectPane.add(subpreBtn); subpreBtn.setFont(subfnt);
		subselectPane.add(subyearCombo); subyearCombo.setFont(subfnt);
		subselectPane.add(subyearLBI); subyearLBI.setFont(subfnt);

		subselectPane.add(submonthCombo); submonthCombo.setFont(subfnt);
		subselectPane.add(submonthLBI); submonthLBI.setFont(subfnt);
		subselectPane.add(subnexBtn); subnexBtn.setFont(subfnt);

        subselectPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        // 배경색 설정
        subsizePane.setBackground(new Color(238,238,238));
        subcenterPane.setBackground(new Color(238,238,238));
        subselectPane.setBackground(new Color(127,185,255));
        subtitlePane.setBackground(new Color(255,255,255));
        subpreBtn.setBackground(new Color(255,255,255));
        subnexBtn.setBackground(new Color(255,255,255));
        subyearCombo.setBackground(new Color(255,255,255));
        submonthCombo.setBackground(new Color(255,255,255));
        subdayPane.setBackground(new Color(255,255,255));
        


			
		
		//---------------------------------------------------------------//

        // 체중기록 버튼을 눌렀을 때
        record.addActionListener(new SubmitListener(this));
        calbutton.addActionListener(new ButtonListener(this));
    }
    
      
	// 날짜 설정
    public void setDay() { 
        
        
        date.set(year, month-1, 1);  
        int week = date.get(Calendar.DAY_OF_WEEK); 
         
        int lastDay = date.getActualMaximum(Calendar.DATE);  

        for(int s=1; s<week; s++) {
            JLabel lbl = new JLabel(" ");  
            dayPane.add(lbl);
        }
            for(int day=1; day<=lastDay; day++) {
            JButton lbl = new JButton();  
            
            lbl.setLayout(new GridLayout(1,1));
            lbl.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            lbl.setBackground(new Color(250, 250, 250));

            JButton daybutton = new JButton();
            daybutton.setText(String.valueOf(day));
            daybutton.setPreferredSize(new Dimension(50,40));
            daybutton.setBackground(new Color(250, 250, 250));
            daybutton.setFont(new Font("돋움",1,12));

            lbl.add(daybutton);
           
            daybutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae){
                    String strmonth="";
                    String strday="";
                    
                    if(month<10){
                        strmonth = "0"+month;
                    }
                    else{
                        strmonth = month+"";
                    }
                    if(Integer.parseInt(daybutton.getText())<10){
                        strday = "0"+daybutton.getText();
                    }
                    else{
                        strday = daybutton.getText();
                    }
                    String clickday = " ";
                    clickday = year+"-"+strmonth+"-"+strday;

                    // 상단 날짜 라벨에 클릭한 날짜 삽입
                    dagsetning.setText(clickday);

                    // 날짜를 선택하면 DB에서 해당 날짜의 영양소를 가지고옴
                    
                    FindFoodinfoDB fd = new FindFoodinfoDB(clickday);
                    NumberFormat nf = new DecimalFormat("##.#");

                    // 날짜 선택 후 보여지는 창에 출력
                    infomation.setText("<html>칼로리: "+ nf.format(fd.getDayAllCalorie()) + "kcal<br><br>"+
                                       "탄수화물: " + nf.format(fd.getDayAllCarb()) + "g "+
                                       "단백질: "+ nf.format(fd.getDayAllProtein()) +"g " + "지방: " + nf.format(fd.getDayAllFat()) + "g" + "</html>");
                    infomation.setBorder(BorderFactory.createEmptyBorder(30,30,15,30));


                    

                    // 몸무게를 가져오는 DB연동 코드에 클릭 한 날짜 저장
                    FindWeightDB fw = new FindWeightDB(clickday);
                    if (fw.getdayweight() == 0.0) {
                        weight.setText("기록 없음");
                    } else {
                        weight.setText(fw.getdayweight()+"kg");
                    }
  
                    // 몸무게를 입력하는 DB연동을 코드에 클릭 한 날짜를 저장
                    Inputdayweight inputday = new Inputdayweight();
                    inputday.setdbday(clickday);

                    
                }
            });


            

            

            
            date.set(Calendar.DATE, day); // 19 -> 1
            int w = date.get(Calendar.DAY_OF_WEEK);  
            if(w==1) {
                daybutton.setForeground(Color.red);   

            }
            if(w==7) {
                daybutton.setForeground(Color.blue);  

            }
            dayPane.add(lbl);
            
            
            
        }
        
    }

     
	// 달력 위 일월화수목금토 설정
    public void setCalendarTitle() {
        Font datefont = new Font("굴림", Font.BOLD, 20);
        for(int i=0; i<title.length; i++) {  
            JLabel lbl = new JLabel(title[i], JLabel.CENTER); 
            lbl.setFont(datefont); 
            if(i==0) {
                lbl.setForeground(Color.red);  
            }
            if(i==6) {
                lbl.setForeground(Color.blue);  
            }
            titlePane.add(lbl);
        }
    }

    
	// 년도 설정
    public void setYear() {
        for(int i= year-50; i<year+20; i++) {  
            yearCombo.addItem(i); 
        }
        yearCombo.setSelectedItem(year); 
    }

    // 달 설정
    public void setMonth() {
        for(int i=1; i<=12; i++) {  
            monthCombo.addItem(i); 
        }
        monthCombo.setSelectedItem(month);
    }


    // 콤보박스 설정
    public void itemStateChanged(ItemEvent e) { 
        year = (int)yearCombo.getSelectedItem();  
        month = (int)monthCombo.getSelectedItem();

        dayPane.setVisible(false);  
        dayPane.removeAll();  
        setDay();  
        dayPane.setVisible(true);

		// 팝업 달력 콤보박스
		subyear = (int)subyearCombo.getSelectedItem(); 
		submonth = (int)submonthCombo.getSelectedItem();

		subdayPane.setVisible(false);
		subdayPane.removeAll();
		subsetDay(); 
		subdayPane.setVisible(true);
    }


    // 버튼 설정
    public void actionPerformed(ActionEvent ae) {  
        Object obj = ae.getSource();  
        if(obj == preBtn) {     
            preMonth();   
            setDayReset();  
        }
        else if(obj == nexBtn) {   
            nexMonth();  
            setDayReset();  
        }

		// 팝업 달력 버튼 설정
		if(obj == subpreBtn) { 
			subpreMonth(); 
			subsetDayReset(); 
		}
		else if(obj == subnexBtn) { 
			subnexMonth(); 
			subsetDayReset(); 
		}
    }

    // 버튼 클릭 시 동작 설정
    private void setDayReset() {
          
        yearCombo.removeItemListener(this);  
        monthCombo.removeItemListener(this);

        yearCombo.setSelectedItem(year);  
        monthCombo.setSelectedItem(month);

        dayPane.setVisible(false);  
        dayPane.removeAll(); 
        setDay();  
        dayPane.setVisible(true);  

        yearCombo.addItemListener(this);  
        monthCombo.addItemListener(this);
    }

	// 이전 달 클릭 설정
    public void preMonth() {
        if(month == 1) { 
            year --;
            month = 12;
        }
        else {    
            month --;
        }
    }

	// 다음 달 클릭 설정
    public void nexMonth() {
        if(month == 12) {
            year ++;
            month = 1;
        }
        else {
            month ++;
        }
    }

    // 처음 실행 시 현재 날짜 찾기
    public String findday(){
        date = Calendar.getInstance(); 
        year = date.get(Calendar.YEAR);  
        month = date.get(Calendar.MONTH)+1;  
        day = date.get(Calendar.DATE);

        String strmonth="";
        String strday="";

        if(month<10){
            strmonth = "0"+month;
        }
        else{
            strmonth = month+"";
        }
        if(day<10){
            strday = "0"+day;
        }
        else{
            strday = day+"";
        }
        String findday = year + "-" + strmonth + "-" +strday;
        return findday;
    }

    public String subfindday(){
        subdate = Calendar.getInstance(); 
        subyear = subdate.get(Calendar.YEAR);  
        submonth = subdate.get(Calendar.MONTH)+1;  
        subday = subdate.get(Calendar.DATE);

        String substrmonth="";
        String substrday="";

        if(month<10){
            substrmonth = "0"+submonth;
        }
        else{
            substrmonth = submonth+"";
        }
        if(day<10){
            substrday = "0"+subday;
        }
        else{
            substrday = subday+"";
        }
        String subfindday = subyear + "-" + substrmonth + "-" +substrday;
        return subfindday;
    }


	// --------------팝업 달력 함수 ----------------//
	public void subsetDay() {
		
		subdate.set(subyear, submonth-1, 1); 
		int subweek = subdate.get(Calendar.DAY_OF_WEEK); 
			
		int sublastDay = subdate.getActualMaximum(Calendar.DATE);  

		for(int s=1; s<subweek; s++) {
			JLabel sublbl = new JLabel(" "); 
			subdayPane.add(sublbl);
			
		}
		
			for(int subday=1; subday<=sublastDay; subday++) {
			JButton sublbl = new JButton(); 

            sublbl.setLayout(new GridLayout(1,1));
            sublbl.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            sublbl.setBackground(new Color(255,255,255));
			

			JButton subdaybutton = new JButton();
			subdaybutton.setText(String.valueOf(subday));
            subdaybutton.setFont(new Font("돋움",1,12));
            subdaybutton.setPreferredSize(new Dimension(50, 40));
            subdaybutton.setBackground(new Color(255,255,255));
            
            sublbl.add(subdaybutton);
            
                
			subdaybutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
                    

                    String strsubmonth="";
                    String strsubday="";
                    if(submonth<10){
                        strsubmonth = "0"+submonth;
                    }
                    else{
                        strsubmonth = submonth+"";
                    }
                    if(Integer.parseInt(subdaybutton.getText())<10){
                        strsubday = "0"+subdaybutton.getText();
                    }
                    else{
                        strsubday = subdaybutton.getText();
                    }
                    
                    String subclickday = subyear + "-" + strsubmonth + "-" +strsubday;
                    // 서브 달력의 날짜를 눌렀을 때 yyyy/mm/dd의 값을 getdayString에 저장

                    Recorddatewithcalendar.setsubRecorddate(subclickday);
                    subdayset.setText(subclickday);
                    

                    setBackground(Color.blue);
				}
			});

			subdate.set(Calendar.DATE, subday);  
			int w = subdate.get(Calendar.DAY_OF_WEEK);  
			if(w==1) {
				subdaybutton.setForeground(Color.red); 

			}
			if(w==7) {
				subdaybutton.setForeground(Color.blue);   

			}
			subdayPane.add(sublbl);
		}
	}

	public void subsetCalendarTitle() {
		Font datefont = new Font("굴림", Font.BOLD, 15);
		for(int i=0; i<subtitle.length; i++) {  
			JLabel sublbl = new JLabel(subtitle[i], JLabel.CENTER);   
            sublbl.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            sublbl.setOpaque(true);
            sublbl.setBackground(new Color(255,255,255));
			sublbl.setFont(datefont);  
			if(i==0) {
				sublbl.setForeground(Color.red); 
			}
			if(i==6) {
				sublbl.setForeground(Color.blue);  
			}
			subtitlePane.add(sublbl);
		}
	}

		
	public void subsetYear() {
		for(int i= year-50; i<year+20; i++) { 
			subyearCombo.addItem(i); 
		}
		subyearCombo.setSelectedItem(subyear); 
	}

		
	public void subsetMonth() {
		for(int i=1; i<=12; i++) { 
			submonthCombo.addItem(i); 
		}
		submonthCombo.setSelectedItem(submonth);
	}
		
	private void subsetDayReset() {
			
		subyearCombo.removeItemListener(this); 
		submonthCombo.removeItemListener(this);

		subyearCombo.setSelectedItem(subyear);  
		submonthCombo.setSelectedItem(submonth);

		subdayPane.setVisible(false); 
		subdayPane.removeAll();  
		subsetDay();  
		subdayPane.setVisible(true);  

		subyearCombo.addItemListener(this);  
		submonthCombo.addItemListener(this);
	}

	public void subpreMonth() {
		if(submonth == 1) { 
			subyear --;
			submonth = 12;
		}
		else {   
			submonth --;
		}
	}

	public void subnexMonth() {
		if(submonth == 12) {
			subyear ++;
			submonth = 1;
		}
		else {
			submonth ++;
		}
	}
	//-----------------------------------------------------------//

}



 