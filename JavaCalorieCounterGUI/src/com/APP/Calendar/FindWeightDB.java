package com.APP.Calendar;

import java.sql.*;
import java.util.ArrayList;
import com.APP.ConnectionManager;

public class FindWeightDB {

	int count = 0;
	ArrayList<WeightDB> weightlist;

	public FindWeightDB(String findday){

		try{
			ConnectionManager connectionManager = new ConnectionManager();
            Connection conn = connectionManager.get();

			weightlist = new ArrayList<>();

			PreparedStatement weightstatement = null;
			ResultSet weightresult = null;
			//findday = "'" + findday + "'";
			
			//System.out.println("찾는 날짜 : " + findday);

			String sql = "select user_date, user_weight from userweight WHERE TO_CHAR(user_date, 'YYYY-MM-DD') = ?"; 
			weightstatement = conn.prepareStatement(sql);
			weightstatement.setString(1, findday);

			weightresult = weightstatement.executeQuery();

			// 레코드 테이블에서 검색할 날짜, 먹은 양, 음식 번호
			double user_weight;
			
			// 결과 불러오기
			while (weightresult.next()) {

            user_weight = weightresult.getDouble("user_weight");

			// DB에서 가져온 정보로 오브젝트 제작
			WeightDB weightDB = new WeightDB(user_weight);

			// 리스트 안에 저장
            weightlist.add(weightDB);
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	// 시험용 출력
	public void testprint() {
        if (weightlist.isEmpty() || weightlist == null) {
            // System.out.println("no weight input");
            return;
        }

        for (int i = 0; i < weightlist.size(); i++) {
            System.out.println(weightlist.get(i));
        }
    }

	public double getdayweight() {
        if (weightlist.isEmpty() || weightlist == null) {
            // System.out.println("no weight input");
            return 0.0;
        }

        double sum = 0.0;

        for (WeightDB k: weightlist) {
            sum += k.getfindweight(); 
        }

        return sum;
    }
}
	