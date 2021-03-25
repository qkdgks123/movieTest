package com.spring.place.order.service;

import java.util.List;
import java.util.Map;

import com.spring.place.order.vo.OrderVO;

public interface OrderService {
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;
	public int addNewOrder(Map orderMap) throws Exception;
	public OrderVO findMyOrder(String order_id) throws Exception;
}