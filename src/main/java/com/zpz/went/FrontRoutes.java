package com.zpz.went;

import com.jfinal.config.Routes;

/**
 * Created by home on 2017/12/12.
 */
public class FrontRoutes extends Routes{
    public void config(){
        setBaseViewPath("view");
        add("/",IndexController.class,"/index");
        add("/login", LoginController.class);
        add("/sign", SignController.class);
        add("/borrow", BorrowController.class);
        add("/restore", RestoreController.class);
    }
}
