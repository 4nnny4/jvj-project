<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../common/header.jsp" />
<link rel="stylesheet" href="${contextPath}/resources/css/member/login.css">

<content>
  <div class="login-title">
        <p class="text-center">로그인</p>
    </div> 
    
    <form action="${contextPath}/member/login" method="post" onsubmit="return loginValidate()">  
        <div class="input-info">
            <div class="input-info-div">
                <div class="p-div"><p>Email</p></div>
                <div class="input-div"><input type="text" class="receiver-info" id="memberEmail" name="memberEmail"
                						value="${ cookie.saveId.value }"></div>
            </div>
            <div class="input-info-div">
                <div class="p-div"><p>Password</p></div>
                <div class="input-div"><input type="password" class="receiver-info" id="memberPw" name="memberPw"></div>
            </div>

        </div>

        <div class="saveContent">
            <div>
                <label class="container">아이디 저장
                
                <c:if test="${!empty cookie.saveId.value}">
                	<c:set var="chk" value="checked"  />
                </c:if>
                
                    <input type="checkbox" id="save" name="save" ${chk}>
                   	<span class="checkmark"></span>
                </label>


                <ul>
                    <li><a href="${contextPath}/member/searchId"><span>아이디 찾기</span></a><div class="updown"></div></li>
                    <li><a href="${contextPath}/member/searchPw"><span>비밀번호 찾기</span></a></li>
                </ul>
            </div>
        </div>

        <div class="login-btn">
            <button type="submit" id="login">로그인</button>
        </div>
    </form> 
        


    

    <div class="login-btns">
        <a href="${kakao_url}">
            <div class="login-kakao">
                <img src="${contextPath}/resources/images/member/kakao-talk.png">
                <p>카카오톡으로 로그인</p>
            </div>
        </a>
        <a href="${naver_url}">
            <div class="login-naver">
                <img src="${contextPath}/resources/images/member/naver.png">
                <p>네이버로 로그인</p>
            </div>
        </a>
    </div>

    <ul class="goSignUp">
        <li><a href="${contextPath}/member/signUp"><span>아직 회원이 아니신가요?</span></a></li> 
    </ul>
</content>

<jsp:include page="../common/footer.jsp" />

 <script src="${contextPath}/resources/js/member/login.js"></script>