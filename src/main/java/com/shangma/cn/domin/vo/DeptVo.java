package com.shangma.cn.domin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shangma.cn.domin.vo.base.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/18 10:38
 * 文件说明：
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeptVo extends BaseVo {

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门的上级部门id 如果是一级部门 则父id为0
     */
    private Long parentId;

    /**
     * 部门排序
     */
    private Integer deptSort;

    /**
     * 部门描述
     */
    private String deptDesc;


    /**
     * 是否有孩子
     */
    Boolean hasChildren;

    /**
     * 孩子
     */
    List<DeptVo> children;

    /**
     * 是否是叶子节点  结果和hasChildren相反的值
     */

    Boolean isLeaf;


}
