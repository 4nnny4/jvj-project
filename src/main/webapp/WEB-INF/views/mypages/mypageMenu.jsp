<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>NAV</title>

<link rel="stylesheet" href="${contextPath}/resources/css/myPage/menu.css">
</head>
<body>
	<div class="divNav">
        <div id="hr"></div>
        <ul>
        	<li class="topText ">회원 관리</li>
        	<li class="subText"><a href="main">마이 페이지</a></li>
        	<li class="subText mbText"><a href="info">회원정보 수정</a></li>
        	<li class="subText mbText"><a href="password">비밀번호 변경</a></li>
        	<li class="subText mbText"><a href="coupon">쿠폰 정보</a></li>
        </ul>
        <ul>
        	<li class="topText mTopText">쇼핑정보</li>
        	<li class="subText"><a href="purchase">상품 주문 내역</a></li>
            <li class="subText"><a href="sub">구독 신청 내역</a></li>
            <li class="subText"><a href="class">수강 신청 내역</a></li>
            <li class="subText"><a href="love">좋아요 내역</a></li>
            <li class="subText"><a href="review">작성한 리뷰 내역</a></li>
        </ul>
    </div> 
</body>
</html>