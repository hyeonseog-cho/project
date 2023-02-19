package com.APP.FoodSearch;
	class Food {
		int food_pk;
		String food_name;
		String food_category;
		String food_metricname;
		int food_metricgrams;
		float food_calorie;
		float food_carb;
		float food_protein;
		float food_fat;
		float food_sodium;
		float food_sugar;
		protected String getFood_carb;
        protected String getFood_protein;
        protected String getFood_fat;
        protected String getFood_sodium;
        protected String getFood_sugar;
	
		Food(int food_pk, String food_name, String food_category, String food_metricname, int food_metricgrams,
				float food_calorie, float food_carb, float food_protein, float food_fat, float food_sodium,
				float food_sugar) {
			this.food_pk = food_pk;
			this.food_name = food_name;
			this.food_category = food_category;
			this.food_metricname = food_metricname;
			this.food_metricgrams = food_metricgrams;
			this.food_calorie = food_calorie;
			this.food_carb = food_carb;
			this.food_protein = food_protein;
			this.food_fat = food_fat;
			this.food_sodium = food_sodium;
			this.food_sugar = food_sugar;
		}
	
		public int getFood_pk() {
			return food_pk;
		}
	
		public void setFood_pk(int food_pk) {
			this.food_pk = food_pk;
		}
	
		public String getFood_name() {
			return food_name;
		}
	
		public void setFood_name(String food_name) {
			this.food_name = food_name;
		}
	
		public String getFood_category() {
			return food_category;
		}
	
		public void setFood_category(String food_category) {
			this.food_category = food_category;
		}
	
		public String getFood_metricname() {
			return food_metricname;
		}
	
		public void setFood_metricname(String food_metricname) {
			this.food_metricname = food_metricname;
		}
	
		public int getFood_metricgrams() {
			return food_metricgrams;
		}
	
		public void setFood_metricgrams(int food_metricgrams) {
			this.food_metricgrams = food_metricgrams;
		}
	
		public float getFood_calorie() {
			return food_calorie;
		}
	
		public void setFood_calorie(float food_calorie) {
			this.food_calorie = food_calorie;
		}
	
		public float getFood_carb() {
			return food_carb;
		}
	
		public void setFood_carb(float food_carb) {
			this.food_carb = food_carb;
		}
	
		public float getFood_protein() {
			return food_protein;
		}
	
		public void setFood_protein(float food_protein) {
			this.food_protein = food_protein;
		}
	
		public float getFood_fat() {
			return food_fat;
		}
	
		public void setFood_fat(float food_fat) {
			this.food_fat = food_fat;
		}
	
		public float getFood_sodium() {
			return food_sodium;
		}
	
		public void setFood_sodium(float food_sodium) {
			this.food_sodium = food_sodium;
		}
	
		public float getFood_sugar() {
			return food_sugar;
		}
	
		public void setFood_sugar(float food_sugar) {
			this.food_sugar = food_sugar;
		}
	
		@Override
		public String toString() {
			return getFood_pk() + "," + getFood_name() + "," + getFood_category() + ", 1" + getFood_metricname() + ","
					+ getFood_metricgrams() + "," + getFood_calorie() + "," + getFood_carb() + "," + getFood_protein() + ","
					+ getFood_fat() + "," + getFood_sodium() + "," + getFood_sugar();
		}
	}   
