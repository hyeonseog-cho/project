package com.APP.DayCalories;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import com.APP.ConnectionManager;
import com.APP.Common.GlobalConstants;
import com.APP.NavBar.bottomMenuPane;

public class AppFoodSearchAdd extends JFrame implements GlobalConstants {
	
	int cnt = 0;
	JButton[] jbtn1 = new JButton[80];

	JButton srch_btn1 = new JButton("확인");
	JLabel srch_input1Label = new JLabel("검색 : ");
	JFormattedTextField srch_input1 = new JFormattedTextField();

	String date;
	String recordType;

	JScrollPane sp = null;

	List<FoodToAdd> list = null;
	FoodToAdd food = null;
	private PreparedStatement statement = null;

	class addButtonListener implements ActionListener {

		FoodToAdd fAdd;
		JFrame parentFrame;
		Connection conn;

		addButtonListener(FoodToAdd fAdd, JFrame parentFrame, Connection conn) {
			this.fAdd = fAdd;
			this.parentFrame = parentFrame;
			this.conn = conn;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame confirmFrame = new JFrame();
			Boolean correctInputed = false;
			Double amount = null;
	
			double carbPercent = fAdd.getFood_carb() / fAdd.getFood_metricgrams() * 100;
			double proteinPercent = fAdd.getFood_protein() / fAdd.getFood_metricgrams() * 100;
			double fatPercent = fAdd.getFood_fat() / fAdd.getFood_metricgrams() * 100;

			String foodinfo = fAdd.getFood_name() + "\n" +
			"1" + fAdd.getFood_metricname() + " 당 " + "\n" +
			fAdd.getFood_metricgrams() + "g" + "\n" +
			fAdd.getFood_calorie() + " 칼로리" + "\n" +
			"탄수화물: " + fAdd.getFood_carb() + "g" + " (" + String.format("%.1f", carbPercent) + "%)" + "\n" +
			"단백질:  " + fAdd.getFood_protein() + "g" + " (" + String.format("%.1f", proteinPercent) + "%)" + "\n" +
			"지방: " + fAdd.getFood_fat() + "g" + " (" + String.format("%.1f", fatPercent) + "%)" + "\n" +
			"나트륨: " + fAdd.getFood_sodium() + "mg" + "\n" + 
			"총당류: " + fAdd.getFood_sugar() + "g" + "\n"
			+ "몇 " + fAdd.getFood_metricname() + "을(를) 먹었나요?";

			while (true) {
				String amouString = JOptionPane.showInputDialog(confirmFrame, foodinfo);
				correctInputed = false;

				// 취소 버튼을 눌렀을시 나가기
				if (amouString == null) {
					break;
				}

				// 숫자 넣었는지 확인
				try {
					amount = Double.parseDouble(amouString);

				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(confirmFrame, "숫자가 아닙니다.", "오류", JOptionPane.ERROR_MESSAGE);
					amouString = "a";
					continue;
				}
				
				// 정당한 숫자가 입력되었음
				correctInputed = true;
				break;
			}

			if (correctInputed && amount != null) {
				String query = null;
				PreparedStatement addStatement = null;
				java.sql.Timestamp sqlDate = java.sql.Timestamp.valueOf(date + " 12:00:00");
				ResultSet rSet = null;

				// DB에 저장하기 시도
				try {
					// 먼저 같은 음식을 먹은적이 있는지 체크하고, 있으면 Insert 대신 Update 사용.

					query = "SELECT * FROM record WHERE record_date = ? AND record_food_id = ? AND record_type = ?";

					addStatement = conn.prepareStatement(query);
					addStatement.setTimestamp(1, sqlDate);
					addStatement.setInt(2, fAdd.getFood_pk());
					addStatement.setString(3, recordType);

					rSet = addStatement.executeQuery();

					if (!rSet.isBeforeFirst()) {
						addStatement.close();
						// 이미 저장된 음식이 없으므로 INSERT문을 이용해 집어넣기.
						query = "INSERT INTO record (record_date, record_food_id, record_food_amount, record_type) VALUES (?, ?, ?, ?)";
						PreparedStatement insertStatement = conn.prepareStatement(query);

						insertStatement.setTimestamp(1, sqlDate);
						insertStatement.setInt(2, fAdd.getFood_pk());
						insertStatement.setDouble(3, amount);
						insertStatement.setString(4, recordType);

						if (insertStatement.executeUpdate() == 0) {
							//System.out.println("무언가 잘못됨");
						} else {
							//System.out.println("저장했다.");
						}

					} else {
						// 이미 저장된 음식이 있으므로 UPDATE 문을 이용해 수량을 바꾼다.
						query = "UPDATE record SET record_food_amount = ? WHERE record_id = ?";
						PreparedStatement updateStatement = conn.prepareStatement(query);

						while (rSet.next()) {
							double totalAmount = amount + rSet.getDouble("record_food_amount");

							updateStatement.setDouble(1, totalAmount);
							updateStatement.setInt(2, rSet.getInt("record_id"));
	
							if (updateStatement.executeUpdate() == 0) {
								//System.out.println("무언가 잘못됨");
							} else {
								//System.out.println("저장했다.");
							}
						}
						 
						addStatement.close();
					}
					
					// 이 화면 닫고 원래 화면 열기
					AppDayCalories appDayCalories = new AppDayCalories(date);
					appDayCalories.setLocationRelativeTo(parentFrame);
					appDayCalories.setVisible(true);
					parentFrame.dispose();
					

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
	}

	class searchFoodListener implements ActionListener, KeyListener {

		JPanel panel;
		JPanel mainPanel;
		JFrame parentFrame;
		Connection conn;

		public searchFoodListener(JPanel panel, JPanel mainPanel, JFrame parentFrame, Connection conn) {
			this.panel = panel;
			this.mainPanel = mainPanel;
			this.parentFrame = parentFrame;
			this.conn = conn;
		}

		@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet result = null;
				list = new ArrayList<FoodToAdd>();

				FoodConstants.setUserFoodSearchingInput(srch_input1.getText());
				String bb = FoodConstants.getUserFoodSearchingInput();
				//System.out.println("bb:" + bb);
				try {

					String sql1 = "select * from food where food_name like ?";
					statement = conn.prepareStatement(sql1);
					statement.setString(1, "%" + bb + "%");
					result = statement.executeQuery();

					while (result.next()) {
						// 리스트에 저장
						FoodToAdd fToAdd = new FoodToAdd(result.getInt(1), result.getString(2), result.getString(3),
						result.getString(4), result.getInt(5), result.getFloat(6), result.getFloat(7),
						result.getFloat(8), result.getFloat(9), result.getFloat(10), result.getFloat(11));
						
						list.add(fToAdd);
					}

					for (int i = 0; i < jbtn1.length; i++) {
						jbtn1[i].setText(" ");

						cnt = list.size() - 1;

						if (!bb.equals("")) {
							panel.setPreferredSize(new Dimension(560, 68 * (list.size() - 1)));
						} else {
							panel.setPreferredSize(new Dimension(560, 5225));
						}
						mainPanel.add(sp, BorderLayout.CENTER);
					}

					for (int i = 0; i < list.size(); i++) {
						
						if (i >= jbtn1.length) {
							break;
						}

						jbtn1[i].setText(list.get(i).getFood_name() + ", " + list.get(i).getFood_category()
								+ ", 1" + list.get(i).getFood_metricname() + ", " + list.get(i).getFood_metricgrams()
								+ ", " + list.get(i).getFood_calorie() + "kcal, " + list.get(i).getFood_carb() + ", "
								+ list.get(i).getFood_protein() + ", "
								+ list.get(i).getFood_fat() + ", " + list.get(i).getFood_sodium() + ", "
								+ list.get(i).getFood_sugar());
						
						
						// 이미 추가된 리스너 삭제하기
						for (ActionListener al : jbtn1[i].getActionListeners()) {
							jbtn1[i].removeActionListener(al);
						}
						
						// 버튼 리스너 추가하기
						jbtn1[i].addActionListener(new addButtonListener(list.get(i), parentFrame, conn));
					}
					statement.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		@Override
		public void keyTyped(KeyEvent e) {
			actionPerformed((ActionEvent) null);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			actionPerformed((ActionEvent) null);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			actionPerformed((ActionEvent) null);
		}
	}

	

	public AppFoodSearchAdd(String date, String recordType) throws SQLException, IOException {
		this.date = date;
		this.recordType = recordType;
		initialize();
	}

	private void initialize() throws SQLException, IOException {
		// db
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.get();

		// 컨테이너
		Container container = getContentPane();
		setVisible(true);
		container.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(560, 840);
		setTitle("먹은 음식 추가");

		//
		JPanel srch_input1firstPanel = new JPanel(new BorderLayout());
		JPanel secondPanel = new JPanel(new BorderLayout());
		JPanel thirdPanel = new JPanel(new BorderLayout());

		JPanel mainPanel = new JPanel(new BorderLayout());
		//
		JPanel bottomMenuPane = new bottomMenuPane(560, 840 / 8, this);

		//

		//
		JPanel ContainerPanel = new JPanel(new BorderLayout());
		ContainerPanel.add(srch_input1firstPanel, BorderLayout.NORTH);
		ContainerPanel.add(secondPanel, BorderLayout.CENTER);
		ContainerPanel.add(thirdPanel, BorderLayout.SOUTH);

		container.add(ContainerPanel);

		//
		JPanel panel = new JPanel(new BorderLayout());
		JPanel jbtnPanel = new JPanel(null);

		// panel.setPreferredSize(new Dimension(560, 5220));
		sp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setViewportView(panel);
		sp.setPreferredSize(new Dimension(543, 100));
		sp.setBorder(null);

		//
		srch_input1firstPanel.setBounds(0, 20, 100, 60);
		srch_input1firstPanel.add(srch_input1Label, BorderLayout.WEST);
		srch_input1firstPanel.add(srch_input1);
		srch_input1firstPanel.add(srch_btn1, BorderLayout.EAST);
		srch_btn1.setBackground(new Color(255, 247, 225));
		srch_btn1.setBorder(null);
		secondPanel.add(mainPanel, BorderLayout.WEST);
		srch_btn1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
		thirdPanel.add(bottomMenuPane);

		srch_input1firstPanel.setBackground(new Color(255, 247, 225));
		srch_input1firstPanel.setPreferredSize(new Dimension(560, 60));
		mainPanel.add(sp, BorderLayout.CENTER);

		for (int i = 0; i < jbtn1.length; i++) {
			jbtn1[i] = new JButton();
			jbtnPanel.setBounds(0, 5, 540, 60);
			jbtn1[i].setBounds(5, i * 65, 540, 60);
			jbtn1[i].setBackground(new Color(255, 255, 225));
			jbtn1[i].setBorder(null);
			jbtn1[i].setHorizontalAlignment(JLabel.LEFT);
			jbtnPanel.setBounds(0, 5, 560, 60);
		}

		for (int i = 0; i < jbtn1.length; i++) {
			jbtnPanel.add(jbtn1[i]);
			panel.add(jbtnPanel);
		}

		srch_input1.setColumns(5);
		srch_input1.setBackground(new Color(255, 247, 225));
		srch_input1.setBorder(null);
		srch_input1Label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));


		searchFoodListener sfl = new searchFoodListener(panel, mainPanel, this, conn);
		srch_btn1.addActionListener(sfl);
		srch_input1.addKeyListener(sfl);
	}
}