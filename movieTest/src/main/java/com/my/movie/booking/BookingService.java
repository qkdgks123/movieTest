package com.my.movie.booking;

import java.util.List;

import com.my.movie.admin.ScreenVO;
import com.my.movie.admin.SeatVO;
import com.my.movie.admin.TheaterVO;
import com.my.movie.admin.TimetableVO;
import com.my.movie.movie.MovieVO;

public interface BookingService {
	//
	//��ȭ ��� ��ȸ
	List<MovieVO> getMovieList(MovieVO vo);
	//���� ��� ��ȸ
	List<TheaterVO> getAreaList(TheaterVO vo);
	//���� ��� ��ȸ
	List<TheaterVO> getTheaterList(TheaterVO vo);
	//�ð� ��� ��ȸ
	List<TimetableVO> getTimetableList(TimetableVO vo);
	//�������� ��ȸ
	TimetableVO getTimetable(String timetable_code);
	//�¼����� ��ȸ
	List<SeatVO> getSeat(String timetable_code);
	//�������� ����
	void insertBooking(BookingVO vo);
	//�����¼� ǥ��
	void updateSeat(String seat_code);
	//�󿵰� ��ȸ
	ScreenVO getScreen(String screen_code);

	
	

}
