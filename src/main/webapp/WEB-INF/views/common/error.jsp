<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
<style>
	html,body{backgroud:black;
	  display: grid;
  place-items: center;
  min-width:800px;
  position:relative;
 }
	img{
	margin-top:100px;
	width:850px;
	}
	a{
	font-size:20px;
	text-decoration:none;
	border:1px solid #fbe4cb;
	color:#fbe4cb;
	border-radius:5px;
	position:relative;
	top:-122px;
	padding:4px 50px;
	left:-158px;
	}
</style>
</head>
<body style="background: #cf6a58">
	
	<img alt="" src="${contextPath}/resources/images/404.png">
	
	<a href="/jvj">HOME</a>
</body>
</html>