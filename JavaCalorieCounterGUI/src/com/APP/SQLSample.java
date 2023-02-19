package com.APP;

import java.sql.*;

public class SQLSample {
    public static void main(String[] args) {
    	try {
			// ConnectionManager를 만들고 .get() function으로 Connection을 만듭니다.
			ConnectionManager connectionManager = new ConnectionManager();
			Connection conn = connectionManager.get();

			// SQL 처리를 위한 PreparedStatement와 ResultSet을 준비합니다.
			PreparedStatement statement = null;
			ResultSet result = null;

			String queryString = "SELECT * FROM emp";
			statement = conn.prepareStatement(queryString);

			// Select문은 executeQuery(), Insert, Update, Delete는 executeUpdate()
			result = statement.executeQuery();

			ResultSetMetaData rsmd = result.getMetaData();
			int colsNumber = rsmd.getColumnCount();

			while(result.next()) {
				for (int i = 1; i <= colsNumber; i++) {
					if (i > 1) {
						System.out.print(", ");
					}
					String columnValue = result.getString(i);
					System.out.print(rsmd.getColumnName(i) + " " + "'" + columnValue + "'");
				}
				System.out.println("");
			}
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
    }
}