package com.APP.FoodSearch.UploadFoodInfo;

import java.awt.*;
import javax.swing.*;

import com.APP.Common.JPanelSize;
import com.APP.NavBar.bottomMenuPane;
import com.APP.FoodSearch.AppFoodSearch;

import java.awt.event.*;
import java.sql.SQLException;

public class DeleteFoodInfo extends JFrame{

    JScrollPane scroll=null;
    JFrame jframe = this;
    JButton[] jbtn1 = new JButton[80];
	
    public DeleteFoodInfo() throws SQLException{
		initialize();
	}
		
    private void initialize() throws SQLException{

        //  컨테이너
        setTitle("음식 검색 페이지");
		Container c = getContentPane();
		c.setBackground(new Color(255, 247, 225));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

        // 화면 사이즈
		JPanelSize jPanelSize = new JPanelSize();
        int BasicWidth = jPanelSize.getWidth();
		int BasicHeight = jPanelSize.getHeight();
		setSize(BasicWidth, BasicHeight);

        // 상단 메뉴바
		JPanel firstBtnPanel=new JPanel(new GridLayout(1,3));
		firstBtnPanel.setPreferredSize(new Dimension(BasicWidth, 40));
		c.add(firstBtnPanel, BorderLayout.NORTH);

		// 상단 메뉴바에 버튼 부착
		JButton firstPanelbtn1 = new JButton("검색");
		JButton firstPanelbtn2 = new JButton("추가");
        JButton firstPanelbtn3 = new JButton("삭제");
		firstPanelbtn1.setBackground(new Color(255, 247, 225));
		firstPanelbtn2.setBackground(new Color(255, 247, 225));
        firstPanelbtn3.setBackground(new Color(255, 247, 225));
		firstPanelbtn3.setBorder(null);
		firstBtnPanel.add(firstPanelbtn1);
		firstBtnPanel.add(firstPanelbtn2);
        firstBtnPanel.add(firstPanelbtn3);

        JLabel searchLabel = new JLabel("음식 찾기 : ");
        JFormattedTextField searchTextField = new JFormattedTextField();
        JButton searchButton = new JButton("확인");
        JPanel searchTopPanel=new JPanel(new BorderLayout());

        searchLabel.setBorder(BorderFactory.createEmptyBorder(0, 20,0, 20));
        searchButton.setBackground(new Color(255, 247, 225));
		searchButton.setBorder(null);
        searchButton.setBorder(BorderFactory.createEmptyBorder(0, 30,0, 40));

        searchTopPanel.setBounds(0,0,100,60);
        searchTopPanel.setBackground(new Color(255, 247, 225));
		searchTopPanel.setPreferredSize(new Dimension(BasicWidth,60));
		searchTopPanel.add(searchLabel,BorderLayout.WEST);
		searchTopPanel.add(searchTextField);
		searchTopPanel.add(searchButton,BorderLayout.EAST);
        
        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel jbtnPanel = new JPanel(null);

        searchPanel.add(searchTopPanel, BorderLayout.NORTH);

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
            searchPanel.add(jbtnPanel);
        }

        // 스크롤팬
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(searchPanel);
		scroll.setPreferredSize(new Dimension(BasicWidth, BasicHeight));
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		scroll.setBorder(null);
		c.add(scroll, BorderLayout.CENTER);        

        JPanel bottomMenuPane= new bottomMenuPane(BasicWidth, BasicHeight/8, this);
		c.add(bottomMenuPane, BorderLayout.SOUTH);

        // '검색' 버튼 누르면 AppFoodSearch 화면으로 이동
		firstPanelbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AppFoodSearch appFoodSearch = new AppFoodSearch();
					appFoodSearch.setLocationRelativeTo(jframe);
					appFoodSearch.setVisible(true);
					dispose();
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
        // '추가' 버튼 누르면 UploadFoodInfo 화면으로 이동
		firstPanelbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UploadFoodInfo uploadFoodInfo = new UploadFoodInfo();
					uploadFoodInfo.setLocationRelativeTo(jframe);
					uploadFoodInfo.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

        // srch_btn1.addActionListener(new ActionListener() {

		// 	@Override
		// 	public void actionPerformed(ActionEvent e) {
		// 		ResultSet result  = null;

		// 			list = new ArrayList<Food>();

					 
					
		// 			FoodConstants.setUserFoodSearchingInput(srch_input1.getText());
		// 			String bb = FoodConstants.getUserFoodSearchingInput();
		// 			System.out.println("bb:"+bb);
		// 			try {	
						
		// 				String sql1 = "select * from food where food_name like ?";
		// 					statement = conn.prepareStatement(sql1);
		// 					statement.setString(1, "%" + bb + "%");
		// 					result  = statement.executeQuery();
		// 					while (result.next()) {
		// 						list.add(new Food(result.getInt(1), result.getString(2), result.getString(3), 
		// 						result.getString(4), result.getInt(5),result.getFloat(6), result.getFloat(7),
		// 						result.getFloat(8), result.getFloat(9), result.getFloat(10), result.getFloat(11)));
		// 					}
		// 					for(int i=0; i<jbtn1.length; i++){
		// 						jbtn1[i].setText(" ");
		// 						cnt=list.size()-1;
														
		// 						if(!bb.equals("")){
		// 							panel.setPreferredSize(new Dimension(560,68*(list.size()-1)));
		// 						}	
		// 						else{
		// 							panel.setPreferredSize(new Dimension(560, 5225));
		// 						}
		// 						mainPanel.add(sp,BorderLayout.CENTER);
		// 					}

		// 					for (int i = 0; i < list.size(); i++) {
		// 						//
		// 						if(i>list.size()){
		// 							continue;
		// 						}
		// 						jbtn1[i].setText(list.get(i).getFood_name() + ", " + list.get(i).getFood_category()
		// 								+ ", 1" + list.get(i).getFood_metricname() + ", "+ list.get(i).getFood_metricgrams() 
		// 								+ ", " + list.get(i).getFood_calorie() +"kcal, "+list.get(i).getFood_carb()+", "+list.get(i).getFood_protein()+", "
		// 								+list.get(i).getFood_fat()+", "+list.get(i).getFood_sodium()+", "+list.get(i).getFood_sugar());

										
		// 					}
		// 				statement.close();
		// 			} catch (SQLException e1) {
		// 				e1.printStackTrace();
		// 			}
		// 		}
		// 	});
		// }


        setVisible(true);
    }

	public static void main(String[] args) throws SQLException {
		
		new DeleteFoodInfo();
		
	}
}