-- create table login (
-- 	id bigint not null auto_increment, 
--     password varchar(500), 
--     username varchar(255), 
--     profile_id bigint, 
--     primary key (id)
-- ) engine=InnoDB;

-- create table profile (
-- 	id bigint not null auto_increment, 
--     email varchar(255), 
--     nickname varchar(255), 
--     primary key (id)
-- ) engine=InnoDB;

-- 제약조건(constraint) 생성
-- alter table 테이블명: 테이블의 구조를 변경
-- add constraint 제약조건명: 제약조건 생성
/* foreign key
	: 외래키 제약조건
	: 다른 테이블을 참조할 수 있는 값을 저장하는 컬럼
	: 해당 컬럼의 값은 다른 테이블의 PK만 가능하다.
    : 예) profile ( 1, 2, ...10)
	:    login(profile_id=11) 제약조건 오류가 발생
*/
-- alter table login 
-- add constraint FKe0ght7n5cjx8tnheha9mpn8l3 
-- foreign key (profile_id) references profile (id);

truncate table profile;
INSERT INTO profile (email, nickname, login_id)
VALUES
    ('email1@example.com', 'nickname1', 1),
    ('email2@example.com', 'nickname2', 2),
    ('email3@example.com', 'nickname3', 3),
    ('email4@example.com', 'nickname4', 4),
    ('email5@example.com', 'nickname5', 5),
    ('email6@example.com', 'nickname6', 6),
    ('email7@example.com', 'nickname7', 7),
    ('email8@example.com', 'nickname8', 8),
    ('email9@example.com', 'nickname9', 9),
    ('email10@example.com', 'nickname10', 10);
    
select * from profile;

truncate table login;
INSERT INTO login (password, username, profile_id)
VALUES
    ('$2y$12$1nqam6QOz/uP/cI6CXLGc.CA1WB/cwhhAbCzETV.qGY29dLsL8tT2', 'username1', 1),
    ('$2y$12$Kkg6RTIaa4OXxZWoySeop.Wrq4TC4/ErI89d.kW7cwi.Kj03li.Lu', 'username2', 2),
    ('$2y$12$sl4LJfM1A/ZusFLN3CcbhOYOCaQOT2pVr0AJSH5Q.zU3.zqZQDGGK', 'username3', 3),
    ('$2y$12$pXMR2rS5nT0h2zQdw9TULeHT.vhIlxHtZ2MlJypDbr3wXbHaxeeLe', 'username4', 4),
    ('$2y$12$C2idhVcTRZfLL24ZcXjHBOx42ZqOeybm2vbnh8kFXDL/hM2QCbGg2', 'username5', 5),
    ('$2y$12$KSqkmo32exRd9uhvJDLjzODq4V6o4cAemalGpS0k./Y0X8eqZabx.', 'username6', 6),
    ('$2y$12$LpJESVNCy45V/B1lQ8VvhO6laUwQgZIDV9KrB0tFg9Th.aYyCzbx6', 'username7', 7),
    ('$2y$12$uo3KYbT.DW/Vfz9yBziQjO/iQO0sP9D7q/zkRQO2mZUOZp4CQAt/2', 'username8', 8),
    ('$2y$12$Z6BB7bVm9CbYnFRPv.FD.u8voUkL6QtEniy9iaTXc7Gizob1ojw9K', 'username9', 9),
    ('$2y$12$3PDBYSsPd7hE9spMxT5nNuNpQKJAzNrCKI6li3J82dmwAfuOrvdwe', 'username10', 10);
    
select * from login;
select * from profile;

-- ----ㅌ (1:M), one-to-many
-- -----  (1:1), one-to-one

-- join(연결)
-- [1,2,3,4].join(";")
-- 1;2;3;4
-- 특정 조건을 기준으로 연결함(where 연결가능,, ANSI SQL 표준 아님)
-- select * 
-- from login l, profile p 
-- where l.profile_id = p.id;

-- join 문법
-- from 주테이블 별칭 join 관련테이블 별칭 on 조건식
-- 대표적인 조건식이 주테이블.FK = 관련테이블.PK

select * 
from login l 
		join profile p 
			on l.profile_id = p.id;


