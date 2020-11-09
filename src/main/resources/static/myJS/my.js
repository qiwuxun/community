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
/*
* 重构回复
* */
function comment_Ajax(targetId,content,type) {
    if (!content) {
        layer.msg("亲，回复内容不能为空！");
        //$("#question_Id").focus();
        return false;
    }

    $.ajax({
        url:"/comment",
        type:"post",
        contentType:'application/json',
        data:JSON.stringify({'parentId':targetId,'content':content,'type':type}),
        dataType:'json',
        success:function (data) {
            console.log(data);
            if (data.code == 200) {
                //$("#comment_div").hide();
                window.location.reload();
                // $("#comment_div").hide();
            }else if (data.code==2003){
                //询问框
                //  layer.msg("你还未登陆呢");
                var   index=layer.confirm(data.message,{btn:['登录','暂不']},function (index) {
                    // window.open("");
                    window.localStorage.setItem("closable","true");
                    // window.load("https://github.com/login/oauth/authorize?client_id=f0e7bffee1b0113953b3&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1")
                    window.location.href="https://github.com/login/oauth/authorize?client_id=f0e7bffee1b0113953b3&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1";
                   // window.open("https://github.com/login/oauth/authorize?client_id=f0e7bffee1b0113953b3&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1");
                    layer.close(index);
                    //layer.alert("抱歉，暂时还没有开发出来",6);
                },function () {
                    layer.close(index);
                });

            }else {
                layer.alert(data.message,5);
            }
        }


    });
   // console.log(questionId);
    console.log(content);
}
/*二级评论回复*/
function comment_Two(e) {
    var contentId = e.getAttribute("data-id");
    var contents = $("#input-"+contentId).val();
    comment_Ajax(contentId,contents,2);
}
/*
* 提交回复
* */
function sub_Comment() {
    var questionId = $("#question_Id").val();
    var content = $("#content_text").val();
    comment_Ajax(questionId,content,1);
   /* if (!content) {
        layer.msg("亲，回复内容不能为空！");
        $("#question_Id").focus();
        return false;
    }

    $.ajax({
        url:"/comment",
        type:"post",
        contentType:'application/json',
        data:JSON.stringify({'parentId':questionId,'content':content,'type':1}),
        dataType:'json',
        success:function (data) {
            console.log(data);
            if (data.code == 200) {
                $("#comment_div").hide();
                window.location.reload();
              // $("#comment_div").hide();
            }else if (data.code==2003){
                //询问框
              //  layer.msg("你还未登陆呢");
            var   index=layer.confirm(data.message,{btn:['登录','暂不']},function () {
                  // window.open("");
                   layer.alert("抱歉，暂时还没有开发出来",6);
               },function () {
                   layer.close(index);
               });

            }else {
                layer.alert(data.message,5);
            }
        }


    });
    console.log(questionId);
    console.log(content);*/

}
/*二级评论展开
* */
function collapseComments(e) {
    //debugger;
   // console.log(e);
    var id = e.getAttribute("data-id");
    var collapse = e.getAttribute("data-collapse");
    var comments = $("#comment-"+id);
    if (collapse){
        //折贴二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{
        //展开二级评论
        //获取评论信息，渲染到页面
        var commentContains= $("#comment-"+id);
        if (commentContains.children().length!=1){

            e.setAttribute("data-collapse","in");
            comments.addClass("in");
            e.classList.add("active");
        } else{
            $.getJSON( "/comment/"+id, function( data ) {
                //var items = [];
                console.log(data);
                var items = [];
                $.each( data.data.reverse(), function( index, comment ) {
                    var mediaBodyElement=$("<div/>",{
                        "class":"media-body"
                    }).append($("<h5/>",{
                        "class":"media-heading text-desc",
                        html:comment.user1.name
                    })).append($("<div/>",{
                        html:comment.content
                    })).append($("<div/>",{
                        "class":"comment-menu"
                    }).append($("<span/>",{
                        "class":"pull-right",
                        html:moment(comment.gmtModified).format("YYYY-MM-DD")
                    })));

                    var mediaLeftElement=$("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>",{
                        "class":"media-object media-img img-thumbnail",
                        "src":comment.user1.avatarUrl
                    }));

                    var contentElement=$( "<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 "
                    }).append($("<div/>",{
                        "class":"media comment-two-comment"
                    }).append(mediaLeftElement).append(mediaBodyElement));

                    commentContains.prepend(contentElement);
                });
                e.setAttribute("data-collapse","in");
                comments.addClass("in");
                e.classList.add("active");
            });
        }

    }

  //  console.log(id);
}



//public  标签
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var oldTags = $("#public_tag").val();
    if (oldTags.indexOf(value) == -1) {

        if (oldTags) {
            $("#public_tag").val(oldTags+","+value);
        }else{
            $("#public_tag").val(value);
        }
    }
}
function showSelectTag() {
    $("#select-tag").show();
}
