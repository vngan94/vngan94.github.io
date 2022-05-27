<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
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

<link
	href="https://fonts.googleapis.com/css2?family=Montserrat&family=Oswald:wght@400;500;600&display=swap"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Flaticon Font -->
<link href="resources/lib/flaticon/font/flaticon.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="resources/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="resources/lib/lightbox/css/lightbox.min.css"
	rel="stylesheet">
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value='/resources/img/icon.ico' />">

<!-- Customized Bootstrap Stylesheet -->
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/nganstyle.css" rel="stylesheet">

</head>

<body>
	<%@include file="/WEB-INF/views/include/headerql.jsp"%>
	<!-- Header End -->
	<form:form action="" method="post" modelAttribute="trasua"
		enctype="multipart/form-data">
		<div class="container-tsp flex">
			<div>
				<img src="resources/img/${trasua.hinhanh}" alt="">
			</div>
			<div>
				<h3 class="title">THÔNG TIN SẢN PHẨM</h3>
				${message}
				<div class="pad-2">
					<label>Tên sản phẩm</label>
					<form:input class="input-pad4" type="text" path="tents"
						placeholder="Nhập tên sản phẩm" />
					<form:errors path="tents" />
				</div>

				<div class="pad-2">
					<label>Mô tả sản phẩm</label>
					<form:input class="input-pad4" type="text" path="mota"
						placeholder="Mô tả sản phẩm" />
					<form:errors path="mota" />
					<form:input class="input-pad4" type="hidden" path="trangthai"
						 />
				</div>

				<div class="pad-2">
					<label>Giá</label>
					<form:input class="input-pad2 trangthai" type="number" min="20000"
						max="100000" step="1000" placeholder="Nhập giá" path="gia" />
					<form:errors path="gia" />
				</div>
				<div class="pad-2">
					<input name="photo" type="file" placeholder="Chọn ảnh " />
				</div>
				<button class="btn btn-primary">Save</button>
			</div>
		</div>
	</form:form>


	<!-- Back to Top -->
	<a href="#" class="btn btn-lg btn-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>


	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
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