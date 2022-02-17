// 장바구니 비우기
$(".x-btn").on("click", (e) => {
  console.log($(e.target).parent().parent().children().val());
  const cartNo = $(e.target).parent().parent().children().val();
  let itemp = $(e.target).parent().parent().find(".cartSumPrice").text();
  const itemPrice = parseInt(itemp.substr("원", itemp.length - 1).replace(',', ''));
  $.ajax({
    url: specialContextPath + "/cart/deleteCart",
    type: "POST",
    data: { cartNo: cartNo },
    success: function (result) {
      console.log(result);
      if (result > 0) {
        console.log(cartNo + "장바구니 비우기");
      }
    },
    error: function (err, message) {
      console.log(err + message);
    },
  });
  $(e.target).parent().parent().next().fadeOut(500);
  $(e.target).parent().parent().fadeOut(500);
  console.log(itemp);
  resultPrice -= itemPrice;
  paging();
});
window.onload = function(){
  getCoupons();
}
let couponNo =0;
function getCoupons(){
  if(loginMember ==""){
    return;
  }
  $.ajax({
    url : contextPath + "/payment/callCoupon",
    data : {"memberNo": loginMember},
    type : "post",
    dataType : "json",
    success : function(data){
      console.log(data);
      const selbox = document.querySelector(".couponCss");
      for(opt of data ){
        const option = document.createElement("option");
        option.className="discountOpt";
        option.value= opt.couponNo;
        option.innerText = opt.couponName+'__'+(opt.discountPer)+' % 쿠폰';
        selbox.append(option);
      }
    },
    error : function(){
      alert("오류발생!")
    }
  })
}

// 미니 합계
const cartSumPrice = $(".cartSumPrice");
const productPrice = $(".productPrice");
const optionPrice = $(".optionPrice");
const items = $(".items");
const addq = $(".addq");
const rp = $(".resultPrice");
const optionBox = $(".option-box");
let resultPrice = 0;
let lastMaxPrice = $(".lastMaxPrice");
let taxPrice = $(".taxPrice");
for (let i = 0; i < items.length; i++) {
  let pp = parseInt(productPrice[i].textContent);
  let aq = parseInt(addq[i].textContent);
  let arr = [];
  let tempsum = 0;
  for (let j = 0; j < optionBox[i].children.length; j++) {
    if (j % 2 == 1) {
      arr.push(optionBox[i].children[j].value);
      tempsum += parseInt(optionBox[i].children[j].value);
    }
  }
  resultPrice += pp * aq + tempsum;
 
  cartSumPrice[i].textContent = pp * aq + tempsum + "원";
}

// 결산
function paging() {
  rp.text(resultPrice + "원");

  if (resultPrice >= 30000) {
    taxPrice.text(0 + "원");
    lastMaxPrice.text((resultPrice-parseInt($(".discountPrice").text())).toLocaleString('ko-KR'));
  } else {
    taxPrice.text('3,000' );
    lastMaxPrice.text((resultPrice-parseInt($(".discountPrice").text())+3000).toLocaleString('ko-KR'));
  }
 
}

$(".couponCss").on("change", function(){
  couponNo = $(this).val();
  const texts = $(".couponCss option:checked").text()
  const last1 = texts.lastIndexOf('_');
  const last2 = texts.indexOf('%');
  let text2 = texts.slice(last1+1, last2-1) / 100;
  const totalp = $(".resultPrice").text().replace("원","");
  if(couponNo==0){
    text2 =0
  }
  const calc = parseInt(totalp * text2);
  $(".calcDis").text(calc);
  $(".discountPrice").text(calc);
  paging();
})

paging();

// 결제 페이지 이동
function buyit() {
 
  $.ajax({
    url: specialContextPath + "/cart/toPayment",
    type: "POST",
    async:false,
    success: function (result) {
      document.getElementsByName('carrierList')[0].value = result;
      console.log(document.getElementsByName('carrierList')[0].value);
      document.requestForm.action = specialContextPath + "/payment/storePayment";
      document.requestForm.method = "POST";
      document.requestForm.submit();
      
      if ( result != '[]') {

        $.ajax({
          url: specialContextPath + "/cart/updateCart",
          type: "POST",
          async:false,
          success: function (result) {
            console.log(result);
            if (result > 0) {
              swal({
                title: "재고 부족",
                text: "현재 상품 수량이 재고 수량보다 많아, 장바구니에 다시 담아주시길 바랍니다.",
                icon: "info",
                buttons: "확인",
              }).then((value) => {
                // 확인눌렀을때 실행
                if (value) {
                  location.reload();
                }
              });
            } 
          },
          error: function (err) {
            console.log(err);
          },
        });
        
    
      } else{
          swal("알림","장바구니에 상품을 담아주세요","info");
      }
    },
    error: function (err) {
      console.log(err);
    },
  });  
  
}



function updateCart() {
  $.ajax({
    url: specialContextPath + "/cart/updateCart",
    type: "POST",
    success: function (result) {
      if (result > 0) {
        swal({
          title: "재고 부족",
          text: "현재 상품 수량이 재고 수량보다 많아, 장바구니에 다시 담아주시길 바랍니다.",
          icon: "info",
          buttons: "확인",
        }).then((value) => {
          // 확인눌렀을때 실행
          if (value) {
            location.reload();
          }
        });
      }
    },
    error: function (err) {
      console.log(err);
    },
  });
}


// view settings//

for(let i = 0; i<productPrice.length; i++ ){
    productPrice[i].textContent = parseInt(productPrice[i].textContent).toLocaleString('ko-KR')+"원";
    

}
for(let i = 0; i< cartSumPrice.length;i++){
    cartSumPrice[i].textContent = parseInt(cartSumPrice[i].textContent.substr('원',cartSumPrice[i].textContent.length-1)).toLocaleString('ko-KR')+"원";
}








// /* 추가 옵션 수량 증감 버튼*/
// function totalCount(type, num,amount) {
//   const resulty = document.getElementsByClassName("addq")[num];
//   let resultNum = resulty.innerText;

//   if (type === "add") {
//     resultNum = parseInt(resultNum) + 1;
//   } else if (type === "minus") {
//     resultNum = parseInt(resultNum) - 1;
//   }

//   if (resultNum < 1) {
//     resultNum = 1;
//   }
//   if (resultNum > amount) {
//     swal(
//       "현재 재고 부족으로 " + amount + "개 이상 구매할 수 없습니다.",
//       "",
//       "error"
//     );
//     resultNum = amount;
//   }
//   resulty.innerText = resultNum;
  
// }