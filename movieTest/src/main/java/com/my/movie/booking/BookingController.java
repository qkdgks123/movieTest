package com.my.movie.booking;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.my.movie.admin.TheaterVO;
import com.my.movie.admin.TimetableVO;
import com.my.movie.movie.MovieVO;
import com.my.movie.user.CustomerVO;


@Controller
@SessionAttributes("booking")
@RequestMapping("/movie")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	//���Ž���
	@RequestMapping(value = "/startBook")
	public String startBook(MovieVO vo, TheaterVO vo1, CustomerVO vo2, Model model, HttpSession session) {
		vo2 = (CustomerVO)session.getAttribute("customer");
		if(vo2==null){
			session.setAttribute("action", "booking");
			return "redirect:/movie/login";
		}//�α����� �� �Ǿ� ������ �α��� ��������
		model.addAttribute("movieList", bookingService.getMovieList(vo));
		model.addAttribute("areaList", bookingService.getAreaList(vo1));
		System.out.println("model:"+model);
		return "/booking/startBook";
	}
	//���� ����
	@ResponseBody
	@RequestMapping(value = "/startBook2")
	public List<TheaterVO> startBook2(TheaterVO vo, Model model) {
		List<TheaterVO> theaterList = bookingService.getTheaterList(vo);
		System.out.println("theaterList:"+theaterList);
		return theaterList;
	}
	//�ð� ����
	@ResponseBody
	@RequestMapping(value = "/startBook3")
	public List<TimetableVO> startBook3(TimetableVO vo) {
		List<TimetableVO> timetableList = bookingService.getTimetableList(vo);
		System.out.println("timetableList:"+timetableList);
		return timetableList;
	}
	//�¼� ����
	@RequestMapping(value = "/selectSeat")
	public String selectSeat(HttpServletRequest request, Model model) {
		String timetable_code = request.getParameter("time");
		model.addAttribute("timetable", bookingService.getTimetable(timetable_code));
		model.addAttribute("seatList", bookingService.getSeat(timetable_code));//����� �¼� ��������
		System.out.println("model:"+model);
		TimetableVO vo = (TimetableVO)bookingService.getTimetable(timetable_code);
		model.addAttribute("screen", bookingService.getScreen(vo.getScreen_code()));
		System.out.println("model:"+model);
		return "/booking/selectSeat";
	}
	//���� �Ϸ�
	@RequestMapping(value = "/bookResult")
	public String bookResult(BookingVO vo, Model model, HttpSession session) {
		System.out.println("model:"+model);
		vo.setCustomer_ID(((CustomerVO)session.getAttribute("customer")).getId());//���ǿ��� ID ��������
		bookingService.insertBooking(vo);//���� ���̺� ����
		String[] seatList = vo.getSeat_code().split(" ");//���� �¼� �迭�� ����
		for(int i=0; i<vo.getSeats_num();i++){
			bookingService.updateSeat(seatList[i]);//�¼����̺��� ���࿩�� ����
		}
		return "/booking/bookResult";
	}
	
}
