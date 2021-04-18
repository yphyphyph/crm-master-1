package com.shangma.cn.domin.criteria;

import com.shangma.cn.domin.criteria.base.BaseQueryCriteria;
import lombok.Data;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/15 15:34
 * 文件说明：
 */
@Data
public class GoodCriteria extends BaseQueryCriteria {


    private String goodName;
    private String goodDesc;
    private Long brandId;

    private Long categoryId;
    private int categoryLevel;

}
