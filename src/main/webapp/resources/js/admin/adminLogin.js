
const checkOption = {
    "id" : false,
    "pw" : false
}

function validate(){
    if(document.querySelector("#adminId").value.trim().length>0){
        checkOption.id=true;
    }

    for(key in checkOption){

        if(!checkOption[key]){
            let message;
            switch(key){
                case "id" : message = "id를 입력해주세요!"; break;
                case "pw" : message = "pw를 입력해주세요!"; break;
            }
            alert(message);
            return false;
        }

    }
}

document.querySelector("#adminId").addEventListener("input", function(){
    if((this.value).trim().length==0){
        checkOption.id = false;
    }
    else{
        checkOption.id = true;
    }

})


document.querySelector("#adminPw").addEventListener("input", function(){
    if((this.value).trim().length==0){
        checkOption.pw = false;
    }
    else{
        checkOption.pw = true;
    }
})

