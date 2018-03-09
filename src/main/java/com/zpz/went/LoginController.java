package com.zpz.went;

import com.jfinal.kit.Ret;

/**
 * Created by home on 2017/12/12.
 */
public class LoginController extends BaseController {
    private static final LoginService srv = new LoginService();

    // /login
    public void index() {
        render("login.html");
    }

    public void doLogin() {
        String username = getPara("username");
        String password = getPara("password");
        Ret ret = srv.login(username, password);
        if (ret.getBoolean("status")) {

            setCookie(LoginService.SESSION_ID_NAME, ret.getStr("loginToken"),
                    60 * 60);

        }
        renderJson(ret);
    }
}
