//const contextPath = getContextPath();

let likedone=0;
/* 사진교체 */
window.onload = function(){
    changeImg();
    // reviewDetail();
    likecheck();
    chooseBtn();
}



function likecheck(){
    if(loginMember=="") return;
    $.ajax({
        url: 'likecheck',
        type: 'post',
        data : {
            "loginMember" : loginMember,
            "productNo" : productNo.value
        },
        success: function(result){
            console.log(result);
            if(result>0){
                hearttoggle()
                likedone = 1;
            }
        },
        error : function(){

        }
    });
}

function hearttoggle(){
    const hearts = $('.heart-btn');
    hearts.children().toggleClass("heart-active");
    hearts.children().next().toggleClass("heart-active");
    hearts.children().children().toggleClass("heart-active");
}


//좋아요 함수
$('.heart-btn').click(function() {
	if (loginMember == "") {
		alert("로그인 후 이용해주세요!");
		return;
	}
	if (likedone == 1) {
		if (confirm("좋아요를 취소하시겠어요?")) {
			likeCancel();
		}
		else return;
	}
	else {
		doLike();
	}
});

function doLike(){
	$.ajax({
		url: 'likeSub',
		type: 'post',
		data: {
			"loginMember": loginMember,
			"productNo": productNo.value
		},
		success: function(result) {
			if (result > 0) {
				hearttoggle()
				likedone = 1;
			}
			else {
				// alert("이미 좋아요를 누르셨어요!");
			}
		},
		error: function() {
            
		}
	})
}

function likeCancel() {
	$.ajax({
		url: 'undolike',
		type: 'post',
		data: {
			"loginMember": loginMember,
			"productNo": productNo.value
		},
		success: function(result) {
			if (result > 0) {
				hearttoggle()
				likedone = 0;
			}
		},
		error: function() {
			alert("취소실패");
		}
	})
}

// 사진 교체
const tempThumb = document.querySelector(".main-thumbnail").getAttribute("src");
function changeImg(){
    const subImgs = document.querySelectorAll(".img-margin");
    const thumb = document.querySelector(".main-thumbnail");
    for(let i = 0; i< subImgs.length ; i++){
        subImgs[i].addEventListener("click", function(){
            thumb.setAttribute("src",subImgs[i].getAttribute("src"))
        })
    }
}






// 제출 시 유효성 검사
function validate(){
    
   if($(".bread-btn.active").length == 0) { // 공개여부의 값이 없다면
		alert("구매 수량을 선택해주세요");
        return false;
	}
	
	// 로그인인 경우
	
    document.subBreadForm.submit();
}




/*----------------------- 버튼 이름 받아오기,input hidden 값 넘기기 ------------------------*/
/*// 빵
$(".bread-btn").on("click", function() {
    $(this).addClass('active').siblings().removeClass('active');
    
    document.getElementById("bread").innerText 
    	= $(".bread-btn.active").find('span').text()+ ' / ';
    	
    $("input[name='chooseBreadCode']").attr('value', $(this).val());
});*/

/*// 맛
$(".taste-btn").on("click", function() {
    $(this).addClass('active').siblings().removeClass('active');
    
    document.getElementById("taste").innerText 
    	= $(".taste-btn.active").find('span').text()+ ' / ';
    	
     $("input[name='chooseTasteCode']").attr('value', $(this).val());
});
*/


// 구독 기간 (1주 2주)
$(".period-btn").on("click", function() {
    $(this).addClass('active').siblings().removeClass('active');
    
    document.getElementById("period").innerText
        = $(".period-btn.active").find('span').text() + ' / ';
        
	$("input[name='choosePeriodCode']").attr('value', $(this).val());
});

// 수령 희망일 
$(".deliveryDay-btn").on("click", function() {
    $(this).addClass('active').siblings().removeClass('active');
    
    document.getElementById("deliveryDay").innerText 
    	= $(".deliveryDay-btn.active").find('span').text();
    	
    $("input[name='chooseDeliveryDayCode']").attr('value', $(this).val());
});





/*----------------------- 수량 증감 버튼 -----------------------*/
/* 수량 / 기본 금액 */
let resultNum = document.getElementById('result').innerText;
const price = document.querySelector(".price").innerText.replace(",","").replace("원","");

	let resultCount = document.getElementById("result");
    let totalprice= document.getElementById("totalprice");

/* 수량 증가 */
function plusCount(){
    
    resultCount.innerText = ++resultNum;
    /* 원화 단위 콤마포함한 값 대입*/					 
    totalprice.innerText = (resultNum*parseInt(price)).toLocaleString('ko-KR');
}

