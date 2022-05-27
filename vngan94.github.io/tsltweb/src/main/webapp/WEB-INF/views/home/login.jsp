<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %> 
<!DOCTYPE html>

<html lang="en">
<base href="${pageContext.servletContext.contextPath}/">

<head>
   <meta charset="utf-8">
    <title>Quản lý quán trà sữa</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="resources/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&family=Oswald:wght@400;500;600&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Flaticon Font -->
    <link href="resources/lib/flaticon/font/flaticon.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="resources/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/nganstyle.css" rel="stylesheet">
   <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
    
   
    <div class="container-login">
  	<form:form method="post"  action="" modelAttribute="taikhoan">  
        <h3 >LOGIN</h3>
        <div class="pad-2">
            <label>Tài khoản</label>
            <form:input type="email" path="tentaikhoan" placeholder="Nhập tên tài khoản" /> 
            <form:errors path="tentaikhoan"/>
        </div>
        <div class="pad-2">
            <label>Mật khẩu</label>
            <form:input type="password" path="password" placeholder="Nhập mật khẩu"/>
             <form:errors path="password"/>
              ${message}  
        </div>
        <div class="g-recaptcha" data-sitekey="6LfiYPkfAAAAAK51MbjpWR9Ja8YzV3GMCEY0DVXu">
        </div>
        <p>${reCaptra}</p>
        
        <a href="taikhoan/quenmatkhau.htm">Quên mật khẩu?</a>
        <button class="btn btn-primary">Đăng nhập</button>
    </form:form>
     </div >
    

    
</body>

</html>