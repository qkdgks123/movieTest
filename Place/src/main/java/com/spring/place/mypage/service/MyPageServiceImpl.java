package com.spring.place.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.place.goods.vo.GoodsVO;
import com.spring.place.member.vo.MemberVO;
import com.spring.place.mypage.dao.MyPageDAO;
import com.spring.place.order.vo.OrderVO;

@Service("myPageService")
@Transactional(propagation=Propagation.REQUIRED)
public class MyPageServiceImpl  implements MyPageService{
	@Autowired
	MyPageDAO myPageDAO;

	public List<OrderVO> listMyOrderGoods(String member_id) throws Exception{
		return myPageDAO.selectMyOrderGoodsList(member_id);
	}
	
	public OrderVO findMyOrderInfo(String order_seq_num) throws Exception{
		
		return myPageDAO.selectMyOrderInfo(order_seq_num);
	}
	
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception{
		return myPageDAO.selectMyOrderHistoryList(dateMap);
	}
	
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception{
		 String member_id=(String)memberMap.get("member_id");
		 myPageDAO.updateMyInfo(memberMap);
		 return myPageDAO.selectMyDetailInfo(member_id);
	}
	
	public void cancelOrder(String order_seq_num) throws Exception{
		
		System.out.println("mypage.service - 예약 삭제");
		myPageDAO.updateMyOrderCancel(order_seq_num);
	}
	public MemberVO myDetailInfo(String member_id) throws Exception{
		return myPageDAO.selectMyDetailInfo(member_id);
	}

	@Override
	public GoodsVO goodsDetail(int goods_id) {
		return myPageDAO.selectGoodsDetail(goods_id);
	}

	@Override
	public String goodsType(int goods_type_id) {
		
		return myPageDAO.selectGoodsType(goods_type_id);
	}
}
