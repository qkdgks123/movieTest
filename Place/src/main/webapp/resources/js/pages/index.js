$(function(){
	$('.header').addClass('fixedMenu');

    // 새로고침시 페이징 쿠키 제거
    $(window).bind('beforeunload',function(){
        var url = $(location).attr('hostname').replace('place','.place');
		$.removeCookie('laravel_paging', {path: '/', domain: url});
		//location.reload();
	});
	// 공지사항 클릭시 PC 연결
    $('.noticeBar').click(function(){
        var url = $(location).attr('hostname').replace('place','');
        $.cookie('isReqPcView', '1', {path: '/', domain: url});
    });

	window.search = $(".mapSearch .searchArea .search").val();

	$('input[name="search"].search').on('focus keyup',function(){
		var textLength = $(this).val().length;
		if(textLength < 1){
			$('.placeholderText').css('display','block');
		}else{
			$('.placeholderText').css('display','none');
		}
	}).trigger('keyup').attr('placeholder','');

	$('.placeholderText').click(function(){
		$('input[name="search"].search').trigger('focus');
	});

	$('.btnShowTag').click(function(e){
		e.preventDefault();
		$('.tagLinkArea').addClass('show');
	});

	var placeList = new Swiper(".todayPlace", {
		slidesPerView: "auto",
		spaceBetween: 10,
		freeMode: true,
		autoHeight : false,
		watchOverflow: true
	});

	$('.selectMenuBtn').click(function(e){
		e.preventDefault();
		var menu = $('.selectArea');

		if(menu.hasClass('show') == true){
			menu.removeClass('show');
		}else{
			menu.addClass('show');
		}
	});
	$('.selectArea .closeBtn').click(function(e){
		e.preventDefault();
		$('.selectArea').removeClass('show');
	});
	$(document).click(function(event) {
	  if(!$(event.target).closest('.selectMenuBtn, .selectArea').length) {
		  $('.selectArea').removeClass('show');
	  }
	});

  $(document).on('click','.pagination a', function(e){
	  e.preventDefault();
	  var pageNum = $(this).attr('href');
	  history.pushState(null, null, pageNum);
	  $('.placeListArea').load($(this).attr('href')+'#main .placeListArea');
	});

	var tickerLength = $('.tickerArea .innerWrap .item').length;
	if(tickerLength > 1){
		$('.tickerArea').easyTicker({
			direction: 'up',
			easing: 'swing',
			speed: 'slow',
			interval: 2000,
			height: 'auto',
			visible: 0,
			mousePause: 1
		});
	}

});