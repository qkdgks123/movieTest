package com.spring.place.mypage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.spring.place.goods.vo.GoodsVO;
import com.spring.place.member.vo.MemberVO;
import com.spring.place.order.vo.OrderVO;

public interface MyPageService{
	public List<OrderVO> listMyOrderGoods(String member_id) throws Exception;
	public OrderVO findMyOrderInfo(String order_seq_num) throws Exception;
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception;
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception;
	public void cancelOrder(String order_id) throws Exception;
	public MemberVO myDetailInfo(String member_id) throws Exception;
	public GoodsVO goodsDetail(int goods_id);
	public String goodsType(int goods_type_id);

}
