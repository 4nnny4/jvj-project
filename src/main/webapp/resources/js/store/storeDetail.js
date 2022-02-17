
  const resultPrice = $(".resultPrice");

let yn;
/* 사진교체 */
window.onload = function () {
  changeImg();
};
const suryang = parseInt($("#result").text());
const tempThumb = document.querySelector(".main-thumbnail").getAttribute("src");
function changeImg() {
  const subImgs = document.querySelectorAll(".img-margin");
  const thumb = document.querySelector(".main-thumbnail");
  for (let i = 0; i < subImgs.length; i++) {
    subImgs[i].addEventListener("click", function () {
      thumb.setAttribute("src", subImgs[i].getAttribute("src"));
    });
  }
}

// 조아요
$(document).ready(function () {
  $(".contentss").on("click", function () {
    if (loginMember != "") {
      // 하트있을때
      if ($(".contentss").hasClass("heart-active")) {
        $.ajax({
          url: contextPath + "/store/doesntLikeit",
          type: "POST",
          data: { productNo: storeNo },
          success: function (result) {
            console.log(result);
          },
          error: function (error) {
            console.log(error);
          },
        });
        $(".contentss").removeClass("heart-active");
        $(".heart").removeClass("heart-active");
      } else {
        $.ajax({
          url: contextPath + "/store/likeit",
          type: "POST",
          data: { productNo: storeNo },
          success: function (result) {
            console.log(result);
          },
          error: function (error) {
            console.log(error);
          },
        });
        $(".contentss").toggleClass("heart-active");
        $(".heart").toggleClass("heart-active");
      }
    }

    // 로그인 안했을때
    else {
      swal({
        title: "로그인 필요",
        text: "로그인 후 좋아요가 가능합니다.",
        icon: "info",
        buttons: "확인",
      }).then((value) => {
        // 확인눌렀을때 실행
        if (value) {
          location.href = contextPath + "/member/login";
        }
      });
    }
  });
});

// 추가옵션 추가 했을때
$("#selectcate-option").change((e) => {
  let selectValue = $(e.target).val();
  console.log("selectValue : " + selectValue);
  if (selectValue == 1) {
    $(".option1").slideDown(500);
  } else if (selectValue == 2) {
    $(".option2").slideDown(500);
  } else if (selectValue == 3) {
    $(".option3").slideDown(500);
  }

  if ($(".choose-option").css("display") != "none") {
    console.log($(".choose-option")[selectValue - 1]);
  }
  resultPrice.text(maxPrice());
  resultComma();
  $(e.target).val(0);
});

// 추가옵션 닫기 했을때
$(".clear-btn").click((e) => {
  $(e.target).parent().slideUp(0);
  resultPrice.text(maxPrice());
  resultComma();
});

/* 수량 증감 버튼*/
let resultNum2 = result.innerText;
function count(type) {
  const result = document.getElementById("result");
  const pdtprice = document.getElementById("pdt-price");

  console.log(result + pdtprice + resultNum2);

  if (type === "add") {
    resultNum2 = parseInt(resultNum2) + 1;
  } else if (type === "minus") {
    resultNum2 = parseInt(resultNum2) - 1;
    console.log(result, pdtprice, resultNum2);
  }

  if (resultNum2 < 1) {
    resultNum2 = 1;
  }
  if (resultNum2 > stock) {
    swal(
      "현재 재고 부족으로 " + stock + "개 이상 구매할 수 없습니다.",
      "",
      "error"
    );
    resultNum2 = stock;
  }

  result.innerText = resultNum2;
  pdtprice.innerText =
    (price * parseInt(resultNum2)).toLocaleString("ko-KR") + "원";
  resultPrice.text(maxPrice());
  resultComma();
}

/* 추가 옵션 수량 증감 버튼*/
function totalCount(type, num) {
  const result = document.getElementsByClassName("total-result")[num];
  let resultNum = result.innerText;

  if (type === "add") {
    resultNum = parseInt(resultNum) + 1;
  } else if (type === "minus") {
    resultNum = parseInt(resultNum) - 1;
  }

  if (resultNum < 1) {
    resultNum = 1;
  }
  if (resultNum > adStock[num]) {
    swal(
      "현재 재고 부족으로 " + adStock[num] + "개 이상 구매할 수 없습니다.",
      "",
      "error"
    );
    resultNum = adstock[num];
  }
  result.innerText = resultNum;
  resultPrice.text(maxPrice());
  resultComma();
}
const optionList = [30, 50, 20];

function maxPrice() {
  let sum = 0;
  sum += price * parseInt($("#result").text());
  console.log(sum);
  for (let i = 0; i <= 2; i++) {
    if ($(".choose-option")[i].style.display == "block") {
      console.log(sum);
      let temp = $(".choose-option").children("div").children(".total-result")[
        i
      ];
      sum += optionList[i] * parseInt(temp.textContent);
      console.log(sum);
    }
  }
  resultPrice.text(sum);
  resultComma();
  return sum;
}

