
const searchIdResult = {
	"name" : false,
    "phone3" : false
}

function validate(){

	for (key in searchIdResult) {

		if (!searchIdResult[key]) {

			let message;

			switch (key) {
				case "name": message = "이름이 유효하지 않습니다."; break;
				case "phone3": message = "전화번호가 유효하지 않습니다."; break;
			}

			alert(message);

			// 유효하지 않은 input 요소로 포커스 이동
			document.getElementById(key).focus();

			// form태그 submit 기본 이벤트 제거
			return false;

		}
	}
	const phone = document.getElementsByName("phone");
	// phone 번호 있을때만 진행
	if(phone[0].value.trim().length > 0){
		const input2 = document.createElement("input");
	    input2.setAttribute("type", "hidden");
	    input2.setAttribute("name", "memberPhone");
	    input2.value = phone[0].value + "-" + phone[1].value + "-" + phone[2].value;
	    document.signUpForm.append(input2);
	}
}


// 이름 
$("#name").on("input", function(){

    const inputName = $(this).val(); // 입력 받은 이름

    if( inputName.length == 0 ){ // 빈칸
        searchIdResult.name = false;

    }else{ 
        searchIdResult.name = true;
    }
});


// 전화번호 글자수 제한 + 유효성 검사
$(".phone").on("input", function(){
	
    if(  $(this).val().length > 4  ){
        const num = $(this).val().slice(0,4); // 4자리만 남음
        $(this).val(num);
    }

    const inputPhone2 = document.getElementById("phone2").value;
    const inputPhone3 = document.getElementById("phone3").value;

    if( inputPhone2.length == 0  && inputPhone3.length == 0){ // 둘다 빈칸일 경우
        searchIdResult.phone3 = false;

    }else{ 
		searchIdResult.phone3 = true;
    }

});