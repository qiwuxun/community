/*function editPublic(data) {


    if (data!=undefined){
        $("#public_title").val(data.title);
    }
    //问题：将数据填入到页面上，值无法填入

   // $("#public_title").attr("value",data.title);
  /!*  $("#public_title").setAttribute("value",data.title);*!/
  // $("#public_title").setValue(data.title);
    $("#public_title").html(data.title);
    console.log("=========");
    console.log(data);
    console.log(data.title);
   // $("#public_title").attr("value",data.title);

   window.location.href="/public";
}*/
//将修改的数据填入页面中
function selPublic(id) {
    $.ajax({
        url:"/UpPublic",
        type:"post",
        data:{"id":id},
        success:function (data) {
            console.log(data);
            console.log(data.question);
            //$("#public_title").html(data.question.title);
             $("#public_title").val(data.question.title);
             $("#public_describes").val(data.question.describes);
             $("#public_tag").val(data.question.tag);
             $("#public_id").val(data.question.id);
            //editPublic(data.question);
            //alert(data);
           // window.location.href="/public";
        }
    });
}

/*$.ready(function () {
  //  UpPublic();
    alert(1);


});*/
