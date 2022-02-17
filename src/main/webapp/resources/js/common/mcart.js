let cartOpenDefaultOne = 1;
var elements = document.querySelectorAll(".a>*");
const bigBox = document.getElementsByClassName("c-s-box")[0];
let resultMax = 0;
// 천천히 열림
let am = anime({
  targets: elements,
  translateX: -420,
  autoplay: false,
});

// 빨리 닫힘
let am2 = anime({
  targets: elements,
  translateX: 0,
  autoplay: false,
});

// 장바구니 열기
document.querySelector(".open-c").addEventListener("click", () => {
  if (loginMember123 == "") {
    swal({
      title: "로그인 필요",
      text: "로그인 후 장바구니에 담기가 가능합니다.",
      icon: "info",
      buttons: "확인",
    }).then((value) => {
      if (value) {
        location.href = contextPath123 + "/member/login";
      }
    });
  } else {
    if (cartOpenDefaultOne == 1) {
      selectModalCart();
      am.restart();
      cartOpenDefaultOne = 0;
      mx();
    } else {
      am2.restart();
      cartOpenDefaultOne = 1;
      bigBox.innerHTML = "";
    }
  }
});

// 카트리스트를 불러옴
function selectModalCart() {
  $.ajax({
    url: contextPath123 + "/cart/selectModalCart",
    type: "POST",
    dataType: "JSON",
    success: function (result) {
      console.log(result);
      paging(result);
   
    },
    error: function (err) {
      console.log(err);
    },
  });
}

// 페이징
function paging(list) {
  for (let i of list) {
    if (i.parentNo == 0) {
      let miniprice = ((i.price * (100 - i.discountPer)) / 100) * i.addq;
      const middleBox = document.createElement("div");
      middleBox.className = "c-ss-box";
      middleBox.innerHTML +=
        '<div class="c-img" style=" background-image: url(' +
        contextPath123 +
        i.imgPath +
        ');"></div>';
      const smallBox = document.createElement("div");
      smallBox.className = "c-sss-box";
      bigBox.append(middleBox);
      middleBox.append(smallBox);
      smallBox.innerHTML += "<h4 >" + i.productName + "</h4>";

      if (i.discountPer != 0) {
        smallBox.innerHTML +=
          "<h2 style='color:#d3d3d3;text-decoration: line-through; display:inline-block; width:30px;'> " +
          i.price +
          " </h2> ";
        smallBox.innerHTML +=
          "<h2 style='float:right; width:137px;'>" +
          ((i.price * (100 - i.discountPer)) / 100).toLocaleString("ko-KR") + "원" +
          "</h2><br>";
      } else {
        smallBox.innerHTML += "<h2>" + (i.price).toLocaleString("ko-KR") + "원" + "</h2>";
      }

      smallBox.innerHTML +=
        '<img class="minus-btn2" src="' +
        contextPath123 +
        '/resources/images/subscribe/minus-btn.png" onclick="minusAddq(' +
        i.cartNo +
        ')">';
      smallBox.innerHTML +=
        '<span class="result2" data-name="' +
        i.cartNo +
        '">' +
        i.addq +
        "</span>";
      smallBox.innerHTML +=
        '<img class="add-btn2" src="' +
        contextPath123 +
        '/resources/images/subscribe/add-btn.png" alt="add-btn2" onclick="plusAddq(' +
        i.cartNo +
        ')" >';

      let options = "";
      for (let j of list) {
        if (j.parentNo == i.cartNo) {
          miniprice += j.price;
          options += j.productName + " " + j.addq + " ";
        }
      }
      smallBox.innerHTML +=
        '<input type="hidden" name="miniprice" value="' + miniprice + '">';

      if (options == "") {
        options = "(없음)";
      }
      middleBox.innerHTML += "<h4>추가 옵션 : " + options + "</h4>";
    }
    if ($(".c-ss-box").length > 4) {
      let csboxheight = $(".c-s-box").css("height");
      csboxheight = $(".c-ss-box").length * 170;
      $(".c-s-box").css("height", csboxheight + "px");
    }
  }

}

