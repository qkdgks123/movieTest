package com.my.movie.booking.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.movie.admin.ScreenVO;
import com.my.movie.admin.SeatVO;
import com.my.movie.admin.TheaterVO;
import com.my.movie.admin.TimetableVO;
import com.my.movie.booking.BookingVO;
import com.my.movie.movie.MovieVO;

@Repository("bookingDAO")
public class BookingDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	//��ȭ ��ȸ
	public List<MovieVO> getMovieList(MovieVO vo) {
		return mybatis.selectList("BookingDAO.selectListMovie", vo);
	}
	//����
	public List<TheaterVO> getAreaList(TheaterVO vo) {
		return mybatis.selectList("BookingDAO.selectListArea", vo);
	}
	//����
	public List<TheaterVO> getTheaterList(TheaterVO vo) {
		return mybatis.selectList("BookingDAO.selectListTheater", vo);
	}
	//�ð�
	public List<TimetableVO> getTimetableList(TimetableVO vo) {
		return mybatis.selectList("BookingDAO.selectListTimetable", vo);
	}
	//��������
	public TimetableVO getTimetable(String timetable_code) {
		return mybatis.selectOne("BookingDAO.selectTimetable", timetable_code);
	}
	//�¼�����
	public List<SeatVO> getSeat(String timetable_code) {
		return mybatis.selectList("BookingDAO.selectListSeat", timetable_code);
	}
	//���� ����
	public void insertBooking(BookingVO vo) {
		mybatis.insert("BookingDAO.insertBooking", vo);
		
	}
	//�����¼� ����
	public void updateSeat(String seat_code) {
		mybatis.update("BookingDAO.updateSeat", seat_code);
		
	}
	//�󿵰� ���� ��ȸ
	public ScreenVO getScreen(String screen_code) {
		return mybatis.selectOne("BookingDAO.selectScreen", screen_code);
	}

}
