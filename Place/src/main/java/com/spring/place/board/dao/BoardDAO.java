package com.spring.place.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.place.board.vo.ArticleVO;


public interface BoardDAO {
	
	// * 게시글 리스트
	public List selectAllArticlesList() throws DataAccessException;
	
	// * 게시글 등록
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	//public void insertNewImage(Map articleMap) throws DataAccessException;
	
	// * 게시글 상세
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	
	// * 게시글 수정
	public void updateArticle(Map articleMap) throws DataAccessException;
	
	// * 게시글 삭제
	public void deleteArticle(int articleNO) throws DataAccessException;
	
	// * 답글 등록
	public int insertNewReply(Map<String, Object> articleMap) throws DataAccessException;

	//public List selectImageFileList(int articleNO) throws DataAccessException;
}