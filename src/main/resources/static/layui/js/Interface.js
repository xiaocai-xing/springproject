

function DoPostMod(){
    let str = '';
    $.ajax({
        url:"/interface/dopost",
        type:"POST",
        datatype:"json",
        data: $('#param').serialize(),
        success:function (data) {
            console.log(data.code);

            if(data.code === "200" ){
                // str = data.toString();

                $("#responsedata").text(JSON.stringify(data));
                layer.open({
                    content: '请求成功',
                    yes: function(index){
                        layer.close(index);
                    }
                });
                return true;
            }else {

                layer.open({
                    content: '请求失败',
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