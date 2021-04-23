package com.shangma.cn.common.async;

import com.shangma.cn.common.utils.ServletUtils;
import com.shangma.cn.common.utils.SpringContainerUtils;
import com.shangma.cn.domin.entity.LoginLog;
import com.shangma.cn.mapper.LoginLogMapper;

import java.time.LocalDateTime;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 11:15
 * 文件说明：
 */
public class RunnableFactory {


    /**
     * 添加日志
     */
    public static Runnable insertLoginLog(String adminName, int status, String message) {
        LoginLogMapper bean = SpringContainerUtils.getBean(LoginLogMapper.class);
        LoginLog loginLog = new LoginLog()
                .setAdminName(adminName)
                .setLoginStatus(status)
                .setMessage(message)
                .setLoginAddress(ServletUtils.getLoginAddress(ServletUtils.getRequest()))
                .setRequestIp(ServletUtils.getRequestIp(ServletUtils.getRequest()))
                .setBroswerName(ServletUtils.getUserAgent(ServletUtils.getRequest()).getBrowser().getName())
                .setOsName(ServletUtils.getUserAgent(ServletUtils.getRequest()).getOperatingSystem().getName())
                .setLoginTime(LocalDateTime.now());

        Runnable runnable = () -> {
            bean.insert(loginLog);
        };

        return runnable;
    }
}
