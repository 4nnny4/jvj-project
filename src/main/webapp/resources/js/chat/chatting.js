function sendMessage(){
    const message = $("#chatting_textarea").val();

    if(message.trim().length == 0){
        alert("내용을 입력해주세요");
    }else{
        const obj = {};
        obj.memberNo = memberNo;
        obj.memberName = memberName;
        obj.message = message;
        obj.chatRoomNo = chatRoomNo;

        chattingSock.send(JSON.stringify(obj));
        $("#chatting_textarea").val("");
        location.reload()
    }
}

    chattingSock.onmessage = function(e){
        console.log(JSON.parse(e.data));
    const obj = JSON.parse(e.data);

    const li = $("<li>");

    const p = $("<p class='chat'>");
    const span = $("<span class='chatDate'>");
    span.html(obj.createDate);

    // XSS, 개행문자 처리
    if(obj.message != undefined){

        let chat = XSS(obj.message);
        chat = chat.replaceAll("\n", "<br>")
        p.html(chat);

    }else{ 
        p.html("<b>"+obj.memberName+"님이 나가셨습니다.</b>")
    }


    if (obj.memberNo == memberNo) {
        li.addClass("myChat");
        li.append(span);
        li.append(p);
    } else {
        li.html("<b>" + obj.memberName + "</b><br>");
        li.append(p);
        li.append(span);
    }

}

// 보내기 버튼 클릭 시 채팅 전달
$("#send").on("click", sendMessage);

//  XSS 처리 함수
function XSS(message){
    let str = message;

    str.replace(/&/g, "&amp;");
    str.replace(/</g, "&lt;");
    str.replace(/>/g, "&gt;");
    str.replace(/"/g, "&quot;");

    return str;
}


// 나가기 버튼 동작
$("#chat_out").on("click", function(){
    if(confirm("나가겠습니까?")){

        const obj = {};
        obj.memberNo = memberNo; 
        obj.chatRoomNo = chatRoomNo;
        obj.memberName = memberName;

        chattingSock.send( JSON.stringify(obj) );
        
        location.replace(contextPath+"");    
    }
});

