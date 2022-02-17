<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   <link rel="stylesheet" href="${contextPath}/resources/css/mcart.css">
   <link rel="stylesheet" type="text/css" href="https://csshake.surge.sh/csshake.min.css">
    <script src="${contextPath}/resources/js/common/anime_min.js"></script>

   <div class="a">
 
       <div class="open-c">
       <img class="crt" alt="" src="${contextPath}/resources/images/common/shopping.png">
       </div>
       <div class="c-wrapper">
       <img src="${contextPath}/resources/images/common/garbage.svg" 
       style="width:30px;float:right; margin-top:21px;margin-right:100px ;cursor:pointer" class="shake" onclick="deleteAllCart()">
       <h4 style="float:right; line-height:2;cursor:pointer" onclick="deleteAllCart()" >모두 비우기 </h4>
           <div class="c-s-box">
           
           </div>
         

       </div>
       
       <div class="c-sum-btn">
           <h3>현재 총 구매 가격 :</h3><h3 class="resultPrice2">0원</h3>
           <h3 class="bi2" >
           <a href="${contextPath}/cart" style="color:white;">
           <u class="bi">구매하기</u></a></h3>
       </div>
   </div>
   <script>
	const contextPath123 = '${contextPath}';
	const loginMember123 = '${loginMember.memberNo}';
   </script>
   <script src="${contextPath}/resources/js/common/mcart.js"></script>
