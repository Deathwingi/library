package com.zpz.went;

import com.jfinal.core.Controller;

/**
 * Created by home on 2018/3/10.
 */
public class ChangeInfoValidator extends BaseValidator {
    @Override
    protected void validate(Controller c) {
        validateCaptcha("captcha", "message", "验证码错误");
    }

    @Override
    protected void handleError(Controller c) {
        c.setAttr("stutus", false);
        c.renderJson();
    }
}
