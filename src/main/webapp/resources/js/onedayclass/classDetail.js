
/* 사진교체 */
let likedone=0;
let nownum = 0;
window.onload = function(){
    changeImg();
    // reviewDetail();
    likecheck();
}
function likecheck(){
    if(loginNo=="") return;
    $.ajax({
        url: contextPath+'/onedayclass/likecheck',
        type: 'post',
        data : {
            "loginNo" : loginNo,
            "productNo" : productNo
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
    })
}
    function hearttoggle(){
        const hearts = $('.heart-btn');
        hearts.children().toggleClass("heart-active");
        hearts.children().next().toggleClass("heart-active");
        hearts.children().children().toggleClass("heart-active");
    }

    //좋아요 함수
    $('.heart-btn').click(function () {
        if(loginNo==""){
            alert("로그인 후 이용해주세요!");
            return;
        }
        if(likedone==1){
            if(confirm("좋아요를 취소하시겠어요?")){
                likeCancle();
            }
            else return;
        }
        else{
            doLike();
        }
    });
    function doLike(){
        $.ajax({
            url: contextPath+'/onedayclass/likeclass',
            method: 'post',
            data : {
                "loginNo" : loginNo,
                "productNo" : productNo
            },
            success: function(result){
                if(result>0){
                    hearttoggle()
                    likedone = 1;
                }
                else{
                    alert("이미 좋아요를 누르셨어요!");
                }
            },
            error : function(){

            }
        }) 
    }
    function likeCancle(){
        $.ajax({
            url: contextPath+'/onedayclass/undolike',
            method: 'post',
            data : {
                "loginNo" : loginNo,
                "productNo" : productNo
            },
            success: function(result){
                if(result>0){
                    hearttoggle()
                    likedone = 0;
                }
            },
            error : function(){
                alert("취소실패");
            }
        })
    }
    //사진 교체
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
    const possibleppl = document.querySelector(".product_detail >form> article:nth-child(11) > div.subdetail-content > span > span:nth-child(3)").innerText
    - document.querySelector(".product_detail > form>article:nth-child(11) > div.subdetail-content > span > span:nth-child(1)").innerText;
    const price = document.querySelector(".price").innerText.replace(",","").replace("원","");
    
    //인원 증감
    function plusppl(){
        if(possibleppl>nownum){
            let pplpick = document.getElementById("result");
            let totalprice= document.getElementById("totalprice");
            const checkppl = document.getElementById("ppl");
            pplpick.innerText = ++nownum;
            totalprice.innerText = (nownum*parseInt(price)).toLocaleString('ko-KR');
            checkppl.innerText = nownum;
        }
        else{
            alert("가능 인원을 초과했습니다!");
        }
    }
    function minusppl(){
        if(nownum!=0){
            let pplpick = document.getElementById("result");
            let totalprice= document.getElementById("totalprice");
            const checkppl = document.getElementById("ppl");
            pplpick.innerText = --nownum;
            totalprice.innerText = (nownum*parseInt(price)).toLocaleString('ko-KR');
            checkppl.innerText = nownum;
        }
    }



function showMaps(){
    var geocoder = new kakao.maps.services.Geocoder();
                
                // 주소로 좌표를 검색합니다
                geocoder.addressSearch(mapAddress, function(result, status) {
                
                // 정상적으로 검색이 완료됐으면 
                if (status === kakao.maps.services.Status.OK) {
                    document.getElementById("cal").style.display = "block";
                
                    var container = document.getElementById('map');
                    var options = { //지도를 생성할 때 필요한 기본 옵션
                        center: new kakao.maps.LatLng(result[0].x, result[0].y), //지도의 중심좌표.
                        level: 2 //지도의 레벨(확대, 축소 정도)
                    };
    
                    // 지도를 생성합니다    
                    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                    // 결과값으로 받은 위치를 마커로 표시합니다
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });
                    // 인포윈도우로 장소에 대한 설명을 표시합니다
                    var infowindow = new kakao.maps.InfoWindow({
                        content: '<div style="width:150px;text-align:center;padding:6px 0;">'+
                        'Jean Val Jean'+
                        '</div>'
                    });
                    infowindow.open(map, marker);

                    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    map.setCenter(coords);
                } 
            });  
}
function hideMaps(){
    document.getElementById("cal").style.display = "none";
    document.getElementById('map').innerHTML="";
}


  // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('551a4828db4bdc904ebb55328c53c9ae');

    // SDK 초기화 여부를 판단합니다.
    console.log(Kakao.isInitialized());
    function kakaoShare() {
        const productNo = document.getElementsByName("productNo")[0].value;
        const title = document.querySelector(".category-title>span").innerText;
        const url = location.href;
        const mainurl = document.querySelector(".main-thumbnail").getAttribute("src");
        console.log(contextPath);
        console.log(url);
        Kakao.Link.sendDefault({
            objectType: 'feed',
            content: {
            title: '장발장만의 특별한 원데이클래스를 함께 즐기세요!',
            description: title,
            imageUrl: mainurl,
            link: {
                mobileWebUrl: url,
                webUrl: url,
            },
            },
            buttons: [
            {
                title: '웹으로 보기',
                link: {
                mobileWebUrl: url,
                webUrl: url,
                },
            },
            ],
            // 카카오톡 미설치 시 카카오톡 설치 경로이동
            installTalk: true,
        })
    }



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
// function returnReviewContent(글번호){
//     /* ajax */
//     return ajax;
// }

/* 스크롤 - 페이지 내 이동 */
function scrollExp(){
    $("html,body").css('scroll-behavior','smooth');
	document.querySelector('#contents-exp').scrollIntoView();	
}
function scrollReview(){
    $("html,body").css('scroll-behavior','smooth');
	document.querySelector('#contents-review').scrollIntoView();
}
function scrollDelievery(){
    $("html,body").css('scroll-behavior','smooth');
	document.querySelector('#contents-delievery').scrollIntoView();
}


// 결제 페이지 이동
/* document.querySelector("#submit-btn").addEventListener("click", function(){
    location.href = contextPath + "/payment/payment";
}); */
// 결제 페이지 이동
function reconfirim(){
    if(loginNo==''){
        alert("로그인 후 가능합니다");
        return false;
    }
    if(nownum==0){
        alert("수강 인원을 선택해주세요");
        return false;
    }
    if(confirm("현재 정보로 클래스를 신청하시겠습니까?")){
        const totalprice= $("#totalprice").text();
        const totalPeople= $("#ppl").text();
        
        $("#hiddenTotalprice").val(totalprice);
        $("#hiddenTotalPeople").val(totalPeople);
        return true;
    }
    else{
        return false;
    }
}

$('.review-content').hide();
$('.review-title').on('click',(e)=>{
  
  const content = $(e.target).parent().next();
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