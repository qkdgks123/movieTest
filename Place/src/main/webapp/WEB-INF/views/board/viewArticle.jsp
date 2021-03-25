<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("viewArticle.jsp\n=====================================");
%>
<head>
<meta charset="UTF-8">
<title>글보기</title>
<style>
#tr_file_upload {
	display: none;
}

#tr_btn_modify {
	display: none;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function backToList(obj){
    obj.action="${contextPath}/board/listArticles.do";
    obj.submit();
 }
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#preview').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}  
function fn_modify_article(obj){
	 obj.action="${contextPath}/board/modArticle.do";
	 obj.submit();
}
function fn_enable(obj){
	document.getElementById("i_title").disabled=false;
	document.getElementById("i_content").disabled=false;
	document.getElementById("i_people").disabled=false;
	document.getElementById("i_tel").disabled=false;
	document.getElementById("i_price").disabled=false;
	document.getElementById("i_location").disabled=false;
	document.getElementById("i_time").disabled=false;
	document.getElementById("i_imageFileName").disabled=false;
	document.getElementById("tr_btn_modify").style.display="block";
	document.getElementById("tr_file_upload").style.display="block";
	document.getElementById("tr_btn").style.display="none";
}

function fn_remove_article(url,ORDER_SEQ_NUM){
	var result=confirm("삭제하시겠습니까?");//true,false
	//alert(result);
	if(result){//result==true
	//현재페이지의 form객체 생성 - document.createElement("form");
	var form=document.createElement("form");//form객체 생성
	form.setAttribute("method","post");
	form.setAttribute("action",url);
	//현재페이지의 <input>요소 생성 - document.createElement("input");
	var articleNOInput = document.createElement("input");
	articleNOInput.setAttribute("type","hidden");
	articleNOInput.setAttribute("name","ORDER_SEQ_NUM");
	articleNOInput.setAttribute("value",ORDER_SEQ_NUM);
	
	//form객체에 input 태그 추가 - 자식요소추가 appendChild(자식요소)
	form.appendChild(articleNOInput);
	//document에 form객체 추가 - 현재페이지에 요소 추가 document.body.appendChild(자식요소)
	document.body.appendChild(form);//
	form.submit();//submit버튼 클릭효과 
	}else{
		return false;
	}
}
function fn_reply_form(url,parentNO){
	/* <form method="post" action="${contextPath}/board/replyForm.do>" */
	 var form = document.createElement("form");
	 form.setAttribute("method", "post");
	 form.setAttribute("action", url);
	 /* <input type="hidden" name="parentNO"  value="3">*/
	 var parentNOInput = document.createElement("input");
	 parentNOInput.setAttribute("type","hidden");
     parentNOInput.setAttribute("name","parentNO");
     parentNOInput.setAttribute("value", parentNO);
     
     form.appendChild(parentNOInput);
     document.body.appendChild(form);
     form.submit();
}
</script>
</head>
<body>
	<div id="app">
		<div class="topSkin web">
			<div class="skin"
				style="background-image: url(${contextPath}/resources/image/test/topSkin2.png)"></div>
			<h2 class="skinTitle">장소 정보 INFO</h2>
		</div>

		<section class="content">
			<h3 class="mainTitle">상세 정보</h3>

			<form name="frmArticle" method="post" class="hostForm"
				action="${contextPath}" enctype="multipart/form-data"
				data-parsley-validate="true"
				data-parsley-excluded="[type=submit], [type=button], [type=reset]">
				<!-- <table  border=0  align="center"> -->
				<input type="hidden" name="_token"
					value="em6Ryv4bY7MjatJP7hgplREpDenK5LGoFbzJXfrV"> <input
					type="hidden" name="level" value="${article.level}">

				<div class="formBox">

					<article class="placeHostName">
						<div class="titleArea">
							<h4>
								글 번호 <span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<input type="text" value="${article.ORDER_SEQ_NUM }" disabled />
							<input type="hidden" name="ORDER_SEQ_NUM"
								value="${article.ORDER_SEQ_NUM}" />
						</div>
					</article>

					<article class="placeHostName">
						<div class="titleArea">
							<h4>
								신청자 <span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<input type=text value="${memberInfo.member_id}" name="writer"
								disabled />
						</div>
					</article>



					<article class="placeHostName">
						<div class="titleArea">
							<h4>
								장소 이름 <span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
						<br>
							<input type=text value="${article.GOODS_NAME}" name="GOODS_NAME"
								id="i_title" disabled /> 
						</div>
					</article>

					<article class="placeHostName">
						<div class="titleArea">
							<h4>
								수용 인원<span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<input type=text value="${article.GOODS_PEOPLE_NUM}"
								name="GOODS_PEOPLE_NUM" id="i_people" disabled />
						</div>
					</article>

					<article class="placeHostName">
						<div class="titleArea">
							<h4>
								플레이스 정보<span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<textarea rows="20" cols="60" name="GOODS_CONTENTS"
								id="i_content" disabled>${article.GOODS_CONTENTS}</textarea>
						</div>
					</article>

					<article class="companyInfo">
						<div class="titleArea">
							<h4>
								문의처<span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<input type=text value="${article.GOODS_TEL}" name="GOODS_TEL"
								id="i_tel" disabled />
						</div>
					</article>

					<article class="companyInfo">
						<div class="titleArea">
							<h4>
								대관금액<span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<input type=text value="${article.GOODS_PRICE}"
								name="GOODS_PRICE" id="i_price" disabled />
						</div>
					</article>

					<article class="companyInfo">
						<div class="titleArea">
							<h4>
								상세주소<span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<input type=text value="${article.GOODS_LOCATION}"
								name="GOODS_LOCATION" id="i_location" disabled />

						</div>
					</article>


					<article class="companyInfo">
						<div class="titleArea">
							<h4>
								대관 가능 시간<span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<input type=text value="${article.GOODS_OPERATION_TIME}"
								name="GOODS_OPERATION_TIME" id="i_time" disabled />
						</div>
					</article>




					<article class="placeHostName">
						<div class="titleArea">
							<h4>
								등록 일자<span class="requiredIcon">*</span>
							</h4>
						</div>
						<div class="editArea">
							<input type=text
								value="<fmt:formatDate value="${article.writeDate}" />" disabled />
						</div>
					</article>


					<c:choose>
						<c:when
							test="${not empty article.imageFileName && article.imageFileName!='null' }">
							<article class="placeBankBookInfo">
								<div class="titleArea">
									<h4>
										장소 이미지<span class="requiredIcon">*</span>
									</h4>
								</div>

								<div class="editArea">
									<input type="file" name="imageFileName"
										onchange="readURL(this);" id="i_imageFileName"
										class="fileUpload" disabled /> <img id="preview"
										src="${contextPath}/download.do?ORDER_SEQ_NUM=${article.ORDER_SEQ_NUM}&imageFileName=${article.imageFileName}"
										width=200 height=200 />
									<div class="bankBookErrorWrp"></div>
									<input type="hidden" name="originalFileName"
										value="${article.imageFileName}" />

								</div>
							</article>
						</c:when>
						<c:otherwise>
							<article class="placeBankBookInfo">
								<div id="tr_file_upload">
									<div class="titleArea">
										<h4>
											장소 이미지<span class="requiredIcon">*</span>
										</h4>
									</div>


									<div class="editArea">
									<img id="preview" src="${contextPath}/download.do?ORDER_SEQ_NUM=${article.ORDER_SEQ_NUM}&imageFileName=${article.imageFileName}" width=200 height=200 /> 
										<input type="file" name="imageFileName"
											onchange="readURL(this);" id="i_imageFileName" disabled />
											<input type="hidden" name="originalFileName" value="${article.imageFileName}" />

									</div>
								</div>
							</article>
						</c:otherwise>
					</c:choose>

					<article class="btnArea">
						<div id="tr_btn_modify" align="center">

							<input type=button value="수정반영하기"
								onClick="fn_modify_article(frmArticle)" /> <input type=button
								value="취소" onClick="backToList(frmArticle)" />

						</div>
					</article>
					<article class="btnArea" align="center">
						<div id="tr_btn" >
							<c:if test="${member.id == article.id }">
								<input type=button value="수정하기" onClick="fn_enable(this.form)">
								<input type=button value="삭제하기"
									onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.ORDER_SEQ_NUM})">
							</c:if>
							<input type=button value="리스트로 돌아가기"
								onClick="backToList(this.form)"> <input type=button
								value="답글쓰기"
								onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.ORDER_SEQ_NUM})">

						</div>
					</article>









					<%--  <tr>
   <td width=150 align="center" bgcolor=#FF9933>
      글번호
   </td>
   <td >
    <input type="text"  value="${article.ORDER_SEQ_NUM }"  disabled />
    <input type="hidden" name="ORDER_SEQ_NUM" value="${article.ORDER_SEQ_NUM}"  />
   </td>
  </tr>
  
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      작성자 아이디
   </td>
   <td >
    <input type=text value="${memberInfo.member_id}" name="writer"  disabled />
   </td>
  </tr>
  
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      제목 
   </td>
   <td>
    <input type=text value="${article.GOODS_NAME}"  name="GOODS_NAME"  id="i_title" disabled />
   </td>   
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      내용
   </td>
   <td>
    <textarea rows="20" cols="60"  name="GOODS_CONTENTS"  id="i_content"  disabled />${article.GOODS_CONTENTS}</textarea>
   </td>  
  </tr>
  
  <c:choose> 
	  <c:when test="${not empty article.imageFileName && article.imageFileName!='null' }">
	   	<tr>
		    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
		      이미지
		   </td>
		   <td>
		     <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
		    <img src="${contextPath}/download.do?ORDER_SEQ_NUM=${article.ORDER_SEQ_NUM}&imageFileName=${article.imageFileName}" id="preview" style="width: 30%;height: 20%" /><br>
		   </td>   
		  </tr>  
		  <tr>
		    <td ></td>
		    <td>
		       <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
		    </td>
		  </tr> 
		 </c:when>
		 <c:otherwise>
		    <tr  id="tr_file_upload" >
				    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
				      이미지
				    </td>
				    <td>
				      <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
				    </td>
			    </tr>
			    <tr>
				    <td ></td>
				    <td>
				       <img id="preview"  /><br>
				       <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
				    </td>
			  </tr>
		 </c:otherwise>
	 </c:choose>
	 
  <tr>
	   <td width="150" align="center" bgcolor="#FF9933">
	      등록일자
	   </td>
	   <td>
	    <input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />
	   </td>   
  </tr>
  <tr   id="tr_btn_modify"  align="center"  >
	   <td colspan="2"   >
	       <input type=button value="수정반영하기"  onClick="fn_modify_article(frmArticle)"  >
           <input type=button value="취소"  onClick="backToList(frmArticle)">
	   </td>   
  </tr>
    
  <tr  id="tr_btn"    >
   <td colspan="2" align="center">
       <c:if test="${member.id == article.id }">
	      <input type=button value="수정하기" onClick="fn_enable(this.form)">
	      <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.ORDER_SEQ_NUM})">
	    </c:if>
	    <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
	     <input type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.ORDER_SEQ_NUM})">
   </td>
  </tr>
 </table> --%>
			</form>
</body>
</html>