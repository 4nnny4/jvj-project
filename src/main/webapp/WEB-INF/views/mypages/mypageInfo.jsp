<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/myPage/myPageInfo.css/">
</head>
<body>
	<main>
    <div class="titleText">
    	<span>회원 정보 수정</span>
    </div>
    <section>
    
    

    <c:choose>
    	<c:when test="${loginMember.service eq null}">
	    	<div class="divNav">
		        <div id="hr"></div>
		        <ul>
		        	<li class="topText ">회원 관리</li>
		        	<li class="subText"><a href="main">마이 페이지</a></li>
		        	<li class="subText mbText"><a href="info">회원정보 수정</a></li>
		        	<li class="subText mbText"><a href="password">비밀번호 변경</a></li>
		        	<li class="subText mbText"><a href="coupon">쿠폰 정보</a></li>
		        </ul>
		        <ul>
		        	<li class="topText mTopText">쇼핑정보</li>
		        	<li class="subText"><a href="purchase">상품 주문 내역</a></li>
		            <li class="subText"><a href="sub">구독 신청 내역</a></li>
		            <li class="subText"><a href="class">수강 신청 내역</a></li>
		            <li class="subText"><a href="love">좋아요 내역</a></li>
		        </ul>
		    	</div> 
    	</c:when>
    	
    	<c:otherwise>
	    	<div class="divNav">
		        <div id="hr"></div>
		        <ul>
		        	<li class="topText ">회원 관리</li>
		        	<li class="subText"><a href="main">마이 페이지</a></li>
		        	<li class="subText mbText"><a href="info">회원정보 수정</a></li>
		        	<li class="subText mbText"><a href="coupon">쿠폰 정보</a></li>
		        </ul>
		        <ul>
		        	<li class="topText mTopText">쇼핑정보</li>
		        	<li class="subText"><a href="purchase">상품 주문 내역</a></li>
		            <li class="subText"><a href="sub">구독 신청 내역</a></li>
		            <li class="subText"><a href="class">수강 신청 내역</a></li>
		            <li class="subText"><a href="love">좋아요 내역</a></li>
		        </ul>
    		</div> 
    	</c:otherwise>
    </c:choose>	



    
            <article>
            <form method="POST" action="update" class="infoForm" role="form" name="updateForm" onsubmit="return memberUpdateValidate()">
            
            	<c:set var="addr" value="${fn:split(loginMember.memberAddress, ',,') }"/>
				
				<c:set var="ph" value="${fn:split(loginMember.memberPhone, '-') }"/>
                    
                     
                    <c:choose>
                    <%--카카오 API로 회원가입한 회원인 경우 --%>
            		<c:when test="${loginMember.service eq 'kakao'}">
                    <div>
		                    <label for="updateName" class="inputLabel" >이름</label>
                            <input type="text" class="inputStyle" name="memberName" id="inputName" value="${loginMember.memberName}" > 
                            <span id="checkNm"></span><br>
		                    
							<div class="userInfo">
							<p>이메일</p>  <p>${loginMember.memberEmail}</p>
							</div>
							
							<div class="userInfo">
							<p>닉네임</p>  <p>${loginMember.memberNickname}</p>
							</div>

                            <label for="phone" class="inputLabel">전화번호</label>
                            
                            <select class="phoneNum phoneOpt" id="phone1" name="phone">
                            	<option>010</option>
		                        <option>011</option>
		                        <option>016</option>
		                        <option>017</option>    
		                        <option>019</option>
                            </select>
                            
                            <input type="text" class="phone phoneNum"  id="phone2" name="phone" value="${ph[1]}" >
                            <input type="text" class="phone phoneNum" id="phone3" name="phone" value="${ph[2]}" > <br>

                        <div id="addInput">
                            <p>주소</p>
                            <input type="text" class="memberAdd addInput1" id="postcode" placeholder="우편번호" name="address" value="${addr[0]}" >
                            <input type="button" class="addBtn" value="우편번호" onclick="addressFind()" >
                            <br>
                            <input type="text" class="memberAdd addInput2 addIb" id="address" placeholder="기본 주소" name="address" value="${addr[1]}" >
                            <input type="text" class="memberAdd addInput3 addIb" id="detailAddress" placeholder="상세 주소" name="address" value="${addr[2]}"><br>

                            <div class="chkInput">
                            <c:if test="${!empty loginMember.memberAddress}">
                                <input type="checkbox" id="cbInput" checked>
                                <label for="cbInput"></label>
                                <div>기본 배송지로 저장</div>
                            </c:if>
                            </div>
                        </div>
                    </div>
                    </c:when>
                   
                    
                    <%-- 네이버 API로 회원가입한 회원인 경우 --%>
            		<c:when test="${loginMember.service eq 'naver'}">
                    <div>
		                    
							<div class="userInfo">
							<p>이름</p>  <p>${loginMember.memberName}</p>
							</div>
							
							<div class="userInfo">
							<p>이메일</p>  <p>${loginMember.memberEmail}</p>
							</div>
							
                            <div class="userInfo">
							<p>닉네임</p>  <p>${loginMember.memberNickname}</p>
							</div>


                            <label for="phone" class="inputLabel">전화번호</label>
                            
                            <select class="phoneNum phoneOpt" id="phoneNum1" name="phone">
                            	<option>010</option>
		                        <option>011</option>
		                        <option>016</option>
		                        <option>017</option>    
		                        <option>019</option>
                            </select>
                            
                            <input type="text" class="phone phoneNum"  id="phoneNum2" name="phone" value="${ph[1]}" >
                            <input type="text" class="phone phoneNum" id="phoneNum3" name="phone" value="${ph[2]}" > <br>

                        <div id="addInput">
                            <p>주소</p>
                            <input type="text" class="memberAdd addInput1" id="postcode" placeholder="우편번호" name="address" value="${addr[0]}">
                            <input type="button" class="addBtn" value="우편번호" onclick="addressFind()" >
                            <br>
                            <input type="text" class="memberAdd addInput2 addIb" id="address" placeholder="기본 주소" name="address" value="${addr[1]}" >
                            <input type="text" class="memberAdd addInput3 addIb" id="detailAddress" placeholder="상세 주소" name="address" value="${addr[2]}"><br>

                            <div class="chkInput">
                            <c:if test="${!empty loginMember.memberAddress}">
                                <input type="checkbox" id="cbInput" checked>
                                <label for="cbInput"></label>
                                <div>기본 배송지로 저장</div>
                            </c:if>
                            </div>
                        </div>
                    </div>
                    </c:when> 
                    
                    <%-- 일반경로로 회원가입한 회원인 경우 --%>
                    <c:otherwise>
                    <div>
		                    <div class="userInfo">
		                    <p>이름</p>  <p>${loginMember.memberName}</p>
		                    </div>
		                    
							<div class="userInfo">
							<p>이메일</p>  <p>${loginMember.memberEmail}</p>
							</div>
							
                            <label for="nickname" class="inputLabel" id="inputNickname">닉네임</label>
                            <input type="text" class="inputStyle" name="memberNickname" value="${loginMember.memberNickname}" > 
                            <span id="checkNnm"></span>
                            <br>


                            <label for="phone" class="inputLabel">전화번호</label>
                            
                            <select class="phoneNum phoneOpt" id="phoneNum1" name="phone">
                            	<option>010</option>
		                        <option>011</option>
		                        <option>016</option>
		                        <option>017</option>    
		                        <option>019</option>
                            </select>
                            
                            <input type="text" class="phone phoneNum"  id="phoneNum2" name="phone" value="${ph[1]}" >
                            <input type="text" class="phone phoneNum" id="phoneNum3" name="phone" value="${ph[2]}"> <br>

                        <div id="addInput">
                            <p>주소</p>
                            <input type="text" class="memberAdd addInput1" id="postcode" placeholder="우편번호" name="address" value="${addr[0]}" >
                            <input type="button" class="addBtn" value="우편번호" onclick="addressFind()" >
                            <br>
                            <input type="text" class="memberAdd addInput2 addIb" id="address" placeholder="기본 주소" name="address" value="${addr[1]}" >
                            <input type="text" class="memberAdd addInput3 addIb" id="detailAddress" placeholder="상세 주소" name="address" value="${addr[2]}"><br>

                            <div class="chkInput">
                            <c:if test="${!empty loginMember.memberAddress}">
                                <input type="checkbox" id="cbInput" checked>
                                <label for="cbInput"></label>
                                <div>기본 배송지로 저장</div>
                            </c:if>
                            </div>
                        </div>
                    </div>
                 </c:otherwise>
                 
                 
              </c:choose>  
             
              <div class="subBtn">
                    <button type="submit" id="infoUpdate" >회원 정보 수정</button><br>
                </div>
              </form>
              
              <div class="subBtn secessionDeleteBtn">
                    <c:choose>
                    	<c:when test="${!empty loginMember.service}">
		                    <button type="button" id="snsInfoDelete" onclick="location.href='secession'">계정 연동 해지하기</button>
                    	</c:when>
                    	<c:otherwise>
		                    <button type="button" id="infoDelete" onclick="location.href='secession'">탈퇴하기</button>
                    	</c:otherwise>
                    </c:choose>
                </div>
			         

        </article>
        
    </section>
    </main>
	<jsp:include page="../common/footer.jsp" />	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	
