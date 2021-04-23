package com.shangma.cn.domin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 11:00
 * 文件说明：
 */
@TableName("log_login_log")
@Data
@Accessors(chain = true)
public class LoginLog {

    @TableId(type = IdType.AUTO)
    private Long loginId;
    private String adminName;

    private String requestIp;

    private String loginAddress;

    private String broswerName;

    private String osName;

    private int loginStatus;

    private String message;

    private LocalDateTime loginTime;
}
