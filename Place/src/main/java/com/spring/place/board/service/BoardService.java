package com.spring.place.board.service;

import java.util.List;
import java.util.Map;

import com.spring.place.board.vo.ArticleVO;

public interface BoardService {
	
	// * 게시글 리스트
	public List<ArticleVO> listArticles() throws Exception;
	
	// * 게시글 등록
	public int addNewPlace(Map articleMap) throws Exception;
	
	// * 게시글 상세
	public ArticleVO viewArticle(int ORDER_SEQ_NUM) throws Exception;
	//public Map viewArticle(int articleNO) throws Exception;
	
	// * 게시글 수정
	public void modArticle(Map articleMap) throws Exception;
	
	// * 게시글 삭제
	public void removeArticle(int ORDER_SEQ_NUM) throws Exception;

	// * 답글 등록
	public int addReply(Map articleMap) throws Exception;
}