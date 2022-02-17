<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storeDetail</title>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/mcart.jsp" />
<link rel="stylesheet"
	href="${contextPath}/resources/css/storeDetail.css">

<!-- 별 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>


<body>

	<main>
		<section class="top">
			<section class="main-nav">
				<a href="${contextPath}">HOME</a> <img
					src="${contextPath}/resources/images/common/expand_less.png"
					alt="expand_less"> <a
					href="${contextPath}/store?cp=1&ct=0&op=0">스토어</a> <img
					src="${contextPath}/resources/images/common/expand_less.png"
					alt="expand_less"> ${store.storeName}
			</section>


			<section class="product-thumbnail">
				<article class="main-img-area">
					<img class="main-thumbnail"
						src="${contextPath}${imgLevel[0].imgPath}" alt="sub-bread-main">
				</article>
				<div>

					<c:if test="${not empty imgLevel[0].imgPath}">
						<img class="sub-img img-margin"
							src="${contextPath}${imgLevel[0].imgPath}" alt="sub-bread-main">
					</c:if>
					<c:if test="${empty imgLevel[0].imgPath}">
						<img class="sub-img img-margin"
							src="${contextPath}/resources/images/common/noimage.png"
							alt="noimage">
					</c:if>
					<c:if test="${not empty imgLevel[1].imgPath}">
						<img class="sub-img img-margin"
							src="${contextPath}${imgLevel[1].imgPath}" alt="sub-bread-main">
					</c:if>
					<c:if test="${empty imgLevel[1].imgPath}">
						<img class="sub-img img-margin"
							src="${contextPath}/resources/images/common/noimage.png"
							alt="noimage">
					</c:if>
					<c:if test="${not empty imgLevel[2].imgPath}">
						<img class="sub-img img-margin"
							src="${contextPath}${imgLevel[2].imgPath}"
							alt="sub-bread-main">
					</c:if>
					<c:if test="${empty imgLevel[2].imgPath}">
						<img class="sub-img img-margin"
							src="${contextPath}/resources/images/common/noimage.png"
							alt="noimage">
					</c:if>
					<c:if test="${not empty imgLevel[3].imgPath}">
						<img class="sub-img img-margin"
							src="${contextPath}${imgLevel[3].imgPath}"
							alt="sub-bread-main">
					</c:if>
					<c:if test="${empty imgLevel[3].imgPath}">
						<img class="sub-img img-margin"
							src="${contextPath}/resources/images/common/noimage.png"
							alt="noimage">
					</c:if>
				</div>
			</section>

			<section class="product_detail">
				<article class="category_product">
					<div class="category-title">
						<span>${store.storeName}</span>
											<div class="kakaolink" style="float: right;">
							    <a id="kakao-link-btn" href="javascript:kakaoShare()" style="float: right;">
							    	<img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" style="float: right; width:44px; margin-right:13px"/>
							    </a>
							</div>
						<div class="heart-btn" style="margin-right:13px;">
							<div
								class="contentss <c:if test="${store.likeit  > 0}">heart-active</c:if>">
								<div
									class="heart <c:if test="${store.likeit > 0}">heart-active</c:if>"></div>
							</div>
						</div>
		
					</div>

					<c:if test="${store.discountPer > 0}">
						<span class="sale">${store.discountPer }%</span>
						<span class="real-price prices">${store.price}</span>
					</c:if>

					<span class="price prices">${store.price * (100-store.discountPer)/100}원</span>
				</article>

				<div class="bottom-line"></div>

				<article class="delivery-detail">
					<span>배송비: 3,000원 (3만원 이상 구매 시, </span> <span
						class="delievery-span">무료배송!</span> <span>)</span>
				</article>

				<div class="bottom-line"></div>

				<article class="sub-detail">
					<div class="sub-title">
						<span>수량</span> <span></span>
					</div>
					<div class="buy-count">
						<img class="minus-btn"
							src="${contextPath}/resources/images/subscribe/minus-btn.png"
							alt="minus-btn" onclick='count("minus")'> <span id="result">1</span>
						<img class="add-btn"
							src="${contextPath}/resources/images/subscribe/add-btn.png"
							alt="add-btn" onclick='count("add")'>
						<c:if test="${store.stock eq 0}">
							<h2 style="margin-left: 20px; color: red; line-height: 1;">
								품절</h2>
						</c:if>
					</div>
				</article>
				<div class="bottom-line"></div>

				<article class="sub-detail">
					<div class="sub-title">
						<span>추가 옵션</span>
					</div>
					<div class="align-div" id="align-div">
						<select id='selectcate-option'>
							<option value=0>선택 없음</option>
							<option value=1>카야잼 30원</option>
							<option value=2>무화과잼 50원</option>
							<option value=3>버터 나이프 20원</option>
						</select>
					</div>
				</article>


				<article class="buy-total">
					<!-- 기본 상품 -->
					<div class="grayBox total-area choose-price">
						<span>상품 가격</span> <span class="prices" id="pdt-price">${store.price * (100-store.discountPer)/100}원</span>
					</div>

					<!-- 선택한 상품 존재 -->
					<div class="grayBox total-area choose-option option1">
						<span>추가상품: 카야잼 30원</span> <img class="clear-btn" id="clearBtn"
							src="${contextPath}/resources/images/subscribe/clear.png"
							alt="clear">

						<div class="totalCount">
							<img class="minus-btn"
								src="${contextPath}/resources/images/subscribe/minus-btn.png"
								alt="minus btn" onclick='totalCount("minus",0)'> <span
								class="total-result">1</span> <img class="add-btn"
								src="${contextPath}/resources/images/subscribe/add-btn.png"
								alt="add btn" onclick='totalCount("add",0)'>
						</div>
					</div>
					<div class="grayBox total-area choose-option option2">
						<span>추가상품: 무화과잼 50원</span> <img class="clear-btn"
							id="clearBtn"
							src="${contextPath}/resources/images/subscribe/clear.png"
							alt="clear">

						<div class="totalCount">
							<img class="minus-btn"
								src="${contextPath}/resources/images/subscribe/minus-btn.png"
								alt="minus btn" onclick='totalCount("minus",1)'> <span
								class="total-result">1</span> <img class="add-btn"
								src="${contextPath}/resources/images/subscribe/add-btn.png"
								alt="add btn" onclick='totalCount("add",1)'>
						</div>
					</div>
					<div class="grayBox total-area choose-option option3">
						<span>추가상품: 버터 나이프 20원</span> <img class="clear-btn"
							id="clearBtn"
							src="${contextPath}/resources/images/subscribe/clear.png"
							alt="clear">

						<div class="totalCount">
							<img class="minus-btn"
								src="${contextPath}/resources/images/subscribe/minus-btn.png"
								alt="minus btn" onclick='totalCount("minus",2)'> <span
								class="total-result">1</span> <img class="add-btn"
								src="${contextPath}/resources/images/subscribe/add-btn.png"
								alt="add btn" onclick='totalCount("add",2)'>
						</div>
					</div>
				</article>

				<article class="total-price">
					<p>총 구매 금액</p>
					<span class="resultPrice"></span>
				</article>
				<div class="submit-sub">
					<button class="shopping-btn"
						onclick=<c:if test="${not empty loginMember }"> 'cart();'</c:if>
						<c:if test="${empty loginMember }">'infoAlert();'</c:if>>
						<span>장바구니</span>
					</button>
					<button class="submit-btn"
						onclick=<c:if test="${not empty loginMember }"> 'buy();'</c:if>
						<c:if test="${empty loginMember }">'infoAlert();'</c:if>>
						<span>바로 구매</span>
					</button>
				</div>
			</section>
		</section>


		<!-- contents -->
		<div class="contents-top-line"></div>
		<section class="detail-contents">
			<ul>
				<li onclick="scrollExp()">상품설명</li>
				<li onclick="scrollReview()">리뷰</li>
				<li onclick="scrollDelievery()">배송/교환/환불</li>

				<div class="contents-bottom-line"></div>
			</ul>

		</section>
		<section class="content">
			<!-- 상품 설명 -->
			<article id="contents-exp">
				<img class="exp-img"
					src="${contextPath}/resources/images/subscribe/sub_detail_bread.jpg"
					alt="sub_detail_bread">
			</article>


			<!-- 리뷰 -->
			<article class="boundary-line" id="contents-review">
				<div class="header-title review-header " id="pagination">
					<p>리뷰</p>
					<div class="review-header-right">
						<div class="align-div" id="align-div">
							<select id='selectcate'>
								<option disabled="disabled" selected>정렬</option>
								<option value="0">최신순</option>
								<option value="2">별점높은순</option>
								<option value="1">별점낮은순</option>
							</select>
						</div>
						<c:if test="${not empty loginMember }">						
						<a class="reviewWrite" href="${contextPath}/board/review/write">리뷰쓰기</a>
						</c:if>
					</div>
				</div>
			</article>
			<article id="reviewbox">
				<c:forEach items="${reviewList}" var="r">

					<div class="one-line-review">
						<div class="star-ratings">
							<div class="star-ratings-fill">
								<c:if test="${r.point eq 1}">
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gray"></i>
									<i class="fas fa-star gray"></i>
									<i class="fas fa-star gray"></i>
									<i class="fas fa-star gray"></i>
								</c:if>
								<c:if test="${r.point eq 2}">
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gray"></i>
									<i class="fas fa-star gray"></i>
									<i class="fas fa-star gray"></i>
								</c:if>
								<c:if test="${r.point eq 3}">
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gray"></i>
									<i class="fas fa-star gray"></i>
								</c:if>
								<c:if test="${r.point eq 4}">
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gray"></i>
								</c:if>
								<c:if test="${r.point eq 5}">
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
									<i class="fas fa-star gold"></i>
								</c:if>
							</div>
						</div>
						<span class="review-title">${r.title}</span> <span>${r.writer}</span>
						<span>${r.date}</span>
					</div>
					<div class="one-line-review review-content">${r.content}</div>
				</c:forEach>
			</article>
			<!-- 리뷰내용  -->
			<!-- 페이지네이션 -->
			<article class="pagination-area">
					<ul class="pagination">
		<c:if test="${pagination.startPage !=1 }">
			<li><a class="page-link"
				href="?cp=1&sr=${param.sr}">&lt;&lt;</a></li>
			<li><a class="page-link"
				href="?cp=${pagination.prevPage}&sr=${param.sr}">&lt;</a></li>
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
						href="?cp=${i}&sr=${param.sr}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pagination.endPage != pagination.maxPage }">
			<li><a class="page-link"
				href="?cp=${pagination.nextPage}&sr=${param.sr}">&gt;</a></li>
			<li><a class="page-link"
				href="?cp=${pagination.maxPage }&sr=${param.sr}">&gt;&gt;</a></li>
		</c:if>
	</ul>
			</article>

		</section>

		<!-- 배송/교환/환불 -->
		<article class=" contents-delievery boundary-line "
			id="contents-delievery">
			<div class="header-title ">
				<p>배송/교환/환불</p>
			</div>

			<div class="delievery-box delievery-area">
				<div class="delievery-title">
					<p>배송 정보</p>
				</div>
				<div class="area-line"></div>
				<div class="delievery-content">
					<pre>
