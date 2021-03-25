package com.spring.place.admin.goods.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.place.admin.goods.service.AdminGoodsService;
import com.spring.place.board.vo.ArticleVO;
import com.spring.place.goods.vo.GoodsVO;

@Controller("adminGoodsController")
@RequestMapping(value="/admin/goods")
public class AdminGoodsControllerImpl  implements AdminGoodsController{
	private static final String CURR_IMAGE_REPO_PATH = "C:\\shopping\\goods";
	@Autowired
	AdminGoodsService adminGoodsService;
	
	@RequestMapping(value="/adminAcceptArticle.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView adminAcceptArticle (
									@RequestParam(value="ORDER_SEQ_NUM", required=true) int order_seq_num,
									HttpServletRequest request, HttpServletResponse response)  throws Exception {
		
	
		ArticleVO articleVO=adminGoodsService.selectOneArticle(order_seq_num);
		
		System.out.println("articleVO-->"+articleVO.toString());
		
		int result=adminGoodsService.acceptArticle(articleVO);
		System.out.println("admin.controller 성공"+ result);
		
		/*
		 * multipartRequest.setCharacterEncoding("utf-8"); HttpSession session =
		 * multipartRequest.getSession();
		 * 
		 * String message; ResponseEntity resEnt = null; HttpHeaders responseHeaders =
		 * new HttpHeaders(); responseHeaders.add("Content-Type",
		 * "text/html; charset=utf-8");
		 */
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out=response.getWriter();
		if(result>0) {
			 out.print("<script>");
			 out.print("alert('승락 성공');");
			 out.print("location.href='http://localhost:8282/studyproj/board/listArticles.do';");
			 out.print("</script>");

		}else {
			 out.print("<script>");
			 out.print("alert('승락 실패');");
			 out.print("</script>");
		}
		return null;
		
	}



	

	
	

}
