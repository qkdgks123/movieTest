package com.spring.place.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.spring.place.order.dao.OrderDAO;
import com.spring.place.order.vo.OrderVO;


@Service("orderService")
@Transactional(propagation=Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDAO orderDAO;

	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception {
		
		System.out.println("order.service - 예약한 상품 목록");
		
		List<OrderVO> orderGoodsList;
		orderGoodsList = orderDAO.listMyOrderGoods(orderVO);
		
		return orderGoodsList;
	}

	public int addNewOrder(Map orderMap) throws Exception {
		
		System.out.println("order.service - 새로운 주문(insert)");
		
		return orderDAO.insertNewOrder(orderMap);
		//카트에서 주문 상품 제거한다.
		//orderDAO.removeGoodsFromCart(myOrderList);
	}
	
	public void removeOrder(List<OrderVO> myOrderList) throws Exception {
		
		System.out.println("order.service - 새로운 주문(remove)");
		
		orderDAO.removeGoodsFromCart(myOrderList);
	}

	public OrderVO findMyOrder(String order_id) throws Exception {
		
		System.out.println("order.service - 내 예약 찾기");

		return orderDAO.findMyOrder(order_id);
	}
}