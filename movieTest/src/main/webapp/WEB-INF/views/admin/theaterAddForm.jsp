<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>영화관 추가창</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
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
	var ischk = 0;//중복체크 여부
	
	function initChk(){
		ischk = 0;//영화관 이름 바꾸면 중복체크 여부 초기화
		//alert("중복 여부 초기화:"+ischk);
	}
	
	function chkTheater(){
		var theater_area = document.getElementById('theater_area').value;
		var theater_name = document.getElementById('theater_name').value;
		var param = 'theater_area='+theater_area+'&theater_name='+theater_name;

		$.ajax({
		    url:'/movie/TheaterChk', // 요청 할 주소
		    data:param,// 전송할 데이터
		    success:function(result) {
		    	if(result.length==0){
		    		alert("등록 가능한 영화관입니다.");
		    		ischk=1;
		    	}else{
		    		alert("등록된 영화관이 있습니다.");
		    		document.getElementById('theater_name').value="";
		    	}
		    },
		    error:function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		});
	}
	
	function registTheater() {
		if(ischk==0){
			alert("중복 체크하세요");
			return false;
		}else{
			var yN = confirm('영화관을 등록하시겠습니까?');
			if (yN) {
				alert('영화관이 등록되었습니다.');
				return true;
			}
		}
	}
</script>
</head>
<body>
	<%
		int num1 = (int) (Math.random() * 90) + 10;
		int num2 = (int) (Math.random() * 90) + 10;
		int num3 = (int) (Math.random() * 90) + 10;
	
		String t_code = "T" + num1 + num2 + num3;
	%>
	
	<jsp:include page="../include/header.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1>영화관 등록</h1>
		</div>
	</div>
	
	<div class="wrapper"
		style="width: 100%; max-width: 1440px; margin: 0 auto;">
		<div class="container">
		<form action="TheaterInsert" class="form-horizontal" method="post">
			<div class="form-group row">
				<label class="col-sm-2">영화관코드</label>
				<div class="col-sm-6">
					<input type="text" id="theater_code" name="theater_code" class="form-controlz" value="<%=t_code%>" readonly required>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">지역</label>
				<div class="col-sm-6">
					<select id="theater_area" name="theater_area" class="custom-select">
						<optgroup label="지역선택">지역선택</optgroup>
							<option value="서울">서울</option>
							<option value="경기">경기</option>
							<option value="인천">인천</option>
							<option value="강원">강원</option>
							<option value="대전/충청">대전/충청</option>
							<option value="대구">대구</option>
							<option value="부산/울산">부산/울산</option>
							<option value="경상">경상</option>
							<option value="광주/전라/제주">광주/전라/제주</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">영화관</label>
				<div class="col-sm-6">
					<input type="text" id="theater_name" name="theater_name" class="form-control" onchange="initChk()" required>
					<!-- id,name명이 함수명과 같으면 인식이 안됨 -->
					<button type="button" id="chkTheaterBtn" name="chkTheaterBtn" onclick="chkTheater()">영화관 중복체크</button>
				</div>
			</div>
			
			<input type="hidden" name="screen_name_A" value="A관">
			<input type="hidden" name="screen_name_B" value="B관">
			
			<input type="submit" class="btn btn-primary" onclick="return registTheater()" value="영화관 등록">
			<a href="/movie/managecinema" class="btn btn-info">되돌아가기</a>
		</form>
	</div>
	</div>
	<jsp:include page="../include/footer.jsp" />


</body>
</html>







