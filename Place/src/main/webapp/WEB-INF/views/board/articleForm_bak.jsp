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
<h1 style="text-align:center">장소 등록하기</h1>
  <form name="articleForm" method="post"  action="${contextPath}/board/addNewPlace.do"   enctype="multipart/form-data">
    <table border="0" align="center">
      <tr>
               <td align="right">신청자</td>
               <td colspan=2  align="left"><input type="text" size="20" maxlength="100"  value="${memberInfo.member_id}" readonly/> </td>
         </tr>
        <tr>
            <td align="right">장소 이름 </td>
            <td colspan="2"><input type="text" size="67"  maxlength="500" name="GOODS_NAME" /></td>
       </tr>
          <tr>
            <td align="right">수용인원 </td>
            <td colspan="2"><input type="text" size="67"  maxlength="500" name="GOODS_PEOPLE_NUM" /></td>
       </tr>
          <tr>
            <td align="right" valign="top"><br>플레이스 정보 </td>
            <td colspan=2><textarea name="GOODS_CONTENTS" rows="10" cols="65" maxlength="4000"></textarea> </td>
        </tr>
        <tr>
            <td align="right">문의처 </td>
            <td colspan="2"><input type="text" size="67"  maxlength="500" name="GOODS_TEL" /></td>
       </tr>
       <tr>
            <td align="right">대관 금액</td>
            <td colspan="2"><input type="text" size="67"  maxlength="500" name="GOODS_PRICE" /></td>
       </tr>
           <tr>
            <td align="right">상세 주소</td>
            <td colspan="2"><input type="text" size="67"  maxlength="500" name="GOODS_LOCATION" /></td>
       </tr>
        <tr>
            <td align="right">대관 시간</td>
            <td colspan="2"><input type="text" size="67"  maxlength="500" name="GOODS_OPERATION_TIME" /></td>
       </tr>
           
           
           
     <tr>
           <td align="right">이미지파일 첨부:  </td>
           <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
           <td><img  id="preview" src="#"   width=200 height=200/></td>
           
           
           <td align="right">이미지파일 첨부</td>
            <td align="left"> <input type="button" value="파일 추가" onClick="fn_addFile()"/></td>
            
            
      </tr>
      <tr>
         <td colspan="4"><div id="d_file"></div></td>
      </tr>
       <tr>
         <td align="right"> </td>
         <td colspan="2">
          <input type="submit" value="신청하기" />
          <input type=button value="뒤로가기"onClick="backToList(this.form)" />
         </td>
     </tr>
    </table>
  </form>
</body>
</html>