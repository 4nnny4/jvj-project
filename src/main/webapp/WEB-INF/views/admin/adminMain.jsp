<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JVJ 관리자페이지</title>
    <!-- 초기화css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@600&family=Noto+Sans+KR&display=swap" rel="stylesheet">
   	<link rel="stylesheet" href="${contextPath}/resources/css/admin/adminMain.css">
    <!-- include libraries(jQuery, bootstrap) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <!-- include summernote css/js -->
   	<link rel="stylesheet" href="${contextPath}/resources/css/admin/summernote-bs4.min.css">
    <script src="${contextPath}/resources/js/admin/summernote-bs4.min.js"></script>
    <script src="${contextPath}/resources/js/admin/summernote-ko-KR.js"></script>
    <!-- include amcharts -->
    <script src="${contextPath}/resources/js/admin/Chartbundle.js"></script>
</head>
<body>
    <header>
        <div class="banner"><h1>Jean Val Jean</h1><h1>관리자 페이지</h1></div>
        <div class="admin-info">
        <h3 id="clock">현재시각</h3>
        <h4>${loginAdmin.nickName}</h4><span>님 환영합니다!</span><a href="${contextPath}/admin/board/adminlogout">로그아웃</a></div>
    </header>
    <main>
        <aside class='showfunc'>
            <div class="one-admin-func">▶조회 업무
            	<div class="each-querybox">
                    <ul class="eachW">
                        <li><a href="${contextPath}/admin/board/route/searchMember">회원 정보 조회</a></li>
                        <li><a href="${contextPath}/admin/board/route/subsMember">구독 회원 조회</a></li>
                        <%-- <li><a href="${contextPath}/admin/board/route/searchOrder">주문 조회</a></li> --%>
                    </ul>
                </div>
            </div>
            <div class="one-admin-func">▶상품 등록 및 공지 작성
                <div class="eachWriter">
                    <ul class="eachW">
                        <li><a href="${contextPath}/admin/board/route/noticeWriter">공지사항 작성</a></li>
                        <li><a href="${contextPath}/admin/board/route/storeSubmit">일반 상품 등록</a></li>
                        <li><a href="${contextPath}/admin/board/route/subsSubmit">구독 상품 등록</a></li>
                        <li><a href="${contextPath}/admin/board/route/classSubmit">클래스 상품 등록</a></li>
                        <li><a href="${contextPath}/admin/board/route/optionSubmit">추가옵션상품 등록</a></li>
                    </ul>
                </div>
            </div>
			<div class="one-admin-func">▶상품 /구독옵션 관리
				<div class="modifyP">
                    <ul class="eachW">
                        <li><a href="${contextPath}/admin/board/route/productManage">상품 관리</a></li>
                        <li><a href="${contextPath}/admin/board/route/subsOptionManage">구독옵션 관리</a></li>
                    </ul>
                </div>
			</div>
            <div class="one-admin-func">▶공지 및 리뷰 관리
                <div class="each-article">
                    <ul class="eachW">
                        <li><a href="${contextPath}/admin/board/route/reviewManage">리뷰 관리</a></li>
                        <li><a href="${contextPath}/admin/board/route/noticeManage">공지사항 수정</a></li>
                    </ul>
                </div>
            </div>
            <div class="one-admin-func">▶기타업무
         		<div class="addAdmin">
                    <ul class="eachW">
                        <li><a href="${contextPath}/admin/board/route/couponMake">쿠폰 발급</a></li>
                    </ul>
                </div>
            </div>
				
            <div class="one-admin-func">▶매출분석
         		<div class="addAdmin">
                    <ul class="eachW">
                        <li><a href="${contextPath}/admin/board/route/showSales" id="showChart">스토어 판매 순위</a></li>
                    </ul>
                </div>
            </div>
        </aside>
        <section>
            <article id="adminPCont">
            </article>
            <article class="cal">
                    <div class="calendar">
                    <div class="calhead">
                    <button class="month-control" onclick="prev()">&lt;&lt;&lt;</button>
                    <span id="today-month"></span>
                    <button class="month-control" onclick="next()">&gt;&gt;&gt;</button>
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
        </section>
        </main>
	<script>
		const contextPath = "${contextPath}"
		const adminNo ="${loginAdmin.memberNo}"
	</script>
	<script type="text/javascript" src="${contextPath}/resources/js/admin/adminMain.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/admin/adminMainChart.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/admin/adminMainMethod.js"></script>
	
</body>
</html>