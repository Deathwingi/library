package com.zpz.went;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.redis.Redis;

import java.util.List;

/**
 * Created by home on 2018/3/8.
 */
public class RestoreController extends BaseController {
    RestoreService rs = new RestoreService();

    public void index() {
        List<Book> bookList = rs.getBookList();
        setAttr("bookList", bookList);
        render("restore.html");
    }

    public void adds() {
        int book_id = getParaToInt();
        String token = getCookie(LoginService.SESSION_ID_NAME);
        User user = Redis.use().get(token);
        int user_id = user.getId();
        Ret ret = rs.restoreBook(book_id, user_id);
        renderJson(ret);
    }
}
