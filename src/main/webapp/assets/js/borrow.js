/**
 * Created by home on 2018/3/8.
 */
layui.use(['jquery', 'layer', 'element'], function () {
    var layer = layui.layer
        , $ = layui.jquery
        , element = layui.element;

    $('.sub').click(function (e) {
        e.preventDefault();
        var $this = $(this);
        var url = $this.prop('href');
        $.post(url, {}, function (json) {
            if (json.status) {
                layer.open({
                    title: '消息',
                    content: "借书成功",
                    yes: function () {
                        location.reload();
                    }, cancel: function () {
                        location.reload();
                    }
                });
            }
            else {
                layer.open(
                    {
                        title: '消息',
                        content: json.message
                    }
                )
            }

        });
    });

});
