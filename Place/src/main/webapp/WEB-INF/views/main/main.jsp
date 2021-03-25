<%@ page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("request.getContextPath() 확인 -> " + request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>메인 페이지</title>
<style type="text/css">
	.searchname{
		font-size: 20px;
		font-weight: bold;
	}
	#placelocation{
		color: #ff4081; 
	}
</style>
</head>
<body>
	<div id="app">
	<section class="content" id="main">
	
    <!-- 상단영역 -->
	<article class="topBanner"> 
		<div class="topSkin" style="background-image: url(${contextPath}/resources/image/main/topSkin.png)"></div>
        <h2 class="bannerTitle">
            “모임하기 좋은 곳 찾았다!!” <br/>플레이스 GO
        </h2>
        <ul class="tagArea">
			<li>#세미나</li>
			<li>#소모임</li>
			<li>#송년회</li>
			<li>#스터디</li>
        </ul>

        <form name="frmSearch" action="${contextPath}/goods/searchGoods.do" method="get" class="searchForm">
            <div class="searchBar">
                <input type="text" class="search" name="searchWord" value="" placeholder="삼성동, 성동구, 강남대로 등" onKeyUp="keywordSearch()"/>
                <input type="submit" class="submit" name="search" value="" src=""/>
                <span class="placeholderText">삼성동, 성동구, 강남대로 등</span>
            </div>
        </form>
    </article>

	<article class="noticeBar">
		<div class="box">
			<div class="noticeArea">
				<i class="noticeIcon"></i>
				<div class="tickerArea">
					<ul class="innerWrap">
						<li class="item"><a href="https://onoffmix.com/cs/notice/400" target="_blank">[이벤트] 신규 플레이스 30일 등록 무료 체험 프로모션</a></li>
						<li class="item"><a href="https://onoffmix.com/cs/notice/396" target="_blank">[이벤트] 온오프믹스와 서울마리나가 함께하는 대관 할인 EVENT!!</a></li>
					</ul>
		   		</div>
       		</div>
            <div class="linkArea">
				<a href="${contextPath}/main/main.do" target="_blank">
					<i class="onoffmixLogo"></i>
                    <i class="arrowRightIcon"></i>
                </a>
            </div>
		</div>
		
		
		
        <div class="tagLinkArea" style="margin-left: 15%; margin-right: 15%;">
            <ul class="tagList">
				<li><a href="${contextPath}/main/main.do?id=${1}">파티룸</a></li>
				<li><a href="${contextPath}/main/main.do?id=${2}">스터디룸</a></li>
				<li><a href="${contextPath}/main/main.do?id=${3}">연습실</a></li>
				<li><a href="${contextPath}/main/main.do?id=${4}">공연장</a></li>
				<li><a href="${contextPath}/main/main.do?id=${5}">다목적홀</a></li>
				<li><a href="${contextPath}/main/main.do?id=${6}">연수원</a></li>	
		</ul>
		</div>
		
		
		
	</article>
	
     <br>
  	<c:if test="${id != null}">
 	<div class="searchname">
 		<c:if test="${id eq 1}"><h9 id="placelocation">'파티룸'</h9></c:if>
 		<c:if test="${id eq 2}"><h9 id="placelocation">'스터디룸'</h9></c:if>
 		<c:if test="${id eq 3}"><h9 id="placelocation">'연습실'</h9></c:if>
 		<c:if test="${id eq 4}"><h9 id="placelocation">'공연장'</h9></c:if>
 		<c:if test="${id eq 5}"><h9 id="placelocation">'다목적홀'</h9></c:if>
 		<c:if test="${id eq 6}"><h9 id="placelocation">'연수원'</h9></c:if>
 		검색결과
 	</div>
    </c:if> 
    
    <!-- 플레이스 리스트 영역 -->
    <article class="placeListArea">
    	
		<!-- 리스트1 -->
        <ul class="placeList">
        	<c:set var="goods_count" value="0" />
        	
        	<c:forEach var="item" items="${goodsMap.bestplace}">
        	<c:if test="${id eq null}">
        		<c:set var="goods_count" value="${goods_count + 1}" />
				<li>
					<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
						<div class="imgArea">
							<img src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
						</div>
						<div class="tagArea">
							<span class="btn tag">#태그</span>
	                   	</div>
						<div class="infoArea">
							<p class="title">${item.goods_name}</p>
	                        <p class="address">${item.goods_location}</p>
						</div>
						<div class="subInfoArea">
							<span class="capacity">수용인원 : ${item.goods_people_num}명</span>
							<p class="priceAndTime">
								<span class="price">
									<fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price" />
									${goods_price}원&nbsp;&nbsp;
								</span>
								<span class="time">
									${item.goods_operation_time}/시간
								</span>
							</p>
						</div>
					</a>
				</li>
			</c:if>
			</c:forEach>		
		</ul>
		
		<!-- 리스트2 -->
        <ul class="placeList">
        	<c:set var="goods_count" value="0" />
        	
        	<c:forEach var="item" items="${goodsMap.bestplace}">
        	<c:if test="${id eq item.goods_type_id}">
        		<c:set var="goods_count" value="${goods_count + 1}" />
				<li>
					<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
						<div class="imgArea">
							<img src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
						</div>
						<div class="tagArea">
							<span class="btn tag">#태그</span>
	                   	</div>
						<div class="infoArea">
							<p class="title">${item.goods_name}</p>
	                        <p class="address">${item.goods_location}</p>
						</div>
						<div class="subInfoArea">
							<span class="capacity">수용인원 : ${item.goods_people_num}명</span>
							<p class="priceAndTime">
								<span class="price">
									<fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price" />
									${goods_price}원&nbsp;&nbsp;
								</span>
								<span class="time">
									${item.goods_operation_time}/시간
								</span>
							</p>
						</div>
					</a>
				</li>
			</c:if>
			</c:forEach>		
		</ul>
	</article>
    
	<!-- 안내영역 -->
	<article class="guideArea">
		<h2 class="guideTitle">about The Place</h2>
		<ul class="guideList">
			<li>
				<a href="https://onoffmix.com/cs/faq/#305">
                    <div class="imgArea">
                        <img src="https://place.onoffmix.com/images/main/guideIcon1.png" />
                    </div>
                    <div class="titleArea">
                        <p class="title">플레이스 등록 안내</p>
                        <p class="summary">지금 당신의 플레이스를 등록해 보세요</p>
                    </div>
                </a>
            </li>
            <li class="center">
                <a href="https://onoffmix.com/cs/faq/#309">
                    <div class="imgArea">
                        <img src="https://place.onoffmix.com/images/main/guideIcon2.png" />
                    </div>
                    <div class="titleArea">
                        <p class="title">플레이스 대관 신청 방법</p>
                        <p class="summary">선택하신 장소의 대관 방법을 알려 드려요</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="https://onoffmix.com/cs/faq/place">
                    <div class="imgArea">
                        <img src="https://place.onoffmix.com/images/main/guideIcon3.png" />
                    </div>
                    <div class="titleArea">
                        <p class="title">플레이스 FAQ</p>
                        <p class="summary">자주 묻는 질문들을 모아 놓았어요</p>
                    </div>
                </a>
            </li>
        </ul>
    </article>
	</section>
    </div>
</body>
</html>