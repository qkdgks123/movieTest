CREATE TABLE theater (theater_code varchar2(10), theater_name varchar2(30) , theater_area varchar2(30));
CREATE TABLE screen (screen_code varchar2(10) , screen_name varchar2(20) , theater_code varchar2(10) , seats_total number(3) , screen_price number(10) , seat_code varchar2(800));
CREATE TABLE movies (m_code varchar2(10) , m_name varchar2(50) , m_director varchar2(20) , m_actor varchar2(100) , m_company varchar2(50) , m_time varchar2(20) , m_playDate varchar2(30) , m_description clob , m_posterImg varchar2(100));
CREATE TABLE timetable (timetable_code varchar2(10) , m_code varchar2(10) , theater_code varchar2(10) , screen_code varchar2(10) , screening_date date , start_time date , end_time date , m_name varchar2(50) , m_poster varchar2(50) , theater_name varchar2(30) , theater_area varchar2(20) , screen_name varchar2(20) , r_time number(10));
CREATE TABLE seat (seat_code varchar2(10) , timetable_code varchar2(10) , booking_code varchar2(20) , m_code varchar2(10) , theater_code varchar2(10) , screen_code varchar2(10) , isbooked number(1) DEFAULT 0);
CREATE TABLE customer (ID varchar2(50) , password varchar2(50) , name varchar2(50) , phone varchar2(20) , gender varchar2(20) , birth varchar2(30) , mail varchar2(50) , zipcode varchar2(10) , address varchar2(100) , addressdetail varchar2(150) , extraAddress varchar2(100) , regdate date);
CREATE TABLE booking (booking_code varchar2(10) , customer_ID varchar2(50) , timetable_code varchar2(10) , m_name varchar2(20) , theater_name varchar2(30) , screen_name varchar2(20) , seat_code varchar2(30) , seats_num number(2) , screening_date date , book_date date DEFAULT sysdate , booking_price number(10) DEFAULT 0);
CREATE TABLE admin (ID varchar2(50) , password varchar2(50) , name varchar2(50));

SELECT * FROM theater;
SELECT * FROM screen;
SELECT * FROM movies;
SELECT * FROM timetable;
SELECT * FROM seat;
SELECT * FROM customer;
SELECT * FROM booking;
SELECT * FROM admin;

