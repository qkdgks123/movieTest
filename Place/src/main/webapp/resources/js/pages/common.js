$(function(){
	$( window ).resize(function() {
		var windowWidth = $( window ).width();
		if(windowWidth <= 767) {
			$('.headerMenu li.mypage').children().appendTo('.mobileLink');
		}else if(windowWidth >= 768){
			$('.mobileLink').children().appendTo('.headerMenu li.mypage');
		}
	}).trigger('resize');

	$('.login a , .gnbArea a').click(function(e){
		e.preventDefault();
		var header = $('.header');
		if(header.hasClass('show') == true){
			$('.loginArea').css('display','none');
			header.removeClass('show');
		}else{
			$('.loginArea').css('display','block');
			header.addClass('show');
		}
	});
	$('.loginArea .bgSkin').click(function(){
		$('.loginArea').css('display','none');
		$('.header').removeClass('show');
	});

	/*
	$('.loginForm').keyup(function(){
		var check = $('.loginForm').parsley().isValid();
		if(check == true){
			$('.loginBtn').attr('disabled', false);
		}else{
			$('.loginBtn').attr('disabled', true);
		}
	});
	*/

	$('.mobileLink .eventBtn').click(function(e){
		e.preventDefault();
		/*
		var linkArea = $(this).closest('.mobileLink');
		var check = linkArea.hasClass('active');

		if(check == true){
			linkArea.removeClass('active');
		}else{
			linkArea.addClass('active');
		}
		 */
	});

	$(".searchLinkBtn").on("click", function(e){
		getLocation();
		var _self = $(this);
		var href = _self.attr('href');
		href = href + "&latitude="+window.lat+"&longitude="+window.lng;
		_self.prop('href', href) ;
	})


	if (typeof Object.assign != 'function') {
		// Must be writable: true, enumerable: false, configurable: true
		Object.defineProperty(Object, "assign", {
		  value: function assign(target, varArgs) { // .length of function is 2
			'use strict';
			if (target == null) { // TypeError if undefined or null
			  throw new TypeError('Cannot convert undefined or null to object');
			}
	  
			var to = Object(target);
	  
			for (var index = 1; index < arguments.length; index++) {
			  var nextSource = arguments[index];
	  
			  if (nextSource != null) { // Skip over if undefined or null
				for (var nextKey in nextSource) {
				  // Avoid bugs when hasOwnProperty is shadowed
				  if (Object.prototype.hasOwnProperty.call(nextSource, nextKey)) {
					to[nextKey] = nextSource[nextKey];
				  }
				}
			  }
			}
			return to;
		  },
		  writable: true,
		  configurable: true
		});
	  }
});

function getLocation(){
	var defLat = 37.502854;
	var defLng = 127.019478;

	window.lat = defLat, window.lng = defLng;
	// if(navigator.geolocation){
	// 	navigator.geolocation.getCurrentPosition(
	// 		function(position) {
	// 			defLat = position.coords.latitude, defLng = position.coords.longitude;
	// 			window.lat = defLat, window.lng = defLng;
	// 		},
	// 		function(failure) {
	// 			if(failure.message.indexOf("Only secure origins are allowed") == 0) {
					
	// 			}
	// 		},{
	// 		enableHighAccuracy: false,
	// 		maximumAge: 0,
	// 		timeout: Infinity
	// 	});
	// }
}

function getArea(lat, lng){
	var naverLatLng = naver.maps.LatLng(lat, lng);
	var tm128 = naver.maps.TransCoord.fromUTMKToTM128(naverLatLng);

	naver.maps.Service.reverseGeocode({
		location: naver.maps.LatLng(lat, lng)
	}, function(status, response) {
		if (status !== naver.maps.Service.Status.OK) {
			return alert('에러');
		}
		var result = response.result;
		var addrDetail = result.items[0].addrdetail;
		var address = addrDetail.sido + ' ' + addrDetail.sigugun + ' ' + addrDetail.dongmyun;
		$('.address').text(address);
	});
}
