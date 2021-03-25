<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("orderGoodsForm.jsp\n===============================");
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="goods" value="${goodsMap.goodsVO}" />

<!-- 주문자 휴대폰 번호 -->
<c:set var="orderer_hp" value="" />

<!-- 최종 결제 금액 -->
<c:set var="final_total_order_price" value="0" />

<!-- 총주문 금액 -->
<c:set var="total_order_price" value="0" />

<!-- 총 상품수 -->
<c:set var="total_order_goods_qty" value="0" />

<!-- 총할인금액 -->
<c:set var="total_discount_price" value="0" />

<!-- 총 배송비 -->
<c:set var="total_delivery_price" value="0" />
<html>
<head>

<meta charset="UTF-8">
<title>장소 예약 폼</title>

<script>

	var goods_id = "";
	var goods_name = "";
	var goods_fileName = "";
	var goods_people_num = "";

	var each_goods_price;
	var orderer_name;
	var receiver_name;
	
	var tel1;
	var tel2;
	var tel3;

	var receiver_tel_num;

	function fn_show_order_detail() {
		
		alert("확인 0");
		
		goods_id = "";
		goods_name = "";
		
		var frm = document.form_order;
		var h_goods_id = frm.h_goods_id;
		var h_goods_name = frm.h_goods_name;
		var h_goods_fileName = frm.h_goods_fileName;
		var h_goods_people_num = frm.h_goods_people_num;
		
		var h_orderer_name = document.getElementById("h_orderer_name");
		var i_receiver_name = document.getElementById("receiver_name");
		
		// h_goods_id
		if (h_goods_id.length < 2 || h_goods_id.length == null) {
			goods_id += h_goods_id.value;
		} else {
			for (var i = 0; i < h_goods_id.length; i++) {
				goods_id += h_goods_id[i].value + "<br>";
			}
		}
		
		// h_goods_name
		if (h_goods_name.length < 2 || h_goods_name.length == null) {
			goods_name += h_goods_name.value;
		} else {
			for (var i = 0; i < h_goods_name.length; i++) {
				goods_name += h_goods_name[i].value + "<br>";
			}
		}
		
		// h_goods_fileName
		if (h_goods_fileName.length < 2 || h_goods_fileName.length == null) {
			goods_fileName += h_goods_fileName.value;
		} else {
			for (var i = 0; i < h_goods_fileName.length; i++) {
				goods_fileName += h_goods_fileName[i].value + "<br>";
			}
		}
		
		goods_people_num += h_goods_people_num.value;
		
		var i_tel1 = document.getElementById("tel1");
		var i_tel2 = document.getElementById("tel2");
		var i_tel3 = document.getElementById("tel3");
		
		orderer_name = h_orderer_name.value;
		receiver_name = i_receiver_name.value;
		
		tel1 = i_tel1.value;
		tel2 = i_tel2.value;
		tel3 = i_tel3.value;
		
		receiver_tel_num = tel1 + "-" + tel2 + "-" + tel3;
	}

