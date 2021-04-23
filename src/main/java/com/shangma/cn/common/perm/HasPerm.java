package com.shangma.cn.common.perm;

import com.baomidou.mybatisplus.annotation.TableId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 17:27
 * 文件说明：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface HasPerm {

    String perm() default "";
}
