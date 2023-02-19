package com.APP.Common;

import java.util.ArrayList;
import java.util.Arrays;

import java.awt.Color;

public interface GlobalConstants{
  static int calorieGoal = 1500;

  String name = "Chilly Billy";
  String address = "10 Chicken head Lane";


  String AdminUserIcon = "src/img/corong_profile.png";

  String navBtnHome = "src/img/nav_home.png";
  String navBtnDayCal = "src/img/nav_cal.png";
  String navBtnFood = "src/img/search_icon.png";
  String navBtnCalendar = "src/img/nav_calendar.png";
  String navBtnDrink = "src/img/nav_drink.png";

  String drinkBtnSoju = "src/img/icon_Soju.png";
  String drinkBtnBeer = "src/img/icon_Beer.png";
  String drinkBtnWine = "src/img/icon_Wine.png";
  String drinkBtnMakgl = "src/img/icon_Makgeolli.png";

  final static Color PRIMARY_COLOR = new Color(45, 99, 255);
  final static Color SECONDARY_COLOR = new Color(127, 185, 255); // HEX color #7fb9ff, Color name:
  // final static Color PRIMARY_COLOR = new Color(45,45,48);
  
  public class VitalConstants {
    public static int vitalValue;

    public static int getVitalValue() {
      return vitalValue;
    }

    public static void setVitalValue(int vitalValue) {
      VitalConstants.vitalValue = vitalValue;
    }

  }
  
  public class UserDateInputWithCalendar {
    public static double UserDateInput;

    public static double getUserDateInput() {
      return UserDateInput;
    }

    public static void setUserDateInput(double userDateInput) {
      UserDateInputWithCalendar.UserDateInput = userDateInput;
    }


  }
  
  public class FoodConstants {
    public static String userFoodSearchingInput;

  public static String getUserFoodSearchingInput() {
      return userFoodSearchingInput;
    }
    public static void setUserFoodSearchingInput(String userFoodSearchingInput) {
      FoodConstants.userFoodSearchingInput = userFoodSearchingInput;
    }
  }
  public class UserInputTxt {
    // 2) ArrayList
    public static ArrayList<String> userInputTxt = new ArrayList<>(Arrays.asList("Joonhyeong", "M", "24", "177", "78", "100", "3500"));

    public static ArrayList<String> getUserInputTxt() {
      return userInputTxt;
    }
    
    public static double eatenCalorie;

    public static void setEatenCalorie(double eatenCalorie) {
      UserInputTxt.eatenCalorie = eatenCalorie;
    }

    public static double getEatenCalorie() {
      return eatenCalorie;
    }

  }

  // #################
  // Drink page class
  // #################

  //http://bgnmh.go.kr/checkmehealme/bbs/bbsView.xx?catNo=2&idx=3
  public class UserInputAction {
    // static ArrayList<Integer> cntUnitDrink = new ArrayList<>(Arrays.asList(0,0,0,0));
    static ArrayList<Double> cntMlDrink = new ArrayList<>(Arrays.asList(360.0, 500.0, 750.0, 750.0));

    static double valueBAC;
    public static void setValueBAC(double valueBAC) {
      UserInputAction.valueBAC = valueBAC;
    }

    static String valueUptime = "20h 30m 30s";
    static String valueLife = "2years";
    
    public static void setCntMlDrink(ArrayList<Double> cntMlDrink) {
      UserInputAction.cntMlDrink = cntMlDrink;
    }

    public static ArrayList<Double> getCntMlDrink() {
        return cntMlDrink;
    }

    public static String getValueBAC() {
        return valueBAC + "";
    }

    public static String getValueUptime() {
        return valueUptime;
    }

    public static String getValueLife() {
        return valueLife;
    }
  }

 

  
  // user input value : 술을 마신 양(string), 몸무게(str), 성별(str)
  public class AlcoholConcentration extends UserInputTxt {
    static double DecreaseAlcoholUnit=0.015; //시간당 알코올 감소량 (평균적으로 0.015라고 생각한 연구결과)

