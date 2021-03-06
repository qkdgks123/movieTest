package com.my.movie.admin.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.movie.admin.AdminVO;
import com.my.movie.admin.ScreenVO;
import com.my.movie.admin.SeatVO;
import com.my.movie.admin.TheaterVO;
import com.my.movie.admin.TimetableVO;
import com.my.movie.movie.Criteria;
import com.my.movie.movie.MovieVO;
import com.my.movie.user.CustomerVO;

@Repository("admin")
public class AdminDAO {
	@Autowired
	 private SqlSessionTemplate mybatis;
	
	public String loginCheck(AdminVO vo){
		System.out.println("vo:"+vo);
		//return mybatis.selectOne("AdminDAO.loginCheck",vo);
		return mybatis.selectOne("admin.loginCheck",vo);
	}
	//영화관 목록
	public List<TheaterVO> getMovieList() {
		return mybatis.selectList("admin.getTheaterList");
	}
	//영화관 갯수
	public int getMovieTotal() {
		return mybatis.selectOne("admin.getTheaterTotal");
	}
	//영화관 삭제
	public void deleteTheater(String theater_code) {
		mybatis.delete("admin.deleteTheater", theater_code);
	}
	//영화관 중복 확인
	public TheaterVO getTheater(TheaterVO vo) {
		return mybatis.selectOne("admin.getTheater",vo);
	}
	//영화관 등록
	public void insertTheater(TheaterVO vo) {
		mybatis.insert("admin.insertTheater", vo);
		
	}
	//상영관 등록(A관)
	public void insertScreen(TheaterVO vo) {
		mybatis.insert("admin.insertScreen", vo);
	}
	//상영관 등록(B관)
	public void insertScreenB(TheaterVO vo) {
		mybatis.insert("admin.insertScreenB", vo);
		
	}
	//상영시간표 목록
	public List<TimetableVO> getTimetableList(TimetableVO vo) {
		return mybatis.selectList("admin.getTimetableList");
	}
	//상영시간표 갯수
	public int getTimetableTotal() {
		return mybatis.selectOne("admin.getTimetableTotal");
	}
	//상영시간표 삭제
	public void deleteTimetable(String timetable_code) {
		mybatis.delete("admin.deleteTimetable", timetable_code);
		
	}
	//상영관 목록
	public List<ScreenVO> getScreenList(ScreenVO vo) {
		return mybatis.selectList("admin.getScreenList",vo);

	}
	//영화 정보
	public MovieVO getMovie(MovieVO vo1) {
		return mybatis.selectOne("admin.getMovie", vo1);
	}
	//상영시간표 등록
	public void insertTimetable(TimetableVO vo) {
		mybatis.insert("admin.insertTimetable", vo);
		
	}
	//상영관 정보 가져오기
	public ScreenVO getScreen(ScreenVO vo2) {
		return mybatis.selectOne("admin.getScreen", vo2);
	}
	//좌석 등록
	public void insertSeat(SeatVO vo3) {
		mybatis.insert("admin.insertSeat", vo3);
		
	}
	//상영시간표 중복찾기-1
	public List<TimetableVO> checkTimetableList1(TimetableVO vo) {
		return mybatis.selectList("admin.checkTimetableList1", vo);
	}
	//상영시간표 중복찾기-2
	public List<TimetableVO> checkTimetableList2(TimetableVO vo) {
		return mybatis.selectList("admin.checkTimetableList2", vo);
	}
	
	//회원관리
	//회원목록 출력, 페이징처리 관련 작업
	public List<CustomerVO> getCustomerList(Criteria cri) {
		return mybatis.selectList("admin.getCustomerList", cri);
	}
	//전체 건수 조회
	public int getTotalCount(Criteria cri) {
		return mybatis.selectOne("admin.getTotalCount", cri);
	}
		
		
	//총 회원수 출력
	public int getCustomerTotal() {
		return mybatis.selectOne("admin.getCustomerTotal");
	}
	//회원 삭제
	public void deleteCustomer(String id) {
		mybatis.delete("admin.deleteCustomer", id);
	}


}
