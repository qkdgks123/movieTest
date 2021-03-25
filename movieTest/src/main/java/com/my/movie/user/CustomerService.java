package com.my.movie.user;

import java.util.ArrayList;
import java.util.List;

import com.my.movie.booking.BookingVO;
import com.my.movie.user.CustomerVO;

public interface CustomerService {
	
	public CustomerVO getCustomer(CustomerVO vo);
	
	public void setCustomer(CustomerVO customervo);// insert
	
	public void updateCustomer(CustomerVO vo);
	
	public void deleteCustomer(CustomerVO vo);
	
	
	// 비번 찾기
	public CustomerVO findPassword(CustomerVO vo);
	
	public CustomerVO idCheck(String id);
	
	
	CustomerVO mypageCustomer(CustomerVO vo);

	public List<BookingVO> booking(CustomerVO vo);

	

	
}