    static double hour=0; //시간의 단위(1시간임)
    static double HowManyEatingAlcohol = 0.00;

    static double PerfectBreakTime;

    // WaterMark equation: Calculation formula for measuring alcohol concentration in the body
    // WaterMark equation: alcohol value measurment in body
    // 위드마크 공식(Widmark)은 1931년 스웨덴의 생리학자 에릭 마테오 프로셰 위드마크(Erik Matteo Prochet Widmark)가 만든것.
    //                    음주운전 후 시간이 많이 경과되어 당시의 알코올 농도를 측정할 수 없는 경우 이 공식을 활용해서 운전 당시의 혈중 알콜농도를 계산한다.
    // 체내 알코올 농도 측정 계산 식을 위드마크 공식이라고 부른다.
    
    // C = A÷(10PR) - (βt)

    // C = 최종 음주로부터 t시간 경과했을 때 추산된 혈중 알코올농도
    
    // A÷(10PR) = 음주한 사람의 혈중 알코올농도 중 최고수치(%)
    // A = 음주한 사람이 섭취한 알코올의 질량(g, = 음주량(ml) X (술의 도수(%)÷100) X 알코올의 비중(0.7894g/ml)) × 0.7(체내흡수율)
    // P = 음주한 사람의 체중(kg)
    // R = 음주한 사람의 성별 계수 (남자 = 0.86, 여자 = 0.64) 
    // β = 시간당 혈중알코올농도 감소량 (평균적으로 0.015 %/h) (시간당 알코올 분해량(최저 0.008% ~ 최고 0.03%))
    // t = 경과 시간 (단위: h)
    // 대한민국에서는 마신 알코올이 체내에 모두 흡수되지 않는 것으로 생각하여, 
    // 마신 알코올의 질량에 체내 흡수율 0.7을 곱한 위드마크 공식을 수정하여 사용하고 있다. 

    static ArrayList<Double> alcoholpercentage = new ArrayList<>(Arrays.asList(17.0, 4.5, 14.5, 6.0)); // 소주, 맥주, 와인, 막걸리 순

    // 표준잔.
    //음주량을 측정할 때 ‘표준잔’을 이용하는 방법이 있습니다. 다양한 종류의 술이 있으며 술의 종류에 따라 마시는 잔의 크기가 다릅니다. 그러나 술의 종류별 1표준잔에 들어있는 순수 알코올의 함량은 동일합니다.
    //술의 종류에 따른 1표준잔의 용량은 다르나, 함유된 순수 알코올의 양은 약 10g으로 동일합니다. 평소 자주 마시는 술의 1 표준잔의 용량을 꼭 기억하세요! 

    public static ArrayList<Double> getAlcoholpercentage() {
      return alcoholpercentage;
    }

    public static void setAlcoholpercentage(ArrayList<Double> alcoholpercentage) {
      AlcoholConcentration.alcoholpercentage = alcoholpercentage;
    }

    //소주 : 0.17(알코올 도수) X 360(용량) X 0.8(비중 변환) = 49g(순수 알코올량)/10g = 4.9잔
    //맥주 : 0.045(알코올 도수) X 500(용량) X 0.8(비중 변환) = 18g(순수 알코올량)/10g = 1.8잔
    //와인 : 0.145(알코올 도수) X 750(용량) X 0.8(비중 변환) = 87g(순수 알코올량)/10g = 8.7잔
    //막걸리 : 0.06(알코올 도수) X 750(용량) X 0.8(비중 변환) = 36g(순수 알코올량)/10g = 3.6잔
    static ArrayList<Double>  pureAlcoholContent10g = new ArrayList<>(Arrays.asList(18.0, 49.0, 87.0, 36.0)); // 소주, 맥주, 와인, 막걸리 순 ;
    static ArrayList<Double>  standardGlass = new ArrayList<>(Arrays.asList(1.8, 4.9, 8.7, 3.6)); // 소주, 맥주, 와인, 막걸리 순 ;

