package com.shangma.cn.common.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/23 10:06
 * 文件说明：
 */
public class JsonUtils {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T str2Obj(String str, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;

    }


    public static String obj2Str(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static <T> List<T> str2List(String str, Class<T> clazz) {
        CollectionLikeType collectionLikeType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            List<T> list = objectMapper.readValue(str, collectionLikeType);

            return list;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
