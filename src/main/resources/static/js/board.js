let count=0;

function setCount(c){
    if(c<1 || c>5){
        c=1
    }
    count=c;
}

$("#fileAdd").click(function(){
    if(flag){
        let size = $("#fileAddResult").attr("data-file-size");
        if(size==undefined){
            size=0
        }
        count=size;
        flag=false
    }
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

let flag =true
// 글 수정시 첨부파일 삭제
$(".deleteFile").click(function(){
    //DB, HDD에 파일 삭제
    let check =confirm("삭제됩니다. 복구 불가");
    if(flag){
        let size = $("#fileAddResult").attr("data-file-size");
        count=size;
        flag=false
    }

    if(check){
        //post
        //url : fileDelete
        // 파라미터 fileNum
        let fileNum =$(this).attr("data-file-num")
        console.log(fileNum)
        const btn =$(this)
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
                count--;
            },
            error:function(){
                console.log("Error 발생");
            }
        })
        
    }
})  