package com.APP.DayCalories;

/**
 * 하루 먹은 음식의 정보를 저장하는 내용의 클래스입니다.
 */
public class Food {
    private int foodid;
    private String food_name;
    private String food_metricname;
    private String food_category;
    private String record_type;
    private double record_food_amount;
    private double food_metricgrams;
    private double food_calorie;
    private double food_carb;
    private double food_protein;
    private double food_fat;
    private double food_sodium;
    private double food_sugar;

    public Food(int foodid, String food_name, String food_metricname, String food_category, String record_type, double record_food_amount, double food_metricgrams, double food_calorie) {
        this.foodid = foodid;
        this.food_name = food_name;
        this.food_metricname = food_metricname;
        this.food_category = food_category;
        this.record_type = record_type;
        this.record_food_amount = record_food_amount;
        this.food_metricgrams = food_metricgrams;
        this.food_calorie = food_calorie;
    }

    public Food(int foodid, double record_food_amount, String record_type) {
        this.foodid = foodid;
        this.record_food_amount = record_food_amount;
        this.record_type = record_type;
    }

    public int getFoodid() {
        return this.foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getFood_name() {
        return this.food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_metricname() {
        return this.food_metricname;
    }

    public void setFood_metricname(String food_metricname) {
        this.food_metricname = food_metricname;
    }

    public String getFood_category() {
        return this.food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getRecord_type() {
        return this.record_type;
    }

    public void setRecord_type(String record_type) {
        this.record_type = record_type;
    }

    public double getRecord_food_amount() {
        return this.record_food_amount;
    }

    public void setRecord_food_amount(double record_food_amount) {
        this.record_food_amount = record_food_amount;
    }

    public double getFood_metricgrams() {
        return this.food_metricgrams;
    }

    public void setFood_metricgrams(double food_metricgrams) {
        this.food_metricgrams = food_metricgrams;
    }

    public double getFood_calorie() {
        return this.food_calorie;
    }

    public void setFood_calorie(double food_calorie) {
        this.food_calorie = food_calorie;
    }

    public double getallCalorie() {
        return this.food_calorie * this.record_food_amount;
    }

    public double getFood_carb() {
        return this.food_carb;
    }

    public void setFood_carb(double food_carb) {
        this.food_carb = food_carb;
    }

    public double getFood_protein() {
        return this.food_protein;
    }

    public void setFood_protein(double food_protein) {
        this.food_protein = food_protein;
    }

    public double getFood_fat() {
        return this.food_fat;
    }

    public void setFood_fat(double food_fat) {
        this.food_fat = food_fat;
    }

    public double getFood_sodium() {
        return this.food_sodium;
    }

    public void setFood_sodium(double food_sodium) {
        this.food_sodium = food_sodium;
    }

    public double getFood_sugar() {
        return this.food_sugar;
    }

    public void setFood_sugar(double food_sugar) {
        this.food_sugar = food_sugar;
    }


    @Override
    public String toString() {
        return "{" +
            " foodid='" + getFoodid() + "'" +
            ", food_name='" + getFood_name() + "'" +
            ", food_metricname='" + getFood_metricname() + "'" +
            ", food_category='" + getFood_category() + "'" +
            ", record_type='" + getRecord_type() + "'" +
            ", record_food_amount='" + getRecord_food_amount() + "'" +
            ", food_metricgrams='" + getFood_metricgrams() + "'" +
            ", food_calorie='" + getFood_calorie() + "'" +
            ", food_carb='" + getFood_carb() + "'" +
            ", food_protein='" + getFood_protein() + "'" +
            ", food_fat='" + getFood_fat() + "'" +
            ", food_sodium='" + getFood_sodium() + "'" +
            ", food_sugar='" + getFood_sugar() + "'" +
            "}";
    }
}