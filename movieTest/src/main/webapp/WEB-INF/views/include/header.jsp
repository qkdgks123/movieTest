<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		href="/movie/" class="navbar-brand">Seoul Cinema</a> <!-- Toggle Button -->
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
		<ul class="navbar-nav">
			<!-- 사용자 메뉴 -->
			<li class="nav-item"><a href="/movie/now" class="nav-link">상영영화</a></li>
			<li class="nav-item"><a href="/movie/startBook" class="nav-link">예매하기</a></li>
			<li class="nav-item"><a href="/movie/future" class="nav-link">개봉예정</a></li>
			<!-- <li class="nav-item"><a href="/movie/mypage" class="nav-link">마이페이지</a></li> -->

			<!-- 관리자용 -->
			<c:choose>
				<c:when test="${sessionScope.adminId != null }">
					<li class="nav-item"><a href="/movie/getMovieList"
						class="nav-link">영화등록</a></li>
					<li class="nav-item"><a href="/movie/managecinema"
						class="nav-link">영화관 관리</a></li>
					<li class="nav-item"><a href="/movie/managemember"
						class="nav-link">회원관리</a></li>
					<li class="nav-item"><a href="/movie/manageTimetable"
						class="nav-link">상영시간표 관리</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a href="/movie/mypage" class="nav-link">마이페이지</a></li>
				</c:otherwise>
			</c:choose>

			<!-- 관리자 로그인, 로그아웃 -->
			<c:choose>
				<c:when test="${sessionScope.userId == null }">
					<c:choose>
						<c:when test="${customer == null}">
							<li class="nav-item"><a href="/movie/login" class="nav-link">로그인</a></li>
							<li class="nav-item"><a href="/movie/adminlogin"
								class="nav-link">관리자 로그인</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a href="/movie/logout"
								class="nav-link">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a href="/movie/adminlogout" class="nav-link">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	</nav>
</body>
</html>