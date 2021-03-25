<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������ �α���</title>
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
</head>
<script>
$(document).ready(function(){
	$("#btnLogin").click(function(){
		var userId = $("#userId").val();
		var userPw = $("#userId").val();
		if(userId==""){
			alert("���̵� �Է��ϼ���.");
			$("#userId").focus();
			return;
		}
		if(userPw==""){
			alert("���̵� �Է��ϼ���.");
			$("#userId").focus();
			return;
		}
		document.form1.action="/movie/loginCheck";
		//����
		document.form1.submit();
	});
});
</script>
<body>
<jsp:include page="../include/header.jsp" />
<div class="jumbotron">
		<div class="container">
			<h1>������ �α���</h1>
		</div>
	</div>
<div class="wrapper" style="width: 100%; max-width:1440px; margin: 0 auto;">
<div class="container">
<form name="form1" method="post" role="form" accept-charset="UTF-8">
  
    <div class="input-group">
	<input type="text" id="userId" name="userId"  placeholder="���̵� �Է��ϼ���" 
	class="form-control" />
	</div><br>

    <div class="input-group">
		<input	type="password" id="userPw" name="userPw"  placeholder="��й�ȣ�� �Է��ϼ���"
			class="form-control" />
			</div><br>
    <button type="submit" id="btnLogin"
					class="btn btn-default btn-block" value="�α���">�α���</button>
    <c:if test="${msg=='failure'}">
        <div style="color: red">
        	���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.
        </div>
      </c:if>
      <c:if test="${msg=='logout'}">
        <div style="color: red">
        	�α׾ƿ� �Ǿ����ϴ�.
        </div>
      </c:if>
 	</form>
</div>
</div>

<jsp:include page="../include/footer.jsp" />
</body>
</html>