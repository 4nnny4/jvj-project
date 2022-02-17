<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>최근 상품 주문내역</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/myPage/myPageShopping.css">
</head>
<body>
	    <main>
         <div class="titleText">
        <span>최근 상품 주문내역</span>
    </div>

   

    <section class="bodySection">
    
    
    
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

            <div class="toDay">
                <span>최근 주문하신 내역입니다.</span>
            </div>

            <div class="mainTable">
                <div class="thList">
                    <ul class="tname">
                        <li></li>
                        <li>상품명/옵션</li>
                        <li>날짜/상품번호</li>
                        <li>상품금액/수량</li>
                        <li></li>
                    </ul>
                </div>
					
               
                
                
                <c:forEach var="purchase" items="${purchase}">
                <c:choose>
               
               		<c:when test="${!empty purchase}">
	               		<div class="tbList">
	               		<ul class="tbname">
	                        <li><img src="${contextPath}/${purchase.productImgPath}/${purchase.productImgName}" alt=""></li>
	                        <li> ${purchase.productName} <br> 옵션: ${purchase.orderOption}</li>
	                        <li>${purchase.paymentDate} <br> ${purchase.productNo}</li>
	                        <li> ${purchase.totalPrice} / ${purchase.productAmount}</li>
	                        <li>
	                            <button class="p-btn" id="btn-pur" >구매 내역</button>
	                            <button class="p-btn" id="btn-cc" onclick="updatePur(${purchase.productNo})">구매 취소</button>
	                        </li>
                    	</ul>
                   </div>
	               </c:when>
               
	            
               </c:choose>
                
                </c:forEach> 
               
                </div>
                
           
        </article>

        
       <article class="pagination-area">
                <ul class="pagination">
                
					<li><a class="page-link" href="purchase?cp=1">&lt;&lt;</a></li>
					<li><a class="page-link" href="purchase?cp=${pagination.prevPage}">&lt;</a></li>
                      
                    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1"  var="i">
					<c:choose>
						<c:when test="${i == pagination.currentPage}">
							<li><a class="page-link" style="padding: 6px 12px; border-radius: 20px; background-color: #B9845A; color: white;">${i}</a></li>   
						</c:when>
						
						<c:otherwise>
							<li><a class="page-link" href="purchase?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					<li><a class="page-link" href="purchase?cp=${pagination.nextPage}">&gt;</a></li>
					<li><a class="page-link" href="purchase?cp=${pagination.maxPage}">&gt;&gt;</a></li>
                </ul>
            </article>
        
        
        
    </section>
    </main>
    
    <script>
	    const contextPath = "${contextPath}";
	    
	</script> 
	<jsp:include page="../common/footer.jsp" />	
	
	
	<script src="${contextPath}/resources/js/mypage/myPageShopping.js"></script> 
	
</body>
</html>