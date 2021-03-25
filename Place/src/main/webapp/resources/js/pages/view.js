$(function(){
	$('.header').addClass('fixedMenu');
	// 반응형 resize
	$( window ).resize(function() {
		var windowWidth = $("body").prop("scrollWidth");
		if(windowWidth <= 767) {
			$('.placeTitle').insertBefore('.placeForm .bgSkin');
			$('.btnArea').insertAfter('.placeSimilar');
			$('.mapBtn').appendTo('.placeLocation .subTitle');

		}else if(windowWidth >= 768  && windowWidth <= 1279){
			$('.placeTitle').insertBefore('.placeForm .bgSkin');
			$('.btnArea').insertAfter('.placeSimilar');
			$('.mapBtn').appendTo('.placeLocation .subTitle');


		}else if(windowWidth >= 1280){
			$('.placeTitle').insertBefore('.placeBanner .banner');
			$('.btnArea').appendTo('.placeForm');
			$('.mapBtn').appendTo('.placeLocation .location');

		}
	}).trigger('resize');

	// selectmenu
	$(".startTime").selectmenu();
	$(".endTime").selectmenu({
		position: { my : "top", at: "left+6 bottom" }
	});

	$('.selectmenu').change(function(){
		$(this).trigger('selectmenuchange');
	});

	// 이미지 슬라이드
	var option = {
		center: true,
		items: 1,
		nav: true,
		autoplay: true,
		autoplayTimeout: 3000,
		autoplayHoverPause: true
		//smartSpeed: 2000
	};
	if($('.banner .slideImg').length > 1){
		option.loop=true;
	}
	$(".banner").owlCarousel(option);

	// form
	$('.btnArea .link').click(function(event){
		event.preventDefault();
		event.stopPropagation();

		if(!($(this).hasClass('logged'))){
			alert("로그인 후 이용해주세요");
			$(".login a").trigger("click");
			return false;
		}

		var form = $('.placeForm');
		form.addClass('showForm');
		$('.btnArea, body').addClass('showForm');
		$('form').parsley();
	});

	// 달력
	var weekCheck = $('.weekCheck').val().split(',');
	var week = new Array('일','월','화','수','목','금','토');
	var weekArray = new Array();

	for(var i in weekCheck){
		weekArray[i] = week.indexOf(weekCheck[i]);
	}

	var date = $('#date').datepicker({
		language: 'ko',
		autoClose: false,
		prevHtml: '<i class="prev"></i>',
		nextHtml: '<i class="next"></i>',
		minDate: new Date(),
		navTitles: {
			days: 'yyyy<i>년</i> MM',
			months: 'yyyy',
			years: 'yyyy1 - yyyy2'
		},
		onRenderCell: function (date, cellType) {
			if (cellType == 'day') {
				var day = date.getDay(),
					isDisabled = (weekArray.indexOf(day) == -1);

				return {
					disabled: isDisabled
				}
			}
		},
		onSelect: function(formattedDate, date, inst){
		},
		onShow: function(inst, animationCompleted){
			$('.placeForm').addClass('showPicker');
		},
		onHide: function(inst, animationCompleted){
			date.visible = true;
		}
	}).data('datepicker');

	$('#date').on('click', function(){
		date.show();
	});

	$(document).on('click','.dateCancel', function(){
		$('.placeForm').removeClass('showPicker');
		date.clear();
		date.hide();
	});
	$(document).on('click','.dateChoice', function(){
		$('.placeForm').removeClass('showPicker');
		date.hide();
	});

	$('.spaceChoice').click(function(){
		var check = $(this).hasClass('showList');

		if(check == true){
			$('.spaceArea').removeClass('showList');
		}else{
			$('.spaceArea').addClass('showList');
		}
	});

	window.Parsley.addValidator('timecheck', {
		validateString: function (value, requirement, parsleyInstance) {
			var dom = $(parsleyInstance.$element[0]);
			var today = new Date();
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			var date = today.getDate();

			var start_time = $("select[name='" + dom.attr('data-starttime') + "']").val(),
				end_time = value;

			var timeStartArr = start_time.split(':'),
				timeEndArr = end_time.split(':');

			var start = new Date(year, month, date, timeStartArr[0], timeStartArr[1], 0),
				end = new Date(year, month, date, timeEndArr[0], timeEndArr[1], 0);

			return start < end;
		},
		requirementType: 'string',
		messages: {
			ko: "모임일시의 종료 시간은 시작 시간보다 '이후'여야 합니다."
		}
	});

	// 지도
	$('#map').each(function(){
		var mapX = $(this).attr('mapX');
		var mapY = $(this).attr('mapY');
		if(mapX == ''){
			mapX = '37.5040684';
		}
		if(mapY == ''){
			mapY = '127.0211714';
		}

		map = new naver.maps.Map('map', {
			center: new naver.maps.LatLng(mapX, mapY),
			zoom: 10
		});
		marker = new naver.maps.Marker({
			position: new naver.maps.LatLng(mapX, mapY),
			map: map
		});
	});

    $('.btnArea .preview').click(function(){
		alert('미리보기 중입니다.');
		return false;
    });
});
