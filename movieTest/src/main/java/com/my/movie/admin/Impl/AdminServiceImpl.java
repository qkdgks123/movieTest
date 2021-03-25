package com.my.movie.admin.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.movie.admin.AdminService;
import com.my.movie.admin.AdminVO;
import com.my.movie.admin.ScreenVO;
import com.my.movie.admin.SeatVO;
import com.my.movie.admin.TheaterVO;
import com.my.movie.admin.TimetableVO;
import com.my.movie.movie.Criteria;
import com.my.movie.movie.MovieVO;
import com.my.movie.user.CustomerVO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO adminDAO;
	
	//관리자 로그인 체크
	@Override
	public String loginCheck(AdminVO vo){
		return adminDAO.loginCheck(vo);
	}
	//영화관 목록 출력
	@Override
	public List<TheaterVO> getTheaterList() {
		return adminDAO.getMovieList();	
	}
	//영화관 갯수
	@Override
	public int getTheaterTotal() {
		return adminDAO.getMovieTotal();
	}
	//영화관 삭제
	@Override
	public void deleteTheater(String theater_code) {
		adminDAO.deleteTheater(theater_code);	
	}
	//영화관 중복 확인
	@Override
	public TheaterVO getTheater(TheaterVO vo) {
		return adminDAO.getTheater(vo);
	}
	//영화관 등록
	@Override
	public void insertTheater(TheaterVO vo) {
		adminDAO.insertTheater(vo);
		
	}
	//상영관 등록(A관)
	@Override
	public void insertScreen(TheaterVO vo) {
		adminDAO.insertScreen(vo);
		
	}
	//상영관 등록(B관)
	@Override
	public void insertScreenB(TheaterVO vo) {
		adminDAO.insertScreenB(vo);
		
	}
	//상영시간표 목록 출력
	@Override
	public List<TimetableVO> getTimetableList(TimetableVO vo) {
		return adminDAO.getTimetableList(vo);	
	}
	//상영시간표 갯수
	@Override
	public int getTimetableTotal() {
		return adminDAO.getTimetableTotal();
	}
	//상영시간표
	@Override
	public void deleteTimetable(String timetable_code) {
		adminDAO.deleteTimetable(timetable_code);
		
	}
	//상영관 목록
	@Override
	public List<ScreenVO> getScreenList(ScreenVO vo) {
		return adminDAO.getScreenList(vo);
	}
	//상영시간표에 저장할 영화 정보 가져오기
	@Override
	public MovieVO getMovie(MovieVO vo1) {
		return adminDAO.getMovie(vo1);
	}
	//상영시간표 등록
	@Override
	public void insertTimetable(TimetableVO vo) {
		adminDAO.insertTimetable(vo);
		
	}
	//상영관 정보
	@Override
	public ScreenVO getScreen(ScreenVO vo2) {
		return adminDAO.getScreen(vo2);
	}
	//좌석 등록
	@Override
	public void insertSeat(SeatVO vo3) {
		adminDAO.insertSeat(vo3);
		
	}
	//상영시간표 중복 찾기-1
	@Override
	public List<TimetableVO> checkTimetableList1(TimetableVO vo) {
		return adminDAO.checkTimetableList1(vo);
	}
	//상영시간표 중복 찾기-2
	@Override
	public List<TimetableVO> checkTimetableList2(TimetableVO vo) {
		return adminDAO.checkTimetableList2(vo);
	}
	
	//회원관리
	//회원목록 출력 , 페이징처리
	@Override
	public List<CustomerVO> getCustomerList(Criteria cri) {
		return adminDAO.getCustomerList(cri);
	}
	@Override
	public int getTotal(Criteria cri) {
		return adminDAO.getTotalCount(cri);
	}
	
	//관리자쪽 총 회원수 보기
	@Override
	public int getCustomerTotal() {
		return adminDAO.getCustomerTotal();
	}
	
	//회원 삭제
	@Override
	public void deleteCustomer(String id) {
		adminDAO.deleteCustomer(id);
	}
}
