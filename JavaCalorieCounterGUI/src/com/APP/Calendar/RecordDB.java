package com.APP.Calendar;


public class RecordDB {

    private String RECORD_DATE;
	double FOOD_CALORIE;
    double FOOD_PROTEIN;
	double food_carb;
	int record_food_amount;
	double food_fat;

//select to_char(r.RECORD_DATE, 'yyyy/mm/dd'), sum(f.FOOD_CALORIE), sum(f.FOOD_CARB), sum(f.FOOD_PROTEIN)
    public RecordDB(String RECORD_DATE, double FOOD_CALORIE, double FOOD_PROTEIN, double food_carb, int record_food_amount, double food_fat) {
        this.RECORD_DATE = RECORD_DATE;
        this.FOOD_CALORIE = FOOD_CALORIE;
        this.FOOD_PROTEIN = FOOD_PROTEIN;
		this.food_carb = food_carb;
		this.record_food_amount=record_food_amount;
		this.food_fat = food_fat;
	}

	public String getRECORD_DATE() {
		return this.RECORD_DATE;
	}

	public void setRECORD_DATE(String RECORD_DATE) {
		this.RECORD_DATE = RECORD_DATE;
	}

	public double getFOOD_CALORIE() {
		return this.FOOD_CALORIE;
	}

	public void setFOOD_CALORIE(Double FOOD_CALORIE) {
		this.FOOD_CALORIE = FOOD_CALORIE;
	}

	public Double getFOOD_PROTEIN() {
		return this.FOOD_PROTEIN;
	}

	public void setFOOD_PROTEIN(Double FOOD_PROTEIN) {
		this.FOOD_PROTEIN = FOOD_PROTEIN;
	}

	public double getfood_carb(){
		return food_carb;
	}

	public void setfood_carb(double food_carb){
		this.food_carb=food_carb;
	}

	public double getrecord_food_amount(){
		return record_food_amount;
	}

	public void setrecord_food_amount(int record_food_amount){
		this.record_food_amount=record_food_amount;
	}

	public Double getdaycalorie(){
		double cut = Math.round((this.FOOD_CALORIE * this.record_food_amount) * 100) / 100;
		
		return cut;
	}

	public Double getdayprotein(){
		double cut = Math.round((this.FOOD_PROTEIN * this.record_food_amount) * 100) / 100;
		
		return cut;
	}

	public double getdaycarb(){
		double cut = Math.round((this.food_carb * this.record_food_amount) * 100) / 100;

		return cut;
	}

	public double getdayfat() {
		return this.food_fat;
	}
	
	public String toString(){
		return 
			" Date(Today) " + getRECORD_DATE() +
			" 칼로리 " + getFOOD_CALORIE() + "kcal" +
			" 탄수화물 " + getfood_carb() + "g" +
			" 단백질 " + getFOOD_PROTEIN() + "g" ;
	}
}