package com.spring.place.order.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.place.order.vo.OrderVO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException {

		System.out.println("order.dao - 예약한 상품 목록");

		List<OrderVO> orderGoodsList=new ArrayList<OrderVO>();
		orderGoodsList = (ArrayList)sqlSession.selectList("mapper.order.selectMyOrderList",orderVO);

		return orderGoodsList;
	}

	public int insertNewOrder(Map orderMap) throws DataAccessException {
		
		System.out.println("order.dao - 새로운 주문(insert)");

		// 새 주문에 대한 번호를 가져옴
		int order_seq_num = selectOrderID();
		
		// 전달된 myOrderList에 주문 번호 설정
		orderMap.put("order_seq_num", order_seq_num);
		
		System.out.println("dao 확인");
		
		// id에 대한 insert문을 호출하면서 articleMap을 전달
		sqlSession.insert("mapper.order.insertNewOrder", orderMap);
		
		return order_seq_num;
		/*
		for(int i = 0; i < myOrderList.size(); i++) {
			
			OrderVO orderVO =(OrderVO)myOrderList.get(i);
			sqlSession.insert("mapper.order.insertNewOrder", orderVO);
		}
		*/
	}	

	public OrderVO findMyOrder(String order_id) throws DataAccessException {
		
		System.out.println("order.dao - 내 예약 찾기");
		
		OrderVO orderVO=(OrderVO)sqlSession.selectOne("mapper.order.selectMyOrder",order_id);		
		
		return orderVO;
	}

	public void removeGoodsFromCart(OrderVO orderVO)throws DataAccessException {
		
		System.out.println("order.dao - 새로운 주문(remove)_2");
		
		sqlSession.delete("mapper.order.deleteGoodsFromCart", orderVO);
	}

	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException {
		
		System.out.println("order.dao - 새로운 주문(remove)_1");
		
		for(int i = 0; i < myOrderList.size(); i++) {
			
			OrderVO orderVO = (OrderVO)myOrderList.get(i);
			sqlSession.delete("mapper.order.deleteGoodsFromCart", orderVO);		
		}
	}	
	
	private int selectOrderID() throws DataAccessException {
		
		System.out.println("order.dao - 주문번호(orderID) 추출");
		
		return sqlSession.selectOne("mapper.order.selectOrderID");
	}
}