resultPrice.text(maxPrice());
resultComma();
/* 리뷰 */
/* 상세리뷰 토글check */
let reviewtoggle = false;
let temp = -1;
/* 댓글보기 func */
function reviewDetail() {
  const reviewLines = document.querySelectorAll(
    ".one-line-review>span:first-of-type"
  );
  for (let i = 0; i < reviewLines.length; i++) {
    reviewLines[i].addEventListener("click", function () {
      const box = $(this).parent();
      if (!reviewtoggle) {
        const reviewDetail = document.createElement("div");
        reviewDetail.className = "reviewBox";
        reviewDetail.innerHTML = "<h1>리뷰열림</h1>";
        /* reviewDetail.innerHTML = returnReviewContent(글번호); */
        box.after(reviewDetail);
        temp = i;
        reviewtoggle = true;
      } else {
        if (temp == i) {
          temp = -1;
          reviewtoggle = false;
          $(".reviewBox").remove();
        } else {
          $(".reviewBox").remove();
          const reviewDetail = document.createElement("div");
          reviewDetail.className = "reviewBox";
          reviewDetail.innerHTML = "<h1>리뷰열림</h1>";
          /* reviewDetail.innerHTML = returnReviewContent(글번호); */
          temp = i;
          box.after(reviewDetail);
        }
      }
    });
  }
}
/* review  Contents ajax 
    리뷰 list 시 constent span 에  value=글번호 넣어놓기
    or display none으로 글번호 작성
*/
function returnReviewContent(글번호) {
  /* ajax */
  return ajax;
}

function resultComma() {
  resultPrice.text(parseInt(resultPrice.text()).toLocaleString("ko-KR") + "원");
}

// 가격에 콤마표시
function comma() {
  let prices = $(".prices");
  for (let i = 0; i < prices.length; i++) {
    prices[i].textContent =
      parseInt(prices[i].textContent).toLocaleString("ko-KR") + "원";
  }
}
comma();

/* 스크롤 - 페이지 내 이동 */
function scrollExp() {
  
  $("html,body").css('scroll-behavior','smooth');
  document.querySelector("#contents-exp").scrollIntoView();
}
function scrollReview() {
  $("html,body").css('scroll-behavior','smooth');
  document.querySelector("#contents-review").scrollIntoView();
}
function scrollDelievery() {
  $("html,body").css('scroll-behavior','smooth');
  document.querySelector("#contents-delievery").scrollIntoView();
}

// 장바구니
function cart() {
  if (stock == 0) {
    swal("오류", "현재 상품은 재고가 부족하여 구매하실 수 없습니다.", "error");
    return;
  }
  selectAmount();
  console.log("result= " + resulty);
  if (resulty + suryang > stock) {
    swal("오류", "현재 상품은 재고가 부족하여 구매하실 수 없습니다.", "error");
    return;
  }
  const options = $(".choose-option");
  const totalresult = $(".total-result");
  console.log(totalresult.length);
  let arrays = "";

  for (let i = 0; i < totalresult.length; i++) {
    console.log(options[i].style.display);
    if (options[i].style.display == "block") {
      arrays += totalresult[i].textContent;
    } else {
      arrays += "0";
    }
    arrays += ",";
  }
  console.log(arrays);
  $.ajax({
    url: contextPath + "/cart/addCart",
    type: "POST",
    data: {
      storeNo: storeNo,
      addq: result.innerText,
      arrays: arrays,
      adPrice: adPrice + "",
      adStock: adStock + "",
    },
    success: function (result) {
      if (result > 0) {
        mx();
        swal("상품이 장바구니에 추가되었습니다.", "", "success");
        bigBox.innerHTML = "";

        if (cartOpenDefaultOne == 0) {
          selectModalCart();

          $(".resultPrice2")[0].textContent =
            mxprice.toLocaleString("ko-KR") + "원";
        }
      } else {
        swal("오류", "관리자에게 문의하세요", "error");
      }
    },
    error: function (err, message) {
      console.log(err);
      console.log(message);
    },
  });
}

// 스토어 물건 바로결제
function buy() {
  if (stock == 0) {
    swal("오류", "현재 상품은 재고가 부족하여 구매하실 수 없습니다.", "error");
    return;
  }
  selectAmount();
  console.log("result= " + resulty);
  if (resulty + suryang > stock) {
    swal("오류", "현재 상품은 재고가 부족하여 구매하실 수 없습니다.", "error");
    return;
  }
  const options = $(".choose-option");
  const totalresult = $(".total-result");
  console.log(totalresult.length);
  let arrays = "";

  for (let i = 0; i < totalresult.length; i++) {
    console.log(options[i].style.display);
    if (options[i].style.display == "block") {
      arrays += totalresult[i].textContent;
    } else {
      arrays += "0";
    }
    arrays += ",";
  }
  console.log(arrays);
  $.ajax({
    url: contextPath + "/cart/addCart",
    type: "POST",
    data: {
      storeNo: storeNo,
      addq: result.innerText,
      arrays: arrays,
      adPrice: adPrice + "",
      adStock: adStock + "",
    },
    success: function (result) {
      if (result > 0) {
        location.href= contextPath + "/cart";
        mx();
        bigBox.innerHTML = "";

        if (cartOpenDefaultOne == 0) {
          selectModalCart();

          $(".resultPrice2")[0].textContent =
            mxprice.toLocaleString("ko-KR") + "원";

           
        }
      } else {
        swal("오류", "관리자에게 문의하세요", "error");
      }
    },
    error: function (err, message) {
      console.log(err);
      console.log(message);
    },
  });
}

// 로그인 안했을때
function infoAlert() {
  swal({
    title: "로그인 필요",
    text: "로그인 후 이용 가능합니다.",
    icon: "info",
    buttons: "확인",
  }).then((value) => {
    if (value) {
      location.href = contextPath + "/member/login";
    }
  });
}
let resulty = 0;
function selectAmount() {
  $.ajax({
    url: contextPath + "/store/selectAmount",
    type: "POST",
    data: { storeNo: storeNo },
    async: false,
    success: function (result) {
      console.log(result);
      console.log(result + parseInt($("#result").text()));
      resulty = result;
    },
    error: function () {},
  });
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