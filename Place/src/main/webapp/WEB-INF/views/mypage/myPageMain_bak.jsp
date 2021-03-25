<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("myPageMain.jsp\n===============================");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

</head>
<body>
<h1>
	<A href="http://localhost:8282/studyproj/mypage/myPageMain.do">최근주문내역		|</A>
	<A href="http://localhost:8282/studyproj/member/modMemberForm.do?action=update">회원정보수정</A>
</h1>
	<br>
 
 <table class="list_view">
 	<tbody align=center >
 		<tr style="background:#33ff00" >
 			<td>플레이스 명</td>
			<td>예약문의 일시</td>
			<td>공간 유형</td>
			<td>상세 주소</td>
			<td></td>
 		</tr>
   <c:choose>
         <c:when test="${ empty myOrderList  }">
		  <tr>
		    <td colspan=2 class="fixed">
				  <strong>주문한 상품이 없습니다.</strong>
		    </td>
		  </tr>
        </c:when>
  		<c:otherwise>
	      <c:forEach var="item" items="${myOrderList }"  varStatus="i">

	       	<tr>
		    <td class="fixed">
				 ${item.goods_name }
		    </td>
		    
		    <td  class="fixed">
				${item.register_date }
		    </td>
			<td class="fixed" >
				  ${goodsTypeList[i.index] }
		    </td>
		    
		    <td  class="fixed">
				  ${goodsDetailList[i.index].goods_location }
		    </td>
		     <td  class="fixed" width=100>
				  <button class="fixed" onclick="window.open('${contextPath}/mypage/myOrderDetail.do?order_seq_num=${item.order_seq_num }&goods_id=${item.goods_id }','window_name','width=600,height=400,location=no,status=no,scrollbars=yes');">상세보기</button>
		    </td>
		    </tr>
	       </c:forEach>
	       
	        
	       
	       </c:otherwise>
	      
	     
	  </c:choose>
                
  </table>
  
</body>
</html>
