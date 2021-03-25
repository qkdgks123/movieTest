<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="theaterList" value="${theaterList}"/>
<c:set var="total_theater" value="${total_theater}"/>
<script>/* �����¼� ǥ�� */
window.onload = function(){

}

</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ȭ�� ����</title>
<style>
.btn-info-addMovie {
	color: #1DDB16;
	background-color: #B7F0B1;
	border: 1px solid #22741C;
}

.btn-info-backPage {
	color: #353535;
	background-color: #BDBDBD;
	border: 1px solid #000000;
}

.btn-info-addMovie:hover {
	cursor: pointer;
	color: white;
	background-color: #22741C;
}

.btn-info-backPage:hover {
	cursor: pointer;
	color: white;
	background-color: #000000;
}
</style>
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

<script>
	function allCheck() {
		if ($('#chkAll').prop("checked")) {
			$("input[name=chk]").prop("checked", true);
		} else {
			$("input[name=chk]").prop("checked", false);
		}
	}

	function delchk() {
		var delchk = [];
		$('#chk:checked').each(function() {//each:�ݺ���
			delchk.push($(this).val());//delchk���ٰ� $(this).value(=$("#chk:checked"))�� �ִ´�
		});

		if (delchk.length == 0) {
			alert('�����Ͻ� ��ȭ���� �����ϴ�.');
			return false;
		} else {
			var loc = "/movie/TheaterDelete?";
			var qur = '';

			if (confirm('�����Ͻ� ��ȭ�� ' + delchk.length + '���� �׸��� �����Ͻðڽ��ϱ�?')){
				for (var i = 0; i < delchk.length; i++) {
					if (i == (delchk.length - 1)) {
						qur += 'theater_code=' + delchk[i];
					} else {
						qur += 'theater_code=' + delchk[i] + '&';
					}
				}
				loc = loc + qur;
				location.href = loc;
			}
		}
	}

	function delMovie($theaterCode, $theaterArea, $theaterName) {
		var theater_code = $theaterCode;
		var theater_area = $theaterArea;
		var theater_name = $theaterName;
		if (confirm('��ȭ�� �ڵ�:[' + theater_code + ']\n����:[' + theater_area + ']\n'
							+'��ȭ����:[' + theater_name + ']��(��) �����Ͻðڽ��ϱ�?')) {
			location.href = "/movie/TheaterDelete?theater_code=" + theater_code;
		}
	}
</script>	
</head>
<body>

	<jsp:include page="../include/header.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1>��ȭ�� ����</h1>
		</div>
	</div>
	<!-- content -->
	<div class="wrapper"
		style="width: 100%; max-width: 1440px; margin: 0 auto;">
	<!-- ��¸���Ʈ ���κ� -->
	<div style="padding-top: 50px;">
		<div style="float:right">��ü ��ȭ����: ${total_theater}��</div>
		<table class="table table-striped table-bordered table-hover" >
			<tr>
				<th style="width:10%; text-align: center; vertical-align: middle">
					<input type="checkbox" name="chkAll" id="chkAll" onclick="allCheck()">
				</th>
				<th style="text-align: center; vertical-align: middle">��ȭ���ڵ�</th>
				<th style=" text-align: center; vertical-align: middle">����</th>
				<th style=" text-align: center; vertical-align: middle">��ȭ����</th>
				<th style=" text-align: center; vertical-align: middle">���<br>(����)</th>
			</tr>

			<c:if test="${not empty theaterList}">
			    <c:forEach var="theaterList" items="${theaterList}">
				
					<tr>
						<th style="text-align: center; vertical-align: middle">
							<input type="checkbox" name="chk" id="chk" value="${theaterList.theater_code}">
						</th>
						<th style="text-align: center; vertical-align: middle">${theaterList.theater_code}</th>
						<th style="text-align: center; vertical-align: middle">${theaterList.theater_area}</th>
						<th style="text-align: center; vertical-align: middle">${theaterList.theater_name}</th>
						
						<th style="text-align: center; vertical-align: middle">
							<button type="button" class="btn btn-info-delete" onclick="delMovie(
								'${theaterList.theater_code}', '${theaterList.theater_area}', 
								'${theaterList.theater_name}')">����
							</button>
						</th>
					</tr>
				</c:forEach>
			</c:if>

		</table>
	</div>
	<div class="text-right">
	<a href="/movie/TheaterInsertForm">
		<button type="button" name="add" id="add" class="btn btn-success">��ȭ�� �߰�</button>
	</a>
	<button type="button" name="delete" id="delete" class="btn btn-danger" onclick="delchk()">��ȭ�� ����</button>
	<a href="/movie/">
		<button type="button" name="backPage" id="backPage" class="btn btn-dark">�ǵ��ư���</button>
	</a>
	</div>
	<!-- <a href="timetableInsertAction.do">
		<button type="button" name="add" id="add" class="btn">�󿵽ð�ǥ �߰�</button>
	</a> -->
	</div>
	<jsp:include page="../include/footer.jsp" />

</body>
</html>







