package com.my.movie.booking.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.movie.admin.ScreenVO;
import com.my.movie.admin.SeatVO;
import com.my.movie.admin.TheaterVO;
import com.my.movie.admin.TimetableVO;
import com.my.movie.booking.BookingService;
import com.my.movie.booking.BookingVO;
import com.my.movie.movie.MovieVO;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingDAO bookingDAO;
	
	//��ȭ ����
	@Override
	public List<MovieVO> getMovieList(MovieVO vo) {
		return bookingDAO.getMovieList(vo);
	}
	//����
	@Override
	public List<TheaterVO> getAreaList(TheaterVO vo) {
		return bookingDAO.getAreaList(vo);
	}
	//����
	@Override
	public List<TheaterVO> getTheaterList(TheaterVO vo) {
		return bookingDAO.getTheaterList(vo);
	}
	//�ð�
	@Override
	public List<TimetableVO> getTimetableList(TimetableVO vo) {
		return bookingDAO.getTimetableList(vo);
	}
	//��������
	@Override
	public TimetableVO getTimetable(String timetable_code) {
		return bookingDAO.getTimetable(timetable_code);
	}
	//�¼�����
	@Override
	public List<SeatVO> getSeat(String timetable_code) {
		return bookingDAO.getSeat(timetable_code);
	}
	//�������� ����
	@Override
	public void insertBooking(BookingVO vo) {
		bookingDAO.insertBooking(vo);
	}
	//�����¼� ǥ��
	@Override
	public void updateSeat(String seat_code) {
		bookingDAO.updateSeat(seat_code);
	}
	//�󿵰� ���� ��ȸ
	@Override
	public ScreenVO getScreen(String screen_code) {
		return bookingDAO.getScreen(screen_code);
	}


	

}