DELIVERY / 배송정보
배송 방법 : 택배
배송 지역 : 전국지역
배송 비용 : 3,000원
배송 기간 : 2일 ~ 7일
- 제주, 산간벽지, 도서지방은 배송에서 제외됩니다.
고객님께서 주문하신 상품은 입금 확인후 배송해 드립니다.
다만, 상품종류에 따라서 상품의 배송이 다소 지연될 수 있습니다.
배송은 발송 후 익일 도착을 원칙으로 하며, 금요일 오전 9시 이후 주문분은 월요일 수령 가능합니다.
</pre>
				</div>
			</div>

			<div class="refund-box delievery-area">
				<div class="refund-title">
					<p>교환/환불 정보</p>
				</div>
				<div class="area-line refund-line"></div>
				<div class="refund-content ">
					<pre>
교환 및 반품 주소
- 서울특별시 중구 남대문로 120 대일빌딩 2층, 3층 (JeanValJean)

교환 및 반품에 따른 요청 가능 기간
- 구매자 단순 변심은 상품 수령 후 7일 이내 (구매자 반품배송비 부담)
- 표시/광고와 상이, 상품 하자의 경우 상품 수령 후 3개월 이내 혹은 표시/광고와 다른 사실을 안 날로부터 30일 이내 (판매자 반품 배송비 부담)
둘 중 하나 경과 시 반품/교환 불가

