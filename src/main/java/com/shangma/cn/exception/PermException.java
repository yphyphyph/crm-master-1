package com.shangma.cn.exception;

import com.shangma.cn.common.http.EnumStatus;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 17:46
 * 文件说明：
 */
public class PermException  extends RuntimeException{

    private EnumStatus enumStatus;

    public PermException(EnumStatus enumStatus) {
        this.enumStatus = enumStatus;
    }

    public EnumStatus getEnumStatus() {
        return enumStatus;
    }

    public void setEnumStatus(EnumStatus enumStatus) {
        this.enumStatus = enumStatus;
    }
}
