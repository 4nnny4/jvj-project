const signUpCheckObj = {
    "email" : false,
    "pwd1" : false,
    "pwd2" : false,
    "nickname" : false,
    "name" : false,
    "phone3" : false,
    "checkEmail" : false,
    "dupEmail" : false
}

 // 회원 가입 버튼 클릭 시 유효성검사 판단 
function validate(){
	
	for( key  in signUpCheckObj ){

        if( !signUpCheckObj[key] ){
            
            let message;

            switch(key){
            case "email" : message = "이메일이 유효하지 않습니다."; break;
            case "name" : message = "이름이 유효하지 않습니다."; break;
            case "nickname" : message = "닉네임이 유효하지 않습니다."; break;
            case "pwd1" : message = "비밀번호가 유효하지 않습니다."; break;
            case "pwd2" : message = "비밀번호가 일치하지 않습니다."; break;
            case "phone3" : message = "전화번호가 유효하지 않습니다."; break;
            case "checkEmail" : message = "인증번호가 유효하지 않습니다."; break;
            case "dupEmail" : message = "중복된 아이디입니다."; break;
           
            }

            alert(message);

            // 유효하지 않은 input 요소로 포커스 이동
           	document.getElementById(key).focus(); 

            // form태그 submit 기본 이벤트 제거
            return false;

        }
	}
	
	// input type="hidden" 태그 생성 및 추가 
    const email = document.getElementsByName("email");
    const phone = document.getElementsByName("phone");
    const address = document.getElementsByName("address");

    // input태그 생성
    // email 진행
	const input1 = document.createElement("input");
    input1.setAttribute("type", "hidden");
    input1.setAttribute("name", "memberEmail");
    input1.value = email[0].value + "@" + email[1].value;
    document.signUpForm.append(input1);
	
	
    // phone 번호 있을때만 진행
	if(phone[0].value.trim().length > 0){
		const input2 = document.createElement("input");
	    input2.setAttribute("type", "hidden");
	    input2.setAttribute("name", "memberPhone");
	    input2.value = phone[0].value + "-" + phone[1].value + "-" + phone[2].value;
	    document.signUpForm.append(input2);
	}
	
    // 우편번호가 작성되어 있을 땜에만 주소 데이터 input 진행
    if(address[0].value.trim().length > 0){
        const input3 = document.createElement("input");
        input3.setAttribute("type", "hidden");    
        input3.setAttribute("name", "memberAddress");
        input3.value = address[0].value + ",," + address[1].value + ",," + address[2].value;
        document.signUpForm.append(input3);
    }   
     /**/
}
    

//이메일 입력방식 선택 
$('#email-select').change(function() {
	$("#email-select option:selected").each(function() {
		if ($(this).val() == '0') { //직접입력일 경우 
			$("#email-input-select").val(''); //값 초기화 
			$("#email-input-select").attr("disabled", false); //활성화 
		} else { //직접입력이 아닐경우 
			$("#email-input-select").val($(this).text()); //선택값 입력 
			$("#email-input-select").attr("disabled", true); //비활성화 
		}
	});
});

// 이메일 중복검사 
$(".email-input").on("change paste", function(){
    const inputEmail = document.getElementById("email-input").value;
    const selectEmail = document.getElementById("email-input-select").value;
    const memberEmail = inputEmail + '@' + selectEmail;
	
	if( inputEmail.length == 0 || selectEmail.length == 0){ // 둘 중 하나라도 입력되지 않은경우
		signUpCheckObj.dupEmail = false;
        dupEmail.innerText ="이메일을 입력해주세요.";
        $("#sendEmail").attr("disabled", true); //비활성화 
        
	} else{
		// 중복 검사 시 필수정보 입력 메시지 없애기
		checkEmail.innerText ="";
		
		
		$.ajax({
	        url: "emailDupCheck",
	        type: "post",
	        dataType: "json",
	        data: { "memberEmail": memberEmail },
	        success: function (result) {
	            if (result > 0) {
					
	                signUpCheckObj.dupEmail = false;
	                dupEmail.innerText ="중복된 이메일";
	                $("#sendEmail").attr("disabled", true); //비활성화 
	            } else {
	                signUpCheckObj.dupEmail = true;
	                dupEmail.innerText ="이메일 사용 가능";
	                $("#sendEmail").attr("disabled", false); //활성화 
	            }
	        }
		})
	}

    
});


