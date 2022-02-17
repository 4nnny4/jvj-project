//이메일 입력방식 선택 
    $('#email-select').change(function(){ 
        $("#email-select option:selected").each(function () { 
            if($(this).val() == '0'){ //직접입력일 경우 
                $("#email-input-select").val(''); //값 초기화 
                $("#email-input-select").attr("disabled",false); //활성화 
            }else{ //직접입력이 아닐경우 
                $("#email-input-select").val($(this).text()); //선택값 입력 
                $("#email-input-select").attr("disabled",true); //비활성화 
            } 
        }); 
    }); 

    $('#delivery-message-select').change(function(){ 
        $("#delivery-message-select option:selected").each(function () { 
            if($(this).val() == '0'){ //직접입력일 경우 
                $("#delivery-message").val(''); //값 초기화 
                $("#delivery-message").show(); //활성화 
            }else{ //직접입력이 아닐경우 
                $("#delivery-message").val($(this).text()); //선택값 입력 
                $("#delivery-message").hide(); //비활성화 
            } 
        }); 
    }); 


    /* 정기 배송 현재 날짜 가져오기 */

    // let today = new Date();

    // let year = today.getFullYear();
    // let month = ('0' + (today.getMonth() + 1)).slice(-2);
    // let day = ('0' + today.getDate()).slice(-2);

    // let dateString = year + '-' + month  + '-' + day;
    // document.querySelector("#calendar-btn").innerHTML = dateString;
    
 	// 주소
    if(productCd!='3'){
        
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
}
    
    







$(".selectInfo").change(function() {

	console.log($(this).val());
	if($(this).val() == 1){
		$(".loginForm").css("display", "block");
		$(".newInfo").css("display", "none");
	} else if($(this).val() == 2){
		$(".loginForm").css("display", "none");
		$(".newInfo").css("display", "block");
	}
});