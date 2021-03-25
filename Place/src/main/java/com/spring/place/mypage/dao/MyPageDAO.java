package com.spring.place.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.place.goods.vo.GoodsVO;
import com.spring.place.member.vo.MemberVO;
import com.spring.place.order.vo.OrderVO;

public interface MyPageDAO {
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException;
	public OrderVO selectMyOrderInfo(String order_seq_num) throws DataAccessException;
	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException;
	public void updateMyInfo(Map memberMap) throws DataAccessException;
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException;
	public void updateMyOrderCancel(String order_id) throws DataAccessException;
	public GoodsVO selectGoodsDetail(int goods_id);
	public String selectGoodsType(int goods_type_id);
}
