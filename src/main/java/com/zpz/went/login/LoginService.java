package com.zpz.went.login;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.redis.Redis;
import com.zpz.went.common.model.User;

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
        String salt = user.getSalt();
        if (pwd.equals(HashKit.sha256(salt + password))) {
            User users = userDao.findFirst(Db.getSqlPara("user.findByName",
                    username));
            String token = StrKit.getRandomUUID();
            Redis.use().setex(token, 60 * 60, users);
            return Ret.by("status", true).set("loginToken", token);

        } else {
            return Ret.by("status", false).set("message", "密码错误");
        }
    }

    public Ret changeIofo(String username, String oldPassword, String newPassword) {
        User user = userDao.findFirst(Db.getSqlPara("user.findByName", username));
        if (user == null) {
            return Ret.by("status", false).set("message", "无此用户");
        }
        String password = user.getPassword();
        if (!password.equals(oldPassword)) {
            return Ret.by("status", false).set("message", "旧密码错误");
        }
        if (user.setPassword(newPassword).update()) {
            return Ret.by("status", true);
        } else {
            return Ret.by("status", false);
        }
    }
}
