package com.spring.place.admin.goods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.place.board.vo.ArticleVO;


@Repository("adminGoodsDAO")
public class AdminGoodsDAOImpl  implements AdminGoodsDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int acceptArticle(ArticleVO articleVO) {
		System.out.println("admin.dao.insert 성공");
		sqlSession.insert("mapper.admin.goods.insertNewImage",articleVO);
		return sqlSession.insert("mapper.admin.goods.insertNewGoods",articleVO);
	
	
	}
	

	@Override
	public ArticleVO selectOneArticle(int order_seq_num) {
		// TODO Auto-generated method stub
		System.out.println("admin.dao.select 성공");
		return sqlSession.selectOne("mapper.admin.goods.selectArticle",order_seq_num);
	}
	
	/*
	 * @Override public int insertNewGoods(Map newGoodsMap) throws
	 * DataAccessException {
	 * sqlSession.insert("mapper.admin.goods.insertNewGoods",newGoodsMap); return
	 * Integer.parseInt((String)newGoodsMap.get("goods_id")); }
	 */
	




	

}
