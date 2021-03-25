package com.my.movie.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminIntercepter extends HandlerInterceptorAdapter{
	//��û�� ó��
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//���ǰ�ü ����
		HttpSession session = request.getSession();
		//session�� ������id�� null�̸�
		if(session.getAttribute("adminId") ==null){
			response.sendRedirect("/inclue/login");
			return false;//��û���� X
		//null�� �ƴϸ�
		}else{
			return true;//��û���� O
		}
	}
	//��û ó����
	public void postHandler(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception{
		super.postHandle(request, response, handler, modelAndView);
	}
}
