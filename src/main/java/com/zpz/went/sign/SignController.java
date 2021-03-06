package com.zpz.went.sign;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Ret;
import com.zpz.went.common.controller.BaseController;
import com.zpz.went.common.interceptor.UserInterceptor;

/**
 * Created by home on 2017/12/12.
 */
@Clear(UserInterceptor.class)
public class SignController extends BaseController {
    private static final SignService srv=new SignService();
    public void index(){render("sign.html");}

    @Before(SignValidator.class)
    public void dosign(){
        String username=getPara("username");
        String password=getPara("password");
        Ret ret=srv.sign(username,password);
        renderJson(ret);
    }
}
