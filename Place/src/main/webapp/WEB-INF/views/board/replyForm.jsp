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
    obj.action="${contextPath}/board/listArticles.do";
    obj.submit();
  }
  
  var cnt=1;
  function fn_addFile(){
     $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
     cnt++;
  }  

</script>
 <title>답글쓰기 페이지</title>
</head>
<body>
<div id="app">
<div class="topSkin web">
    <div class="skin" style="background-image: url(${contextPath}/resources/image/test/topSkin2.png)"></div>
    <h2 class="skinTitle">답글쓰기</h2>
</div>
<section class="content">
<h3 class="mainTitle">장소 정보 입력</h3>
  <form name="frmReply" method="post"  
            action="${contextPath}/board/addReply.do"   enctype="multipart/form-data">
     <input type="hidden" name="parentNO" value="${parentNO}">
     <input type="hidden" name="level" value="${level}">
    <input type="hidden" name="_token" value="em6Ryv4bY7MjatJP7hgplREpDenK5LGoFbzJXfrV">
        <div class="formBox">
             <article class="placeHostName">
                <div class="titleArea">
                    <h4>작성자 <span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
                    <input type="text" name="name" value="${memberInfo.member_id}" readonly />
                </div>
            </article>
            
            <article class="companyInfo">
                <div class="titleArea">
                    <h4>글 제목<span class="requiredIcon">*</span></h4>
                </div>
                <div class="editArea">
           <input type="text" name="goods_name" />
            </div>
            
         <article class="companyInfo">
                <div class="titleArea">
                    <h4>글 내용</h4>
                </div>
                <div class="editArea">
            <textarea name="goods_contents" rows="10" cols="65" maxlength="4000"></textarea>
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
            
     <article class="btnArea">
     
          <input type="submit" value="답글반영하기" />
          
         
</article>
    

  
  
  </form>
  </section>
   </div>
</body>
</html>