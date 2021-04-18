package com.shangma.cn.domin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shangma.cn.domin.vo.base.BaseVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/16 10:30
 * 文件说明：
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryVo extends BaseVo {

    /**
     * 分类名称
     */
    private String catetoryName;

    /**
     * 分类父id 如果是顶级分类 父id 为0
     */
    private Long parentId;

    /**
     * 分类等级 一共三级分类 1 2 3
     */
    private Integer categoryLevel;

    Boolean hasChildren;


    public List<CategoryVo> children;

    Boolean isLeaf;

}
