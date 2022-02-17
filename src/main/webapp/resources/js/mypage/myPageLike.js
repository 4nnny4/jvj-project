function cancelLike(productNo) {
	$.ajax({
		url: contextPath + "/mypage/deletelike",
		type: "POST",
		data: { "productNo": productNo },
		success: function(result) {
			console.log(result);
			swal({"icon" : "info" , "title" : "삭제되었습니다."})
				.then(function(){
					location.reload();
				})
		},
		error: function(error) {
			console.log(error);
		},
	});
}

document.getElementById("").addEventListener("click", function(){

	
});
