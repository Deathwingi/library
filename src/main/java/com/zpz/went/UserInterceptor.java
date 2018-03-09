package com.zpz.went;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Redis;

/**
 * Created by home on 2018/3/9.
 */
public class UserInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        Controller c = inv.getController();
        String token = c.getCookie(LoginService.SESSION_ID_NAME);
        User user = token == null ? null : Redis.use().get(token);
        if (user == null) {
            c.redirect("/login");
            return;
        }
        inv.invoke();
    }
}
