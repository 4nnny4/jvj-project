<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1대1 문의</title>

<style type="text/css">
/* 채팅리스트 */
	html {overflow:hidden;} 
	ul>li{
	    display: inline-block;
	    list-style: none;
	    padding: 0;
	    text-align: center;
	}
	ul{
	    padding: 0;
	    
	}
	.chatting_div{
	    width: 400px;
	    height: 500px;
	    background-color: rgba(167, 138, 108, 0.3);
	    border: 2px solid rgba(167, 138, 108, 1);
	    border-radius: 10px;
	   
	}
	
	
	
	
	#chat_top_text{
	    width: 400px;
	    height: 20px;
	    padding-top: 10px;
	    padding-bottom: 10px;
	    text-align: center;
	    border-bottom: 2px solid rgba(167, 138, 108, 1);
	    color: darkred;
	    font-weight: bold;
	}
	
	#chatList_body>ul{
	    border-bottom: 1px solid rgba(167, 138, 108, 1);
	    margin: 0;
	    padding-top: 10px;
	    padding-bottom: 10px;
	    
	    
	}
	#chatList_body{
	    background-color: white;
	    overflow: scroll;
	    -ms-overflow-style: none;
	    height: 426px;
	    border-bottom-left-radius: 10px;
	    border-bottom-right-radius: 10px;
	}
	#chatList_body::-webkit-scrollbar{ display:none; }
	
	
	#chatList_body>ul>li{
	    width: 100px;
	    height: 30px;
	    line-height: 30px;
	    
	}
	
	#chatList_body>ul>li:first-child{
	    width: 50px;
	    
	}
	
	#chatList_body>ul>li:last-child{
	    width: 150px;
	    
	}
	
	#position_btn{
	    width: 100px;
	    height: 30px;
	    margin-bottom: 4px;
	    border-radius: 3px;
	    border:  2px solid rgba(167, 138, 108, 1);
	    background-color: rgba(167, 138, 108, 0.3);
	    margin-left: 20px;
	    
	}
	#position_btn>a{
		text-decoration: none;
	    color:black;
	}
	
	#chat_top{
	    text-align: center;
	    font-weight: bold;
	    padding-top: 5px;
	    padding-bottom: 5px;
	    border-bottom: 1px solid rgba(167, 138, 108, 1);
	    margin: 0;
	}
	#none{
	width: 400px; 
	text-align : center; 
	padding-top: 150px;
    font-weight: bold; 
    padding-bottom: 255px;
    background-color: rgba(167, 138, 108, 0.3);
    }
    
</style>
</head>
<body style="overflow-x:hidden; overflow-y:auto;">
	<main>
		<section>
        <div class="chatting_div">
            <div id="chat_top_text">
                <span>회원 문의 리스트</span>
            </div>
            <div>
                <ul id="chat_top">
                    <li style="width: 50px;">방번호</li><!--
                    --><li style="width: 100px;" >회원 이름</li><!--
                    --><li style="width: 100px;">참가 인원</li><!--
                    --><li style="width: 150px;" ></li>
                </ul>
            </div>

            <div id="chatList_body">
            <c:choose>
            <c:when test="${empty chatVowel}">
           		<div id="none">회원의 문의가 없습니다</div>
            </c:when>
            <c:otherwise>
            	<c:forEach items="${chatVowel}" var="chatVowel">
            	<ul>
                   <li>${chatVowel.chatRoomNo}</li><!--
                --><li>${chatVowel.memberName}</li><!--
                --><li></li><!--
                --><li> <div id="position_btn">
                			<a  href="${contextPath}/chatting/chat/${chatVowel.chatRoomNo}">입장</a>
                		</div>
                  </li>
                </ul>
                </c:forEach>
            </c:otherwise>
            </c:choose>
            </div>

        </div>
    </section>
    
		
    </main>
    <%-- 
    <script src="${contextPath}/resources/js/chat/chatList.js"></script>
    <script type="text/javascript">
    
    </script>
    --%>
    </body>