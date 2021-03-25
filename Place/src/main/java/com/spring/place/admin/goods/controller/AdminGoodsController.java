package com.spring.place.admin.goods.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface AdminGoodsController {
	public ModelAndView adminAcceptArticle (
			@RequestParam(value="ORDER_SEQ_NUM", required=true) int order_seq_num,
			HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
