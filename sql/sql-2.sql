CREATE TABLE `contact` (
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 
/*
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
--
더미데이터 30건만 만들어줘, SQL insert문으로
*/
-- batch insert
truncate table contact;

select * from contact;

INSERT INTO contact (email, name, phone) 
VALUES
  ('email1@example.com', 'Name1', '010-1111-1111'),
  ('email2@example.com', 'Name2', '010-2222-2222'),
  ('email3@example.com', 'Name3', '010-3333-3333'),
  ('email4@example.com', 'Name4', '010-4444-4444'),
  ('email5@example.com', 'Name5', '010-5555-5555'),
  ('email6@example.com', 'Name6', '010-6666-6666'),
  ('email7@example.com', 'Name7', '010-7777-7777'),
  ('email8@example.com', 'Name8', '010-8888-8888'),
  ('email9@example.com', 'Name9', '010-9999-9999'),
  ('email10@example.com', 'Name10', '010-1010-1010'),
  ('email11@example.com', 'Name11', '010-1111-1111'),
  ('email12@example.com', 'Name12', '010-1212-1212'),
  ('email13@example.com', 'Name13', '010-1313-1313'),
  ('email14@example.com', 'Name14', '010-1414-1414'),
  ('email15@example.com', 'Name15', '010-1515-1515'),
  ('email16@example.com', 'Name16', '010-1616-1616'),
  ('email17@example.com', 'Name17', '010-1717-1717'),
  ('email18@example.com', 'Name18', '010-1818-1818'),
  ('email19@example.com', 'Name19', '010-1919-1919'),
  ('email20@example.com', 'Name20', '010-2020-2020'),
  ('email21@example.com', 'Name21', '010-2121-2121'),
  ('email22@example.com', 'Name22', '010-2222-2222'),
  ('email23@example.com', 'Name23', '010-2323-2323'),
  ('email24@example.com', 'Name24', '010-2424-2424'),
  ('email25@example.com', 'Name25', '010-2525-2525'),
  ('email26@example.com', 'Name26', '010-2626-2626'),
  ('email27@example.com', 'Name27', '010-2727-2727'),
  ('email28@example.com', 'Name28', '010-2828-2828'),
  ('email29@example.com', 'Name29', '010-2929-2929'),
  ('email30@example.com', 'Name30', '010-3030-3030');

-- clustered index 기준 역정렬
SELECT * FROM contact ORDER BY email DESC;

-- 데이터 조회건수를 제한
-- LIMIT 건수
select * from contact 
order by email desc
limit 20;
-- OFFSET 건수만큼 건너띄기(skip)
select * from contact 
order by email desc
limit 10 offset 20;
-- 총데이터 건수 조회
-- count(인덱스), *로하면 clustered index의 값 개수만큼을 조회함
select count(*) from contact;

-- 조건 검색(equal), =
select * from contact where name = 'Name9';
select count(*) from contact where name = 'Name9';

-- 조건 검색(contain), LIKE
-- 전체 데이터를 다 뒤져야함.
select * from contact where name like '%이%';
-- 조건과 일치하는 5개만 조회한 후 종료
select * from contact 
where name like '%Name1%' limit 5;
-- 특정키워드로 시작하는 검색
-- '이'로 시작하는 이름은 몇명일까?
select * from contact where name like 'Name1%';
select * from contact where name like '이%';

-- key range 탐색, or 검색