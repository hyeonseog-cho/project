package com.APP.FoodSearch.UploadFoodInfo;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import com.APP.ConnectionManager;
import com.APP.Common.JPanelSize;
import com.APP.FoodSearch.AppFoodSearch;
import com.APP.NavBar.bottomMenuPane;
import com.APP.NavBar.NavWindowCtrl.NavigationController;
import com.APP.NavBar.NavWindowCtrl.PageOpen;

import java.awt.event.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UploadFoodInfo extends JFrame{
	
	// 변수 선언
	int food_pk = 0;
	int highest_food_pk = 1;
	String food_name;
	String food_category;
	String food_metricname;
	String food_metricgrams;
	String food_calorie;
	String food_carb;
	String food_protein;
	String food_fat;
	String food_sodium;
	String food_sugar;

	int answer;

	JFrame jframe = this;
	
	// 콤보박스
	JComboBox<String> food_category_combo;
	String food_category_list[] = {"김치류", "곡류 및 서류", "과자류", "구이류", "국 및 탕류", "면 및 만두류", "밥류", "볶음류", "빵류", "생채및 무침류", "숙채류", "음료 및 차류", "장아찌 및 절임류", "전.적 및 부침류", "조림류", "죽 및 스프류", "찌개 및 전골류", "찜류", "튀김류", "포류", "회류", "기타"};
	JComboBox<String> food_metricname_combo;
	String food_metricname_list[] = {"개", "인분", "조각", "줄", "컵", "공기", "기타"};

	JScrollPane scroll =null;
	
	String[] inputTitle =
	{"1. 음식 이름을 입력해주세요.", 
	"2. 음식의 카테고리(분류)를 선택해주세요.", 
	"3. 음식의 1회 제공 단위를 선택해주세요.", 
	"4. 1회 제공량의 분량(g)을 입력해주세요.", 
	"5. 1회 제공량의 칼로리 양(kcal)를 입력해주세요.", 
	"6. 1회 제공량의 탄수화물 양(g)을 입력해주세요.", 
	"7. 1회 제공량의 단백질 양(g)을 입력해주세요.",
	"8. 1회 제공량의 지방 양(g)을 입력해주세요.",
	"9. 1회 제공량의 나트륨 양(mg)을 입력해주세요.",
	"10. 1회 제공량의 당류의 양(g)을 입력해주세요."};
	JPanel[] Title;
	JTextField[] foodInputField;

	String userid;
	
	// 폰트 설정
	public void setUIFont (javax.swing.plaf.FontUIResource f){
		java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get (key);
			if (value instanceof javax.swing.plaf.FontUIResource)
			UIManager.put (key, f);
		}
	}

	// 메인 코드 시작 위치
	public UploadFoodInfo() {

		userid = "20230176";

		setTitle("음식 정보 입력");
		Container c = getContentPane();
		c.setBackground(new Color(255, 247, 225));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		// 화면 사이즈
		JPanelSize jPanelSize = new JPanelSize();
        int BasicWidth = jPanelSize.getWidth();
		int BasicHeight = jPanelSize.getHeight();
		setSize(BasicWidth, BasicHeight);

		// 기본 폰트
		setUIFont (new javax.swing.plaf.FontUIResource("굴림",Font.BOLD,12));
		
		// 메인 색상 HEX 코드 #03c75a, RGB 코드 3, 199, 90
		Color MainColor = new Color(3, 199, 90);

		// 상단 메뉴바
		JPanel firstBtnPanel=new JPanel(new GridLayout(1,2));
		firstBtnPanel.setPreferredSize(new Dimension(BasicWidth, 40));
		c.add(firstBtnPanel, BorderLayout.NORTH);

		// 상단 메뉴바에 버튼 부착
		JButton firstPanelbtn1 = new JButton("검색");
		JButton firstPanelbtn2 = new JButton("추가");
		firstPanelbtn1.setBackground(new Color(106,129,193));
		firstPanelbtn2.setBackground(new Color(45,99,255));
		firstPanelbtn2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		firstPanelbtn2.setForeground(new Color(255,255,255));
		firstPanelbtn2.setBorder(null);
		firstBtnPanel.add(firstPanelbtn1);
		firstBtnPanel.add(firstPanelbtn2);

		// 메인 패널
		JPanel mainpanel = new JPanel();
		mainpanel.setSize(BasicWidth, BasicHeight);
		mainpanel.setMaximumSize(new Dimension(BasicWidth, BasicHeight));
		mainpanel.setMaximumSize(getMaximumSize());
		mainpanel.setLayout(new GridBagLayout());
		mainpanel.setBackground(new Color(45,99,255));

		// 스크롤팬
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(mainpanel);
		scroll.setPreferredSize(new Dimension(BasicWidth, BasicHeight));
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		scroll.setBorder(null);
		c.add(scroll, BorderLayout.CENTER);

		// 질문 및 입력창
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Title = new JPanel[inputTitle.length];
		foodInputField = new JTextField[inputTitle.length];
		
		
		for (int i=0; i<inputTitle.length; i++) {
			
			Title[i] = new JPanel(new GridBagLayout());
            Title[i].setOpaque(true);
			Title[i].setBackground(new Color(127,185,255));
            Title[i].setBorder(BorderFactory.createTitledBorder(inputTitle[i]));
            gridBagConstraints.ipadx = 100;
            gridBagConstraints.ipady = 20;
            gridBagConstraints.insets = new Insets(10,0,10,0);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i+1;
            if (i == 1) {
                food_category_combo = new JComboBox<>(food_category_list);
                food_category_combo.setPreferredSize(new Dimension(350,20));
                //gridBagConstraints.ipadx = 310;
				food_category_combo.setBackground(Color.white);
                Title[i].add(food_category_combo);
            } 
			else if(i == 2){
				food_metricname_combo = new JComboBox<String>(food_metricname_list);
                food_metricname_combo.setPreferredSize(new Dimension(350,20));
                //gridBagConstraints.ipadx = 310;
				food_metricname_combo.setBackground(Color.white);
                Title[i].add(food_metricname_combo);
			}
			else {
                foodInputField[i] = new JTextField();
                // foodInputField[i].setPreferredSize(new Dimension(400,40)); setColumns 사용하기로 함
				foodInputField[i].setColumns(35);
                Title[i].add(foodInputField[i]);
            }
            mainpanel.add(Title[i], gridBagConstraints);
		}

		// 확인 버튼
		JButton jb1 = new JButton("확인");
		gridBagConstraints.ipadx = 400;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.insets = new Insets(10,0,10,0);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = inputTitle.length+1;
		jb1.setBackground(new Color(127,185,255));
		mainpanel.add(jb1, gridBagConstraints);
		jb1.setBorder(new LineBorder(new Color(255,255,102), 2));

		// 하단 메뉴바
		JPanel bottomMenuPane= new bottomMenuPane(BasicWidth, BasicHeight/8, this);
		c.add(bottomMenuPane, BorderLayout.SOUTH);

		// food 테이블에서 마지막 food_pk 값 추출
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			ConnectionManager cm = new ConnectionManager();
			conn = cm.get();

			String que = "select food_pk from food";

			psmt = conn.prepareStatement(que);

			rs = psmt.executeQuery();

			while (rs.next()) {
				highest_food_pk = rs.getInt(1);
			}
			System.out.println("기존 가장 높은 food_pk: "+highest_food_pk);
			rs.close();
			psmt.close();
			conn.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		// '확인' 버튼 누르면 입력한 값을 가져와서 DB에 입력		
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				food_name = foodInputField[0].getText();
				food_category =  (String)food_category_combo.getSelectedItem();
				food_metricname = (String)food_metricname_combo.getSelectedItem();
				food_metricgrams = foodInputField[3].getText();
				food_calorie = foodInputField[4].getText();
				food_carb = foodInputField[5].getText();
				food_fat = foodInputField[6].getText();
				food_protein = foodInputField[7].getText();
				food_sodium = foodInputField[8].getText();
				food_sugar = foodInputField[9].getText();

				answer = JOptionPane.showConfirmDialog(null,
					"이름 : "+food_name+"\n"+
					"카테고리 : "+food_category+"\n"+
					"1회 제공 단위 : "+food_metricname+"\n"+
					"1회 제공량의 분량(g) : "+food_metricgrams+"\n"+
					"1회 제공량의 칼로리 양(kcal) : "+food_calorie+"\n"+
					"1회 제공량의 탄수화물 양(g) : "+food_carb+"\n"+
					"1회 제공량의 단백질 양(g) : "+food_fat+"\n"+
					"1회 제공량의 지방 양(g) : "+food_protein+"\n"+
					"1회 제공량의 나트륨 양(mg) : "+food_sodium+"\n"+
					"1회 제공량의 당류의 양(g) : "+food_sugar+"\n", 
					"입력하시겠습니까?", JOptionPane.YES_NO_OPTION );
			
				Connection con = null;
				CallableStatement cs = null;

				// Food 테이블에 값 입력
				String insertQue = "{CALL addfood (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

				/*
					CREATE OR REPLACE PROCEDURE addfood
					(cal_user_id IN VARCHAR2, 1 
					v_food_name IN VARCHAR2, 2
					v_food_category IN VARCHAR2, 3 
					v_food_metricname IN VARCHAR2, 4
					v_food_metricgrams IN NUMBER, 5
					v_food_calorie IN NUMBER, 6
					v_food_carb IN NUMBER, 7
					v_food_protein IN NUMBER, 8
					v_food_fat IN NUMBER, 9 
					v_food_sodium IN NUMBER, 10
					v_food_sugar IN NUMBER 11
					)
					AS
						v_food_pk NUMBER;
					BEGIN
						INSERT INTO food (food_name, food_category, food_metricname, food_metricgrams,
						food_calorie, food_carb, food_protein, food_fat, food_sodium, food_sugar)
						VALUES (v_food_name, v_food_category, v_food_metricname, v_food_metricgrams,
						v_food_calorie, v_food_carb, v_food_protein, v_food_fat, v_food_sodium, v_food_sugar);
						
						SELECT food_pk INTO v_food_pk FROM food WHERE food_name = v_food_name AND food_category = v_food_category AND food_calorie = v_food_calorie;
						
						INSERT INTO foodinfo_addlog (userid, logdate, food_pk) VALUES (cal_user_id, SYSTIMESTAMP, v_food_pk);
					END;
					/
				 */

				if(answer==0){
					try {
						ConnectionManager cm = new ConnectionManager();
						con = cm.get();

						cs = con.prepareCall(insertQue);

						cs.setString(1, userid);
						cs.setString(2, food_name);
						cs.setString(3, food_category);
						cs.setString(4, food_metricname);
						cs.setInt(5, Integer.parseInt(food_metricgrams));
						cs.setDouble(6, Double.parseDouble(food_calorie));
						cs.setDouble(7, Double.parseDouble(food_carb));
						cs.setDouble(8, Double.parseDouble(food_protein));
						cs.setDouble(9, Double.parseDouble(food_fat));
						cs.setDouble(10, Double.parseDouble(food_sodium));
						cs.setDouble(11, Double.parseDouble(food_sugar));

						cs.execute();
						JOptionPane.showMessageDialog(null, "새로운 음식을 등록했습니다.");

						cs.close();
						con.close();
					}
					catch(SQLIntegrityConstraintViolationException ex){
						ex.printStackTrace();
						if(ex!=null){
							JOptionPane.showMessageDialog(null, "같은 이름의 음식이 존재합니다.", "에러", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(NumberFormatException ex){
						ex.printStackTrace();
						if(ex!=null){
							JOptionPane.showMessageDialog(null, "4~10번 항목에 숫자를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
					}
					catch (Exception ex) {
						ex.printStackTrace();
						if(ex!=null){
							JOptionPane.showMessageDialog(null, "알 수 없는 오류입니다.", "에러", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "입력이 취소되었습니다.");
				}
			}
		});

		// '검색' 버튼 누르면 AppFoodSearch 화면으로 이동
		firstPanelbtn1.addActionListener(new SearchActionListener(this));

	}

	class SearchActionListener implements ActionListener {
		JFrame parentFrame;
		
		SearchActionListener(JFrame frame) {
			parentFrame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			AppFoodSearch appFoodSearch = new AppFoodSearch();
			appFoodSearch.setVisible(true);
			appFoodSearch.setLocationRelativeTo(parentFrame);
			parentFrame.dispose();
		}
	}

	/*
	public static void main(String[] args) throws SQLException {
		try {
			new UploadFoodInfo();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/
}