package com.my.movie.user.Impl;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.movie.booking.BookingVO;
import com.my.movie.user.CustomerVO;


@Repository("CustomerDAO")
public class CustomerDAO {
 @Autowired
 private SqlSessionTemplate mybatis;
 
 public CustomerVO getCustomer(CustomerVO vo) {
   return mybatis.selectOne("CustomerDAO.getCustomer",vo);
 }
 
		//
		public void setCustomer(CustomerVO customervo) {
		mybatis.insert("CustomerDAO.setCustomer", customervo);
 }
 	
	 	//
		public void updateCustomer(CustomerVO vo) {
		System.out.println("===> JDBC updateCustomer() ?? ?™?˜™? ï¿??");
		mybatis.update("CustomerDAO.updateCustomer",vo);
	}
	
		//
		public void deleteCustomer(CustomerVO vo) {
		mybatis.delete("CustomerDAO.deleteCustomer", vo);
	}
	
		/*//
		public int passCheck(CustomerVO vo) {
		int result=mybatis.selectOne("CustomerDAO.getCustomer",vo);

		return result;
		}*/
		
		//
		public CustomerVO idCheck(String id) {
		System.out.println("===> Mybatisæ¿¡ï¿½ idCheck");
		return mybatis.selectOne("CustomerDAO.idCheck",id);
		}
		
		//
		public CustomerVO mypageCustomer(CustomerVO vo){
		return mybatis.selectOne("CustomerDAO.mypageCustomer",vo);
		}
		
		// ºñ¹ø Ã£±â
		public CustomerVO findPassword(CustomerVO vo) {
		return mybatis.selectOne("CustomerDAO.findPassword", vo);
		}

		public List<BookingVO> findBooking(CustomerVO vo) {
			return mybatis.selectList("CustomerDAO.findBooking", vo);
		}
		 
	

		
}
