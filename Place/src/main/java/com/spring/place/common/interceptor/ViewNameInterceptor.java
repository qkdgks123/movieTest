// 인터셉터 수행 시 preHandle() 메서드로 전달된 request에서 추가한 getViewName() 메서드를 이용해 뷰이름을 가져온 후 request에 바인딩
package com.spring.place.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 	* 인터셉터
 	: Controller로 전달되는 request를 가로채서 viewName을 만들어서 Controller로 보냄
 	: 브라우저 요청 시 요청 메서드 호출 전후에 개발자가 원하는 기능을 수행함
	: 필터와 기능이 유사하지만 필터보다 좀 더 자유롭게 위치를 변경해서 기능을 수행함
	: 쿠키(Cookie) 제어, 파일 업로드 등의 작업을 수행함
*/
public class ViewNameInterceptor implements HandlerInterceptor {

	// request가 Controller로 가기 전에 처리(컨트롤러 실행 전 호출)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try {
			// getViewName() 메서드를 이용해 브라우저의 요청명에서 뷰이름을 가져옴
			String viewName = getViewName(request);
			
			// 뷰이름을 request에 바인딩
			request.setAttribute("viewName", viewName);
			
			System.out.println(request.getAttribute("viewName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	// 컨트롤러 실행 후 DispatcherServlet이 뷰로 보내기 전에 호출
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	// 뷰까지 수행하고 나서 호출
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	// request 객체로부터 viewName을 만드는 메소드; 요청명에서 뷰이름을 반환
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

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
		}
		return fileName;
	}
}