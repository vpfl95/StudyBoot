console.log("조인")

$("#all").click(function(){
    let ch = $(this).prop("checked")
    
    $(".check").prop("checked",ch)  
})

$(".check").click(function(){
    let flag = true;

    $(".check").each(function(idx,item){
        let ch =$(item).prop("checked")
        if(!ch){
            flag=false
        }
        console.log(ch)
    });

    $("#all").prop("checked",flag);
});

let idCheck=false 
let pwCheck=false
let pwequal=false
let nmCheck=false
let emailCheck=false


// id check
$("#inputId").blur(function(){
    let id = $("#inputId").val()
    idCheck = nullCheck(id,"#inputIdResult","id");

    //단 id가 비어잇지 않을 때
    $.get("./idCheck?id="+id, function(data){
        console.log("data",data);
        if(data=='0'){
            $("#inputIdResult").html("사용가능")
            idCheck = true
        }else{
            $("#inputIdResult").html("중복 ID")
            idCheck = false
        }
    })
})

$("#inputPw").blur(function(){
   pwCheck = nullCheck($("#inputPw").val(),"#inputPwResult","pw")
})

$("#inputPw2").blur(function(){
    let result = equals($("#inputPw").val(), $("#inputPw2").val());
    if(result){
        pwequal=true
        $("#inputPwResult2").html("정상")
    }else{
        $("#inputPwResult2").html("다름")
    }
})

$("#inputName").blur(function(){
    nmCheck= nullCheck($("#inputName").val(),"#inputNameResult","이름");
})

$("#inputEmail").blur(function(){
    emailCheck= nullCheck($("#inputEmail").val(),"#inputEmailResult","email");

})

$("#joinButton").click(function(){
    //event 강제 실행
    if(idCheck && pwCheck && pwequal && nmCheck && emailCheck){
        $("#joinForm").submit();
    }else{
        alert("입력안한거 존재")
    }

})

// function joinCheck(){
//     let idCheck=false
//     let pwCheck=false
//     let pwequal=false
//     let nmCheck=false
//     let emailCheck=false

//     $("#inputId").blur(function(){
//         if($("#inputId").val().length>=3){
//             idCheck=true
//         }else{
//             alert("id는 3자 이상")
//         }
//     })

//     $("#inputPw").blur(function(){
//         if($("#inputPw").val().length>=3){
//             pwCheck=true
//         }else{
//             alert("pw는 3자 이상")
//         }
//     })

//     $("#inputPw2").keyup(function(){
//         if($("#inputPw2").val()!=$("#inputPw").value){
//             console.log("비밀번호다름")
//         }else{
//             pwequal=true
//         }
//     })

//     $("#inputName").blur(function(){
//         if($("#inputName").val().length==0){
//             nmCheck=true
//         }else{
//             alert("이름 1자 이상")
//         }
//     })
    
    
//     $("#inputEmail").blur(function(){
//         if($("#inputEmail").val().length==0){
//             emailCheck=true
//         }else{
//             alert("이메일 1자 이상")
//         }
//     })

//     $("#joinButton").click(function(){
//         //event 강제 실행
//         if(idCheck && pwCheck && pwequal && nmCheck && emailCheck){
//             $("#joinForm").submit();
//         }else{
//             alert("입력안한거 존재")
//         }
    
//     })
    
// }