교환 및 반품 불가능 사유
1. 반품요청기간이 지난 경우
2. 구매자의 책임 있는 사유로 상품 등이 멸실 또는 훼손된 경우 (단, 상품의 내용을 확인하기 위하여 포장 등을 훼손한 경우는 제외)
3. 구매자의 책임 있는 사유로 포장이 훼손되어 상품 가치가 현저히 상실된 경우 (예: 식품, 화장품, 향수류, 음반 등)
4. 구매자의 사용 또는 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우 (라벨이 떨어진 의류 또는 태그가 떨어진 명품관 상품인 경우)
5. 시간의 경과에 의하여 재판매가 곤란할 정도로 상품 등의 가치가 현저히 감소한 경우
6. 고객의 요청사항에 맞춰 제작에 들어가는 맞춤제작상품의 경우 (판매자에게 회복불가능한 손해가 예상되고,
 그러한 예정으로 청약철회권 행사가 불가하다는 사실을 서면 동의 받은 경우)
7. 복제가 가능한 상품 등의 포장을 훼손한 경우 (CD/DVD/GAME/도서의 경우 포장 개봉 시)
</pre>
				</div>
			</div>
		</article>



	</main>
	<script>
	const cp = '${param.cp}';
	const sr = '${param.sr}';
	const loginMember = '${loginMember.memberNo}';
	const price = ${store.price * (100-store.discountPer)/100};
	const stock = ${store.stock};
	const adStock = [${advantage[0].stock},${advantage[1].stock},${advantage[2].stock}];
	const storeNo = ${store.storeNo};
	const adPrice = [${advantage[0].price},${advantage[1].price},${advantage[2].price}];
	</script>



	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript"
		src="${contextPath}/resources/js/store/storeDetail.js"></script>
		<!--  kakao sdk  -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>


	<script>
	// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('551a4828db4bdc904ebb55328c53c9ae');
    // SDK 초기화 여부를 판단합니다.
    console.log(Kakao.isInitialized());
    function kakaoShare() {
        const title = document.querySelector("body > main > section.top > section.product_detail > article.category_product > div > span").innerText;
        const url = location.href;
        const mainurl = document.querySelector("body > main > section.top > section.product-thumbnail > div > img:nth-child(1)").getAttribute("src");
        console.log(contextPath);
        console.log(url);
        Kakao.Link.sendDefault({
            objectType: 'feed',
            content: {
            title: '장발장의 특별한 빵을 만나보세요!',
            description: title,
            imageUrl: mainurl,
            link: {
                mobileWebUrl: url,
                webUrl: url,
            },
            },
            buttons: [
            {
                title: '웹으로 보기',
                link: {
                mobileWebUrl: url,
                webUrl: url,
                },
            },
            ],
            // 카카오톡 미설치 시 카카오톡 설치 경로이동
            installTalk: true,
        })
    }
    </script>
</body>
</html>