<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
   	<link rel="stylesheet" href="${contextPath}/resources/css/admin/adminLogin.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
</head>
<body>
			<c:if test="${ !empty cookie.saveId.value  }">
				<c:set var="chk" value="checked" />
			</c:if>
    <div id="admin-wraaper">
        <div id="adminlogin-box">
            <h1 class="prjName">Jean Val Jean </h1>
            <h1 class="admin-title">관리자 로그인</h1>
            <form action="${contextPath}/admin/board/main" method="POST" onsubmit="return validate()">
                <div class="login-one-line">
                    <label for="adminId">아이디</label><input type="text" name="adminId" id="adminId" class="admin-input" value="${cookie.saveId.value}">
                </div>
                <div class="login-one-line">
                    <label for="adminPw">비밀번호</label><input type="password" name="adminPw" id="adminPw" class="admin-input">
                </div>
                <div class="login-one-line loginbtn">
                	<label>아이디 저장<input type="checkbox" name="saveId" ${chk}></label>
                    <button>로그인</button>
                    <button type="reset">취소</button>
                </div>
            </form>
        </div>
    </div>
    <%-- session에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
	<c:if test="${ !empty requestScope.message }">
		<script> const message = "${requestScope.message}";
					window.onload= function(){
					if(message!=null){
						swal({
						  title: message,
						  text: '아이디 추가는 루트관리자만 가능합니다!',
						  icon: "error",
						});
						message=null;
					}
				}
		</script>
	</c:if>
	<script type="text/javascript" src="${contextPath}/resources/js/admin/adminLogin.js"></script>
</body>
</html>