<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />

<link rel="stylesheet" href="${contextPath}/resources/css/payment/payment.css">
 <main>
        <section class="title-section">
            <p>주문 / 결제 </p>  
        </section>
		
		
        <!-- 주문 내역 -->
        <section>
            <p class="listTitle">주문 내역 확인</p>
            <hr>
			
			
			<!-- 원데이 클래스 용 주문 확인 -->
			<c:if test="${ productcate eq 3 }">
            <div class="product-list">
                <a href="#"><img src="${contextPath}/${oneClass.classImgList[0].productImgPath}/${oneClass.classImgList[0].productImgName}"></a>
                <div>
                    <a href="#"><p>${oneClass.title}</p></a>
                    <p>
                        지점명 : <span class="classTitle">${oneClass.title}</span><br>
                        수강일 : <span class="classDt">${oneClass.classDt }</span> <br>
                        수업 시간 :<span class="classtime"> ${oneClass.classtime }</span> <br>
                        신청인원 : <span class="totalPeople">${totalPeople }</span> <br>
                    </p>
                </div>
                <div>
                    <p class="classPrice">${oneClass.price}원</p>
                </div>
            </div>
            	<c:set var="totalP" value="${oneClass.price * totalPeople}" />
			</c:if>
			
            
            
            <!-- 정기구독용 주문내역확인 -->
            <c:if test="${ productcate eq 2}">
            <div>${carrierList }</div>
            <div>${cartList }</div>
            <div class="product-list">
			<c:if test="${!empty oneSubsOrder.optionList[4]}">  <c:set var="optionNo4" value="/ ${oneSubsOrder.optionList[4].optioanName} " /> </c:if> 
                <a href="#"><img src="${contextPath}${oneSubsOrder.classImgList[0].productImgPath}${oneSubsOrder.classImgList[0].productImgName}"></a>
                <div>
                    <a href="#"><p class="titlesubs">${oneSubsOrder.title}</p></a>
                    <p> 첫 구독일 : ${oneSubsOrder.optionList[0].optioanName} , 2022-02-11 <br>
                        구독내용 : ${oneSubsOrder.optionList[2].optioanName} /  ${oneSubsOrder.optionList[3].optioanName} 
                        / 2개월동안 X ${oneSubsOrder.optionList[1].optioanName} 마다 <br>
                        수량 ${oneSubsOrder.totalAmount}개 <br>
                    </p>
                    <input type="hidden" value="${oneSubsOrder.optionList[0].optioanName} / ${oneSubsOrder.optionList[1].optioanName} / ${oneSubsOrder.optionList[2].optioanName} / ${oneSubsOrder.optionList[3].optioanName} ${optionNo4}" name="totalOption">
                </div>
                <div>
                    <p>${oneSubsOrder.totalPrice}원</p>
                </div>
            </div>
            <c:set var="totalP" value="${oneSubsOrder.totalPrice}" />
            </c:if> 
            
            <!-- 정기구독용 주문내역확인 -->
            <c:if test="${ productcate eq 1}">
            <div class="product-list">
			<c:if test="${!empty oneSubsOrder.optionList[4]}">  <c:set var="optionNo4" value="/ ${oneSubsOrder.optionList[4].optioanName} " /> </c:if> 
                <a href="#"><img src="${contextPath}sd"></a>
                <div>
                    <a href="#"><p class="titlesubs">제목</p></a>
                    <p> 내용
                    </p>
                    <input type="hidden" value="dd" name="totalOption">
                </div>
                <div>
                    <p>price 원</p>
                </div>
            </div>
            <c:set var="totalP" value="totalp" />
            </c:if> 


            <!-- 주문자 정보 -->
            <div class="orderer-info-title">
                <p class="listTitle">주문자 정보</p>
            </div>

            <hr>
            <div class="orderer-info">
            
            
            <!-- 이부분은 원데이 클래스를 제외한 두 스토어에서 사용되는 항목입니다.
            	배송지 정보 입력하는 부분입니다.
             -->
            <c:if test="${ oneClass.productCd ne 3 }">
                <div>
                    <input type="radio" name="orderer-addr" class="selectInfo" id="member-addr" value="1" checked> 
                    <label for="member-addr">회원정보와 동일</label>
                    <input type="radio" name="orderer-addr" class="selectInfo" value="2" id="new-addr"> 
                    <label for="new-addr">새로운 배송지</label>
                </div>
			</c:if>
            <br>
            	
            	
            	
                
                <!-- 원데이 클래스 회원 정보 -->
                <c:if test="${  oneClass.productCd eq 3 }">
                	<div class="loginForm">
            	<div class="input-info-div">
                      <div class="p-div"><p>참여자</p></div>
                      <div class="span-div"><span>${loginMember.memberName}</span></div>
                </div>
                <div class="input-info-div">
                        <div class="p-div"><p>휴대폰번호</p></div>
                 		<div class="span-div"><span>${loginMember.memberPhone}</span></div>
               	</div>
                
                <div class="input-info-div">
                      <div class="p-div"><p>이메일</p></div>
                      <div class="span-div"><span>${loginMember.memberEmail}</span></div>
                </div>
                </div>
                <div class="space-div"></div>
                </c:if>
                 
                
                <c:if test="${  oneClass.productCd ne 3 }">
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
                        <div class="p-div"><p>배송메세지</p></div>
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

                    <label class="container">기본배송지로 저장
                        <input type="checkbox">
                        <span class="checkmark"></span>
                    </label>
                    
                    
                    </c:if>
                </div>




			<!--  해당 부분은 사용되지 않습니다!!  -->
            <!--  정기 배송 정보 
             <div class="orderer-info-title">
                 <p class="listTitle">정기 배송 시작일</p>
            </div>

            <hr>
            <div class="orderer-info">
                <div class="input-info-div">
                    <div class="p-div"><p>배송시작일<span> *</span></p></div>
                    <div class="input-div"><button type="button" class="none-border-btn" id="calendar-btn"></button></div>
                    <p class="delivery-info">*화, 수, 목, 금 중 선택(토, 일, 월 선택시 화요일로 일괄배송)<br>
                        (결제일 : 주기별 배송 시작일의 1일전)</p>
                
                </div>
            </div> -->
 
			
              <!-- 할인/부가결제 -->
            <!-- <div class="orderer-info-title">
                <p class="listTitle">할인/부가결제</p>
            </div>


            <hr>

            <div class="price-container">
                <div class="discount-price">
                    <div class="p-div"><p>쿠폰</p></div>
                    <div class="price-won">
                        <select class="receiver-info discount-select">
                            <option>사용가능한 쿠폰</option>
                            <option>5000원 할인</option>
                            <option>1000원 할인</option>
                        </select>
                    </div>
                </div>
                <div class="discount-price">
                    <div class="p-div"><p>마일리지</p></div>
                    <div class="price-won">
                        <input type="text" class="receiver-info discount">
                        <p class="mileage">사용가능 마일리지 : 1000점</p>
                    </div>
                </div>
            </div> -->
            
            
            
            
              <!-- 최종 결제 금액 을 표시하는 부분 -->
				
			<!-- 
            <div class="orderer-info-title">
                <p class="listTitle">최종 결제 금액</p>
            </div>

            <hr>

            <div class="price-container">
                 <div class="price-flex">
                    <div class="p-div"><p>총 상품 금액</p></div>
                    <div class="price-won"><p>13,500원</p></div>
                </div>
               <div class="price-flex">
                    <div class="p-div"><p>배송비</p></div>
                    <div class="price-won"><p>3,000원</p></div>
                </div>
                <div class="price-flex">
                    <div class="p-div"><p>쿠폰할인</p></div>
                    <div class="price-won"><p>-0원</p></div>
                </div>
                <div class="price-flex">
                    <div class="p-div"><p>마일리지</p></div>
                    <div class="price-won"><p>-0원</p></div>
                </div> 
            </div>-->

            <hr class="price-hr">

            <div class="price-container">
                <div class="price-flex">
                    <div class="p-div"><p>총 결제 금액</p></div>
                    <div class="price-won"><p>${totalP }원</p></div>
                </div>
           </div>



		<!-- 해당 부분은 사용되지 않습니다!  -->
        <!--  <div class="orderer-info-title">
            <p class="listTitle">결제 수단</p>
        </div>

        <hr>
        <div class="give-flex">
            <div class="payment-select-btn">
                <button type="button" class="none-border-btn">신용카드</button>
            </div>
        </div> -->
        
        
        
        
        
        <div class="give-flex">
            <div class="payment-select-btn">
            	<c:if test="${productcate != 2}">
                <button type="button" id="payment-btn">결제하기</button>
                </c:if>
            	<c:if test="${productcate == 2}">
                <button type="button" id="kakaoPay">정기결제하기</button>
                </c:if>
            </div>
        </div>

    </section>  

    </main>
    
    


	<jsp:include page="../common/footer.jsp" />


  
  <script>
  	const productCd = "${productcate}"
  	const productNo = "${productNo}"
  	const loginMember = "${loginMember.memberNo}"
  	const memberId = "${loginMember.memberEmail}"
  	const amount ="${oneSubsOrder.totalAmount}";
  	const totalprice = "${totalP}"
  </script>
	
	<!-- 다음 지도 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
  	<!-- iamport.payment.js -->
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<script src="${contextPath}/resources/js/payment/payment.js"></script>
  <script>
  if(document.querySelector("#payment-btn") != null){
	  document.querySelector("#payment-btn").addEventListener("click", function(){
		  $.ajax({
			  url : "possibleCheck" ,
		        type : "post" ,
		        data : {
		        	"productNo" : productNo,
		        },
		        dataType : "json",
		        success : function(data){
		        	const submit = document.querySelector(".totalPeople").innerText;
		        	if(submit<data){
		                requestPay();
		        	}
		        	else{
		        		alert("가능인원 초과!")
		        		return;
		        	}
		        },
		        error: function(){
		            alert("오류가 발생했습니다.");
		            return;
		        }
		  })	  	
	    
	  });		
  }				
  
  function peoplecheck(productNo){

  }
  
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
	  
	  name: '${oneClass.title}',
	  
	  //결제창에서 보여질 이름
	  
	  amount: totalprice,
	  
	  //가격
	  buyer_email: '${loginMember.memberEmail}',
	  buyer_name: '${loginMember.memberName}',
	  buyer_tel: '${loginMember.memberPhone}',
	  buyer_addr: '서울특별시 중구 남대문로 120 대일빌딩 2F, 3F',
	  buyer_postcode: '04540', 
	  m_redirect_url: '${contextPath}/payments/complete'
	  
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
	  		saveOrderInfo(rsp.imp_uid,rsp.merchant_uid,rsp.paid_amount);
	  		var msg = '결제가 완료되었습니다.';
		  		msg += '고유ID : ' + rsp.imp_uid;
		  		msg += '상점 거래ID : ' + rsp.merchant_uid; // !!! merchant_uid 결제 ID !!!
		  		msg += '결제 금액 : ' + rsp.paid_amount;
		  		msg += '카드 승인번호 : ' + rsp.apply_num;
		  } 
	  	else {
		  	var msg = '결제에 실패하였습니다.';
		  		msg += '에러내용 : ' + rsp.error_msg;
			  	alert(msg);
		  }
	  });
	  }
  
  </script>
  <script type="text/javascript" src="${contextPath}/resources/js/payment/paymentDbFunction.js"></script>

  