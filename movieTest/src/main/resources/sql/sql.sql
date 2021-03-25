CREATE TABLE theater (theater_code varchar2(10) NOT NULL, theater_name varchar2(30) NOT NULL, theater_area varchar2(30) NOT NULL, PRIMARY KEY (theater_code));
DROP TABLE theater CASCADE CONSTRAINTS;
SELECT theater_code, theater_name, theater_area FROM theater;
INSERT INTO theater(theater_code, theater_name, theater_area) VALUES (?, ?, ?);
UPDATE theater SET theater_name = ?, theater_area = ? WHERE theater_code = ?;
DELETE FROM theater WHERE theater_code = ?;


CREATE TABLE screen (screen_code varchar2(10) NOT NULL, screen_name varchar2(20) NOT NULL, theater_code varchar2(10) NOT NULL, seats_total number(3) NOT NULL, screen_price number(10) NOT NULL, seat_code varchar2(800) NOT NULL, PRIMARY KEY (screen_code));
DROP TABLE screen CASCADE CONSTRAINTS;
SELECT screen_code, screen_name, theater_code, seats_total, screen_price, seat_code FROM screen;
INSERT INTO screen(screen_code, screen_name, theater_code, seats_total, screen_price, seat_code) VALUES (?, ?, ?, ?, ?, ?);
UPDATE screen SET screen_name = ?, theater_code = ?, seats_total = ?, screen_price = ?, seat_code = ? WHERE screen_code = ?;
DELETE FROM screen WHERE screen_code = ?;

CREATE TABLE movies (m_code varchar2(10) NOT NULL, m_name varchar2(50) NOT NULL, m_director varchar2(50) NOT NULL, m_actor varchar2(100) NOT NULL, m_company varchar2(50) NOT NULL, m_time varchar2(20) NOT NULL, m_playDate varchar2(30) NOT NULL, m_description clob NOT NULL, m_posterImg varchar2(100) NOT NULL, PRIMARY KEY (m_code));
DROP TABLE movies CASCADE CONSTRAINTS;
SELECT m_code, m_name, m_director, m_actor, m_company, m_time, m_playDate, m_description, m_posterImg FROM movies;
INSERT INTO movies(m_code, m_name, m_director, m_actor, m_company, m_time, m_playDate, m_description, m_posterImg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
UPDATE movies SET m_name = ?, m_director = ?, m_actor = ?, m_company = ?, m_time = ?, m_playDate = ?, m_description = ?, m_posterImg = ? WHERE m_code = ?;
DELETE FROM movies WHERE m_code = ?;

CREATE TABLE timetable (timetable_code varchar2(10) NOT NULL, m_code varchar2(10) NOT NULL, theater_code varchar2(10) NOT NULL, screen_code varchar2(10) NOT NULL, screening_date date NOT NULL, start_time date NOT NULL, end_time date NOT NULL, m_name varchar2(50) NOT NULL, m_poster varchar2(50) NOT NULL, theater_name varchar2(30) NOT NULL, theater_area varchar2(20) NOT NULL, screen_name varchar2(20) NOT NULL, r_time number(10), CONSTRAINT movie_image PRIMARY KEY (timetable_code));
DROP TABLE timetable CASCADE CONSTRAINTS;
SELECT timetable_code, m_code, theater_code, screen_code, screening_date, start_time, end_time, m_name, m_poster, theater_name, theater_area, screen_name, r_time FROM timetable;
INSERT INTO timetable(timetable_code, m_code, theater_code, screen_code, screening_date, start_time, end_time, m_name, m_poster, theater_name, theater_area, screen_name, r_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
UPDATE timetable SET m_code = ?, theater_code = ?, screen_code = ?, screening_date = ?, start_time = ?, end_time = ?, m_name = ?, m_poster = ?, theater_name = ?, theater_area = ?, screen_name = ?, r_time = ? WHERE timetable_code = ?;
DELETE FROM timetable WHERE timetable_code = ?;

CREATE TABLE seat (seat_code varchar2(10) NOT NULL, timetable_code varchar2(10) NOT NULL, booking_code varchar2(20) NOT NULL, m_code varchar2(10) NOT NULL, theater_code varchar2(10) NOT NULL, screen_code varchar2(10) NOT NULL, isbooked number(1) DEFAULT 0 NOT NULL, CONSTRAINT seat PRIMARY KEY (seat_code));
DROP TABLE seat CASCADE CONSTRAINTS;
SELECT seat_code, timetable_code, booking_code, m_code, theater_code, screen_code, isbooked FROM seat;
INSERT INTO seat(seat_code, timetable_code, booking_code, m_code, theater_code, screen_code, isbooked) VALUES (?, ?, ?, ?, ?, ?, ?);
UPDATE seat SET timetable_code = ?, booking_code = ?, m_code = ?, theater_code = ?, screen_code = ?, isbooked = ? WHERE seat_code = ?;
DELETE FROM seat WHERE seat_code = ?;

CREATE TABLE booking (booking_code varchar2(10) NOT NULL, customer_ID varchar2(50) NOT NULL, timetable_code varchar2(10) NOT NULL, m_name varchar2(20) NOT NULL, theater_name varchar2(30) NOT NULL, screen_name varchar2(20) NOT NULL, seat_code varchar2(30) NOT NULL, seats_num number(2) NOT NULL, screening_date date NOT NULL, book_date date DEFAULT sysdate NOT NULL, booking_price number(10) DEFAULT 0 NOT NULL, PRIMARY KEY (booking_code));
DROP TABLE booking CASCADE CONSTRAINTS;
SELECT booking_code, customer_ID, timetable_code, m_name, theater_name, screen_name, seat_code, seats_num, screening_date, book_date, booking_price FROM booking;
INSERT INTO booking(booking_code, customer_ID, timetable_code, m_name, theater_name, screen_name, seat_code, seats_num, screening_date, book_date, booking_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
UPDATE booking SET customer_ID = ?, timetable_code = ?, m_name = ?, theater_name = ?, screen_name = ?, seat_code = ?, seats_num = ?, screening_date = ?, book_date = ?, booking_price = ? WHERE booking_code = ?;

CREATE TABLE customer (ID varchar2(50) NOT NULL, password varchar2(50) NOT NULL, name varchar2(50) NOT NULL, phone varchar2(20) NOT NULL, gender varchar2(20) NOT NULL, birth varchar2(30) NOT NULL, mail varchar2(50) NOT NULL, zipcode varchar2(10) NOT NULL, address varchar2(100) NOT NULL, addressdetail varchar2(150) NOT NULL, extraAddress varchar2(100) NOT NULL, regdate date NOT NULL, CONSTRAINT ID PRIMARY KEY (ID));
DROP TABLE customer CASCADE CONSTRAINTS;
SELECT ID, password, name, phone, gender, birth, mail, zipcode, address, addressdetail, extraAddress, regdate FROM customer;
INSERT INTO customer(ID, password, name, phone, gender, birth, mail, zipcode, address, addressdetail, extraAddress, regdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
UPDATE customer SET password = ?, name = ?, phone = ?, gender = ?, birth = ?, mail = ?, zipcode = ?, address = ?, addressdetail = ?, extraAddress = ?, regdate = ? WHERE ID = ?;

CREATE TABLE admin (ID varchar2(50) NOT NULL, password varchar2(50) NOT NULL, name varchar2(50) NOT NULL, PRIMARY KEY (ID));
DROP TABLE admin CASCADE CONSTRAINTS;
SELECT ID, password, name FROM admin;
INSERT INTO admin(ID, password, name) VALUES (?, ?, ?);
UPDATE admin SET password = ?, name = ? WHERE ID = ?;