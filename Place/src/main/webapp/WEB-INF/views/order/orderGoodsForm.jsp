<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   request.setCharacterEncoding("UTF-8");
   System.out.println("orderGoodsForm.jsp\n===============================");
   System.out.println(" *** request.getContextPath() 확인 -> " + request.getContextPath());
%>

<script src="http://lite.payapp.kr/public/api/payapp-lite.js"></script>

<script type="text/javascript">
   PayApp.setDefault('userid',     'payapptest');
   PayApp.setDefault('shopname',   'GoPlace');
   
   function myPay(this_form) {
       PayApp.setParam('goodname', document.getElementById('h_goods_name').value);
       PayApp.setParam('price',    document.getElementById('goods_price').value);
       //PayApp.setParam('price',    document.getElementById('goods_price').value);
/*        PayApp.setParam('returnurl', 'http://localhost:8282/studyproj/order/addNewOrder.do');
 */
       PayApp.call();
         this_form.submit();

       
   }
   function check(){
      var result = confirm("결제하시겠습니까?");
      if(result){
         myPay();
         return true;
      }else
        return false;
   }

</script>

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

   <div class="topSkin responsible">
      <div class="skin" style="background-image: url(${contextPath}/resources/image/test/topSkin2.png)"></div>
      <h2 style="font-weight: bold; display: block;" class="skinTitle">예약 문의 확인</h2>
   </div>
   
   <div id="app">
   
   <!-- <section class="content"> -->
      <form name="form_order" action="${contextPath}/order/addNewOrder.do" method="POST" enctype="multipart/form-data" data-parsley-validate="true">
         
<table align="center">
	<tr align="center">
		<td colspan="2" style="padding: 50px;" align="center">
		
      <c:forEach var="item" items="${myOrderList}">
         <!-- <article style="width:600px;" class="placeInfo"> 
         
            <span style="width:600px;margin: 15px;" class="infoArea">      
               <span style="width:400px;" class="slideImg">
           -->    
                  <img style="margin:0px;" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}" alt="이미지 준비 중입니다.">
                  <input type="hidden" id="h_goods_id" name="h_goods_id" value="${item.goods_id }" />
                  <input type="hidden" id="h_goods_fileName" name="h_goods_fileName" value="${item.goods_fileName }" />
                  <input type="hidden" id="h_goods_name" name="h_goods_name" value="${item.goods_name }" />
               
         <!--       
               </span>
            </span>
         </article> -->
         
		</td>
	</tr>
	<tr>
		<td style="padding-right: 50px;">
            <span class="infoArea">
               <h4 class="subTitle">기본 정보</h4><br><br>

               <ul class="infoList formList">
                  <li class="title">
                     <span class="formTitle">플레이스 명&nbsp;&nbsp;&nbsp;</span>
                     <div class="rightArea">
                        <p class="placeTitle">${item.goods_name}</p>
                     </div>
                  </li>
                  <li class="coronation">
                     <span class="formTitle">대관시간&nbsp;&nbsp;&nbsp;</span>
                     <div class="rightArea">
                        <p>${item.goods_operation_time}</p>
                     </div>
                  </li>
                  <li>
                     <span class="formTitle">공간유형&nbsp;&nbsp;&nbsp;</span>
                     <div class="rightArea">
                        <p>${item.goods_type_name}
                           <input type="hidden" name="goods_type_id" value="${item.goods_type_id}">
                        </p>
                     </div>
                  </li>
                  <li class="coronation">
                     <span class="formTitle">수용인원&nbsp;&nbsp;&nbsp;</span>
                     <div class="rightArea">
                        <p>${item.goods_people_num}명 까지</p>
                     </div>
                  </li>
                  <li class="coronation">
                     <span class="formTitle">문의처&nbsp;&nbsp;&nbsp;</span>
                     <div class="rightArea">
                        <p>${item.goods_tel}</p>
                     </div>
                  </li>
                  <li class="full">
                     <span class="formTitle">상세주소&nbsp;&nbsp;&nbsp;</span>
                     <div class="rightArea">
                        <p>${item.goods_location}</p>
                     </div>
                  </li>
                  <li class="price full">
                     <span class="formTitle"> 대관금액&nbsp;&nbsp;&nbsp;<br/>
                        <span style="font-size: 12px;">(1시간당)</span>
                     </span>
                     <div class="rightArea">
                        <p class="totalPrice">${item.goods_price}원</p>
                         <input type="hidden" id="goods_price" name="goods_price" value="${item.goods_price}" />
                        
                     </div>
                  </li>
               </ul>
            </span>
         </c:forEach>
	
		</td>
		<td>

         <!-- <article class="reservationInfo"> -->
         <span class="infoArea">
            <h4 class="subTitle">예약 정보</h4><br><br>
            <ul class="infoList formList">            
               <li>
                  <span class="formTitle">날짜&nbsp;&nbsp;&nbsp;</span>
                  <div class="rightArea">
                           <input type="date" name="reservation_date">
                        </div>
               </li>                        
                        
               <li>
                  <span class="formTitle">시간&nbsp;&nbsp;&nbsp;</span>
                  <div class="selectArea commonForm" id="selectArea">
                        <select class="selectMenu startTime" name="reservation_from_time" >
                            <option value='08:30' selected='selected'>8시 30분</option>
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
                        </select>
                        &nbsp;&nbsp;~&nbsp;&nbsp;
                        <select class="selectMenu endTime" name="reservation_to_time">
                            <option value='08:60' selected='selected'>8시 60분</option>
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
                        </select>
                        <div class="timeErrorWrap"></div>
                    </div>
               <input type="hidden" name="placeList[]" value="139"/>
            </li>
            <li class="people">
               <span class="formTitle">인원선택</span>
               <div class="rightArea">&nbsp;
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
                  </p>
               </div>
            </li>
            <li class="name">
               <span class="formTitle">이름&nbsp;&nbsp;&nbsp;</span>
               <div class="rightArea">
                  <p>${orderer.member_name}</p>
               </div>
            </li>
            <li>
               <span class="formTitle">전화번호&nbsp;&nbsp;&nbsp;</span>
               <div class="rightArea">
                  <p>
                     <input size="3px" type="text" name="tel1" value="${orderer.tel1}"/>
                      - <input size="3px" type="text" name="tel2" value="${orderer.tel2}"/>
                      - <input size="3px" type="text" name="tel3" value="${orderer.tel3}"/>
                  </p>
               </div>
            </li>
            <li>
               <span class="formTitle">이메일&nbsp;&nbsp;&nbsp;</span>
               <div class="rightArea">
                  <p>${orderer.email1}@${orderer.email2}</p>
               </div>
            </li>
            
            </ul>
         </span>
         <!-- </article> -->
       
       <br><br><br>
       
       </td>
     </tr>
    </table>
   
   <table align="center">
   		<tr align="center">
   			<td align="center">
   			
         <article class="btnArea">
          
          	<!-- 버튼 에서 인풋으로 바꾼 거라 확인 요망.. -->
    		<input type="submit" value="대관 문의하기" onclick="return check(this.form)">
         </article>
         
         	</td>
         </tr>
    </table>
            
            <br><br><br>
            
      </form>
   <!-- </section> -->
   
      </div>
</body>
</html>