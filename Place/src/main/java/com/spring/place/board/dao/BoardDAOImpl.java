package com.spring.place.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.place.board.vo.ArticleVO;
import com.spring.place.board.vo.ImageVO;


@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllArticlesList() throws DataAccessException {
		
		System.out.println("board.dao - 게시글 리스트");
		
		// id가 selectAllArticlesList인 SQL문을 요청
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		
		return articlesList;
	}

	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		
		System.out.println("board.dao - 게시글 등록");
		
		// 새 글에 대한 번호를 가져옴
		int ORDER_SEQ_NUM = selectNewArticleNO();

		// 전달된 articleMap에 글 번호 설정
		articleMap.put("ORDER_SEQ_NUM", ORDER_SEQ_NUM);
		
		// id에 대한 insert문을 호출하면서 articleMap을 전달
		sqlSession.insert("mapper.board.insertNewArticle", articleMap);
	
		return ORDER_SEQ_NUM;
	}
	
	@Override
	public ArticleVO selectArticle(int ORDER_SEQ_NUM) throws DataAccessException {
		
		System.out.println("board.dao - 게시글 상세");
		return sqlSession.selectOne("mapper.board.selectArticle", ORDER_SEQ_NUM);
	}

	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		
		System.out.println("board.dao - 게시글 수정");
		sqlSession.update("mapper.board.updateArticle", articleMap);
	}

	@Override
	public void deleteArticle(int ORDER_SEQ_NUM) throws DataAccessException {
		
		System.out.println("board.dao - 게시글 삭제");
		sqlSession.delete("mapper.board.deleteArticle", ORDER_SEQ_NUM);
	}
	
	private int selectNewArticleNO() throws DataAccessException {
		
		System.out.println("board.dao - 새 글에 대한 번호를 가져옴");
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}

	@Override
	public int insertNewReply(Map articleMap) throws DataAccessException {

		System.out.println("board.dao - 답글 등록");
		
		// 새 글에 대한 번호를 가져옴
		int ORDER_SEQ_NUM = selectNewArticleNO();
		// 전달된 articleMap에 글 번호 설정
		articleMap.put("ORDER_SEQ_NUM", ORDER_SEQ_NUM);
		System.out.println("dao:"+articleMap.get("goods_name"));
		sqlSession.insert("mapper.board.insertReply", articleMap);
		
		return ORDER_SEQ_NUM;
	}
}