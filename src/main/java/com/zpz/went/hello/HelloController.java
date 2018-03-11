package com.zpz.went.hello;

import com.jfinal.core.Controller;

/**
 * Created by home on 2017/11/23.
 */
public class HelloController extends Controller {
    public void index(){
        renderText("welcome");
    }
}
