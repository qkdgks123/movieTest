package com.spring.place.member.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.place.member.service.MemberService;
import com.spring.place.member.vo.MemberVO;

// MemberControllerImpl클래스에 대해 id가 memberController인 빈을 생성
@Controller("memberController")
// aop 기능을 활성화
@EnableAspectJAutoProxy
public class MemberControllerImpl implements MemberController {
	
	// id가 memberService인 빈을 자동 주입
	@Autowired
	private MemberService memberService;
	
	// id가 memberVO인 빈을 자동 주입
	@Autowired
	MemberVO memberVO;

	@Override
	// 두 단계로 요청 시 바로 해당 메서드를 호출하도록 매핑
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("member.controller - 회원 목록");
		
		String viewName = getViewName(request);
		//System.out.println("viewName 출력 -> " + viewName);
		
		// service로부터 결과를 받아서 list로 만들고
		List membersList = memberService.listMembers();
		
		// viewName이 <definition>태그에 설정한 뷰이름과 일치
		ModelAndView mav = new ModelAndView(viewName);
		// list 객체를 view로 넘기기 위해 addObject("속성", list)에 저장
		mav.addObject("membersList", membersList);
		
		// ModelAndView 객체에 설정한 뷰이름을 타일즈 뷰리졸버로 반환 
		return mav;
	}

	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(
			// 회원 가입창에서 전송된 회원 정보를 바로 MemberVO 객체에 설정
			@ModelAttribute("member") MemberVO memberVO, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("member.controller - 회원 등록");
		System.out.println(memberVO);

		// 설정된 memberVO 객체를 SQL문으로 전달해 회원 등록		
		//return new ModelAndView("redirect:/member/listMembers.do");
		
		memberService.addMember(memberVO);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('회원 정보가 등록되었습니다.');");
		out.print("location.href='http://localhost:8282/studyproj/main/main.do'");
		out.print("</script>");
		
		return null;
	}

	@Override
	@RequestMapping(value="/member/modMember.do", method=RequestMethod.POST)
	public ModelAndView modMember(
			// @ModelAttribute -> 사용자가 입력한 값을 request로 받아와서 + 객체를 만드는 과정까지를 처리(so, HttpServletRequest생략해도 작동됨)
			@ModelAttribute("info") MemberVO memberVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("member.controller - 회원 수정");
		
		/* 회원 정보 수정시, 비밀번호 일치 유무 확인 */
		
		String id = memberVO.getMember_id();
		String pwd = memberVO.getMember_pw();
		
		System.out.println(memberVO.toString());
		String dbPwd = memberService.getMemberDBPwd(memberVO);
		// ID or 비밀번호 둘 중 한 개라도 일치하지 않으면 null값
		System.out.println("dbPwd값 확인 -> " + dbPwd);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 비밀번호가 없거나 입력한 비밀번호와 dbPwd가 다른 경우 되돌아 감
		if(dbPwd == null || !dbPwd.equals(pwd)) {
			
			out.print("<script>");
			out.print("alert('비밀번호가 다릅니다.');");
			out.print("history.back();");
			out.print("</script>");
			
		} else {
			
			memberService.updateMember(memberVO);
			out.print("<script>");
			out.print("alert('회원 정보가 수정되었습니다.');");
			out.print("location.href='http://localhost:8282/studyproj/mypage/myPageMain.do'");
			out.print("</script>");
		}	
		
		//return new ModelAndView("redirect:/member/listMembers.do");
		return null;
	}
	
	// form()메서드를 호출
	@RequestMapping(value= {"/member/*Form.do"}, method=RequestMethod.GET)
	public ModelAndView form(
			// 로그인 후 수행할 글쓰기 요청명을 action에 저장; 로그인 성공 후 바로 글쓰기 창으로 이동
			@RequestParam(value="action", required=false) String action,
			// 로그인 요청 시 매개변수 result가 전송되면 변수 result에 값을 저장; 최초로 로그인창을 요청할 때는 매개변수 result가 전송되지 않으므로 무시함
			@RequestParam(value="result", required=false) String result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		HttpSession session = request.getSession();

		if(action != null) {
			if(action.equals("update")) {
				
				System.out.println("member.controller - 회원 수정 폼");

				MemberVO member = (MemberVO)session.getAttribute("memberInfo");
				String id=member.getMember_id();

				
				MemberVO memberVO = memberService.getMemberInfo(id);
				
				mav.addObject("member", memberVO);
				mav.setViewName(viewName);

				return mav;
				
			} else {

				System.out.println("member.controller - 로그인 폼");

				// action의 값이 update가 아닐 때; 글쓰기창 요청명을 action속성으로 세션에 저장
				session.setAttribute("action", action);

				mav.addObject("result", result);
				mav.setViewName(viewName);
				System.out.println("viewName은 " + viewName);
				//System.out.println(session.getAttribute("action"));

				return mav;
			}
		} else if(action == null) {

			//System.out.println("member.controller - 회원 등록 폼");
			System.out.println("member.controller - 로그인/회원 등록 폼");

			mav.addObject("result", result);

			return mav;
		}
		
		return null;
	}
	
	@Override
	@RequestMapping("/member/removeMember.do")
	public ModelAndView removeMember(
			// 전송된 ID를 변수 id에 설정
			@RequestParam("id") String id, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("member.controller - 회원 삭제");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.print("<script>");
		out.print("var result=confirm('삭제하시겠습니까?');");
		out.print("alert(result);");
		out.print("if(result != true) { history.back(); }");
		out.print("</script>");
		
		int result = memberService.removeMember(id);
		System.out.println("result값 확인 -> " + result);

		if(result > 0) {
			out.print("<script>");
			out.print("location.href='http://localhost:8282/place/member/listMembers.do'");
			out.print("</script>");
		}
		
		return null;
	}
	
	// 로그인 처리
	@Override
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public ModelAndView login(
			// 로그인 창에서 전송된 ID과 비밀번호를 MemberVO 객체인 member에 저장
			@ModelAttribute("member") MemberVO member,
			// RedirectAttributes 클래스를 이용해 로그인 실패 시 다시 로그인창으로 redirect하여 실패 메시지를 전달; redirect로 이동 시 객체 전달
			RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("member.controller - 로그인");
		
		ModelAndView mav = new ModelAndView(getViewName(request));
		// Autowired된 memberVO객체를 사용; login() 메서드를 호출하면서 로그인 정보를 전달
		
		memberVO = memberService.login(member);
		System.out.println("확인" + memberVO.getMember_id());
		
		// 로그인 성공 시 조건문을 수행
		if(memberVO != null) {
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			
			// 세션에 회원 정보를 저장
			session.setAttribute("memberInfo", memberVO); // 회원정보를 session에 담기
			// 세션에 로그인 상태를 true로 설정
			session.setAttribute("isLogOn", true); // 로그인 여부 true를 session에 담기

			// 로그인 성공 시 세션에 저장된 action 값을 가져옴
			String action = (String) session.getAttribute("action");
			// 로그인 처리 후 action 정보는 session에서 삭제
			
			session.removeAttribute("action");
			if(action != null) { // action값이 null이 아니면 action값을 뷰이름으로 지정해 글쓰기 창으로 이동
				
				// redirect:/member/listMembers.do
				mav.setViewName("redirect:" + action);
			} else {
				
				// memberVO로 반환된 값이 있으면 세션을 이용해 로그인 상태를 true로 함
				mav.setViewName("redirect:/main/main.do");		
			}
		} else {
			System.out.println("로그인 실패");

			// 로그인 실패 시 실패 메시지를 로그인창으로 전달
			rAttr.addAttribute("result", "loginFailed");
			
			// 로그인 실패 시 다시 로그인창으로 redirect함
			mav.setViewName("redirect:/main/main.do");
		}
		
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("member.controller - 로그아웃");
		
		// 세션 얻기
		HttpSession session = request.getSession();
		
		// 로그아웃 요청 시 세션에 저장된 로그인 정보와 회원 정보를 삭제; removeAttribute() 세션에서 정보 제거
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main/main.do");
		
		return mav;
	}
	
	// request 객체로부터 viewName을 만드는 메소드
	private String getViewName(HttpServletRequest request) throws Exception {
		
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			// action-servlet.xml의 "/WEB-INF/views/" 부분은 그대로 두고 이 부분을 수정
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
}