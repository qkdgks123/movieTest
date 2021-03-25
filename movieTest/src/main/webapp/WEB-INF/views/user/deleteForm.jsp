<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>deleteForm</title>
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
    
        function checkValue()
        {
            inputForm = eval("document.del");
           
            
            if(!inputForm.password.value)
            {
                alert("��й�ȣ�� �Է��ϼ���");    
                inputForm.password.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1>ȸ��Ż��</h1>
		</div>
	</div>
 
    <form name="del" id="del" method="post" onsubmit="return checkValue()"
         class="form-horizontal" action="/movie/delete">
         <div class="form-group row">
  <label class="col-sm-2">���̵�</label>
  <div class="col-sm-3"> 
 		<input type="text" name="id" value="${customer.id}" class="form-control" readonly="readonly">
        
        </div>
        </div>
        <div class="form-group row">
  <label class="col-sm-2">��й�ȣ</label>
  <div class="col-sm-3">
					<input type="password" name="password" id="password"
					size="15" class="form-control" >
				</div>
		</div>
        
        <div class="form-group row">
  <div class="col-sm-offset-2 col-sm-10">
        <input type="button" value="���"  class="btn btn-warning" onclick="re(-1)">
        <input type="submit" value="Ż��" class="btn btn-primary" id="chk"/> 
        </div>  
</div>
    </form>
    <c:if test="${msg == false }">
 <p>
 �Է��� ��й�ȣ�� Ʋ�Ƚ��ϴ�.
 </p>
</c:if>
    <script>
    function re(val){
    	if(val=='-1')
    		location.href="/movie/";
    }
    
    </script>

    <jsp:include page="../include/footer.jsp"/>
</body>
</html>