<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />
<link rel="stylesheet" href="${contextPath}/resources/css/payment/paymentResult.css">

  <main>

        <section class="title-section">
            <p>결제완료</p>
        </section>


        <!-- 주문 내역 -->
        <section>

            <!-- 주문자 정보 -->
            <div class="orderer-info-title">
                <p class="listTitle">주문자 정보</p>
            </div>

            <hr>

            <div class="orderer-info">
                <div class="result-info">
                    <div class="result-info-div">
                        <div class="p-div"><p>예약자</p></div>
                        <div class="span-div"><span>${payResult.memberName}</span></div>
                    </div>
                    <div class="result-info-div">
                        <div class="p-div"><p>이메일</p></div>
                        <div class="span-div"><span>${payResult.memberEmail}</span></div>
                    </div>

                    <div class="result-info-div">
                        <div class="p-div"><p>휴대 전화</p></div>
                        <div class="span-div"><span>${payResult.memberPhone}</span></div> 
                    </div>
                </div>
            </div>

            <div class="orderer-info-title">
                <p class="listTitle">주문 내역 확인</p>
            </div>
            <hr>

            <div class="product-list">

                <a href="#"><img src="${contextPath}/${payResult.mainImgPath}"></a>
                <div>
                    <a href="#"><p>${orderInfo.productName}</p></a>
                    <p>
                        ${payResult.productOption} <br>
                        신청인원 : ${payResult.amount} 명<br>
                    </p>
                </div>
                <div>
                    <p>${payResult.totalPrice}원</p>
                </div>
            </div>

            <hr>
              <!-- 최종 결제 금액 -->
            <div class="orderer-info-title">
                <p class="listTitle">최종 결제 금액</p>
            </div>

            <hr>

            <div class="price-container">
                <div class="price-flex">
                    <div class="p-div"><p>총 상품 금액</p></div>
                    <div class="price-won"><p>${payResult.totalPrice}원</p></div>
                </div>
                <div class="price-flex">
                    <div class="p-div"><p>배송비</p></div>
                    <div class="price-won"><p>0원</p></div>
                </div>
                <div class="price-flex">
                    <div class="p-div"><p>쿠폰할인</p></div>
                    <div class="price-won"><p>0원</p></div>
                </div>
                <div class="price-flex">
                    <div class="p-div"><p>마일리지</p></div>
                    <div class="price-won"><p>0원</p></div>
                </div>
            </div>

            <hr class="price-hr">

            <div class="price-container">
                <div class="price-flex">
                    <div class="p-div"><p>총 결제 금액</p></div>
                    <div class="price-won"><p>${payResult.totalPrice}원</p></div>
                </div>
           </div>

        <div class="give-flex">
            <div class="payment-result-btn">
                <button type="button" onclick="goback()">클래스 리스트 보기</button>
            </div>
        </div>

    </section>  

    </main>


<jsp:include page="../common/footer.jsp" />
<script>
	function goback(){
		location.href = contextPath+"/onedayclass/list";
	}
</script>
</body>
</html>