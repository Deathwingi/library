package com.zpz.went.borrow;

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
public class BorrowController extends BaseController {
    private static final BorrowService bs=new BorrowService();
    public void index(){
        List<Book> bookList=bs.getBookList();
        setAttr("bookList", bookList);
        render("borrow.html");
    }

    public void sub(){
        String token = getCookie(LoginService.SESSION_ID_NAME);
        User user = Redis.use().get(token);
        int user_id = user.getId();
        System.out.print(user_id);
        Integer bookId=getParaToInt();
        Ret ret = bs.BorrowBook(bookId, user_id);
        renderJson(ret);
    }
}
