/**
 * Created by home on 2018/3/11.
 */
layui.use(['jquery', 'layer', 'element', 'form'], function () {
    var layer = layui.layer
        , $ = layui.jquery
        , form = layui.form
        , element = layui.element;

    $('.sub').click(function (e) {
        e.preventDefault();
        var $this = $(this);
        var url = $this.prop('href');
        $.post(url, {}, function (json) {
            if (json.status) {
                layer.open({
                    title: '消息',
                    content: "删除成功",
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
    form.on('submit(addSubmit)', function (data) {
        var postData = data.field;
        $.post('addBook/plus', postData, function (addBookData) {
            if (addBookData.status) {
                layer.open({
                    title: '消息'
                    , content: '添加书籍成功!'
                    , yes: function () {
                        location.reload();
                    }
                    , cancel: function () {
                        location.reload();
                    }
                });
            } else {
                layer.open({
                    title: '消息'
                    , content: addBookData.message
                });
            }
        });
        return false;
    });
    $(document).ready(function () {
        $('#add-Book-button').click(function () {
            $('#add-Book-div').show();
            $('#add-Book-button').hide();
        });
        $('#cancerAdd').click(function () {
            $('#add-Book-div').hide();
            $('#add-Book-button').show();
        });
    });
});
