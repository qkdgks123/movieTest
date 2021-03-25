<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
     //치환 변수 선언
      pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
      pageContext.setAttribute("br", "<br/>"); //br 태그
      System.out.println("searchGoods.jsp\n===============================");
%>
<head>
<title>검색 결과 페이지</title>
</head>
<body>
   <div id="app">
   
      <section class="content" id="main">
         <article class="listArea">
            <div>
            
               <div class="listNav">
                  <div class="searchResult">
                     ‘<span class="pageFont">${searchWord}</span>’ 검색결과 (?건)
                  </div>
                  <div class="searchFilter">
                  </div>
               </div>
				 
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
										<span class="price"><fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price" />${goods_price}원</span>
										<span class="time">${item.goods_operation_time}/시간</span>
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
		        	<c:if test="${id eq item.goods_name}">
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
										<span class="price"><fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price" />${goods_price}원</span>
										<span class="time">${item.goods_operation_time}/시간</span>
									</p>
								</div>
							</a>
						</li>
					</c:if>
					</c:forEach>			
				</ul>
			</article>
            </div>
         </article>
      </section>
   </div>
</body>