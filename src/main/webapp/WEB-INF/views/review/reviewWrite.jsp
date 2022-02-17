<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JVJ 공지사항</title>
<jsp:include page="../common/header.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/admin/summernote-bs4.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/admin/reviewWrite.css">
<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
<!-- include libraries(jQuery, bootstrap) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">
<!-- include summernote css/js -->

<script src="${contextPath}/resources/js/admin/summernote-bs4.min.js"></script>
<script src="${contextPath}/resources/js/admin/summernote-ko-KR.js"></script>
</head>
<body>
	<main>
		<section>
			<article>
				<h1 id="classpname">리뷰 작성</h1>
				<form action="writeReview" method="post"
					onsubmit="return validate();">
					<div id="notice-info">
						<div id="notice-title">
							<span>제목</span> <input type="text" name="title" class="fgmkt"
								placeholder="제목을 입력해주세요">
							<c:if test="${empty orderList}">
								<span class="voidworker" style="margin-left: 19px;color: #ff5151">  상품을 구매하셔야 리뷰작성이 가능합니다.</span>

							</c:if>
							<c:if test="${not empty orderList}">
								<select id="reviewImg" name="productNo" class="tSBKF"
									style="padding-left: 30px;">
									<c:forEach items="${orderList}" var="o">
										<option value="${o.productNo}"
											title="${contextPath}${o.imgPath}">${o.productName}</option>

									</c:forEach>
								</select>
							</c:if>
						</div>

						<div id="notice-create-dt">
							<span>평점</span> <label><input type="radio" name="point"
								value="5"> <i class="fas fa-star gold"></i> <i
								class="fas fa-star gold"></i> <i class="fas fa-star gold"></i> <i
								class="fas fa-star gold"></i> <i class="fas fa-star gold"></i> </label>
							<label><input type="radio" name="point" value="4">
								<i class="fas fa-star gold"></i> <i class="fas fa-star gold"></i>
								<i class="fas fa-star gold"></i> <i class="fas fa-star gold"></i>
								<i class="fas fa-star gray"></i> </label> <label><input
								type="radio" name="point" value="3"> <i
								class="fas fa-star gold"></i> <i class="fas fa-star gold"></i> <i
								class="fas fa-star gold"></i> <i class="fas fa-star gray"></i> <i
								class="fas fa-star gray"></i> </label> <label><input
								type="radio" name="point" value="2"> <i
								class="fas fa-star gold"></i> <i class="fas fa-star gold"></i> <i
								class="fas fa-star gray"></i> <i class="fas fa-star gray"></i> <i
								class="fas fa-star gray"></i> </label> <label><input
								type="radio" name="point" value="1"> <i
								class="fas fa-star gold"></i> <i class="fas fa-star gray"></i> <i
								class="fas fa-star gray"></i> <i class="fas fa-star gray"></i> <i
								class="fas fa-star gray"></i> </label>
						</div>
						<div id="notice-content">
							<textarea id='summernote' name='content'></textarea>
						</div>
						<span id="rvbtns">
							<button type="button" id="gotolist" class="rvbtn"
								onclick="window.history.back();">목록으로</button>
							<button type="submit" id="submitrv" class="rvbtn">등록</button>
							<button type="reset" id="canclerv" class="rvbtn"
								onclick="window.history.back();">취소</button>
						</span>
					</div>
				</form>
			</article>
		</section>
	</main>
	<jsp:include page="../common/footer.jsp" />

	<script type="text/javascript"
		src="${contextPath}/resources/js/admin/reviewWrite.js"></script>
</body>
</html>