<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/header.jsp" />

    <link rel="stylesheet" href="${contextPath}/resources/css/member/searchPwResult.css">

    <main>

        <section class="title-section">
            <p>비밀번호 변경</p>
        </section>


        <div class="member-form-info">
        
        <form action="${contextPath}/member/searchPwResult" method="POST">
        <div class="input-info">
        
	        <div class="input-info-div">
	            <div class="p-div"><p>비밀번호</p></div>
	            <div class="input-div">
	            	<input type="password" class="member-info"  id="newPw1" name="newPw1" maxlength="12" placeholder="문자, 숫자, 기호 6~20글자">
	            </div>
	        </div>
	        <!-- 비밀번호 유효성 검사1 -->
			<p class="check-p" id="checkPw1"></p>
							
	       	<div class="input-info-div">
	            <div class="p-div"><p>비밀번호 확인</p></div>
	            <div class="input-div">
	            	<input type="password" class="member-info" id="newPw2" name="newPw2" maxlength="12" placeholder="비밀번호 확인" required>
	            </div>
        	</div>
			<!-- 비밀번호 유효성 검사2 -->
			<p class="check-p" id="checkPw2"></p>
			
        </div>
		
		
            <div class="flex-div">
                <div class="signUp-select-btn">
                    <button type="submit" id="search-btn"  onclick="return updatePwValidate();">확인</button>
                </div>
            </div>
            
            <!-- member값 넘기기용  -->
            <input type="hidden" name="memberEmail" value="${memberEmail}">

        </form>
        </div>

    </main>
    


<jsp:include page="../common/footer.jsp" />

<script>

//////////////////////

//새 비밀번호 유효성 검사
//- 영어 대/소문자, 숫자, 특수문자(!,@,#,-,_), 6~20글자
const updatePwResult = {
	newPw1 : false,
	newPw2 : false
}

function updatePwValidate() {

	for( key  in updatePwResult ){
	
		if( !updatePwResult[key] ){
			document.getElementById(key).style.backgroundcolor = "#F99C9C";
			// 유효하지 않은 input 요소로 포커스 이동
			document.getElementById(key).focus(); 
			
			return false;
		
		}
	}
		 
}


document.getElementById("newPw1").addEventListener("input", function(){
	
	 const inputPw = document.getElementById("newPw1").value; 
	
	 const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/; // 정규식
	
	 const checkPw1 = document.getElementById("checkPw1"); // 출력용
	
	 if( inputPw.trim().length == 0 ){ // 비밀번호 확인이 빈칸일 경우
		 checkPw1.innerText = "";
		 updatePwResult.newPw1 = false;
	
	 }else if(regExp.test(inputPw)){ // 유효할 때
		 checkPw1.innerText = "유효한 비밀번호 입니다.";
		 checkPw1.style.color = "#9CC7F9";
		 updatePwResult.newPw1 = true;
	
	 }else{
		 checkPw1.innerText = "유효하지 않은 비밀번호 입니다.";
		 checkPw1.style.color = "#F99C9C";
		 updatePwResult.newPw1 = false;
	 }
});


//비밀번호 확인 유효성 검사  == > pw1이랑 같은 값이면 유효
$("#newPw2, #newPw1").on("input", function(){
	
	 const newPw1 = document.getElementById("newPw1").value;
	 const newPw2 = document.getElementById("newPw2").value;
	 const checkPw2 = document.getElementById("checkPw2"); // 출력용
	
	 if( newPw2.trim().length == 0 ){ // 비밀번호 확인이 빈칸일 경우
		 checkPw2.innerText = "";
		 updatePwResult.newPw2 = false;
	
	 }else if(newPw1 == newPw2){ // 유효한 경우
		 checkPw2.innerText = "비밀번호가 일치합니다.";
		 checkPw2.style.color = "#9CC7F9";
		 updatePwResult.newPw2 = true;
	
	 }else { // 유효하지 않은 경우
	 	checkPw2.innerText = "비밀번호가 일치하지 않습니다.";
		 checkPw2.style.color = "#F99C9C";
		 updatePwResult.newPw2 = false;
	 }
});
	 
</script>
<script src="${contextPath}/resources/js/member/searchPw.js"></script>
    