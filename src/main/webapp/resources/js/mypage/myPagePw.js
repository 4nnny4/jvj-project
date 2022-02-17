const modifyInfomationChk = {
    "pwInput" : true,
    "pwd1" : true,
    "pwd2" : true
}

// 비밀번호 유효성 검사
let formerPw = document.getElementById("nowPwd").value

document.getElementById("nowPwd").addEventListener("change",(e)=>{

    const checkPwd = document.getElementById("checkPwd");
    
    const inputPw = e.target.value;
    
    if(inputPw==0){

        checkPwd.innerText = "";
        modifyInfomationChk.pwInput = false;

    }else if(inputPw == formerPw){

        checkPwd.innerText = "비밀번호가 일치합니다";
        checkPwd.style.color = "#9CC7F9";
        modifyInfomationChk.pwInput = true;

    }else{

        checkPwd.innerText = "기존의 비밀번호와 불일치합니다";
        checkPwd.style.color = "#F99C9C";
        modifyInfomationChk.pwInput = false;
    }

});

$("#modifyPwd1").on("input", function(e){

    const inputPw = e.target.value;

    const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/; // 정규식

    const checkPwd1 = document.getElementById("checkPwd1"); // 출력용

    if(inputPw.length == 0){ // 빈칸
        checkPwd1.innerText = "";
        signUpCheckObj.pwd1 = false;
    
    }else if(regExp.test(inputPw)){ // 유효할 때
        checkPwd1.innerText = "유효한 비밀번호 입니다.";
        checkPwd1.style.color = "#9CC7F9";
        signUpCheckObj.pwd1 = true;

    }else{
        checkPwd1.innerText = "유효하지 않은 비밀번호 입니다.";
        checkPwd1.style.color = "#F99C9C";
        signUpCheckObj.pwd1 = false;
    }
});

$("#modifyPwd1, #modifyPwd2").on("input", function(){

    const pwd1 = document.getElementById("modifyPwd1").value;
    const pwd2 = document.getElementById("modifyPwd2").value;
    const checkPwd2 = document.getElementById("checkPwd2"); // 출력용

    if( pwd2.trim().length == 0 ){ // 비밀번호 확인이 빈칸일 경우
        checkPwd2.innerText = "";
        signUpCheckObj.pwd2 = false;

    }else if(pwd1 == pwd2){ // 유효한 경우
        checkPwd2.innerText = "비밀번호가 일치합니다.";
        checkPwd2.style.color = "#9CC7F9";
        signUpCheckObj.pwd2 = true;
        
    }else { // 유효하지 않은 경우
        checkPwd2.innerText = "비밀번호가 일치하지 않습니다.";
        checkPwd2.style.color = "#F99C9C";
        signUpCheckObj.pwd2 = false;
    }
});


function pwUpdateValidate(){

    for( key in modifyInfomationChk ){

        if(!modifyInfomationChk[key]){

            let message;

            switch(key){
            case "pwInput"  : message = "비밀번호가 일치하지 않습니다.";  break;
            case "pwd1"  : message = "입력한 비밀번호가 유효하지 않습니다.";  break;
            case "pwd2"  : message = "입력한 비밀번호가 일치하지 않습니다.";  break;
            }

            alert(message);

            // 유효하지 않은 input요소로 포커스 이동
            document.getElementById(key).focus(); 

            return false; // submit 이벤트 제거

        }
    }
}
