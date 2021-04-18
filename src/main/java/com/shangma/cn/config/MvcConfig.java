package com.shangma.cn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/15 15:00
 * 文件说明：
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {



    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }
}
