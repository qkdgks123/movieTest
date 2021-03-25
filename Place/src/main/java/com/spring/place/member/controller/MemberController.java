package com.spring.place.member.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.place.member.vo.MemberVO;

public interface MemberController {
	
	// * 회원 목록
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// * 회원 등록
	public ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// * 회원 수정
	public ModelAndView modMember(@ModelAttribute("info") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// * 회원 삭제
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// * 로그인
	public ModelAndView login(@ModelAttribute("member") MemberVO member, 
				// RedirectAttributes : redirect로 이동 시 객체를 전달하는 객체
				RedirectAttributes rAttr,
				HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// * 로그아웃
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
}