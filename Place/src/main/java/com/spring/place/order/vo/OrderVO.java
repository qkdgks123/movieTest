package com.spring.place.order.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("orderVO")
public class OrderVO {
	private int order_seq_num;
	private String member_id;
	private int goods_id;
	private String goods_name;
	private Date register_date;
	private Date reservation_date;
	private int goods_type_id;
	private String tel1;
	private String tel2;
	private String tel3;
	
	private String goods_fileName;
	private String goods_operation_time;
	private int goods_people_num;
	private String goods_location;
	private int goods_price;
	private String goods_type_name;
	private String goods_tel;
	private String RESERVATION_FROM_TIME;
	private String RESERVATION_TO_TIME;
	
	
	
	
	public String getRESERVATION_FROM_TIME() {
		return RESERVATION_FROM_TIME;
	}
	public void setRESERVATION_FROM_TIME(String rESERVATION_FROM_TIME) {
		RESERVATION_FROM_TIME = rESERVATION_FROM_TIME;
	}
	public String getRESERVATION_TO_TIME() {
		return RESERVATION_TO_TIME;
	}
	public void setRESERVATION_TO_TIME(String rESERVATION_TO_TIME) {
		RESERVATION_TO_TIME = rESERVATION_TO_TIME;
	}
	public String getGoods_tel() {
		return goods_tel;
	}
	public void setGoods_tel(String goods_tel) {
		this.goods_tel = goods_tel;
	}
	public String getGoods_type_name() {
		return goods_type_name;
	}
	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}
	public int getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(int goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_location() {
		return goods_location;
	}
	public void setGoods_location(String goods_location) {
		this.goods_location = goods_location;
	}
	public int getGoods_people_num() {
		return goods_people_num;
	}
	public void setGoods_people_num(int goods_people_num) {
		this.goods_people_num = goods_people_num;
	}
	public String getGoods_operation_time() {
		return goods_operation_time;
	}
	public void setGoods_operation_time(String goods_operation_time) {
		this.goods_operation_time = goods_operation_time;
	}
	public String getGoods_fileName() {
		return goods_fileName;
	}
	public void setGoods_fileName(String goods_fileName) {
		this.goods_fileName = goods_fileName;
	}
	public OrderVO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param order_seq_num
	 * @param member_id
	 * @param goods_id
	 * @param goods_name
	 * @param register_date
	 * @param reservation_date
	 * @param goods_type_id
	 * @param tel1
	 * @param tel2
	 * @param tel3
	 */
	public OrderVO(int order_seq_num, String member_id, int goods_id, String goods_name, Date register_date,
			Date reservation_date, int goods_type_id, String tel1, String tel2, String tel3) {
		super();
		this.order_seq_num = order_seq_num;
		this.member_id = member_id;
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.register_date = register_date;
		this.reservation_date = reservation_date;
		this.goods_type_id = goods_type_id;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
	}
	public int getOrder_seq_num() {
		return order_seq_num;
	}
	public void setOrder_seq_num(int order_seq_num) {
		this.order_seq_num = order_seq_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	public int getGoods_type_id() {
		return goods_type_id;
	}
	public void setGoods_type_id(int goods_type_id) {
		this.goods_type_id = goods_type_id;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	@Override
	public String toString() {
		return "OrderVO [order_seq_num=" + order_seq_num + ", member_id=" + member_id + ", goods_id=" + goods_id
				+ ", goods_name=" + goods_name + ", register_date=" + register_date + ", reservation_date="
				+ reservation_date + ", goods_type_id=" + goods_type_id + ", tel1=" + tel1 + ", tel2=" + tel2
				+ ", tel3=" + tel3 + "]";
	}


	
	
	
	
	
	

}
