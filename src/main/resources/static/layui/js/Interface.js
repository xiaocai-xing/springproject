
function DoPostMod(){
    $.ajax({
        url:"/interface/dopost",
        type:"POST",
        datatype:"json",
        data: $('#param').serialize(),
        success:function (data) {
            if(data.code==="200"){

                layer.open({
                    content: '请求成功',
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
        }
    )
}