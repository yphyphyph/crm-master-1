package com.shangma.cn.common.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/3/29 11:40
 * 文件说明：
 */
//表示 这个类在转json格式字符串时  如果属性中有null 则json字符串中不会有这个属性
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AxiosResult<T> {

    private int status;

    private String message;


    private T data;

    /**
     * 返回成功的方法
     *
     * @return
     */

    public static <T> AxiosResult<T> success() {
        return getAxiosResult(EnumStatus.OK, null);
    }


    public static <T> AxiosResult<T> success(T data) {
        return getAxiosResult(EnumStatus.OK, data);
    }


    public static <T> AxiosResult<T> error() {
        return getAxiosResult(EnumStatus.ERROR, null);
    }

    public static <T> AxiosResult<T> error(T data) {
        return getAxiosResult(EnumStatus.ERROR, data);
    }


    public static <T> AxiosResult<T> error(EnumStatus enumStatus ,T data) {
        return getAxiosResult(enumStatus, data);
    }



    private static <T> AxiosResult<T> getAxiosResult(EnumStatus enumStatus, T data) {
        return new AxiosResult<T>(enumStatus, data);
    }


    public static <T> AxiosResult<T> error(EnumStatus enumStatus) {
        return getAxiosResult(enumStatus, null);
    }

    private AxiosResult(EnumStatus enumStatus, T data) {
        this.status = enumStatus.getStatus();
        this.message = enumStatus.getMessage();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
