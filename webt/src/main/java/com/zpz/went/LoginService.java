package com.zpz.went;

import com.jfinal.kit.Ret;

/**
 * Created by home on 2018/3/7.
 */
public class LoginService {
    public static final LoginService me = new LoginService();
    private static User userDao=new User().dao();

    public Ret login(String username,String password){
        User user=userDao.findFirst("select * from j_user where username=?",username);
        if(user==null)
        {
            return Ret.by("status",false).set("message","用户不存在");
        }
        String pwd=user.getPassword();
        if(pwd.equals(password)){
            return Ret.by("status",true);
        }
        else{
            return  Ret.by("status",false).set("message","密码错误");
        }
    }
}
