package com.zpz.went;

import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.redis.Redis;

/**
 * Created by home on 2018/3/7.
 */
public class LoginService {
    public static final LoginService me = new LoginService();
    public static final String SESSION_ID_NAME = "loginToken";
    private static User userDao = new User().dao();

    public Ret login(String username, String password) {
        User user = userDao.findFirst("select * from j_user where username=?", username);
        if (user == null) {
            return Ret.by("status", false).set("message", "用户不存在");
        }
        String pwd = user.getPassword();
        if (pwd.equals(password)) {
            User users = userDao.findFirst(Db.getSqlPara("user.findByName",
                    username));
            int users_id = users.getId();
            String token = StrKit.getRandomUUID();
            Redis.use().setex(token, 60 * 60, users_id);
            return Ret.by("status", true).set("loginToken", token);

        } else {
            return Ret.by("status", false).set("message", "密码错误");
        }
    }
}
