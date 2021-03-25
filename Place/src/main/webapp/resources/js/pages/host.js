$(function(){
    if($('.content').hasClass('hostEdit') == true){
        $('.header').addClass('fixedMenu');
    }

    $('.placeBtn').on("click", function(event){
        event.preventDefault();
        event.stopPropagation();
        var _self = $(this);
        var authData = "default";
        var authType = "default";

        if(_self.hasClass('phoneAuth')){
            authType = "user_phone_auth";
            authData = $('input[name="mobile"]').val();
        } else if(_self.hasClass('emailAuth')){
            authType = "user_email_auth";
            authData = $('input[name="email"]').val();
        }else {
            return false;
        }

        // FIXME: authData 값 검증 스크립트 추가 할 것
        $.ajax({
            url: '/api/v1/auth',
            type: 'post',
            data: {
                'type' : authType,
                'data': authData,
            },
            success: function(data){
                if(data == "true"){
                    if(_self.hasClass('phoneAuth')){
                        $('.isMobile').val('n');
                    } else if(_self.hasClass('emailAuth')){
                        $('.isEmail').val('n');
                    }

                    alert("인증번호가 발송 되었습니다");
                    return false;
                } else {
                    alert("인증번호 발송중 에러가 발생했습니다.\r\n 잠시 후 다시 시도해 주시기 바랍니다.");
                    return false;
                }
            },
            error: function(data){
                alert("인증번호 발송중 에러가 발생했습니다.\r\n 잠시 후 다시 시도해 주시기 바랍니다.");
                return false;
            }
        });
    });

    $(".authCertBtn").on("click", function(event){
        event.preventDefault();
        event.stopPropagation();
        var _self = $(this);
        var authData;
        var authType;
        var inputData;

        if(_self.hasClass("phone")){
            authType = "mobile";
            authData = $('.checkInput.certPhone').val();
            inputData = $('.submitInput.phoneCheck').val();
        } else if(_self.hasClass("email")){
            authType = "email";
            authData = $('.checkInput.certEmail').val();
            inputData = $('.submitInput.emailCheck').val();
        } else {
            return false;
        }

        if(authData == "" || authData == null || authData == undefined){
            alert("인증번호를 입력해주세요");
            return false;
        } else {
            $.ajax({
                url: '/api/v1/authCert',
                type: 'post',
                data: {
                   'type': authType,
                   'data': authData 
                },
                success: function(data){
                    // 인증완료 코드 작성
                    if(data == "true"){
                        if(authType == "mobile"){
                            $(".isMobile").val("y")
                        } else if(authType == "email"){
                            $(".isEmail").val("y")
                        }

                        _self.closest('.editArea').find('.equaltoCheck').text(inputData);
                        _self.closest('.editArea').addClass('completed');
                        alert("인증되었습니다.");
                    } else {
                        alert("인증번호가 틀립니다.");
                        return false;    
                    }
                },
                error: function(data){
                    alert("에러가 발생했습니다.\r\n 잠시 후 다시 시도해 주시기 바랍니다.");
                    return false;
                }
            })
        }
    });

    // 이메일 휴대전화 재수정 코드
    $('.modifyBtn').click(function(e){
        e.preventDefault();
        var _parent = $(this).closest('.editArea');

        _parent.removeClass('completed');
        _parent.find('.checkInput').val('');
    });

    // 프로필 이미지 업로드
    $('.profileImgUpload').fileupload({
        url: '/api/file',
        paramName: 'Filedata',
        singleFileUploads: true,
        acceptFileTypes: /(\.|\/)(bmp|pdf|doc|docx|xls|xlsx|ppt|pptx|pps|hwp|txt|sas|spss|zip|alz|tar|tgz|gz|z|swf|flv|mp3|ogg|asf|wmv|wma|avi|mov|rm|ra|jpeg|png|jpg|gif|svg)$/i,
        maxFileSize: 2097152,
        messages : {
            acceptFileTypes: '업로드가 가능한 확장자가 아닙니다',
            maxFileSize: '파일 용량은 최대 2MB까지 등록할 수 있습니다.'
        },
        done: function(e, data){
            var sObj = data.result.split("|");
            console.log(sObj);
            if (sObj.length == 5) {
                var FName = sObj[1];
                var FSize = sObj[2];
                var FURI = sObj[3];
                var ThumbURI = sObj[4];
            }
            $(this).closest('.editArea').find('.uploadInput').val(sObj[0]);
            $(this).closest('.editArea').find('label').addClass('uploadSuccess').text('').css('background-image', 'url('+ThumbURI+')');
        }
    }).bind('fileuploadprocessfail', function (e, data) {
        alert(data.files[data.index].error);
    });

    // 파일업로드
    $('.fileUpload').each(function () {
        var _this = $(this);
        _this.fileupload({
            url: '/api/file',
            paramName: 'Filedata',
            singleFileUploads: true,
            acceptFileTypes: /(\.|\/)(bmp|pdf|doc|docx|xls|xlsx|ppt|pptx|pps|hwp|txt|sas|spss|zip|alz|tar|tgz|gz|z|swf|flv|mp3|ogg|asf|wmv|wma|avi|mov|rm|ra|jpeg|png|jpg|gif|svg)$/i,
            maxFileSize: 2097152,
            messages : {
                acceptFileTypes: '업로드가 가능한 확장자가 아닙니다',
                maxFileSize: '파일 용량은 최대 2MB까지 등록할 수 있습니다.'
            },
            done: function(e, data){
                var sObj = data.result.split("|");
                console.log(sObj);
                if (sObj.length == 5) {
                    var FName = sObj[1];
                    var FSize = sObj[2];
                    var FURI = sObj[3];
                    var ThumbURI = sObj[4];
                }
                $(this).closest('.editArea').find('.uploadInput').val(sObj[0]);
                $(this).closest('.editArea').find('.fileName').val(sObj[1]);
            }
        }).bind('fileuploadprocessfail', function (e, data) {
            alert(data.files[data.index].error);
        });
    });

    // 이메일 휴대전화 재인증 체크
    $('.submitInput').change(function(){
        var _this = $(this);
        var equalto = _this.closest('.editArea').find('.equaltoCheck').text();
        if(_this.val() == equalto){
            if(_this.hasClass('emailCheck') == true){
                $('.isEmail').val('y');
            }else if(_this.hasClass('phoneCheck') == true){
                $('.isMobile').val('y');
            }
        }else{
            if(_this.hasClass('emailCheck') == true){
                $('.isEmail').val('n');
            }else if(_this.hasClass('phoneCheck') == true){
                $('.isMobile').val('n');
            }
        }
    });
    $.listen('parsley:field:error', function(parsleyField) {
        console.log(parsleyField);
        if (parsleyField.$element.hasClass('isMobile') == true || parsleyField.$element.hasClass('isEmail') == true) {
            $("input.checkInput").focus();
        }
    });

    $('input[name="copyHostInfo"]').change(function(){
        var check = $(this).is(':checked');

        var companyName = $('input[name="company_payment_name"]');
        var companyEmail = $('input[name="company_payment_email"]');
        var companyPhone = $('input[name="company_payment_mobile"]');

        // 정보 동일 체크박스 체크 여부
        if(check == true){
            var name = $('input[name="name"]').val();
            var email = $('input[name="email"]').val();
            var phone = $('input[name="mobile"]').val();

            $(this).val('y');
            companyName.val(name);
            companyEmail.val(email);
            companyPhone.val(phone);
        }else{
            $(this).val('n');
            companyName.val('');
            companyEmail.val('');
            companyPhone.val('');
        }
    });

    /*
    $('.hostForm').on('keyup change',function(){
        var check = $(this).parsley().isValid();
        if(check == true){
            $('.submitBtn').removeClass('disabled');
        }else{
            $('.submitBtn').addClass('disabled');
        }
    });
     */
});