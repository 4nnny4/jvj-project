<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원데이 클래스 수강 신청 내역</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/myPage/myPageClass.css/">
</head>
<body>
	<main>
    <div class="titleText">
        <span>원데이 클래스 수강 신청 내역</span>
    </div>

    <section>
    
    <%-- 메뉴 --%>
   <c:choose>
    	<c:when test="${loginMember.service eq 'naver'}">
	    	<div class="divNav">
		        <div id="hr"></div>
		        <ul>
		        	<li class="topText ">회원 관리</li>
		        	<li class="subText"><a href="main">마이 페이지</a></li>
		        	<li class="subText mbText"><a href="info">회원정보 수정</a></li>
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
    	</c:when>
    	<c:when test="${loginMember.service eq 'kakao'}">
	    	<div class="divNav">
		        <div id="hr"></div>
		        <ul>
		        	<li class="topText ">회원 관리</li>
		        	<li class="subText"><a href="main">마이 페이지</a></li>
		        	<li class="subText mbText"><a href="info">회원정보 수정</a></li>
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
    	</c:when>
    	
    	<c:otherwise>
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
    	</c:otherwise>
    </c:choose>	
    
    
    
    
       <article>
            <div class="selectDiv">
                <span>최근 6개월내 수강하신 목록입니다.</span>
            </div>
            <table >
                <thead >
                    <tr id="tb-tr" >
                        <td id="td1"></td>
                        <td id="td2">클래스 강의명</td>
                        <td id="td3">신청일/해당지점/클래스 기간</td>
                        <td id="td4"></td>
                        <td id="td5"></td>
                    </tr>
                </thead>
                <tbody>
                
                <c:choose>
                <c:when test="${!empty classList}">
                
	                <c:forEach var="classList" items="${classList}">
	                    <tr class="tb-tbd">
	                        <td></td>
	                        <td>${classList.productName}</td>
	                        <td>${classList.orderOption}</td>
	                        <td></td>
	                        <td>
	                            <button class="shippingBtn" onclick="cancleClass(${classList.productNo})">취소</button>
	                        </td>
	                    </tr>
	                 </c:forEach>
                 
                 </c:when>
                 
                    
                 </c:choose>   
                </tbody>

            </table>
        </article>

      <article class="pagination-area">
                <ul class="pagination">
                
					<li><a class="page-link" href="class?cp=1">&lt;&lt;</a></li>
					<li><a class="page-link" href="class?cp=${pagination.prevPage}">&lt;</a></li>
                      
                    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1"  var="i">
					<c:choose>
						<c:when test="${i == pagination.currentPage}">
							<li><a class="page-link" style="padding: 6px 12px; border-radius: 20px; background-color: #B9845A; color: white;">${i}</a></li>   
						</c:when>
						
						<c:otherwise>
							<li><a class="page-link" href="class?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					<li><a class="page-link" href="class?cp=${pagination.nextPage}">&gt;</a></li>
					<li><a class="page-link" href="class?cp=${pagination.maxPage}">&gt;&gt;</a></li>
                </ul>
            </article>
        
    </section>
    </main>
    
	<script>
	    const contextPath = "${contextPath}";
	    
	</script> 
	<jsp:include page="../common/footer.jsp" />	
	
	
	<script src="${contextPath}/resources/js/mypage/myPageClass.js"></script> 
</body>
</html>