// 이메일 인증 버튼 클릭 시  
// 인증번호 input 태그 보여줌 
document.querySelector("#sendEmail").addEventListener("click", function() {
	
	const inputEmail = document.getElementById("email-input").value;
    const selectEmail = document.getElementById("email-input-select").value;
	const checkEmail = document.getElementById("checkEmail");
	const memberEmail = inputEmail + '@' + selectEmail;
	
	const regExp1 = /^[\w]{4,20}$/;
	const regExp2 = /^[\w]+(\.[\w]+){1,3}$/;
	
	//초기화
	//checkEmail.innerText ="";
	
	if( inputEmail.length == 0  && selectEmail.length == 0){ // 둘 다 빈칸일 경우
		
    	document.querySelector("#email-checkNum").style.display = "none";
    	$("#sendEmail").attr("disabled", true); //비활성화
		
		checkEmail.innerText ="필수 정보입니다.";
        signUpCheckObj.email = false;
   	
    }else if(!regExp1.test(inputEmail) || !regExp2.test(selectEmail) ){ // 둘 중 하나라도 유효 X
    	document.querySelector("#email-checkNum").style.display = "none";
    	$("#sendEmail").attr("disabled", true); //비활성화
		checkEmail.innerText ="4~20자의 영문 소문자,숫자만 사용가능합니다.";
		checkEmail.style.color = "#F99C9C";
        signUpCheckObj.email = false;
    
    } else { 
		// 유효성 검사 통과 시 이메일 인증 시간제한 시작
		startTimer();
		
		$("#sendEmail").attr("disabled", false); //활성화
	    document.querySelector("#email-checkNum").style.display = "block";
        signUpCheckObj.email = true;
        
        
    
    	$.ajax({
			type:"POST",
			url :"email",
			data :{"memberEmail":memberEmail},
			success : function(data){


			}, error : function(request, status, error){
				alert("오류입니다.");
			}
		});
    }
    
    
    
    
});

// 인증번호 확인
document.querySelector("#check-email-Authentication").addEventListener("click", function() {

	let inputEmail = document.getElementById("email-input").value;
    let selectEmail = document.getElementById("email-input-select").value;
    
    let memberEmail = inputEmail + '@' + selectEmail;
    
    let inputCode= document.getElementById("email-Authentication").value;
    
    
    
    $.ajax({
		type:"POST",
		url :"certification",
		data :{"memberEmail":memberEmail, "inputCode":inputCode},
		success : function(result){
			console.log(result);
			if(result == true){
				
				
				// 인증번호 맞을 경우 타이머 멈추기
				stopTimer();
				document.querySelector('#count-down-timer').style.display ="none"; 
				
				$("#email-Authentication").attr("disabled", true); // 입력창 비활성화
				$("#email-Authentication").css("background-color", "white");
				
				document.getElementById("certificationYN").value = "true";
				
				signUpCheckObj.checkEmail= true;
				memberEmail.onchange = function(){
					document.getElementById("certificationYN").value = "false";
					signUpCheckObj.checkEmail = true;
				}
			} else{
				$("#email-Authentication").css("background-color", "#F99C9C");
				
				signUpCheckObj.checkEmail = false;
			}
			
		}, error : function(request, status, error){
			alert("인증 확인 중 오류입니다.");
		}
	
	
	
	});
    
    
});



// 이메일
let flag = true;


// 전역변수로 빼주고 clearInterval 수행해야함
let isStop = false;
let countInterval;

// 인증번호 카운트다운
function paddedFormat(num) {
	return num < 10 ? "0" + num : num;
}

function startCountDown(duration, element) {
	
	// 타이머 시작되면 활성화
	$("#email-Authentication").attr("disabled", false);
	$("#check-email-Authentication").attr("disabled", false);
	
	let secondsRemaining = duration;
	let min = 0;
	let sec = 0;

	countInterval = setInterval(function() {

		if (!isStop) { // isStop false

			min = parseInt(secondsRemaining / 60);
			sec = parseInt(secondsRemaining % 60);

			element.textContent = `${paddedFormat(min)}:${paddedFormat(sec)}`;

			secondsRemaining = secondsRemaining - 1;
			// 타이머가 만료되면
			if (secondsRemaining < 0) {

				clearInterval(countInterval);
				$("#email-Authentication").attr("disabled", true); // 입력창 비활성화
				$("#check-email-Authentication").attr("disabled", true); // 확인버튼 비활성화

			};

		} else { //
			
			clearInterval(countInterval);
		}

	}, 1000);
}

// 타이머 멈추기
function stopTimer() {
	isStop = true;
	clearInterval(countInterval);
}

// 타이머 시작하기
function startTimer() {
	let time_minutes = 3; // Value in minutes
	let time_seconds = 00; // Value in seconds

	let duration = time_minutes * 60 + time_seconds;

	element = document.querySelector('#count-down-timer'); 
	element.textContent = `${paddedFormat(time_minutes)}:${paddedFormat(time_seconds)}`;

	isStop = false;

	startCountDown(--duration, element);
	
	
};








