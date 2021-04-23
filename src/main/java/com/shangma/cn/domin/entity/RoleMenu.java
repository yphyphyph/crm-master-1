package com.shangma.cn.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/21 15:37
 * 文件说明：
 */
@TableName("sys_role_menu")
@Data
public class RoleMenu {

    private Long roleId;

    private Long menuId;
}
