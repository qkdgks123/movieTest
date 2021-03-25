<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   

 <script>
function checkValue(){
	if(!document.userInfo.id.value){
		alert("아이디를 입력하세요");
		return false;
	}
	
	if(!document.userInfo.password.value){
		alert("비밀번호를 입력하세요");
		return false;
	}
	if(document.userInfo.password.value!=document.userInfo.repassword.value){
		alert("비밀번호를 동일하게 입력하세요");
		return false;
	}
	if(!document.userInfo.name.value){
		alert("이름을 입력하세요");
		return false;
	}
	
}
</script>



<!DOCTYPE html><html>
<head>
<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- JS -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<div class="jumbotron">
 <div class="container">
   <h1 class="display-3">회원 가입</h1>
 </div>
</div>
<div class="wrapper" style="width: 100%; max-width:1440px; margin: 0 auto;">
<div class="container"><!-- 내용 -->
<form action="/movie/addmember" name="userInfo" id="userInfo" method="post" 
                 class="form-horizontal" onsubmit="return checkValue()">
<div class="form-group row">
  <label class="col-sm-2">아이디</label>
  <div class="col-sm-3">
  	<input type="text" name="id" id="id" class="form-control" placeholder="id"> 
  	 <button type="button" id="chk" name="chk" class="btn btn-success sm-3 mt-2 mb-1">중복확인</button>
  	  
      <p class="result">
       <span class="msg">아이디를 확인</span>
      </p>
  </div>
</div>

<div class="form-group row">
  <label class="col-sm-2">비밀번호</label>
  <div class="col-sm-3">
  	<input type="password" name="password" id="password" class="form-control" placeholder="password">
  </div>
</div>
<div class="form-group row">
  <label class="col-sm-2">비밀번호확인</label>
  <div class="col-sm-3">
  	<input type="password" name="repassword" id="repassword" class="form-control" placeholder="password confirm">
  </div>
</div>

<div class="form-group row">
  <label class="col-sm-2">성 명</label>
  <div class="col-sm-3">
  	<input type="text" name="name" class="form-control" placeholder="name">
  </div>
</div>

<div class="form-group row">
  <label class="col-sm-2">전화번호</label>
  <div class="col-sm-3">
  	<input type="text" name="phone" class="form-control" placeholder="phone">
  </div>  
</div>

<div class="form-group row">
  <label class="col-sm-2">성별</label>
  <div class="col-sm-10">
  	<input type="radio" name="gender" value="남" checked="checked">남
  	<input type="radio" name="gender" value="여">여
  </div>
</div>



<div class="form-group row">
  <label class="col-sm-2">생년월일</label>
  <div class="col-sm-3">
  	<input type="text" name="birth" id="birth" class="form-control" placeholder="birth">   
  </div>
</div>

<div class="form-group row">
  <label class="col-sm-2">이메일</label>
  <div class="col-sm-3">
  	<input type="text" name="mail" maxlength="50" class="form-control" placeholder="mail">
  </div>  
</div>



<div class="form-group row">
  <label class="col-sm-2">우편번호</label>
  <div class="col-sm-5">
  	<input type="text" name="zipcode" id="postcode" class="form-control" placeholder="zipcode"><a href="javascript:execDaumPostcode()" class="btn btn-secondary" role="button"> 우편번호 &raquo;</a>
  </div>  
</div>



<div class="form-group row">
  <label class="col-sm-2">주소1</label>
  <div class="col-sm-5">
  	<input type="text" name="address" id="address" class="form-control" placeholder="address">
  </div>  
</div>
<div class="form-group row">
  <label class="col-sm-2">상세주소</label>
  <div class="col-sm-5">
  	<input type="text" name="addressdetail" id="detailAddress" class="form-control" placeholder="address">
  </div>  
</div>
<div class="form-group row">
  <label class="col-sm-2">참고</label>
  <div class="col-sm-5">
  	<input type="text" name="extraAddress" id="extraAddress" class="form-control" placeholder="address">
  </div>  
</div>

<div class="form-group row">
  <div class="col-sm-offset-2 col-sm-10">
  	<input type="submit" class="btn btn-primary" value="등록" id=signUp disabled="disabled">
  	<input type="reset" class="btn btn-primary" value="취소">
  </div>  
</div>


</form>
</div>
</div>

</body>
</html>

 <script> 
$("#chk").click(function(){
 
 var query = {id : $("#id").val()};
 
 $.ajax({
  url : "/movie/idCheck",
  type : "post",
  data : query,
  success : function(data) {
  
		  

   if(data == 1) {
    $(".result .msg").text("사용 불가");
    $(".result .msg").attr("style", "color:#f00");  
    
    $("#signUp").attr("disabled", "disabled");
    
   } else {
    $(".result .msg").text("사용 가능");
    $(".result .msg").attr("style", "color:#00f");
    
    $("#signUp").removeAttr("disabled");
    
    $("#id").keyup(function(){
    	 $(".result .msg").text("아이디를 확인해주십시오.");
    	 $(".result .msg").attr("style", "color:#000");
    	 
    	 $("#submit").attr("disabled", "disabled");
    	 
    });
   }
  }
 });  // ajax 끝
});
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
</script>