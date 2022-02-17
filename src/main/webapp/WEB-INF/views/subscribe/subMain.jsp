<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<jsp:include page="../common/header.jsp" />
	<link rel="stylesheet" href="${contextPath}/resources/css/subMain.css">
</head>
<body>
	<section class="sub-top-area">
		<div>
			<span>빵 정기구독, 이렇게 시작해보세요!</span>
		</div>
		<div>
			<img class="top-img" src="${contextPath}/resources/images/subscribe/image.png" alt="subMain_image">
		</div>
	</section>
	
	<!-- 구독영역 -->
	<div class="subscribe">
		
			<div>
				<h1 >정기구독</h1>
			</div>
	
			<div class="subs-wrap main-img-area" >
			<a href="${contextPath}/subscribe/subBread">
					<img class="subs-item subs-img1 " src="${contextPath}/resources/images/subscribe/subMain_bread.jpg" alt="subMain_bread">
					<p>빵 세트</p>
				</a>
			</div>
			<div class="subs-wrap main-img-area">
				<a href="${contextPath}/subscribe/subCoffee">
					<img class="subs-item subs-img2" src="${contextPath}/resources/images/subscribe/subMain_coffee.jpg" alt="subMain_coffee">
					<p>빵 & 커피 세트</p>
				</a>
			</div>
	</div>
	
	<jsp:include page="../common/footer.jsp" />	
	<script type="text/javascript" src="${contextPath}/resources/js/subscribe/subMain.js"></script>
</body>
</html>