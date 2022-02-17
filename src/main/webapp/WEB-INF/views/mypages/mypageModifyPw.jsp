<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/myPage/mypageModifyPw.css/">
</head>
<body>
	<main>
    <div class="titleText">
    	<span>회원 정보 수정</span>
    </div>
    <section>
    
    
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
		        </ul>
		    	</div> 
    	
    	
            <article>
            <form method="POST" action="modify" class="modifyPwForm" onsubmit="return pwUpdateValidate();" role="form" name="updateForm">
            
                    <div>
                        <label for="presentPw" class="inputLabel">현재 비밀번호</label>
                        <input type="password" class="inputStyle" id="memberPw"  name="memberPw" > <span id="checkPwd"></span><br>

                        <label for="modifyPw" class="inputLabel">수정할 비밀번호</label>
                        <input type="password" class="inputStyle" id="modifyPw1" name="modifyPw1" required> <span id="checkPwd1"></span><br>
                        <label for="modifyPw" class="inputLabel">비밀번호 재입력</label>
                        <input type="password" class="inputStyle" id="modifyPw2" name="modifyPw2" required> <span id="checkPwd2"></span><br> 
                           
                    </div>
                <div class="subBtn">
                    <button type="submit" id="infoUpdate">비밀번호 수정</button><br>
                </div>
            </form>

        </article>
        
    </section>
    </main>
	<jsp:include page="../common/footer.jsp" />	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="${contextPath}/resources/js/mypage/myPageModifyPw.js"></script>
	
	
</body>
</html>