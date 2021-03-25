<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- <%@ include file="dbconn.jsp" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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
<title>Insert title here</title>
<style>
.btn-info-addMovie{
	color: #1DDB16;
	background-color: #B7F0B1;
	border: 1px solid #22741C;
}

.btn-info-backPage{
	color: #353535;
	background-color: #BDBDBD;
	border: 1px solid #000000;
}
.btn-info-addMovie:hover{
	cursor: pointer;
	color: white;
	background-color: #22741C;
}

.btn-info-backPage:hover{
	cursor: pointer;
	color: white;
	background-color: #000000;
}
</style>
<link rel="stylesheet" href="./resources/css/mybootstrap.min.css">
<script src="./resources/js/jquery.js"></script>
<script>
	
	function allCheck(){
		if($('#chkAll').prop("checked")){
			$("input[name=chk]").prop("checked", true);
		}else{
			$("input[name=chk]").prop("checked", false);
		}
	}
</script>
</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1>��ȭ ����</h1>
		</div>
	</div>

	<!-- content -->
	<div class="wrapper" style="width: 100%; max-width:1440px; margin: 0 auto;">
	<!-- ��¸���Ʈ ���κ� -->
			<div style="padding-top: 50px;">
				<table class="table table-striped table-bordered table-hover">
					<tr>
						<th style=" width:3%; text-align: center; vertical-align: middle"><input type="checkbox" name="chkAll" id="chkAll" onclick="allCheck()"></th>
						<th style="width:5%; text-align: center; vertical-align: middle">��ȣ</th>
						<th style="width:5%; text-align: center; vertical-align: middle">��ȭ�ڵ�</th>
						<th style="width:150px; text-align: center; vertical-align: middle">������</th>
						<th style="width:7%; text-align: center; vertical-align: middle">��ȭ����</th>
						<th style="width:7%; text-align: center; vertical-align: middle">����</th>
						<th style="width:10%; text-align: center; vertical-align: middle">���</th>
						<th style=" width:10%; text-align: center; vertical-align: middle">��ȭ��</th>
						<th style="width:7%; text-align: center; vertical-align: middle">����Ÿ��</th>
						<th style="width:7%; text-align: center; vertical-align: middle">������</th>
						<th style="text-align: center; vertical-align: middle">�ٰŸ�</th>
						<th style="width:5%; text-align: center; vertical-align: middle">���<br>(����/����)</th>
					
					</tr>

					<tr>
						<th style="text-align: center; vertical-align: middle"><input type="checkbox" name="chk" id="chk"></th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle"><img src="/resources/img/F3.jpg" style="width: 100%"></th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="vertical-align: middle"></th>
						<th style="text-align: center; vertical-align: middle">
							<a  href="#"><button type="button" class="btn btn-outline-primary">����</button></a><br><br>
							<a href="delete.jsp?movieCode="><button type="button" class="btn btn-outline-danger">����</button></a>
						</th>		
					</tr>
					<tr>
						<th style="text-align: center; vertical-align: middle"><input type="checkbox" name="chk" id="chk"></th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle"><img src="/resources/img/F1.jpg" style="width: 100%"></th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="vertical-align: middle"></th>
						<th style="text-align: center; vertical-align: middle">
							<a  href="#"><button type="button" class="btn btn-outline-primary">����</button></a><br><br>
							<a href="delete.jsp?movieCode="><button type="button" class="btn btn-outline-danger">����</button></a>
						</th>		
					</tr>
					
					<tr>
						<th style="text-align: center; vertical-align: middle"><input type="checkbox" name="chk" id="chk"></th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle"><img src="/resources/img/F2.jpg" style="width: 100%"></th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="text-align: center; vertical-align: middle">����</th>
						<th style="vertical-align: middle"></th>
						<th style="text-align: center; vertical-align: middle">
							<a  href="#"><button type="button" class="btn btn-outline-primary">����</button></a><br><br>
							<a href="delete.jsp?movieCode="><button type="button" class="btn btn-outline-danger">����</button></a>
						</th>		
					</tr>

			</table>
	</div>
	<div class="text-right">
			<a href="addMovie.jsp" ><button type="button" class="btn btn-success">��ȭ �߰�</button></a>
			<a href="delete.jsp?movieCode="><button type="button" class="btn btn-danger">��ȭ ����</button></a>
			<a href="adminPage.jsp"><button type="button" class="btn btn-dark">�ǵ��ư���</button></a>
	</div>
	</div>
	<jsp:include page="../include/footer.jsp"/>
	
	
	
</body>
</html>
