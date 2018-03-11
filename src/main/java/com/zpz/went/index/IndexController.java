package com.zpz.went.index;

import com.jfinal.aop.Clear;
import com.zpz.went.common.controller.BaseController;
import com.zpz.went.common.interceptor.UserInterceptor;

/**
 * Created by home on 2017/12/12.
 */
@Clear(UserInterceptor.class)
public class IndexController extends BaseController {
    public void index(){render("index.html");}
}
