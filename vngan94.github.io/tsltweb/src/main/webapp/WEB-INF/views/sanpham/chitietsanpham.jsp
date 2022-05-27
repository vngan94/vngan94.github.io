<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
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
    
	 
		 <%@include file="/WEB-INF/views/include/headerql.jsp"%>                   
	
    <!-- Header End -->
	
    <div class="container-tsp flex">
        <div> <img src="resources/img/${trasua.hinhanh}" alt=""></div>
        <div>
            <h3>Chi tiết sản phẩm</h3>
            <ul>
                <li> <p><a href="sanpham/quanli/edit/${trasua.mats}.htm"> <b>Mã sản phẩm:</b>  </a> ${trasua.mats}</p>    </li> 
                 <li> <p><b>Tên: </b> ${trasua.tents}</p> </li>
                 <li> <p><b>Trạng thái:</b>
                ${trasua.trangthai == 1 ?  'Mở bán': 'Ngưng bán'}
                  <li> <p><b>Mô tả: </b> ${trasua.mota}</p> </li>
                 
                  
                 <li> <p><a href="sanpham/quanli/editchitietsize/${trasua.mats}.htm"><b> Size </b></a>   </p> 
                 	<ul>
                 		 <c:forEach var="u" items="${dscts}">
                	  			<li> <p> <b>Size: ${u.size.tensize} </b> - <f:formatNumber value=" ${u.size.tile * trasua.gia}"/>VND</p>  </li>
                  		</c:forEach>
                 	</ul>
                 </li>
              	<li> <p><a href="sanpham/quanli/editcongthuc/${trasua.mats}.htm"><b> Nguyên liệu </b></a> </p>
             			
                    <ul>
                         <c:forEach var="u" items="${dsct}">
                		 <li> <p> <b> ${u.nguyenlieu.tennl}: </b>  ${u.lieuluong} ${u.donvi}</p> </li>
                		 
                 		 </c:forEach>
                    </ul>
                </li> 
            </ul> 
        </div>
        <div>
			  
              <a href="sanpham/khoahoacmokhoa/${trasua.mats}.htm"> <i class="fa  fa-lock"></i> </a>
              <a href="sanpham/xoa/${trasua.mats}.htm"> <i class="fa  fa-eraser"></i></i> </a>
        </div>
        ${message}
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
    <script src="resources/js/nganscript.js"></script>
</body>

</html>