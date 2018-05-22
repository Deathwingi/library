package com.zpz.went.restore;

import com.jfinal.aop.Before;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.redis.Redis;
import com.zpz.went.common.controller.BaseController;
import com.zpz.went.common.interceptor.RoleInterceptor;
import com.zpz.went.common.model.Book;
import com.zpz.went.common.model.User;
import com.zpz.went.login.LoginService;

import java.util.List;

/**
 * Created by home on 2018/3/8.
 */
@Before(RoleInterceptor.class)
public class RestoreController extends BaseController {
    RestoreService rs = RestoreService.me;

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
