<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("modMemberForm.jsp\n===============================");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정창</title>
	<style>
		.text_center {
			text-align: center;
		}
	</style>
</head>
<body>
	<h1>
		<A href="http://localhost:8282/studyproj/mypage/myPageMain.do">최근주문내역		|</A>
		<A href="http://localhost:8282/studyproj/member/modMemberForm.do?action=update">회원정보수정</A>
	</h1>

	<form method="post" action="${contextPath}/member/modMember.do">
		<br>
		<br><br>
		<h3>회원 정보 수정창</h3>
		<div id="detail_table">
		
		<table align="center">
			<tr class="dot_line">
				<td class="fixed_join" width="200"><p align="right">아이디</td>
				<td width="400"><input type="text" name="member_id" value="${member.member_id}" readonly></td>
			</tr>
			<tr class="dot_line">
				<td class="fixed_join" width="200"><p align="right">비밀번호</td>
				<td width="400"><input type="password" name="member_pw" required></td>
			</tr>
			<tr class="dot_line">
				<td class="fixed_join" width="200"><p align="right">이름</td>
				<td width="400"><p><input type="text" name="member_name" value="${member.member_name}"></td>
			</tr>
			<tr class="dot_line">
					<td class="fixed_join" width="200"><p align="right">휴대폰번호</td>
					<td><select  name="tel1" >
							<option>없음</option>
							<option selected value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select> - <input size="10px"  type="text" name="tel2" value="${member.tel2}"> - <input size="10px" value="${member.tel3}" type="text"name="tel3"><br> <br> 
					<input type="checkbox"	name="smssts_yn" value="Y" checked /> 쇼핑몰에서 발송하는 SMS 소식을 수신합니다.</td>
				</tr>
			<tr class="dot_line">
				<td class="fixed_join" width="200"><p align="right">이메일</td>
				<td width="400"><input size="10px"   type="text" name="email1" value="${member.email1}" /> @ <input  size="10px"  type="text"name="email2" /> 
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
							</select><br> <br></td>
			</tr>
			<tr class="dot_line">
				<td class="fixed_join" width="200" ><p align="right">잔여 포인트</td>
				<td width="400"><input value="${member.point}" type="text" name="point" readonly="readonly"></td>
			</tr>
			<tr>
				<td width="200"><p>&nbsp;</p></td>
				<td width="400">
					<input type="submit" value="수정하기">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
		</div>
		
	</form>
</body>
</html>