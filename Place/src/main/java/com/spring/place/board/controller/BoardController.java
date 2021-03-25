package com.spring.place.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface BoardController {
	
	// * 게시글 리스트
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// * 게시글 등록
	public ResponseEntity addNewPlace(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	
	// * 게시글 상세
	public ModelAndView viewArticle(@RequestParam("ORDER_SEQ_NUM") int ORDER_SEQ_NUM, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// * 게시글 수정
	public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,  HttpServletResponse response) throws Exception;
	
	// * 게시글 삭제
	public ResponseEntity removeArticle(@RequestParam("ORDER_SEQ_NUM") int ORDER_SEQ_NUM, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// * 답글 등록
	public ResponseEntity addReply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
}