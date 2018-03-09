package com.zpz.went;

import com.jfinal.aop.Clear;

/**
 * Created by home on 2017/12/12.
 */
@Clear(UserInterceptor.class)
public class IndexController extends BaseController{
    public void index(){render("index.html");}
}
