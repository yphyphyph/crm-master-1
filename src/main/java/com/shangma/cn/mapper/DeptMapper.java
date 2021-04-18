package com.shangma.cn.mapper;

import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.mapper.base.MyMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:52
 * 文件说明：
 */
public interface DeptMapper extends MyMapper<Dept> {


    /**
     * 查询是否有孩子
     */
    @Select("select count(1) from base_dept where parent_id=#{id}")
    int getChildrenCount(Long id);
}
