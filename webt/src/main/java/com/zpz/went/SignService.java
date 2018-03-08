package com.zpz.went;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;

/**
 * Created by home on 2018/3/6.
 */
public class SignService {
    public static final SignService me = new SignService();
    private static final User userDao = new User().dao();

    public Ret sign(String username, String password) {

        User user = userDao.findFirst(Db.getSqlPara("user.findByName",username));
        if (user == null) {
            new User().setUsername(username).setPassword(password).save();
            return Ret.by("status", true);
        } else {
            return Ret.fail("status", false).set("message", "用户名已经存在!");
        }
    }
}
