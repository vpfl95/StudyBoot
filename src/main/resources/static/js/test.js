console.log("start");



$("#btn").click(function(){
    console.log("click")
})

$(".buttons").click(function(){
    console.log("buttons")
})

$("#test").on("click","#btn2",function(){       //자식인 btn2로 이벤트 위임

});