    //위드마크 공식 변수
    static double BodyAlcohol; // 체내 알코올 농도(mL)

    public static double getBodyAlcohol() {
        return BodyAlcohol;
    }

    public static void setBodyAlcohol() {
      double AlcoholSpecificGravity = 0.7894; //알코올의 비중(0.7894g/ml))
      double bodyAbsorptionRate = 0.7;
      String gender = userInputTxt.get(1);
      double Weight = Double.parseDouble(userInputTxt.get(4)); //술 마신 사람의 체중 //55;
      for (int i = 0; i < alcoholpercentage.size(); i++ ) {
        HowManyEatingAlcohol += alcoholpercentage.get(i)*0.01* UserInputAction.getCntMlDrink().get(i)*AlcoholSpecificGravity*bodyAbsorptionRate;
      }
      
      //체내 알코올 농도 계산 공식
      if (gender.equals("M")) {
          double SexMale=0.86; //성별에 따라 남녀의 비례계수가 다름.
          BodyAlcohol = HowManyEatingAlcohol/(10*Weight*SexMale) - DecreaseAlcoholUnit*hour;
      } else if (gender.equals("F")) {
          double SexFeMale=0.64; //성별에 따라 남녀의 비례계수가 다름.
          BodyAlcohol = HowManyEatingAlcohol/(10*Weight*SexFeMale) - DecreaseAlcoholUnit*hour;
      }
      //System.out.println("인풋 Weight: " + Weight + ", 인풋 Gender: " + gender);
      //System.out.println("계산된 혈중알코올농도: " + BodyAlcohol);
      // HowManyEatingAlcohol = 0.00; // 다시 초기화
    }
    public static double getDecreaseAlcoholUnitHour() {
      return DecreaseAlcoholUnit;
    }

    public static void setDecreaseAlcoholUnitHour(double decreaseAlcoholUnit) {
      DecreaseAlcoholUnit = decreaseAlcoholUnit;
    }

    public static double getPerfectBreakTime() {
      PerfectBreakTime = BodyAlcohol/DecreaseAlcoholUnit;
      return PerfectBreakTime;
    }

    public static void setPerfectBreakTime(double perfectBreakTime) {
      PerfectBreakTime = perfectBreakTime;
    }
    



  // 주당 5~10잔을 마실 경우 6개월이 단축됐으며 
  // 하루에 1잔씩 마시면 6개월 단축


  //매주 알코올 350g 이상을 섭취하면 수명이 5년 단축된다는 결과를 도출했다.
  //하루에 50g씩 마시면 수명 5년 단축

  // drinking 100 to 200 grams was estimated to shorten
  //the life span of a 40-year-old by six months.
  //하루에 100g 마시면 6개월 정도 단축


  //Another major study in the UK/Europe found that consuming 10 to 15 alcoholic drinks every week may shorten an individual’s life expectancy by between one and two years, while those 
  //who drink more than 18 drinks a week may lose four to five years.
  // 일주일 10잔~15잔 = 하루 1.5잔 1~2년 단축


  //최종정리 : 매일 하루에 1잔정도 마시면 수명이 1년 정도 단축된다..
  //소주잔 용량은 50mL이다. (알)

  // int LifeSpan=80; //우리가 일반적으로 살면 80살까지 산다고 가정
  // double AlcoholDrinking = 500; //마신 알코올량
  // double day = 7; //며칠동안 먹었는지  나타냄

  // double OnedayAlcoholDrinking= AlcoholDrinking/day; 
  // //하루평균 몇인지 나타냄
  
  
  // if(OnedayAlcoholDrinking>=50) {
  //   System.out.println(LifeSpan-1);
  // }
  
  //   else if(OnedayAlcoholDrinking>=100) {
  //     System.out.println(LifeSpan-2);
  //   }

  }
  
}