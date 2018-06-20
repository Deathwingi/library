package com.zpz.went.sign;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.zpz.went.common.model.User;

/**
 * Created by home on 2018/3/6.
 */
public class SignService {
    public static final SignService me = new SignService();
    private static final User userDao = new User().dao();

    public Ret sign(String username, String password) {

        User user = userDao.findFirst(Db.getSqlPara("user.findByName",username));
        if (user == null) {
            String salt = StrKit.getRandomUUID();
            String pwd = HashKit.sha256(salt + password);
            new User().setUsername(username).setPassword(pwd).setSalt(salt).save();
            return Ret.by("status", true);
        } else {
            return Ret.fail("status", false).set("message", "用户名已经存在!");
        }
    }
}