// 비밀번호 유효성 검사
// - 영어 대/소문자, 숫자, 특수문자(!,@,#,-,_), 6~20글자
document.getElementById("pwd1").addEventListener("input", (e) => {

    const inputPw = e.target.value; 

    const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/; // 정규식

    const checkPwd1 = document.getElementById("checkPwd1"); // 출력용

    if(inputPw.length == 0){ // 빈칸
        checkPwd1.innerText = "";
        signUpCheckObj.pwd1 = false;
    
    }else if(regExp.test(inputPw)){ // 유효할 때
        checkPwd1.innerText = "유효한 비밀번호 입니다.";
        checkPwd1.style.color = "initial";
        signUpCheckObj.pwd1 = true;

    }else{
        checkPwd1.innerText = "유효하지 않은 비밀번호 입니다.";
        checkPwd1.style.color = "#F99C9C";
        signUpCheckObj.pwd1 = false;
    }



});


// 비밀번호 확인 유효성 검사  == > pwd1이랑 같은 값이면 유효
$("#pwd2, #pwd1").on("input", function(){

    const pwd1 = document.getElementById("pwd1").value;
    const pwd2 = document.getElementById("pwd2").value;
    const checkPwd2 = document.getElementById("checkPwd2"); // 출력용

    if( pwd2.trim().length == 0 ){ // 비밀번호 확인이 빈칸일 경우
        checkPwd2.innerText = "";
        signUpCheckObj.pwd2 = false;

    }else if(pwd1 == pwd2){ // 유효한 경우
        checkPwd2.innerText = "비밀번호가 일치합니다.";
        checkPwd2.style.color = "initial";
        signUpCheckObj.pwd2 = true;
        
    }else { // 유효하지 않은 경우
        checkPwd2.innerText = "비밀번호가 일치하지 않습니다.";
        checkPwd2.style.color = "#F99C9C";
        signUpCheckObj.pwd2 = false;
    }
});



// 닉네임 유효성 검사
// -  숫자, 영어, 한국어와 언더스코어, 공백을 허용하며 최소 2자 이상의 닉네임
$("#nickname").on("input", function(){

    const inputNickname = $(this).val(); // 입력 받은 이름
    const regExp = /^[가-힣ㄱ-ㅎa-zA-Z0-9._ -]{2,5}$/;

    if( inputNickname.length == 0 ){ // 빈칸
        $("#checkName").text("");

        signUpCheckObj.nickname = false;

    }else if(regExp.test(inputNickname)){ // 유효한 경우
        $("#checkNickname").text("유효한 이름 입니다.").css("color", "initial");
        
        signUpCheckObj.nickname = true;

    }else{ // 유효하지 않은 경우
        $("#checkNickname").text("유효하지 않은 이름 입니다.").css("color", "#F99C9C");

        signUpCheckObj.nickname = false;
    }
});


// 이름 유효성 검사
// - 한글(자음+모음[+받침]), 2~5 글자
$("#name").on("input", function(){

    const inputName = $(this).val(); // 입력 받은 이름
    const regExp = /^[가-힣]{2,5}$/;

    if( inputName.length == 0 ){ // 빈칸
        $("#checkName").text("");

        signUpCheckObj.name = false;

    }else if(regExp.test(inputName)){ // 유효한 경우
        $("#checkName").text("유효한 이름 입니다.").css("color", "initial");
        
        signUpCheckObj.name = true;

    }else{ // 유효하지 않은 경우
        $("#checkName").text("유효하지 않은 이름 입니다.").css("color", "#F99C9C");

        signUpCheckObj.name = false;
    }
});

// 전화번호 유효성 검사
// 전화번호 글자수 제한 + 유효성 검사
$(".phone").on("input", function(){
	
    	console.log($(this).val());
    if(  $(this).val().length > 4  ){
        const num = $(this).val().slice(0,4); // 4자리만 남음
	
        $(this).val(num);
    }

    const inputPhone2 = document.getElementById("phone2").value;
    const inputPhone3 = document.getElementById("phone3").value;

    const regExp2 = /^\d{3,4}$/;
    const regExp3 = /^\d{4}$/;

    const checkPhone = document.getElementById("checkPhone");

    if( inputPhone2.length == 0  && inputPhone3.length == 0){ // 둘다 빈칸일 경우
        checkPhone.innerText = "";
        signUpCheckObj.phone3 = false;

    }else if(regExp2.test(inputPhone2) && regExp3.test(inputPhone3) ){ // 둘다 유효

        checkPhone.innerText = "유효한 전화번호 입니다.";
        checkPhone.style.color = "initial";
        signUpCheckObj.phone3 = true;

    }else{ // 둘 중 하나라도 유효 X

        checkPhone.innerText = "유효하지 않은 전화번호 입니다.";
        checkPhone.style.color = "#F99C9C";
        signUpCheckObj.phone3 = false;

    }

});

  // 주소
document.querySelector("#searchAddr").addEventListener("click", function(){
	
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
 
 			document.querySelector("#addr1").value = data.zonecode;
 			document.querySelector("#addr2").value = data.address;
        }
    }).open();
});


