package com.springbook.biz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.entry.MemberVO;
import com.springbook.biz.model.RequestVo;
import com.springbook.biz.service.MemberDAOJPA;
import com.springbook.biz.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;

	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(MemberVO mvo, HttpSession session) {
		
		MemberVO member = memberService.getMember(mvo);
		if (member != null) {
              session.setAttribute("member", member);
			return "main";
		} else
			return "login1";
	}
	
	@RequestMapping(value = "/adminlogin.do", method = RequestMethod.POST)
	public String adminlogin(MemberVO mvo, HttpSession session) {
		
		MemberVO member = memberService.getAdminMember(mvo);
		
		if (member != null) {
              session.setAttribute("member", member);
			return "redirect:/getEstimationList.do";
		} else
			return "Login2";
	}
	

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "welcome";
	}

	@RequestMapping(value = "/mainConfirm.do", method = RequestMethod.POST)
	public String mainConfirm(RequestVo reqVo, Model model) {
		System.out.println(reqVo);
		List<RequestVo> reqList = new ArrayList();
		reqList.add(reqVo);
		model.addAttribute("reqVolist", reqList);
		return "redirect:/progressView.do";
	}

	@RequestMapping(value = "/progressView.do", method = RequestMethod.GET)
	public void progressView(Model model) {
		List<RequestVo> reqList = new ArrayList();
		RequestVo vo = new RequestVo();
		vo.setPname("product1");
		vo.setSerial("0001");
		vo.setEmail("hong@naver.com");
		reqList.add(vo);
		model.addAttribute("reqVolist", reqList);
	}
}