</script>
</head>
<body>
	<H1>* 기본 정보</H1>
	<form name="form_order" method="post" action="${contextPath}/order/addNewOrder.do" enctype="multipart/form-data">
		<table class="list_view">
			<tbody align=center>
				<tr style="background: #33ff00">
					<td colspan=2 class="fixed">플레이스 명</td>
					<td>대관시간</td>
					<td>공간유형</td>
					<td>수용인원</td>
					<td>문의처</td>
					<td>상세주소</td>
					<td>대관금액(1시간당)</td>
				</tr>
				<tr>
					<c:forEach var="item" items="${myOrderList}">
						<td>
							<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
								<img width="75" alt="이미지준비중입니다." src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
								<input type="hidden" id="h_goods_id" name="h_goods_id" value="${item.goods_id }" />
								<input type="hidden" id="h_goods_fileName" name="h_goods_fileName" value="${item.goods_fileName }" />
		               		</a>
		               		
		               	</td>
						<td>
							<h2>
								<a href="${pageContext.request.contextPath}/goods/goods.do?command=goods_detail&goods_id=${item.goods_id }">${item.goods_name}</a>
								<input type="hidden" id="h_goods_name" name="h_goods_name" value="${item.goods_name}" />
							</h2>
						</td>
						<td>
							${item.goods_operation_time}
						</td>
						<td>
							<%-- ${item.goods_type_id} --%>
							<%-- ${goods.goods_type_name} --%>
							${item.goods_type_name}
							<input type="hidden" name="goods_type_id" value="${item.goods_type_id}">
						</td>
						<td>
							${item.goods_people_num}명
						</td>
						<td>
							${item.goods_tel}
						</td>
						<td>
							${item.goods_location}
						</td>
		               	<td>
		               		${item.goods_price}원
		               	</td>
					</c:forEach>
				</tr>
			</tbody>
		</table>
		
		<br><br>
		
		<H1>* 예약 정보</H1>
		<table class="list_view">
			<tbody align=center>
				<tr style="background: #33ff00">
					<td>날짜</td>
					<td>시간</td>
					<td>인원선택</td>
					<td>이름</td>
					<td>전화번호</td>
					<td>이메일</td>
					<td>사용용도</td>
					<td>문의내용</td>
				</tr>
				<tr>
					<%-- <td>${goods.reservation_date}</td> --%>
					<td><input type="date" name="reservation_date"></td>
					<td>
						<select name="reservation_from_time">
												<option value='07:00' selected='selected'>7시 00분</option>
												<option value='07:30'>7시 30분</option>
												<option value='08:00'>8시 00분</option>
												<option value='08:30'>8시 30분</option>
												<option value='09:00'>9시 00분</option>
												<option value='09:30'>9시 30분</option>
												<option value='10:00'>10시 00분</option>
												<option value='10:30'>10시 30분</option>
												<option value='11:00'>11시 00분</option>
												<option value='11:30'>11시 30분</option>
												<option value='12:00'>12시 00분</option>
												<option value='12:30'>12시 30분</option>
												<option value='13:00'>13시 00분</option>
												<option value='13:30'>13시 30분</option>
												<option value='14:00'>14시 00분</option>
												<option value='14:30'>14시 30분</option>
												<option value='15:00'>15시 00분</option>
												<option value='15:30'>15시 30분</option>
												<option value='16:00'>16시 00분</option>
												<option value='16:30'>16시 30분</option>
												<option value='17:00'>17시 00분</option>
												<option value='17:30'>17시 30분</option>
												<option value='18:00'>18시 00분</option>
												<option value='18:30'>18시 30분</option>
												<option value='19:00'>19시 00분</option>
												<option value='19:30'>19시 30분</option>
												<option value='20:00'>20시 00분</option>
												<option value='20:30'>20시 30분</option>
												<option value='21:00'>21시 00분</option>
												<option value='21:30'>21시 30분</option>
												<option value='22:00'>22시 00분</option>
												<option value='22:30'>22시 30분</option>
												<option value='23:00'>23시 00분</option>

                        </select>
                        <select name="reservation_to_time">
												<option value='07:00' selected='selected'>7시 00분</option>
												<option value='07:30'>7시 30분</option>
												<option value='08:00'>8시 00분</option>
												<option value='08:30'>8시 30분</option>
												<option value='09:00'>9시 00분</option>
												<option value='09:30'>9시 30분</option>
												<option value='10:00'>10시 00분</option>
												<option value='10:30'>10시 30분</option>
												<option value='11:00'>11시 00분</option>
												<option value='11:30'>11시 30분</option>
												<option value='12:00'>12시 00분</option>
												<option value='12:30'>12시 30분</option>
												<option value='13:00'>13시 00분</option>
												<option value='13:30'>13시 30분</option>
												<option value='14:00'>14시 00분</option>
												<option value='14:30'>14시 30분</option>
												<option value='15:00'>15시 00분</option>
												<option value='15:30'>15시 30분</option>
												<option value='16:00'>16시 00분</option>
												<option value='16:30'>16시 30분</option>
												<option value='17:00'>17시 00분</option>
												<option value='17:30'>17시 30분</option>
												<option value='18:00'>18시 00분</option>
												<option value='18:30'>18시 30분</option>
												<option value='19:00'>19시 00분</option>
												<option value='19:30'>19시 30분</option>
												<option value='20:00'>20시 00분</option>
												<option value='20:30'>20시 30분</option>
												<option value='21:00'>21시 00분</option>
												<option value='21:30'>21시 30분</option>
												<option value='22:00'>22시 00분</option>
												<option value='22:30'>22시 30분</option>
												<option value='23:00'>23시 00분</option>

                        </select>
					</td>
					<td>                        
						<select id="order_people_num" name="order_people_num">
							<option value="1" selected>1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						</select>
					</td>
					<td>${orderer.member_name}</td>
					<td><input type="text" name="tel1" value="${orderer.tel1}">
						-<input type="text" name="tel2" value="${orderer.tel2}">
						-<input type="text" name="tel3" value="${orderer.tel3}">
					</td>
					<td>${orderer.email1}@${orderer.email2}</td>
					<td></td>
					<td></td>
				</tr>
				<tr style="background: #33ff00">
					<td colspan=8 class="fixed">서비스 동의</td>
				</tr>
				<tr>
					<td>
						<span class="hostTitle">서비스 동의</span>
                        <label class="agreementCheck">
                            <input type="checkbox" class="agreementCheck checkStyle" name="rsvp_legal"
                                    data-parsley-required="true"
                                    data-parsley-class-handler=".agreementCheck"
                                    data-parsley-errors-container=".agreementErrorWrap"
                                    data-parsley-error-message="약관에 동의해 주세요" />약관 동의
                        </label>
                        <p><br><br>
                        <textarea class="agreementText" disabled>제1장 총칙</textarea>
					</td>
				</tr>
			</tbody>
		</table>
		
		<!-- <input type="submit" value="대관 문의하기" /> -->
		<script src="http://lite.payapp.kr/public/api/payapp-lite.js"></script>
		<script>
			PayApp.setDefault('userid',     'payapptest');
			PayApp.setDefault('shopname',   '중앙_2팀');
			
			function myPay() {
			    PayApp.setParam('goodname', '플레이스11');
			    PayApp.setParam('price',    '1000');
			    PayApp.setParam('returnurl', 'http://localhost:8282/studyproj/mypage/.do');
			    PayApp.call();
			}
		</script>
		
		<a href="#" onclick="myPay();"></a>

	</form>
</body>
</html>