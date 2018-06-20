package com.zpz.went.sign;

import com.jfinal.core.Controller;
import com.zpz.went.common.validator.BaseValidator;

/**
 * Created by home on 2018/3/8.
 */
public class SignValidator extends BaseValidator {

    @Override
    protected void validate(Controller c) {
        validateEqualString(c.getPara("password"), c.getPara("repeatPassword"), "message",
                "两次密码不一致");
        validateString("username", 2, 20, "message", "用户名格式错误");
        validateRegex("username", "^\\S{2,20}$", "message", "用户名格式错误");
    }

    @Override
    protected void handleError(Controller c) {
        c.setAttr("status",false);
        c.renderJson();
    }
}
