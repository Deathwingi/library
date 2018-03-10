/**
 * Created by home on 2018/3/10.
 */
layui.use(['form', 'jquery', 'layer', 'element'], function () {
    var form = layui.form
        , layer = layui.layer
        , $ = layui.jquery
        , element = layui.element;

    form.on('submit(changePwdSubmit)', function (data) {
        var postdata = data.field;

        $.post('/login/doChangePassword', postdata, function (data) {
            if (data.status) {
                layer.open(
                    {
                        title: '消息'
                        , content: "修改密码成功",
                        yes: function () {
                            location.href = "/login"
                        }, cancel: function () {
                        location.href = "/login"
                    }
                    }
                );

            }
            else {
                layer.open(
                    {
                        title: '消息',
                        content: data.message
                    }
                );
            }
        });
        return false
    });
});

