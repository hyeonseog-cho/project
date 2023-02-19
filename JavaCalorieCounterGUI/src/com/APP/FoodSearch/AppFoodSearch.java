package com.APP.FoodSearch;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.APP.ConnectionManager;
import com.APP.Common.GlobalConstants;
import com.APP.FoodSearch.UploadFoodInfo.UploadFoodInfo;
import com.APP.NavBar.bottomMenuPane;
import com.APP.NavBar.NavWindowCtrl.NavigationController;
import com.APP.NavBar.NavWindowCtrl.PageOpen;


/**
 * 버튼을 누르면 그 음식의 정보를 출력합니다.
 */
public class AppFoodSearch extends JFrame implements GlobalConstants {
	
	class ViewInfoListener implements ActionListener {
		Food fAdd;

		ViewInfoListener (Food fAdd) {
			this.fAdd = fAdd;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame();
			
			double carbPercent = fAdd.getFood_carb() / fAdd.getFood_metricgrams() * 100;
			double proteinPercent = fAdd.getFood_protein() / fAdd.getFood_metricgrams() * 100;
			double fatPercent = fAdd.getFood_fat() / fAdd.getFood_metricgrams() * 100;

			String msgString = fAdd.getFood_name() + "\n" +
			"1" + fAdd.getFood_metricname() + " 당 " + "\n" +
			fAdd.getFood_metricgrams() + "g" + "\n" +
			fAdd.getFood_calorie() + " 칼로리" + "\n" +
			"탄수화물: " + fAdd.getFood_carb() + "g" + " (" + String.format("%.1f", carbPercent) + "%)" + "\n" +
			"단백질:  " + fAdd.getFood_protein() + "g" + " (" + String.format("%.1f", proteinPercent) + "%)" + "\n" +
			"지방: " + fAdd.getFood_fat() + "g" + " (" + String.format("%.1f", fatPercent) + "%)" + "\n" +
			"나트륨: " + fAdd.getFood_sodium() + "mg" + "\n" + 
			"총당류: " + fAdd.getFood_sugar() + "g";

			JOptionPane.showMessageDialog(frame, msgString, "음식 정보", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

	}
	
	class SearchListener implements ActionListener, KeyListener {

		JPanel panel;
		JPanel mainPanel;

		SearchListener(JPanel panel, JPanel mainPanel) {
			this.panel = panel;
			this.mainPanel = mainPanel;
		}

		@Override
			public void actionPerformed(ActionEvent e) {
					ResultSet result  = null;

					list = new ArrayList<Food>();
					FoodConstants.setUserFoodSearchingInput(srch_input1.getText());
					String bb = FoodConstants.getUserFoodSearchingInput();

					try {
						String sql1 = "SELECT * FROM food WHERE food_name LIKE ?";
							statement = conn.prepareStatement(sql1);
							statement.setString(1, "%" + bb + "%");
							result  = statement.executeQuery();
							while (result.next()) {
								list.add(new Food(result.getInt(1), result.getString(2), result.getString(3), 
								result.getString(4), result.getInt(5),result.getFloat(6), result.getFloat(7),
								result.getFloat(8), result.getFloat(9), result.getFloat(10), result.getFloat(11)));
							}
							
							for(int i = 0; i < jbtn1.length; i++){
								jbtn1[i].setText(" ");
								cnt = list.size()-1;
														
								if (!bb.equals("")){
									panel.setPreferredSize(new Dimension(560,68*(list.size()-1)));
								}	
								else {
									panel.setPreferredSize(new Dimension(560, 5225));
								}

								mainPanel.add(sp, BorderLayout.CENTER);
							}

							for (int i = 0; i < list.size(); i++) {
								
								if (i >= jbtn1.length){
									break;
								}

								// 버튼에 정보 넣기
								jbtn1[i].setText(list.get(i).getFood_name() + ", " + list.get(i).getFood_category()
										+ ", 1" + list.get(i).getFood_metricname() + ", "+ list.get(i).getFood_metricgrams() 
										+ ", " + list.get(i).getFood_calorie() +"kcal, "+list.get(i).getFood_carb()+", "+list.get(i).getFood_protein()+", "
										+list.get(i).getFood_fat()+", "+list.get(i).getFood_sodium()+", "+list.get(i).getFood_sugar());				
	
								// 버튼에 이미 있는 리스너 삭제
								for (ActionListener al : jbtn1[i].getActionListeners()) {
									jbtn1[i].removeActionListener(al);
								}

								// 버튼 리스너 추가하기
								jbtn1[i].addActionListener(new ViewInfoListener(list.get(i)));
							}

						statement.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

		@Override
		public void keyTyped(KeyEvent e) {
			this.actionPerformed(null);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			this.actionPerformed(null);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			this.actionPerformed(null);
		}
	}

	class updateFoodInfoListener implements ActionListener {

		JFrame appFoodSearch;

		updateFoodInfoListener(JFrame appFoodSearch) {
			this.appFoodSearch = appFoodSearch;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			UploadFoodInfo uploadFoodInfo = new UploadFoodInfo();
			uploadFoodInfo.setVisible(true);
			uploadFoodInfo.setLocationRelativeTo(appFoodSearch);
			appFoodSearch.dispose();
		}
	}

	Connection conn;
	int cnt=0;
	JButton[] jbtn1 = new JButton[80];
	//
	JButton firstPanelbtn1 = new JButton("검색");	
	JButton firstPanelbtn2 = new JButton("추가");
	JButton srch_btn1=new JButton("확인");
	JLabel srch_input1Label =new JLabel("검색 : ");
	
	
	JPanel srch_textpane = new JPanel(new GridLayout(3,1));
	JLabel nonelabel = new JLabel(" ");
	JFormattedTextField srch_input1 = new JFormattedTextField();
	JLabel nonelabel2 = new JLabel(" ");
	
	//
	JScrollPane sp=null;

	List<Food> list=null;
	Food food = null;

	private PreparedStatement statement= null;
	
	public AppFoodSearch() {
		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void initialize() {
			try {
			//db
			ConnectionManager connectionManager = new ConnectionManager();
			conn = connectionManager.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//컨테이너
			Container container = getContentPane();
				setVisible(true);
			container.setLayout(new BorderLayout());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setSize(560, 840);
			setTitle("음식 검색 페이지");


			//
			JPanel srch_input1firstPanel=new JPanel(new BorderLayout());
			JPanel secondPanel=new JPanel(new BorderLayout());
			JPanel thirdPanel=new JPanel(new BorderLayout());
			//
			JPanel firstBtnPanel=new JPanel(new GridLayout(1,2));
			JPanel mainPanel =new JPanel(new BorderLayout());
			//
			JPanel bottomMenuPane=new bottomMenuPane(560, 840/8, this);

			//



			//
			JPanel ContainerPanel=new JPanel(new BorderLayout());
			ContainerPanel.add(srch_input1firstPanel, BorderLayout.NORTH);
			ContainerPanel.add(secondPanel, BorderLayout.CENTER);
			ContainerPanel.add(thirdPanel, BorderLayout.SOUTH);
			
			
			container.add(firstBtnPanel, BorderLayout.NORTH);
			container.add(ContainerPanel);
			
			//
			JPanel panel = new JPanel(new BorderLayout());			
			JPanel jbtnPanel = new JPanel(null);
			jbtnPanel.setBackground(new Color(45,99,255));
			//panel.setPreferredSize(new Dimension(560, 5220));
			sp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setViewportView(panel);
			sp.setPreferredSize(new Dimension(543, 100));
			sp.setBorder(null);
			
			//
			srch_input1firstPanel.setBounds(0,20,100,60);
			srch_input1firstPanel.add(srch_input1Label,BorderLayout.WEST);
			srch_input1firstPanel.add(srch_textpane);
			srch_textpane.setBackground(new Color(45,99,255));
			srch_textpane.add(nonelabel);
			srch_textpane.add(srch_input1);
			srch_textpane.add(nonelabel2);
			nonelabel.setBackground(new Color(45,99,255));
			nonelabel2.setBackground(new Color(45,99,255));
			srch_input1firstPanel.add(srch_btn1,BorderLayout.EAST);
			srch_btn1.setBackground(new Color(45,99,255));
			srch_btn1.setBorder(null);
			secondPanel.add(mainPanel,BorderLayout.WEST);
			srch_btn1.setBorder(BorderFactory.createEmptyBorder(0, 10,0, 20));
			thirdPanel.add(bottomMenuPane);

			//
			firstBtnPanel.setPreferredSize(new Dimension(500, 40));
			srch_input1firstPanel.setBackground(new Color(45,99,255));
			srch_input1firstPanel.setPreferredSize(new Dimension(560,60));
			mainPanel.add(sp,BorderLayout.CENTER);
			
			//
			for (int i = 0; i < jbtn1.length; i++) {
				jbtn1[i] = new JButton();
				jbtnPanel.setBounds(0, 5, 540, 60);
				jbtn1[i].setBounds(5, i * 65, 540, 60);
				jbtn1[i].setBackground(new Color(127,185,255));
				jbtn1[i].setBorder(null);
				jbtn1[i].setHorizontalAlignment(JLabel.LEFT);
				jbtnPanel.setBounds(0, 5, 560, 60);
				}
		
				
			
				for (int i = 0; i < jbtn1.length; i++) {
					jbtnPanel.add(jbtn1[i]);		
					panel.add(jbtnPanel);
				
				}

			//
			firstBtnPanel.add(firstPanelbtn1);
			firstBtnPanel.add(firstPanelbtn2);
			firstPanelbtn1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			firstPanelbtn1.setForeground(new Color(255,255,255));
			firstPanelbtn1.setBounds(2,5,270,30);
			firstPanelbtn2.setBounds(272,5,270,30);
			firstPanelbtn1.setBackground(new Color(45,99,255));
			firstPanelbtn2.setBackground(new Color(106,129,193));
			// firstPanelbtn1.setBorder(null);
			// firstPanelbtn2.setBorder(null);

			//
			srch_input1.setColumns(5);
			srch_input1.setBackground(new Color(127,185,255));
			srch_input1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			srch_input1.setBorder(null);	
			srch_input1Label.setBorder(BorderFactory.createEmptyBorder(0, 20,0, 10));
			srch_input1Label.setForeground(new Color(0,0,0));
			srch_input1Label.setFont(new Font("맑은 고딕", Font.BOLD, 18));

			//
			firstPanelbtn2.addActionListener(new updateFoodInfoListener(this));
			
			SearchListener sl = new SearchListener(panel, mainPanel);

			srch_btn1.addActionListener(sl);
			srch_input1.addKeyListener(sl);
		}
	
}