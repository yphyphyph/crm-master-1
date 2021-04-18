package com.shangma.cn.mapper;

import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.mapper.base.MyMapper;
import org.apache.ibatis.annotations.Select;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:50
 * 文件说明：
 */
public interface BrandMapper extends MyMapper<Brand> {


    @Select("select brand_name from base_brand where id = #{id}")
    String getBrandNameById(Long id);
}
