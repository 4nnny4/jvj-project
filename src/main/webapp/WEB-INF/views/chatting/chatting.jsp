<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1대1 문의</title>

  
<link rel="stylesheet" href="${contextPath}/resources/css/chatting/chatting.css/">
</head>

<body>
		<section>
        <div id="chat_body">
            <div id="chat_title">
                <span >문의 채팅방입니다.</span>
            </div>
            <div>
                <div id="chat_text">
					<ul class="display-chatting">
	
			<c:forEach items="${inpuChatting}" var="chattingMember">
			
				<c:choose>
					<c:when test="${!empty msg.message}">
						<c:set var="message" value="msg.message"/>
					</c:when>
					
					<c:otherwise>
						<c:set var="message" value="<b>${chattingMember.memberName}님이 나가셨습니다.</b>"/>
					</c:otherwise>
				</c:choose>
							
				<c:if test="${chattingMember.memberNo == loginMember.memberNo }">
					<li class="myChat">
						<span class="chatDate">${chattingMember.createDate}</span>
						<p class="chat">${chattingMember.message}</p>
					</li>
				</c:if>
				
				<c:if test="${chattingMember.memberNo != loginMember.memberNo }">
					<li>
						<b>${chattingMember.memberName }</b>	<br>
						<p class="chat">${chattingMember.message}</p>
						<span class="chatDate">${chattingMember.createDate}</span>
					</li>
				</c:if>
			
			</c:forEach>
	
		</ul>	
                </div>


                <div id="chatting_input">
                    <textarea  cols="40" rows="8" id="chatting_textarea"></textarea>

                    <div id="chat_div">
                        <button id="send" class="ct_btn sendBtn">입력</button><br>
                        <button id="chat_out" class="ct_btn">나가기</button>

                    </div>
                </div>
                
            </div>
        </div>
    </section>
    
    <!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->
	<!-- https://github.com/sockjs/sockjs-client -->
	<!-- sockjs : 웹소켓과 유사한 객체를 제공하는 JS라이브러리  -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script>
		let chattingSock = new SockJS("<c:url value='/chatting' />");
		const memberNo = "${loginMember.memberNo}";
		const memberName = "${loginMember.memberName}";
		const chatRoomNo = "${chatRoomNo}";
		const contextPath = "${contextPath}";
		
		
	</script>
	<script src="${contextPath}/resources/js/chat/chatting.js"></script>
    </body>