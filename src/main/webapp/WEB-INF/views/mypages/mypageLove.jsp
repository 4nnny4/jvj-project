<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관심 상품 내역</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/myPage/myPageLove.css/">
</head>
<body>
	    <main>
         <div class="titleText">
        <span>좋아요 상품 내역</span>
    </div>

   

    <section>
    <%-- 메뉴 --%>
    <c:choose>
    	<c:when test="${listLike.service eq 'null'}">
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
    	</c:when>
    	
    	<c:otherwise>
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
    	</c:otherwise>
    </c:choose>
    
       
            <article>
                <div class="mainTable">
                    <div class="tList thList">
                        <ul class="tname">
                            <li></li>
                            <li>상품명</li>
                            <li>가격</li>
                            <li></li>
                        </ul>
                    </div>
                    
                    <div class="tList tbList">
                    
                    <c:choose>
	                    <c:when test="${empty likeList}">
	                    </c:when>
	                    
	                    <c:otherwise>
	                    
	                    	<c:forEach items="${likeList}" var="like">
	                    	
		                        <ul class="tbname">
		                        
		                            <li><img src="${contextPath}/${like.productImagePath}/${like.productImageName}" alt=""></li>
		                            <li>${like.productName} 
		                            </li>
		                            <li>${like.productPrice}</li>
		                            <li>
		                            
		                            <c:if test="${like.productCode eq 1}">
		                            	<button class="p-btn" id="btn-pur" onclick="location.href='${contextPath}/store/info/${like.productNo}'">상품 페이지로</button>
		                            </c:if>
		                            
		                            <c:if test="${like.productCode eq 2}">
		                            	<button class="p-btn" id="btn-pur" onclick="location.href='${contextPath}/onedayclass/view/${like.productNo}'">상품 페이지로</button>
		                            </c:if>
		                            
		                            <c:if test="${like.productCode eq 3}">
		                            	<button class="p-btn" id="btn-pur" onclick="location.href='${contextPath}/subscribe/subMain'">상품 페이지로</button>	
		                            </c:if>
			                           
			                           <button class="p-btn" id="btn-cc"  onclick="cancelLike(${like.productNo})">좋아요 삭제</button>
		                            </li>
		                        </ul>
	                        </c:forEach>
	                    </c:otherwise>
                     </c:choose>  
                    </div>
                    
                </div>

            </article>

      		<article class="pagination-area">
                <ul class="pagination">
                
					<li><a class="page-link" href="love?cp=1">&lt;&lt;</a></li>
					<li><a class="page-link" href="love?cp=${pagination.prevPage}">&lt;</a></li>
                      
                    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1"  var="i">
					<c:choose>
						<c:when test="${i == pagination.currentPage}">
							<li><a class="page-link" style="padding: 6px 12px; border-radius: 20px; background-color: #B9845A; color: white;">${i}</a></li>   
						</c:when>
						
						<c:otherwise>
							<li><a class="page-link" href="love?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					<li><a class="page-link" href="love?cp=${pagination.nextPage}">&gt;</a></li>
					<li><a class="page-link" href="love?cp=${pagination.maxPage}">&gt;&gt;</a></li>
                </ul>
            </article>
        
        
    </section>
    </main>
    	
    <script>
	    const contextPath = "${contextPath}";
			
	</script> 
	
	<jsp:include page="../common/footer.jsp" />	
	<script src="${contextPath}/resources/js/mypage/myPageLike.js"></script> 
	
</body>
</html>