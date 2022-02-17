
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>subCoffee</title>


<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/subBread.css">

 	<!-- 별 -->
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
 	
</head>
<body>

	<main>

        <section class="top">
            <section class="main-nav">
                <a href="${contextPath}">HOME</a>
                <img src="${contextPath}/resources/images/common/expand_less.png" alt="expand_less">
                <a href="${contextPath}/subscribe/subMain">정기 구독</a>
                <img src="${contextPath}/resources/images/common/expand_less.png" alt="expand_less">
                <a href="${contextPath}/subscribe/subCoffee">${subVOList[0].productName}</a>
            </section>  
               
			<section class="product-thumbnail">

                <article class="main-img-area">
                    <c:if test="${subVOImgList[0].productImgLevel == 0}"> 
	                    <img class="main-thumbnail"  
	                    src="${contextPath}${subVOImgList[0].productImgPath}${subVOImgList[0].productImgName}" 
	                    alt="sub-bread-main">
            		</c:if>
                </article>
          
            	<div>
		            <c:forEach items="${subVOImgList}" var="imginfo">
		            
	            		<c:if test="${imginfo.productImgLevel == 0}"> 
			                <img class="sub-img img-margin" src="${contextPath}${imginfo.productImgPath}${imginfo.productImgName}" alt="sub-bread-main">
	            		</c:if>
	            		<c:if test="${imginfo.productImgLevel == 1}"> 
			                <img class="sub-img img-margin" src="${contextPath}${imginfo.productImgPath}${imginfo.productImgName}" alt="sub-bread-main">
	            		</c:if>
	            		<c:if test="${imginfo.productImgLevel == 2}"> 
			                <img class="sub-img img-margin" src="${contextPath}${imginfo.productImgPath}${imginfo.productImgName}" alt="sub-bread-main">
	            		</c:if>
	            		<c:if test="${imginfo.productImgLevel == 3}"> 
			                <img class="sub-img img-margin" src="${contextPath}${imginfo.productImgPath}${imginfo.productImgName}" alt="sub-bread-main">
	            		</c:if>
	            		
		            </c:forEach>
            	</div>
            </section>

            <section class="product_detail">
                <article class="category_product">
                    <div class="category-title">
                        <span>${subVOList[0].productName}</span>

						<div class="top-btn-area">
							<div class="heart-btn">
								<div class="heart-content">
									<div class="heart"></div>
								</div>
							</div>
							<div class="kakao-content">
								<div class="kakaolink ">
									<a id="kakao-link-btn" href="javascript:kakaoShare()"> 
										<img class="kakao-img" src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" style="width: 45px;" />
									</a>
									<!-- <span id="kakaolink-text" class="text">카톡으로 <br>공유하기</span> -->
								</div>
							</div>
						</div>

					</div>