/* 수량 감소 */
function minusCount(){
	if(resultNum!=0){ // 0이 아니라면
		/*let resultCount = document.getElementById("result");
	    let totalprice= document.getElementById("totalprice");*/
	    
	    resultCount.innerText = --resultNum;				 
	    totalprice.innerText = (resultNum*parseInt(price)).toLocaleString('ko-KR');
    }
    else if(resultNum == 0){
		resultCount.inner
	}
    
}





// /* 리뷰 */


// /* 상세리뷰 토글check */
// let reviewtoggle = false;
// let temp = -1;
// /* 댓글보기 func */
// function reviewDetail(){
//     const reviewLines = document.querySelectorAll(".one-line-review>span:first-of-type");
//     for(let i =0 ; i<reviewLines.length ; i++){
//         reviewLines[i].addEventListener("click", function(){
//             const box = $(this).parent();
//             if(!reviewtoggle){
//                 const reviewDetail = document.createElement("div");
//                 reviewDetail.className = "reviewBox";
//                 reviewDetail.innerHTML = "<h1>리뷰열림</h1>";
//                 /* reviewDetail.innerHTML = returnReviewContent(글번호); */
//                 box.after(reviewDetail);
//                 temp = i;
//                 reviewtoggle = true;
//             }
//             else{
//                 if(temp == i){
//                     temp = -1
//                     reviewtoggle = false;
//                     $(".reviewBox").remove();
//                 }
//                 else{
//                     $(".reviewBox").remove();
//                     const reviewDetail = document.createElement("div");
//                     reviewDetail.className = "reviewBox";
//                     reviewDetail.innerHTML = "<h1>리뷰열림</h1>";
//                     /* reviewDetail.innerHTML = returnReviewContent(글번호); */
//                     temp= i;
//                     box.after(reviewDetail);
//                 }
//             }
//         })
//     }
// }

/* review  Contents ajax 
    리뷰 list 시 constent span 에  value=글번호 넣어놓기
    or display none으로 글번호 작성
*/
/*function returnReviewContent(글번호){
    // ajax 
    return ajax;
}
*/



/*
// 결제 페이지 이동
function reconfirim(){
	if(loginMember==''){
        alert("로그인 후 가능합니다");
        return false;
    }
	
	if (!$(".bread-btn.active")[0]) {
		alert("빵 종류를 선택해주세요.")
		return false;
	}
	
	if (!$(".taste-btn.active")[0]) {
		alert("맛 종류를 선택해주세요.")
		return false;
	}
	
	if (!$(".period-btn.active")[0]) {
		alert("구독 옵션을 선택해주세요.")
		return false;
	}
	if (!$(".deliveryDay-btn.active")[0]) {
		alert("수령 희망일을 선택해주세요.")
		return false;
	}
	
    if(resultNum == 0){
        alert("구매 수량을 선택해주세요");
        return false;
    }
    if(loginMember !='' && resultNum !=0 
    	&& $(".bread-btn.active")[0] && $(".taste-btn.active")[0]
    	&& $(".period-btn.active")[0] && $(".deliveryDay-btn.active")[0]
    	){
        $("#totalAmount").val(resultNum);
        $("#hiddenTotalPrice").val(totalprice.innerText);
        return true;
    } else{
        return false;
    }
 	
}
*/


/* 스크롤 - 페이지 내 이동 */
function scrollExp(){
    $("html,body").css('scroll-behavior','smooth');
	document.querySelector('#contents-exp').scrollIntoView();	
	
	
	/*
	
	var location = document.querySelector('#contents-exp').offsetTop;
	var menuHeight = document.querySelector(".detail-contents").offsetHeight;
	window.scrollTo({top:location - menuHeight, behavior:'smooth'});*/
}
function scrollReview(){
    $("html,body").css('scroll-behavior','smooth');
	document.querySelector('#contents-review').scrollIntoView();
	
}
function scrollDelievery(){
    $("html,body").css('scroll-behavior','smooth');
	document.querySelector('#contents-delievery').scrollIntoView();
}

$('.review-content').hide();
$('.review-title').on('click',(e)=>{
  
  const content = $(e.target).parent().next();
  console.log($(e.target));
  if( $(e.target).parent().next().css('display') != 'flex'){
    $('.review-content').hide(100);

   
  }
  content.slideToggle(500);
});

$('.review-content').on('click',(e)=>{

  const content =$('.review-title');

  content.click();
});
$(document).ready(function(){
  $("html,body").css('scroll-behavior','smooth');
  if(cp!='' || sr!=''){
    $("html,body").css('scroll-behavior','unset');
   
    var position = $("#contents-review").position();
    $("html, body").animate({scrollTop: position.top},0);
  }
});

$('#selectcate').on("change",(e)=>{
  console.log($(e.target).val());
  location.href = "?cp="+cp+"&sr="+$(e.target).val();
});