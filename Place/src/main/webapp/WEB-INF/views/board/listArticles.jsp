<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>글 목록창</title>
<style>
.cls1{
  text-decoration: none;/* 링크택의 밑줄 제거 */
  }
.cls2{
 text-align: center;
 font-size: 20px;
}
tr {
padding: 10000;
}
</style>
</head>
<script>
function fn_articleForm(isLogOn, articleForm, loginForm){
   if(isLogOn !='' && isLogOn!='false'){
      location.href=articleForm;//게시글 입력페이지 이동
   }else{
      alert('로그인 후 글쓰기가 가능합니다.');
      location.href=loginForm+"?action=/board/articleForm.do";//게시글 입력페이지 이동
   }
}

function fn_accept_article(url,ORDER_SEQ_NUM){
   var result=confirm("승낙하시겠습니까?");//true,false
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

</script>
<body>
<div id="app">
<div class="topSkin web">
    <div class="skin" style="background-image: url(${contextPath}/resources/image/test/topSkin2.png)"></div>
    <h2 class="skinTitle">장소 리스트</h2>
</div>
<section class="content" style="height: 25em;">
    <h3 class="mainTitle">장소 리스트</h3><br>
    <table align="center" width="100%">
  <tr align="center" bgcolor="#FBEFF2" >
     <td style="padding: 10px;">글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
          <c:if test="${isLogOn==true and memberInfo.member_id =='admin' }">  
           <td>관리자</td>
        </c:if>
  </tr>
  <c:choose>
  <c:when test="${articlesList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesList !=null }" >
    <c:forEach var="article" items="${articlesList}" varStatus="articleNum" >
     <tr align="center">
   <td width="5%">${articleNum.count}</td>
   <td width="10%">${memberInfo.member_id}</td>
   <td align='left'  width="35%">
     <span style="padding-right:30px"></span>
      <c:choose>
         <c:when test='${article.level > 1 }'>  
            <c:forEach begin="1" end="${article.level}" step="1">
                 <span style="padding-left:20px"></span>    
            </c:forEach>
            <span style="font-size:12px;">[승인 완료]</span>
                   <a class='cls1' href="${contextPath}/board/viewArticle.do?ORDER_SEQ_NUM=${article.ORDER_SEQ_NUM}">${article.GOODS_NAME}</a>
             </c:when>
             <c:otherwise>
               <a class='cls1' href="${contextPath}/board/viewArticle.do?ORDER_SEQ_NUM=${article.ORDER_SEQ_NUM}">${article.GOODS_NAME }</a>
             </c:otherwise>
           </c:choose>
     
     <td  width="10%">${article.writeDate}</td> 
       <c:if test="${isLogOn==true and memberInfo.member_id =='admin' }">  
      <td colspan="2"><input type=button value="승낙하기" onClick="fn_accept_article('${contextPath}/admin/goods/adminAcceptArticle.do', ${article.ORDER_SEQ_NUM})" /></td> 
      </c:if>
   </tr>
    </c:forEach>
     </c:when>
    </c:choose></table>
    <!--   글쓰기 버튼 -->
    <br>
<a class="cls1"
   href="javascript:fn_articleForm('${isLogOn}',
                                                  '${contextPath}/board/addNewPlaceForm.do',
                                                  '${contextPath}/member/loginForm.do')">
     <article class="btnArea">
  <!--  <p style="margin-left: 45%; margin-right: 45%">글쓰기</p> -->
  <input style="margin-left: 40%; margin-right: 40%" type="submit" value="장소 신청하기" />
   </article>
   </a>
   </section>
</div>

</body>
</html>