<%--                     <span class="price">${subVOList[0].productPrice} 원</span> --%>
                    <span class="price">25,000 원</span>
                </article>

                <div class="bottom-line"></div>

                <article class="delivery-detail">
                    <span>정기구독 전상품, </span>
                    <span>무료배송!</span>
                </article>

                <div class="bottom-line"></div>
                
                
			 <form action="${contextPath}/payment/subscribePayment" method="POST"   name="subCoffeeForm" onsubmit="return reconfirim();">
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>빵 종류</span>
                        <span></span>
                    </div>
                    <div class="bread btn-area">
						<c:forEach items="${subVOList}" var="sub" begin="0" end="1">
							<c:if test="${sub.subOptionCode eq 31}">
								<button type="button" class="bread-btn btn" name="bread" value="${sub.subOptionNo}">
									<span>${sub.subOptionContent}</span>
								</button>
							</c:if>
						</c:forEach>
					</div>
                </article>
                
                <div class="bottom-line"></div>
                
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>맛 종류</span>
                        <span></span>
                    </div>
                    <div class="taste btn-area"> 
                        <c:forEach items="${subVOList}" var="sub" begin="2" end="4"> 
                   			<c:if test="${sub.subOptionCode eq 32}">
                   				<button type="button" class="taste-btn btn" name="taste" value="${sub.subOptionNo}">
	                            	<span>${sub.subOptionContent}</span>
	                        </button>
	                        </c:if>
	                     </c:forEach> 
                    </div>
                </article>
                <div class="bottom-line"></div>

                <article class="sub-detail">
                    <div class="sub-title">
                        <span>커피 종류</span>
                        <span></span>
                    </div>
                    <div class="coffee btn-area"> 
                        <c:forEach items="${subVOList}" var="sub" begin="5" end="6">
							<c:if test="${sub.subOptionCode eq 33}">
								<button type="button" class="coffee-btn btn" name="coffee" value="${sub.subOptionNo}">
									<span>${sub.subOptionContent}</span>
								</button>
							</c:if>
						</c:forEach>
                    </div>
                </article>
                
                <div class="bottom-line"></div>
                
                <article class="sub-detail">
                    <div class="sub-title">
                        <span>구독 옵션</span>
                        <span></span>
                    </div>
                    <div class="period-area btn-area"> 
                        <c:forEach items="${subVOList}" var="sub" begin="7" end="8">
							<c:if test="${sub.subOptionCode eq 34}">
								<button type="button" class="period-btn btn" name="period" value="${sub.subOptionNo}">
									<span>${sub.subOptionContent}</span>
								</button>
							</c:if>
						</c:forEach>
                    </div>
                </article>

                <div class="bottom-line"></div>

				<article class="sub-detail">
					<div class="sub-title">
						<span>수령 희망일</span> 
						<span></span>
					</div>
					<div class="taste btn-area">
						<c:forEach items="${subVOList}" var="sub" begin="9" end="10">
							<c:if test="${sub.subOptionCode eq 35}">
								<button type="button" class="deliveryDay-btn btn" name="deliveryDay" value="${sub.subOptionNo}">
									<span>${sub.subOptionContent}</span>
								</button>
							</c:if>
						</c:forEach>
					</div>
				</article>
				
				<article class="buy-total">
                 <!-- 선택한 상품 없을경우 -->
            <!--         <div class="total-area-none">
                        <span>구독 상품을 선택해주세요.</span>
                    </div> --> 
                    <!-- 선택한 상품 있을경우 -->
                	<div class="total-area">
                        <span id="bread"></span>
                        <span id="taste"></span>
                        <span id="coffee"></span>
                        <span id="period"></span>
                        <span id="deliveryDay"></span>
                    </div>
                    <div class="buy-count">
                        <img class="minus-btn" src="${contextPath}/resources/images/subscribe/minus-btn.png" alt="minus-btn"  onclick='minusCount()'>
                        <span id="result" >1</span>
                        <img class="add-btn" src="${contextPath}/resources/images/subscribe/add-btn.png" alt="add-btn" onclick='plusCount()'>
                    </div>
                
                </article>
				
                <article class="total-price">
                    <p>총 구매 금액
	                   <!--  <span id="totalprice" class="showprice">0</span> -->
	                    <span id="totalprice" class="showprice">25,000</span>
	                    <span class="showprice">원</span>
                    </p>
                </article>

                <div class="submit-sub">
					<button class="submit-btn" id="submit-btn"  onclick="return reconfirim();">
						<span>바로 구독 신청</span>
					</button>
                </div>
                
                <!-- 
					선택한 버튼의 subOptionNo 값이 넘어감
					
					chooseBreadCode : 빵
					chooseTasteCode : 맛
					choosePeriodCode : 구독 기간
					chooseDeliveryDayCode : 수령 희망일
					totalAmount : 최종 수량
					
					-->
						<input type="hidden" id="chooseBreadCode" name="chooseBreadCode">
						<input type="hidden" id="chooseTasteCode" name="chooseTasteCode">
						<input type="hidden" id="choosePeriodCode" name="choosePeriodCode">
						<input type="hidden" id="chooseDeliveryDayCode" name="chooseDeliveryDayCode">
						<input type="hidden" id="chooseCoffeeCode" name="chooseCoffeeCode">
						<input type="hidden" id="hiddentotalAmount" name="hiddentotalAmount">
						<input type="hidden" id="hiddenTotalPrice" name="hiddenTotalPrice">
						
						<input type="hidden" id="memberNo" name="memberNo" value="${loginMember.memberNo}">
						<input type="hidden" id="productNo" name="productNo" value="${subVOList[0].productNo}">
            </form>
            </section>
        </section>
    	

        <!-- contents -->
        <div class="contents-top-line"></div>        
        <section class="detail-contents">
            <ul >
                <li onclick="scrollExp()">상품설명</li>
                <li onclick="scrollReview()">리뷰</li>
                <li onclick="scrollDelievery()">배송/교환/환불</li>

                <div class="contents-bottom-line"></div>        
            </ul>
            
        </section>
        <section class="content">
            <!-- 상품 설명 -->
            <article id="contents-exp">
                <img class="exp-img" src="${contextPath}/resources/images/subscribe/sub_detail_coffee.jpg" alt="sub_detail_coffee">
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


