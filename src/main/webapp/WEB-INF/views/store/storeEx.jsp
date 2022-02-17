<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="${contextPath}/resources/css/store.css">
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/mcart.jsp" />
</head>

<body>
	<div class="main-banner"></div>
	<div class="main-wrapper">

		<div class="category-set">
			<div class="select">
				<select name="format" id="format">
					<option selected disabled>카테고리 선택</option>
					<option value="0">전체</option>
					<option <c:if test="${param.ct eq '1'}">selected</c:if> value="1">식빵</option>
					<option <c:if test="${param.ct eq '2'}">selected</c:if> value="2">바게트</option>
					<option <c:if test="${param.ct eq '3'}">selected</c:if> value="3">기타</option>
				</select>
			</div>
			<ul class="main-category">
				<li style="color: #f58c8c;"><a
					href="store?cp=1&ct=${param.ct}&op=0">신상품</a></li>
				<li>|</li>
				<li><a href="store?cp=1&ct=${param.ct}&op=1">낮은가격</a></li>
				<li>|</li>
				<li><a href="store?cp=1&ct=${param.ct}&op=2">높은가격</a></li>
				<c:if test="${not empty loginMember }">
				<li>|</li>
				<li><a href="store?cp=1&ct=${param.ct}&op=3">찜한상품</a></li>
				</c:if>
				<c:if test ="${empty loginMember }">
				<li>|</li>
				<li><a href="member/login">찜한상품</a></li>
				
				</c:if>
			</ul>
		</div>
		<div class="main-content">



			<c:forEach var="pdt" items="${store}" varStatus="i">

				<div class="pdt-box">
					<div class="outer brand-new">
						<div class="pdt-img"
							style=" background-image: url('${contextPath}${pdt.imgPath}');"></div>
						<input type="hidden" value="${pdt.storeNo}" name="storeNo">
					</div>
					<div class="pdt-name">${pdt.storeName}</div>
					<div class="heart-btn">
						<div
							class="content <c:if test="${pdt.likeit  > 0}">heart-active</c:if>">
							<div
								class="heart <c:if test="${pdt.likeit > 0}">heart-active</c:if>"></div>
						</div>

					</div>

					<span class="pricewon"
						style="font-size: 20px; margin-top: 5px;<c:if test="${pdt.discountPer ne 0}">text-decoration:line-through;</c:if>">
						${pdt.price} </span>
					<c:if test="${pdt.discountPer ne 0}">
						<span class="pricewon"
							style="font-size: 24px; margin: 5px 0 0 10px; color: #00b992;">${pdt.price * (100-pdt.discountPer)/100}원</span>
					</c:if>
					<div style="font-size: 16px; margin-top: 15px;">${pdt.memo}</div>

					<c:if test="${ pdt.stock  eq 0}">
						<div class="pdt-label label-sold">품절</div>
					</c:if>

					<c:if test="${param.cp eq 1 && i.first && param.op eq 0}">

						<div class="pdt-label label-new">NEW</div>

					</c:if>

					<c:if test="${best == pdt.storeNo}">
						<div class="pdt-label label-best">BEST</div>
					</c:if>

					<c:if test="${ pdt.discountPer ne 0}">
						<div class="pdt-label label-sale">SALE</div>
					</c:if>
				</div>
			</c:forEach>


		</div>
	</div>
	<ul class="pagination">
		<c:if test="${pagination.startPage !=1 }">
			<li><a class="page-link"
				href="store?cp=1&ct=${search.ct}&op=${param.op}">&lt;&lt;</a></li>
			<li><a class="page-link"
				href="store?cp=${pagination.prevPage}&ct=${search.ct}&op=${param.op}">&lt;</a></li>
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
					<li><a class="page-link" style="margin: 5px"
						href="store?cp=${i}&ct=${search.ct}&op=${param.op}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pagination.endPage != pagination.maxPage }">
			<li><a class="page-link"
				href="store?cp=${pagination.nextPage}&ct=${search.ct}&op=${param.op}">&gt;</a></li>
			<li><a class="page-link"
				href="store?cp=${pagination.maxPage }&ct=${search.ct}&op=${param.op}">&gt;&gt;</a></li>
		</c:if>
	</ul>
	<!-- main-wrapper end-->
</body>
<script>

const loginMember = '${loginMember.memberNo}';
</script>
<script type="text/javascript"
	src="${contextPath}/resources/js/store/store.js"></script>
<jsp:include page="../common/footer.jsp" />
</html>