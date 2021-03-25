package com.spring.place.mypage.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.place.goods.vo.GoodsVO;
import com.spring.place.member.vo.MemberVO;
import com.spring.place.order.vo.OrderVO;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO{
	@Autowired
	private SqlSession sqlSession;
	
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException{
		List<OrderVO> orderGoodsList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderGoodsList",member_id);
		return orderGoodsList;
	}
	
	public OrderVO selectMyOrderInfo(String order_seq_num) throws DataAccessException{
		OrderVO myOrderInfo = sqlSession.selectOne("mapper.mypage.selectMyOrderInfo",order_seq_num);
		return myOrderInfo;
	}	

	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException{
		List<OrderVO> myOrderHistList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderHistoryList",dateMap);
		return myOrderHistList;
	}
	
	public void updateMyInfo(Map memberMap) throws DataAccessException{
		sqlSession.update("mapper.mypage.updateMyInfo",memberMap);
	}
	
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException{
		MemberVO memberVO=(MemberVO)sqlSession.selectOne("mapper.mypage.selectMyDetailInfo",member_id);
		return memberVO;
		
	}
	
	public void updateMyOrderCancel(String order_seq_num) throws DataAccessException{
		
		System.out.println("mypage.dao - 예약 삭제");
		sqlSession.delete("mapper.mypage.deleteMyOrderCancel",order_seq_num);
	}

	@Override
	public GoodsVO selectGoodsDetail(int goods_id) {
		GoodsVO goodsVO=(GoodsVO)sqlSession.selectOne("mapper.mypage.selectGoodsInfo",goods_id);
		return goodsVO;
	}

	@Override
	public String selectGoodsType(int goods_type_id) {
		
		return sqlSession.selectOne("mapper.mypage.selectGoodsType",goods_type_id);
	}
}
