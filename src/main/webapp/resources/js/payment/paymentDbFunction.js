
const orderInfoObject ={
    "productCd" : productCd,
    "productName" : "",
    "productNo" : productNo,
    "productOption" : "",
    "amount" : "",
    "totalPrice" : "",
    "memberNo" : loginMember,
    "shippingAddr" : "",
    "usedCouponNo" : "",
    "result_merchant_uid" : "",
    "result_imp_uid" : "",
}


//일반 결제성공 시 메서드
function saveOrderInfo(result_imp_uid , result_merchant_uid,paid_amount){
    //클래스의 경우
    if(productCd==3){
        orderInfoObject.productCd = 3;
        orderInfoObject.productName = document.getElementsByClassName("classTitle")[0].innerText;
        
        const option = 
        document.getElementsByClassName("classTitle")[0].innerText + " / "+
        document.getElementsByClassName("classDt")[0].innerText + " / "+
        document.getElementsByClassName("classtime")[0].innerText + " / ";
        
        orderInfoObject.productOption = option;
        orderInfoObject.amount = document.getElementsByClassName("totalPeople")[0].innerText ;
        const won = document.querySelector(".price-won>p").innerText;
        orderInfoObject.totalPrice =  won.substr(0, won.length -1);
        orderInfoObject.productNo = productNo;
        orderInfoObject.result_merchant_uid = result_merchant_uid;
        orderInfoObject.result_imp_uid = result_imp_uid;
    }
    $.ajax({
        url : "savePaymentInfo",
        type : "post",
        data : orderInfoObject,
        dataType : "json",
        success : function(data){
            const returnurl = data.msg

            if(returnurl=='예약성공'){
                alert("예약성공!");
                location.href = contextPath+"/payment/nomalPaymentResult?merchant_uid="+result_merchant_uid;
            }
            else{
                alert("결제 중 오류가 발생했습니다. 결제가 환불됩니다!");
                //cancelPay(result_merchant_uid,paid_amount);
            }
        },
        error: function(){
            alert("결제 중 오류가 발생했습니다. 결제가 환불됩니다!");
            //cancelPay(result_merchant_uid,paid_amount);
        }
    })
}
    function cancelPay(result_merchant_uid) {
        jQuery.ajax({
            "url": "normalpayRefund", // 예: http://www.myservice.com/payments/cancel
            "type": "POST",
            "contentType": "application/json",
            "data": JSON.stringify({
                "merchant_uid": result_merchant_uid, // 예: ORD20180131-0000011
                "cancel_request_amount": paid_amount, // 환불금액
                "reason": "오류로 결제 환불", // 환불사유
            }),
            "dataType": "json"
        });
    }

/* 카카오 정기결제 */
if($("#kakaoPay")!=null){
    const RegularOrderObject ={
        "productCd" : productCd,
        "productNo" : productNo,
        "productName" : "",
        "productOption" : "",
        "amount" : "",
        "totalPrice" : "",
        "memberNo" : loginMember,
        "memberId" : memberId,
        "shippingAddrEqualMemberAddr" : true,
        "shippingName" : "",
        "shippingAddr" : "",
        "shippingPhone" : "",
        "shippingEmail" : "",
        "shippingMsg" : "",
        "tid" : "",
        "partner_order_id" : ""
    }
    $("#kakaoPay").on("click", function(){
        RegularOrderObject.productName = document.querySelector(".titlesubs").innerText;
        RegularOrderObject.productOption = document.getElementsByName("totalOption")[0].value;
        RegularOrderObject.amount = amount;
        RegularOrderObject.totalPrice = totalprice;
        RegularOrderObject.shippingMsg = 
        document.getElementsByClassName("receiver-info")[9].value;
        console.log(RegularOrderObject);
        if(!$('input:radio[name=orderer-addr]:first-of-type').is(':checked')){
            const inputs = document.getElementsByClassName("receiver-info");
            RegularOrderObject.shippingAddrEqualMemberAddr =  false;
            RegularOrderObject.shippingName =  inputs[0].value;
            RegularOrderObject.shippingAddr = 
            inputs[1].value + "__" + inputs[2].value + "__" + inputs[3].value;
            RegularOrderObject.shippingPhone = 
            document.getElementsByClassName("receiver-info-select phone-input")[0].value
            + "-" +inputs[4].value + "-" + inputs[5].value;
            RegularOrderObject.shippingEmail =
            inputs[6].value + "-" + inputs[7].value;
        }
        
            $.ajax({
                url : 'kapay',
                data: RegularOrderObject,
                dataType : 'json',
                success: function(data){
                    console.log(data);
                    RegularOrderObject.tid = data.tid;
                    RegularOrderObject.partner_order_id = data.partner_order_id;
                    saveTidKey(RegularOrderObject);
                    
                    var box = data.next_redirect_pc_url;
                    //window.open(box); // 새창 열기
                    location.href = box;
                },
                error: function(error){
                    console.log(error);
                }
            });
        });
}

