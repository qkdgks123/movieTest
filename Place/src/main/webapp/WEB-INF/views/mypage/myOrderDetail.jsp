<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("myOrderDetail.jsp\n===============================");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script>
	function cancel(){
		
		alert('예약이 삭제되었습니다');
		location.href="http://localhost:8282/studyproj/mypage/cancelMyOrder.do?order_seq_num=${myOrderInfo.order_seq_num}";
		
		// 팝업창을 닫으면서 부모창을 새로고침
		opener.parent.location.reload();
		window.close();
	}

</script>
</head>
<body>
	<h1>1. 주문 상세정보</h1>
	<div class="popupContent">
		<ul class="popupList">
		<li class="name"><span class="popupText">이름</span>
				<p>${orderer.member_name}</p></li>
			<li class="phone"><span class="popupText">전화번호</span>
				<p>${myOrderInfo.tel1}-${myOrderInfo.tel2}-${myOrderInfo.tel3}</p></li>
			<li class="email"><span class="popupText">이메일</span>
				<p>${orderer.email1}@${orderer.email2}</p></li>
			<li class="purpose"><span class="popupText">사용용도</span>
				<p>${myOrderInfo.goods_type_id}</p></li>
			<li class="date"><span class="popupText">예약날짜</span>
				<p>${myOrderInfo.reservation_date}</p></li>
		
			
	

			<li class="buttonArea"><button onclick="self.close();">닫기</button></li>

			<li class="buttonArea"><button onclick="cancel()">예약취소</button></li>
			
		</ul>
	</div>
</body>

