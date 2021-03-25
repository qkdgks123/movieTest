<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function readURL(input) {
      if (input.files && input.files[0]) {
         var reader = new FileReader();
         reader.onload = function (e) {
           $('#preview').attr('src', e.target.result);
          }
         reader.readAsDataURL(input.files[0]);
      }
  }  
  function backToList(obj){
    obj.action="${contextPath}/main/main.do";
    obj.submit();
  }
  
  var cnt=1;
  function fn_addFile(){
     $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
     cnt++;
  }  

</script>
 <title>글쓰기창</title>
</head>
<body>
<div id="app">
        
<div class="topSkin web">
    <div class="skin" style="background-image: url(${contextPath}/resources/image/test/topSkin2.png)"></div>
    <h2 class="skinTitle">장소 등록하기</h2>
</div>
<section class="content">
    <h3 class="mainTitle">장소 정보 입력</h3>

    <form name="articleForm" action="${contextPath}/board/addNewPlace.do" method="POST" class="hostForm" enctype="multipart/form-data" data-parsley-validate="true" data-parsley-excluded="[type=submit], [type=button], [type=reset]">
        <input type="hidden" name="_token" value="em6Ryv4bY7MjatJP7hgplREpDenK5LGoFbzJXfrV">
        <div class="formBox">
        
            <article class="placeHostName">
                <div class="titleArea">
                    <h4>신청자 <span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
                    <input type="text" name="name" value="${memberInfo.member_id}" readonly />
                </div>
            </article>
             
            <article class="companyInfo">
                <div class="titleArea">
                    <h4>장소 이름<span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
                    <input type="text" name="GOODS_NAME" placeholder="사업자등록증에 기재된 장소명"
                            data-parsley-required="true"
                            data-parsley-maxlength="30"
                            data-parsley-required-message="장소 명을 입력해 주세요"
                            data-parsley-maxlength-message="최대 30자 이내로 문구를 입력해 주세요" />
                </div>
            </article>
            
            
            <article class="companyInfo">
                <div class="titleArea">
                    <h4>수용인원</h4>
                </div>
                <div class="editArea">
                    <input type="text" name="GOODS_PEOPLE_NUM" placeholder="사업자등록증에 기재된 수용인원 "
                            data-parsley-required="false"
                            data-parsley-maxlength="50"
                            data-parsley-required-message="업태를 입력해 주세요"
                            data-parsley-maxlength-message="최대 50자 이내로 문구를 입력해 주세요" />
                </div>
            </article>
            
            <article class="companyInfo">
                <div class="titleArea">
                    <h4>문의처<span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
                    <input type="text" name="GOODS_TEL" placeholder="사업자등록증에 기재된 번호"
                            data-parsley-required="true"
                            data-parsley-error-message="사업자 등록번호를 입력해 주세요" />
                </div>
            </article>
            
            <article class="companyInfo">
                <div class="titleArea">
                    <h4>플레이스 정보</h4>
                </div>
                <div class="editArea">
                    <textarea name="GOODS_CONTENTS" 
                            data-parsley-required="false" placeholder="최대 50자 이내로 문구를 입력해 주세요"
                            data-parsley-maxlength="50"
                            data-parsley-required-message="종목을 입력해 주세요"
                            data-parsley-maxlength-message="최대 50자 이내로 문구를 입력해 주세요" rows="10" cols="65" maxlength="4000"></textarea>
                </div>
            </article>
            
            <article class="companyInfo">
                <div class="titleArea">
                    <h4>대관 금액</h4>
                </div>
                <div class="editArea">
                    <input type="text" name="GOODS_PRICE" 
                            data-parsley-required="false"
                            data-parsley-maxlength="50"
                            data-parsley-required-message="업태를 입력해 주세요"
                            data-parsley-maxlength-message="최대 50자 이내로 문구를 입력해 주세요" />
                </div>
            </article>
            
            <article class="companyInfo">
                <div class="titleArea">
                    <h4>상세 주소</h4>
                </div>
                <div class="editArea">
                    <input type="text" name="GOODS_LOCATION" placeholder="사업자등록증에 기재된 주소"
                            data-parsley-required="false"
                            data-parsley-maxlength="50"
                            data-parsley-required-message="업태를 입력해 주세요"
                            data-parsley-maxlength-message="최대 50자 이내로 문구를 입력해 주세요"/>
                </div>
            </article>
                   <article class="companyInfo">
                <div class="titleArea">
                    <h4>대관 시간</h4>
                </div>
                <div class="editArea">
                    <input type="text" name="GOODS_OPERATION_TIME" 
                            data-parsley-required="false"
                            data-parsley-maxlength="50"
                            data-parsley-required-message="업태를 입력해 주세요"
                            data-parsley-maxlength-message="최대 50자 이내로 문구를 입력해 주세요" />
                </div>
            </article>
            
            <article class="companyInfo">
                <div class="titleArea">
                    <h4>장소 타입</h4>
                </div>
                <div class="editArea">
                <select  name="goods_type_id">
							<option selected value="1">파티룸</option>
							<option value="2">스터디룸</option>
							<option value="3">연습실</option>
							<option value="4">공연장</option>
							<option value="5">다목적홀</option>
							<option value="6">연수원</option>
					</select>  
                    
                </div>
            </article>
            
            
            
           <article class="placeBankBookInfo">
                <div class="titleArea">
                    <h4>이미지파일 첨부 :</h4>
                </div>
                <div class="editArea">
                    <input type="file" name="imageFileName" onchange="readURL(this);" id="payInfoApply" class="fileUpload" />
                    <label for="payInfoApply" class="hostBtn">첨부하기</label>
                    <img  id="preview" src="#"   width=200 height=200/>
                    <div class="bankBookErrorWrp"></div>
                </div>
            </article>
            
     <!--        <article class="placePayInfo">
                <div class="titleArea">
                    <h4>이미지파일 첨부</h4>
                </div>
                <div class="editArea">
                    <input type="button" value="파일 추가" onClick="fn_addFile()" id="companyUpload" class="fileUpload">
                    <label for="companyUpload" class="hostBtn">첨부하기</label>
                    <div class="payInfoErrorWrap"></div>
                </div>
            </article> -->
            
          
        <div id="d_file"></div>
        <article class="btnArea">
            <input type="submit" value="장소 등록 하기"/>
          
        </article>
    </form>
</section>
    </div>
</body>
</html>