<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구독 상품 신청 내역</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/myPage/myPageSub.css/">
</head>
<body>
	<main>
    <div class="titleText">
        <span>구독 상품 신청 내역</span>
    </div>

    <section>
    
    
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
                
                    <div class="tList thList">
                        <ul class="tname">
                            <li>정기 구독명</li>
                            <li>구독 신청일</li>
                            <li>상품 옵션</li>
                            <li></li>
                        </ul>
                    </div>
               
              
                <c:forEach var="subList" items="${subList}">
                <c:choose>
                	<c:when test="${!empty subList}">
	                	<div class="tList tbList">
	                        <ul class="tbname">
	                            <li>${subList.productName}</li>
	                            <li>${subList.paymentDate}</li>
	                            <li>${subList.orderOption}</li>
	                            <li>
	                             <button class="p-btn" id="btn-pur" onclick="cancleSub(${subList.productNo})">취소하기</button>
	                            </li>
	                        </ul>
	                    </div>
                	</c:when>
                	
                	<c:otherwise>
                	</c:otherwise>
                	</c:choose>
               </c:forEach> 
                	</div>
            </article>


       <article class="pagination-area">
                <ul class="pagination">
                
					<li><a class="page-link" href="sub?cp=1">&lt;&lt;</a></li>
					<li><a class="page-link" href="sub?cp=${pagination.prevPage}">&lt;</a></li>
                      
                    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1"  var="i">
					<c:choose>
						<c:when test="${i == pagination.currentPage}">
							<li><a class="page-link" style="padding: 6px 12px; border-radius: 20px; background-color: #B9845A; color: white;">${i}</a></li>   
						</c:when>
						
						<c:otherwise>
							<li><a class="page-link" href="sub?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					<li><a class="page-link" href="sub?cp=${pagination.nextPage}">&gt;</a></li>
					<li><a class="page-link" href="sub?cp=${pagination.maxPage}">&gt;&gt;</a></li>
                </ul>
            </article>
        
        
    </section>
    </main>
	<script>
	    const contextPath = "${contextPath}";
	    
	</script> 
	<jsp:include page="../common/footer.jsp" />	
	
	
	<script src="${contextPath}/resources/js/mypage/myPageSub.js"></script> 
</body>
</html>