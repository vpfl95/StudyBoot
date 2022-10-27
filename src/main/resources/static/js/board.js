let count=0;

$("#fileAdd").click(function(){
    if(count<5){
        let add = '<div class="mb-3"> '
            +'<input type="file" name="files">'
            +'<button type="button" class="del">X</button>'
            +'</div>'
        $("#fileAddResult").append(add)
        count++;
    }else{
        alert("최대 5개만 가능")
    }
})


$("#fileAddResult").on("click",".del",function(){
    $(this).parent().remove();
    count -=1
})

// 글 수정시 첨부파일 삭제
$(".deleteFile").click(function(){
    //DB, HDD에 파일 삭제
    let check =confirm("삭제됩니다. 복구 불가");

    if(check){
        //post
        //url : fileDelete
        // 파라미터 fileNum
        let fileNum =$(this).attr("data-file-num")
        console.log(fileNum)
        $.ajax({
            type:"POST",
            url:"fileDelete",
            data:{
                fileNum:fileNum
            },
            success:function(result){
                console.log(result)
                console.log("After Result This", $(this));
                $(btn).parent().remove();
            },
            error:function(){
                console.log("Error 발생");
            }
        })
        
    }
})  