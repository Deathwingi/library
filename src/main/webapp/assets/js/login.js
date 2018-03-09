/**
 * Created by home on 2017/12/12.
 */
/**
 * Created by home on 2017/12/12.
 */
layui.use(['form', 'jquery', 'layer','element'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,$ = layui.jquery
        ,element=layui.element;

    form.on('submit(loginSubmit)',function (data) {
        var postdata=data.field;

        $.post('/login/doLogin',postdata,function (signdata) {
            if(signdata.status){
                layer.open(
                    {
                        title:'消息'
                        ,content:"登录成功",
                        yes:function () {
                            location.href="/borrow"
                        },cancel:function () {
                        location.href="/borrow"
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

