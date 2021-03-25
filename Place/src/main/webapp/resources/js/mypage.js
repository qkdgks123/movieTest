$(function(){
	$('.header').addClass('fixedMenu');

	$('.reserveBtn').on('click', function(e){
		e.preventDefault();
		var tmplInfo = $(this).closest('li');
		var peopleChoice = [];
		tmplInfo.find('.rsvpList').each(function(index, element){
			peopleChoice.push({'placeName': $(this).val(), 'placeCount': $(this).data('count')});
		});
		var data = {
			'name': tmplInfo.find('.userName').val(),
			'belong': tmplInfo.find('.userBelong').val(),
			'phone': tmplInfo.find('.userPhone').val(),
			'email': tmplInfo.find('.userEmail').val(),
			'purpose': tmplInfo.find('.userPurpose').val(),
			'date' : tmplInfo.find('.rsvpStartDate').val(),
			'startTime' : tmplInfo.find('.rsvpStartTime').val(),
			'endTime' : tmplInfo.find('.rsvpEndTime').val(),
			'people': peopleChoice,
			'inquire': tmplInfo.find('.userInquire').val(),
			'pay': tmplInfo.find('.userPay').val() == 1 ? '온오프믹스 결제 사용 함' : '온오프믹스 결제 사용 하지 않음'
		};

		$('.tmplReserveInfo').tmpl(data).appendTo('.popupArea');
		$('.popupArea').addClass('show');
	});

	$('.comment').on('click', function(e){
		e.preventDefault();
		var idx = {'idx': $(this).attr('idx')};

		$('.tmplReserve').tmpl(idx).appendTo('.popupArea');
		$('.reserveForm').parsley();
		$('.popupArea').addClass('show');
	});

	$('.report').on('click', function(e){
		e.preventDefault();
		var tmplInfo = $(this).closest('li');
		var data = {
			'idx': $(this).data('idx'),
			'infoIdx': $(this).data('info-idx'),
			'email': tmplInfo.find('.userEmail').val(),
		};

		$('.tmplReport').tmpl(data).appendTo('.popupArea');
		$('.reportForm').parsley();
		$('.popupArea').addClass('show');
	});

	$(document).on('click', '.bgSkin , .closeBtn' ,  function(e){
        e.preventDefault();
		$('.popupArea').removeClass('show');
		$('.popupArea .popupContent').remove();
	});
});