package com.spring.place.order.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.place.goods.vo.GoodsVO;
import com.spring.place.member.vo.MemberVO;
import com.spring.place.order.service.OrderService;
import com.spring.place.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl implements OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderVO orderVO;

	// goodsDetail창에서 장소 예약 창 가기 전까지 진행되는 과정
	@RequestMapping(value = "/orderEachGoods.do", method = RequestMethod.POST)
	public ModelAndView orderEachGoods(
			@ModelAttribute("orderVO") OrderVO _orderVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("order.controller - 장소 예약 폼");
		System.out.println("orderVO.getGoods_type_name() -> " + orderVO.getGoods_type_name());
		System.out.println("request.getParameter(\'goods_type_name\') -> " + request.getParameter("goods_type_name"));
		
		_orderVO.setGoods_type_name(request.getParameter("goods_type_name"));
		request.setCharacterEncoding("utf-8");
		
		// 주문 및 장바구니 기능 -> session으로 구현하는 경우 多 / request, mav, redirect는 페이지간 이동
		HttpSession session = request.getSession();
		session = request.getSession();

		Boolean isLogOn = (Boolean)session.getAttribute("isLogOn");
		String action = (String)session.getAttribute("action");
		
		// 로그인 여부 체크
		if(isLogOn == null || isLogOn == false) { // 1) 로그아웃 상태인 경우, 로그인 화면으로 이동
			
			session.setAttribute("orderInfo", _orderVO);
			session.setAttribute("action", "/order/orderEachGoods.do");
			
			return new ModelAndView("redirect:/member/loginForm.do");
			
		} else { // 2) 이전에 로그인 상태인 경우, 주문과정 진행
			
			// 2-1) 로그인 상태 + action이 orderEachGoods.do일 때
			if(action != null && action.equals("/order/orderEachGoods.do")) {
				
				// session에서 주문 정보 불러오고
				orderVO = (OrderVO)session.getAttribute("orderInfo");
				// action을 삭제(다른 action과의 충돌 방지)
				session.removeAttribute("action");
				
			// 2-2) 
			} else {
				
				// orderVO에 저장
				orderVO = _orderVO;
			}
		}

		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		System.out.println("mav 값 확인 -> " + mav);
		
		List myOrderList = new ArrayList<OrderVO>();		
		
		myOrderList.add(orderVO);

		MemberVO memberInfo = (MemberVO)session.getAttribute("memberInfo");
		
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberInfo);
		
		return mav;
	}

	@RequestMapping(value = "/orderAllCartGoods.do", method = RequestMethod.POST)
	public ModelAndView orderAllCartGoods(
			@RequestParam("cart_goods_qty") String[] cart_goods_qty,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session = request.getSession();
		Map cartMap = (Map)session.getAttribute("cartMap");
		List myOrderList = new ArrayList<OrderVO>();

		List<GoodsVO> myGoodsList = (List<GoodsVO>)cartMap.get("myGoodsList");
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");

		for(int i = 0; i < cart_goods_qty.length; i++) {
			String[] cart_goods = cart_goods_qty[i].split(":");
			for(int j = 0; j < myGoodsList.size(); j++) {
				GoodsVO goodsVO = myGoodsList.get(j);
				int goods_id = goodsVO.getGoods_id();
				if(goods_id == Integer.parseInt(cart_goods[0])) {
					
					OrderVO _orderVO = new OrderVO();
					/*
					 * String goods_title=goodsVO.getGoods_title(); int
					 * goods_sales_price=goodsVO.getGoods_sales_price(); String
					 * goods_fileName=goodsVO.getGoods_fileName();
					 */
					_orderVO.setGoods_id(goods_id);

					myOrderList.add(_orderVO);
					
					break;
				}
			}
		}
		
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberVO);
		
		return mav;
	}	

	@Override
	@RequestMapping(value="/addNewOrder.do", method = RequestMethod.POST)
	public ModelAndView addNewOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("order.controller - 장소 예약하기");
		
		request.setCharacterEncoding("utf-8");
		
		// 예약 정보를 저장하기 위한 orderMap을 생성
		Map<String, Object> orderMap = new HashMap<String, Object>();
		// parameter로 받기 위함
		Enumeration enu = request.getParameterNames();
		
		// 예약 창에서 전송된 글 정보를 Map에 key/value로 저장
		while(enu.hasMoreElements()) {
			
			String name = (String)enu.nextElement();
			String value = request.getParameter(name);
			
			orderMap.put(name, value);
			System.out.println("name -> " + name + ", value -> " + value);
		}
		
		HttpSession session = request.getSession();

		// DB에 저장하기 위한 데이터 세팅
		MemberVO memberVO = (MemberVO)session.getAttribute("orderer");
		System.out.println("memberVO.orderer -> " + memberVO.getMember_id());
		
		// 세션에 저장된 회원 정보로부터 회원ID를 가져옴
		String member_id = memberVO.getMember_id();
		
		// 회원ID를 orderMap에 저장
		orderMap.put("member_id", member_id);
		
		System.out.println(orderMap.get("reservation_date"));
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			// DB 저장하기
			// 글 정보가 저장된 ordereMap을 Service클래스의 addArticle() 메서드로 전달
			int ORDER_SEQ_NUM = orderService.addNewOrder(orderMap);
			System.out.println("addNewOrder의 ORDER_SEQ_NUM -> " + ORDER_SEQ_NUM); // 주문 번호
			System.out.println("reservation_from_time -> " + orderMap.get("reservation_from_time")); // 주문 번호
			System.out.println("페이지 출력 -> " + request.getContextPath() + "/mypage/myPageMain.do");
			
			// 새 글을 추가한 후 메시지를 전달
			/*
			 * message = "<script>"; message += " alert('새글을 추가했습니다.');"; message +=
			 * " location.href='" + request.getContextPath() + "/mypage/myPageMain.do';";
			 * message +=" </script>";
			 */
			
			
		    //resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED); // 결과 메시지를 전달하기 위함
		    return new ModelAndView("redirect:/mypage/myPageMain.do");
			
		} catch(Exception e) {
			
			// 오류 발생 시 오류 메시지를 전달
			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message += " location.href='" + request.getContextPath() + "/order/orderEachGoods.do'; ";
			message += " </script>";
			
			//resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
			
			return null;
		}
		
		//return null;

		/*
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("orderer");
		
		String member_id = memberVO.getMember_id();
		String orderer_name = memberVO.getMember_name();
		String orderer_hp = memberVO.getTel1() + "-" + memberVO.getTel2() + "-" + memberVO.getTel3();
		
		List<OrderVO> myOrderList = (List<OrderVO>)session.getAttribute("myOrderList");

		for(int i = 0; i < myOrderList.size(); i++){
			
			OrderVO orderVO = (OrderVO)myOrderList.get(i);
			orderVO.setMember_id(member_id);

			myOrderList.set(i, orderVO); //각 orderVO에 주문자 정보를 세팅한 후 다시 myOrderList에 저장
		}//end for

		orderService.addNewOrder(myOrderList);
		mav.addObject("myOrderInfo",receiverMap); //OrderVO로 주문결과 페이지에  주문자 정보를 표시
		mav.addObject("myOrderList", myOrderList);
		
		return mav;
		*/
	}
}