function getContextPath() {
    return sessionStorage.getItem("contextPath");
}


$("#open").on("click", function(){
	
});


























/*
// 전체 영역 -> 모달
const contentbox = document.getElementById("adminPCont");

// 검색 버튼이 선택되었을 때 
function memberInfo(){
	contentbox.innerHTML = "";
	//이름 div 생성
	const funcName = document.createElement("div");
	funcName.setAttribute("class", "oneLine");
	funcName.innerHTML = "<h2>" + "회원정보 조회" + "</h2>"

	const searchDiv = document.createElement("div");
	searchDiv.setAttribute("class", "oneLine search-member");
	searchDiv.innerHTML = "<select name='searchSelector'><option value='1'>회원아이디</option><option value='2'>회원명</option><option value='3'>닉네임</option></select><input type='text' class='inputboxs'><button class='opencal' onclick='firstMemberSearch()'>회원검색</button>";

	const SearchResult = document.createElement("ul");
	SearchResult.setAttribute("class", "ResultLine");
	contentbox.append(funcName, searchDiv, SearchResult);
	enterfunc();
}


function firstMemberSearch() {
	cp = 1;
	cate = document.getElementsByName("searchSelector")[0].value;
	search = document.querySelector(".inputboxs").value;
	searchMember();
}
function searchMember() {
	let httpRequest = new XMLHttpRequest();
	const jsonObj = {
		"search": search,
		"cate": cate,
		"cp": cp
	}
	httpRequest.open("POST", contextPath + '/admin/board/searchMember', true);
	httpRequest.responseType = "json";
	httpRequest.setRequestHeader("Content-Type", "application/json");
	httpRequest.send(JSON.stringify(jsonObj));

	httpRequest.onreadystatechange = () => {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {

				const resultData = httpRequest.response;
				const memberList = JSON.parse(resultData.memberList);
				const page = JSON.parse(resultData.pagination);
				const parentUl = document.getElementsByClassName("ResultLine")[0];
				parentUl.innerHTML = '<li class="resultTitle oneMemberResult"><span class="oneMemberInfo">아이디</span><span class="oneMemberInfo">닉네임</span><span class="oneMemberInfo">이름</span><span class="oneMemberInfo">가입일</span><span class="oneMemberInfo">누적구매금액</span><span class="oneMemberInfo">탈퇴여부</span></li>';
				if (memberList.length == 0) {
					console.log("결과없음");
					const li = document.createElement("li");
					li.className = "oneMemberResult";
					li.innerHTML = "<div style='font-size:20px;'>조회되는 회원이 없습니다</div>";
					parentUl.append(li);
					return;
				}
				for (oneLi of memberList) {
					const li = document.createElement("li");
					li.className = "oneMemberResult";
					let inner =
						"<span class='oneMemberInfo sequence'>" + oneLi.memberEmail + "</span>" +
						"<span class='oneMemberInfo'>" + oneLi.memberNickName + "</span>" +
						"<span class='oneMemberInfo'>" + oneLi.memberName + "</span>" +
						"<span class='oneMemberInfo'>" + oneLi.enrollDt + "</span>" +
						"<span class='oneMemberInfo'>" + "100000" + "</span>";
					if (oneLi.statusCode == 1) inner += "<span class='oneMemberInfo'>" + "정상" + "</span>";
					else inner += "<span class='oneMemberInfo'>" + "탈퇴" + "</span>"
					li.innerHTML = inner;
					parentUl.append(li);
					const memNo = document.createElement("input");
					memNo.type = "hidden";
					memNo.value = oneLi.memberNo;
					memNo.name = "memberNo";
					li.append(memNo);
				}
				cate = resultData.cate;
				search = resultData.search;

				/* 페이지네이션 생성 및 동작  */
/*				parentUl.append(makePagination(page));
				addPageFunc(searchMember, page, cate, search);
			}
			else {

			}
		}
	}
}*/

