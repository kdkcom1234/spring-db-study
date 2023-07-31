-- 해당 스키마(데이터베이스)를 사용
-- 데이터베이스
-- : 데이터베이스 객체를 사용할 수 있는 공간
-- : 객체 - 테이블, 뷰, 인덱스, 저장프로시저 ...
/*
데이터베이스 엔진: MySQL
데이터베이스(스키마): myapp
CREATE SCHEMA myapp;
CREATE DATABASE myapp;
MySQL에서는 스키마와 데이터베이스가 동일한 개념이다.
*/
use myapp;
-- 테이블: 데이터를 저장할 수 있는 기본적인 공간
/* create table 테이블 (
    컬럼명 데이터타입 제약조건,
    ...,
    추가적인 제약조건(constraint)
)
*/
/* 
	DDL(data definion language)
    create ...
    SQL(Structured Query Language): 시퀄
*/
create table contact (
    -- varchar: variable charactor
    -- 가변문자열, 255byte까지 넣을 수 있음.
	email varchar(255) not null, 
    image varchar(255), 
    name varchar(255) not null, 
    phone varchar(255) not null,
    primary key (email)
) engine=InnoDB;

select length('홍'); -- 3byte
select length('a'); -- 1byte

-- insert into 테이블 value(값목록 ...);
-- 값 목록은 순서를 잘 맞춰야함.
insert into contact
value("hong@gmail", null, "홍길동", "010-1234-5678");
-- 1 row(s) affectd(1 행이 영향을 받았다.)
-- 데이터 1건을 row(행) 또는 record(레코드)
-- 데이터 데한 속성을 column(열) 또는 field(필드)
-- 값 순서를 설정
-- insert into 테이블 (컬럼명목로....) values (값목록...)
-- insert into contact (name, phone, email, image)
-- value("john doe", "010-0987-6543", "john@naver.com", null);

insert into contact (name, phone, email, image)
value("john doe", "010-0987-6543", "john@gmail.com", null);
/* 기존에 있는 email로 insert
Error Code: 1062. Duplicate entry 'john@gmail.com' 
for key 'contact.PRIMARY'	0.000 sec
*/
/* 
Priamry Key 제약조건: 
1. 테이블내의 데이터 중에서 같은 값이 중복이 있으며 안 됨
2. null 값이 될 수 없음
*/

-- 목록 조회
select * from contact;

-- 특정 컬럼으로 정렬하여 조회
-- asc(기본값): 순정렬, desc: 역정렬
select * from contact order by name;

-- 데이터베이스의 PK(index, clustered)
-- clustered index에 맞게 데이터가 정렬되어있음.
-- index(binaray tree)구조이고, 데이터(linked list) 구조이다.
-- index(목차) 트리를 탐색하여 데이터까지 찾아감

-- PK 값으로 1건만 찾아옴
select * from contact where email = 'hong@gmail';