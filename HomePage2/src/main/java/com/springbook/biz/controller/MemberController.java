package com.springbook.biz.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.entry.MemberVO;
import com.springbook.biz.service.MemberService;

@Controller
@SessionAttributes("member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/addMember.do", method = RequestMethod.GET)
	public String addMemberForm() throws IOException {
		return "addMember";
	}

	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	public String addMember(MemberVO mvo) throws IOException {
		memberService.addMember(mvo);
		return "login";
	}

	@RequestMapping(value = "/updateMember.do", method = RequestMethod.GET)
	public String updateMemberForm(MemberVO mvo, Model model, HttpSession session) {
		try {
			if (session.getAttribute("member") == null)
				return "redirect:/login.do";
			mvo = memberService.getMember((MemberVO) session.getAttribute("member"));
			MemberVO member = memberService.getMember(mvo);
			model.addAttribute("member", mvo);
		} catch (Exception e) {
			return "redirect:/login.do";
		}
		return "updateMember";
	}

	@RequestMapping(value = "/updateMember.do", method = RequestMethod.POST)
	public String updateMemberproc(@ModelAttribute("member") MemberVO mvo, HttpSession session) {
		memberService.updateMember(mvo);
		MemberVO member = memberService.getMember(mvo);
		session.setAttribute("memberId", member.getId());
		session.setAttribute("memberName", member.getName());
		session.setAttribute("memberPhone", member.getPhone());
		session.setAttribute("memberMail", member.getMail());

		return "main";
	}

	@RequestMapping(value = "/checkIdDoubleOrNot.do", method = RequestMethod.GET)
	public String checkIdDoubleOrNotproc(@RequestParam(value = "id") String id,
			@RequestParam(value = "checkidID") String checkidID, MemberVO mvo, HttpSession session) {
		System.out.println("ID CHECK");
		System.out.println(id);
		System.out.println(checkidID);

		MemberVO member = memberService.checkIdDoubleOrNotproc(mvo);
		session.setAttribute("memberId", member.getId());

		if (member.getPassword() == null) {
			checkidID = "1";
			return "addMember2";
		} else
			return "redirect:/againIdCheck.do";
	}

	@RequestMapping(value = "/againIdCheck.do", method = RequestMethod.GET)
	public String againIdCheck() {
		return "againIdCheck";
	}

	@RequestMapping("/deleteMember.do")
	public String deleteMember(MemberVO mvo, Model model, HttpSession session) {
		mvo.setId(((MemberVO) session.getAttribute("member")).getId());
		memberService.deleteMember(mvo);
		return "welcome";
	}

	@RequestMapping("/getMember.do")
	public String getBoard(MemberVO mvo, Model model) {
		model.addAttribute("member", memberService.getMember(mvo)); // Model
		return "home"; // View
	}

	@RequestMapping("/findingMember.do")
	public String findingMemberForm() {
		return "FindingMember";
	}

	@RequestMapping(value = "/findingId.do", method = RequestMethod.POST)
	public String findingIdProcess(MemberVO mvo, Model model) {
		if (mvo.getName() == null | mvo.getPhone() == null) {
			return "redirect:/findingMember.do";
		}
		model.addAttribute("member", memberService.getMemberByNameAndPhone(mvo));
		return "FindingIdResult";
	}

	@RequestMapping(value = "/findingPwd.do", method = RequestMethod.POST)
	public String findingPwdProcess(MemberVO mvo, Model model) {
		if (mvo.getName() == null | mvo.getId() == null) {
			return "redirect:/findingMember.do";
		}
		model.addAttribute("member", memberService.getMemberByNameAndId(mvo));
		return "FindingPwdResult";
	}

	@RequestMapping(value = "/changePwd.do", method = RequestMethod.POST)
	public String changePwdProcess(MemberVO mvo, Model model) {
		memberService.changePwd(mvo);
		return "redirect:/login.do";
	}

}
