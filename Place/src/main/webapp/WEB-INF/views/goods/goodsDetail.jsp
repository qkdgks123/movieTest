<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="goods" value="${goodsMap.goodsVO}" />
<c:set var="imageList" value="${goodsMap.imageList }" />
<%
	//치환 변수 선언합니다.
	pageContext.setAttribute("crcn", "\r\n"); //개행문자
	pageContext.setAttribute("br", "<br/>"); //br 태그
	System.out.println("goodsDetail.jsp\n===============================");
%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">

	// * 단일 상품 예약하기
	function fn_order_each_goods(goods_id, goods_name, goods_people_num, goods_contents, goods_tel, goods_price, goods_location, goods_operation_time, goods_type_id, goods_status, goods_fileName, goods_type_name) {
		
		var _isLogOn = document.getElementById("isLogOn");
		var isLogOn = _isLogOn.value;

		if (isLogOn == "false" || isLogOn == '') {

			alert("로그인 후 주문이 가능합니다.");
		}

		var formObj = document.createElement("form");
		
		var i_goods_id = document.createElement("input");
		var i_goods_name = document.createElement("input");
		var i_goods_people_num = document.createElement("input");
		var i_goods_contents = document.createElement("input");
		var i_goods_tel = document.createElement("input");
		var i_goods_price = document.createElement("input");
		var i_goods_location = document.createElement("input");
		var i_goods_operation_time = document.createElement("input");
		var i_goods_type_id = document.createElement("input");
		var i_goods_status = document.createElement("input");
		var i_goods_fileName = document.createElement("input");
		//var i_goods_credate = document.createElement("input");
		var i_goods_type_name = document.createElement("input");
		
		i_goods_id.name = "goods_id";
		i_goods_name.name = "goods_name";
		i_goods_people_num.name = "goods_people_num";
		i_goods_contents.name = "goods_contents";
		i_goods_tel.name = "goods_tel";
		i_goods_price.name = "goods_price";
		i_goods_location.name = "goods_location";
		i_goods_operation_time.name = "goods_operation_time";
		i_goods_type_id.name = "goods_type_id";
		i_goods_status.name = "goods_status";
		i_goods_fileName.name = "goods_fileName";
		//i_goods_credate.name = "goods_credate";
		i_goods_type_name.name = "goods_type_name";
		
		i_goods_id.value = String(goods_id);
		i_goods_name.value = String(goods_name);
		i_goods_people_num.value = String(goods_people_num);
		i_goods_contents.value = String(goods_contents);
		i_goods_tel.value = String(goods_tel);
		i_goods_price.value = String(goods_price);
		i_goods_location.value = String(goods_location);
		i_goods_operation_time.value = String(goods_operation_time);
		i_goods_type_id.value = String(goods_type_id);
		i_goods_status.value = String(goods_status);
		i_goods_fileName.value = String(goods_fileName);
		//i_goods_credate.value = Date(goods_credate);
		i_goods_type_name.value = String(goods_type_name);
				
		formObj.appendChild(i_goods_id);
		formObj.appendChild(i_goods_name);
		formObj.appendChild(i_goods_people_num);
		formObj.appendChild(i_goods_contents);
		formObj.appendChild(i_goods_tel);
		formObj.appendChild(i_goods_price);
		formObj.appendChild(i_goods_location);
		formObj.appendChild(i_goods_operation_time);
		formObj.appendChild(i_goods_type_id);
		formObj.appendChild(i_goods_status);
		formObj.appendChild(i_goods_fileName);
		//formObj.appendChild(i_goods_credate);
		formObj.appendChild(i_goods_type_name);
				
		document.body.appendChild(formObj);
		
		formObj.method = "post";
		formObj.action = "${contextPath}/order/orderEachGoods.do";
		formObj.submit();
	}
