<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JVJ 공지사항</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
</head>
<body>
    <main>
        <section>
            <!-- 공지사항 리스트 -->
            <article>
            <%-- <div>${pagination }</div> --%>
                <div id="pathline"><a href="${contextPath}">HOME</a>&gt;<a href="${contextPath}/notice/list">공지사항</a></div>
                <h1 id="classpname" style="font-size: 40px;">
                <a href="${contextPath}/notice/list">공지사항</a> | 
                </h1>
                <div id="align-div">
                    <select id='selectcate'>
                        <option value="0"  <c:if test="${cate == '0'}"> selected</c:if>>전체</option>
                        <option value="1"  <c:if test="${cate == '1'}"> selected</c:if>>프로모션</option>
                        <option value="2"  <c:if test="${cate == '2'}"> selected</c:if>>공지사항</option>
                        <option value="3"  <c:if test="${cate == '3'}"> selected</c:if>>이벤트</option>
                    </select>
                </div>
                <div id="noticebox">
                    <div class="one-line-notice header-notice">
                        <span>번호</span>
                        <span>카테고리</span>
                        <span>제목</span>
                        <span>작성자</span>
                        <span>작성일</span>
                    </div>
                    <c:if test="${empty noticeList}">
                    	<div>공지사항이 없습니다!</div>
                    </c:if>
                    <c:forEach items="${noticeList}"  var="notice">
	                    <div class="one-line-notice">
	                        <span>${notice.noticeNo}</span>
	                        <span>${notice.noticeCateName}</span>
	                        <span><a href="view?noticeNo=${notice.noticeNo}&cate=${cate}&cp=${cp}">${notice.noticeTitle}</a></span>
	                        <span>장발장식빵</span>
	                        <span>${notice.createDt}</span>
	                    </div>
                    </c:forEach>
                </div>
            </article>
            <!-- 페이지네이션 -->
            
            <article>
           		<ul class="pagination">
					<c:if test="${pagination.startPage !=1 }">
						<li><a class="page-link" href="list?cp=1&cate=${cate}">&lt;&lt;</a></li>
						<li><a class="page-link" href="list?cp=${pagination.prevPage}&cate=${cate}">&lt;</a></li>
					</c:if>
					<%-- 페이지 네이션 번호 목록 --%>
					<c:forEach begin="${pagination.startPage }"
						end="${pagination.endPage}" step="1" var="i">
						<c:choose>
							<c:when test="${i==pagination.currentPage}">
								<li><a class="page-link"
									style="padding: 6px 12px; border-radius: 20px; background-color: #B9845A; color: white;">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a class="page-link" style="margin:5px" href="list?cp=${i}&cate=${cate}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pagination.endPage != pagination.maxPage }">
						<li><a class="page-link" href="list?cp=${pagination.nextPage}&cate=${cate}">&gt;</a></li>
						<li><a class="page-link" href="list?cp=${pagination.maxPage }&cate=${cate}">&gt;&gt;</a></li>
					</c:if>
				</ul>
            </article>
        </section>
    </main>
	<jsp:include page="../common/footer.jsp" />	
	
	<script>
		document.getElementById("selectcate").addEventListener("change",function(){
			location.href="list?cate="+this.value;
		})
	</script>
</body>
</html>