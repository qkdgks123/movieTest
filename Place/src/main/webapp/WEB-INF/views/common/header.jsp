<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	var loopSearch=true;
	function keywordSearch(){
		if(loopSearch==false)
			return;
	 var value=document.frmSearch.searchWord.value;
		$.ajax({
			type : "get",
			async : true, //false인 경우 동기식으로 처리한다.
			url : "${contextPath}/goods/keywordSearch.do",
			data : {keyword:value},
			success : function(data, textStatus) {
			    var jsonInfo = JSON.parse(data);
				displayResult(jsonInfo);
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다."+data);
			},
			complete : function(data, textStatus) {
				//alert("작업을완료 했습니다");
				
			}
		}); //end ajax	
	}
	
	function displayResult(jsonInfo){
		var count = jsonInfo.keyword.length;
		if(count > 0) {
		    var html = '';
		    for(var i in jsonInfo.keyword){
			   html += "<a href=\"javascript:select('"+jsonInfo.keyword[i]+"')\">"+jsonInfo.keyword[i]+"</a><br/>";
		    }
		    var listView = document.getElementById("suggestList");
		    listView.innerHTML = html;
		    show('suggest');
		}else{
		    hide('suggest');
		} 
	}
	
	function select(selectedKeyword) {
		 document.frmSearch.searchWord.value=selectedKeyword;
		 loopSearch = false;
		 hide('suggest');
	}
		
	function show(elementId) {
		 var element = document.getElementById(elementId);
		 if(element) {
		  element.style.display = 'block';
		 }
		}
	
	function hide(elementId){
	   var element = document.getElementById(elementId);
	   if(element){
		  element.style.display = 'none';
	   }
	}

</script>
<body>

<header class="header">

	<div class="headerWrap">
		<div class="linkArea">
			<a href="https://place.onoffmix.com/search?dummy=" class="searchLinkBtn">검색</a>
        </div>
        
        <div class="logoArea">
			<h1 class="headerLogo">
                <a href="${contextPath}/main/main.do"><img src="${contextPath}/resources/image/global/headerLogo.svg" alt="onoffmix.place" /></a>
            </h1>
        </div>
        
        <div class="gnbArea">
            <a href="#" class="gnbBtn"></a>
        </div>
        
        <div class="menuArea">
            <ul class="headerMenu">
	        	<c:choose>
					<c:when test="${isLogOn==true and not empty memberInfo }">
						<li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
			   			<li><a href="${contextPath}/mypage/myPageMain.do">마이페이지</a></li>			   			
				   		<li><a href="${contextPath}/board/listArticles.do">장소등록 게시판</a></li>
		
				 	</c:when>
				 	<c:otherwise>
						<li><a href="${contextPath}/member/memberForm.do">회원가입</a></li>
		                <!-- 
		                <li class="login"><a href="${contextPath}/member/loginForm.do">로그인</a></li>
		                 -->
		                <li class="login"><a href="#">로그인</a></li>
					</c:otherwise>
				</c:choose>
				
	
				<c:if test="${isLogOn==true and memberInfo.member_id =='admin' }">
					<li class="no_line"><a href="${contextPath}/board/listArticles.do">장소 승낙하기[관리자]</a>
				</c:if>
			</ul>
        </div>
    </div>

	<div class="loginArea">
		<div class="bgSkin"></div>
        <div class="loginWrap">
			<!-- 로그인 전 -->
			<form action="${contextPath}/member/login.do" method="POST" class="loginForm" data-parsley-validate="true">
                <input type="hidden" name="_token" value="FHvaCHb6ukumHJqmsG4EqBspk4O5VUEEmfxwvJJP">
                <h2 class="loginTitle">로그인</h2>
                <div class="loginInput">
					<input type="text" class="emailId" name="member_id" placeholder="이메일 아이디"
                            data-parsley-required="true"
                            data-parsley-type="email"
                            data-parsley-errors-messages-disabled="true"/>
                    <input type="password" class="password" name="member_pw" placeholder="비밀번호"
                            data-parsley-required="true"
                            data-parsley-errors-messages-disabled="true" />
                </div>
                <div class="checkArea">
                    <span class="loginRemember">
                        <input type="checkbox" name="remember" id="remember" class="checkStyle">
                        <label for="remember" class="checkboxOn"><i></i>로그인 상태 유지</label>
                    </span>
                    <span class="find">
                        <a href="https://onoffmix.com/account/find">아이디 찾기</a>
                        <span class="txt">/</span>
                        <a href="https://onoffmix.com/account/find">비밀번호 찾기</a>
                    </span>
                </div>
                <div class="loginBtnArea">
                    <input type="submit" class="btn loginBtn" value="로그인" >
                    <a href="${contextPath}/member/memberForm.do" class="btn registerBtn">회원가입</a>
                </div>
            </form>
		</div>
    </div>
</header>
</body>
</html>