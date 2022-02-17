<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JVJ 원데이클래스</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/classList.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div id="classmainimg">
        <img src="${contextPath}/resources/images/onedayclassList/classMain.jpg" alt="">
    </div>
    <main>
    <section>
        <article>
            <div id="pathline"><a href="">HOME</a>&gt;<a href="">원데이 클래스</a></div>
            <h1 id="classpname" style="font-size: 35px;">원데이 클래스<span id="placespan">[클래스 진행 지점] 마포점, 부천점, 금천점, 남양주점, 창동점</span></h1>
            <div id="align-div">
                <i class="far fa-calendar-alt"></i><button onclick="calmodal()" class='opencal'>날짜별 정렬</button>
                <i class="fas fa-map-marker-alt" >&nbsp;</i>
                <select id='selectplace' name='selectplace'>
                    <option value='0'>지점별 정렬</option>
                    <option value='1'>마포점</option>
                    <option value='2'>부천점</option>
                    <option value='3'>금천점</option>
                    <option value='4'>남양주점</option>
                    <option value='5'>창동점</option>
                </select>
                <span id="resetsearch"><i class="fas fa-undo-alt"></i></span>
            </div>
            <div id="infinitebox">
				
            </div><!-- infinite -->
        </article>
        <article class="cal">
            <div class="calendar">
                <div class="calhead">
                <button class="month-control" onclick="prev()">&lt;&lt;&lt;</button>
                <span id="today-month"></span>
                <button class="month-control" onclick="next()">&gt;&gt;&gt;</button>
                <button id="allcal" onclick="showAllDay()">전체날짜</button>
                <button id="closecal">닫기</button>
                </div>
                <table id = "selectDate">
                    <thead>
                        <tr>
                            <th>SUN</th>
                            <th>MON</th>
                            <th>TUE</th>
                            <th>WED</th>
                            <th>THU</th>
                            <th>FRI</th>
                            <th>SAT</th>
                        </tr>
                    </thead>
                    <tbody id="month-day">
                    </tbody>
                </table>
            </div>
            </article>
		<article class="loadingshow">
			<div class="loadingtext">
				<img alt="로딩이미지" src="${contextPath}/resources/images/onedayclassList/loadingIMG.gif">
				<h1>클래스 목록을 불러오는 중입니다...</h1>
			</div>
		</article>
        </section>
    </main>
	<jsp:include page="../common/footer.jsp" />	
	<c:if test="${empty loginMember }">
		<script>
			const loginNo = '';
		</script>
	</c:if>
	<c:if test="${!empty loginMember }">
		<script>
			const loginNo = "${loginMember.memberNo}";
		</script>
	</c:if>
	<script type="text/javascript" src="${contextPath}/resources/js/onedayclass/classList.js"></script>
</body>
</html>