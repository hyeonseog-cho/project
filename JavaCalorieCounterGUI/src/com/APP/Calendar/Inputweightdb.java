

package com.APP.Calendar;

import java.sql.Connection;
import javax.swing.*;

import com.APP.ConnectionManager;
import java.sql.*;


public class Inputweightdb {

	private int count = 0;
	private PreparedStatement pstmt = null;
	JFrame parentFrame;

	public boolean Dayweight(String day, double weight, JFrame parentFrame){

		try{
			ConnectionManager connectionManager = new ConnectionManager();
            Connection conn = connectionManager.get();
			
			ResultSet result = null;

			// 입력받은 체중 문자열로 변환
			//String strweight = "'" + weight + "'";
			//day = "'" + day + "'";

			String sqlquery = "SELECT * FROM userweight WHERE TO_CHAR(user_date, 'YYYY-MM-DD') = ? ";
			pstmt = conn.prepareStatement(sqlquery);
			pstmt.setString(1, day);
			result = pstmt.executeQuery();

			if (result.isBeforeFirst()) {
				// 값이 있음
				String uQuery = "UPDATE userweight SET user_weight = ? WHERE TO_CHAR(user_date, 'YYYY-MM-DD') = ?";
				PreparedStatement uPreparedStatement = conn.prepareStatement(uQuery);
				uPreparedStatement.setDouble(1, weight);
				uPreparedStatement.setString(2, day);
				
				uPreparedStatement.executeUpdate();
				System.out.println("값이 있던 " + day + " " + weight + "kg로 업데이트.");

			} else {
				// 값이 없음
				String iQuery = "INSERT INTO userweight (user_date, user_weight) VALUES (?, ?)";
				PreparedStatement iPreparedStatement = conn.prepareStatement(iQuery);

				iPreparedStatement.setTimestamp(1, Timestamp.valueOf(day + " 12:00:00"));
				iPreparedStatement.setDouble(2, weight);
				
				iPreparedStatement.executeUpdate();
				System.out.println("값이 없던 " + day + " " + weight + "kg 저장.");
			}
			conn.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(parentFrame, e.getLocalizedMessage(), "DB 에러", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return count > 0 ? true : false;
    }
}

 