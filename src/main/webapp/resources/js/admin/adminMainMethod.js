

        const routepath = contextPath+'/admin/board/route/';
        /* router 구현 */
        const routes = [
            { path: routepath+'showSales', component: function(){
                drawing(); } },
                /* 1 조회 업무 */
            { path: routepath+'searchMember', component: function(){
                memberInfo();firstMemberSearch();} },//회원
            { path: routepath+'subsMember', component: function(){
                subscribingInfo();} },//구독상품 구매자
            { path: routepath+'searchOrder', component: function(){
                orderInfo();} },
                /* 2 게시글작성 -- 공지사항 / 상품등록 */
            { path: routepath+'noticeWriter', component: function(){
                noticeboardWriter('공지사항 등록');} },
            { path: routepath+'storeSubmit', component: function(){
                commonWriter('일반 상품 등록'); makeNormalPWritePage();} },
            { path: routepath+'subsSubmit', component: function(){
                commonWriter('구독 상품 등록');  makeSubscribePWritePage();} },
            { path: routepath+'classSubmit', component: function(){
                commonWriter('클래스 상품 등록'); makeClassPWritePage();} },
            { path: routepath+'optionSubmit', component: function(){
                optionProductWrite(); } },
                /* 3 글관리 */
            { path: routepath+'reviewManage', component: function(){
                reviewManage();
                document.getElementById("searchreview").click();
            } },
            { path: routepath+'noticeManage', component: function(){
                modifyWrite();searchNotice();} },
                { path: routepath+'modifyNotice', component: function(){
                    if(modifyNo==0){
                        alert("새로고침시 수정내용이 초기화됩니다")
                        location.href='noticeManage';
                        return;
                    }
                    noticeboardWriter('공지사항수정');
                    modifyThisNotice();
                } },
                /* 4 상품 관리(수정) */
            { path: routepath+'productManage', component: function(){
                modifyProduct();searchProduct();} },
                /* 4-1 상품관리 실제 수정 form 페이지 */
                { path: routepath+'modifyProduct', component: function(){
                    if(productcate==3){makeClassModify();}
                    else if(productcate==1){makeStoreModify();}
                    else if(productcate==2){makeSubsModify();}
                    else {
                        alert("준비중입니다"+productcate)
                        location.href='main';
                    }
                } },
                /* 4-2 구독옵션관리 */
            { path: routepath+'subsOptionManage', component: function(){
                subsOptionManage();
            } },
                /* 기타업무 */
                { path: routepath+'couponMake', component: function(){
                    couponMake();} },
                /* 마케팅 업무 */
                { path: routepath+'sendCouponToMember', component: function(){
                    sendCouponToMember();} },
        ];
        
		function render(path){
            try {
                for(let route of routes){
                    if(route.path === path){
                        route.component();
                        return;
                    }
                }
            } catch (err) {
                console.error(err);
            }
		};
        const allNavi = document.querySelectorAll(".eachW > li >a");
        allNavi.forEach(
            function(navi){
                navi.addEventListener("click", e=>{
                    if(!e.target.matches('.eachW > li >a')) 
                    {
                        console.log("안맞음");
                        return;
                    }
                    e.preventDefault();
                    //이동 방지
                    firstdone=true;
                    const path = e.target.getAttribute('href');
                    // pushState는 주소창의 url을 변경하지만 HTTP 요청을 서버로 전송하지는 않는다.
                    window.history.pushState({}, null, path);
                    render(path);
                });
            }
        )

        // pjax 방식은 hash를 사용하지 않으므로 hashchange 이벤트를 사용할 수 없다.
        // popstate 이벤트는 pushState에 의해 발생하지 않고 앞으로/뒤로 가기 버튼을 클릭하거나
        // history.forward/back/go(n)에 의해 history entry가 변경되면 발생한다.
        window.addEventListener('popstate', () => {
        // 앞으로/뒤로 가기 버튼을 클릭하면 window.location.pathname를 참조해 뷰를 전환한다.
        render(window.location.pathname);
        });

        // 웹페이지가 처음 로딩되면 window.location.pathname를 확인해 페이지를 이동시킨다.
        // 새로고침을 클릭하면 현 페이지(예를 들어 localhost:8080/~~)가 서버에 요청된다.
        // 이에 응답하는 처리는 서버에서 구현해야 한다.
        render(window.location.pathname);

        //---------------------------------------------------

        /* input 엔터시 동작 함수 */
        function enterfunc(){
            document.querySelector(".inputboxs").addEventListener("keyup",function(){
                if (window.event.keyCode == 13) {
                    document.querySelector(".opencal").click();
                }
            })
        }

        //---------------------------------------------------
        //1. 회원정보 조회---------------------------------------------------
        function memberInfo(){
            contentbox.innerHTML="";
             //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"회원정보 조회"+"</h2>"

            const searchDiv = document.createElement("div");
            searchDiv.setAttribute("class", "oneLine search-member");
            searchDiv.innerHTML="<select name='searchSelector'><option value='1'>회원아이디</option><option value='2'>회원명</option><option value='3'>닉네임</option></select><input type='text' class='inputboxs'><button class='opencal' onclick='firstMemberSearch()'>회원검색</button>";

            const SearchResult = document.createElement("ul");
            SearchResult.setAttribute("class", "ResultLine");
            contentbox.append(funcName,searchDiv,SearchResult);
            enterfunc();
        }

        
        function firstMemberSearch(){
            cp =1;
            cate = document.getElementsByName("searchSelector")[0].value;
            search = document.querySelector(".inputboxs").value;
            searchMember();
        }
        function searchMember(){
            let httpRequest = new XMLHttpRequest();
            const jsonObj ={
                "search": search,
                "cate" : cate,
                "cp" : cp
            }
            httpRequest.open("POST", contextPath+'/admin/board/searchMember', true);
            httpRequest.responseType = "json";
            httpRequest.setRequestHeader("Content-Type", "application/json");
            httpRequest.send(JSON.stringify(jsonObj));
            
            httpRequest.onreadystatechange = ()=>{
                if(httpRequest.readyState===XMLHttpRequest.DONE){
                    if(httpRequest.status ===200){
                        
                        const resultData = httpRequest.response;
                        const memberList = JSON.parse(resultData.memberList);
                        const page = JSON.parse(resultData.pagination);
                        const parentUl = document.getElementsByClassName("ResultLine")[0];
                        parentUl.innerHTML = '<li class="resultTitle oneMemberResult"><span class="oneMemberInfo">아이디</span><span class="oneMemberInfo">닉네임</span><span class="oneMemberInfo">이름</span><span class="oneMemberInfo">가입일</span><span class="oneMemberInfo">누적구매금액</span><span class="oneMemberInfo">탈퇴여부</span></li>';
                        if(memberList.length ==0){
                            console.log("결과없음");
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            li.innerHTML="<div style='font-size:20px;'>조회되는 회원이 없습니다</div>";
                            parentUl.append(li);
                            return;
                        }
                        for(oneLi of memberList){
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            let inner =
                            "<span class='oneMemberInfo sequence'>"+oneLi.memberEmail+"</span>"+
                            "<span class='oneMemberInfo'>"+oneLi.memberNickName+"</span>"+
                            "<span class='oneMemberInfo'>"+oneLi.memberName+"</span>"+
                            "<span class='oneMemberInfo'>"+oneLi.enrollDt+"</span>"+
                            "<span class='oneMemberInfo'>"+(oneLi.paysum).toLocaleString('ko-KR')+" 원 </span>";
                            if(oneLi.statusCode ==1) inner+="<span class='oneMemberInfo'>"+"정상"+"</span>";
                            else  inner+="<span class='oneMemberInfo'>"+"탈퇴"+"</span>"
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
                        parentUl.append(makePagination(page));
                        addPageFunc(searchMember, page,cate,search); 
                    }
                    else{

                    }
                }
            }
        }

        //-----------------------------------------------------------------//
        //2. 구독 회원 조회---------------------------------------------------
        
        function subscribingInfo(){
            contentbox.innerHTML="";
            //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"구독회원 조회"+"</h2>"
            const searchDiv = document.createElement("div");
            searchDiv.setAttribute("class", "oneLine subscribe-search");
            searchDiv.innerHTML="<select name='showSubsMember'>"+
            "<option value='1437'>빵 세트</option>"+
            "<option value='1438'>빵 & 커피 세트</option>"+
            "</select>";
            const SearchResult = document.createElement("ul");
            SearchResult.setAttribute("class", "ResultLine");

            contentbox.append(funcName,searchDiv,SearchResult);
            document.getElementsByName("showSubsMember")[0].addEventListener("change",function(){
                tempNo= this.value;
                cp=1;
                showSubsMember(tempNo)
            })
            showSubsMember(tempNo);
        }
        function showSubsMember(proNo){
            
            let httpRequest = new XMLHttpRequest();
            const jsonObj ={
                "productNo": tempNo,
                "cp" : cp
            }
            httpRequest.open("POST", contextPath+'/admin/board/showSubsMember', true);
            httpRequest.responseType = "json";
            httpRequest.setRequestHeader("Content-Type", "application/json");
            httpRequest.send(JSON.stringify(jsonObj));
            
            httpRequest.onreadystatechange = ()=>{
                if(httpRequest.readyState===XMLHttpRequest.DONE){
                    if(httpRequest.status ===200){
                        
                        const resultData = httpRequest.response;
                        const resultSubsMember = JSON.parse(resultData.resultSubsMember);
                        const page = JSON.parse(resultData.pagination);
                        const parentUl = document.getElementsByClassName("ResultLine")[0];
                        parentUl.innerHTML = 
                        "<li class='resultTitle oneMemberResult'>"+
                            "<span class='oneMemberInfo'>구독상품명</span>"+
                            "<span class='oneMemberInfo notice-title'>아이디</span>"+
                            "<span class='oneMemberInfo'>닉네임</span>"+
                            "<span class='oneMemberInfo'>구독시작일</span>"+
                            "<span class='oneMemberInfo notice-title'>상세 옵션</span>";
                        "</li>";
                        for(oneLi of resultSubsMember){
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            let inner =
                            "<span class='oneMemberInfo'>"+oneLi.title+"</span>"+
                            "<span class='oneMemberInfo notice-title'>"+oneLi.userId+"</span>"+
                            "<span class='oneMemberInfo'>"+oneLi.userNickName+"</span>"+
                            "<span class='oneMemberInfo'>"+oneLi.StartDay+"</span>"
                            +"<span class='oneMemberInfo notice-title'>"+oneLi.options+"</span>";
                            li.innerHTML = inner;
                            parentUl.append(li);
                        }

                        /* 페이지네이션 생성 및 동작  */
                        parentUl.append(makePagination(page));
                        addPageFunc(showSubsMember, page,cate,search); 
                    }
                    else{

                    }
                }
            }
        }








        //-----------------------------------------------------------------//

        //3. 주문 조회--------------------------------------------------------
        function orderInfo(){
            contentbox.innerHTML="";
                //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"주문 내역 조회"+"</h2>"
            const searchDiv = document.createElement("div");
            searchDiv.setAttribute("class", "oneLine order-search");
            searchDiv.innerHTML="<select>"+
            "<option>회원ID로 검색</option>"+
            "<option>주문번호로 검색</option>"+
            "</select>"+
            "<select>"+
            "<option>결제완료+환불</option>"+
            "<option>결제완료</option>"+
            "<option>환불</option>"+
            "</select>"+
            "<input type='text'><button class='opencal'>검색</button>";
            const SearchResult = document.createElement("ul");
            SearchResult.setAttribute("class", "ResultLine");
            SearchResult.innerHTML=
            "<li class='resultTitle oneMemberResult'>"+
                "<span class='oneMemberInfo sequence'>주문 번호</span>"+
                "<span class='oneMemberInfo'>아이디</span>"+
                "<span class='oneMemberInfo notice-title'>배송지</span>"+
                "<span class='oneMemberInfo'>구매금액</span>"+
                "<span class='oneMemberInfo'>결제일</span>"+
                "<span class='oneMemberInfo'>주문상태</span>"+
            "</li>";
            contentbox.append(funcName,searchDiv,SearchResult);
        }
        //-----------------------------------------------------------------//
        //글관리1- 리뷰 관리
        function reviewManage(){
            contentbox.innerHTML="";
            //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"리뷰 관리"+"</h2>"
            const searchDiv = document.createElement("div");
            searchDiv.setAttribute("class", "oneLine search-review");
            searchDiv.innerHTML="<select name='review_searchby'>"+
            "<option value='0'>All</option>"+
            "<option value='1'>스토어 상품 별</option>"+
            "<option value='2'>구독 상품 별</option>"+
            "<option value='3'>클래스 상품 별</option>"+
            "<option value='4'>아이디 별</option>"+
            "<input type='text' class='inputboxs'><button class='opencal' type='button' value='1' id='searchreview'>검색</button>";
            const SearchResult = document.createElement("ul");
            SearchResult.setAttribute("class", "ResultLine");
            contentbox.append(funcName,searchDiv,SearchResult);
            enterfunc();
            document.getElementById("searchreview").addEventListener("click", function(){
                cp = this.value;
                cate = document.querySelector("select[name='review_searchby']").value;
                search = document.querySelector(".inputboxs").value;
                searchReview();
            })
        }

        function searchReview(){
            let httpRequest = new XMLHttpRequest();
            const reqJson  = {
                "search": search,
                "cate" : cate,
                "cp" : cp
            } 
            /* Post 방식으로 요청 */
            httpRequest.open("POST", contextPath+'/admin/board/reviewselect' , true);
            /* Response Type 을 Json으로  */
            httpRequest.responseType = "json";
            /* 요청 헤더에 컨텐츠 타입 json 명시 */
            httpRequest.setRequestHeader("Content-Type", "application/json");
            /* 데이더 json으로 변환 후 전송하기 */
            httpRequest.send(JSON.stringify(reqJson));
            //readyState가 변화했을 때 함수 실행
            httpRequest.onreadystatechange = ()=>{
                if(httpRequest.readyState === XMLHttpRequest.DONE){
                //readyState가 Done이고 응답값이 200이면
                    if(httpRequest.status ===200){
                        //성공
                        const result = httpRequest.response;
                        console.log(result);
                        const reviewList = JSON.parse(result.reviewList);
                        const page = JSON.parse(result.pagination);
                        console.log(reviewList);
                        const ul = document.querySelector(".ResultLine");
                        ul.innerHTML=
                        "<li class='resultTitle oneMemberResult'>"+
                            "<span class='oneMemberInfo reviewno'>글 번호</span>"+
                            "<span class='oneMemberInfo'>상품명</span>"+
                            "<span class='oneMemberInfo reviewId'>아이디</span>"+
                            "<span class='oneMemberInfo  reviewId'>리뷰 제목</span>"+
                            "<span class='oneMemberInfo '>별점</span>"+
                            "<span class='oneMemberInfo'>블라인드 여부</span>"+
                            "<span class='oneMemberInfo'>블라인드 처리</span>"+
                        "</li>";

                        if(reviewList.length ==0){
                            console.log("결과없음");
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            li.innerHTML="<div style='font-size:20px;'>조회되는 결과가 없습니다</div>";
                            ul.append(li);
                            return;
                        }
                        for(review of reviewList){
                            let status='';
                            if(review.reviewStatus==0) status='정상 리뷰'
                            else if(review.reviewStatus==1) status='블라인드 리뷰'
                            else status='삭제'
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            const inner =
                            "<span class='oneMemberInfo reviewno'>"+review.reviewNo+"</span>"+
                            "<span class='oneMemberInfo'>"+review.productName+"</span>"+
                            "<span class='oneMemberInfo  reviewId'>"+review.userId+"</span>"+
                            "<span class='oneMemberInfo  reviewId showR' onclick='showRcontent(this)'>"+review.reviewTitle+"</span>"+
                            "<span class='oneMemberInfo '>"+review.reviewPoint+"</span>"+
                            "<span class='oneMemberInfo'>"+status+"</span>"+
                            "<span class='oneMemberInfo'><button onclick='doblind(this)'>블라인드</button></span>";
                            li.innerHTML = inner;
                            ul.append(li);
                        }
                        cate = result.cate;
                        search = result.search;
                        /* 페이지네이션 생성 및 동작  */
                        ul.append(makePagination(page));
                        addPageFunc(searchReview, page,cate,search);

                    }
                    else{
                        //실패
                    }
                }
            }
        }
        function showRcontent(btn){
            const nos = btn.previousElementSibling.previousElementSibling.previousElementSibling.innerText;
            
            if(!Reviewtoggle){
                $.ajax({
                    url : contextPath+'/admin/board/getReview',
                    type: "post",
                    data: {"reviewNo" : nos},
                    success : function(content){
                        Reviewtoggle= true;
                        tempRNo = nos;
                        const lis = document.createElement("li");
                        lis.className = "appendreviewC";
                        lis.innerHTML = content;
                        btn.parentElement.after(lis);
                    },
                    error : function(){
                        alert("에러발생")
                    }
                })
            }
            else if(nos == tempRNo){
                document.querySelector(".appendreviewC").remove();
                Reviewtoggle= false;
            }
            else {
                document.querySelector(".appendreviewC").remove();
                $.ajax({
                    url : contextPath+'/admin/board/getReview',
                    type: "post",
                    data: {"reviewNo" : nos},
                    success : function(content){
                        Reviewtoggle= true;
                        tempRNo = nos;
                        const lis = document.createElement("li");
                        lis.className = "appendreviewC";
                        lis.innerHTML = content;
                        btn.parentElement.after(lis);
                    },
                    error : function(){
                        alert("에러발생")
                    }
                })
            }
        }
        function doblind(btn){
            const nowstatus = btn.parentElement.previousElementSibling.innerText;
            const nos = btn.parentElement.parentElement.firstElementChild.innerText;
            console.log(nos);
            let msg = '';
            let doing = 0;
            if(nowstatus=='블라인드 리뷰'){
                msg= "블라인드처리를 취소하시겠습니까?";
            }
            else{
                msg= "블라인드처리 하시겠습니까?";
                doing= 1;
            }
            if(confirm(msg)){
                $.ajax({
                    url : contextPath+'/admin/board/blindReview',
                    type: "post",
                    data: {"reviewNo" : nos,
                        "setStatus" : doing
                    },
                    success : function(result){
                        searchReview();
                    },
                    error : function(){
                        alert("에러발생")
                    }
                })
            }
        }
        //-----------------------------------------------------------------//
        
        //글관리2- 공지사항 관리
        function modifyWrite(){

            contentbox.innerHTML="";
             //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"공지사항 관리"+"</h2>"

            const searchDiv = document.createElement("div");
            searchDiv.setAttribute("class", "oneLine search-review");
            searchDiv.innerHTML="<select name='noticecate'>"+
            "<option value='0'>All</option>"+
            "<option value='1'>프로모션</option>"+
            "<option value='2'>공지사항</option>"+
            "<option value='3'>이벤트</option>"+
            "<input type='text' class='inputboxs'><button class='opencal' id='noticeSearch' value='1'>검색</button>";
            
            const SearchResult = document.createElement("ul");
            SearchResult.setAttribute("class", "ResultLine");
            contentbox.append(funcName,searchDiv,SearchResult);
            enterfunc();
            if(firstdone){
                cate = document.querySelector("select[name='noticecate']").value;
                search = document.querySelector(".inputboxs").value;
                cp=1;
                firstdone=false;
            } 
            document.getElementById("noticeSearch").addEventListener("click", function(){
                cp = this.value;
                cate = document.querySelector("select[name='noticecate']").value;
                search = document.querySelector(".inputboxs").value;
                searchNotice();
            })
        }

        /* 공지사항 리스트 조회 ajax */
        function searchNotice(){

            let httpRequest = new XMLHttpRequest();
            const reqJson  = {
                "search": search,
                "cate" : cate,
                "cp" : cp
            } 

            /* Post 방식으로 요청 */
            httpRequest.open("POST", contextPath+'/admin/board/noticeselect' , true);

            /* Response Type 을 Json으로  */
            httpRequest.responseType = "json";

            /* 요청 헤더에 컨텐츠 타입 json 명시 */
            httpRequest.setRequestHeader("Content-Type", "application/json");

            /* 데이더 json으로 변환 후 전송하기 */
            httpRequest.send(JSON.stringify(reqJson));

            //readyState가 변화했을 때 함수 실행
            httpRequest.onreadystatechange = ()=>{
                if(httpRequest.readyState === XMLHttpRequest.DONE){
                //readyState가 Done이고 응답값이 200이면
                    if(httpRequest.status ===200){
                        //성공
                        const result = httpRequest.response;
                        const noticeList = JSON.parse(result.noticeList);
                        const page = JSON.parse(result.pagination);

                        const ul = document.querySelector(".ResultLine");
                        ul.innerHTML=
                        "<li class='resultTitle oneMemberResult'>"+
                            "<span class='oneMemberInfo numberformat'>글 번호</span>"+
                            "<span class='oneMemberInfo'>카테고리</span>"+
                            "<span class='oneMemberInfo notice-title'>제목</span>"+
                            "<span class='oneMemberInfo'>수정</span>"+
                        "</li>";
                        if(noticeList.length ==0){
                            console.log("결과없음");
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            li.innerHTML="<div style='font-size:20px;'>조회되는 결과가 없습니다</div>";
                            ul.append(li);
                            return;
                        }
                        for(notice of noticeList){
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            const inner =
                            "<span class='oneMemberInfo numberformat'>"+notice.noticeNo+"</span>"+
                            "<span class='oneMemberInfo'>"+notice.noticeCateName+"</span>"+
                            "<span class='oneMemberInfo  notice-title'><a target='_blank' href='"+contextPath+
                            "/notice/view?noticeNo="+notice.noticeNo+"&cate="+result.cate+"&cp="+page.currentPage+"'"+
                            ">"+notice.noticeTitle+"</a></span>"+
                            "<span class='oneMemberInfo'>"
                            +"<a href='modifyNotice' class='modifyBtn'>수정하기</a>";
                            li.innerHTML = inner;
                            ul.append(li);
                        }
                        cate = result.cate;
                        search = result.search;
                        /* 페이지네이션 생성 및 동작  */
                        ul.append(makePagination(page));
                        addPageFunc(searchNotice, page,cate,search);

                        /* 수정동작 실행 함수 */
                        const allModifyBtn = document.querySelectorAll(".modifyBtn");
                        allModifyBtn.forEach(
                            function(Btn){
                                Btn.addEventListener("click", e=>{
                                    modifyNo = Btn.parentElement.parentElement.firstElementChild.innerText;
                                    if(!e.target.matches('.modifyBtn')) 
                                    {
                                        console.log("안맞음");
                                        return;
                                    }
                                    e.preventDefault();
                                    //이동 방지
                                    const path = e.target.getAttribute('href');
                                    // pushState는 주소창의 url을 변경하지만 HTTP 요청을 서버로 전송하지는 않는다.
                                    window.history.pushState({}, null, path);
                                    render(routepath+path);
                                });
                            }
                        )
                    }
                    else{
                        //실패
                    }
                }
            }
        }
        /* 공지사항 수정 동작 */
        function modifyThisNotice(){
                const goback = document.createElement("button");
                const reload = document.createElement("button");
                goback.setAttribute("type", "button");
                reload.setAttribute("type", "button");
                goback.setAttribute("onclick","window.history.back()");
                reload.setAttribute("onclick","loadNoticeDetail()");
                goback.innerText = "목록으로 돌아가기";
                reload.innerText = "다시불러오기";
                const modifyNoInput = document.createElement("input");
                modifyNoInput.name = "noticeNo";
                modifyNoInput.type="hidden";
                modifyNoInput.value = modifyNo;
                document.querySelector("#writerForm").prepend(goback,reload,modifyNoInput);
                /* 공지사항 내용 불러오기   */
                loadNoticeDetail();
                const submitbtn= document.querySelector(".admin-write-btn");
                const cancelbtn= document.querySelector("button[type='reset']");
                submitbtn.setAttribute("onclick", "updateNotice()");
                cancelbtn.setAttribute("onclick", "window.history.back()"); 
        }
        function loadNoticeDetail(){
            $.ajax({
                url : contextPath+"/admin/board/ajaxNoticeDetail",
                data : {"noticeNo":modifyNo},
                type : "POST",
                dataType : 'JSON',
                success : function(data) {
                    console.log(data);
                    console.log(data.noticeTitle);
                    document.querySelector("input[name='noticeTitle']").value = data.noticeTitle;
                    document.querySelector("select[name='noticeCd']").value = data.noticeCd;
                    if(document.querySelector(".note-placeholder") !=null){
                        document.querySelector(".note-placeholder").innerText=""; 
                    }
                    document.querySelector(".note-editable").innerHTML = data.content; 
                    document.querySelector("textarea[name='editordata']").innerHTML = data.content; 
                }
            })
        }
        function updateNotice(){
            if(document.querySelector("input[name='noticeTitle']").value.trim().length==0){
                alert("제목을 입력해주세요!");
                document.querySelector("input[name='noticeTitle']").focus();
                return;
            }
            const form = $("#writerForm"); 
            const formData = new FormData(form[0]);
            $.ajax({
                url : contextPath+"/admin/board/noticeUpdate",
                type : "POST",
                data : formData,
                contentType: false,
                processData: false,
                cache: false,
                success : function(result){
                    console.log("결과: " + result);
                    alert("수정 성공!");
                    url = contextPath + '/notice/view?noticeNo=' + modifyNo;
                    window.open(url, '등록클래스상품', ''+result); 
                    location.href='noticeManage';
                },
                error: function(result){
                    alert("오류 발생");
                }
            })
        }
        //-----------------------------------------------------------------//
        //9. 상품 관리
        function modifyProduct(){
            contentbox.innerHTML="";
            //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"상품 관리"+"</h2>"

            const searchDiv = document.createElement("div");
            searchDiv.setAttribute("class", "oneLine search-review");
            searchDiv.innerHTML="<select name='productCate'>"+
            "<option value='0' selected>All</option>"+
            "<option value='1'>스토어 상품</option>"+
            "<option value='2'>구독 상품</option>"+
            "<option value='3'>클래스 상품</option>"+
            "<option value='4'>추가옵션 상품</option>"+
            "<input type='text' class='inputboxs'><button class='opencal' id='searchProduct'>검색</button>";

            const SearchResult = document.createElement("ul");
            SearchResult.setAttribute("class", "ResultLine");
            contentbox.append(funcName,searchDiv,SearchResult);
            enterfunc();
            if(firstdone){
                cate = document.querySelector("select[name='productCate']").value;
                search = document.querySelector(".inputboxs").value;
                cp=1;
                firstdone=false;
            }
            document.getElementById("searchProduct").addEventListener("click", function(){
                cate = document.querySelector("select[name='productCate']").value;
                search = document.querySelector(".inputboxs").value;
                cp=1;
                searchProduct();
            })
        }
        /* 상품리스트조회 ajax */
        function searchProduct(){
            let httpRequest = new XMLHttpRequest();
            const reqJson  = {
                "search": search,
                "cate" : cate,
                "cp" : cp
            }
            httpRequest.open("POST", contextPath+'/admin/board/productselect' , true);
            httpRequest.responseType = "json";
            httpRequest.setRequestHeader("Content-Type", "application/json");
            httpRequest.send(JSON.stringify(reqJson));
            httpRequest.onreadystatechange = ()=>{
                if(httpRequest.readyState === XMLHttpRequest.DONE){
                    if(httpRequest.status ===200){
                        const result = httpRequest.response;
                        const productList = JSON.parse(result.productList);
                        const page = JSON.parse(result.pagination);
                        const ul = document.querySelector(".ResultLine");
                        ul.innerHTML=
                        "<li class='resultTitle oneMemberResult'>"+
                        "<span class='oneMemberInfo numberformat'>상품 번호</span>"+
                        "<span class='oneMemberInfo'>카테고리</span>"+
                        "<span class='oneMemberInfo notice-title'>이름</span>"+
                        "<span class='oneMemberInfo'>가격</span>"+
                        "<span class='oneMemberInfo'>등록일</span>"+
                        "<span class='oneMemberInfo'>수정하기</span>"+
                        "</li>";
                        if(productList.length ==0){
                            console.log("결과없음");
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            li.innerHTML="<div style='font-size:20px;'>조회되는 결과가 없습니다</div>";
                            ul.append(li);
                            return;
                        }
                        for(product of productList){
                            const li = document.createElement("li");
                            li.className = "oneMemberResult";
                            if(product.writecate == 2){
                                if(product.productNo == 1437){
                                    titleAtag="<a target='_blank' href="+contextPath+"/subscribe/subBread/>"+
                                    product.title+"</a>"
                                }
                                else{
                                    titleAtag="<a target='_blank' href="+contextPath+"/subscribe/subCoffee/>"+
                                    product.title+"</a>"
                                }
                            }
                            else if(product.writecate == 3){
                                titleAtag="<a target='_blank' href="+contextPath+"/onedayclass/view/"+product.productNo+">"+
                                product.title+"</a>"
                            }
                            else if(product.writecate == 1){
                                titleAtag="<a target='_blank' href="+contextPath+"/store/info/"+product.productNo+">"+
                                product.title+"</a>"
                            }
                            else{
                                titleAtag = "<a>"+product.title+"</a>";
                            }
                            const inner =
                            "<span class='oneMemberInfo numberformat'>"+product.productNo+"</span>"+
                            "<span class='oneMemberInfo'>"+product.cateName+"</span>"+
                            "<span class='oneMemberInfo notice-title'>"+titleAtag+"</span>"+
                            "<span class='oneMemberInfo'>"+product.price+"</span>"+
                            "<span class='oneMemberInfo'>"+product.createDate+"</span>"+
                            "<input type='hidden' value='"+product.writecate+"'>"+
                            "<span class='oneMemberInfo'><a href='modifyProduct' class='modifyProductBtn'>수정하기</a></span>";
                            li.innerHTML = inner;
                            ul.append(li);
                        }
                        cate = result.cate;
                        search = result.search;
                        ul.append(makePagination(page));
                        addPageFunc(searchProduct, page,cate,search);
                        const allModifyBtn = document.querySelectorAll(".modifyProductBtn");
                        allModifyBtn.forEach(
                            function(Btn){
                                Btn.addEventListener("click", e=>{
                                    modifyNo = Btn.parentElement.parentElement.firstElementChild.innerText;
                                    productcate = Btn.parentElement.previousElementSibling.value;
                                    if(!e.target.matches('.modifyProductBtn')) 
                                    {
                                        console.log("안맞음");
                                        return;
                                    }
                                    e.preventDefault();
                                    const path = e.target.getAttribute('href');
                                    window.history.pushState({}, null, path);
                                    render(routepath+path);
                                });
                            }
                        )
                    }
                    else{
                        //실패
                    }
                }
            }
        }

        function makeClassModify(){
            commonWriter('클래스 상품 수정'); makeClassPWritePage();
            const reload = document.createElement("button");
            reload.setAttribute("type", "button");
            reload.setAttribute("onclick","makeClassModify();loadProductDetail();");
            reload.innerText = "다시불러오기";
            document.querySelector("#writerForm").prepend(reload);
            doplusUpdateBtn();
            /* 카테고리에 따라 상품 정보를 불러온다 */
            loadProductDetail();
        }
        function makeStoreModify(){
            commonWriter('일반 상품 수정'); makeNormalPWritePage();
            const reload = document.createElement("button");
            reload.type="button";
            reload.innerText = "다시불러오기";
            reload.setAttribute("onclick","makeStoreModify();loadProductDetail();");
            document.querySelector("#writerForm").prepend(reload);
            doplusUpdateBtn();
            /* 카테고리에 따라 상품 정보를 불러온다 */
            loadProductDetail();
        }
        function makeSubsModify(){
            commonWriter('구독 상품 수정'); makeSubscribePWritePage();
            const reload = document.createElement("button");
            reload.type="button";
            reload.innerText = "다시불러오기";
            reload.setAttribute("onclick","makeSubsModify();loadProductDetail();");
            document.querySelector("#writerForm").prepend(reload);
            doplusUpdateBtn();
            /* 카테고리에 따라 상품 정보를 불러온다 */
            loadProductDetail();
        }
        function doplusUpdateBtn(){
            const goback = document.createElement("button");
            goback.setAttribute("type", "button");
            goback.setAttribute("onclick","window.history.back()");
            goback.innerText = "목록으로 돌아가기";
            const modifyNoInput = document.createElement("input");
            modifyNoInput.name = "productNo";
            modifyNoInput.type="hidden";
            modifyNoInput.value = modifyNo;
            const modifyNoInput2 = document.createElement("input");
            modifyNoInput2.name = "productcate";
            modifyNoInput2.type="hidden";
            modifyNoInput2.value = productcate;
            const imgCheckInput = document.createElement("input");
            imgCheckInput.name = "currentImageCheck";
            imgCheckInput.type="hidden";
            const imgCheckInput2 = document.createElement("input");
            imgCheckInput2.name = "afterImageCheck";
            imgCheckInput2.type="hidden";
            document.querySelector("#writerForm").prepend(goback,modifyNoInput,modifyNoInput2,imgCheckInput,imgCheckInput2);
            /* 버튼수정 */
            const submitbtn= document.querySelector(".admin-write-btn");
            const cancelbtn= document.querySelector("button[type='reset']");
            submitbtn.setAttribute("onclick", "updateProduct()");
            cancelbtn.setAttribute("onclick", "window.history.back()"); 
            validateVarInitial();
            for(key in commonWriteCheckObj){ commonWriteCheckObj[key] = true }
            for(key in nomalChekcObj){ nomalChekcObj[key] = true }
            for(key in subsCheckObj){ subsCheckObj[key] = true }
            for(key in classCheckObj){ classCheckObj[key] = true }
        }

        function loadProductDetail(){
            let httpRequest = new XMLHttpRequest();
            const reqJson  = {
                "productNo":modifyNo , "productcate" : productcate
            }
            httpRequest.open("POST", contextPath+'/admin/board/ajaxProductDetail' , true);
            httpRequest.responseType = "json";
            httpRequest.setRequestHeader("Content-Type", "application/json");
            httpRequest.send(JSON.stringify(reqJson));
            httpRequest.onreadystatechange = ()=>{
                if(httpRequest.readyState === XMLHttpRequest.DONE){
                    if(httpRequest.status ===200){
                        const resultData = httpRequest.response;
                        /* 이미지처리 */
                        const showImgs = document.querySelectorAll(".images>img");
                        if(resultData==null){
                            alert("상품 오류, DB 수정 필요");
                            window.history.back();
                            return;
                        }
                        let nowimglist='';
                        /* 이미지처리 */
                        for(let i = 0 ; i<resultData.classImgList.length ; i++){
                            showImgs[resultData.classImgList[i].productImgLevel].setAttribute("src", contextPath+resultData.classImgList[i].productImgPath+resultData.classImgList[i].productImgName)
                            filecheck[resultData.classImgList[i].productImgLevel] = 1;
                            showImgs[resultData.classImgList[i].productImgLevel].style.display ="block";
                        } 
                        for(ch of filecheck){ nowimglist+= (''+ch);}
                        document.getElementsByName("currentImageCheck")[0].value=nowimglist;
                        /* 일반상품 */
                        if(productcate==1){
                            document.getElementsByName("title")[0].value = resultData.storeName;
                            document.getElementsByName("detailcontents")[0].value = resultData.memo;
                            document.getElementsByName("price")[0].value = resultData.price;
                            document.getElementsByName("stock")[0].value = resultData.stock;
                            document.getElementsByName("storecate")[0].value = resultData.storecate;
                            if(resultData.discountStart !=null){
                                document.getElementsByName("discountyn")[1].click();
                                setTimeout(function(){
                                    document.getElementsByName("discountStart")[0].value = resultData.discountStart;
                                    document.getElementsByName("discountEnd")[0].value = resultData.discountEnd;
                                    document.getElementsByName("discountPer")[0].value = resultData.discountPer;
                                },200);
                            }
                            else document.getElementsByName("discountyn")[0].click();
                            if(document.querySelector(".note-placeholder") !=null){
                                document.querySelector(".note-placeholder").innerText=""; 
                            }
                            document.querySelector(".note-editable").innerHTML = resultData.storeExp; 
                            document.querySelector("textarea[name='editordata']").innerHTML = resultData.storeExp; 
                        }
                        /* 구독상품 */
                        else if(productcate==2){
                            document.getElementsByName("title")[0].value = resultData.title;
                            document.getElementsByName("price")[0].value = resultData.price;
                            if(document.querySelector(".note-placeholder") !=null){
                                document.querySelector(".note-placeholder").innerText=""; 
                            }
                            document.querySelector(".note-editable").innerHTML = resultData.storeExp; 
                            document.querySelector("textarea[name='editordata']").innerHTML = resultData.contents; 
                        }
                        /* 클래스상품 */
                        else if(productcate==3){
                            document.getElementsByName("title")[0].value = resultData.title;
                            document.getElementsByName("price")[0].value = resultData.price;
                            document.getElementsByName("place")[0].value = resultData.placeCd;
                            document.getElementsByName("people")[0].value = resultData.classMaxPpl;
                            document.getElementsByName("classdate")[0].value = resultData.classDt;
                            const timeinput = document.getElementsByClassName("timeinput");
                            const times= resultData.classtime;
                            timeinput[0].value= parseInt(times.substring(0,2));
                            timeinput[1].value= parseInt(times.substring(3,5));
                            timeinput[2].value= parseInt(times.substring(7,10));
                            timeinput[3].value= parseInt(times.substring(11,13));
                            if(document.querySelector(".note-placeholder") !=null){
                                document.querySelector(".note-placeholder").innerText=""; 
                            }
                            document.querySelector(".note-editable").innerHTML = resultData.contents; 
                            document.querySelector("textarea[name='editordata']").innerHTML = resultData.contents; 
                        }
                    }
                    else{
                        alert("error발생");
                    }
                }
            }
        }
        function updateProduct(){
            const cate = productcate;
            let tempdata = document.getElementsByName("currentImageCheck")[0].value;
            let inputfiles = document.getElementsByName("images");
            let deletepart ='';
            //기존 이미지 존재 배열
            for(let i=0 ; i<4 ; i++){
                parseInt(tempdata.substring(i,i+1))==1 ;
                //제출한 이미지 배열
                //-1(새로추가) 아니면 전부 삭제후 새로 추가
                let tempbool = parseInt(tempdata.substring(i,i+1))-filecheck[i];
                if(tempbool==1){ deletepart += '1';} // 존재 -> 없음 :  삭제 delete
                else if(tempbool==0 && inputfiles[i].files.length ==0) { deletepart += '0';}
                //존재 -> 존재 / 없음 -> 없음 + input태그 값 없음 : 현상유지
                else if(tempbool==0 && inputfiles[i].files.length ==1) { deletepart += '1';}
                //존재 -> 존재 / 없음 -> 없음 + input태그 값 있음 : 삭제 후 insert
                else{ deletepart += '0';} //
            }

            document.getElementsByName("afterImageCheck")[0].value=deletepart;
            if(!validate(parseInt(cate))){
                return;
            };
            const form = $("#writerForm");
            const formData = new FormData(form[0]);
            $.ajax({
                url : contextPath+"/admin/board/updateProduct",
                type : "POST",
                data : formData, 
                contentType: false,
                processData: false,
                cache: false,
                success : function(result){
                    alert("등록 성공!");
                    
                    let url = "";
                    switch(cate){
                        case '1' : 
                        window.history.back();
                        url = contextPath + '/store/info/' + result;
                        window.open(url, '등록일반상품', ''+result); 
                        break;
                        case '2' : 
                        window.history.back(); break;
                        case '3' : 
                        window.history.back();
                        url = contextPath + '/onedayclass/view/' + result;
                        window.open(url, '등록클래스상품', ''+result);
                        break;
                    }
                },
                error: function(result){
                    alert(result);
                }
            })
        }
        //-----------------------------------------------------------------//
        /* 페이지네이션 생성 */
        function makePagination(page){
            const pageli = document.createElement("li");
            pageli.className = "pagination";
            if(page.startPage !=1){
                const prev = document.createElement("span");
                prev.innerText = '<';
                prev.className = "goPrevPage";
                const firstspan = document.createElement("span");
                firstspan.innerText = '<<';
                firstspan.className = "goFirstPage";
                pageli.append(firstspan,prev);
            }
            for(let i = 0; i < page.pageSize ; i++){
                if(page.maxPage < page.startPage + i){
                    break;
                } 
                const pagespan = document.createElement("span");
                pagespan.innerText = page.startPage + i;
                pageli.append(pagespan);
                if(page.startPage + i == page.currentPage){
                    pagespan.setAttribute("id","currPage");
                    continue;
                }
                else{
                    pagespan.className="goThisPage";
                }
            }

            if(page.endPage != page.maxPage){
                const next = document.createElement("span");
                next.innerText = '>';
                next.className = "goNextPage";
                const endspan = document.createElement("span");
                endspan.innerText = '>>';
                endspan.className = "goLastPage";
                pageli.append(next,endspan);
            }
            return pageli;
        }
        function addPageFunc(func, page,noticecateN,searchN){
            cate = noticecateN;
            search = searchN;
            if(document.querySelector(".goPrevPage") != null){
                document.querySelector(".goPrevPage").addEventListener("click", function(){
                    cp= page.prevPage
                    func();
                })
                document.querySelector(".goFirstPage").addEventListener("click", function(){
                    cp= 1;
                    func();
                })
            }
            if(document.querySelector(".goNextPage") != null){
                document.querySelector(".goNextPage").addEventListener("click", function(){
                    cp= page.nextPage
                    func();
                })
                document.querySelector(".goLastPage").addEventListener("click", function(){
                    cp= page.maxPage;
                    func();
                })
            }
            if(document.querySelector(".goThisPage") != null){
                const pages = document.querySelectorAll(".goThisPage");
                pages.forEach(
                    function(Element){
                        Element.addEventListener("click", function(){
                            cp= Element.innerText;
                            func();
                        })
                    }
                )
            }
        }

        //-----------------------------------------------------------------//
        /* 문자테스트 */
        function sendMessage(){
            alert("막누르면 안됨")
            $.ajax({
                url : contextPath+"/admin/board/sendMessage",
                type : "POST",
                data : {}, 
                dataType: "json",
                success : function(result){
                    console.log("성공 "+result);
                },
                error : function(status){
                    console.log("오류 "+status);
                }
            })
        }

        //-----------------------------------------------------------------//
        /* 쿠폰발급 */
        function couponMake(){
            contentbox.innerHTML="";

            const adminwriteform = document.createElement("form")
            adminwriteform.setAttribute("id","writerForm");
            adminwriteform.setAttribute("method","POST");

            //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"쿠폰 프로모션 생성 메뉴"+"</h2>"
            //제목
            const title = document.createElement("div");
            title.setAttribute("class", "oneLine");
            title.innerHTML="<label class='labels'>제목</label><input type='text' name='couponName'>";

            const couponDate = document.createElement("div");
            couponDate.setAttribute("class", "oneLine");
            couponDate.innerHTML="<label class='labels'>만료날짜</label>"+
            "<input type='text' id='expiredate' name='expireDate' readonly placeholder='만료일을 선택해주세요'>"+
            "<button type='button' class='opencal selectdate'>만료일 설정</button>";
            
            const discountPer = document.createElement("div");
            discountPer.setAttribute("class", "oneLine");
            discountPer.innerHTML="<label class='labels'>할인율</label><input type='number' name='discountPer'>";
            
            //개수제한
            const couponLimit = document.createElement("div");
            couponLimit.setAttribute("class", "oneLine");
            couponLimit.innerHTML="<label class='labels'>개수제한</label><input type='number' name='couponLimit'>";

            const hiddenadminNo = document.createElement("input");
            hiddenadminNo.type= "hidden";
            hiddenadminNo.value= adminNo ;
            hiddenadminNo.name = "adminNo";
            adminwriteform.append(funcName,title,discountPer,couponLimit,couponLimit,couponDate,hiddenadminNo,subcanBTN());
            contentbox.append(adminwriteform);

            document.querySelector(".admin-write-btn").setAttribute("onclick","makingCoupon();");

            calmodal();
        }
            function validateCoupon(){
                const test1=    document.querySelector("input[name='couponName']").value.trim().length;
                const test2=    document.querySelector("input[name='expireDate']").value.trim().length;
                const test3=    document.querySelector("input[name='discountPer']").value.trim().length;
                const test4=    document.querySelector("input[name='couponLimit']").value.trim().length;
                if(test1==0 || test2==0 || test3 ==0 || test4 ==0){
                    alert("쿠폰 정보를 입력해주세요!")
                    return false;
                }
                else return true;
            }
            function makingCoupon(){
                const form = $("#writerForm"); 
                const formData = new FormData(form[0]);

                $.ajax({
                    url : contextPath+"/admin/board/makingCoupon",
                    data : formData,
                    contentType: false,
                    processData: false,
                    cache: false,
                    type:"post",
                    success : function(result){
                        if(result>0){

                        }
                    },
                    error:function(request, status, error){
                        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    }
                })
            }



        //----------------------------------------------------------------------------------------
        /* 구독옵션 */
        function subsOptionManage(){
            contentbox.innerHTML="";
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"구독상품 옵션 현황"+"</h2>";

            const subsType = document.createElement("div");
            subsType.setAttribute("class", "oneLine");
            subsType.innerHTML="<label class='labels'>구독 종류</label>"+
            "<select name='subsType'>"+
            "<option value='1437'>빵 구독</option>" +
            "<option value='1438'>빵 커피 구독</option>"+
            "</select>";

            const SearchResult = document.createElement("ul");
            SearchResult.setAttribute("class", "ResultLine");

            contentbox.append(funcName,subsType,SearchResult);
            document.querySelector("select[name='subsType']").addEventListener("change",function(){
                selectSubsOption(this.value);
            });
            selectSubsOption(1437);
        }

        function selectSubsOption(val){
            $.ajax({
                url : contextPath + "/admin/board/selectSubsOption",
                type : "post",
                data:{
                    "productNo" : val
                },
                dataType : "json",
                success : function(result){
                    const ul = document.querySelector(".ResultLine");
                    ul.innerHTML =
                    "<li class='resultTitle oneMemberResult'>"+
                    "<span class='oneMemberInfo'>해당 구독상품명</span>"+
                    "<span class='oneMemberInfo numberformat'>옵션 번호</span>"+
                    "<span class='oneMemberInfo notice-title'>옵션명</span>"+
                    "<span class='oneMemberInfo '>옵션코드</span>"+
                    "<span class='oneMemberInfo'>변경</span>"+
                    "<span class='oneMemberInfo'>추가/삭제</span>"+
                    "</li>";
                    for(let i = 0 ; i<result.length ; i++){
                        const li = document.createElement("li");
                        li.className = "oneMemberResult";
                        li.innerHTML=
                        "<span class='oneMemberInfo'>"+ result[i].productName +"</span>"+
                        "<span class='oneMemberInfo  numberformat'>"+ result[i].suboptionNo +"</span>"+
                        "<span class='oneMemberInfo notice-title'>"+ "<input type='text' readonly value='"+result[i].subOptionName +"' style='border:none; text-align:center; background:none'></span>"+
                        "<span class='oneMemberInfo'>"+ result[i].subOptionCd +"</span>"+
                        "<input type='hidden' value='"+result[i].productNo+"'>"+
                        "<span class='oneMemberInfo'>"+ "<button onclick='changeSubsOption(this)'>변경하기</button>" +"</span>"+
                        "<span class='oneMemberInfo'>"+ "<button onclick='deleteSubsOption(this)'>삭제하기</button>" +"</span>"
                        ul.append(li);
                    }
                    ul.innerHTML += 
                    "<li class='oneMemberResult addOptionLine'>"+
                    "<span class='oneMemberInfo'><input type='text' readonly value='"+result[0].productName+"' style='border:none; text-align:center'></span>"+
                    "<span class='oneMemberInfo numberformat'>(자동입력)</span>"+
                    "<span class='oneMemberInfo notice-title'><input type='text'></span>"+
                    "<span class='oneMemberInfo '><select><option value='31'>31</option><option value='32'>32</option><option value='33'>33</option><option value='34'>34</option><option value='35'>35</option></select></span>"+
                    "<input type='hidden' value='"+result[0].productNo+"'>"+
                    "<span class='oneMemberInfo'>null</span>"+
                    "<span class='oneMemberInfo'><button onclick='addSubsOption(this)'>추가하기</button></span>"+
                    "</li>";
                },
                error : function(){

                }
            })  
        }
        function addSubsOption(btn){
            alert("허가가 필요합니다");
            return;
/* 
            if(confirm("추가하시겠습니까?")){
                let SubsProductNo = btn.parentElement.previousElementSibling.previousElementSibling;
                let subOptionName = SubsProductNo.previousElementSibling.previousElementSibling.children[0].value;
                let subsOprionCd = SubsProductNo.previousElementSibling.children[0].value;
                console.log(SubsProductNo);
                console.log(subOptionName);
                console.log(subsOprionCd);
                $.ajax({
                    url : contextPath+"/admin/board/addSubsOption",
                    type : "POST",
                    data : {
                        "productNo" : SubsProductNo.value,
                        "subOptionName" : subOptionName,
                        "subOptionCd" : subsOprionCd
                        } ,
                    success : function(result){
                        if(result>0){
                            selectSubsOption(SubsProductNo.value)
                        }
                    },
                    error:function(request, status, error){
                        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    }
                }) 
            }
            else return; */
        }
        function deleteSubsOption(btn){
            if(confirm("삭제하시겠습니까?")){
                const suboptionNo = btn.parentElement.parentElement.firstElementChild.nextElementSibling.innerText;
                const SubsProductNo = btn.parentElement.previousElementSibling.previousElementSibling.value;
                console.log(suboptionNo)
                $.ajax({
                    url : contextPath+"/admin/board/deleteSubsOption",
                    type : "POST",
                    data : {
                        "suboptionNo" : suboptionNo
                        } ,
                    success : function(result){
                        if(result>0){
                            selectSubsOption(SubsProductNo)
                        }
                    },
                    error : function(){
                        alert("옵션 삭제 중 에러발생")
                    }
                }) 
            }
            else return;
        }
        function changeSubsOption(btn){
            console.log(btn);
            const suboptionNo = btn.parentElement.parentElement.firstElementChild.nextElementSibling.innerText;
            const subOptionName = btn.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.firstElementChild
            if(btn.className==""){
                subOptionName.readOnly= false;
                subOptionName.style.background = "#A78A6C";
                subOptionName.style.color = "white";
                subOptionName.focus();
                btn.classList.toggle("doingUpdateOption");
                console.log(subOptionName);
            }
            else {
                $.ajax({
                    url : contextPath+"/admin/board/changeSubsOption",
                    type : "POST",
                    data : {
                        "suboptionNo" : suboptionNo,
                        "subOptionName"    :subOptionName.value
                        } ,
                    success : function(result){
                        if(result>0){
                        subOptionName.readOnly= true;
                        subOptionName.style.background = "none";
                        subOptionName.style.color = "black";
                        btn.classList.toggle("doingUpdateOption");
                        console.log(subOptionName);
                        }
                    },
                    error : function(){
                        alert("옵션 변경 중 에러발생")
                    }
                }) 
            }
        }