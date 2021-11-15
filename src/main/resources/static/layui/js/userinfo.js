function checkSUer() {
    //获取用户权限
    $.ajax({
        url: "/checkUser",
        type: "post",
        dataType: "json",

        success: function (data) {
            var add = document.getElementById("add");
            var del = document.getElementById("del");
            if (data.code === "0" && data.data.XX === true) {

                add.removeAttribute("style","display:none");
                del.removeAttribute("style","display:none");
            }

            return true;
        }

    })
}

window.onload=function () {
    checkSUer();
    }

function AddInfo (){

    $.ajax({
        url:"/add/userinfo",
        type:"POST",
        datatype:"JSON",
        data: $('#formadd').serialize(),
        success:function (data) {
            if(data.code==="0"){

                layer.open({
                    content: '新增用户成功！',
                    yes: function(index, layero){
                        layer.close(index);
                    }
                });
                return true;
            }else {

                layer.open({
                    content: data.errormsg,
                    yes: function(index, layero){
                        layer.close(index);
                    }
                });
                return false;

            }

        }
    })
    return false;

}

function DelInfo ( UserInfo ){

    $.ajax({
        url:"/del/userinfo",
        type:"POST",
        datatype:"JSON",
        contentType:"application/json",
        data:JSON.stringify(UserInfo),
        success:function (data) {
            if(data.code==="0"){
                layer.confirm("删除用户成功！", {
                    btn: ['确认']
                });
                return true;
            }else {
                layer.confirm(data.errormsg, {
                    btn: ['确认']
                });
                return false;
            }

        }
    });
    return false;

}

function clean(){

    $('#username').attr("readonly",false).css("background-color","#FFFFFF").val("");
    $('#age').attr("readonly",false).css("background-color","#FFFFFF").val("");
    $('#sex').attr("readonly",false).css("background-color","#FFFFFF").val("");
    $('#password').val("");


    return true;
}

