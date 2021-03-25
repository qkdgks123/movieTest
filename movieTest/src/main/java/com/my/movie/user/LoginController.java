package com.my.movie.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.movie.booking.BookingService;
import com.my.movie.booking.BookingVO;
import com.my.movie.user.Impl.CustomerDAO;

@Controller
@SessionAttributes("customer")
@RequestMapping("movie")
public class LoginController {
	
	private static final Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	CustomerService customerService;
	

	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginView(CustomerVO vo){
		return "/user/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(CustomerVO vo, Model model,HttpSession session,
			            RedirectAttributes rttr)throws Exception{
		//System.out.println("session:"+session.getAttribute("action"));
		String action = (String)session.getAttribute("action");
		if(action!=null) System.out.println(action);
		
		CustomerVO customer = customerService.getCustomer(vo);
		/*if(customer == null) {
			session.setAttribute("customer", null);
			rttr.addFlashAttribute("msg", false);
		}
	 */
		if(customer!=null){
			if(action==null){
			session.setAttribute("customer", customer);
			//model.addAttribute("customer", customer);
			return "/include/main";
			}else{
				session.setAttribute("customer", customer);
				return "redirect:/movie/startBook";
			}
		}else
			return "/user/login";
	}
	
	
		@ResponseBody
		@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
		public int postIdCheck(HttpServletRequest req) throws Exception {
		 logger.info("post idCheck");
		 
		 String id= req.getParameter("id");
		 CustomerVO idCheck =  customerService.idCheck(id);
		 
		 int result = 0;
		 
		 if(idCheck != null) {
		  result = 1;
		 } 
		 
		 return result;
		}

		@RequestMapping(value="/findPassword",method=RequestMethod.GET)
		public String findPassword( CustomerVO vo){
			return "/user/forgotPassword";
		}

		// 비번찾기
		@RequestMapping(value="/findPassword", method=RequestMethod.POST)
		public String findPassword(HttpSession session,ServletRequest sq
				,CustomerVO vo,CustomerDAO customerDAO) throws Exception {
		CustomerVO c= customerService.findPassword(vo);
		if(c!=null){
			session.setAttribute("name",c.getName());
			session.setAttribute("phone",c.getPhone());
			session.setAttribute("password",c.getPassword());
		}else if(c==null){
			session.setAttribute("name", null);
			session.setAttribute("phone", null);
			session.setAttribute("password", null);
		}
		
		return "/user/findPassword";

		} 



// 회원가입
@RequestMapping(value="/addmember",method=RequestMethod.GET)
public String addCustomerView(CustomerVO vo){
	return "/user/addmember";
}

@RequestMapping(value="/addmember",method=RequestMethod.POST)
public String addCustomerproc(CustomerVO vo,CustomerDAO customerDAO){
	 customerService.setCustomer(vo);
	 return "/user/login";
}

// 정보 수정
@RequestMapping(value="/update",method=RequestMethod.GET)
public String updateCustomerView( CustomerVO vo){
	return "/user/update";
}

@RequestMapping(value="/update",method=RequestMethod.POST)
public String updateCustomerproc(@ModelAttribute("customer")CustomerVO vo,
		CustomerDAO customerDAO){
	 customerService.updateCustomer(vo);
	
	 return "redirect:/movie/mypage"; 
}

// 회원 탈퇴
@RequestMapping(value="/delete",method=RequestMethod.GET)
public String deleteCustomerView(HttpSession session,CustomerVO vo,Model model){
	vo=(CustomerVO)session.getAttribute("customer");
	vo=customerService.mypageCustomer(vo);//db에 바뀐내용 가져오기
	session.setAttribute("customer", vo);
	System.out.println("삭제:"+vo);
	
	
	return "/user/deleteForm";
}

@RequestMapping(value="/delete",method=RequestMethod.POST)
public String deleteMemberproc(CustomerVO vo
		,HttpSession session,RedirectAttributes rttr,Model model){
	
	
	CustomerVO customer=(CustomerVO)session.getAttribute("customer");
	
	
	String oldPass = customer.getPassword();
	 String newPass = vo.getPassword();
	     System.out.println(newPass);
	     System.out.println(oldPass);
	 if(!(oldPass.equals(newPass))) {
	  rttr.addFlashAttribute("msg", false);
	  return "redirect:/movie/delete";
	 }
	
	customerService.deleteCustomer(vo);
	
	
	
	 return "redirect:/movie/logout";
}




// 마이페이지
@RequestMapping(/*value=*/"/mypage"/*,method=RequestMethod.GET*/)
public String mypageView(CustomerVO vo,Model model,HttpSession session){
	vo = (CustomerVO)session.getAttribute("customer");
	if(vo==null){
		return "/user/login";
	}
	vo.setId(((CustomerVO)session.getAttribute("customer")).getId());
	model.addAttribute("customer", customerService.mypageCustomer(vo));
	
	
	model.addAttribute("booking", customerService.booking(vo));// 예매 목록
	//System.out.println(model);
	
	
	
	return "/user/mypage";
	
	
}




}

