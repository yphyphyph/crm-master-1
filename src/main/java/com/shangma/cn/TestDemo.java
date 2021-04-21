package com.shangma.cn;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/19 16:24
 * 文件说明：
 */
public class TestDemo {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        /*
        *
        * */

        System.out.println(bCryptPasswordEncoder.matches("123456", "$2a$10$ZX98K8f/YIrLwSw94oRQeNtNzxATQy7A2KDVwCk4.Vsma9kjfbHm"));

    }
}
