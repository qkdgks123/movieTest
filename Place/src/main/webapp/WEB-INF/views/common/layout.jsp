<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<%
   request.setCharacterEncoding("utf-8");
%>

<head>
   <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="csrf-token" content="K9lKU8SjmoGcfVlm304XCGTPw1D7KgNKZQeW5Y1e">
    <meta property="og:image" content="https://place.onoffmix.com/images/global/placeThumb.png" />

   <title><tiles:insertAttribute name="title" /></title>
   
   <script src="${pageContext.request.contextPath}/resources/image/favicon.jpeg"></script>
   <script src="${pageContext.request.contextPath}/resources/css/styles/datepicker.min.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/styles/jquery.ui.selectmenu.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/styles/swiper.min.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/styles/owl.carousel.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/style.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/common.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/index.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/view.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/rsvp.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/mypage.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/accountCommon.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/formCommon.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/host.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/pages/search.css"></script>
   <script src="${pageContext.request.contextPath}/resources/css/font/webfont.css"></script>

   <link href="${pageContext.request.contextPath}/resources/images/favicon.jpeg" rel="shortcut icon" type="image/x-icon">
    <link href="${pageContext.request.contextPath}/resources/css/styles/datepicker.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/styles/jquery.ui.selectmenu.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/styles/swiper.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/styles/owl.carousel.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/common.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/index.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/view.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/ravp.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/mypage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/accountCommon.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/formCommon.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/host.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/pages/search.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/font/webfont.css" rel="stylesheet" type="text/css">
    
    <script>
        var baseUrl = 'http://place.onoffmix.com';
        var msg = '';
        var exist = '';
        if(exist){
            alert(msg.replace('&lt;br/&gt;', '\r\n'));
            location.reload();
        } else {
        }
    </script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/handlebars.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pages/common.js"></script>
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-25312010-13"></script>
    
    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());
        gtag('config', 'UA-25312010-13');
    </script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pages/index.js"></script>    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/swiper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datepicker.ko.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pages/view.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pages/rsvp.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pages/search.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.tmpl.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.fileupload-process.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.fileupload-validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pages/host.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app.js"></script>
    
    <!-- 
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pages/jquery.easy-ticker.min.js"></script>
    -->
</head>
<body>
   <div id="outer_wrap">
      <div id="wrap">
         <header>
            <tiles:insertAttribute name="header" />
         </header>
         
         <article>
             <tiles:insertAttribute name="body" />
         </article>
         
         <footer>
             <tiles:insertAttribute name="footer" />
         </footer>
      </div>
      <tiles:insertAttribute name="quickMenu" />
   </div>
</body>

   <!-- 
   <aside>
       <tiles:insertAttribute name="side" />
   </aside>
    -->