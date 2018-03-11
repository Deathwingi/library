package com.zpz.went.common;

import com.jfinal.config.Routes;
import com.zpz.went.addBook.AddBookController;
import com.zpz.went.borrow.BorrowController;
import com.zpz.went.index.IndexController;
import com.zpz.went.login.LoginController;
import com.zpz.went.restore.RestoreController;
import com.zpz.went.sign.SignController;

/**
 * Created by home on 2017/12/12.
 */
public class FrontRoutes extends Routes{
    public void config(){
        setBaseViewPath("/view");
        add("/",IndexController.class,"/index");
        add("/login", LoginController.class);
        add("/sign", SignController.class);
        add("/borrow", BorrowController.class);
        add("/restore", RestoreController.class);
        add("/addBook", AddBookController.class);
    }
}
