-- food table

create table food(
food_pk number
constraint food_pk primary key,
food_name varchar2(40) not null unique,
food_category varchar2(30),
food_metricname varchar2(20),
food_metricgrams number(5),
food_calorie number(7,2),
food_carb number(5,2),
food_protein number(5,2),
food_fat number(5,2),
food_sodium number(6,2),
food_sugar number(5,2));

drop table food;
select * from food;

select distinct food_category from food;
select distinct food_metricname from food;

insert into food values(1, '공기밥', '밥류', '공기', 73.78, 310, 67.32, 5.61, 0.85, 0, 0);
insert into food values(1, '밥', '밥류', '공기', 71.86, 300, 65.15, 5.71, 1, 5, 0);
select * from food where food_pk>290;
delete from food where food_pk>291;

-- 시퀀스 생성
create sequence record_sequence;

-- 시퀀스 생성됨 확인
select * from user_sequences;

-- 새로운 정보 추가할 때마다 발동하는 트리거
create or replace trigger food_pk_insert
	before insert on food
	for each row
begin
	select record_sequence.nextval+300
	into :new.food_pk
	from dual;
end;
/

-- 트리거 삭제 sql문
-- drop trigger food_pk_insert;