<c:choose>
	<c:when test="${!empty loginMember.service}">
	<c:if test="${loginMember.service eq 'naver'}">
		<script>
		const modifyInfomationChk = {
   			"phoneInput"  : true
		}


		let phoneNumber;
		
		// 전화번호 입력되는 글자 수 제한
		$(".phone").on("input", function(e){
		    
		    if(  $(this).val().length > 4  ){
		        const num = $(this).val().slice(0,4);
		        $(this).val(num);
		    }
		
		    // e 입력 방지
		    if(e.originalEvent.data == "e"){
		        $(this).val(phoneNumber);
		    }else{
		        phoneNumber = $(this).val();
		    }
		
		});
		
		// 전화번호가 변했을 경우 유효성 검사
		$(".phone").on("change", function(){
		
		    const phoneNum2 = document.getElementById("phone2").value;
		    const phoneNum3 = document.getElementById("phone3").value;
		
		    const regExp2 = /^\d{3,4}$/;
		    const regExp3 = /^\d{4}$/;
		
		    if( phoneNum2.length == 0  || phoneNum3.length == 0){ // 둘 중 하나라도 빈칸일 경우
		        modifyInfomationChk.phoneInput = false;
		
		    }else if(regExp2.test(phoneNum2) && regExp3.test(phoneNum3) ){ // 둘다 유효
		        modifyInfomationChk.phoneInput = true;
		
		    }else{ // 둘 중 하나라도 유효 X
		        alert("유효하지 않는 전화번호 입니다.");
		        modifyInfomationChk.phoneInput = false;
		        this.focus();
		    }
		
		});
		
		
		
		function memberUpdateValidate(){
		
		    for( key in modifyInfomationChk ){
		
		        if(!modifyInfomationChk[key]){
		
		            let message;
		
		            switch(key){
		            case "phoneInput" : message = "전화번호가 유효하지 않습니다.";   break;
		            }
		
		            alert(message);
		
		            // 유효하지 않은 input요소로 포커스 이동
		            document.getElementById(key).focus(); 
		
		            return false; // submit 이벤트 제거
		
		        }
		    }
		    
		    const phone = document.getElementsByName("phone");
		    const address = document.getElementsByName("address");
		
		    
		    const input1 = document.createElement("input");
		    input1.setAttribute("type", "hidden");
		    input1.setAttribute("name", "updatePhone");
		    input1.value = phone[0].value + "-" + phone[1].value + "-" + phone[2].value;
		    document.updateForm.append(input1);
		    
		
		
		    if(address[0].value.trim().length > 0){
		        const input2 = document.createElement("input");
		        input2.setAttribute("type", "hidden");
		        input2.setAttribute("name", "updateAddress");
		        input2.value = address[0].value + ",," + address[1].value + ",," + address[2].value;
		        document.updateForm.append(input2);
		    }
		}
		</script>
	
	</c:if>
	
	<c:if test="${loginMember.service eq 'kakao'}">
	
	<script>
		const modifyInfomationChk = {
		    "phoneInput" : true,
		    "nameInput" : true
		}
		
		
		let phoneNumber;
		
		// 전화번호 입력되는 글자 수 제한
		$(".phone").on("input", function(e){
		    
		    if(  $(this).val().length > 4  ){
		        const num = $(this).val().slice(0,4);
		        $(this).val(num);
		    }
		
		    // e 입력 방지
		    if(e.originalEvent.data == "e"){
		        $(this).val(phoneNumber);
		    }else{
		        phoneNumber = $(this).val();
		    }
		
		});
		
		// 전화번호가 변했을 경우 유효성 검사
		$(".phone").on("change", function(){
		
		    const phoneNum2 = document.getElementById("phoneNum2").value;
		    const phoneNum3 = document.getElementById("phoneNum3").value;
		
		    const regExp2 = /^\d{3,4}$/;
		    const regExp3 = /^\d{4}$/;
		
		    if( phoneNum2.length == 0  || phoneNum3.length == 0){ // 둘 중 하나라도 빈칸일 경우
		        modifyInfomationChk.phoneInput = false;
		
		    }else if(regExp2.test(phoneNum2) && regExp3.test(phoneNum3) ){ // 둘다 유효
		        modifyInfomationChk.phoneInput = true;
		
		    }else{ // 둘 중 하나라도 유효 X
		        alert("유효하지 않는 전화번호 입니다.");
		        modifyInfomationChk.phoneInput = false;
		        this.focus();
		    }
		
		});
		
		// 이름 유효성 검사
		const formerNm = document.getElementById("inputName").value;
		
		document.getElementById("inputName").addEventListener("change",(e)=>{
		    const checkNm = document.getElementById("checkNm"); // 출력용
		    const inputNameVal = e.target.value;
		    const regExp = /^[가-힣]{2,5}$/;
		
		    if( formerNm == inputNameVal){ // 기존 이름과 같을경우
		
		        checkNm.innerText = "유효한 이름 입니다.";
		        checkNm.style.color = "#9CC7F9";
		        modifyInfomationChk.nameInput = true;
		
		    }else if(regExp.test(inputNameVal)){ // 유효한 이름일 경우
		
		        checkNm.innerText = "유효한 이름 입니다.";
		        checkNm.style.color = "#9CC7F9";
		        modifyInfomationChk.nameInput = true;
		        modifyInfomationChk.nameInput = true;
		
		    }else if(inputNameVal == 0){ // 작성하지 않는 경우
		        modifyInfomationChk.nameInput = false;
		        alert("이름을 작성해주세요");
		        this.focus();
		        
		    }else{
		        modifyInfomationChk.nameInput = false;
		        checkNm.innerText = "유효하지 않는 이름 입니다.";
		        checkNm.style.color = "#F99C9C";
		        this.focus();
		    }
		});
		
		function memberUpdateValidate(){
		
		    for( key in modifyInfomationChk ){
		
		        if(!modifyInfomationChk[key]){
		
		            let message;
		
		            switch(key){
		            case "phoneInput" : message = "전화번호가 유효하지 않습니다.";   break;
		            case "nameInput" : message = "이름이 유효하지 않습니다.";   break;
		            }
		
		            alert(message);
		
		            // 유효하지 않은 input요소로 포커스 이동
		            document.getElementById(key).focus(); 
		
		            return false; // submit 이벤트 제거
		
		        }
		    }
		    
		    const phone = document.getElementsByName("phone");
		    const address = document.getElementsByName("address");
		
		    
		    const input1 = document.createElement("input");
		    input1.setAttribute("type", "hidden");
		    input1.setAttribute("name", "updatePhone");
		    input1.value = phone[0].value + "-" + phone[1].value + "-" + phone[2].value;
		    document.updateForm.append(input1);
		    
		
		
		    if(address[0].value.trim().length > 0){
		        const input2 = document.createElement("input");
		        input2.setAttribute("type", "hidden");
		        input2.setAttribute("name", "updateAddress");
		        input2.value = address[0].value + ",," + address[1].value + ",," + address[2].value;
		        document.updateForm.append(input2);
		    }
		}
	</script>
	</c:if>
	
	</c:when>
	
	<%-- 일반회원 --%>
	<c:otherwise>
		<script>
			const modifyInfomationChk = {
			    "phoneInput" : true,
			    "nicknameInput" : true
			}
			
			
			let phoneNumber;
			
			// 전화번호 입력되는 글자 수 제한
			$(".phone").on("input", function(e){
			    
			    if(  $(this).val().length > 4  ){
			        const num = $(this).val().slice(0,4);
			        $(this).val(num);
			    }
			
			    // e 입력 방지
			    if(e.originalEvent.data == "e"){
			        $(this).val(phoneNumber);
			    }else{
			        phoneNumber = $(this).val();
			    }
			
			});
			
			// 전화번호가 변했을 경우 유효성 검사
			$(".phone").on("change", function(){
			
			    const phoneNum2 = document.getElementById("phoneNum2").value;
			    const phoneNum3 = document.getElementById("phoneNum3").value;
			
			    const regExp2 = /^\d{3,4}$/;
			    const regExp3 = /^\d{4}$/;
			
			    if( phoneNum2.length == 0  || phoneNum3.length == 0){ // 둘 중 하나라도 빈칸일 경우
			        modifyInfomationChk.phoneInput = false;
			
			    }else if(regExp2.test(phoneNum2) && regExp3.test(phoneNum3) ){ // 둘다 유효
			        modifyInfomationChk.phoneInput = true;
			
			    }else{ // 둘 중 하나라도 유효 X
			        alert("유효하지 않는 전화번호 입니다.");
			        modifyInfomationChk.phoneInput = false;
			        this.focus();
			    }
			
			});
			
			// 닉네임 유효성 검사
			const formerNm = document.getElementById("inputNickname").value;
			
			document.getElementById("inputNickname").addEventListener("change",(e)=>{
			    const checkNnm = document.getElementById("checkNnm"); // 출력용
			    const inputNm = e.target.value;
			    const regExp = /^[가-힣ㄱ-ㅎa-zA-Z0-9._ -]{2,5}$/;
			
			    if( formerNm == inputNm){ // 기존 닉네임과 같을경우
			
			        checkNnm.innerText = "유효한 닉네임 입니다.";
			        checkNnm.style.color = "#9CC7F9";
			
			        modifyInfomationChk.nicknameInput = true;
			
			    }else if(regExp.test(inputNm)){ // 유효한 닉네임일 경우
			
			        checkNnm.innerText = "유효한 닉네임 입니다.";
			        checkNnm.style.color = "#9CC7F9";
			        modifyInfomationChk.nicknameInput = true;
			
			    }else if(inputNm == 0){ // 작성하지 않는 경우
			        modifyInfomationChk.nicknameInput = false;
			        alert("이름을 작성해주세요");
			        this.focus();
			        
			    }else{
			        modifyInfomationChk.nicknameInput = false;
			        checkNnm.innerText = "유효하지 않는 닉네임 입니다.";
			        checkNnm.style.color = "#F99C9C";
			
			        this.focus();
			    }
			});
			
			function memberUpdateValidate(){
			
			    for( key in modifyInfomationChk ){
			
			        if(!modifyInfomationChk[key]){
			
			            let message;
			
			            switch(key){
			            case "phoneInput" : message = "전화번호가 유효하지 않습니다.";   break;
			            case "nicknameInput" : message = "닉네임이 유효하지 않습니다.";   break;
			            }
			
			            alert(message);
			
			            // 유효하지 않은 input요소로 포커스 이동
			            document.getElementById(key).focus(); 
			
			            return false; // submit 이벤트 제거
			
			        }
			    }
			    
			    const phone = document.getElementsByName("phone");
			    const address = document.getElementsByName("address");
			
			    
			    const input1 = document.createElement("input");
			    input1.setAttribute("type", "hidden");
			    input1.setAttribute("name", "updatePhone");
			    input1.value = phone[0].value + "-" + phone[1].value + "-" + phone[2].value;
			    document.updateForm.append(input1);
			    
			
			
			    if(address[0].value.trim().length > 0){
			        const input2 = document.createElement("input");
			        input2.setAttribute("type", "hidden");
			        input2.setAttribute("name", "updateAddress");
			        input2.value = address[0].value + ",," + address[1].value + ",," + address[2].value;
			        document.updateForm.append(input2);
			    }
			}
		</script>
	</c:otherwise>
	
	</c:choose>
	
	
	<script>
			// 주소 API
			function addressFind() {
			    new daum.Postcode({
			        oncomplete: function(data) {
			            var addr = ''; // 주소 변수
			            var extraAddr = ''; // 참고항목 변수
		
			            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
			                addr = data.roadAddress;
			            } else { // 사용자가 지번 주소를 선택했을 경우(J)
			                addr = data.jibunAddress;
			            }
		
			            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			            if(data.userSelectedType === 'R'){
			                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
			                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
			                    extraAddr += data.bname;
			                }
			                // 건물명이 있고, 공동주택일 경우 추가한다.
			                if(data.buildingName !== '' && data.apartment === 'Y'){
			                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			                }
			                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			            
			            } else {
			                document.getElementById("detailAddress").value = '';
			            }
		
			            // 우편번호와 주소 정보를 해당 필드에 넣는다.
			            document.getElementById('postcode').value = data.zonecode;
			            document.getElementById("address").value = addr;
			            // 커서를 상세주소 필드로 이동한다.
			            document.getElementById("detailAddress").focus();
			        }
			    }).open();
			}
			
			
			
			
		</script>	


	
</body>
</html>