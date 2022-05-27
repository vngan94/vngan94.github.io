<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <c:choose>
		<c:when test="${userLogin.vaitro.mavt == 1}">
			<%@include file="/WEB-INF/views/include/headernv.jsp"%>
		</c:when>
		<c:otherwise>
			<%@include file="/WEB-INF/views/include/headerql.jsp"%>
		</c:otherwise>
	</c:choose>
    <!-- Header End -->
	 <div class="container-tsp">
        <div class="space">
            <h3>Đơn đặt hàng  ${dsctddh[0].dondathang.maddh} </h3>
        	<a href="kho/chontiep/${dsctddh[0].dondathang.maddh}.htm">Chọn tiếp</a>
			<a href="kho/datxong.htm">Xong</a>
        </div>
  			${message}
        	<div class = space>
        		<div>
        			
        			<h4>Nhà cung cấp</h4>
        			<p>Tên nhà cung cấp: ${dsctddh[0].dondathang.nhacungcap.tenncc}</p>
        			<p>Địa chỉ: ${dsctddh[0].dondathang.nhacungcap.diachi}</p>
        			<p>Số điện thoại: ${dsctddh[0].dondathang.nhacungcap.sdt}</p>
        		</div>
        		
        		<div>
        			<h4>Người lập hóa đơn</h4>
        			<p>Mã nhân viên: ${dsctddh[0].dondathang.nhanvien.manv}</p>
        			<p>Tên nhân viên: ${dsctddh[0].dondathang.nhanvien.ho} ${dsctddh[0].dondathang.nhanvien.ten}</p>
        			<p>Sdt: ${dsctddh[0].dondathang.nhanvien.sdt}</p>
        		</div>
        	</div>
        	
            <table class="table table-hover table-bordered">
            <tr>Tổng cộng: ${tongcong}</tr>
                <thead class="table-dark">
                    <tr>
                        <th>Tên nguyên liệu</th>
                        <th>Đơn giá</th>
                        <th>Số lượng</th>
                        <th>Đơn vị</th>
                        <th>Thành tiền</th>
                      
                       
                    </tr>
                </thead>
                <tbody>
                 <c:forEach var="u" items="${dsctddh}">
                		<tr>
							<td>${u.nguyenlieu.tennl}</td>
							
							
							<c:forEach var="c" items = "${dsctcc}"> 
								<c:choose>
									<c:when test="${c[0] == u.nguyenlieu.manl}">
										<td>${c[1]}/${c[2]}</td>
										<td>${u.soluong}</td>
										<td>${u.donvi}</td>
										<td>${c[1]*u.soluong}</td>
									</c:when>
								</c:choose>
							</c:forEach>
						</tr>
                 	</c:forEach> 
                 	
           			
                 	
					
				
			
				 
                </tbody>
            </table>
          
    </div>
    
	


   

    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="resources/lib/easing/easing.min.js"></script>
    <script src="resources/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="resources/lib/isotope/isotope.pkgd.min.js"></script>
    <script src="resources/lib/lightbox/js/lightbox.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="resources/mail/jqBootstrapValidation.min.js"></script>
    <script src="resources/mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="resources/js/main.js"></script>
</body>

</html>