# 자바2팀: 식단관리 프로그램

하루 식단 관리를 통해 건강 관리를 돕는 어플입니다.

## 기능

- 하루 먹은 식단 저장 및 수정
- 한 눈에 하루 먹은 칼로리 확인
- 다이어트를 위한 목표 칼로리 계산 및 설정
- 새로운 음식 추가 가능
- 하루 단위로 체중 기록
## DB 테이블 용어

### TABLE RECORD

1. PRIMARY KEY (RECORD_ID)
2. 날짜 (RECORD_DATE)
3. 먹은 음식 (RECORD_FOOD_ID)
4. 먹은 양 (RECORD_FOOD_AMOUNT)
5. 기록의 종류 ("아침"/"점심"/"저녁") (RECORD_TYPE)

### TABLE CAL_USER

1. PRIMARY KEY (USER_ID)
2. 나이 (USER_AGE)
3. 키 (USER_HEIGHT)
4. 몸무게 (USER_WEIGHT)
5. 성별 (USER_GENDER)
6. 현재 목표 칼로리수 (USER_CAL_GOAL)

### TABLE FOOD

1. PRIMARY KEY (food_pk)
2. 음식의 이름 (food_name)
3. 음식의 대분류 (EX. 구이, 국...) (food_category)
4. 단위의 이름 (예: 개, 인분, 횟수...) (food_metricname)
5. 1단위의 그람수 (food_metricgrams)
6. 1단위 당 칼로리 (food_calorie)
7. 1단위 당 탄수화물 (food_carb)
8. 1단위 당 단백질 (food_protein)
9. 1단위 당 지방 (food_fat)
10. 1단위 당 나트륨 (food_sodium)
11. 1단위 당 당류 (food_sugar)


### TABLE USERWEIGHT
1. PRIMARY KEY (USER_DATE)
2. 체중 기록 (USER_WEIGHT)