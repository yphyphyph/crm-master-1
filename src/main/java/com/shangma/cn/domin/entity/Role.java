package com.shangma.cn.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.cn.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/20 9:54
 * 文件说明：
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class Role  extends BaseEntity {


    private String roleName;

    private String roleDesc;
}
