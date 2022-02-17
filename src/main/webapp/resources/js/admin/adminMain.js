const contentbox = document.getElementById("adminPCont");
const navbox= document.getElementsByClassName("one-admin-func");
for(element of navbox){
    element.addEventListener("click", function(){
        this.children[0].classList.toggle("shownav");
    })
}
const clock = document.getElementById('clock');
function getTime(){
    const time = new Date();
    const hour = time.getHours();
    const ampm = hour<12? '오전 ' : '오후 ';
    const minutes = time.getMinutes();
    const seconds = time.getSeconds();
    clock.innerHTML = ampm+`${hour<10 ? `0${hour}`:hour}:${minutes<10 ? `0${minutes}`:minutes}:${seconds<10 ? `0${seconds}`:seconds}`
}
function dodouble(){
    $.ajax({
        url : contextPath+"/admin/board/"+ "dodouble",
        type : "post",
        data : {
            
        },
        success : function(){
            alert("done");
        },
        error : function (){

        }

    })
}
setInterval(getTime, 1000);
        //check 변수
        let firstdone = true;
        let Reviewtoggle = false;
        let tempRNo =0 ;
        let selectMonth = 0;
        let commonWriteCheckObj={};
        let nomalChekcObj={};
        let subsCheckObj={};
        let filecheck=[0 , 0 , 0 , 0];
        let discountset=false;
        let saveDateBtnthis;
        let tempNo =1437;
        let classCheckObj={};
        /* 페이지네이션 변수 */
        let cp =1;
        let cate;
        let search ='';
        let productcate;
        let modifyNo = 0;

        
        //게시글 validation
        function validate(options){
            for( key  in commonWriteCheckObj){
                if( !commonWriteCheckObj[key] ){
                    let message;
                    switch(key){
                        case "title" : message = "제목을 입력해주세요"; break;
                        case "price" : message = "가격을 입력해주세요"; break;
                        case "images" : message = "썸네일을 넣어주세요"; break;
                    }
                    alert(message);
                    document.querySelector("input[name="+key+"]").focus();
                    return false;
                }
            }
            switch(options){
                case 1 : 
                for( key  in nomalChekcObj ){
                    if( !nomalChekcObj[key] ){
                        let message;
                        switch(key){
                            case "stock" : message = "재고를 입력해주세요"; break;
                            case "detailcontents" : message = "상세설명을 입력해주세요"; break;
                            case "discountyn" : message = "할인여부를 입력해주세요"; break;
                        }
                        alert(message);
                        document.querySelector("input[name="+key+"]").focus();
                        return false;
                    }}
                    if(discountset){
                        return validatediscountnum();} break;
                case 2 : 
                for( key  in subsCheckObj ){
                    if( !subsCheckObj[key] ){
                        let message;
                        switch(key){
                            case "stock" : message = "재고를 입력해주세요"; break;
                        }
                        alert(message);
                        document.querySelector("input[name="+key+"]").focus();
                        return false;
                }} break;

                case 3 : 
                for( key  in classCheckObj ){
                    if( !classCheckObj[key] ){
                        let message;
                        switch(key){
                            case "people" : message = "인원을 입력해주세요"; break;
                            case "classtime" : message = "시작과 종료 시간을 올바르게 입력해주세요"; break;
                            case "classdate" : message = "수강일을 입력해주세요"; break;
                        }
                        alert(message);
                        if(key=='classtime'){
                            document.querySelector("select[name='starthour']").focus();
                        }
                        else{
                            document.querySelector("input[name="+key+"]").focus();
                        }
                        return false;
                }} break;
            }
            return true;
        }
                //게시글 input check 변수 initial
                function validateVarInitial(){
                    commonWriteCheckObj = {
                        "title" : false,
                        "price" : false,
                        "images" : false
                    }
                    nomalChekcObj = {
                        "stock" : false,
                        "detailcontents" : false,
                        "discountyn" : false
                    } 
                    subsCheckObj = {
                    }
                    classCheckObj = {
                        "people" : false,
                        "classtime":false,
                        "classdate" : false
                    } 
                    filecheck = [0 , 0 , 0 , 0];
                    discountset = false;
                    selectMonth = 0;
                }
        //1. 공지사항 작성------------------------------------------
        //카테고리 / 제목 / 내용
        function noticeboardWriter(writename){
            //초기화
            validateVarInitial();
            contentbox.innerHTML="";
            //폼태그
            const adminwriteform = document.createElement("form")
            adminwriteform.setAttribute("id","writerForm");
            adminwriteform.setAttribute("method","POST");

            //폼태그 append
            contentbox.append(adminwriteform)

             //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+writename+"</h2>"
            
             //카테고리
            const noticecate = document.createElement("div");
            noticecate.setAttribute("class", "oneLine noticecate");
            noticecate.innerHTML="<label class='labels'>카테고리</label>"+
            "<select name='noticeCd'>"+
            "<option value='2'>공지사항</option>" +
            "<option value='1'>프로모션</option>"+
            "<option value='3'>이벤트</option>" +
            "</select>";
            //제목
            const title = document.createElement("div");
            title.setAttribute("class", "oneLine");
            title.innerHTML="<label class='labels'>제목</label><input type='text' name='noticeTitle'>";
            const loginAdmin = document.createElement("div");
            loginAdmin.innerHTML="<input type='hidden' name='loginAdmin' value='"+adminNo+"'>";

            //썸머노트
            const div4 = document.createElement("div");
            div4.setAttribute("class", "oneLine contnote");
            div4.innerHTML="<label class='labels'>내용</label><textarea id='summernote' name='editordata'></textarea>";

            $("#writerForm").append(funcName,title,loginAdmin,noticecate,div4,subcanBTN());
            notesummer();
            //썸머노트 실행
            const submit = document.querySelector(".admin-write-btn");
            submit.removeAttribute("onclick")
            submit.setAttribute("onclick", "submitNotice()");
            document.querySelector("select[name='noticeCd']").addEventListener("change",function(){
                if(this.value == 1){
                    const addwritecoupon = document.createElement("div");
                    addwritecoupon.setAttribute("class", "oneLine addcoupons couponbtn");
                    addwritecoupon.innerHTML="<label class='labels'>&nbsp;</label><button type='button' class='opencal' onclick='addCouponSelector();couponbtnremove();'>쿠폰추가</button>" ;
                    document.querySelector(".noticecate").after(addwritecoupon);
                    calmodal();
                }
                else{
                    $(".addcoupons").remove();
                }
            })

        }
        function addCouponSelector(){
            const parent = document.querySelector(".contnote");
            const couponName = document.createElement("div");
            couponName.setAttribute("class", "oneLine addcoupons");
            couponName.innerHTML="<label class='labels'>쿠폰명</label><input type='text' name='couponName'>"+"<button type='button' class='opencal removecoupon' onclick='removecoupon(this);'>-쿠폰삭제</button>";

            const couponDate = document.createElement("div");
            couponDate.setAttribute("class", "oneLine addcoupons");
            couponDate.innerHTML="<label class='labels'>만료날짜</label>"+
            "<input type='text' id='expiredate' name='expireDate' readonly placeholder='만료일을 선택해주세요'>"+
            "<button type='button' class='opencal selectdate'>만료일 설정</button>";
            
            const discountPer = document.createElement("div");
            discountPer.setAttribute("class", "oneLine addcoupons");
            discountPer.innerHTML="<label class='labels'>할인율</label><input type='number' name='discountPer'>";
            //개수제한
            const couponLimit = document.createElement("div");
            couponLimit.setAttribute("class", "oneLine addcoupons");
            couponLimit.innerHTML="<label class='labels'>개수제한</label><input type='number' name='couponLimit'>";
            const addwritecoupon = document.createElement("div");
            addwritecoupon.setAttribute("class", "oneLine addcoupons couponbtn");
            addwritecoupon.innerHTML="<label class='labels'>&nbsp;</label><button type='button' class='opencal' onclick='addCouponSelector();couponbtnremove();'>+쿠폰추가</button>" 
            
            parent.before(couponName,couponDate,discountPer,couponLimit,addwritecoupon);
            calmodal();
        }
        function couponbtnremove(){
            $(".couponbtn")[0].remove();
        }
        function removecoupon(btn){
            btn.parentElement.nextElementSibling.remove();
            btn.parentElement.nextElementSibling.remove();
            btn.parentElement.nextElementSibling.remove();
            btn.parentElement.remove();
        }
        //공지사항 등록
        function submitNotice(){
            if(document.querySelector("input[name='noticeTitle']").value.trim().length==0){
                alert("제목을 입력해주세요!");
                document.querySelector("input[name='noticeTitle']").focus();
                return;
            }
            const form = $("#writerForm"); 
            const formData = new FormData(form[0]);
            const cate = document.querySelector("select[name='noticeCd']").value;
            
            if(cate==1 && !validateCoupon()){
                return;
            }   
                $.ajax({
                    url : contextPath+"/admin/board/noticeWrite",
                    type : "POST",
                    data : formData,
                    contentType: false,
                    processData: false,
                    cache: false,
                    success : function(result){
                        alert("등록 성공!");
                        const goUrl = contextPath + '/notice/view?noticeNo='+result;
                        window.open(goUrl, '공지사항', ''+result); 
                        noticeboardWriter('공지사항 등록');

                    },
                    error: function(result){
                        alert("오류 발생");
                    }
                })
        }
        function validateCoupon(){
            const couponName = document.querySelectorAll("input[name='couponName'");
            const expireDate = document.querySelectorAll("input[name='expireDate'");
            const discountPer = document.querySelectorAll("input[name='discountPer'");
            const couponLimit = document.querySelectorAll("input[name='couponLimit'");
            let validateName = true;
            couponName.forEach((each)=>{
                if(each.value.trim().length==0){
                    validateName=false;
                    return ;
                }
            })
            if(!validateName) 
            {
                alert("쿠폰이름을 입력해주세요");
                return false;
            }

            let validateexpireDate = true;
            expireDate.forEach((each)=>{
                if(each.value.trim().length==0){
                    validateexpireDate=false;
                    return;
                }
            })
            if(!validateexpireDate)             {
                alert("쿠폰 만료일을 입력해주세요");
                return false;
            }

            let validatediscountPer = true;
            discountPer.forEach((each)=>{
                if(each.value.trim().length==0){
                    validatediscountPer=false;
                    return;
                }
            })
            if(!validatediscountPer)             {
                alert("쿠폰할인율을 입력해주세요");
                return false;
            }

            let validatecouponLimit = true;
            couponLimit.forEach((each)=>{
                if(each.value.trim().length==0){
                    validatecouponLimit=false;
                    return;
                }
            })
            if(!validatecouponLimit)             {
                alert("쿠폰수를 입력해주세요");
                return false;
            }

            return true;
        }
        //-----------------------------------------------------------------//

        //4-2. 일반 상품 게시글 작성
        //사진 4장 / 제목 / 가격  / 내용 / 카테고리 / 재고 / 할인정책
        function makeNormalPWritePage(){
            //재고
            const stock = document.createElement("div");
            stock.setAttribute("class", "oneLine");
            stock.innerHTML="<label class='labels'>재고</label><input type='number' name='stock'><span class='won'>개</span>";

            //카테고리
            const storecate = document.createElement("div");
            storecate.setAttribute("class", "oneLine");
            storecate.innerHTML="<label class='labels'>카테고리</label>"+
            "<select name='storecate'>"+
            "<option value='1'>식빵</option>"+
            "<option value='2'>바게트</option>"+
            "<option value='3'>기타</option>"+
            "</select>";
            
            //할인 프로모션
            const discountPromotion = document.createElement("div");
            discountPromotion.className= "oneLine discountLine";
            discountPromotion.setAttribute("id", "discountyn");
            discountPromotion.innerHTML="<label class='labels'>할인 Y/N</label>"+
            "<label class='labels'>할인 없음<input type='radio' name='discountyn' value='none'></label>"+
            "<label class='labels'>할인 추가<input type='radio' name='discountyn' value='yes'></label>";

            //썸머노트
            const div4 = document.createElement("div");
            div4.className= "oneLine contnote";
            div4.innerHTML="<label class='labels'>내용</label><br><textarea id='summernote' name='editordata'></textarea>";
            
            //상품종류 히든인풋
            const writecate = document.createElement("input");
            writecate.setAttribute("type", "hidden");
            writecate.setAttribute("name", "writecate");
            writecate.setAttribute("value", "1");
            $("#writerForm").append(stock,storecate,discountPromotion,div4,subcanBTN(),writecate);

            //상세설명
            const detailcontents = document.createElement("div");
            detailcontents.setAttribute("class", "oneLine");
            detailcontents.innerHTML="<label class='labels'>상세설명</label><input type='text' name='detailcontents' >";
            document.querySelector("input[name='title']").parentElement.after(detailcontents);
            //썸머노트 실행
            notesummer();
            //이미지 함수 실행
            showImg();
            //할인 선택함수 실행
            discountPlus();

            document.querySelector("input[name='stock']").addEventListener("input", function(){
                if(this.value.trim().length>0) nomalChekcObj.stock = true;
                else  nomalChekcObj.stock = false;
            })
            document.querySelector("input[name='detailcontents']").addEventListener("input", function(){
                if(this.value.trim().length>0) nomalChekcObj.detailcontents = true;
                else  nomalChekcObj.detailcontents = false;
            })

        }
        //할인추가 function
        function discountPlus(){
            const discountcheck = document.querySelectorAll("input[name='discountyn']");
                for(btn of discountcheck){
                    btn.addEventListener("change", function(){
                        if(this.value == 'yes'){
                            const discountStart = document.createElement("div");
                            discountStart.setAttribute("class", "oneLine disp");
                            discountStart.innerHTML="<label class='labels'>할인 시작일</label><input type='text' readonly name='discountStart' ><button type='button' class='opencal selectdate'>날짜 선택</button><button type='button' class='opencal' onclick='todayshow()'>당일</button>";
                            
                            const discountEnd = document.createElement("div");
                            discountEnd.setAttribute("class", "oneLine disp");
                            discountEnd.innerHTML="<label class='labels'>할인 종료일</label><input type='text' readonly name='discountEnd'><button type='button' class='opencal selectdate'>날짜 선택</button>";
                            const discountPer = document.createElement("div");
                            discountPer.setAttribute("class", "oneLine disp");
                            discountPer.innerHTML="<label class='labels'>할인율(%)</label><input type='number' name='discountPer'>";
                            document.querySelector(".discountLine").after(discountStart, discountEnd,discountPer)
                            //달력모달 함수
                            calmodal();
                            discountset = true;
                        }
                        else{
                            discountset = false;
                            $(".disp").remove();
                        }
                        nomalChekcObj.discountyn = true;
                    })
                }
        }
        //-----------------------------------------------------------------//
        //당일선택
        function todayshow(){
            const date= new Date();
            let month = (date.getMonth()+1)>10?  (date.getMonth()+1) : '0'+(date.getMonth()+1);
            let day = date.getDate()>10?  date.getDate() : '0'+date.getDate();
            const today = ""+date.getFullYear() + "-" + month + "-" + day; 
            $("input[name='discountStart']").val(today);        
        }
        function validatediscountnum(){
            const startday = document.querySelector("input[name='discountStart']").value;

            const endday = document.querySelector("input[name='discountEnd']").value;

            const percent =document.querySelector("input[name='discountPer']").value;
            console.log(new Date(startday));
            console.log(new Date(endday));
            console.log(new Date(startday)>new Date(endday));
            
            if(startday.length!=0 && endday.length!=0 && percent.length!=0) {
                if(new Date(startday)>new Date(endday)){ alert("할인 기간을 확인해주세요!"); return false;}
                else return true;
            }
            else { alert("할인 정보를 입력해주세요! ");return false;}
            
        }
        //4-3. 구독 상품 게시글 작성
        //사진 1장 / 제목 / 빵 or 커피  / 가격 / 식빵 종류 
        function makeSubscribePWritePage(){
            //썸머노트
            const div4 = document.createElement("div");
            div4.setAttribute("class", "oneLine contnote");
            div4.innerHTML="<label class='labels'>내용</label><textarea id='summernote' name='editordata'></textarea>";
            const writecate = document.createElement("input");
            writecate.setAttribute("type", "hidden");
            writecate.setAttribute("name", "writecate");
            writecate.setAttribute("value", "2");
            $("#writerForm").append(div4,writecate,subcanBTN());
            //썸머노트 실행
            notesummer();
            //이미지 함수 실행
            showImg();
        }
        //-----------------------------------------------------------------//

        //4-4.클래스 상품 
        //사진 1장 / 제목 / 가격 / 지점 / 가능수강일 /  
        function makeClassPWritePage(){
            const place = document.createElement("div");
            place.setAttribute("class", "oneLine");
            place.innerHTML="<label class='labels'>클래스 지점</label>"+
            "<select name='place'>"+
            "<option value='1'>마포점</option>" +
            "<option value='2'>부천점</option>"+
            "<option value='3'>금천점</option>" +
            "<option value='4'>남양주점</option>" +
            "<option value='5'>창동점</option>" +
            "</select>";
            
            const classDate = document.createElement("div");
            classDate.setAttribute("class", "oneLine");
            classDate.innerHTML="<label class='labels'>클래스 날짜</label>"+
            "<input type='text' id='classdate' name='classdate' readonly placeholder='날짜를 선택해주세요'>"+
            "<button type='button' class='opencal selectdate'>날짜 선택</button>";
            
            //수강인원
            const people = document.createElement("div");
            people.setAttribute("class", "oneLine");
            people.innerHTML="<label class='labels'>수강 인원</label><input type='number' name='people'><span class='won'>명</span>" ;
            
            //시작 시간
            const starthour = document.createElement("div");
            starthour.setAttribute("class", "oneLine");
            starthour.innerHTML="<label class='labels'>시작 시간</label>"+
            "<select name='starthour' class='timeinput'></select><select name='startminute' class='timeinput'></select>";
            //종료 시간
            const endhour = document.createElement("div");
            endhour.setAttribute("class", "oneLine");
            endhour.innerHTML="<label class='labels'>종료 시간</label>"+
            "<select name='endhour' class='timeinput'></select><select name='endminute' class='timeinput'></select>";

            //썸머노트
            const div4 = document.createElement("div");
            div4.setAttribute("class", "oneLine contnote");
            div4.innerHTML="<label class='labels'>내용</label><textarea id='summernote' name='editordata'></textarea>";
            const writecate = document.createElement("input");
            writecate.setAttribute("type", "hidden");
            writecate.setAttribute("name", "writecate");
            writecate.setAttribute("value", "3");
            $("#writerForm").append(place,people,starthour,endhour,classDate,div4,subcanBTN(),writecate);
            //썸머노트 실행
            notesummer();
            //이미지 함수 실행
            showImg();
            //달력모달 함수
            calmodal();
            //시작시간 make
            makehourminute();
            document.querySelector("input[name='people']").addEventListener("input", function(){
                if(this.value.trim().length>0) classCheckObj.people = true;
                else  classCheckObj.people = false;
            })
            document.querySelector("input[name='classdate']").addEventListener("change", function(){
                if(this.value.trim().length>0) classCheckObj.classdate = true;
                else  classCheckObj.classdate = false;
            })
            const timeinput= document.getElementsByClassName("timeinput");
            for(TI of timeinput){
                TI.addEventListener("change",function(){
                    const starthour = parseInt(document.querySelector("select[name='starthour']").value);
                    const endhour = parseInt(document.querySelector("select[name='endhour']").value);
                    const startminute = parseInt(document.querySelector("select[name='startminute']").value);
                    const endminute = parseInt(document.querySelector("select[name='endminute']").value);
                    if(starthour < endhour) {classCheckObj.classtime = true; }
                    else if(starthour===endhour) {
                        if(startminute<endminute) classCheckObj.classtime = true;
                        else classCheckObj.classtime = false;
                    }
                    else classCheckObj.classtime = false;
                })
            }
        }
        //-----------------------------------------------------------------//
        //4 게시글작성 common  일반 + 구독 + 클래스 공통부분
        function commonWriter(writename){
            //초기화
            validateVarInitial();
            contentbox.innerHTML="";
            //폼태그
            const adminwriteform = document.createElement("form")
            adminwriteform.setAttribute("id","writerForm");
            adminwriteform.setAttribute("enctype","multipart/form-data");
            adminwriteform.setAttribute("method","POST");
            //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+writename+"</h2>"
            //제목
            const title = document.createElement("div");
            title.setAttribute("class", "oneLine");
            title.innerHTML="<label class='labels'>상품명</label><input type='text' name='title'>";

            //가격
            const price = document.createElement("div");
            price.setAttribute("class", "oneLine");
            price.innerHTML="<label class='labels'>가격</label><input type='number' name='price'><span class='won'>원</span>";

            const div3 = document.createElement("div");
            div3.setAttribute("class", "oneLine imgline");
            div3.innerHTML="<label class='labels'>썸네일<br> & 이미지</label><div class='images'><img></div>"+ "<div class='thumbdivider'></div>"+
            "<div class='images'><img></div>"+"<div class='images'><img></div>"+"<div class='images'><img></div>"+
            "<input type='file' name='images' class='imagesinput'>" +
            "<input type='file' name='images' class='imagesinput'>" +
            "<input type='file' name='images' class='imagesinput'>" +
            "<input type='file' name='images' class='imagesinput'>";

            adminwriteform.append(funcName,title,price,div3);
            contentbox.append(adminwriteform);
            //게시글 input check

            document.querySelector("input[name='title']").addEventListener("input", function(){
                if(this.value.trim().length>0) commonWriteCheckObj.title = true;
                else  commonWriteCheckObj.title = false;
            })
            document.querySelector("input[name='price']").addEventListener("input", function(){
                if(this.value.trim().length>0) commonWriteCheckObj.price = true;
                else  commonWriteCheckObj.price = false;
            })
            document.querySelector("input[name='images']").addEventListener("input", function(){
                if(this.value !=null) commonWriteCheckObj.images = true;
            })

        }
        //-----------------------------------------------------------------//
        
        //4 게시글작성 common  게시글 등록 취소 공통버튼
        function subcanBTN(){
             //제출 취소 버튼
            const div5 = document.createElement("div");
            div5.setAttribute("class", "oneLine write-btns");
            const span = document.createElement("span");
            div5.setAttribute("id", "btns");
            const btn1 = document.createElement("button");
            btn1.setAttribute("type", "button");
            btn1.setAttribute("onclick", "submitProduct()")
            btn1.setAttribute("class", "admin-write-btn opencal");
            btn1.innerText = "제출";
            const btn2 = document.createElement("button");
            btn2.setAttribute("class", "admin-write-btn opencal");
            btn2.setAttribute("type", "reset");
            btn2.innerText = "취소";
            span.append(btn1,btn2)
            div5.append(span)
            return div5;
        }
        //-----------------------------------------------------------------//
        //게시글 5 추가옵션상품
        function optionProductWrite(){
            //초기화
            validateVarInitial();
            contentbox.innerHTML="";
            //폼태그
            const adminwriteform = document.createElement("form")
            adminwriteform.setAttribute("id","writerForm");
            adminwriteform.setAttribute("method","POST");
            //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>추가 옵션 상품 등록<br>(스토어 상품)</h2>"
            //제목
            const title = document.createElement("div");
            title.setAttribute("class", "oneLine");
            title.innerHTML="<label class='labels'>상품명</label><input type='text' name='title'>";
            
            //가격
            const price = document.createElement("div");
            price.setAttribute("class", "oneLine");
            price.innerHTML="<label class='labels'>가격</label><input type='number' name='price'><span class='won'>원</span>";
            const writecate = document.createElement("input");
            writecate.setAttribute("type", "hidden");
            writecate.setAttribute("name", "writecate");
            writecate.setAttribute("value", "4");

            //버튼
            const div5 = document.createElement("div");
            div5.setAttribute("class", "oneLine write-btns");
            div5.innerHTML= '<span><button type="button" onclick="addOptionProduct()" class="admin-write-btn opencal">제출</button><button class="admin-write-btn opencal" type="reset">취소</button></span>'
            adminwriteform.append(funcName,title,price,writecate,div5);
            contentbox.append(adminwriteform);
        }
        
        //6. 관리자 계정 추가
        function addManagerId(){
            contentbox.innerHTML="";

            const adminwriteform = document.createElement("form")
            adminwriteform.setAttribute("id","writerForm");
            adminwriteform.setAttribute("method","POST");
            contentbox.append(adminwriteform)
             //이름
            const funcName = document.createElement("div");
            funcName.setAttribute("class", "oneLine");
            funcName.innerHTML="<h2>"+"관리자 계정 추가"+"</h2>";
            
            //관리자명
            const adminname = document.createElement("div");
            adminname.setAttribute("class", "oneLine addAdmin");
            adminname.innerHTML="<label class='labels'>관리자명</label><input type='text' name='adminname'>";
            
            //아이디
            const adminID = document.createElement("div");
            adminID.setAttribute("class", "oneLine addAdmin");
            adminID.innerHTML="<label class='labels'>관리자ID</label><input type='text' name='adminid'>";
            
            //비밀번호
            const adminPW = document.createElement("div");
            adminPW.setAttribute("class", "oneLine addAdmin");
            adminPW.innerHTML="<label class='labels'>비밀번호</label><input type='password' name='adminpw'>";
            
            $("#writerForm").append(funcName,adminname,adminID,adminPW,subcanBTN());
            $("#writerForm").attr("action","/addadminid");
        }
        //-----------------------------------------------------------------//
        

        //달력모달 동작
        function calmodal(){
            const closecal = document.querySelector('#closecal');
            const btnOpenPopup = document.getElementsByClassName('selectdate'); 
            const cal = document.querySelector('.cal'); 
            for(let i = 0 ; i<btnOpenPopup.length ; i++){
                btnOpenPopup[i].addEventListener('click', function() {
                    cal.style.display = 'block';
                    saveDateBtnthis= this.parentElement.children[1];
                    displaycal();
                });
            }
            closecal.addEventListener('click', ()=>{
                const monthday = document.getElementById("month-day");
                monthday.innerHTML="";
                cal.style.display = 'none';
                selectMonth=0;
            })
           /*  $("#month-day > tr > td") */
        }
        //썸머노트 실행 함수
        function notesummer(){
            //썸머노트 실행
            $('#summernote').summernote({
            lang:"ko-KR",
            placeholder: '내용을 입력해주세요',
            tabsize: 2,
            width : 1200,
            height: 600,
            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'underline', 'clear']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'video']],
                ['view', ['fullscreen', 'codeview', 'help']]
            ],
            callbacks : { 
                //onImageUpload = 이미지 업로드시 작동하는 콜백함수
                onImageUpload : function(files, editor, welEditable) {
            // 파일 업로드(다중업로드를 위해 반복문 사용)
                for (var i = files.length - 1; i >= 0; i--) {
                        uploadSummernoteImageFile(files[i],
                        this);
                        }
                }
            }//end callbacks 
            });
            // 이미지 업로드시 ajax로 파일 업로드를 하고 성공 후 파일 경로를 return받음
            function uploadSummernoteImageFile(file, editor) {
            data = new FormData();
            data.append("file", file);
            $.ajax({
                url : contextPath+"/admin/board/summernoteImage",
                data : data,
                type : "POST",
                dataType : 'JSON',
                contentType : false,
                processData : false,
                success : function(data) {
                    //항상 업로드된 파일의 url이 있어야 한다.
                    $(editor).summernote('insertImage', contextPath+data.url);
                }
            });
            } 
        }
        //날짜선택 달력 함수
        function displaycal(){
        const today = new Date();
        const origindate = today.getDate();
        //기존 일 저장
        //1일로 초기화해야 다음달 오류 제거됨
        today.setDate(1);

        today.setMonth((today.getMonth()+selectMonth));
        let year = today.getFullYear();
        let month = today.getMonth()+1;
        let date = today.getDate();
        let firstDay = new Date(year, month-1 , 1).getDay();
        let day = today.getDay();
        var monthDay = Array(31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

        /* 2월의 마지막 날 설정 */
        if (year % 400 == 0)
            monthDay[1] = 29;
        else if (year % 100 == 0)
            monthDay[1] = 28;
        else if (year % 4 == 0)
            monthDay[1] = 29;
        else
            monthDay[1] = 28;

        let lastDate =  monthDay[month-1];

        let count = 1;
        let week =1;
        let notLast= true; //마지막 날짜 검사 boolean;

        const tmonth = document.getElementById("today-month");
        const monthday = document.getElementById("month-day");
        monthday.innerHTML="";
        let showmonth = ""+month;
        if(month<10) {
            showmonth = "0"+showmonth;
        }
        tmonth.innerText = year+"-" +showmonth; 
        let tempDate = new Date();
        while(notLast){
            const tr = document.createElement("tr");
            for(let i = 0 ; i<7 ; i++){
                const td = document.createElement("td");
                    //첫번째 주는 해당 요일에서부터 1이 들어감
                    if(week==1&&i<firstDay){
                        td.innerText = " ";
                    }
                    else{
                        td.innerText = count;
                        let checkPossibleDay = false;
                        if(year>tempDate.getFullYear()){
                            checkPossibleDay = true;
                        }
                        else if(year==tempDate.getFullYear() && month-1>tempDate.getMonth()){
                            checkPossibleDay = true;
                        }
                        else if(year==tempDate.getFullYear() && month-1==tempDate.getMonth()){
                            if(count>tempDate.getDate()) checkPossibleDay = true;
                        }
                        if(checkPossibleDay) td.setAttribute("class","possible");
                        else td.setAttribute("class","impossible");
                        count= count+1;
                    }
                    tr.append(td);
                    tr.style.textAlign = "center";
                    //해당 월의 마지막 날짜까지 표기됐는지 검사
                    if(count == lastDate+1) {
                        notLast = false;
                        break;
                    }
            }
                week = week+1;//for문 1번 == 1주일
                monthday.append(tr);
            }
            dateclick();
            function dateclick(){
                $(".possible").on("click",function(){
                    const count = this.innerText ;
                    let showday = ""+count;
                    if(count<10){showday = '0'+showday}
                    saveDateBtnthis.value = tmonth.innerText+"-"+showday;

                    classCheckObj.classdate = true;
                    $("#closecal").click();
                })
            };
        }
        //다음달btn
        const prev = function(){
            selectMonth=selectMonth - 1;
            const monthday = document.getElementById("month-day");
            monthday.innerHTML = "";
            displaycal();
        }
        //이전달btn
        const next = function(){
            selectMonth=selectMonth +1;
            const monthday = document.getElementById("month-day");
            monthday.innerHTML = "";
            displaycal();
        }
        //클릭하면 이미지 첨부 / 삭제되도록
        function showImg(){
            $(".images").on("click", function(){
                var index = $(".images").index(this);
                if(filecheck[index]==0){
                    $("input[name=images]").eq(index).click()
                }
                else{
                    if(confirm("이미지를 삭제하시겠습니까?")){
                        $(this).children("img").removeAttr("src");
                        $(this).children("img").css("display","none");
                        $("input[name=images]").eq(index).val("");
                        if(index==0&&filecheck[index] ==1){
                            commonWriteCheckObj.images = false
                        }
                        filecheck[index]=0;
                    }
                }
            })
            //이미지 첨부되면 미리보기 뜨도록
            $("input[name=images]").on("input",function(){
                var index = $("input[name=images]").index(this);
                
                if(this.files[0]){
                    filecheck[index]=1;
                    var reader = new FileReader();
                    reader.readAsDataURL(this.files[0]);
                    reader.onload = function(e){
                        $(".images").eq(index).children("img").attr("src", e.target.result);
                        $(".images").eq(index).children("img").css("display", "block");
                    }
                }
            })
        }
        
        function makehourminute(){
            for(let i = 9 ; i< 21 ; i++){
                const option = document.createElement("option")
                const option2 = document.createElement("option")
                option.value= i;
                option.innerText = i+"시";
                option2.value= i;
                option2.innerText = i+"시";
                $("select[name='starthour']").append(option);
                $("select[name='endhour']").append(option2);
            }
            for(let i = 0 ; i< 60 ; i=i+10){
                const option = document.createElement("option")
                const option2 = document.createElement("option")
                option.value= i;
                option.innerText = i+"분";
                option2.value= i;
                option2.innerText = i+"분";
                $("select[name='startminute']").append(option);
                $("select[name='endminute']").append(option2);
            }

        }
        function submitProduct(){
            const cate = document.querySelector("input[name='writecate']").value;
            if(cate==2){
                alert("허가가 필요합니다!");
                return;
            }
            if(!validate(parseInt(cate))){
                return;
            };

            const form = $("#writerForm");
            const formData = new FormData(form[0]);
            $.ajax({
                url : contextPath+"/admin/board/productWrite",
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
                        commonWriter('일반 상품 등록');
                        makeNormalPWritePage(); 
                        url = contextPath + '/store/info/' + result;
                        window.open(url, '등록일반상품', ''+result); 
                        break;
                        case '2' : 
                        commonWriter('구독 상품 등록');
                        makeSubscribePWritePage(); break;
                        case '3' : 
                        commonWriter('클래스 상품 등록');
                        makeClassPWritePage(); 
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

        
        function addOptionProduct(){

            const title = document.querySelector("input[name='title']").value;
            const price = document.querySelector("input[name='price']").value;
            const writecate = document.querySelector("input[name='writecate']").value;
            if(title.trim().length==0){
                alert("상품명을 입력해주세요!");
                document.querySelector("input[name='title']").focus();
                return;
            }
            if(price.trim().length==0){
                alert("가격을 입력해주세요!");
                document.querySelector("input[name='price']").focus();
                return;
            }
            $.ajax({
                url : contextPath+"/admin/board/addOptionProduct",
                data : {
                    "title" : title,
                    "price" : price,
                    "writecate" : writecate
                },
                type : "POST",
                success : function(result){
                    alert("등록 성공!");
                    document.querySelector("input[name='title']").value = "";
                    document.querySelector("input[name='price']").value = "";
                },
                error: function(result){
                    alert("오류 발생");
                }
            })
        }

