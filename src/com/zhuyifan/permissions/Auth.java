package com.zhuyifan.permissions;

import java.lang.annotation.*;

/**
 * @author haohu 
 * 在类或方法上添加@Auth就验证登录 
 */  
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

}