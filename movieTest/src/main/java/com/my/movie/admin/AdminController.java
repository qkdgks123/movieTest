package com.my.movie.admin;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.movie.booking.BookingService;
import com.my.movie.movie.Criteria;
import com.my.movie.movie.MovieService;
import com.my.movie.movie.MovieVO;
import com.my.movie.movie.PageMaker;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/movie")
public class AdminController {

	@Inject
	AdminService adminService;

	@Inject
	MovieService movieService;
	
	@Inject
	BookingService bookingService;

	// ������ �α���
	@RequestMapping(value = "/adminlogin")
	public String adminlogin() {
		return "/admin/adminlogin";
	}

	// ������ �α��� üũ
	@RequestMapping("loginCheck")
	public ModelAndView loginCheck(HttpSession session, AdminVO vo, ModelAndView mav) {
		String name = adminService.loginCheck(vo);
		// �α��� ����
		if (name != null) {
			session.setAttribute("adminId", vo.getUserId());
			session.setAttribute("userId", vo.getUserId());
			session.setAttribute("adminName", name);
			session.setAttribute("userName", name);
			// mav.setViewName("admin/managecinema"); // ������ ������ �̵�
			mav.setViewName("admin/adminMain"); // ������ ������ �̵�
			mav.addObject("msg", "success");

			// �α��� ����
		} else {
			mav.setViewName("admin/adminlogin"); // �α��� ������ �̵�
			mav.addObject("msg", "failure");
		}
		return mav;
	}

