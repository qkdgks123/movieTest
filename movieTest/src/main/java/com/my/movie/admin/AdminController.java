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

	// 관리자 로그인
	@RequestMapping(value = "/adminlogin")
	public String adminlogin() {
		return "/admin/adminlogin";
	}

	// 관리자 로그인 체크
	@RequestMapping("loginCheck")
	public ModelAndView loginCheck(HttpSession session, AdminVO vo, ModelAndView mav) {
		String name = adminService.loginCheck(vo);
		// 로그인 성공
		if (name != null) {
			session.setAttribute("adminId", vo.getUserId());
			session.setAttribute("userId", vo.getUserId());
			session.setAttribute("adminName", name);
			session.setAttribute("userName", name);
			// mav.setViewName("admin/managecinema"); // 관리자 페이지 이동
			mav.setViewName("admin/adminMain"); // 관리자 페이지 이동
			mav.addObject("msg", "success");

			// 로그인 실패
		} else {
			mav.setViewName("admin/adminlogin"); // 로그인 페이지 이동
			mav.addObject("msg", "failure");
		}
		return mav;
	}

	// 3. 관리자 로그아웃
	@RequestMapping("adminlogout")
	public ModelAndView Logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("include/main");
		mav.addObject("msg", "Logout");
		return mav;
	}

	//영화관 관리 - 영화관 리스트 출력
	@RequestMapping(value = "/managecinema")
	public String managecinema(Model model) {
		model.addAttribute("theaterList", adminService.getTheaterList());
		model.addAttribute("total_theater", adminService.getTheaterTotal());
		System.out.println("model"+model);
		return "/admin/managecinema";
	}
	//영화관 관리 - 영화관 삭제
	@RequestMapping(value = "/TheaterDelete")
	public String TheaterDelete(HttpServletRequest request) {
		String[] theater_code=request.getParameterValues("theater_code");
		for(int i=0;i<theater_code.length;i++){
			adminService.deleteTheater(theater_code[i]);
		}
		return "redirect:/movie/managecinema";
	}
	//영화관 관리 - 영화관 등록 페이지
	@RequestMapping(value = "/TheaterInsertForm")
	public String TheaterInsertForm(Model model) {
		model.addAttribute("theaterList", adminService.getTheaterList());
		System.out.println("model"+model);
		return "/admin/theaterAddForm";
	}
	//영화관 등록 - 영화관 중복 확인
	@ResponseBody
	@RequestMapping(value = "/TheaterChk")
	public TheaterVO TheaterChk(TheaterVO vo) {
		System.out.println("vo:"+vo);
		vo= adminService.getTheater(vo);
		System.out.println("찾은 vo:"+vo);
		return vo;
	}	
	//영화관 등록 처리
	@RequestMapping(value = "/TheaterInsert")
	public String TheaterInsert(TheaterVO vo){
		adminService.insertTheater(vo);
		adminService.insertScreen(vo);//상영관-A관 등록
		adminService.insertScreenB(vo);//상영관-B관 등록
		return "redirect:/movie/managecinema";

	}
	//상영시관표 관리 - 상영시간표 리스트 출력
	@RequestMapping(value = "/manageTimetable")
	public String manageTimetable(Model model, TimetableVO vo) {
		model.addAttribute("timetablelist", adminService.getTimetableList(vo));
		model.addAttribute("total_timetable", adminService.getTimetableTotal());
		System.out.println("model"+model);
		return "/admin/adminTimetable";
	}
	//상영시간표 관리 - 상영시간표 삭제
	@RequestMapping(value = "/TimetableDelete")
	public String TimetableDelete(HttpServletRequest request) {
		String[] timetable_code=request.getParameterValues("timetable_code");
		for(int i=0;i<timetable_code.length;i++){
			adminService.deleteTimetable(timetable_code[i]);
		}
		return "redirect:/movie/manageTimetable";
	}
	//상영시간표 관리 - 상영시간표 등록 페이지
	@RequestMapping(value = "/TimetableInsertForm")
	public String TimetableInsertForm(Model model, TimetableVO vo, MovieVO vo1, TheaterVO vo2) {		
		model.addAttribute("timetableList", adminService.getTimetableList(vo));
		model.addAttribute("movieList", bookingService.getMovieList(vo1));
		model.addAttribute("areaList", bookingService.getAreaList(vo2));
		System.out.println("model"+model);
		return "/admin/timetableAddForm";
	}
	//상영시간표 관리 - 상영시간표 등록 페이지 , 극장 선택
	@ResponseBody
	@RequestMapping(value = "/TimetableInsertForm2")
	public List<TheaterVO> TimetableInsertForm2(TheaterVO vo, Model model) {
		System.out.println("시작");
		List<TheaterVO> theaterList = bookingService.getTheaterList(vo);
		System.out.println("theaterList:"+theaterList);
		return theaterList;
	}
	//상영시간표 관리 - 상영시간표 등록 페이지 , 상영관 선택
	@ResponseBody
	@RequestMapping(value = "/TimetableInsertForm3")
	public List<ScreenVO> TimetableInsertForm3(ScreenVO vo, Model model) {
		System.out.println(vo);
		List<ScreenVO> screenList = adminService.getScreenList(vo);
		System.out.println("screenList:"+screenList);
		return screenList;
	}
	//상영시간표 등록 처리
	@RequestMapping(value = "/TimetableInsert")
	public String TimetableInsert(TimetableVO vo,HttpServletRequest request, MovieVO vo1, ScreenVO vo2, SeatVO vo3) throws ParseException{
		System.out.println("TimetableVO:"+vo);
		
		String date = (String)request.getParameter("date");
		String time = (String)request.getParameter("time");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date screening_date =sdfDate.parse(date);
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		Date start_time =sdfTime.parse(date+time);//상영 날짜 및 시각 date타입 변환
		
		vo1 = adminService.getMovie(vo1);//영화테이블에서 m_name, m_posterImg, m_time 가져오기
		
		vo.setM_name(vo1.getM_name());
		vo.setM_poster(vo1.getM_posterImg());
		vo.setR_time(Integer.parseInt(vo1.getM_time()));
		vo.setScreening_date(screening_date);
		vo.setStart_time(start_time);//
		
		adminService.insertTimetable(vo);//상영시간표 추가
		
		//좌석테이블 추가하는 메소드 필요
		vo2 = adminService.getScreen(vo2);//상영관 정보 가져오기
		String code = vo2.getSeat_code();
		List<String> list = Arrays.asList(code.split(" "));
		for(Object obj : list) {
			String seat_code = (String)obj;
			vo3.setSeat_code(seat_code);
			adminService.insertSeat(vo3);
		}//상영관 테이블에서 seat_code를 가져와 각 seat_code별로 좌석 테이블에 저장
		
		return "redirect:/movie/manageTimetable";

	}
	//상영시간표 관리 - 상영시간표 등록 페이지 , 상영시간 중복 확인
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
		
		List<TimetableVO> timetableList1 = adminService.checkTimetableList1(vo);//상영시작시간보다 늦게 끝나는 상영시간표찾기
		List<TimetableVO> timetableList2 = adminService.checkTimetableList2(vo);//상영종료시간보다 빨리 시작하는 상영시간표찾기
		
		int duChk = 0;;

		for(int i = 0; i < timetableList1.size(); i++) {
			for(int j=0; j< timetableList2.size(); j++){
			    if(timetableList1.get(i).getTimetable_code().equals(timetableList2.get(j).getTimetable_code())) {
			        System.out.println("중복 있음");
			        duChk++;
			    }
			}
		}
		return duChk;
	}
	
	//회원 관리 - 회원 목록 출력
	@RequestMapping(value = "/managemember")
	public String managemember(Criteria cri, Model model) {
		//회원 목록출력:페이징처리때문 수정
		model.addAttribute("customerList", adminService.getCustomerList(cri));
		//페이징처리 관련 작업
		int total = adminService.getTotal(cri);
		model.addAttribute("pageMaker", new PageMaker(cri, total));
		
		//관리쪽 전체 회원수 확인용
		model.addAttribute("total_customer", adminService.getCustomerTotal());
		System.out.println("model"+model);
		return "/admin/managemember";
	}
	
	//회원 삭제
	@RequestMapping(value = "/CustomerDelete")
	public String CustomerDelete(HttpServletRequest request) {
		String[] id=request.getParameterValues("id");
		for(int i=0;i<id.length;i++){
			adminService.deleteCustomer(id[i]);
		}
		return "redirect:/movie/managemember";
	}
	


}
