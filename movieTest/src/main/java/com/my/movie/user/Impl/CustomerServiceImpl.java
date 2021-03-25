package com.my.movie.user.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.movie.booking.BookingVO;
import com.my.movie.user.CustomerService;
import com.my.movie.user.CustomerVO;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO customerDAO;
	@Override
	public CustomerVO getCustomer(CustomerVO vo) {
	  if(vo.getId()==null|| vo.getId().equals("")){
			throw new IllegalArgumentException("");
		}
	 return customerDAO.getCustomer(vo);
	}
	
	@Override
	public void setCustomer(CustomerVO customervo) {
		customerDAO.setCustomer(customervo);
	}
	
	@Override
	public void updateCustomer(CustomerVO vo) {
		customerDAO.updateCustomer(vo);
	}
 
	@Override
	public void deleteCustomer(CustomerVO vo) {
		customerDAO.deleteCustomer(vo);
	}
	
	
	
	//
	@Override
	public CustomerVO idCheck(String id) {
		
		return customerDAO.idCheck(id);
	}
	
	public CustomerVO mypageCustomer(CustomerVO vo){
	return customerDAO.mypageCustomer(vo);
	}
	
	// 비번 찾기
	public CustomerVO findPassword(CustomerVO vo) {
		return customerDAO.findPassword(vo);
	}

	@Override
	public List<BookingVO> booking(CustomerVO vo) {
		return customerDAO.findBooking(vo);
	}
	 
	
	
}
