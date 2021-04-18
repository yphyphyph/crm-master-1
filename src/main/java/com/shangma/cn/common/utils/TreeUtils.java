package com.shangma.cn.common.utils;

import com.shangma.cn.common.reflect.ReflectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/16 10:44
 * 文件说明：
 */
public class TreeUtils {


    /**
     * 通过父亲id=0  从一级开始往下排的方法
     *
     * @param list
     * @param <T>
     * @return
     */

    public static <T> List<T> buildTree(List<T> list) {
        //获取到一级分类
        List<T> root = list.stream().filter(item -> (long) ReflectionUtils.getFieldValue(item, "parentId") == 0).collect(Collectors.toList());
        list.removeAll(root);
        root.forEach(item -> {
            getChildren(item, list);
        });
        return root;
    }


    public static <T> void getChildren(T t, List<T> list) {
        if (hasChildren(t, list)) {
            List<T> collect = list.stream().filter(item -> (long) ReflectionUtils.getFieldValue(item, "parentId") == (long) ReflectionUtils.getFieldValue(t, "id")).collect(Collectors.toList());
            if (collect != null && collect.size() > 0) {
                ReflectionUtils.setFieldValue(t, "children", collect);
                list.removeAll(collect);
                collect.forEach(item1 -> getChildren(item1, list));
            }
        }
    }


    public static <T> boolean hasChildren(T t, List<T> list) {
        return list.stream().anyMatch(item -> {
            long a = (long) ReflectionUtils.getFieldValue(item, "parentId");
            long b = (long) ReflectionUtils.getFieldValue(t, "id");
            return a == b;
        });

    }
}
