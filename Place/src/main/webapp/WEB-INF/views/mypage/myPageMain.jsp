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
   <div id="app">       
      <div class="topSkin web">
          <div class="skin" style="background-image: url(${contextPath}/resources/image/test/topSkin2.png)"></div>
          <h2 class="skinTitle">마이 페이지</h2>
      </div>
      <section class="content">
      
      <h3 class="subTitle">
      
      <a href="${contextPath}/mypage/myPageMain.do">
       예약 문의 내역                               |
      </a>
       
       <a href="${contextPath}/member/modMemberForm.do?action=update">
       	회원정보수정
       </a>
       
       </h3>
   
          <form action="" method="POST" class="hostForm" enctype="multipart/form-data" data-parsley-validate="true" data-parsley-excluded="[type=submit], [type=button], [type=reset]">
            <div class="formBox">
               <table>
                  <tbody align=center >
                     <article class="placeHostName">
                            <div class="titleArea">
                                <tr>
                               <td>플레이스 명</td>
                              <td>예약문의 일시</td>
                              <td>공간 유형</td>
                              <td>상세 주소</td>
                            </tr>
                            </div>
                            <div class="editArea">
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
                                        <td class="fixed">
                                          ${item.register_date }
                                        </td>
                                       <td class="fixed" >
                                          ${goodsTypeList[i.index] }
                                        </td>
                                        <td  class="fixed">
                                          ${goodsDetailList[i.index].goods_location }
                                        </td>
                                         <td  class="fixed" width=100>
                                            <button class="fixed" onclick="window.open('${contextPath}/mypage/myOrderDetail.do?order_seq_num=${item.order_seq_num }&goods_id=${item.goods_id }','window_name','width=600,height=400,location=no,status=no,scrollbars=yes');return false; ">상세보기</button>                                         
                                        </td>
                                     </tr>
                                     </c:forEach>
                              </c:otherwise>
                           </c:choose>
                            </div>
                     </article>
                  </tbody>
               </table>
            </div> 
          </form>
      </section>
   </div>
</body>
</html>