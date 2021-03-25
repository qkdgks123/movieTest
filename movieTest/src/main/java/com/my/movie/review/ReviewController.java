package com.my.movie.review;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.my.movie.booking.BookingService;
import com.my.movie.movie.Criteria;
import com.my.movie.movie.MovieVO;
import com.my.movie.movie.PageMaker;
import com.my.movie.user.CustomerVO;

import javafx.scene.control.Alert;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("review")
@RequestMapping("/movie")
public class ReviewController {
	@Inject
	ReviewService reviewService;

	// ���� ���
	@RequestMapping(value = "/ReviewInsert")
	public String ReviewInsert(ReviewVO vo, CustomerVO vo1, Model model, HttpSession session, Criteria cri, HttpServletRequest request) {
		vo1 = (CustomerVO) session.getAttribute("customer");
		String m_code = request.getParameter("m_code");

		// �α����� �� �Ǿ� ������ �α��� ��������
		if (vo1 == null) {
			System.out.println("vo1:" + vo1);

			return "/user/login";
		} else {
			System.out.println("vo1:" + vo1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
			String regist_day = sdf.format(new Date());

			vo.setRegist_day(regist_day);

			System.out.println("vo:" + vo);
			
			List<ReviewVO> chkFirstReviewList = reviewService.chkFirstReview(vo.getM_code());

			System.out.println("chkFirstReviewList:"+chkFirstReviewList);
			
			int chkFirstReviewNum = 1;
			
			if (chkFirstReviewList == null) {
				
				System.out.println("Null vo NUM:"+vo.getReview_num());
				
				reviewService.insertReview(vo);
				session.getAttribute("movieinfo");
				System.out.println("movieinfo:" + session.getAttribute("movieinfo"));
				System.out.println("------------chkFirstReviewList == null------------");
				
				// ���������:����¡ó��
				model.addAttribute("ReviewList", reviewService.getReviewList(cri));

				
				// ����¡ó�� ���� �۾�
				int total = reviewService.getTotal(cri);
				model.addAttribute("pageMaker", new PageMaker(cri, total));

				//�������
				//�ش� ��ȭ ��������
				float total_review_Avg;
				if(total==0) {
					total_review_Avg = 0f;
				}else {
					total_review_Avg = reviewService.getReviewAvg(m_code);
				}
				model.addAttribute("total_review_Avg", total_review_Avg);
				
				
				return "/movie/movieinfo";
			} else {
				for(int i=0;i<chkFirstReviewList.size();i++) {
					chkFirstReviewNum+=1;
				}
				System.out.println("chkFirstReviewNum:"+chkFirstReviewNum);
				vo.setReview_num(chkFirstReviewNum);
				System.out.println("Not Null vo NUM:"+vo.getReview_num());
				
				
				reviewService.insertReview(vo);
				session.getAttribute("movieinfo");
				System.out.println("movieinfo:" + session.getAttribute("movieinfo"));
				System.out.println("------------chkFirstReviewList != null------------");
				
				// ���������:����¡ó��
				model.addAttribute("ReviewList", reviewService.getReviewList(cri));
				
				// ����¡ó�� ���� �۾�
				int total = reviewService.getTotal(cri);
				model.addAttribute("pageMaker", new PageMaker(cri, total));

				//�������
				//�ش� ��ȭ ��������
				float total_review_Avg;
				if(total==0) {
					total_review_Avg = 0f;
				}else {
					total_review_Avg = reviewService.getReviewAvg(m_code);
				}
				model.addAttribute("total_review_Avg", total_review_Avg);
				
				
				return "/movie/movieinfo";
			}
		}
	}
	//���� ����
	@RequestMapping(value = "/ReviewDelete")
	public String ReviewDelete(ReviewVO vo, HttpServletRequest request) {
		reviewService.deleteReview(vo);
		
		//���� ������ ���� �缳������ �۾�
		String m_code = request.getParameter("m_code");
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		int getReviewListFromDeleteNum = reviewService.getReviewListFromDelete(vo);
		System.out.println(getReviewListFromDeleteNum);
		for(int i = 0;i<getReviewListFromDeleteNum;i++) {
			vo.setReview_num(review_num);
			System.out.println("vo.getReview_num():"+vo.getReview_num());
			reviewService.reNameReview(vo);
			System.out.println("review_numStr:"+review_num);
			review_num++;
			System.out.println("review_numStr:"+review_num);
		}
		
		return "redirect:/movie/movieinfo?m_code="+m_code;
	}

	
	

}