function saveTidKey(RegularOrderObject){
    $.ajax({
        url : 'payKeySave',
        type : "POST",
        data : RegularOrderObject,
        success: function(result){		
            console.log("성공");				
        },
        error: function(error){
            console.log(error);
        }
    });
}




function saveStoreOrderInfo(rsp_imp_uid,rsp_merchant_uid,rsp_paid_amount){

    const StoreOrderInfoObject ={
        "productCd" : 1,
        "productNos" : "",
        "productNames" : "",
        "productQuantities" : "",
        "productPrices" : "",
        "totalPrice" : rsp_paid_amount,
        "memberNo" : loginMember,
        "shippingAddr" : "",
        "usedCouponNo" : couponNo,
        "shippingAddrEqualMemberAddr" : true,
        "shippingName" : "",
        "shippingPhone" : "",
        "shippingEmail" : "",
        "shippingMsg" : "",
        "result_merchant_uid" :rsp_merchant_uid,
        "result_imp_uid" : rsp_imp_uid,
    }

    //스토어인 경우
    const itemss = document.querySelectorAll(".items>.inline-block>div>h4");
    let names = '';
    let productQuantity ='';
    let perPrice ='';
    for(let i = 0; i< itemss.length ; i++){
        if(i%2 == 0 ){
            names += itemss[i].innerText;
            let option_box = document.querySelectorAll(".inline-block.option-box > h4");
            if(option_box.length!=0){
                names += ' +추가상품 : '
            }
            for(let z = 0 ; z< option_box.length ; z++){
                if(z %2 == 0){
                    names +=  option_box[z].innerText + ",";
                }
            }
            if(option_box.length!=0){
                names = names.substring(0,names.length-1);
            }
            names += '/';
        }
        else{
            productQuantity += itemss[i].innerText+' ,';
            
        }
    }
    let perp = document.querySelectorAll(".cartSumPrice")
    for(let j = 0 ; j< perp.length ; j++){
        perPrice+= perp[j].innerText.replace('원','').replace(',','') + '/';
    }
    const productNoQ = document.querySelectorAll(".productsNo");
    let productNoss = "";
    for(let j = 0 ; j< productNoQ.length ; j++){
            productNoss+= productNoQ[j].value + '/';
        }
    productQuantity = productQuantity.substring(0,productQuantity.length-1);
    perPrice = perPrice.substring(0,perPrice.length-1);
    names = names.substring(0,names.length-1);
    productNoss = productNoss.substring(0,productNoss.length-1);

    StoreOrderInfoObject.productNos =productNoss
    StoreOrderInfoObject.productNames =names
    StoreOrderInfoObject.productQuantities =productQuantity
    StoreOrderInfoObject.productPrices =perPrice
    if(!$('input:radio[name=orderer-addr]:first-of-type').is(':checked')){
        const inputs = document.getElementsByClassName("receiver-info");
        StoreOrderInfoObject.shippingAddrEqualMemberAddr =  false;
        StoreOrderInfoObject.shippingName =  inputs[0].value;
        StoreOrderInfoObject.shippingAddr = 
        inputs[1].value + "__" + inputs[2].value + "__" + inputs[3].value;
        StoreOrderInfoObject.shippingPhone = 
        document.getElementsByClassName("receiver-info-select phone-input")[0].value
        + "-" +inputs[4].value + "-" + inputs[5].value;
        StoreOrderInfoObject.shippingEmail =
        inputs[6].value + "-" + inputs[7].value;
    }
    StoreOrderInfoObject.shippingMsg = 
    document.getElementsByClassName("receiver-info")[9].value;
    console.log(StoreOrderInfoObject);
    $.ajax({
        url : contextPath + "/payment/saveStoreOrderInfo",
        type : "post",
        data : StoreOrderInfoObject,
        dataType : "json",
        success : function(data){
            const returnurl = data.msg
            console.log(returnurl);
        },
        error: function(){
            alert("결제 중 오류가 발생했습니다. 결제가 환불됩니다!");
            //cancelPay(rsp_merchant_uid,rsp_paid_amount);
        }
    })
}