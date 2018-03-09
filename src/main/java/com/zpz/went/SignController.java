package com.zpz.went;

import com.jfinal.aop.Before;
import com.jfinal.kit.Ret;

/**
 * Created by home on 2017/12/12.
 */
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
