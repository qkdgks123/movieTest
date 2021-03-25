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
	
	//������ �α��� üũ
	@Override
	public String loginCheck(AdminVO vo){
		return adminDAO.loginCheck(vo);
	}
	//��ȭ�� ��� ���
	@Override
	public List<TheaterVO> getTheaterList() {
		return adminDAO.getMovieList();	
	}
	//��ȭ�� ����
	@Override
	public int getTheaterTotal() {
		return adminDAO.getMovieTotal();
	}
	//��ȭ�� ����
	@Override
	public void deleteTheater(String theater_code) {
		adminDAO.deleteTheater(theater_code);	
	}
	//��ȭ�� �ߺ� Ȯ��
	@Override
	public TheaterVO getTheater(TheaterVO vo) {
		return adminDAO.getTheater(vo);
	}
	//��ȭ�� ���
	@Override
	public void insertTheater(TheaterVO vo) {
		adminDAO.insertTheater(vo);
		
	}
	//�󿵰� ���(A��)
	@Override
	public void insertScreen(TheaterVO vo) {
		adminDAO.insertScreen(vo);
		
	}
	//�󿵰� ���(B��)
	@Override
	public void insertScreenB(TheaterVO vo) {
		adminDAO.insertScreenB(vo);
		
	}
	//�󿵽ð�ǥ ��� ���
	@Override
	public List<TimetableVO> getTimetableList(TimetableVO vo) {
		return adminDAO.getTimetableList(vo);	
	}
	//�󿵽ð�ǥ ����
	@Override
	public int getTimetableTotal() {
		return adminDAO.getTimetableTotal();
	}
	//�󿵽ð�ǥ
	@Override
	public void deleteTimetable(String timetable_code) {
		adminDAO.deleteTimetable(timetable_code);
		
	}
	//�󿵰� ���
	@Override
	public List<ScreenVO> getScreenList(ScreenVO vo) {
		return adminDAO.getScreenList(vo);
	}
	//�󿵽ð�ǥ�� ������ ��ȭ ���� ��������
	@Override
	public MovieVO getMovie(MovieVO vo1) {
		return adminDAO.getMovie(vo1);
	}
	//�󿵽ð�ǥ ���
	@Override
	public void insertTimetable(TimetableVO vo) {
		adminDAO.insertTimetable(vo);
		
	}
	//�󿵰� ����
	@Override
	public ScreenVO getScreen(ScreenVO vo2) {
		return adminDAO.getScreen(vo2);
	}
	//�¼� ���
	@Override
	public void insertSeat(SeatVO vo3) {
		adminDAO.insertSeat(vo3);
		
	}
	//�󿵽ð�ǥ �ߺ� ã��-1
	@Override
	public List<TimetableVO> checkTimetableList1(TimetableVO vo) {
		return adminDAO.checkTimetableList1(vo);
	}
	//�󿵽ð�ǥ �ߺ� ã��-2
	@Override
	public List<TimetableVO> checkTimetableList2(TimetableVO vo) {
		return adminDAO.checkTimetableList2(vo);
	}
	
	//ȸ������
	//ȸ����� ��� , ����¡ó��
	@Override
	public List<CustomerVO> getCustomerList(Criteria cri) {
		return adminDAO.getCustomerList(cri);
	}
	@Override
	public int getTotal(Criteria cri) {
		return adminDAO.getTotalCount(cri);
	}
	
	//�������� �� ȸ���� ����
	@Override
	public int getCustomerTotal() {
		return adminDAO.getCustomerTotal();
	}
	
	//ȸ�� ����
	@Override
	public void deleteCustomer(String id) {
		adminDAO.deleteCustomer(id);
	}
}
