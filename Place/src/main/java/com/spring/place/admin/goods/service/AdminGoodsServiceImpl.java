package com.spring.place.admin.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.spring.place.admin.goods.dao.AdminGoodsDAO;
import com.spring.place.board.vo.ArticleVO;


@Service("adminGoodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminGoodsServiceImpl implements AdminGoodsService {
	@Autowired
	AdminGoodsDAO adminGoodsDAO;

	@Override
	public int acceptArticle(ArticleVO articleVO) {
		
		return adminGoodsDAO.acceptArticle(articleVO);
	}

	@Override
	public ArticleVO selectOneArticle(int order_seq_num) {
		
		return adminGoodsDAO.selectOneArticle(order_seq_num);
	}
	
	/*
	 * @Override public int addNewGoods(Map newGoodsMap) throws Exception{ int
	 * goods_id = adminGoodsDAO.insertNewGoods(newGoodsMap); ArrayList<ImageFileVO>
	 * imageFileList = (ArrayList)newGoodsMap.get("imageFileList"); for(ImageFileVO
	 * imageFileVO : imageFileList) { imageFileVO.setGoods_id(goods_id); }
	 * adminGoodsDAO.insertGoodsImageFile(imageFileList); return goods_id; }
	 */
	
	
	
}
