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
	//��ȭ�� ���
	public List<TheaterVO> getMovieList() {
		return mybatis.selectList("admin.getTheaterList");
	}
	//��ȭ�� ����
	public int getMovieTotal() {
		return mybatis.selectOne("admin.getTheaterTotal");
	}
	//��ȭ�� ����
	public void deleteTheater(String theater_code) {
		mybatis.delete("admin.deleteTheater", theater_code);
	}
	//��ȭ�� �ߺ� Ȯ��
	public TheaterVO getTheater(TheaterVO vo) {
		return mybatis.selectOne("admin.getTheater",vo);
	}
	//��ȭ�� ���
	public void insertTheater(TheaterVO vo) {
		mybatis.insert("admin.insertTheater", vo);
		
	}
	//�󿵰� ���(A��)
	public void insertScreen(TheaterVO vo) {
		mybatis.insert("admin.insertScreen", vo);
	}
	//�󿵰� ���(B��)
	public void insertScreenB(TheaterVO vo) {
		mybatis.insert("admin.insertScreenB", vo);
		
	}
	//�󿵽ð�ǥ ���
	public List<TimetableVO> getTimetableList(TimetableVO vo) {
		return mybatis.selectList("admin.getTimetableList");
	}
	//�󿵽ð�ǥ ����
	public int getTimetableTotal() {
		return mybatis.selectOne("admin.getTimetableTotal");
	}
	//�󿵽ð�ǥ ����
	public void deleteTimetable(String timetable_code) {
		mybatis.delete("admin.deleteTimetable", timetable_code);
		
	}
	//�󿵰� ���
	public List<ScreenVO> getScreenList(ScreenVO vo) {
		return mybatis.selectList("admin.getScreenList",vo);

	}
	//��ȭ ����
	public MovieVO getMovie(MovieVO vo1) {
		return mybatis.selectOne("admin.getMovie", vo1);
	}
	//�󿵽ð�ǥ ���
	public void insertTimetable(TimetableVO vo) {
		mybatis.insert("admin.insertTimetable", vo);
		
	}
	//�󿵰� ���� ��������
	public ScreenVO getScreen(ScreenVO vo2) {
		return mybatis.selectOne("admin.getScreen", vo2);
	}
	//�¼� ���
	public void insertSeat(SeatVO vo3) {
		mybatis.insert("admin.insertSeat", vo3);
		
	}
	//�󿵽ð�ǥ �ߺ�ã��-1
	public List<TimetableVO> checkTimetableList1(TimetableVO vo) {
		return mybatis.selectList("admin.checkTimetableList1", vo);
	}
	//�󿵽ð�ǥ �ߺ�ã��-2
	public List<TimetableVO> checkTimetableList2(TimetableVO vo) {
		return mybatis.selectList("admin.checkTimetableList2", vo);
	}
	
	//ȸ������
	//ȸ����� ���, ����¡ó�� ���� �۾�
	public List<CustomerVO> getCustomerList(Criteria cri) {
		return mybatis.selectList("admin.getCustomerList", cri);
	}
	//��ü �Ǽ� ��ȸ
	public int getTotalCount(Criteria cri) {
		return mybatis.selectOne("admin.getTotalCount", cri);
	}
		
		
	//�� ȸ���� ���
	public int getCustomerTotal() {
		return mybatis.selectOne("admin.getCustomerTotal");
	}
	//ȸ�� ����
	public void deleteCustomer(String id) {
		mybatis.delete("admin.deleteCustomer", id);
	}


}
