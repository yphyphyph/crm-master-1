package com.shangma.cn.domin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 10:39
 * 文件说明：
 */
@TableName("sys_admin_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRole {
    private Long adminId;
    private Long roleId;
}
