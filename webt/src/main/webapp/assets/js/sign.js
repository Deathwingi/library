/**
 * Created by home on 2017/12/12.
 */
layui.use(['form', 'jquery', 'layer','element'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,$ = layui.jquery
        ,element=layui.element;

    form.on('submit(signSubmit)',function (data) {
        var postdata=data.field;

        $.post('sign/dosign',postdata,function (signdata) {
            if(signdata.status){
                layer.open(
                    {
                        title:'消息'
                        ,content:"注册成功",
                        yes:function () {
                            location.href="/login"
                        },cancel:function () {
                            location.href="/login"
                    }
                    }
                );
            }
            else{
                layer.open(
                    {
                        title:'消息',
                        content:signdata.message
                    }
                );
            }
        });
        return false
    });
});

