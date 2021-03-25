window.search = "";
window.area = "전국";
window.type = 0;
window.people = 0;

$(function(){
	// 새로고침시 페이징 쿠키 제거
	$(window).bind('beforeunload',function(){
		var url = $(location).attr('hostname').replace('place','.place');
		$.removeCookie('laravel_paging', {path: '/', domain: url});
	});

	// 리스트 출력
	redraw_page();

	// 카테고리 필터 버튼
	$('.filterBtn').click(function(e){
		e.preventDefault();
		var check = $(this).hasClass('showFilter');
		if(check == true){
			$(this).removeClass('showFilter');
			$('.filterArea').removeClass('show');
			$('.filterBgSkin').removeClass('show');
		}else{
			$(this).addClass('showFilter');
			$('.filterArea').addClass('show');
			$('.filterBgSkin').addClass('show');
		}
	});
	// 카테고리 hide 버트
	$('.filterHide, .filterBgSkin').click(function(e){
		e.preventDefault();
		$('.filterBtn').removeClass('showFilter');
		$('.filterArea').removeClass('show');
		$('.filterBgSkin').removeClass('show');
	});

	// 검색
	$('.searchBar input[type="submit"].submit').on('click',function(event){
		event.preventDefault();
		event.stopPropagation();
		window.search =  $(".searchArea input[name='search']").val();
		window.type = $(".filterArea input[name='type']:checked").val();
		window.people = $(".filterArea input[name='people']:checked").val();
		window.area = $(".filterArea input[name='area']:checked").val();
		$.ajax({
			url: "/api/v1/place/search",
			type: "post",
			data : {
				"search" : search,
				"type" : type,
				"people" : people,
				"area" : area
			},
			dataType: 'json',
			success : function (data){
				vueApp.$children[0].fetchData(data);
			},
			error: function(error){
				console.log(error.responseText);
			}

		});
	});

	// 필터 선택시 리스트 실시간 변경
	$('.filterArea input[type="radio"]').change(function(event){
		event.preventDefault();
		event.stopPropagation();
		window.search =  $(".searchArea input[name='search']").val();
		window.type = $(".filterArea input[name='type']:checked").val();
		window.people = $(".filterArea input[name='people']:checked").val();
		window.area = $(".filterArea input[name='area']:checked").val();
		$.ajax({
			url: "/api/v1/place/search",
			type: "post",
			data : {
				"search" : search,
				"type" : type,
				"people" : people,
				"area" : area
			},
			dataType: 'json',
			success : function (data){
				vueApp.$children[0].fetchData(data);
			},
			error: function(error){
				console.log(error.responseText);
			}
		});
	});

	$(document).on('click', '.filterArea input[type="radio"]:checked + span' ,function(e){
		e.preventDefault();
		$(this).prev().prop('checked', false).trigger('change');
	});

	// 필터 삭제
	$(document).on('click', '.filterDelete', function(e){
		e.preventDefault();
		e.stopPropagation();
		var filter = $(this).parent('.filterBlock');
		if(filter.hasClass('area') == true){
			$('.filterArea input[type="radio"][name="area"]').prop('checked', false);
		}else if(filter.hasClass('people') == true){
			$('.filterArea input[type="radio"][name="people"]').prop('checked', false);
		}else if(filter.hasClass('type') == true){
			$('.filterArea input[type="radio"][name="type"]').prop('checked', false);
		}

		window.search =  $(".searchArea input[name='search']").val();
		window.type = $(".filterArea input[name='type']:checked").val();
		window.people = $(".filterArea input[name='people']:checked").val();
		window.area = $(".filterArea input[name='area']:checked").val();
		$.ajax({
			url: "/api/v1/place/search",
			type: "post",
			data : {
				"search" : search,
				"type" : type,
				"people" : people,
				"area" : area
			},
			dataType: 'json',
			success : function (data){
				vueApp.$children[0].fetchData(data);
			},
			error: function(error){
				console.log(error.responseText);
			}
		});
	});
});

function redraw_page(page){
	$.when(
		get_data(page)
	).done(function(data){
			vueApp.$children[0].fetchData(data);
		});
}

function get_data(page){
	if(page == '' || page == undefined || page == null){
		page = 1;
	}
	var data = {
		"dummy" : '',
		"search" : $('input[name="search"]').val(),
		"area" : $('input[name="area"]:checked').val(),
		"type" : $('input[name="type"]:checked').val(),
		"people" : $('input[name="people"]:checked').val(),
		"page" : page
	};

	var ajax = $.ajax({
		url:'/api/v1/place/search',
		type: 'post',
		data: data
	});

	return ajax;
}