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
   
</head>
<body>
    
    <div class="container-login">
    	<form  action="" method="post">  
        <h3 >Cung cấp Email để đặt lại mật khẩu</h3>
        <div class="pad-2">
            <label>Email</label>
            <input type="email" name="tentaikhoan" placeholder="Nhập email"/>
        </div>
        <button class="btn btn-primary">Gửi</button>
        ${message} 
        <a href="taikhoan/login.htm">login here</a>
        
        </form>
    </div>
    

    
</body>

</html>