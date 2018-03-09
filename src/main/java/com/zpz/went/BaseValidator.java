package com.zpz.went;

import com.jfinal.validate.Validator;

/**
 * Created by home on 2018/3/8.
 */
public abstract class BaseValidator extends Validator {
    /**
     * 遇错直接跳转
     */
    public BaseValidator() {
        shortCircuit=true;
    }
}