	// 3. ������ �α׾ƿ�
	@RequestMapping("adminlogout")
	public ModelAndView Logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("include/main");
		mav.addObject("msg", "Logout");
		return mav;
	}

	//��ȭ�� ���� - ��ȭ�� ����Ʈ ���
	@RequestMapping(value = "/managecinema")
	public String managecinema(Model model) {
		model.addAttribute("theaterList", adminService.getTheaterList());
		model.addAttribute("total_theater", adminService.getTheaterTotal());
		System.out.println("model"+model);
		return "/admin/managecinema";
	}
	//��ȭ�� ���� - ��ȭ�� ����
	@RequestMapping(value = "/TheaterDelete")
	public String TheaterDelete(HttpServletRequest request) {
		String[] theater_code=request.getParameterValues("theater_code");
		for(int i=0;i<theater_code.length;i++){
			adminService.deleteTheater(theater_code[i]);
		}
		return "redirect:/movie/managecinema";
	}
	//��ȭ�� ���� - ��ȭ�� ��� ������
	@RequestMapping(value = "/TheaterInsertForm")
	public String TheaterInsertForm(Model model) {
		model.addAttribute("theaterList", adminService.getTheaterList());
		System.out.println("model"+model);
		return "/admin/theaterAddForm";
	}
	//��ȭ�� ��� - ��ȭ�� �ߺ� Ȯ��
	@ResponseBody
	@RequestMapping(value = "/TheaterChk")
	public TheaterVO TheaterChk(TheaterVO vo) {
		System.out.println("vo:"+vo);
		vo= adminService.getTheater(vo);
		System.out.println("ã�� vo:"+vo);
		return vo;
	}	
	//��ȭ�� ��� ó��
	@RequestMapping(value = "/TheaterInsert")
	public String TheaterInsert(TheaterVO vo){
		adminService.insertTheater(vo);
		adminService.insertScreen(vo);//�󿵰�-A�� ���
		adminService.insertScreenB(vo);//�󿵰�-B�� ���
		return "redirect:/movie/managecinema";

	}
	//�󿵽ð�ǥ ���� - �󿵽ð�ǥ ����Ʈ ���
	@RequestMapping(value = "/manageTimetable")
	public String manageTimetable(Model model, TimetableVO vo) {
		model.addAttribute("timetablelist", adminService.getTimetableList(vo));
		model.addAttribute("total_timetable", adminService.getTimetableTotal());
		System.out.println("model"+model);
		return "/admin/adminTimetable";
	}
	//�󿵽ð�ǥ ���� - �󿵽ð�ǥ ����
	@RequestMapping(value = "/TimetableDelete")
	public String TimetableDelete(HttpServletRequest request) {
		String[] timetable_code=request.getParameterValues("timetable_code");
		for(int i=0;i<timetable_code.length;i++){
			adminService.deleteTimetable(timetable_code[i]);
		}
		return "redirect:/movie/manageTimetable";
	}
	//�󿵽ð�ǥ ���� - �󿵽ð�ǥ ��� ������
	@RequestMapping(value = "/TimetableInsertForm")
	public String TimetableInsertForm(Model model, TimetableVO vo, MovieVO vo1, TheaterVO vo2) {		
		model.addAttribute("timetableList", adminService.getTimetableList(vo));
		model.addAttribute("movieList", bookingService.getMovieList(vo1));
		model.addAttribute("areaList", bookingService.getAreaList(vo2));
		System.out.println("model"+model);
		return "/admin/timetableAddForm";
	}
	//�󿵽ð�ǥ ���� - �󿵽ð�ǥ ��� ������ , ���� ����
	@ResponseBody
	@RequestMapping(value = "/TimetableInsertForm2")
	public List<TheaterVO> TimetableInsertForm2(TheaterVO vo, Model model) {
		System.out.println("����");
		List<TheaterVO> theaterList = bookingService.getTheaterList(vo);
		System.out.println("theaterList:"+theaterList);
		return theaterList;
	}
	//�󿵽ð�ǥ ���� - �󿵽ð�ǥ ��� ������ , �󿵰� ����
	@ResponseBody
	@RequestMapping(value = "/TimetableInsertForm3")
	public List<ScreenVO> TimetableInsertForm3(ScreenVO vo, Model model) {
		System.out.println(vo);
		List<ScreenVO> screenList = adminService.getScreenList(vo);
		System.out.println("screenList:"+screenList);
		return screenList;
	}
	//�󿵽ð�ǥ ��� ó��
	@RequestMapping(value = "/TimetableInsert")
	public String TimetableInsert(TimetableVO vo,HttpServletRequest request, MovieVO vo1, ScreenVO vo2, SeatVO vo3) throws ParseException{
		System.out.println("TimetableVO:"+vo);
		
		String date = (String)request.getParameter("date");
		String time = (String)request.getParameter("time");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date screening_date =sdfDate.parse(date);
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		Date start_time =sdfTime.parse(date+time);//�� ��¥ �� �ð� dateŸ�� ��ȯ
		
		vo1 = adminService.getMovie(vo1);//��ȭ���̺��� m_name, m_posterImg, m_time ��������
		
		vo.setM_name(vo1.getM_name());
		vo.setM_poster(vo1.getM_posterImg());
		vo.setR_time(Integer.parseInt(vo1.getM_time()));
		vo.setScreening_date(screening_date);
		vo.setStart_time(start_time);//
		
		adminService.insertTimetable(vo);//�󿵽ð�ǥ �߰�
		
		//�¼����̺� �߰��ϴ� �޼ҵ� �ʿ�
		vo2 = adminService.getScreen(vo2);//�󿵰� ���� ��������
		String code = vo2.getSeat_code();
		List<String> list = Arrays.asList(code.split(" "));
		for(Object obj : list) {
			String seat_code = (String)obj;
			vo3.setSeat_code(seat_code);
			adminService.insertSeat(vo3);
		}//�󿵰� ���̺��� seat_code�� ������ �� seat_code���� �¼� ���̺� ����
		
		return "redirect:/movie/manageTimetable";

	}
	//�󿵽ð�ǥ ���� - �󿵽ð�ǥ ��� ������ , �󿵽ð� �ߺ� Ȯ��
	@ResponseBody
	@RequestMapping(value = "/TimetableCheckForm")
	public int TimetableCheckForm(TimetableVO vo, MovieVO vo1, Model model, HttpServletRequest request) throws ParseException {
		String time = request.getParameter("time");
		
		vo1 = adminService.getMovie(vo1);
		int r_time = Integer.parseInt(vo1.getM_time());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		Date start_time = sdf.parse(time);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(start_time);
		cal.add(Calendar.MINUTE, r_time);
		Date end_time=cal.getTime();
		
		vo.setStart_time(start_time);
		vo.setEnd_time(end_time);
		
		List<TimetableVO> timetableList1 = adminService.checkTimetableList1(vo);//�󿵽��۽ð����� �ʰ� ������ �󿵽ð�ǥã��
		List<TimetableVO> timetableList2 = adminService.checkTimetableList2(vo);//������ð����� ���� �����ϴ� �󿵽ð�ǥã��
		
		int duChk = 0;;

		for(int i = 0; i < timetableList1.size(); i++) {
			for(int j=0; j< timetableList2.size(); j++){
			    if(timetableList1.get(i).getTimetable_code().equals(timetableList2.get(j).getTimetable_code())) {
			        System.out.println("�ߺ� ����");
			        duChk++;
			    }
			}
		}
		return duChk;
	}
	
	//ȸ�� ���� - ȸ�� ��� ���
	@RequestMapping(value = "/managemember")
	public String managemember(Criteria cri, Model model) {
		//ȸ�� ������:����¡ó������ ����
		model.addAttribute("customerList", adminService.getCustomerList(cri));
		//����¡ó�� ���� �۾�
		int total = adminService.getTotal(cri);
		model.addAttribute("pageMaker", new PageMaker(cri, total));
		
		//������ ��ü ȸ���� Ȯ�ο�
		model.addAttribute("total_customer", adminService.getCustomerTotal());
		System.out.println("model"+model);
		return "/admin/managemember";
	}
	
	//ȸ�� ����
	@RequestMapping(value = "/CustomerDelete")
	public String CustomerDelete(HttpServletRequest request) {
		String[] id=request.getParameterValues("id");
		for(int i=0;i<id.length;i++){
			adminService.deleteCustomer(id[i]);
		}
		return "redirect:/movie/managemember";
	}
	


}
