package com.APP.DayCalories;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.APP.ConnectionManager;

public class FoodManager {

    private ArrayList<Food> foodList;
    ConnectionManager connectionManager;
    Connection conn;
    String managerDate;

    /**
     * 날짜를 받아 그 날에 먹은 음식정보를 DB에서 가져오고 Food 오브젝트로 만들어서 리스트 안에 저장하는 역할을 하는 클래스입니다.
     * 
     * @param date DB에서 음식 정보를 가져올 날짜
     */
    public FoodManager(String date) {
        try {

            foodList = new ArrayList<>();

            // Connection 생성
            connectionManager = new ConnectionManager();
            conn = connectionManager.get();
            
            // SQL 처리를 위한 PreparedStatement와 ResultSet을 준비합니다.
			PreparedStatement statement = null;
			ResultSet result = null;
            
            managerDate = "'" + date + "'";

            // SQL 쿼리문을 준비
            String queryString = "SELECT record_food_id, record_food_amount, record_type FROM record WHERE TO_CHAR(record_date, 'YYYY-MM-DD') = " + managerDate;
			statement = conn.prepareStatement(queryString);

            // 쿼리 결과를 받아옴
            result = statement.executeQuery();

            // 결과값 저장공간 생성
            String food_name;
            String food_metricname;
            String food_category;
            double food_metricgrams;
            double food_calorie;
            double food_carb;
            double food_protein;
            double food_fat;
            double food_sodium;
            double food_sugar;

            // 레코드 저장
            String record_type;
            double record_food_amount;

            int foodid;

            // 쿼리문과 statement, result 준비
            String queryStringForFoodInfo;
            PreparedStatement statementForFoodInfo;
            ResultSet resultForFoodInfo;

            // 결과 불러오기
            while (result.next()) {
                //System.out.println("음식 정보 가져오는중..");
                foodid = result.getInt("record_food_id");
                record_food_amount = result.getDouble("record_food_amount");
                record_type = result.getString("record_type");

                Food food = new Food(foodid, record_food_amount, record_type);
                foodList.add(food);
            }

            queryStringForFoodInfo = "SELECT food_name, food_category, food_metricname, food_metricgrams, food_calorie, food_carb, food_protein, food_fat, food_sodium, food_sugar FROM food WHERE food_pk = ?";
            statementForFoodInfo = conn.prepareStatement(queryStringForFoodInfo);

            // 음식 세부 정보 불러오기
            for (Food k: foodList) {
                statementForFoodInfo.setInt(1, k.getFoodid());
                resultForFoodInfo = statementForFoodInfo.executeQuery();

                while (resultForFoodInfo.next()) {
                    food_name = resultForFoodInfo.getString("food_name");
                    food_category = resultForFoodInfo.getString("food_category");
                    food_metricname = resultForFoodInfo.getString("food_metricname");
                    food_metricgrams = resultForFoodInfo.getDouble("food_metricgrams");
                    food_calorie = resultForFoodInfo.getDouble("food_calorie");
                    food_carb = resultForFoodInfo.getDouble("food_carb");
                    food_protein = resultForFoodInfo.getDouble("food_protein");
                    food_fat = resultForFoodInfo.getDouble("food_fat");
                    food_sodium = resultForFoodInfo.getDouble("food_sodium");
                    food_sugar = resultForFoodInfo.getDouble("food_sugar");
    
                    k.setFood_name(food_name);
                    k.setFood_category(food_category);
                    k.setFood_metricname(food_metricname);
                    k.setFood_metricgrams(food_metricgrams);
                    k.setFood_calorie(food_calorie);
                    k.setFood_carb(food_carb);
                    k.setFood_protein(food_protein);
                    k.setFood_fat(food_fat);
                    k.setFood_sodium(food_sodium);
                    k.setFood_sugar(food_sugar);
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * 클래스안에 저장하고 있는 Food의 ArrayList를 돌려보냅니다.
     * @return 음식을 저장하고 있는 배열리스트
     */
    public ArrayList<Food> getList() {
        return foodList;
    }

    /**
     * 그 날 먹은 칼로리 총합을 보내는 메소드입니다.
     * @return FoodManager(date)로 만들어진 FoodManager라면, date ('YYYY-MM-DD') 에 먹은 칼로리 총합을 return합니다.
     */
    public double getDayAllCalorie() {
        if (foodList.isEmpty()) {
            System.out.println("The food list is empty.");
            return 0.0;
        }

        double sum = 0.0;

        for (Food k: foodList) {
            sum += k.getallCalorie();    
        }

        return sum;
    }
    
    public void deleteFood(Food food, String recordType) {
        System.out.println("DATE: " + managerDate);
        
        // SQL 쿼리 준비
        PreparedStatement statement = null;
        recordType = "'" + recordType + "'";
        String queryString = "DELETE FROM record WHERE TO_CHAR(record_date, 'YYYY-MM-DD') = " + managerDate + " AND record_food_id = " + food.getFoodid() + " AND record_type = " + recordType;
        try {
            statement = conn.prepareStatement(queryString);
            
            // DELETE 요청
            if (statement.executeUpdate() != 0) {
                System.out.println("성공적으로 " + food.getFoodid() + " " + food.getFood_name() + "를 삭제했다.");
            } else {
                System.out.println("무언가 오류가 있어 제대로 삭제되지 않았다.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        

    }


    /**
     * 테스트용으로 리스트안에 있는 내용을 출력합니다.
     */
    public void print() {
        if (foodList.isEmpty()) {
            System.out.println("The food list is empty.");
            return;
        }

        for (int i = 0; i < foodList.size(); i++) {
            System.out.println(foodList.get(i));
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (Exception e) {
            
        }
    }
}
