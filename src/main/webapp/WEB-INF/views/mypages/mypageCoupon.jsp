<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보유 쿠폰 내역</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/myPage/myPageCoupon.css/">
</head>
<body>
	<main>
    

    <section style="margin-top:60px" >
    <div class="titleText">
        <span>보유한 쿠폰 목록</span>
    </div>
    
     <%-- 메뉴 --%>
    <c:choose>
    	<c:when test="${loginMember.service eq null}">
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
     	
          <div class="selectDiv" >
                <span>회원님이 보유하신 쿠폰 목록 입니다.</span>
                  <%--<select name="couponStatus" id="selectOption">
                	<option>전체</option>
                	<c:forEach items="${couponStatus}"  var="c">
	               		<option value="${c.couponStatusCode}">${c.couponStatusName}</option>
	            	</c:forEach>
                </select>--%>
            </div> 
            
            <%-- 파라미터 중 cs가 있다면 변수 생성 --%>
			<c:if test="${!empty param.ct}">
				<c:set var="c" value="&cs=${param.ct}"/>
			</c:if>
            
            <table>
                <thead >
                    <tr id="tb-tr" >
                        <td id="td1">쿠폰명</td>
                        <td id="td2">할인율</td>
                        <td id="td3">발급일</td>
                        <td id="td4">유효기간</td>
                        <td id="td5">쿠폰상태</td>
                    </tr>
                </thead>
                <tbody >
                <c:choose>
                
                	<%-- 쿠폰이 존재하지 않을 경우 --%>
                	<c:when test="${empty couponList}">
                	</c:when>
                	
                	
                	<c:otherwise>
		                	<c:forEach items="${couponList}" var="couponList">
		                	
		                	
		                	<c:if test="${couponList.couponStatusCode == 1}">
			                	<tr class="tb-tbd">
			                        <td>${couponList.couponName}</td>
			                        <td>${couponList.discountPer}</td>
			                        <td>${couponList.provideDate}</td>
			                        <td>${couponList.expireDate}</td>
			                        <td>${couponList.couponStatusName}</td>
			                    </tr>
			                </c:if> 
			                  
			                <c:if test="${couponList.couponStatusCode == 2}">
		                		<tr class="tb-tbd">
			                	<td class="dontCoupon">${couponList.couponName}</td>
						        <td class="dontCoupon">${couponList.discountPer}</td>
						        <td class="dontCoupon">${couponList.provideDate}</td>
						        <td class="dontCoupon">${couponList.expireDate}</td>
						        <td class="dontCoupon">${couponList.couponStatusName}</td>
					        	</tr>
                			</c:if>
                			
			                <c:if test="${couponList.couponStatusCode == 3}">
		                		<tr class="tb-tbd">
			                	<td class="dontCoupon">${couponList.couponName}</td>
						        <td class="dontCoupon">${couponList.discountPer}</td>
						        <td class="dontCoupon">${couponList.provideDate}</td>
						        <td class="dontCoupon">${couponList.expireDate}</td>
						        <td class="dontCoupon">${couponList.couponStatusName}</td>
					        	</tr>
                			</c:if>
		                    </c:forEach>
                	</c:otherwise>
                </c:choose>
                  
                </tbody>
            </table>
        </article>


        <!-- 페이지네이션 -->
           <article class="pagination-area">
                <ul class="pagination">
                
					<li><a class="page-link" href="coupon?cp=1">&lt;&lt;</a></li>
					<li><a class="page-link" href="coupon?cp=${pagination.prevPage}">&lt;</a></li>
                      
                    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1"  var="i">
					<c:choose>
						<c:when test="${i == pagination.currentPage}">
							<li><a class="page-link" style="padding: 6px 12px; border-radius: 20px; background-color: #B9845A; color: white;">${i}</a></li>   
						</c:when>
						
						<c:otherwise>
							<li><a class="page-link" href="coupon?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
					<li><a class="page-link" href="coupon?cp=${pagination.nextPage}">&gt;</a></li>
					<li><a class="page-link" href="coupon?cp=${pagination.maxPage}">&gt;&gt;</a></li>
                </ul>
            </article>
        
        
        
        
    </section>
    </main>
	<jsp:include page="../common/footer.jsp" />	
	<script>
		
		// 쿼리스트링에서 파라미터를 얻어와 반환하는 함수
		function getParam(key){
			return new URLSearchParams(location.search).get(key);	
		}
			
		
		// 카테고리 select 세팅하기
		const ctOptions = document.querySelectorAll("select[name=ct] > option");
		for(let option of ctOptions){
			if(option.value == getParam("ct")){
				option.setAttribute("selected", true);
			}
		}
		
		
		// 카테고리 select가 change 됐을 때 
		document.getElementById("selectOption").addEventListener("change", function(){
			console.log(this.value);
			
			if(){
				
			}
		});
		
	</script>
	
</body>
</html>