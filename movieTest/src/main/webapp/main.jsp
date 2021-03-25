<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SC_Main</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<body>

	<jsp:include page="header.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1>Seoul Cinema에 오신걸 환영합니다.</h1>
		</div>
	</div>

	<!-- content -->
	<div class="wrapper" style="width: 100%; max-width:1440px; margin: 0 auto;">	
	<div class="row col-md-12" style="padding-left: 15%;">
    <div class="col-sm-5">
      <div class="thumbnail">
        <a href="movieinfo.jsp">
          <img src="/resources/img/F3.jpg" alt="Lights" class="img-thumbnail"
          		style="width:100%;"/>
        </a>
      </div>
    </div>
    
    <div class="col-sm-5">
      <div class="thumbnail">
        <a href="movieinfo.jsp">
          <img src="/resources/img/F7.jpg" alt="Fjords"  class="img-responsive center-block img-thumbnail"
            	style="width:100%;"/>
        </a>
      </div>
    </div>
  </div>

  <br>
  <div class="col-sm-12">
      <div class="thumbnail">
        <a href="movieinfo.jsp">
          <img src="/resources/img/place2wide.jpg" alt="Fjords" style="width:100%" class="img-thumbnail">
        </a>
      </div>
    </div>
    <br>
  <div class="col-sm-12">
      <div class="thumbnail">
        <a href="movieinfo.jsp">
          <img src="/resources/img/birdboxwide.jpg" alt="Fjords" style="width:100%" class="img-thumbnail">

        </a>
      </div>
    </div>
    </div>
	<jsp:include page="footer.jsp" />
</body>
</html>