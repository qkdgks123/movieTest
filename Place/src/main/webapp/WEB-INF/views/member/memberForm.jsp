<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("memberForm.jsp\n===============================");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
	<style>
		.text_center {
			text-align: center;
		}
	</style>
	
<script type="text/javascript">

function fn_overlapped(){
    var _id=$("#_member_id").val();
    if(_id==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do",
       dataType:"text",
       data: {id:_id},
       success:function (data,textStatus){
          if(data=='false'){
       	    alert("사용할 수 있는 ID입니다.");
       	    $('#btnOverlapped').prop("disabled", true);
       	    $('#_member_id').prop("disabled", true);
       	    $('#member_id').val(_id);
          }else{
        	  alert("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }	

</script>
</head>
<body>
	<div id="app">
        
	<div class="topSkin web">
	    <div class="skin" style="background-image: url(${contextPath}/resources/image/test/topSkin2.png)"></div>
	    <h2 class="skinTitle">회원 가입</h2>
	</div>
	
	<section class="content">
	<h3 class="mainTitle">회원 정보 입력</h3>

    <form action="${contextPath}/member/addMember.do" method="POST" class="hostForm" enctype="multipart/form-data" data-parsley-validate="true" data-parsley-excluded="[type=submit], [type=button], [type=reset]">
        <input type="hidden" name="_token" value="em6Ryv4bY7MjatJP7hgplREpDenK5LGoFbzJXfrV">
        <div class="formBox">
        
            <article class="placeHostName">
                <div class="titleArea">
                    <h4>아이디<span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
                    <input type="text" name="member_id"  id="member_id" placeholder="이름을 정확히 입력해 주세요"
                            data-parsley-required="true"
                            data-parsley-maxlength="50"
                            data-parsley-required-message="이름을 입력해 주세요"
                            data-parsley-maxlength-message="최대 50자 이내로 문구를 입력해 주세요" />
                            
                	<input type="button"  id="btnOverlapped" value="중복체크" onClick="fn_overlapped()" />
                </div>
            </article>
            
            <article class="placeHostName">
                <div class="titleArea">
                    <h4>비밀번호<span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
                    <input type="text" name="member_pw" type="password" placeholder="이름을 정확히 입력해 주세요"
                            data-parsley-required="true"
                            data-parsley-maxlength="50"
                            data-parsley-required-message="이름을 입력해 주세요"
                            data-parsley-maxlength-message="최대 50자 이내로 문구를 입력해 주세요" />
                </div>
            </article>
            
            <article class="placeHostName">
                <div class="titleArea">
                    <h4>이름<span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
                    <input type="text" name="member_name" placeholder="이름을 정확히 입력해 주세요"
                            data-parsley-required="true"
                            data-parsley-maxlength="50"
                            data-parsley-required-message="이름을 입력해 주세요"
                            data-parsley-maxlength-message="최대 50자 이내로 문구를 입력해 주세요" />
                </div>
            </article>
       
            <article class="placeEmail emailCheckArea">
                <div class="titleArea">
                    <h4>이메일 <span class="requiredIcon">*</span></h4>
                </div>
                
                <div class="editArea">                	
                	<table>
                		<tr>
							<td>
								<input size="10px"   type="text" name="email1" /> @
								<input  size="10px"  type="text"name="email2" /> 
							 	<select name="email2" onChange=""	title="직접입력">
									<option value="non">직접입력</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="naver.com">naver.com</option>
									<option value="yahoo.co.kr">yahoo.co.kr</option>
									<option value="hotmail.com">hotmail.com</option>
									<option value="paran.com">paran.com</option>
									<option value="nate.com">nate.com</option>
									<option value="google.com">google.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="empal.com">empal.com</option>
									<option value="korea.com">korea.com</option>
									<option value="freechal.com">freechal.com</option>
								</select>
								<input type="checkbox" name="emailsts_yn" value="Y" checked /> 쇼핑몰에서 발송하는 e-mail을 수신합니다.
							</td>
							</tr>
					</table>
                </div>
            </article>
            
            <article class="placePhoneNumber phoneCheckArea_2">
                <div class="titleArea">
                    <h4>휴대전화 <span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
                	<table style="">
                    	<tr>
							<td>
								<select  name="tel1">
									<option>없음</option>
									<option selected value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
								</select> - 
								<input type="text" name="tel2"> - 
								<input type="text"name="tel3">
							</td>
						</tr>
					</table>
                </div>
            </article>
            
	        <article class="btnArea">
	            <input type="submit" class="submitBtn" value="회원 가입">
				<input type="reset" class="submitBtn" value="다시입력">
	        </article>
	        
        </form>
	</section>
	</div>
</body>