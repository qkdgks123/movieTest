package com.spring.place.admin.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.place.board.vo.ArticleVO;


public interface AdminGoodsService {

	int acceptArticle(ArticleVO articleVO);

	ArticleVO selectOneArticle(int order_seq_num);

}
