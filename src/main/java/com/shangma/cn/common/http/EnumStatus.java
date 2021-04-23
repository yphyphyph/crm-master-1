package com.shangma.cn.common.http;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/3/29 11:45
 * 文件说明：
 */
public enum EnumStatus {

    OK(20000, "操作成功"),
    ERROR(40000, "小奥做失败"),
    NO_LOGIN(33333, "未登录"),
    ACCOUNT_ERROR(22222, "用户名不正确"),
    CODE_ERROR(22223, "验证码不正确"),
    PASSWOED_ERROR(22224, "密码错误"),
    NO_ACTIVE(22225, "用户未激活"),
    FORM_VALICATOR_ERROR(33233, "表单校验失败"),
    Upload_NOT_IMAGE(223344, "上传的不是一个图片"),
    IMG_EXT_ERROR(223444, "上传的格式不正确"),
    UPLoad_FILE_TOO_LANGE(233344, "上传的文件太大"),
    NO_PERM(666666, "无权限"),
    ;

    private int status;

    private String message;

    EnumStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
