package com.spring.place.goods.vo;

import java.sql.Date;
import java.util.ArrayList;

public class GoodsVO {
	private int    goods_id;
	private String goods_name;
	private int    goods_people_num;
	private String goods_contents;
	private String   goods_tel;
	private int   goods_price;
	private String goods_location;
	private String goods_operation_time;
	private int goods_type_id;
	private String goods_fileName;
	private String goods_status;
	private Date   goods_credate;
	
	
	private String goods_type_name;

	
	
	public String getGoods_type_name() {
		return goods_type_name;
	}


	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}


	public Date getGoods_credate() {
		return goods_credate;
	}


	public void setGoods_credate(Date goods_credate) {
		this.goods_credate = goods_credate;
	}


	public String getGoods_status() {
		return goods_status;
	}


	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}


	public String getGoods_fileName() {
		return goods_fileName;
	}


	public void setGoods_fileName(String goods_fileName) {
		this.goods_fileName = goods_fileName;
	}


	public GoodsVO() {
	}


	public int getGoods_id() {
		return goods_id;
	}


	@Override
	public String toString() {
		return "GoodsVO [goods_id=" + goods_id + ", goods_name=" + goods_name + ", goods_people_num=" + goods_people_num
				+ ", goods_contents=" + goods_contents + ", goods_tel=" + goods_tel + ", goods_price=" + goods_price
				+ ", goods_location=" + goods_location + ", goods_operation_time=" + goods_operation_time
				+ ", goods_type_id=" + goods_type_id + "]";
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


	public int getGoods_people_num() {
		return goods_people_num;
	}


	public void setGoods_people_num(int goods_people_num) {
		this.goods_people_num = goods_people_num;
	}


	public String getGoods_contents() {
		return goods_contents;
	}


	public void setGoods_contents(String goods_contents) {
		this.goods_contents = goods_contents;
	}


	public String getGoods_tel() {
		return goods_tel;
	}


	public void setGoods_tel(String goods_tel) {
		this.goods_tel = goods_tel;
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


	public String getGoods_operation_time() {
		return goods_operation_time;
	}


	public void setGoods_operation_time(String goods_operation_time) {
		this.goods_operation_time = goods_operation_time;
	}


	public int getGoods_type_id() {
		return goods_type_id;
	}


	public void setGoods_type_id(int goods_type_id) {
		this.goods_type_id = goods_type_id;
	}




	
	

}
