package com.spring.place.admin.goods.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.place.board.vo.ArticleVO;


public interface AdminGoodsDAO {


	int acceptArticle(ArticleVO articleVO);

	ArticleVO selectOneArticle(int order_seq_num);

}
