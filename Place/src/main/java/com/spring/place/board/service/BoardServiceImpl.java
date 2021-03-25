package com.spring.place.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.place.board.dao.BoardDAO;
import com.spring.place.board.vo.ArticleVO;
import com.spring.place.board.vo.ImageVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	ArticleVO articleVO;
	
	@Override
	public List<ArticleVO> listArticles() throws Exception {

		System.out.println("board.service - 게시글 리스트");
		
		// boardDAO의 selectAllArticlesList() 메서드를 호출
		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList();
        
		return articlesList;
	}

	@Override
	public int addNewPlace(Map articleMap) throws Exception {

		System.out.println("board.service - 게시글 등록");
		return boardDAO.insertNewArticle(articleMap);
	}

	@Override
	public ArticleVO viewArticle(int ORDER_SEQ_NUM) throws Exception {

		System.out.println("board.service - 게시글 상세");
		return boardDAO.selectArticle(ORDER_SEQ_NUM);
		
		
	}

	@Override
	public void modArticle(Map articleMap) throws Exception {

		System.out.println("board.service - 게시글 수정");
		boardDAO.updateArticle(articleMap);
	}

	@Override
	public void removeArticle(int ORDER_SEQ_NUM) throws Exception {
		System.out.println("board.service - 게시글 삭제");
		boardDAO.deleteArticle(ORDER_SEQ_NUM);
	}

	@Override
	public int addReply(Map articleMap) throws Exception {
		
		System.out.println("board.service - 답글 등록");
		System.out.println("service:"+articleMap.get("goods_name"));
		return boardDAO.insertNewReply(articleMap);
	}
}