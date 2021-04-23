package com.shangma.cn.common.valid;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/22 11:03
 * 文件说明：
 */
public class SexConstraintValidator implements ConstraintValidator<SexList, Integer> {


    List<Integer> list;

    /**
     * 初始化的时候执行
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(SexList constraintAnnotation) {
        //拿到了 我们在代码中指定的0 1 2 如果代码中指定4 5 6  这里就拿到了4 5 6
        //代码中指定了啥 我们就拿到了啥
        int[] sex = constraintAnnotation.sex();
        list = CollectionUtils.arrayToList(sex);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //value 表示的是前端传过来的数据 返回值是个boolean
        return list.contains(value);
    }
}