<jsp:include page="../common/footer.jsp" />	
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<script>
// 로그인한 회원의 회원 번호, 비로그인 시 "" (빈문자열)
const loginMember = "${loginMember.memberNo}";
const cp = '${param.cp}';
const sr = '${param.sr}';


const subBread= "${subVOList[0].productName}";
const url = location.href;
const subBreadImg = document.querySelector("body > main > section.top > section.product-thumbnail > div > img:nth-child(1)").getAttribute('src')


function chooseBtn(){
	// 빵 버튼 제외 모두 비활성화
	$(".btn").not(".bread-btn").attr("disabled", true);
	
	// 빵
	$(".bread-btn").on("click", function() {
	    $(this).addClass('active').siblings().removeClass('active');
	    
	    document.getElementById("bread").innerText 
	    	= $(".bread-btn.active").find('span').text()+ ' / ';
	    	
	    $("input[name='chooseBreadCode']").attr('value', $(this).val());
	    
	    // 맛 선택 버튼 활성
	    $(".taste-btn").attr("disabled", false);
	    
	});
	
	// a맛 버튼 선택 시 
	$(".taste-btn").on("click", function() {
		$(this).addClass('active').siblings().removeClass('active');

		document.getElementById("taste").innerText
			= $(".taste-btn.active").find('span').text() + ' / ';

		$("input[name='chooseTasteCode']").attr('value', $(this).val());

		// 기간 선택 버튼 활성
		$(".coffee-btn").attr("disabled", false);
	
	});
	
	// 커피 버튼 선택 시 
	$(".coffee-btn").on("click", function() {
		$(this).addClass('active').siblings().removeClass('active');

		document.getElementById("coffee").innerText
			= $(".coffee-btn.active").find('span').text() + ' / ';

		$("input[name='chooseCoffeeCode']").attr('value', $(this).val());

		// 기간 선택 버튼 활성
		$(".period-btn").attr("disabled", false);
	
	});

	
	// 구독 기간 (1주 2주)
	$(".period-btn").on("click", function() {
	    $(this).addClass('active').siblings().removeClass('active');
	    
	    document.getElementById("period").innerText
	        = $(".period-btn.active").find('span').text() + ' / ';
	        
		$("input[name='choosePeriodCode']").attr('value', $(this).val());
		
		// 기간 선택 버튼 활성
		$(".deliveryDay-btn").attr("disabled", false);
	});
	
	// 수령 희망일 
	$(".deliveryDay-btn").on("click", function() {
	    $(this).addClass('active').siblings().removeClass('active');
	    
	    document.getElementById("deliveryDay").innerText 
	    	= $(".deliveryDay-btn.active").find('span').text();
	    	
	    $("input[name='chooseDeliveryDayCode']").attr('value', $(this).val());
	});
}


	
	
	
//결제 페이지 이동
function reconfirim(){
	if(loginMember==''){
		alert("로그인 후 가능합니다");
		return false;
 	}
	
	if ( !$(".bread-btn.active")[0] || !$(".taste-btn.active")[0] || !$(".coffee-btn.active")[0]
 		|| !$(".period-btn.active")[0] || !$(".deliveryDay-btn.active")[0]) { // 빵 버튼이 선택되지 않은경우
		alert("옵션을 선택해주세요");
		return false;
	}

	if (resultNum == 0) {
		alert("구매 수량을 선택해주세요");
		return false;
	}
	
	if (loginMember != '' && resultNum != 0 && $(".bread-btn.active")[0]
			&& $(".taste-btn.active")[0] && $(".coffee-btn.active")[0]
			&& $(".period-btn.active")[0] && $(".deliveryDay-btn.active")[0]) {
		
		$("#hiddentotalAmount").val(resultNum);
		$("#hiddenTotalPrice").val(totalprice.innerText.replace(",",""));
		return true;
	} else {
		return false;
	}
}



//SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
Kakao.init('551a4828db4bdc904ebb55328c53c9ae');
//SDK 초기화 여부를 판단합니다.
console.log(Kakao.isInitialized());

function kakaoShare() {
<!--  <한글 부분은 각자 설정!!> -->

	   console.log(contextPath);
	   console.log(url);
	   Kakao.Link.sendDefault({
	       objectType: 'feed',
		       content: {
		       title:  subBread ,
		       description:  subBread,
		       imageUrl:  subBreadImg,
		       link: {
		           mobileWebUrl: url,
		           webUrl: url,
		       },
	       }, buttons: [
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

<%-- <script type="text/javascript" src="${contextPath}/resources/js/subscribe/subCoffee.js"></script> --%>
 	<script type="text/javascript" src="${contextPath}/resources/js/subscribe/subBread.js"></script>

