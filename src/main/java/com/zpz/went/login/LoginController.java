package com.zpz.went.login;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.Ret;
import com.zpz.went.common.controller.BaseController;
import com.zpz.went.common.interceptor.UserInterceptor;

/**
 * Created by home on 2017/12/12.
 */
public class LoginController extends BaseController {
    private static final LoginService srv = new LoginService();

    // /login
    @Clear(UserInterceptor.class)
    public void index() {
        render("login.html");
    }

    @Clear(UserInterceptor.class)
    @ActionKey("/changeInfo")
    public void changeInfo() {
        render("changeInfo.html");
    }

    @Clear(UserInterceptor.class)
    @Before(LoginValidator.class)
    public void doLogin() {
        String username = getPara("username");
        String password = getPara("password");
        Ret ret = srv.login(username, password);
        if (ret.getBoolean("status")) {

            setCookie(LoginService.SESSION_ID_NAME, ret.getStr("loginToken"),
                    -1);

        }
        renderJson(ret);
    }

    @Clear(UserInterceptor.class)
    @Before(ChangeInfoValidator.class)
    public void doChangePassword() {
        String username = getPara("username");
        String oldPassword = getPara("oldPassword");
        String newPassword = getPara("newPassword");
        String repeatNewPassword = getPara("repeatNewPassword");
        if (!newPassword.equals(repeatNewPassword)) {
            renderJson(Ret.by("status", false).set("message", "两次输入的密码不一致"));
        } else {
            Ret ret = srv.changeIofo(username, oldPassword, newPassword);
            renderJson(ret);
            removeCookie(LoginService.SESSION_ID_NAME);
        }
    }

    @Clear({UserInterceptor.class})
    public void captcha() {
        renderCaptcha();
    }

    @ActionKey("/logout")
    public void logout() {
        removeCookie(LoginService.SESSION_ID_NAME);
        redirect("/login");
    }
}
