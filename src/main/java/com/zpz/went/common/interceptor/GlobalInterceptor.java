package com.zpz.went.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Redis;
import com.zpz.went.common.model.User;
import com.zpz.went.login.LoginService;

/**
 * Created by home on 2018/3/11.
 */
public class GlobalInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        Controller c = inv.getController();
        String token = c.getCookie(LoginService.SESSION_ID_NAME);
        User user = token == null ? null : Redis.use().get(token);
        c.setAttr("user", user);
        inv.invoke();
    }
}
