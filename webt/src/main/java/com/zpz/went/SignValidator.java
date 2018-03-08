package com.zpz.went;

import com.jfinal.core.Controller;

/**
 * Created by home on 2018/3/8.
 */
public class SignValidator extends BaseValidator {

    @Override
    protected void validate(Controller c) {
        validateEqualString(c.getPara("password"),c.getPara("repeatpassword"),"message",
                "两次密码不一致");
    }

    @Override
    protected void handleError(Controller c) {
        c.setAttr("status",false);
        c.renderJson();
    }
}
