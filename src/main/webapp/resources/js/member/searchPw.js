
const searchPwResult = {
	"email" : false,
    "checkEmail" : false,
    "checkAuthentication" : false
}

function validate(){

	for (key in searchPwResult) {
		if (!searchPwResult[key]) {

			let message;

			switch (key) {
				case "email": message = "이메일이 유효하지 않습니다."; break;
				case "checkEmail": message = "인증번호가 유효하지 않습니다."; break;
				case "checkAuthentication": message = "인증번호를 확인해주세요."; break;
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

    // input태그 생성
    // email 진행
	const input1 = document.createElement("input");
    input1.setAttribute("type", "hidden");
    input1.setAttribute("name", "memberEmail");
    input1.value = email[0].value + "@" + email[1].value;
    document.searchPwForm.append(input1);
	
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
$(".member-info").on("change paste", function(){
	const inputEmail = document.getElementById("email-input").value;
    const selectEmail = document.getElementById("email-input-select").value;
    const memberEmail = inputEmail + '@' + selectEmail;
    
    // 중간에 값이 바뀔 시 
    stopTimer();
    document.querySelector('#count-down-timer').innerText = "";
    
    if( inputEmail.length == 0 || selectEmail.length == 0){ // 둘 중 하나라도 입력되지 않은경우
		searchPwResult.email = false;
		checkEmail.innerText = "이메일을 입력해주세요";
		checkEmail.style.color = "#F99C9C";
    	$("#sendEmail").attr("disabled", true); //비활성화
    	document.querySelector("#email-checkNum").style.display = "none";
	} else {
		checkEmail.innerText = "";
		$("#sendEmail").attr("disabled", false); //활성화
	}

});

// 이메일 인증 버튼 인증번호 input 태그 보여줌 
document.querySelector("#sendEmail").addEventListener("click", function() {

	let inputEmail = document.getElementById("email-input").value;
	let selectEmail = document.getElementById("email-input-select").value;
	let checkEmail = document.getElementById("checkEmail");
	let memberEmail = inputEmail + '@' + selectEmail;
	
	
	if(inputEmail.length == 0 || selectEmail.length == 0){ 
		searchPwResult.email = false;
		checkEmail.innerText = "이메일을 입력해주세요";
		checkEmail.style.color = "#F99C9C";
    	$("#sendEmail").attr("disabled", true); //비활성화	
	} 
	else {	
		
		// 값이 있을 경우
		// 유효성 검사 통과 시 이메일 인증 시간제한 시작
		startTimer();
		
		$("#sendEmail").attr("disabled", false); //활성화
		checkEmail.innerText = "";
		document.querySelector("#email-checkNum").style.display = "block";
		searchPwResult.email = true;

		$.ajax({
			type: "POST",
			url: "email",
			data: { "memberEmail": memberEmail },
			success: function(data) {
			}, error: function(request, status, error) {
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
    let checkAuthentication = document.getElementById("checkAuthentication");

	if(inputCode.length == 0){ // 입력된 값 없이 확인 버튼 눌렀을 경우
		checkAuthentication.innerText = "인증번호를 입력해주세요";
		searchPwResult.checkAuthentication = false;
	} else{
	    $.ajax({
			type:"POST",
			url :"certification",
			data :{"memberEmail":memberEmail, "inputCode":inputCode},
			success : function(result){
				if(result == true){
					// 인증번호 맞을 경우 타이머 멈추기
					stopTimer();
					document.querySelector('#count-down-timer').style.display ="none"; 
					$("#email-Authentication").attr("disabled", true); // 입력창 비활성화
					$("#email-Authentication").css("background-color", "initial");
					document.getElementById("certificationYN").value = "true";
					searchPwResult.checkEmail= true;
					searchPwResult.checkAuthentication = true;
					
					memberEmail.onchange = function(){
						document.getElementById("certificationYN").value = "false";
						searchPwResult.checkEmail = true;
						searchPwResult.checkAuthentication = true;
					}
				} else{
					$("#email-Authentication").css("background-color", "#F99C9C");
					searchPwResult.checkEmail = false;
				}
			}, error : function(request, status, error){
				alert("인증 확인 중 오류입니다.");
			}
		});
	}
	
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
				checkAuthentication.innerText = "인증번호를 입력해주세요";
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
