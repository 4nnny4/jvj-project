<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">

    <footer>
        <div class="footer-section">

            <img class="footer-main-logo" src="${contextPath}/resources/images/common/jvj_logo.png" alt="로고">
            <hr>
            <div class="images">
                <a href="https://www.instagram.com"><img src="${contextPath}/resources/images/instagram.png" alt="로고"></a>
                <a href="https://www.youtube.com/"><img src="${contextPath}/resources/images/youtube.png" alt="로고"></a>
                <a href="https://blog.naver.com"><img src="${contextPath}/resources/images/blog.png" alt="로고"></a> 

            </div>
            <div class="footer-notice">
                <a href="#">이용약관</a>
                <a href="#">개인정보 처리방침</a>
                <p>copyrightⓒ 2022 All rights reserved by 구독좋아요알림설정</p>
            </div>
        </div>
    </footer>
    
    <c:if test="${!empty requestScope.title}">
	<script>
      swal({
         title : "${title}",
         text : "${text}",
         icon : "${icon}"
      });
   </script>
	</c:if>