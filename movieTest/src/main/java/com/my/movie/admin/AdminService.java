package com.my.movie.admin;

import java.util.List;

import com.my.movie.movie.Criteria;
import com.my.movie.movie.MovieVO;
import com.my.movie.user.CustomerVO;

public interface AdminService {
	public String loginCheck(AdminVO vo);

	public List<TheaterVO> getTheaterList();

	public int getTheaterTotal();

	public void deleteTheater(String theater_code);

	public TheaterVO getTheater(TheaterVO vo);

	public void insertTheater(TheaterVO vo);
	
	public void insertScreen(TheaterVO vo);

	public void insertScreenB(TheaterVO vo);

	public List<TimetableVO> getTimetableList(TimetableVO vo);

	public int getTimetableTotal();

	public void deleteTimetable(String timetable_code);

	public List<ScreenVO> getScreenList(ScreenVO vo);

	public MovieVO getMovie(MovieVO vo1);

	public void insertTimetable(TimetableVO vo);

	public ScreenVO getScreen(ScreenVO vo2);

	public void insertSeat(SeatVO vo3);

	public List<TimetableVO> checkTimetableList1(TimetableVO vo);

	public List<TimetableVO> checkTimetableList2(TimetableVO vo);
	
	//회원 관리
	//회원 목록 출력, 페이징처리관련 작업
	public List<CustomerVO> getCustomerList(Criteria cri);
	//페이징처리관련 작업
	public int getTotal(Criteria cri);
	
	//총 회원수 출력
	public int getCustomerTotal();
	
	//회원 삭제
	public void deleteCustomer(String id);

	
}
