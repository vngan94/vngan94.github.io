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
	<div class="container-tsp">
		<div class="space">
			<h3>Quản lý size ${dscts[0].trasua.tents} </h3>
		</div>
		<form:form action="sanpham/quanli/themsize/${dscts[0].trasua.mats}.htm"
			method="post" modelAttribute="cts">
			<div class="flex">
				<div>
					<label>Chọn size</label>
					<form:select path="size.masize" items="${dssize}"
						itemValue="masize" itemLabel="tensize" />
				</div>
				<button class="btn btn-primary">Insert</button>
				${message}
			</div>
		</form:form>

		<table class="table table-hover table-bordered">
			<thead class="table-dark">
				<tr>
				
					<th>Size</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="u" items="${dscts}">
					<tr>
						
						<td>${u.size.tensize}</td>

						<c:choose>
							<c:when test="${u.size.masize==2}">
								<td></td>
							</c:when>
							<c:otherwise>
								<td><a href="sanpham/quanli/deletects/${u.macts}.htm"><i
										class="fa fa-eraser"></i></a></td>
							</c:otherwise>
						</c:choose>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>






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