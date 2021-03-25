$(function(){
    $('.header').addClass('fixedMenu');
    // 반응형 resize
    $( window ).resize(function() {
        var windowWidth = $( window ).width();
        if(windowWidth <= 767) {
            $('.placeTitle').insertBefore('.mobileInfo .priceAndTime');
        }else if(windowWidth >= 768  && windowWidth <= 1279){
            $('.placeTitle').insertBefore('.mobileInfo .priceAndTime');
        }else if(windowWidth >= 1280){
            $('.placeTitle').appendTo('li.title .rightArea');
        }
    }).trigger('resize');

    // 뒤로가기 시 폼 reset
    $('form')[0].reset();

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
    if($('.bannerArea .slideImg').length > 1){
        option.loop=true;
    }
    $(".bannerArea").owlCarousel(option);

    $('.reservationList .count').val('1');
    // 시간 증가 감소
     $('.increment').click(function(){
         var input = $(this).closest('.countArea').find('.count');
         var count = input.val();
         if(isNaN(count) == true){
             input.val(1);
             return false
         }else if(count + 1 > 1){
             $(this).closest('.countArea').find('.decrement').removeClass('disabled');
         }
         count++;
         input.val(count);
     });
     $('.decrement').click(function(){
         var input = $(this).closest('.countArea').find('.count');
         var count = input.val();

         if(isNaN(count) == true){
             input.val(1);
             return false;
         }else if(count <= 1 ){
            return false;
         }

         if(count <= 2){
             $(this).addClass('disabled');
         }
         count--;
         input.val(count);
     });

    $('form').submit(function(){
        var form = $(this);
        if (form.parsley().isValid()){
            $('input[type="submit"]').attr('disabled','disabled');
        }else{
            return false;
        }
    });
});