INSERT INTO theater(theater_code, theater_name, theater_area) VALUES (?, ?, ?);
INSERT INTO screen(screen_code, screen_name, theater_code, seats_total, screen_price, seat_code) VALUES (?, ?, ?, ?, ?, ?);
INSERT INTO movies(m_code, m_name, m_director, m_actor, m_company, m_time, m_playDate, m_description, m_posterImg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO timetable(timetable_code, m_code, theater_code, screen_code, screening_date, start_time, end_time, m_name, m_poster, theater_name, theater_area, screen_name, r_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO seat(seat_code, timetable_code, booking_code, m_code, theater_code, screen_code, isbooked) VALUES (?, ?, ?, ?, ?, ?, ?);
INSERT INTO customer(ID, password, name, phone, gender, birth, mail, zipcode, address, addressdetail, extraAddress, regdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO booking(booking_code, customer_ID, timetable_code, m_name, theater_name, screen_name, seat_code, seats_num, screening_date, book_date, booking_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO admin(ID, password, name) VALUES (?, ?, ?);

DROP TABLE theater;
DROP TABLE screen;
DROP TABLE movies;
DROP TABLE timetable;
DROP TABLE seat;
DROP TABLE booking;
DROP TABLE customer;
DROP TABLE admin;


CREATE TABLE theater (theater_code varchar2(10), theater_name varchar2(30) , theater_area varchar2(30));
DROP TABLE theater;
SELECT theater_code, theater_name, theater_area FROM theater;
INSERT INTO theater(theater_code, theater_name, theater_area) VALUES (?, ?, ?);
UPDATE theater SET theater_name = ?, theater_area = ? WHERE theater_code = ?;
DELETE FROM theater WHERE theater_code = ?;


CREATE TABLE screen (screen_code varchar2(10) , screen_name varchar2(20) , theater_code varchar2(10) , seats_total number(3) , screen_price number(10) , seat_code varchar2(800));
DROP TABLE screen;
SELECT screen_code, screen_name, theater_code, seats_total, screen_price, seat_code FROM screen;
INSERT INTO screen(screen_code, screen_name, theater_code, seats_total, screen_price, seat_code) VALUES (?, ?, ?, ?, ?, ?);
UPDATE screen SET screen_name = ?, theater_code = ?, seats_total = ?, screen_price = ?, seat_code = ? WHERE screen_code = ?;
DELETE FROM screen WHERE screen_code = ?;

CREATE TABLE movies (m_code varchar2(10) , m_name varchar2(50) , m_director varchar2(50) , m_actor varchar2(100) , m_company varchar2(50) , m_time varchar2(20) , m_playDate varchar2(30) , m_description clob , m_posterImg varchar2(100));
DROP TABLE movies;
SELECT m_code, m_name, m_director, m_actor, m_company, m_time, m_playDate, m_description, m_posterImg FROM movies;
INSERT INTO movies(m_code, m_name, m_director, m_actor, m_company, m_time, m_playDate, m_description, m_posterImg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
UPDATE movies SET m_name = ?, m_director = ?, m_actor = ?, m_company = ?, m_time = ?, m_playDate = ?, m_description = ?, m_posterImg = ? WHERE m_code = ?;
DELETE FROM movies WHERE m_code = ?;

CREATE TABLE timetable (timetable_code varchar2(10) , m_code varchar2(10) , theater_code varchar2(10) , screen_code varchar2(10) , screening_date date , start_time date , end_time date , m_name varchar2(50) , m_poster varchar2(50) , theater_name varchar2(30) , theater_area varchar2(20) , screen_name varchar2(20) , r_time number(10));
DROP TABLE timetable;
SELECT timetable_code, m_code, theater_code, screen_code, screening_date, start_time, end_time, m_name, m_poster, theater_name, theater_area, screen_name, r_time FROM timetable;
INSERT INTO timetable(timetable_code, m_code, theater_code, screen_code, screening_date, start_time, end_time, m_name, m_poster, theater_name, theater_area, screen_name, r_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
UPDATE timetable SET m_code = ?, theater_code = ?, screen_code = ?, screening_date = ?, start_time = ?, end_time = ?, m_name = ?, m_poster = ?, theater_name = ?, theater_area = ?, screen_name = ?, r_time = ? WHERE timetable_code = ?;
DELETE FROM timetable WHERE timetable_code = ?;

CREATE TABLE seat (seat_code varchar2(10) , timetable_code varchar2(10) , booking_code varchar2(20) , m_code varchar2(10) , theater_code varchar2(10) , screen_code varchar2(10) , isbooked number(1) DEFAULT 0);
DROP TABLE seat;
SELECT seat_code, timetable_code, booking_code, m_code, theater_code, screen_code, isbooked FROM seat;
INSERT INTO seat(seat_code, timetable_code, booking_code, m_code, theater_code, screen_code, isbooked) VALUES (?, ?, ?, ?, ?, ?, ?);
UPDATE seat SET timetable_code = ?, booking_code = ?, m_code = ?, theater_code = ?, screen_code = ?, isbooked = ? WHERE seat_code = ?;
DELETE FROM seat WHERE seat_code = ?;

CREATE TABLE booking (booking_code varchar2(10) , customer_ID varchar2(50) , timetable_code varchar2(10) , m_name varchar2(20) , theater_name varchar2(30) , screen_name varchar2(20) , seat_code varchar2(30) , seats_num number(2) , screening_date date , book_date date DEFAULT sysdate , booking_price number(10) DEFAULT 0);
DROP TABLE booking;
SELECT booking_code, customer_ID, timetable_code, m_name, theater_name, screen_name, seat_code, seats_num, screening_date, book_date, booking_price FROM booking;
INSERT INTO booking(booking_code, customer_ID, timetable_code, m_name, theater_name, screen_name, seat_code, seats_num, screening_date, book_date, booking_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
UPDATE booking SET customer_ID = ?, timetable_code = ?, m_name = ?, theater_name = ?, screen_name = ?, seat_code = ?, seats_num = ?, screening_date = ?, book_date = ?, booking_price = ? WHERE booking_code = ?;

CREATE TABLE customer (ID varchar2(50) , password varchar2(50) , name varchar2(50) , phone varchar2(20) , gender varchar2(20) , birth varchar2(30) , mail varchar2(50) , zipcode varchar2(10) , address varchar2(100) , addressdetail varchar2(150) , extraAddress varchar2(100) , regdate date);
DROP TABLE customer;
SELECT ID, password, name, phone, gender, birth, mail, zipcode, address, addressdetail, extraAddress, regdate FROM customer;
INSERT INTO customer(ID, password, name, phone, gender, birth, mail, zipcode, address, addressdetail, extraAddress, regdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
UPDATE customer SET password = ?, name = ?, phone = ?, gender = ?, birth = ?, mail = ?, zipcode = ?, address = ?, addressdetail = ?, extraAddress = ?, regdate = ? WHERE ID = ?;

CREATE TABLE admin (ID varchar2(50) , password varchar2(50) , name varchar2(50));
DROP TABLE admin;
SELECT ID, password, name FROM admin;
INSERT INTO admin(ID, password, name) VALUES (?, ?, ?);
UPDATE admin SET password = ?, name = ? WHERE ID = ?;

create table review1(
	ID varchar2(50) NOT NULL,
	m_code varchar2(20) not null,
	review_num number(10) not null,
	content clob not null,
	star number(10) not null,
	regist_day varchar2(20) not null
);

select * from review1;
drop table review1;

select avg(star) from review1;
select count(*) from review1;
select round(AVG(star), 2) from review1 where m_code = 'M777530';


create table review1(
	num int not null auto_increment,
	movieCode varchar2(20) not null,
	reviewNum int not null,
	content clob not null,
	regist_day varchar(30),
	star int not null,
	goodCnt int,
	badCnt int,
	primary key(num)
)default charset=utf8;