</script>
</head>
<body>
	<div id="app">
		<div class="topSkin responsible">
			<div class="skin" style="background-image: url(${contextPath}/resources/image/view/topSkin.png)"></div>
		</div>
		<section class="content">
		
			<article class="placeBanner">
				<h3 class="placeTitle">${goods.goods_name}</h3>
				<div class="banner">
					<div class="slideImg">
						<img src="${contextPath}/thumbnails.do?goods_id=${goods.goods_id}&fileName=${goods.goods_fileName}" alt="이미지 준비 중입니다.">
					</div>
				</div>
				<div style="clear: both;"></div>
			</article>
			
			<article class="placeForm">
				<div class="bgSkin"></div>
				<p class="priceAndTime">
					<span class="price">${goods.goods_price}</span>
					<span class="time">~/시간</span>
				</p>
				<ul class="infoList formList">
					<li><span class="formTitle">공간유형</span>
						<div class="rightArea">
							<p>
								${goods.goods_type_name}
								<input type="hidden" name="goods_type_id" value="${item.goods_type_id}">
							</p>
						</div>
					</li>
					<li><span class="formTitle">상세주소</span>
						<div class="rightArea">
							<p>${goods.goods_location}</p>
						</div>
					</li>
					<li><span class="formTitle">수용인원</span>
						<div class="rightArea">
							<p>${goods.goods_people_num}명 까지</p>
						</div>
					</li>
					<li><span class="formTitle">대관금액</span>
						<div class="rightArea">
							<p>${goods.goods_price}원</p>
						</div>
					</li>
					<li><span class="formTitle">대관시간</span>
						<div class="rightArea">
							<p>${goods.goods_operation_time}</p>
						</div>
					</li>
					<li><span class="formTitle">문의처</span>
						<div class="rightArea">
							<p>${goods.goods_tel}</p>
						</div>
					</li>
				</ul>
				<div class="btnArea">
				<!-- 
					<a class="btn link ">예약문의 하기</a>
					<input type="submit" class="btn submit" value="예약정보 입력" />
					 -->
					<a class="reservation_btn" href="javascript:fn_order_each_goods(
															'${goods.goods_id}', 
															'${goods.goods_name}', 
															'${goods.goods_people_num}', 
															'${goods.goods_contents}', 
															'${goods.goods_tel}',
															'${goods.goods_price}',
															'${goods.goods_location}',
															'${goods.goods_operation_time}',
															'${goods.goods_type_id}',
															'${goods.goods_status}',
															'${goods.goods_fileName}',
															'${goods.goods_type_name}'
														);">예약하기 
									</a>
				</div>
			</article>

			<article class="placeInfo">
				<div class=".apply_h4">
				<h4 class="subTitle">플레이스 정보</h4>
				<p class="infoText">${goods.goods_contents}</p>
				</div>
			</article>
	
			<article class="placeTips">
				<h4 class="subTitle">편의 시설 / 주의 사항</h4>
				<ul class="tipMenu">
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon1.png" alt="TV/프로젝터">
						</div> TV/프로젝터
					</li>
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon2.png" alt="인터넷/WiFi">
						</div> 인터넷/WiFi
					</li>
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon3.png" alt="복사/인쇄기">
						</div> 복사/인쇄기
					</li>
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon4.png" alt="화이트보드">
						</div> 화이트보드
					</li>
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon11.png" alt="금연">
						</div> 금연
					</li>
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon13.png" alt="PC/노트북">
						</div> PC/노트북
					</li>
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon14.png" alt="의자/테이블">
						</div> 의자/테이블
					</li>
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon15.png" alt="내부화장실">
						</div> 내부화장실
					</li>
					<li>
						<div class="imgArea">
							<img src="${contextPath}/resources/image/add/facilityIcon16.png" alt="남여화장실분리">
						</div> 남여화장실분리
					</li>
	
				</ul>
				<p class="tips">
					- 준비시간과 정리시간을 포함하여 예약해 주시기 바랍니다.(예약시간 15분초과시 추가요금발생) <br />
					- 주차장은 별도로 구비되어 있지 않으니, 인근 주차장 및 노상도로를 이용부탁드립니다. <br />
				</p>
			</article>

			<article class="placePayTips">
				<h4 class="subTitle">환불기준</h4>
				<p class="tips">
					이고스스터디랩은 이용일 전까지 환불요청시, 100%환불 처리해드립니다.
					다만,결제시스템에 의해서 환불수수료가 발생할 수 있으니 참고하시기 바랍니다.
				</p>
			</article>

			<article class="placeLocation">
				<h4 class="subTitle">위치</h4>
				<div class="location">
					<p>${goods.goods_location}</p>
				</div>
				<div id="map" style="width: 100%; height: 350px;"></div>
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a8f3c51ee0d9f1ba6a30b597e153e745&libraries=services"></script>
				<script>
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new kakao.maps.LatLng(33.450701,
								126.570667), // 지도의 중심좌표
						level : 3
					// 지도의 확대 레벨
					};

					// 지도를 생성합니다    
					var map = new kakao.maps.Map(mapContainer, mapOption);

					// 주소-좌표 변환 객체를 생성합니다
					var geocoder = new kakao.maps.services.Geocoder();

					// 주소로 좌표를 검색합니다
					geocoder.addressSearch(
								'서울특별시 강남구 강남대로156길',
								function(result, status) {

									// 정상적으로 검색이 완료됐으면 
									if (status === kakao.maps.services.Status.OK) {

										var coords = new kakao.maps.LatLng(
												result[0].y,
												result[0].x);

										// 결과값으로 받은 위치를 마커로 표시합니다
										var marker = new kakao.maps.Marker(
												{
													map : map,
													position : coords
												});

										// 인포윈도우로 장소에 대한 설명을 표시합니다
										var infowindow = new kakao.maps.InfoWindow(
												{
													content : '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
												});
										infowindow.open(map, marker);

										// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
										map.setCenter(coords);
									}
								});
					</script>
			</article>
		</section>
	</div>
</body>
</html>
<input type="hidden" name="isLogOn" id="isLogOn" value="${isLogOn}" />