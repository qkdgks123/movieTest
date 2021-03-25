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
		
		alert(i_goods_type_name.value);
		
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
		
		alert(i_goods_type_name.value);
		
		document.body.appendChild(formObj);
		
		formObj.method = "post";
		formObj.action = "${contextPath}/order/orderEachGoods.do";
		formObj.submit();
	}
</script>
</head>
<body>
	<hgroup>
		<h1>상세보기</h1>
		<br>
		<h3>${goods.goods_name}</h3>
	</hgroup>
	
	<div style="float: left; margin-right: 10px;">
		<table>
			<tr>
				<td>
					<figure>
						<img alt="이미지준비중입니다." src="${contextPath}/thumbnails.do?goods_id=${goods.goods_id}&fileName=${goods.goods_fileName}">
					</figure>
				</td>
				<td max-width=50px;>
					<div id="detail_table" max-width=10px>
						<table>
							<tbody>
								<tr>
									<td class="fixed">상세주소</td>
									<td class="active">${goods.goods_location}</td>
								</tr>
								<tr>
									<td class="fixed">수용인원</td>
									<td class="active">${goods.goods_people_num}</td>
								</tr>
								<tr>
									<td class="fixed">가격</td>
									<td class="active">${goods.goods_price}원</td>
								</tr>
								<tr>
									<td class="fixed">대관시간</td>
									<td class="active">${goods.goods_operation_time}</td>
								</tr>
								<tr>
									<td class="fixed">문의처</td>
									<td class="active">${goods.goods_tel}</td>
								</tr>
								<tr>
									<td class="fixed">위치</td>
									<td class="active">${goods.goods_location}</td>
								</tr>

							</tbody>
						</table>
						<div class="tagLinkArea">
							<ul class="tagList">
								<li>
									<a class="buy" href="javascript:fn_order_each_goods(
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
								</li>
							</ul>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p>플레이스 정보</p>
					<hr> <br> ${goods.goods_contents}
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<p>지도</p>
					<hr> <br>
					<p style="margin-top: -12px">
						<em class="link"> <a href="javascript:void(0);"
							onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
								혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요. </a>
						</em>
					</p>
					<div id="map" style="width: 100%; height: 350px;"></div> <script
						type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a8f3c51ee0d9f1ba6a30b597e153e745&libraries=services"></script>
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
						geocoder
								.addressSearch(
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
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
<input type="hidden" name="isLogOn" id="isLogOn" value="${isLogOn}" />