package com.my.movie.booking;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BookingVO {//����
	private String booking_code;//���� �ڵ�
	private String customer_ID;//������ ID
	private String timetable_code;//�󿵽ð�ǥ �ڵ�
	private String m_name;//��ȭ ����
	private String theater_name;//���� �̸�
	private String screen_name;//�󿵰� �̸�
	private String seat_code;//�¼� �ڵ�
	private int seats_num;//���� �¼� ��
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date screening_date;//�� ��¥
	private Date book_date;//������ ��¥
	private int booking_price;//�� ���� �ݾ�
	
	public String getBooking_code() {
		return booking_code;
	}
	public void setBooking_code(String booking_code) {
		this.booking_code = booking_code;
	}
	public String getCustomer_ID() {
		return customer_ID;
	}
	public void setCustomer_ID(String customer_ID) {
		this.customer_ID = customer_ID;
	}
	public String getTimetable_code() {
		return timetable_code;
	}
	public void setTimetable_code(String timetable_code) {
		this.timetable_code = timetable_code;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getSeat_code() {
		return seat_code;
	}
	public void setSeat_code(String seat_code) {
		this.seat_code = seat_code;
	}
	public int getSeats_num() {
		return seats_num;
	}
	public void setSeats_num(int seats_num) {
		this.seats_num = seats_num;
	}
	public Date getScreening_date() {
		return screening_date;
	}
	public void setScreening_date(Date screening_date) {
		this.screening_date = screening_date;
	}
	public Date getBook_date() {
		return book_date;
	}
	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}
	public int getBooking_price() {
		return booking_price;
	}
	public void setBooking_price(int booking_price) {
		this.booking_price = booking_price;
	}
	@Override
	public String toString() {
		return "BookingVO [booking_code=" + booking_code + ", customer_ID=" + customer_ID + ", timetable_code="
				+ timetable_code + ", m_name=" + m_name + ", theater_name=" + theater_name + ", screen_name="
				+ screen_name + ", seat_code=" + seat_code + ", seats_num=" + seats_num + ", screening_date="
				+ screening_date + ", book_date=" + book_date + ", booking_price=" + booking_price + "]";
	}

}
