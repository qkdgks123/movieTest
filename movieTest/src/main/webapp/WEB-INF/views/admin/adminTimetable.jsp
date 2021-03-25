<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="timetablelist" value="${timetablelist}" scope="request"/>
<c:set var="total_timetable" value="${total_timetable}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.btn-info-addMovie {
   color: #1DDB16;
   background-color: #B7F0B1;
   border: 1px solid #22741C;
}

.btn-info-backPage {
   color: #353535;
   background-color: #BDBDBD;
   border: 1px solid #000000;
}

.btn-info-addMovie:hover {
   cursor: pointer;
   color: white;
   background-color: #22741C;
}

.btn-info-backPage:hover {
   cursor: pointer;
   color: white;
   background-color: #000000;
}
</style>
<!-- CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- JS -->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script>
   function allCheck() {
      if ($('#chkAll').prop("checked")) {
         $("input[name=chk]").prop("checked", true);
      } else {
         $("input[name=chk]").prop("checked", false);
      }
   }

   function delchk() {
      var delchk = [];
      $('#chk:checked').each(function() {//each:반복문
         delchk.push($(this).val());//delchk에다가 $(this).value(=$("#chk:checked"))를 넣는다
      });

      if (delchk.length == 0) {
         alert('선택하신 상영시간표가 없습니다.');
         return false;
      } else {
         var loc = "/movie/TimetableDelete?";
         var qur = '';

         if (confirm('선택하신 상영시간표 ' + delchk.length + '개의 항목을 삭제하시겠습니까?')){
            for (var i = 0; i < delchk.length; i++) {
               if (i == (delchk.length - 1)) {
                  qur += 'timetable_code=' + delchk[i];
               } else {
                  qur += 'timetable_code=' + delchk[i] + '&';
               }
            }
            loc = loc + qur;
            location.href = loc;
         }
      }
   }

   function delTimetable($timetable_code, $screening_date, $theater_area, $theater_name, $screen_name, $m_name, $start_time, $end_time) {
      var timetable_code = $timetable_code;
      var screening_date = $screening_date;
      var theater_area = $theater_area;
      var theater_name = $theater_name;
      var screen_name = $screen_name;
      var m_name = $m_name;
      var start_time = $start_time;
      var end_time = $end_time;
      

      if (confirm('날짜:[' + screening_date + ']\n지역:[' + theater_area + ']\n영화관:['+ theater_name +']\n상영관:['+ screen_name +']\n영화:['+ m_name +']\n상영시간:['+ start_time +'~'+ end_time +']을(를) 삭제하시겠습니까?')) {
         location.href = "/movie/TimetableDelete?timetable_code=" + timetable_code;
      } 
   }
</script>
</head>
<body>

   <jsp:include page="../include/header.jsp" />
   <div class="jumbotron">
      <div class="container">
         <h1>상영시관표 관리</h1>
      </div>
   </div>
	<!-- content -->
	<div class="wrapper"
		style="width: 100%; max-width: 1440px; margin: 0 auto;">
   <!-- 출력리스트 헤드부분 -->
   <div style="padding-top: 50px;">
      <div style="float:right">전체 상영시간표수: ${total_timetable}개</div>
      <table class="table">
         <tr>
            <th style="text-align: center; vertical-align: middle">
               <input type="checkbox" name="chkAll" id="chkAll" onclick="allCheck()">
            </th>
            <th style="width: 7%; text-align: center; vertical-align: middle">지역</th>
            <th style="text-align: center; vertical-align: middle">영화관</th>
            <th style="width: 7%; text-align: center; vertical-align: middle">상영관</th>
            <th style="width: 15%; text-align: center; vertical-align: middle">날짜</th>
            <th style="width: 10%; text-align: center; vertical-align: middle">영화</th>
            <th style="text-align: center; vertical-align: middle">영화 포스터</th>
            <th style="width: 15%; text-align: center; vertical-align: middle">상영시간</th>
            <th style="width: 5%; text-align: center; vertical-align: middle">비고<br>(삭제)</th>
         </tr>
         
         <c:if test="${fn:length(timetablelist) ne 0}">
            <c:forEach var="i" begin="0" end="${fn:length(timetablelist)-1 }">
            <tr>
               <th style="text-align: center; vertical-align: middle">
                  <input type="checkbox" name="chk" id="chk" value="${timetablelist[i].timetable_code}">
               </th>
               
               <th style="text-align: center; vertical-align: middle">${timetablelist[i].theater_area}</th>

               <th style="text-align: center; vertical-align: middle">${timetablelist[i].theater_name}</th>
               <th style="text-align: center; vertical-align: middle">${timetablelist[i].screen_name}</th>
               <fmt:formatDate var="screening_date" pattern="yyyy-MM-dd" value="${timetablelist[i].screening_date}"/>
               <th style="text-align: center; vertical-align: middle">${screening_date}</th>
               <th style="text-align: center; vertical-align: middle">${timetablelist[i].m_name}</th>
               <th style="width: 10%;"><img src='/movie/image/${timetablelist[i].m_poster}' alt="Lights" style="width:100%" class="img-thumbnail" ></th>
               <fmt:formatDate var="start_time" pattern="yyyy-MM-dd HH:mm" value="${timetablelist[i].start_time}"/>
               <fmt:formatDate var="end_time" pattern="yyyy-MM-dd HH:mm" value="${timetablelist[i].end_time}"/>
               <th style="text-align: center; vertical-align: middle">${start_time}~${end_time}</th>
               <th style="text-align: center; vertical-align: middle">
                  <button type="button" class="btn btn-info-delete" onclick="delTimetable(
                     '${timetablelist[i].timetable_code}', '${timetablelist[i].screening_date}',
                     '${timetablelist[i].theater_area}', '${timetablelist[i].theater_name}',
                     '${timetablelist[i].screen_name}', '${timetablelist[i].m_name}',
                     '${timetablelist[i].start_time}', '${timetablelist[i].end_time}')">삭제</button>
               </th>
            </tr>
            </c:forEach>
         </c:if>

      </table>
   </div>
   <div class="text-right">
   <a href="TimetableInsertForm?num=0">
      <button type="button" name="add" id="add" class="btn btn-success">상영시간표 추가</button>
   </a>
   <button type="button" name="delete" id="delete" class="btn btn-danger" onclick="delchk()">상영시간표 삭제</button>
   <a href="/movie/">
      <button type="button" name="backPage" id="backPage" class="btn btn-dark">되돌아가기</button>
   </a>
   </div>
   </div>
   <jsp:include page="../include/footer.jsp" />
</body>
</html>

