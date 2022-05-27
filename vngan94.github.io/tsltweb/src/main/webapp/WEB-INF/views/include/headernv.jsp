<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
    <link href="resources/img/favicon.ico'" rel="icon">

    <!-- Google Web Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&family=Oswald:wght@400;500;600&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Flaticon Font -->
    <link href="resources/lib/flaticon/font/flaticon.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="resources/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/icon.ico' />">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="resources/css/style.css" rel="stylesheet">
     <link href="resources/css/nganstyle.css" rel="stylesheet">

</head>

<body>
    <div class="container-fluid bg-dark py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center">
                      
                    </div>
                </div>
                <div class="col-md-6 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <span class="user-name">Xin chào ${userLogin.nhanvien.ho} ${userLogin.nhanvien.ten}</span>
                        <a class="text-white px-3" href="taikhoan/logout.htm">
                            <i class="fa fa-door-open"></i>
                        </a>
                      
                       
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- Header Start -->
    <div class="container-fluid position-relative nav-bar p-0">
        <div class=" position-relative" style="z-index: 9;">
            <nav class="navbar navbar-expand-lg bg-secondary navbar-dark py-3 py-lg-0 pl-3 pl-lg-5 w-100">
               
                    <h1 class="m-0 display-5 text-white"><span class="text-primary">Nhóm</span>21</h1>
               
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between px-3" id="navbarCollapse">
                    <div class="navbar-nav ml-auto py-0">
                       
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Quản lý sản phẩm</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="sanpham/danhsachsanpham.htm" class="dropdown-item">Sản phẩm</a>
                              
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Quản lý kho</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="kho/danhsachnguyenlieu.htm" class="dropdown-item">Nguyên liệu</a>
                                <a href="kho/datnguyenlieu.htm" class="dropdown-item">Đặt nguyên liệu</a>
                                <a href="kho/danhsachdondathang.htm" class="dropdown-item">Đơn đặt nguyên liệu</a>
                            </div>
                        </div>
                       
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Giao dịch</a>
                            <div class="dropdown-menu rounded-0 m-0">
                               
                                <a href="giaodich/danhsachdonhang.htm" class="dropdown-item">Đơn hàng</a>
                                <a href="giaodich/laphoadon.htm" class="dropdown-item">Lập hóa đơn</a>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- Header End -->