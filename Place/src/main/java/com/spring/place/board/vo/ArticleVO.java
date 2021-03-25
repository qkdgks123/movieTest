// 장소등록 정보를 저장
package com.spring.place.board.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("articleVO")
public class ArticleVO {
	
	private int level;
	private int ORDER_SEQ_NUM;
	private int parentNO;
	private String GOODS_NAME;
	private int GOODS_PEOPLE_NUM;
	private String GOODS_CONTENTS;
	private String GOODS_TEL;
	private int GOODS_PRICE;
	private String GOODS_LOCATION;
	private Date writeDate;
	private String GOODS_OPERATION_TIME;
	private String imageFileName;
	private String id;
	private int GOODS_TYPE_ID;
	private String GOODS_STATUS;
	

	@Override
	public String toString() {
		return "ArticleVO [level=" + level + ", ORDER_SEQ_NUM=" + ORDER_SEQ_NUM + ", parentNO=" + parentNO
				+ ", GOODS_NAME=" + GOODS_NAME + ", GOODS_PEOPLE_NUM=" + GOODS_PEOPLE_NUM + ", GOODS_CONTENTS="
				+ GOODS_CONTENTS + ", GOODS_TEL=" + GOODS_TEL + ", GOODS_PRICE=" + GOODS_PRICE + ", GOODS_LOCATION="
				+ GOODS_LOCATION + ", writeDate=" + writeDate + ", GOODS_OPERATION_TIME=" + GOODS_OPERATION_TIME
				+ ", imageFileName=" + imageFileName + ", id=" + id + ", GOODS_TYPE_ID=" + GOODS_TYPE_ID
				+ ", goods_status=" + goods_status + "]";
	}

	public int getGOODS_TYPE_ID() {
		return GOODS_TYPE_ID;
	}

	public void setGOODS_TYPE_ID(int gOODS_TYPE_ID) {
		GOODS_TYPE_ID = gOODS_TYPE_ID;
	}

	public String getGoods_status() {
		return goods_status;
	}

	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}

	private String goods_status;

	public ArticleVO() {
		
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public int getORDER_SEQ_NUM() {
		return ORDER_SEQ_NUM;
	}

	public void setORDER_SEQ_NUM(int oRDER_SEQ_NUM) {
		ORDER_SEQ_NUM = oRDER_SEQ_NUM;
	}

	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}

	public String getGOODS_NAME() {
		return GOODS_NAME;
	}

	public void setGOODS_NAME(String gOODS_NAME) {
		GOODS_NAME = gOODS_NAME;
	}

	public int getGOODS_PEOPLE_NUM() {
		return GOODS_PEOPLE_NUM;
	}

	public void setGOODS_PEOPLE_NUM(int gOODS_PEOPLE_NUM) {
		GOODS_PEOPLE_NUM = gOODS_PEOPLE_NUM;
	}

	public String getGOODS_CONTENTS() {
		return GOODS_CONTENTS;
	}

	public void setGOODS_CONTENTS(String gOODS_CONTENTS) {
		GOODS_CONTENTS = gOODS_CONTENTS;
	}

	public String getGOODS_TEL() {
		return GOODS_TEL;
	}

	public void setGOODS_TEL(String gOODS_TEL) {
		GOODS_TEL = gOODS_TEL;
	}

	public int getGOODS_PRICE() {
		return GOODS_PRICE;
	}

	public void setGOODS_PRICE(int gOODS_PRICE) {
		GOODS_PRICE = gOODS_PRICE;
	}

	public String getGOODS_LOCATION() {
		return GOODS_LOCATION;
	}

	public void setGOODS_LOCATION(String gOODS_LOCATION) {
		GOODS_LOCATION = gOODS_LOCATION;
	}

	public String getGOODS_OPERATION_TIME() {
		return GOODS_OPERATION_TIME;
	}

	public void setGOODS_OPERATION_TIME(String gOODS_OPERATION_TIME) {
		GOODS_OPERATION_TIME = gOODS_OPERATION_TIME;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}	

	
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		try {
			if(imageFileName!= null && imageFileName.length()!=0) {
				this.imageFileName = URLEncoder.encode(imageFileName,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	

}