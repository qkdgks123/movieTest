<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="customerList" value="${customerList}"/>
<c:set var="total_customer" value="${total_customer}"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resources/css/mybootstrap.min.css" />
<meta charset="EUC-KR">
<title>��ȭ �����ִ� â</title>
<!-- CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script>
	function allCheck(){
		if($('#chkAll').prop("checked")){
			$("input[name=chk]").prop("checked", true);
		}else{
			$("input[name=chk]").prop("checked", false);
		}
	}
	
	function delchk(){
		var delchk = []; // key ���� ���� �迭
		$('#chk:checked').each(function(){//each�� ���� �ݺ���
			delchk.push($(this).val());//$(this):üũ�ڽ��� 0,1,2...��° ������ �׸���� ����Ŵ
		});
		
		if(delchk.length==0){
			alert('������ ȸ���� �����ϴ�.');
			return false;
		}else{
			var loc ="/movie/CustomerDelete?";
			var qur = '';
			
			if(confirm('�����Ͻ� ȸ�� '+delchk.length+'���� �����Ͻðڽ��ϱ�?')){
				for(var i = 0;i<delchk.length;i++){
					if(i==(delchk.length-1)){
						qur+='id='+delchk[i];
					}else{
						qur+='id='+delchk[i]+'&';
					}
				}
				
				loc=loc+qur;
				location.href=loc; 
			}
		}
	}
	
	function delMember($id, $name){
		var id = $id;
		var name = $name;
		
		if(confirm('���̵�:'+id+'\n�̸�:'+name+' ���� �����Ͻðڽ��ϱ�?')){
			location.href = "/movie/CustomerDelete?id="+id;
		}
	}
	
</script>

</head>
<body>

	<jsp:include page="../include/header.jsp" />
		<div class="jumbotron">
			<div class="container">
				<h1>ȸ�� ����</h1>
			</div>
		</div>
		<!-- content -->
		<div class="wrapper" style="width: 100%; max-width: 1440px; margin: 0 auto;">
			<div style="padding-top: 50px;">
				<div style="float:right">��ü ȸ����: ${total_customer}��</div>
				<table class="table table-striped table-bordered table-hover">
					<tr>
						<th style="text-align: center; width: 5%; vertical-align: middle">
							<input type="checkbox" name="chkAll" id="chkAll" onclick="allCheck()">
						</th>
						<th style="width: 8%; text-align: center; vertical-align: middle">���̵�</th>
						<th style="width: 13%; text-align: center; vertical-align: middle">��й�ȣ</th>
						<th style="width: 13%; text-align: center; vertical-align: middle">�̸�</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">����</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">�������</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">�̸���</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">��ȭ��ȣ</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">�����ȣ</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">�ּ�</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">���ּ�1</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">���ּ�2</th>
						<th style="width: 18%; text-align: center; vertical-align: middle">������</th>
						<th style="width: 8%; text-align: center; vertical-align: middle">����</th>
					</tr>
					
					
					<c:if test="${not empty customerList}">
			    		<c:forEach var="customerList" items="${customerList}">
							<tr>
								<th style="text-align: center; vertical-align: middle">
									<input type="checkbox" name="chk" id="chk" value="${customerList.id }">
								</th>
								<th style="text-align: center; vertical-align: middle">${customerList.id }</th>
								<th style="text-align: center; vertical-align: middle">****</th>
								<th style="text-align: center; vertical-align: middle">${customerList.name }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.gender }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.birth }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.mail }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.phone }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.zipcode }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.address }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.addressdetail }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.extraAddress }</th>
								<th style="text-align: center; vertical-align: middle">${customerList.regdate }</th>
								<th style="text-align: center; vertical-align: middle">
									<button type="button" class="btn btn-info-delete" name="delBtn"
												id="delBtn" onclick="delMember('${customerList.id }', '${customerList.name }')">����
									</button>
								</th>		
							</tr>	
						</c:forEach>
					</c:if>
				</table>
			</div>
			<button type="button" name="delete" id="delete" class="btn btn-info-delete" onclick="delchk()">ȸ�� ����</button>
			<a href="/movie/">
				<button type="button" name="backPage" id="backPage" class="btn">�ǵ��ư���</button>
			</a>
		</div>
		
		<!--  ����¡ ó��  -->
		<div>
			<ul class="pagination" style="justify-content: center;">
				<c:if test="${pageMaker.prev}">
					<li class="page-item">
					<a class="page-link" href="/movie/managemember?pageNum=${pageMaker.startPage-1}">����
							</a></li>
				</c:if>

				<c:forEach var="num" begin="${pageMaker.startPage}"
					end="${pageMaker.endPage}">
					<li class="page-item"><a class="page-link" href="/movie/managemember?pageNum=${num}">${num}</a></li>

				</c:forEach>

				<c:if test="${pageMaker.next}">
					<li class="page-item"><a class="page-link" href="/movie/managemember?pageNum=${pageMaker.endPage + 1}">����
							</a></li>
				</c:if>
			</ul>
		</div>
	<jsp:include page="../include/footer.jsp" />

</body>
</html>







