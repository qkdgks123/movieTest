<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- header -->
	<div class="jumbotron text-center mb-0">
		<h1>Seoul Cinema HOME</h1>
		<p>영화! 상상 그 이상의 감동</p>
	</div>
	
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark"> <a
		href="main.jsp" class="navbar-brand">Seoul Cinema</a> <!-- Toggle Button -->
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
		<ul class="navbar-nav">
			<li class="nav-item"><a href="nowmovie.jsp" class="nav-link">상영영화</a></li>
			<li class="nav-item"><a href="startBook.jsp" class="nav-link">예매하기</a></li>
			<li class="nav-item"><a href="future.jsp" class="nav-link">계봉예정</a></li>
			<li class="nav-item"><a href="login.jsp" class="nav-link">로그인</a></li>
			<li class="nav-item"><a href="mypage.jsp" class="nav-link">마이페이지</a></li>
			<li class="nav-item"><a href="addmovie.jsp" class="nav-link">영화등록</a></li>
			<li class="nav-item"><a href="managemember.jsp" class="nav-link">회원관리</a></li>
			<li class="nav-item"><a href="managecinema.jsp" class="nav-link">영화관 관리</a></li>
		</ul>
	</div>
	</nav>
</body>
</html>