package com.APP.Calendar;

import java.sql.Connection;
import java.util.ArrayList;
import com.APP.ConnectionManager;
import java.sql.*;


public class FindFoodinfoDB {

	private ArrayList<RecordDB> RecordList;

	public FindFoodinfoDB(String findday){

		try{
			RecordList = new ArrayList<>();
			ConnectionManager connectionManager = new ConnectionManager();
            Connection conn = connectionManager.get();

			PreparedStatement recordstatement = null;
			ResultSet recordresult = null;
			findday = "'" + findday + "'";

			String recordqueryString = "select record_date, record_food_id, record_food_amount from record WHERE TO_CHAR(record_date, 'YYYY-MM-DD') = " + findday; 
			recordstatement = conn.prepareStatement(recordqueryString);

			recordresult = recordstatement.executeQuery();

			// 레코드 테이블에서 검색할 날짜, 먹은 양, 음식 번호
			String record_day;
			int record_food_amount;

			int record_food_id;

			// 음식테이블에 쓸 쿼리문
			String foodqueryString;
			PreparedStatement foodstatement;
			ResultSet foodresult;

			// 레코드 테이블에서 가져온 음식 번호로 검색할 음식 칼로리, 단백질
			double food_calorie;
			double food_protein;
			double food_carb;
			double food_fat;

			// 결과 불러오기
			while (recordresult.next()) {

			record_day = recordresult.getString("record_date");
			record_food_id = recordresult.getInt("record_food_id");
			record_food_amount = recordresult.getInt("record_food_amount");

			
			foodqueryString = "select food_pk, food_calorie, food_protein, food_carb, food_fat from food where food_pk=" + record_food_id;
			foodstatement = conn.prepareStatement(foodqueryString);
			foodresult = foodstatement.executeQuery();

				while(foodresult.next()){
					food_calorie = foodresult.getDouble("food_calorie");
					food_protein = foodresult.getDouble("food_protein");
					food_carb = foodresult.getDouble("food_carb");
					food_fat = foodresult.getDouble("food_fat");
					
					// DB에서 가져온 정보로 오브젝트 제작
					RecordDB RecordDB = new RecordDB(record_day,food_calorie, food_protein, food_carb, record_food_amount, food_fat);

					// 리스트 안에 저장
					RecordList.add(RecordDB);
					}
				}

				conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	public ArrayList<RecordDB> getRecordList() {
		return RecordList;
	}

	// 시험용 출력
	public void testprint() {
        if (RecordList.isEmpty()) {
            // System.out.println("no food input");
            return;
        }

        for (int i = 0; i < RecordList.size(); i++) {
            System.out.println(RecordList.get(i));
        }
    }

	public double getDayAllCalorie() {
        if (RecordList.isEmpty()) {
            // System.out.println("no food input");
            return 0.0;
        }

        double sum = 0.0;

        for (RecordDB k: RecordList) {
            sum += k.getdaycalorie(); 
        }

        return sum;
    }

	public double getDayAllProtein() {
        if (RecordList.isEmpty()) {
            // System.out.println("no food input");
            return 0.0;
        }

        double sum = 0.0;

        for (RecordDB k: RecordList) {
            sum += k.getdayprotein(); 
        }

        return sum;
    }

	public double getDayAllFat() {
        if (RecordList.isEmpty()) {
            // System.out.println("no food input");
            return 0.0;
        }

        double sum = 0.0;

        for (RecordDB k: RecordList) {
            sum += k.getdayfat(); 
        }

        return sum;
    }

	public double getDayAllCarb() {
        if (RecordList.isEmpty()) {
            // System.out.println("no food input");
            return 0.0;
        }

        double sum = 0.0;

        for (RecordDB k: RecordList) {
            sum += k.getdaycarb(); 
        }

        return sum;
    }
}

//create table USERWEIGHT(
//USER_DATE DATE PRIMARY KEY,
//USER_WEIGHT NUMBER(10,5)
//);