<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JVJ 공지사항 상세</title>
<jsp:include page="../common/header.jsp" />
 	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/admin/notice.css">
 	<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
 
 	<style>
        body{
            width: 100%;
            margin: 0 auto;
        }
        section{
            width: 1200px;
            margin: 0 auto;
        }
        #classpname{
            color: #A78A6C;
            text-align: center;
            margin-top: 35px;
            padding-bottom: 35px;
            border-bottom: 5px solid rgba(167, 138, 108, 0.5);
        }
        #notice-info{
            width: 100%;
        }
        #notice-info>div{
            border-bottom: 1px solid #B7B7B7;
            display: flex;
        }
        #notice-info>div>span{
            height: 60px;
            line-height: 60px;
        }
        #notice-info>div>span:first-of-type{
            width: 15%;
            font-weight: bold;
        }
        #notice-content >p{
        	width :100%;
        }
        #notice-content >p>img{
        	width :100%!important;
        }
        #gotolist{
            background: #A78A6C;
            border: 3px solid #A78A6C;
            box-sizing: border-box;
            border-radius: 5px;
            color: white;
            margin-top: 20px;
        }
        #notice-content{
            padding: 10px;
        }
        .coupon-area{
        	width:100%;
        	display : flex;
        	justify-content : left;
        	flex-wrap : wrap;
        }
        .notice-coupon{
        	width:48%;
        	display : flex;
        	flex-direction : row;	
        	justify-content : between-space;
        }
	    .coupon-exp{
	    	width : 70%;
	    	display : flex;
	    	flex-direction : column;	
	    	justify-content : center;
	    	padding : 10px;
		    margin : 10px 0px 10px 70px;
		    background: #eee;
	    }
	    .coupon-exp>span{
	    margin-bottom : 10px;
	    }
	    .coupon-exp>span:first-of-type{
	    	font-size : 20px;
	    	font-weight : bold;
	    }
	    .coupon-btn{
	    	width : 10%;
	        vertical-align: middle;
		    background: #A78A6C;
		    margin : 10px 0px;
		    padding: 25px 10px 25px 10px;
		    }
	    .coupon-btn>img{
	   		width:50px;
	   		height:50px;
	   }
	   .coupon-btn:hover{
	   	cursor : pointer;
	   }
	   
	   .soldout > .coupon-exp>span:first-of-type{
	   		text-decoration : line-through;
	   }
	   .soldout > .coupon-exp>span:last-of-type{
	   		text-decoration : line-through;
	   }
        </style>
</head>
<body>
    <main>
        <section>
            <article>
                <h1 id="classpname" style="font-size: 40px;">공지사항</h1>
                <div id="notice-info">
                    <div id="notice-title">
                        <span>제목</span>
                        <span>${notice.noticeTitle}</span>
                    </div>
                    <div id="notice-create-dt">
                        <span>작성일</span>
                        <span>${notice.createDt}</span>
                    </div>
                    <div class="coupon-area">
                    	<c:forEach items="${coupons}" var="onecou">
	                    	<c:if test="${onecou.couponLimit ==0}">
		                        <c:set var="soldout" value="soldout" />
	                    	</c:if>
	                    	<c:if test="${onecou.couponLimit !=0}">
		                        <c:set var="soldout" value="" />
	                    	</c:if>
	                    <div class="notice-coupon   ${soldout }">
		                        <div class="coupon-exp">
	                        	<span>  ${onecou.couponName} ${onecou.discountPer}% 할인</span>
	                        	<input type="hidden" value="${onecou.couponNo}" name="couponNo">
	                        	<input type="hidden" value="${onecou.couponStatusCode}" name="couponStatusCode">
	                        	<c:if test="${onecou.couponLimit ==0}">
		                       <span> 쿠폰이 모두 소진되었습니다!</span>
	                    		</c:if>
	                        	<c:if test="${onecou.couponLimit !=0}">
		                        <span> 남은수량 ${onecou.couponLimit} 매 (1인 최대 3매)</span>
	                    		</c:if>
	                        	<span> ${onecou.createDate} ~ ${onecou.expireDate} 사용가능 </span>
	                        </div>
	                        <div class="coupon-btn"  onclick="giveCoupon(this)">
	                        	<img src="${contextPath}/resources/images/onedayclassList/down.jpg">
	                        </div>
                   		</div>
                    	</c:forEach>
                   	</div>
                    <div id="notice-content">
                        ${notice.content}
                    </div>
                    <a id="gotolist" href="list?cate=${cate}&cp=${cp}">목록으로</a>
                </div>
            </article>
        </section>
    </main>
	<jsp:include page="../common/footer.jsp" />	
	<script> 
		const loginNo = "${loginMember.memberNo}"
		const notiecNo = "${notiecNo}"
	</script>
	<script>
		function giveCoupon(btn){
			if(loginNo==""){
				alert("로그인 후 이용해주세요!");
				return;
			}
			const status = document.getElementsByName("couponStatusCode")[0].value;
			if(status==1){
				const madeCouponNo = btn.previousElementSibling.children[1].value;
				const stockspan = btn.previousElementSibling.children[3];
				$.ajax({
					url: "giveCoupon" ,
					type: "POST",
					async: false,
					data : {
						"madeCouponNo" : madeCouponNo,
						"memberNo"  : loginNo
					},
					success : function(result){
						if(result>=0){
							alert("발급성공! 마이페이지에서 확인해주세요");
							let stock = result;
							if(result>0) {
								stockspan.innerText =" 남은수량 "+result+" 매"; 
							}
							else if(result==0) {
								stockspan.innerText ="쿠폰이 모두 소진되었습니다!"; 
								document.queryselector(".coupon-exp").className="coupon-exp  soldout";
							}
						}
						else if(result==-1){
							alert("1인당 최대 발급 수량 초과! 마이페이지에서 확인해주세요")
						}
						else if(result==-2){
							alert("쿠폰이 모두 소진되었습니다! ");
							location.reload();
						}
						else{
							alert("error! ");
							location.reload();
						}
					},
					error : function(){
						
					}
				})
			}
		}
	</script>
</body>
</html>