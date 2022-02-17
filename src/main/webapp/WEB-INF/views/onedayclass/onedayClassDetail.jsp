<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JVJ 원데이클래스</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/classDetail.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <main>
        <section class="top">
            <section class="main-nav">
                <a href="${contextPath}">HOME</a>
                <img src="${contextPath}/resources/images/common/expand_less.png" alt="expand_less">
                <a href="${contextPath}/onedayclass/list">원데이 클래스</a>
                <img src="${contextPath}/resources/images/common/expand_less.png" alt="expand_less">
                <a href="#">클래스 상세</a>
            </section>     
            <section class="product-thumbnail">
                <article class="main-img-area">
                    <img class="main-thumbnail" src="${contextPath}${Oneclass.classImgList[0].productImgPath}${Oneclass.classImgList[0].productImgName}" alt="sub-bread-main">
                </article>
            <div>
            	<c:forEach items="${Oneclass.classImgList}" var="imginfo">
	                <img class="sub-img img-margin" src="${contextPath}${imginfo.productImgPath}${imginfo.productImgName}" alt="sub-bread-main">
            	</c:forEach>
                </div>
            </section>
            <section class="product_detail">
            	<form action="${contextPath}/payment/classPayment" method="POST" onsubmit="return reconfirim();">
                <article class="category_product">
                    <div class="category-title">
                        <span>${Oneclass.title}</span>
                        <input type="hidden" name="classtitle" readonly value="${Oneclass.title}">
                        <div class="heart-btn">
							<div class="contentss">
								<div class="heart"></div>
							</div>
							<div class="text">클래스 <br> 좋아요</div>
						</div>
						<div class="kakaolink">
						    <a id="kakao-link-btn" href="javascript:kakaoShare()">
						    	<img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" />
						    </a>
						    <span id="kakaolink-text" class="text">카톡으로 <br>공유하기</span>
						</div>
                    </div>
                </article>
                <div class="bottom-line"></div>
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>가격</span>
                    </div>
                    <div class="period-area btn-area"> 
 						 <span class="price">${Oneclass.price}원</span>
                    </div>
                </article>
                <div class="bottom-line"></div>
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>지점명</span>
                    </div>
                    <div class="subdetail-content"> 
                          <input type="text" name="place" readonly value="${Oneclass.placeName}"><button onclick="showMaps()" type="button"><i class="fas fa-map-marker-alt"></i> 위치</button>
                    </div>
                </article>
                <div class="bottom-line"></div>
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>수강일</span>
                        <span></span>
                    </div>
                    <div class="subdetail-content"> 
                        <input type="text" readonly value="${Oneclass.classDt}" name="classdate">
                    </div>
                </article>
                <div class="bottom-line"></div>
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>수업 시간</span>
                    </div>
                    <div class="subdetail-content"> 
                      	<input type="text" readonly value="${Oneclass.classtime}" name="classtime">
                    </div>
                </article>
                <div class="bottom-line"></div>
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>신청 현황</span>
                    </div>
                    <div class="subdetail-content"> 
                        <span class="nowsubmit"><span>${Oneclass.nowPeople}</span><span> / </span><span>${Oneclass.classMaxPpl}</span></span>
                    </div>
                </article>
                <div class="bottom-line"></div>
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>신청 인원</span>
                    </div>
                    <div class="subdetail-content"> 
                        <div class="buy-count">
                        <img class="minus-btn" src="${contextPath}/resources/images/subscribe/minus-btn.png" alt="minus-btn" onclick="minusppl()">
                        <span id="result">0</span>
                        <img class="add-btn" src="${contextPath}/resources/images/subscribe/add-btn.png" alt="add-btn" onclick="plusppl()">
                    	</div>
                    </div>
                </article>
                <div class="bottom-line"></div>

                <article class="total-price">
                    <!-- 선택한 상품 있을경우 -->
                    <!-- <div class="total-area">
                        <span>구독 상품을 선택해주세요.</span>
                    </div> -->
                    <!-- 선택한 상품 없을경우 -->
                    <div class="total-area">
	                   	<span>${Oneclass.title}</span>
	                    <span>${Oneclass.placeName} / ${Oneclass.classtime} / <span id="ppl">0</span>명</span>
                    </div>
                    <div class="showpay">
	                    <p>총 구매 금액 <span id="totalprice" class="showprice">0</span>  <span class="showprice">원</span></p>
                    </div>
                </article>
                <div class="submit-sub">
                                <!-- 상태코드 넘어가는 input -->
                	<!-- <input type="hidden" name="periodStatusCode"> -->
                    <input id="hiddenTotalprice" type="hidden" name="totalPrice">
                    <input id="hiddenTotalPeople" type="hidden" name="totalPeople">
                    <input id="categoryCode" type="hidden" name="categoryCode" value="3">
                    <input type="hidden" value="${productNo}" name="productNo">
                    <button id="submit-btn" class="submit-btn" type="submit">
                        <span>클래스 신청</span>
                    </button>
                </div>
                </form>
<!-- formData   클래스이름-- classtitle
				지점명 -- place
				수강일 -- classdate
				수강시간 -- classtime
				신청 인원수 -- totalPeople
				총 가격 -- totalPrice
				상품번호 -- productNo -->
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
                ${Oneclass.contents}
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
        <article class=" contents-delievery boundary-line " id="contents-delievery">
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
      	<div id="cal">
        <div id="showmap">
        <span>지점 위치</span><button onclick="hideMaps()">닫기</button>
        <div id="map" style="width:100%;height:100%;"></div>
        </div>
    	</div>
	<jsp:include page="../common/footer.jsp" />	
	<script> 
		let mapAddress = "${Oneclass.placeAddr}" ;
		/* const contextPath = "${contextPath}"; */
		const loginNo = "${loginMember.memberNo}"
		const productNo = "${productNo}"
		const cp = '${param.cp}';
		const sr = '${param.sr}';
	</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=551a4828db4bdc904ebb55328c53c9ae&libraries=services,clusterer,drawing"></script>
	
	<!--  kakao sdk  -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/onedayclass/classDetail.js"></script>
	
</body>
</html>