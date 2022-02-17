<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link rel="stylesheet" href="${contextPath}/resources/css/payment/payment.css">
<link rel="stylesheet" href="${contextPath}/resources/css/cart.css">

</head>
<body>

	<div class="j-wrapper">
		<h1 style="margin: 35px 0; color: #564334">장바구니</h1>
		<div class="hr"></div>
		<div class="inline-block">

			<div style="width: 486px; height: 50px; text-align: center;">
				<h2>상품정보</h2>
			</div>
			<div
				style="width: 330px; height: 50px; text-align: center; margin: 0 40px">
				<h2>추가상품</h2>
			</div>
			<div style="width: 204px; height: 50px; text-align: center;">
				<h2>합계금액</h2>
			</div>
		</div>



		<div class="hr" style="height: 5px; margin: 20px 0 30px 0"></div>

		<c:forEach items="${cartList}" var="cart">

			<c:if test="${cart.parentNo eq 0}">
		
				<div class="inline-block items">
					<input type="hidden" value="${cart.cartNo}">
					<input type="hidden" value="${cart.productNo}" class = "productsNo">
					<div
						style="width: 486px; height: 195px; border-right: 1px solid #d3c5b6;"
						class="inline-block">
						<h4 class="x-btn">x</h4>
						<div class="j-img"
							style="background-image: url('${contextPath}${cart.imgPath }');"></div>
						<div>

							<h4 style="margin-bottom: 25px;">${cart.productName }</h4>
							<c:if test="${cart.discountPer ne 0}">
								<h5
									style="margin-bottom: 15px; display: inline-block; text-decoration: line-through; color: gray">${cart.price}</h5>
							</c:if>
							<h3 style="display: inline-block" class="productPrice">${cart.price * (100-cart.discountPer)/100}</h3>

							<br> 
							
							<img>x
							<h4 class="addq"
								style="display: inline-block; margin: 0 20px; vertical-align: top;"> ${cart.addq}</h4>
							<img >
						</div>
					</div>

					<div
						style="width: 330px; margin: 0 40px; height: 195px; border-right: 1px solid #d3c5b6;"
						class="inline-block option-box">
						<c:forEach items="${cartList}" var="cart2">
							<c:if test="${cart2.parentNo eq cart.cartNo}">

								<h4 style="margin: 10px 0 25px 60px;">${cart2.productName}
									${cart2.price}원 x ${cart2.addq}</h4>
								<input class="optionPrice" type="hidden"
									value="${cart2.price * cart2.addq *(100-cart2.discountPer)/100} ">
							</c:if>
						</c:forEach>
					</div>
					<div style="width: 204px; height: 195px; text-align: center;">
						<h2 class="cartSumPrice" style="margin-top: 80px">0원</h2>

					</div>

				</div>
				<div class="hr" style="height: 1px; margin: 30px 0 30px 0"></div>
			</c:if>
		
		</c:forEach>
		
		
		            <!-- 주문자 정보 -->
            <div class="orderer-info-title">
                <h2 class="listTitle" style="margin-left:0">주문자 정보</h2>
            </div>
           <div class="hr" style="height: 1px; margin: 0 0 30px 0"></div>
                 <div>
                    <input type="radio" name="orderer-addr" class="selectInfo" id="member-addr" value="1" checked> 
                    <label for="member-addr">회원정보와 동일</label>
                    <input type="radio" name="orderer-addr" class="selectInfo" value="2" id="new-addr"> 
                    <label for="new-addr">새로운 배송지</label>
                </div>
                	<div class="loginForm">
            	<div class="input-info-div">
                      <div class="p-div"><p>받는 사람<span> *</span></p></div>
                      <div class="span-div"><span>${loginMember.memberName}</span></div>
                </div>
                <c:if test="${ empty loginMember.memberAddress}">
                
                	 <div class="input-info-div">
                        <div class="p-div"><p>주소<span> *</span></p></div>
                        <div class="input-div"><input type="text" id="addr1" class="receiver-info addr"></div>
                        <div class="input-div"><button id="searchAddr" class="input-btn" type="button">주소검색</button></div>
                    </div>
                    <div class="input-info-div">
                        <div class="p-div"></div>
                        <div class="input-div"><input type="text" id="addr2" class="receiver-info"></div>
                    </div>
                    <div class="input-info-div">
                        <div class="p-div"></div>
                        <div class="input-div"><input type="text"  class="receiver-info"></div>
                    </div>
                </c:if>
                
                	<div class="input-info-div">
                        <div class="p-div"><p>휴대폰번호<span> *</span></p></div>
                 		<div class="span-div"><span>${loginMember.memberPhone}</span></div>
                 	</div>
                
                <div class="input-info-div">
                      <div class="p-div"><p>이메일<span> *</span></p></div>
                      <div class="span-div"><span>${loginMember.memberEmail}</span></div>
                </div>
            	
            	</div>
            <div class="orderer-info">
            
            <!-- 이부분은 원데이 클래스를 제외한 두 스토어에서 사용되는 항목입니다.
            	배송지 정보 입력하는 부분입니다.
             -->

            <br>
            </div>
            
            <!-- 새로운 배송지 선택시 보이는 div-->
            	<div class="newInfo">
                <div class="input-info">
                    <div class="input-info-div">
                        <div class="p-div"><p>받는 사람<span> *</span></p></div>
                        <div class="input-div"><input type="text" class="receiver-info"></div>
                    </div>
                    <div class="input-info-div">
                        <div class="p-div"><p>주소<span> *</span></p></div>
                        <div class="input-div"><input type="text" id="addr1" class="receiver-info addr"></div>
                        <div class="input-div"><button id="searchAddr" class="input-btn" type="button">주소검색</button></div>
                    </div>
                    <div class="input-info-div">
                        <div class="p-div"></div>
                        <div class="input-div"><input type="text" id="addr2" class="receiver-info"></div>
                    </div>
                    <div class="input-info-div">
                        <div class="p-div"></div>
                        <div class="input-div"><input type="text"  class="receiver-info"></div>
                    </div>

                    <div class="input-info-div">
                        <div class="p-div"><p>휴대폰번호<span> *</span></p></div>
                        <div class="input-div">
                            <select type="text" class="receiver-info-select phone-input">
                                <option>010</option>
                                <option>011</option>
                                <option>016</option>
                                <option>017</option>    
                                <option>019</option>
                            </select>
                        </div> 
                        <span>-</span>
                        <div class="input-div"><input type="text" class="receiver-info phone-input"></div>
                        <span>-</span>
                        <div class="input-div"><input type="text" class="receiver-info phone-input"></div>
                    </div>

                    <div class="input-info-div">
                        <div class="p-div"><p>이메일<span> *</span></p></div>
                        <div class="input-div hidden-email"><input type="text" class="receiver-info email-input hidden-email"></div>
                        <span class="hidden-email">@</span>
                        <div class="input-div hidden-email">
                            <input type="text" class="receiver-info email-input hidden-email" id="email-input-select">
                            <select class="receiver-info hidden-email" id="email-select">
                                <option value="0">직접입력</option>
                                <option value="1">naver.com</option>
                                <option value="2">daum.net</option>
                                <option value="3">gmail.com</option>  
                            </select>
                        </div> 
                    </div>
                    </div>
                    </div>

                    <div class="input-info-div">
                        <div class="p-div"><p style="line-height:3">배송메세지</p></div>
                        <div class="input-div">
                            <select class="receiver-info" id="delivery-message-select">
                                <option>-- 메세지 선택 --</option>
                                <option value="배송 전에 미리 연락바랍니다.">배송 전에 미리 연락바랍니다.</option>
                                <option value="부재 시 경비실에 맡겨주세요.">부재 시 경비실에 맡겨주세요.</option>
                                <option value="부재시 문 앞에 놓아주세요.">부재시 문 앞에 놓아주세요.</option>  
                                <option value="빠른 배송 부탁드립니다." selected>빠른 배송 부탁드립니다.</option>  
                                <option value="택배함에 보관해 주세요.">택배함에 보관해 주세요.</option>  
                            </select>
                            <br>
                            <input type="text" class="receiver-info" id="delivery-message">
                        </div>
                    </div>
            
            
            
            
            
            
            
		<div class="j-sum inline-block">
			쿠폰 할인: 
			<select class = "couponCss receiver-info"  style="font-size:18px;">
			<option value ="0">쿠폰선택</option>
			</select>
			<h1>총 할인 금액</h1>
			<h1 class="calcDis">0</h1><h1 style="margin-right:20px">원</h1>
			
		</div>
		<div class="j-sum inline-block">
			<p>
				총 주문 금액 <b class="resultPrice">0</b> + 배송비 <b class="taxPrice">3,000</b>원
				- 할인 금액 <b class="discountPrice"> 0</b>원
				=
			</p>
			<h1>총 결제 금액</h1>
			<h1 class="lastMaxPrice">0</h1><h1 style="margin-right:20px">원</h1>
			
		</div>
		
		<div class="j-notice">


			<p>구매 전 확인해주세요.</p>
			<p>- 구매 금액 합산 30,000원 이상일 경우, 배송비는 무료입니다. (단, [정기구독], [무료배송] 상품은
				구매금액 합산에 포함되지 않습니다.)</p>

		</div>
		
		<div class="j-buy" id="payment-btn" style="width : 100%; height : 80px">
			<h2 >구매하기</h2>
		</div>
	</div>
	<form action="#" method="POST" name="requestForm">
		<input type="hidden" name="carrierList" value="">
	</form>
	<script>
	const specialContextPath = '${contextPath}';
	//const carrierList = '${carrierList}';
	</script>
	<script>
		const productCd =1;
		const totalpp = document.getElementsByClassName("lastMaxPrice")[0].innerText.replace(',', '').replace('원','');
		//const amount = parseInt(totalpp);
		const loginMember = "${loginMember.memberNo}"
		const productNo = 0;
  		const memberId = "${loginMember.memberEmail}"
		const amount =100;
	</script>
	<script src="${contextPath}/resources/js/member/cart.js"></script>
	<jsp:include page="../common/footer.jsp" />
	
	
	
  	<!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
		<!-- 다음 지도 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="${contextPath}/resources/js/payment/payment.js"></script>
	<script src="${contextPath}/resources/js/payment/paymentDbFunction.js"></script>
  <script>
  document.querySelector("#payment-btn").addEventListener("click", function(){
	    const productNoQ = document.querySelector(".productsNo");
	    if(productNoQ==null){
	        alert("상품을 담아주세요!");
	        return;
	    }
    requestPay();
  });
  
  function requestPay() {
	  var IMP = window.IMP; // 생략가능
	  IMP.init('imp64541030');
	  
	  // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
	  // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
	  
	  IMP.request_pay({
	  pg: 'inicis', // version 1.1.0부터 지원.
	  /*
	  'kakao':카카오페이, html5_inicis':이니시스(웹표준결제) 'nice':나이스페이 'jtnet':제이티넷 'uplus':LG유플러스
	  'danal':다날 'payco':페이코 'syrup':시럽페이 'paypal':페이팔
	  */
	  pay_method: 'card',
	  
	  /* 'samsung':삼성페이, 'card':신용카드, 'trans':실시간계좌이체, 'vbank':가상계좌, 'phone':휴대폰소액결제 */
	  
	  merchant_uid: 'merchant_' + new Date().getTime(),
	  /* merchant_uid에 경우 https://docs.iamport.kr/implementation/payment */
	  
	  name: 'JVJ스토어상품',
	  
	  //결제창에서 보여질 이름
	  amount : amount,
	  
	  //가격
	  buyer_email: '${loginMember.memberEmail}',
	  buyer_name: '${loginMember.memberName}',
	  buyer_tel: '${loginMember.memberPhone}',
	  buyer_addr: '서울특별시 중구 남대문로 120 대일빌딩 2F, 3F',
	  buyer_postcode: '04540', 
	  m_redirect_url: specialContextPath+'/payments/complete'
	  
	  /*
		  모바일 결제시, 결제가 끝나고 랜딩되는 URL을 지정
		  (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
	  */
  }, 
	  function (rsp) {
	  		console.log(rsp);
	  		let resultUrl =''; 
	  		let resultpayid ='';
	  	if (rsp.success) {
	  		saveStoreOrderInfo(rsp.imp_uid,rsp.merchant_uid,rsp.paid_amount);
	  		var msg = '결제가 완료되었습니다.';
		  		msg += '결제 금액 : ' + rsp.paid_amount;
		  		alert(msg);
	  		location.href = contextPath+"/store?cp=1&ct=0&op=0";
	  } else {
	  	var msg = '결제에 실패하였습니다.';
	  		msg += '에러내용 : ' + rsp.error_msg;
		  	alert(msg);
	  }
	  	
	  });
		
	  }
  		
  </script>
</html>