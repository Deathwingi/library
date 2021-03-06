package com.zpz.went.login;

import com.jfinal.core.Controller;
import com.zpz.went.common.validator.BaseValidator;

/**
 * Created by home on 2018/3/10.
 */
public class LoginValidator extends BaseValidator {
    @Override
    protected void validate(Controller c) {
        validateCaptcha("captcha", "message", "验证码错误");
        validateString("username", 2, 20, "message", "用户名错误");
        validateRegex("username", "^\\S{2,20}$", "message", "用户名错误");
    }

    @Override
    protected void handleError(Controller c) {
        c.setAttr("stutus", false);
        c.renderJson();
    }
}