// 모두비웃기
function deleteAllCart() {
  if ($(".c-ss-box").length > 0) {
    swal({
      title: "장바구니 비우기",
      text: "장바구니 목록을 모두 비우시겠습니까 ?",
      icon: "info",
      closeOnClickOutside: false,

      buttons: {
        cancle: {
          text: "안비우기",
          value: false,
        },
        confirm: {
          text: "비우기",
          value: true,
        },
      },
    }).then((value) => {
      if (value) {
        $.ajax({
          url: contextPath123 + "/cart/deleteAllCart",
          type: "POST",
          success: function (result) {
            if (result > 0) {
              cartOpenDefaultOne = 1;

              am.play();

              am.finished.then(() => {
                am.reverse();
                bigBox.innerHTML = "";
              }).then(()=>{
                mx();
              });

              
            }
            
          },
        })
        am.reverse();
      } else {
        cartOpenDefaultOne = 0;
      }
    });
  } else {
    swal("장바구니 없음", "장바구니가 비어있습니다.", "error");
  }
}

function plusAddq(cartNo) {
  $.ajax({
    url: contextPath123 + "/cart/plusAddq",
    type: "POST",
    data: { cartNo: cartNo },
    success: function (result) {
      if (result > 0) {
        console.log(result);

        for (let i = 0; i < $(".result2").length; i++) {
          if ($(".result2")[i].dataset.name == cartNo) {
            $(".result2")[i].textContent =
              parseInt($(".result2")[i].textContent) + 1;
          }
        }

        mx();
      } else {
        swal(
          "재고부족",
          "현재 재고 부족으로 더이상 구매할 수 없습니다.",
          "error"
        );
      }
    },
    error: function (err) {
      console.log(err);
    },
  });
}

function minusAddq(cartNo) {
  for (let i = 0; i < $(".result2").length; i++) {
    if ($(".result2")[i].dataset.name == cartNo) {
      if ($(".result2")[i].textContent <= 1) {
        swal({
          title: "알림",
          text: "카트에 담긴 상품을 버리시겠습니까?",
          icon: "info",
          closeOnClickOutside: false,

          buttons: {
            cancle: {
              text: "아니요",
              value: false,
            },
            confirm: {
              text: "네",
              value: true,
            },
          },
        }).then((value) => {
          if (value) {
            $.ajax({
              url: contextPath123 + "/cart/deleteCart",
              type: "POST",
              data: { cartNo: cartNo },
              success: function (result) {
                console.log(result);
                if (result > 0) {
                  $(".result2")[i].parentElement.parentElement.remove();
                  if ($(".c-ss-box").length > 4) {
                    let csboxheight = $(".c-s-box").css("height");
                    csboxheight = $(".c-ss-box").length * 170;
                    $(".c-s-box").css("height", csboxheight + "px");
         
                  }
                }
                mx();
              },
              error: function (err, message) {
                console.log(err + message);
              },
            });
          } else {
          }
        });
      } else {
        $.ajax({
          url: contextPath123 + "/cart/minusAddq",
          type: "POST",
          data: { cartNo: cartNo },
          success: function (result) {
            if (result > 0) {
              console.log(result);

              for (let i = 0; i < $(".result2").length; i++) {
                if ($(".result2")[i].dataset.name == cartNo) {
                  $(".result2")[i].textContent =
                    parseInt($(".result2")[i].textContent) - 1;
                 
        
                
                }
              }
             mx();
            }
          },
          error: function (err) {
            console.log(err);
          },
        });
      }
    }
  }
}

let mxprice = 0;

function mx() {
  $.ajax({
    url: contextPath123 + "/cart/calc",
    type: "POST",
    success: function (result) {
      mxprice = result;
      $(".resultPrice2")[0].textContent =
      mxprice.toLocaleString("ko-KR") + "원";
    },
    error: function (error) {
      console.log(error);
    },
  });
}

mx();
