/**
 * Created by home on 2017/12/12.
 */
layui.use(['form', 'jquery', 'layer', 'element'], function () {
    var form = layui.form
        , layer = layui.layer
        , $ = layui.jquery
        , element = layui.element;

    $('#captcha-img').prop('src', '/login/captcha?x=' + Math.random());

    form.on('submit(loginSubmit)', function (data) {
        var postdata = data.field;

        $.post('/login/doLogin', postdata, function (signdata) {
            if (signdata.status) {
                layer.open(
                    {
                        title: '消息'
                        , content: "登录成功",
                        yes: function () {
                            location.href = "/"
                        }, cancel: function () {
                            location.href = "/"
                        }
                    }
                );

            }
            else {
                $('#captcha-img').prop('src', '/login/captcha?x=' + Math.random());
                layer.open(
                    {
                        title: '消息',
                        content: signdata.message
                    }
                );
            }
        });
        return false
    });
});

